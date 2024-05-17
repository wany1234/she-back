package com.she.vendor.education.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.vendor.model.VendorEducation;

@Mapper
@Repository("com.she.vendor.education.mapper.VendorEducationMapper")
public interface VendorEducationMapper {

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
     */
    public List<VendorEducation> getVendorEducationList(@Param("plantCd") String plantCd,
            @Param("eduAttCd") String eduAttCd, @Param("safEduCourseNo") int safEduCourseNo,
            @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("vendorNm") String vendorNm,
            @Param("workerNm") String workerNm, @Param("vendorCd") String vendorCd);
}
