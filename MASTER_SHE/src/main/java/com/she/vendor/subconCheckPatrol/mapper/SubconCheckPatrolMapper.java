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
package com.she.vendor.subconCheckPatrol.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.CodeMaster;
import com.she.safety.model.CheckItem;
import com.she.vendor.model.SubconCheckInspector;
import com.she.vendor.model.SubconCheckItemResult;
import com.she.vendor.model.SubconCheckResult;

@Mapper
@Repository("com.she.vendor.subconCheckPatrol.mapper.SubconCheckPatrolMapper")
public interface SubconCheckPatrolMapper {
    /**
     * 협력업체자체점검 목록 조회
     * 
     * @param plantCd
     *            사업장
     * @param checkKindCd
     *            점검종류코드
     * @param fromDt
     *            시작일
     * @param toDt
     *            종료일
     * @param vendorCd
     *            업체코드
     * @param vendorNm
     *            업체명
     * @return 협력업체평가 목록
     * @throws Exception
     */
    public List<SubconCheckResult> getSubconCheckResults(@Param("plantCd") String plantCd, @Param("checkKindCd") String checkKindCd, @Param("fromDt") String fromDt, @Param("toDt") String toDt, @Param("checkStepCd") String checkStepCd, @Param("vendorCd") String vendorCd, @Param("vendorNm") String vendorNm, @Param("checkTitle") String checkTitle) throws Exception;

    /**
     * 협력업체자체점검종류 조회
     * 
     * @param plantCd
     *            사업장
     * @return 협력업체자체점검종류
     * @throws Exception
     */
    public List<CodeMaster> getSubconCheckKinds() throws Exception;

    /**
     * 협력업체자체점검 결과 상세 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체평가
     * @throws Exception
     */
    public SubconCheckResult getSubconCheckResult(@Param("vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception;

    /**
     * 협력업체자체점검결과 항목별결과 목록 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체평가
     * @throws Exception
     */
    public List<SubconCheckItemResult> getSubconCheckItemResults(@Param("safCheckKindNo") int safCheckKindNo, @Param("vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception;

    /**
     * 협력업체자체점검결과 점검자 목록 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체평가
     * @throws Exception
     */
    public List<SubconCheckInspector> getSubconCheckInspectors(@Param("vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception;

    /**
     * 협력업체자체점검결과 개선사항 목록 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체평가
     * @throws Exception
     */
    // public List<SubconCheckImprAct>
    // getSubconCheckImprActs(@Param("vendorCheckRsltNo") int vendorCheckRsltNo)
    // throws Exception;

    /**
     * 점검항목 목록 조회
     * 
     * @param safCheckKindNo
     * @return
     * @throws Exception
     */
    public List<CheckItem> getCheckItemList(@Param("safCheckKindNo") String safCheckKindNo) throws Exception;

    /**
     * 업체자체점검결과 등록
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    public int createCheckResult(SubconCheckResult subconCheckResult) throws Exception;

    /**
     * 항목별점검결과 등록
     * 
     * @param subconCheckItemResult
     * @return
     * @throws Exception
     */
    public int createCheckItemResult(SubconCheckItemResult subconCheckItemResult) throws Exception;

    /**
     * 업체자체점검 점검자 등록
     * 
     * @param subconCheckInspector
     * @return
     * @throws Exception
     */
    public int createCheckInspector(SubconCheckInspector subconCheckInspector) throws Exception;

    /**
     * 업체자체점검결과 수정
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    public int updateCheckResult(SubconCheckResult subconCheckResult) throws Exception;

    /**
     * 업체자체점검 항목별점검결과 삭제
     * 
     * @param vendorCheckRsltNo
     * @return
     * @throws Exception
     */
    public int deleteCheckItemRslt(@Param("vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception;

    /**
     * 업체자체점검 점검자 삭제
     * 
     * @param vendorCheckRsltNo
     * @return
     * @throws Exception
     */
    public int deleteCheckInspectPsn(@Param("vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception;

    /**
     * 업체자체점검결과 삭제
     * 
     * @param vendorCheckRsltNo
     * @return
     * @throws Exception
     */
    public int deleteCheckResult(@Param("vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception;
}
