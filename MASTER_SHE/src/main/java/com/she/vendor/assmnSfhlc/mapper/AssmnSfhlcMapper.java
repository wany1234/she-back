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
package com.she.vendor.assmnSfhlc.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.vendor.model.AssmnSfhlc;
import com.she.vendor.model.AssmnSfhlcItemList;
import com.she.vendor.model.AssmnSfhlcVendorList;

@Mapper
@Repository("com.she.vendor.assmnSfhlc.mapper.AssmnSfhlcMapper")
public interface AssmnSfhlcMapper {

    /**
     * 평가 및 안전보건 계획 목록 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 목록 조회
     * @throws Exception
     */
    public List<AssmnSfhlc> getPlanmgmtLists(@Param("plantCd") String plantCd, @Param("year") String year, @Param("evalClsCd") String evalClsCd, @Param("evalTitle") String evalTitle, @Param("mainDeptCd") String mainDeptCd, @Param("deptSubYn") String deptSubYn, @Param("stateCd") String stateCd, @Param("procStepCd") String procStepCd,
            @Param("yearChk") String yearChk, @Param("deptCd") String deptCd, @Param("deptSubYn2") String deptSubYn2, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가 및 안전보건 계획 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 조회
     * @throws Exception
     */
    public AssmnSfhlc getPlanmgmtInfo(@Param("vendorEvalPlanNo") int vendorEvalPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가 및 안전보건 계획 > 대상협력업체
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 대상협력업체
     * @throws Exception
     */
    public List<AssmnSfhlcVendorList> getAssmnSfhlVendorList(@Param("vendorEvalPlanNo") int vendorEvalPlanNo) throws Exception;

    /**
     * 평가 및 안전보건 계획 > 평가항목
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 평가항목
     * @throws Exception
     */
    public List<AssmnSfhlcItemList> getAssmnSfhlcItemList(@Param("vendorEvalPlanNo") int vendorEvalPlanNo) throws Exception;

    /**
     * 평가 및 안전보건 계획 > 평가항목(기본조회)
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 평가항목(기본조회)
     * @throws Exception
     */
    public List<AssmnSfhlcItemList> searchAssmnSfhlcItemList(@Param("evalPlanVendorNo") int evalPlanVendorNo, @Param("plantCd") String plantCd, @Param("evalClsCd") String evalClsCd) throws Exception;

    /**
     * 평가 및 안전보건 계획 등록
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 등록
     * @throws Exception
     */
    public Integer createAssmnSfhlc(AssmnSfhlc assmnSfhlc) throws Exception;

    /**
     * 평가 및 안전보건 계획 > 대상협력업체 등록
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 대상협력업체 등록
     * @throws Exception
     */
    public Integer createAssmnSfhlcVendor(AssmnSfhlcVendorList assmnSfhlc) throws Exception;

    /**
     * 평가 및 안전보건 계획 > 평가항목 등록
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 평가항목 등록
     * @throws Exception
     */
    public Integer createAssmnSfhlcItemList(AssmnSfhlcItemList assmnSfhlc) throws Exception;

    /**
     * 평가 및 안전보건 계획 수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 수정
     * @throws Exception
     */
    public Integer updateAssmnSfhlc(AssmnSfhlc assmnSfhlc) throws Exception;

    /**
     * 평가 및 안전보건 계획 > 대상협력업체 수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 대상협력업체 수정
     * @throws Exception
     */
    public Integer updateAssmnSfhlcVendor(AssmnSfhlcVendorList assmnSfhlc) throws Exception;

    /**
     * 평가 및 안전보건 계획 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 삭제
     * @throws Exception
     */
    public Integer deleteAssmnSfhlc(int vendorEvalPlanNo) throws Exception;

    public Integer deleteAssmnSfhlcVendor(int vendorEvalPlanNo) throws Exception;

    public Integer deleteAssmnSfhlcItemList(int vendorEvalPlanNo) throws Exception;

    /**
     * 평가 및 안전보건 계획 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가 및 안전보건 계획 삭제 가능 확인
     * @throws Exception
     */
    public int getPlanmgmtStatus(@Param("vendorEvalPlanNo") int vendorEvalPlanNo) throws Exception;

    /**
     * 평가 및 안전보건 계획 대상협력업체 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 대상협력업체 삭제
     * @throws Exception
     */
    public int deleteAssmnSfhlcVendorEach(@Param("vendorEvalPlanNo") int vendorEvalPlanNo, @Param("evalPlanVendorNo") int evalPlanVendorNo) throws Exception;

    /**
     * 평가 및 안전보건 계획 결재상태수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 결재상태수정
     * @throws Exception
     */
    public int updateAppr(@Param("vendorEvalPlanNo") int vendorEvalPlanNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd);

    /**
     * 평가 및 안전보건 계획 대상협력업체 팝업
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 대상협력업체 팝업
     * @throws Exception
     */
    public List<AssmnSfhlcVendorList> getPlanmgmtVendorList(@Param("vendorEvalPlanNo") int vendorEvalPlanNo, @Param("apprFlag") String apprFlag, @Param("procStepCd") String procStepCd) throws Exception;

    /**
     * 평가 및 안전보건 결과 목록
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 목록
     * @throws Exception
     */
    public List<AssmnSfhlcVendorList> getResultList(@Param("plantCd") String plantCd, @Param("evalClsCd") String evalClsCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("evalTitle") String evalTitle, @Param("vendorNm") String vendorNm, @Param("procStepCd") String procStepCd, @Param("stateCd") String stateCd,
            @Param("costRefYn") String costRefYn, @Param("deptSubYn") String deptSubYn, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가 및 안전보건 결과 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 조회
     * @throws Exception
     */
    public AssmnSfhlcVendorList getResultmgmtInfo(@Param("evalPlanVendorNo") int evalPlanVendorNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가 및 안전보건 결과 결재상태수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 결재상태수정
     * @throws Exception
     */
    public int updateResultAppr(@Param("evalPlanVendorNo") int evalPlanVendorNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd);

    /**
     * 평가 및 안전보건 비용 현황 목록
     *
     * @param parameter
     * @return 평가 및 안전보건 비용 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAssmnSfhlcStatus(@Param("plantCd") String plantCd, @Param("year") String year, @Param("allSearchYn") String allSearchYn) throws Exception;

    /**
     * 평가 및 안전보건 비용 현황 팝업
     *
     * @param parameter
     * @return 평가 및 안전보건 비용 현황 팝업
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAssmnSfhlcStatusPopupList(@Param("plantCd") String plantCd, @Param("year") String year, @Param("searchFlag") String searchFlag, @Param("monFlag") String monFlag) throws Exception;

    public List<HashMap<String, Object>> getAssmnSfhlcResultStatusPopupList(@Param("plantCd") String plantCd, @Param("year") String year, @Param("searchFlag") String searchFlag, @Param("monFlag") String monFlag) throws Exception;

    /**
     * 평가 및 안전보건 비용 계획 평가항목 조회
     * 
     * @param vendorEvalPlanNo
     * @return
     * @throws Exception
     */
    public List<AssmnSfhlcItemList> getPlanEvalItemList(@Param("vendorEvalPlanNo") int vendorEvalPlanNo, @Param("evalPlanVendorNo") int evalPlanVendorNo, @Param("type") String type) throws Exception;

    /**
     * 평가 및 안전보건 비용 계획 평가항목 결과 제거
     * 
     * @param vendorEvalPlanNo
     * @return
     * @throws Exception
     */
    public int deletePlanEvalItemList(@Param("vendorEvalPlanNo") int vendorEvalPlanNo) throws Exception;

    /**
     * 평가 및 안전보건 비용 계획 평가항목 결과 수정
     * 
     * @param assmnSfhlc
     * @return
     * @throws Exception
     */
    public int updateVendorEvalItemRslt(AssmnSfhlcItemList assmnSfhlcItemList) throws Exception;
}
