package com.she.vendor.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.vendor.education.mapper.VendorEducationMapper;
import com.she.vendor.model.VendorEducation;

@Service
public class VendorEducationService {

    @Autowired
    private VendorEducationMapper vendorEducationMapper;

    /**
     * 협력업체 교육 목록 조회
     * 
     * @param plantCd
     * @param eduAttCd
     * @param safEduCourseNo
     * @param startDate
     * @param endDate
     * @param vendorNm
     * @param workerNm
     * @param vendorCd
     * @return
     * @throws Exception
     */
    public List<VendorEducation> getVendorEducationList(String plantCd, String eduAttCd, int safEduCourseNo,
            String startDate, String endDate, String vendorNm, String workerNm, String vendorCd) throws Exception {
        return vendorEducationMapper.getVendorEducationList(plantCd, eduAttCd, safEduCourseNo, startDate, endDate,
                vendorNm, workerNm, vendorCd);
    }
}
