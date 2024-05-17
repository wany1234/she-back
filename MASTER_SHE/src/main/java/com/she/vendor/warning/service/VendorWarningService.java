package com.she.vendor.warning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.vendor.model.VendorWarning;
import com.she.vendor.warning.mapper.VendorWarningMapper;

@Service
public class VendorWarningService {

    @Autowired
    private VendorWarningMapper vendorWarningMapper;

    /**
     * 경고 및 지적현황 목록 조회
     * 
     * @param plantCd
     * @param deptCd
     * @param startDate
     * @param endDate
     * @param tgtVendorCd
     * @return
     * @throws Exception
     */
    public List<VendorWarning> getVendorWarningList(String plantCd, String deptCd, String startDate, String endDate,
            String tgtVendorCd, String checkTitle) throws Exception {
        return vendorWarningMapper.getVendorWarningList(plantCd, deptCd, startDate, endDate, tgtVendorCd, checkTitle);
    }
}
