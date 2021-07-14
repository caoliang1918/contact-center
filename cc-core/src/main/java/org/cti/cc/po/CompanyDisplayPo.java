package org.cti.cc.po;

import org.cti.cc.entity.CompanyDisplay;
import org.cti.cc.entity.CompanyPhone;

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
