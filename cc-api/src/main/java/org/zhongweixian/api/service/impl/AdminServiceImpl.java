package org.zhongweixian.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.AdminMenu;
import org.cti.cc.entity.AdminRole;
import org.cti.cc.entity.AdminUser;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.mapper.AdminMenuMapper;
import org.cti.cc.mapper.AdminRoleMapper;
import org.cti.cc.mapper.AdminUserMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.*;
import org.cti.cc.util.AuthUtil;
import org.cti.cc.vo.AdminLogin;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.api.exception.BusinessException;
import org.zhongweixian.api.service.AdminService;
import org.zhongweixian.api.util.BcryptUtil;
import org.zhongweixian.api.vo.server.MenuVo;
import org.zhongweixian.api.vo.server.RoleVo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2022/1/7
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminUser> implements AdminService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public AdminLoginResult login(AdminLogin adminLogin) {
        AdminUser adminUser = adminUserMapper.adminLogin(adminLogin.getUsername());
        if (adminUser == null) {
            logger.warn("user:{} login error", adminLogin.getUsername());
            throw new BusinessException(ErrorCode.ACCOUNT_ERROR);
        }
        if (adminUser.getStatus() == 0) {
            throw new BusinessException(ErrorCode.ACCOUNT_DISABLED);
        }
        //验证密码
        try {
            if (!BcryptUtil.checkPwd(adminLogin.getPasswd(), adminUser.getPasswd())) {
                throw new BusinessException(ErrorCode.ACCOUNT_ERROR);
            }
        } catch (Exception e) {
            logger.error("user:{} password is error", adminLogin.getUsername());
            throw new BusinessException(ErrorCode.ACCOUNT_ERROR);
        }

        CompanyInfo companyInfo = companyMapper.selectById(adminUser.getCompanyId());
        if (companyInfo == null) {
            //企业禁用
            throw new BusinessException(ErrorCode.COMPANY_NOT_AVALIABLE);
        }
        String token = AuthUtil.createToken(adminUser.getUsername(), adminUser.getCompanyId(), companyInfo.getSecretKey());

        AdminLoginResult result = new AdminLoginResult();
        result.setCts(Instant.now().getEpochSecond());
        result.setToken(token);
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar(adminUser.getAvatar());
        userInfo.setOssServer(ossServer);
        userInfo.setUsername(adminUser.getUsername());
        userInfo.setCompanyName(companyInfo.getName());
        userInfo.setCompanyCode(companyInfo.getCompanyCode());
        userInfo.setGmt(companyInfo.getGmt());
        userInfo.setClientIp(adminLogin.getClientIp());
        result.setUserInfo(userInfo);
        result.setMenus(getMenus(adminUser.getId()));
        redisTemplate.opsForValue().set("token:" + token, result);
        return result;
    }

    @Override
    public Boolean logout(String token) {
        return redisTemplate.delete("token:" + token);
    }

    @Override
    public List<AdminMenu> menusList(Map<String, Object> params) {
        return adminMenuMapper.menusList(params);
    }

    @Override
    public int updateRoleMenus(List<Long> ids) {
        return 0;
    }


    private List<MenusPo> getMenus(Long uid) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("menuLevel", 1);
        return userMenuIterator(params);
    }

    @Override
    public Integer saveOrUpdateMenus(MenuVo menusVo) {
        AdminMenu adminMenu = new AdminMenu();
        BeanUtils.copyProperties(menusVo, adminMenu);
        if (StringUtils.isBlank(menusVo.getMenuId())) {
            adminMenu.setMenuId(RandomStringUtils.randomAlphanumeric(32));
            adminMenu.setUts(Instant.now().getEpochSecond());
            adminMenu.setUts(adminMenu.getCts());
            return adminMenuMapper.insertSelective(adminMenu);
        }
        adminMenu.setUts(Instant.now().getEpochSecond());
        return adminMenuMapper.updateByPrimaryKeySelective(adminMenu);
    }

    @Override
    public Integer deleteMenus(String menuId) {
        AdminMenu adminMenu = adminMenuMapper.selectByMenuId(menuId);
        if (adminMenu == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        if (adminMenu.getChildNum() != null && adminMenu.getChildNum() > 0) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR, "子节点存在");
        }
        return adminMenuMapper.deleteMenus(menuId);
    }


    @Override
    public PageInfo<RolePo> getRoleList(Map<String, Object> params) {
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<RolePo> list = adminRoleMapper.selectRoleMenuList(params);
        return new PageInfo<RolePo>(list);
    }

    @Override
    public Integer saveOrUpdateRole(RoleVo roleVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", roleVo.getCompanyId());
        params.put("name", roleVo.getName());
        List<AdminRole> roleList = adminRoleMapper.selectList(params);
        if (!CollectionUtils.isEmpty(roleList)) {
            if (roleVo.getId() == null || !roleVo.getId().equals(roleList.get(0).getId())) {
                throw new BusinessException(ErrorCode.DUPLICATE_EXCEPTION);
            }
        }
        AdminRole adminRole = new AdminRole();
        BeanUtils.copyProperties(roleVo, adminRole);
        if (roleVo.getId() == null) {
            adminRole.setCts(Instant.now().getEpochSecond());
            adminRole.setUts(adminRole.getCts());
            return adminRoleMapper.insertSelective(adminRole);
        }
        adminRole.setUts(Instant.now().getEpochSecond());
        return adminRoleMapper.updateByPrimaryKeySelective(adminRole);
    }

    /**
     * 登录账号菜单
     *
     * @param params
     * @return
     */
    private List<MenusPo> userMenuIterator(Map<String, Object> params) {
        List<AdminMenu> adminMenus = adminMenuMapper.selectUserMenus(params);
        if (CollectionUtils.isEmpty(adminMenus)) {
            return null;
        }
        List<MenusPo> menusPoList = new ArrayList<>();
        for (AdminMenu child : adminMenus) {
            MenusPo childPo = new MenusPo();
            params.put("parentId", child.getMenuId());
            params.remove("menuLevel");
            BeanUtils.copyProperties(child, childPo);
            childPo.setChilds(userMenuIterator(params));
            menusPoList.add(childPo);
        }
        return menusPoList;
    }


    @Override
    BaseMapper<AdminUser> baseMapper() {
        return adminUserMapper;
    }
}
