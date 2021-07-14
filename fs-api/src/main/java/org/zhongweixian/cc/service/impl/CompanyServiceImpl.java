package org.zhongweixian.cc.service.impl;

import org.cti.cc.entity.Company;
import org.cti.cc.entity.VdnCode;
import org.cti.cc.mapper.*;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.CompanyInfo;
import org.cti.cc.po.VdnCodePo;
import org.cti.cc.po.VdnSchedulePo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.service.CompanyService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by caoliang on 2020/11/6
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private RouteGroupMapper routeGroupMapper;

    @Autowired
    private VdnCodeMapper vdnCodeMapper;

    @Autowired
    private VdnScheduleMapper vdnScheduleMapper;

    @Override
    public Map<Long, CompanyInfo> initAll() {
        List<CompanyInfo> companies = companyMapper.selectCompanyInfoList(null);
        if (CollectionUtils.isEmpty(companies)) {
            return null;
        }
        return companies.stream().collect(Collectors.toMap(CompanyInfo::getId, Function.identity()));
    }

    @Override
    public void initVdn(CompanyInfo companyInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyInfo.getId());
        List<VdnCode> vdnCodes = vdnCodeMapper.selectListByMap(params);
        if (CollectionUtils.isEmpty(vdnCodes)) {
            return;
        }
        Map<Long, VdnCodePo> vdnCodePoMap = new HashMap<>();

        for (VdnCode vdnCode : vdnCodes) {
            VdnCodePo vdnCodePo = new VdnCodePo();
            BeanUtils.copyProperties(vdnCode, vdnCodePo);
            if (vdnCode.getStatus() == 0) {
                continue;
            }

            List<VdnSchedulePo> vdnSchedulePoList = vdnScheduleMapper.selectByVdn(vdnCode.getId());
            Iterator<VdnSchedulePo> iterator = vdnSchedulePoList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getScheduleConfig() == null) {
                    iterator.remove();
                }
            }
            Collections.sort(vdnSchedulePoList);
            vdnCodePo.setVdnSchedulePoList(vdnSchedulePoList);
            vdnCodePoMap.put(vdnCode.getId(), vdnCodePo);
        }
        companyInfo.setVdnCodeMap(vdnCodePoMap);
    }

    @Override
    BaseMapper<Company> baseMapper() {
        return companyMapper;
    }
}
