package com.she.vendor.warning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.vendor.model.VendorWarning;

@Mapper
@Repository("com.she.vendor.warning.mapper.VendorWarningMapper")
public interface VendorWarningMapper {

    /**
     * 경고 및 지적현황 목록 조회
     * 
     * @param plantCd
     * @param deptCd
     * @param startDate
     * @param endDate
     * @param tgtVendorCd
     * @return
     */
    public List<VendorWarning> getVendorWarningList(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd,
            @Param("startDate") String startDate, @Param("endDate") String endDate,
            @Param("tgtVendorCd") String tgtVendorCd, @Param("checkTitle") String checkTitle);
}
