/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.env.air.sems.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SEMS MAPPER
 *
 */
@Mapper
@Repository("com.she.env.air.sems.mapper.OpLogSemsMapper")
public interface OpLogSemsMapper {
    /**
     * SEMS 조건별 조회
     *
     * @param fromYmd,
     *            toYmd, plantCd, eairSemsRptTypeCd, deptCd 시작일자, 종료일자, 사업장코드,
     *            검색 조건, 부서코드
     * @return SEMS 목록
     * @throws Exception
     */

    public List<LinkedHashMap<String, Object>> getSems(@Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd, @Param("ymCols") List<String> ymCols, @Param("plantCd") String plantCd, @Param("eairSemsRptTypeCd") String eairSemsRptTypeCd, @Param("deptCd") String deptCd, @Param("ymColStr") String ymColStr,
            @Param("mainDischFacNm") String mainDischFacNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
