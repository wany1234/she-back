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
package com.she.psm.processFacility.mapper;

import java.util.List;

import com.she.psm.model.ChemprodPrint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.Chemprod;
import com.she.safety.model.FacilityChemprod;

@Mapper
@Repository("com.she.psm.processFacility.mapper.FacilityChemprodMapper")
public interface FacilityChemprodMapper {

    /**
     * 유해위험요인 조회
     *
     * @param plantCd
     *            사업장
     * @param chemProdNmKr
     *            취급물질명
     * @return 설비별 취급물질 목록
     * @throws Exception
     */
    public List<Chemprod> getRiskChemprodchems(@Param("plantCd") String plantCd,
                                        @Param("chemProdNmKr") String chemProdNmKr, @Param("casNo") String casNo,
                                        @Param("saveChemprodNos") int[] saveChemprodNos, @Param("chemProdNo") int chemProdNo) throws Exception;


    /**
     * 설비별 취급물질 조회
     *
     * @param plantCd
     *            사업장
     * @return 설비별 취급물질 목록
     * @throws Exception
     */
    public List<ChemprodPrint> geChemProdNms(@Param("plantCd") String plantCd) throws Exception;

    /**
     * /**
     *      * 설비별 취급물질 조회
     *      *
     *      * @param plantCd
     *      *            사업장
     *      * @param chemProdNmKr
     *      *            취급물질명
     *      * @param safFacilityCd
     *      *            설비 코드
     *      * @param equipmentNo
     *      *            설비별 장치번호
     *      * @return 설비별 취급물질 목록
     *      * @throws Exception
     *      */

    public List<FacilityChemprod> getFacilityChemprods(@Param("plantCd") String plantCd,
                                                @Param("chemProdNmKr") String chemProdNmKr, @Param("safFacilityCd") String safFacilityCd, @Param("equipmentNo") int equipmentNo) throws Exception;


    /** * 설비별 취급물질 등록
     *
     * @param chemprod
     * @return 변경 행 수
     * @throws Exception
     */
    public int createFacilityChemprod(Chemprod chemprod) throws Exception;

    /**
     * 설비별 취급물질 삭제
     *
     * @param safFacilityCd
     * @param chemProdNo
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteFacilityChemprod(@Param("safFacilityCd") String safFacilityCd, @Param("chemProdNo") int chemProdNo,
                               @Param("equipmentNo") int equipmentNo) throws Exception;

}
