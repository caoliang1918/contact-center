package com.voice9.core.po;

import com.voice9.core.entity.CompanyDisplay;
import com.voice9.core.entity.CompanyPhone;

import java.util.List;

public class CompanyDisplayPo extends CompanyDisplay {

    /**
     * 号码
     */
    private List<CompanyPhone> companyPhoneList;

    public List<CompanyPhone> getCompanyPhoneList() {
        return companyPhoneList;
    }

    public void setCompanyPhoneList(List<CompanyPhone> companyPhoneList) {
        this.companyPhoneList = companyPhoneList;
    }
}
