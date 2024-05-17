package com.she.mgt.mgtTarget.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.MgtTgt;
import com.she.mgt.model.MgtTgtItemEvalRslt;
import com.she.mgt.model.MgtTgtItemPlanRslt;
import com.she.mgt.model.MgtTgtItemRslt;
import com.she.mgt.model.MgtTgtStatus;

@Mapper
@Repository("com.she.mgt.mgtTarget.mapper.MgtTargetMapper")
public interface MgtTargetMapper {

    /**
     * 목표/실적/평가 관리 목록
     * 
     * @param from
     *            대상연월 시작 to 대상연월 종료 plantCd 사업장코드 deptCd 부서코드 bizFieldCd 분야코드
     *            bizFieldItemNm 항목명 unregistered 미등록건 areaType 전사/사업장/부서 구분
     * @return MgtTgtItemEvalRslt 목표/실적/평가 목폭
     * @throws Exception
     *             예외
     */
    public List<MgtTgtItemEvalRslt> getMgtTargets(@Param("from") String from, @Param("to") String to, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("bizFieldCd") String bizFieldCd, @Param("bizFieldItemNm") String bizFieldItemNm, @Param("unregistered") String unregistered, @Param("areaType") String areaType,
            @Param("plantRoleYn") String plantRoleYn, @Param("deptRoleYn") String deptRoleYn, @Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 목표 등록/수정
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return MgtTgt 목표 상세정보
     * @throws Exception
     *             예외
     */
    public MgtTgt getMgtTarget(@Param("mgtTargetGrpNo") String mgtTargetGrpNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 목표 계획 목록
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return MgtTgtItemPlanRslt 목표 계획 목록
     * @throws Exception
     *             예외
     */
    public List<MgtTgtItemPlanRslt> getMgtTargetItems(@Param("mgtTargetGrpNo") int mgtTargetGrpNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 목표 중복 체크
     * 
     * @param year
     *            대상연도 plantCd 사업장코드 deptCd 부서코드
     * @return HashMap 중복데이터 목록
     * @throws Exception
     *             예외
     */
    public List<HashMap<String, Object>> checkMgtTarget(@Param("year") String year, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public Integer getMgtTargetGrpSeq();

    /**
     * 목표상세, 목표계획 데이터 등록/수정
     * 
     * @param MgtTgt
     *            목표데이터 모델
     * @return Integer 목표그룹번호
     * @throws Exception
     *             예외
     */
    public int createMgtTargetData(MgtTgt mgtTgt);

    /**
     * 실적/평가 데이터 등록/수정
     * 
     * @param MgtTgt
     *            목표데이터 모델
     * @return Integer 목표월번호
     * @throws Exception
     */
    public int createMgtTargetRsltData(MgtTgt mgtTgt);

    public void createMgtTgtItemTarget(MgtTgtItemRslt mgtTgtItemRslt);

    public int updateMgtTargetData(MgtTgt mgtTgt);

    public void updateMgtTgtItemTarget(MgtTgtItemRslt mgtTgtItemRslt);

    public void updateMgtTgtItemRslt(MgtTgtItemRslt mgtTgtItemRslt);

    public void updateMgtTgtItemEval(MgtTgtItemRslt mgtTgtItemRslt);

    /**
     * 목표데이터 삭제
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return Integer 0
     * @throws Exception
     *             예외
     */
    public int deleteMgtTargetData(String mgtTargetGrpNo);

    /**
     * 실적/평가 데이터 삭제
     * 
     * @param mgtTargetMonthNo
     *            목표월번호 mgtTargetType 실적/평가 구분
     * @return Integer 0
     * @throws Exception
     *             예외
     */
    public int deleteMgtTargetMonthData(String mgtTargetMonthNo, String mgtTargetType);

    /**
     * 실적/평가 상세 데이터
     * 
     * @param mgtTargetMonthNo
     *            목표월번호
     * @return MgtTgt 목표데이터 모델
     * @throws Exception
     *             예외
     */
    public MgtTgt getMgtTargetRslt(@Param("mgtTargetMonthNo") String mgtTargetMonthNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 실적/평가 목록
     * 
     * @param mgtTargetMonthNo
     *            목표월번호
     * @return MgtTgtItemEvalRslt 실적/평가 목록 모델
     * @throws Exception
     *             예외
     */
    public List<MgtTgtItemEvalRslt> getMgtTargetRslts(@Param("mgtTargetMonthNo") int mgtTargetNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int updateMgtTargetTargetAppr(@Param("mgtTargetNo") String mgtTargetNo, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    public int updateMgtTargetTargetStatus(@Param("mgtTargetNo") String mgtTargetNo, @Param("bizApprStepCd") String bizApprStepCd) throws Exception;

    public int updateMgtTargetRsltAppr(@Param("mgtTargetNo") String mgtTargetNo, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    public int updateMgtTargetRsltStatus(@Param("mgtTargetNo") String mgtTargetNo, @Param("bizApprStepCd") String bizApprStepCd) throws Exception;

    public int updateMgtTargetEvalAppr(@Param("mgtTargetNo") String mgtTargetNo, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    public int updateMgtTargetEvalStatus(@Param("mgtTargetNo") String mgtTargetNo, @Param("bizApprStepCd") String bizApprStepCd) throws Exception;

    /**
     * SHE목표달성 현황 목록
     * 
     * @param from
     *            대상연월 시작 to 대상연월 종료 plantCd 사업장코드 deptCd 부서코드 bizFieldCd 분야코드
     *            bizFieldItemNm 항목명 areaType 전사/사업장/부서 구분
     * @return MgtTgtStatus SHE목표달성 현황 목폭
     * @throws Exception
     *             예외
     */
    public List<MgtTgtStatus> getMgtTargetStatus(@Param("from") String from, @Param("to") String to, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("bizFieldCd") String bizFieldCd, @Param("bizFieldItemNm") String bizFieldItemNm, @Param("areaType") String areaType, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

}
