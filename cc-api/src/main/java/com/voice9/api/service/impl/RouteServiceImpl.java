package com.voice9.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.voice9.api.service.RouteService;
import com.voice9.core.entity.RouteCall;
import com.voice9.core.entity.RouteGetway;
import com.voice9.core.entity.RouteGetwayGroup;
import com.voice9.core.entity.RouteGroup;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.mapper.RouteCallMapper;
import com.voice9.core.mapper.RouteGetwayGroupMapper;
import com.voice9.core.mapper.RouteGetwayMapper;
import com.voice9.core.mapper.RouteGroupMapper;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.RouteCallInfo;
import com.voice9.core.po.RouteGroupPo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.voice9.api.exception.BusinessException;
import com.voice9.core.vo.RouteCallVo;
import com.voice9.core.vo.RouteGetwayVo;
import com.voice9.core.vo.RouteGroupVo;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteServiceImpl extends BaseServiceImpl<RouteCallInfo> implements RouteService {

    @Autowired
    private RouteCallMapper routeCallMapper;

    @Autowired
    private RouteGroupMapper routeGroupMapper;

    @Autowired
    private RouteGetwayGroupMapper routeGetwayGroupMapper;

    @Autowired
    private RouteGetwayMapper routeGetwayMapper;

    @Override
    BaseMapper<RouteCallInfo> baseMapper() {
        return routeCallMapper;
    }

    @Override
    public PageInfo<RouteGetway> findRouteGetwayByPage(Map<String, Object> params) {
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<RouteGetway> list = routeGetwayMapper.selectListByMap(params);
        return new PageInfo<>(list);
    }

    @Override
    public RouteGetway findRoutewayById(Long id) {
        RouteGetway routeGetway = routeGetwayMapper.selectByPrimaryKey(id);
        if (routeGetway == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        return routeGetway;
    }

    @Override
    public int delRoutewayById(Long id) {
        RouteGetway routeGetway = routeGetwayMapper.selectByPrimaryKey(id);
        if (routeGetway == null || routeGetway.getStatus() == 0) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        //判断是否被引用
        RouteGroupPo routeGroupPo = routeGroupMapper.selectByGetwayId(id);
        if (routeGroupPo != null) {
            throw new BusinessException(ErrorCode.DATA_IS_USED);
        }
        routeGetway.setUts(Instant.now().getEpochSecond());
        routeGetway.setStatus(0);
        routeGetway.setName(routeGetway.getName() + randomDelete());
        return routeGetwayMapper.updateByPrimaryKeySelective(routeGetway);
    }

    @Override
    public int saveOrUpdateRouteGetway(RouteGetwayVo routeGetwayVo) {
        RouteGetway routeGetway = new RouteGetway();
        BeanUtils.copyProperties(routeGetwayVo, routeGetway);
        //判断数据是否存在
        Map<String, Object> params = new HashMap<>();
        params.put("routeName", routeGetwayVo.getName());
        List<RouteGetway> list = routeGetwayMapper.selectListByMap(params);
        if (!CollectionUtils.isEmpty(list)) {
            if (routeGetway.getId() == null ||
                    (routeGetway.getId() != null && !routeGetway.getId().equals(list.get(0).getId()))) {
                throw new BusinessException(ErrorCode.DUPLICATE_EXCEPTION);
            }
        }

        if (routeGetwayVo.getId() == null) {
            routeGetway.setCts(Instant.now().getEpochSecond());
            return routeGetwayMapper.insertSelective(routeGetway);
        }
        RouteGetway exist = routeGetwayMapper.selectByPrimaryKey(routeGetwayVo.getId());
        if (exist == null) {
            new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        routeGetway.setUts(Instant.now().getEpochSecond());
        return routeGetwayMapper.updateByPrimaryKeySelective(routeGetway);
    }


    @Override
    public PageInfo<RouteGroup> findRouteGroupByPage(Map<String, Object> params) {
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<RouteGroup> list = routeGroupMapper.selectListByMap(params);
        return new PageInfo<>(list);
    }

    @Override
    public RouteGroupPo findRouteGroup(Long id) {
        return routeGroupMapper.selectById(id);
    }

    @Override
    public PageInfo<RouteCallInfo> findRouteCallByPage(Map<String, Object> params) {
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<RouteCallInfo> list = routeCallMapper.selectListByMap(params);
        return new PageInfo<>(list);
    }

    @Override
    public RouteCallInfo findRouteCall(Map<String, Object> params) {
        List<RouteCallInfo> list = routeCallMapper.selectListByMap(params);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        return routeCallMapper.selectListByMap(params).get(0);
    }

    @Override
    public int saveOrUpdateRouteCall(RouteCallVo routeCallVo) {
        if (routeCallVo.getNumMax() < routeCallVo.getNumMin()) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR.getCode(), "字冠匹配长度错误");
        }
        RouteGroupPo routeGroup = routeGroupMapper.selectById(routeCallVo.getRouteGroupId());
        if (routeGroup == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST.getCode(), "路由组id不存在");
        }

        //判断数据是否存在
        Map<String, Object> params = new HashMap<>();
        params.put("name", routeCallVo.getRouteNum());
        params.put("companyId", routeCallVo.getCompanyId());
        List<RouteCall> list = routeCallMapper.selectList(params);
        if (!CollectionUtils.isEmpty(list)) {
            if (routeCallVo.getId() == null ||
                    (routeCallVo.getId() != null && !routeCallVo.getId().equals(list.get(0).getId()))) {
                throw new BusinessException(ErrorCode.DUPLICATE_EXCEPTION);
            }
        }
        RouteCall routeCall = new RouteCall();
        BeanUtils.copyProperties(routeCallVo, routeCall);
        if (routeCall.getId() == null) {
            routeCall.setCts(Instant.now().getEpochSecond());
            return routeCallMapper.insertSelective(routeCall);
        }
        RouteCall exist = routeCallMapper.selectByPrimaryKey(routeCall.getId());
        if (exist == null || exist.getStatus() == 0) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        routeCall.setUts(Instant.now().getEpochSecond());
        return routeCallMapper.updateByPrimaryKeySelective(routeCall);
    }

    @Override
    public int saveOrUpdateRouteGroup(RouteGroupVo routeGroupVo) {
        //先验证网关是不是都存在
        List<Long> getwayIds = routeGroupVo.getRouteGetways();
        if (CollectionUtils.isEmpty(getwayIds)) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR);
        }
        List<RouteGetway> routeGetways = routeGetwayMapper.selectByIds(getwayIds);
        if (routeGetways.size() != getwayIds.size()) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR);
        }
        //判断名称是否重复
        Map<String, Object> params = new HashMap<>();
        params.put("routeGroupName", routeGroupVo.getRouteGroup());
        List<RouteGroup> routeGroupList = routeGroupMapper.selectListByMap(params);
        if (!CollectionUtils.isEmpty(routeGroupList)) {
            RouteGroup exist = routeGroupList.get(0);
            if (routeGroupVo.getId() == null || !exist.getId().equals(routeGroupVo.getId())) {
                throw new BusinessException(ErrorCode.DUPLICATE_EXCEPTION);
            }
        }


        if (routeGroupVo.getId() == null) {
            RouteGroup routeGroup = new RouteGroup();
            BeanUtils.copyProperties(routeGroupVo, routeGroup);
            routeGroupMapper.insertSelective(routeGroup);
            routeGroupList = routeGroupMapper.selectListByMap(params);
            Long id = routeGroupList.get(0).getId();
            //插入关联关系
            RouteGetwayGroup routeGetwayGroup = null;
            for (RouteGetway routeGetway : routeGetways) {
                routeGetwayGroup = new RouteGetwayGroup();
                routeGetwayGroup.setCts(Instant.now().getEpochSecond());
                routeGetwayGroup.setUts(null);
                routeGetwayGroup.setGetwayId(routeGetway.getId());
                routeGetwayGroup.setRouteGroupId(id);
                routeGetwayGroupMapper.insertSelective(routeGetwayGroup);
            }
            return 1;
        }
        //修改，先判断是不是存在
        RouteGroup routeGroup = routeGroupMapper.selectByPrimaryKey(routeGroupVo.getId());
        if (routeGroup == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        routeGroup = new RouteGroup();
        BeanUtils.copyProperties(routeGroupVo, routeGroup);
        routeGroup.setCts(null);
        routeGroup.setUts(Instant.now().getEpochSecond());
        routeGroupMapper.updateByPrimaryKeySelective(routeGroup);
        //删除之前绑定的
        routeGetwayGroupMapper.deleteByRoutegroup(routeGroup.getId());
        RouteGetwayGroup routeGetwayGroup = null;
        for (RouteGetway routeGetway : routeGetways) {
            routeGetwayGroup = new RouteGetwayGroup();
            routeGetwayGroup.setCts(Instant.now().getEpochSecond());
            routeGetwayGroup.setUts(null);
            routeGetwayGroup.setGetwayId(routeGetway.getId());
            routeGetwayGroup.setRouteGroupId(routeGroup.getId());
            routeGetwayGroupMapper.insertSelective(routeGetwayGroup);
        }
        return 1;
    }

    @Override
    public int deleteRouteCall(Long companyId, Long id) {
        //判断数据是否存在
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("companyId", companyId);
        List<RouteCall> list = routeCallMapper.selectList(params);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        RouteCall routeCall = list.get(0);
        routeCall.setStatus(0);
        routeCall.setUts(Instant.now().getEpochSecond());
        routeCall.setRouteNum(routeCall.getRouteNum() + randomDelete());
        return routeCallMapper.updateByPrimaryKeySelective(routeCall);
    }

    @Override
    public int deleteRouteGroup(Long id) {
        RouteGroup routeGroup = routeGroupMapper.selectByPrimaryKey(id);
        if (routeGroup == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        //判断字冠是否引用
        List<RouteGetway> routeGetwayList = routeGetwayMapper.selectByGroupId(id);
        if (!CollectionUtils.isEmpty(routeGetwayList)) {
            throw new BusinessException(ErrorCode.DATA_IS_USED);
        }
        routeGroup.setUts(Instant.now().getEpochSecond());
        routeGroup.setStatus(0);
        routeGroup.setRouteGroup(routeGroup.getRouteGroup() + randomDelete());
        return routeGroupMapper.updateByPrimaryKeySelective(routeGroup);
    }
}
