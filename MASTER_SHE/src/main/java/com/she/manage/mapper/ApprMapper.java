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
package com.she.manage.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.manage.model.Appr;
import com.she.manage.model.ApprBiz;
import com.she.manage.model.ApprBizLine;
import com.she.manage.model.ApprBizLineDtl;
import com.she.manage.model.ApprDelegate;
import com.she.manage.model.ApprGroupWare;
import com.she.manage.model.ApprRqst;
import com.she.manage.model.ApprRqstGroupWare;
import com.she.manage.model.ApprRqstLine;
import com.she.manage.model.User;

@Mapper
@Repository("com.she.manage.mapper.ApprMapper")
public interface ApprMapper {

    /**
     * 결재문서마스터 목록 조회
     * 
     * @param apprBizCd
     * @param bizNm
     * @param apprBizTypeCd
     * @return
     * @throws Exception
     */
    public List<ApprBiz> getApprBizList(@Param("apprBizCd") String apprBizCd, @Param("bizNm") String bizNm, @Param("apprBizTypeCd") String apprBizTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결재문서마스터 등록
     * 
     * @param apprBiz
     * @return
     * @throws Exception
     */
    public int createApprBiz(ApprBiz apprBiz) throws Exception;

    /**
     * 결재문서결재선 등록
     * 
     * @param apprBizLine
     * @return
     * @throws Exception
     */
    public int createApprBizLine(ApprBizLine apprBizLine) throws Exception;

    /**
     * 결재문서결재선 세부정보 등록
     * 
     * @param apprBizLineDtl
     * @return
     * @throws Exception
     */
    public int createApprBizLineDtl(ApprBizLineDtl apprBizLineDtl) throws Exception;

    /**
     * 결재문서 마스터 상세 조회
     * 
     * @param apprBizNo
     * @param apprBizCd
     * @return
     * @throws Exception
     */
    public ApprBiz getApprBizDetail(@Param("apprBizNo") int apprBizNo, @Param("apprBizCd") String apprBizCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결재문서결재선 목록 조회
     * 
     * @param apprBizNo
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<ApprBizLine> getApprBizLineList(@Param("apprBizNo") int apprBizNo, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결재문서결재선 세부정보 목록 조회
     * 
     * @param apprBizNo
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<ApprBizLineDtl> getApprBizLineDtlList(@Param("apprBizNo") int apprBizNo, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결재문서 유형코드 조회
     * 
     * @param apprBizCd
     * @return
     * @throws Exception
     */
    public int getApprBizCodeCount(@Param("apprBizCd") String apprBizCd) throws Exception;

    /**
     * 결재문서마스터 수정
     * 
     * @param apprBiz
     * @return
     * @throws Exception
     */
    public int updateApprBiz(ApprBiz apprBiz) throws Exception;

    /**
     * 결재문서 결재선 삭제
     * 
     * @param apprBizNo
     * @return
     * @throws Exception
     */
    public int deleteApprBizLine(@Param("apprBizNo") int apprBizNo) throws Exception;

    /**
     * 결재문서 결재선 세부정보 삭제
     * 
     * @param apprBizNo
     * @return
     * @throws Exception
     */
    public int deleteApprBizLineDtl(@Param("apprBizNo") int apprBizNo) throws Exception;

    /**
     * 결재요청 등록
     * 
     * @param apprRqst
     * @return
     * @throws Exception
     */
    public int createApprRqst(ApprRqst apprRqst) throws Exception;

    /**
     * 결재요청 결재선 등록
     * 
     * @param apprRqstLine
     * @return
     * @throws Exception
     */
    public int createApprRqstLine(ApprRqstLine apprRqstLine) throws Exception;

    /**
     * 결재 할 문서 조회
     * 
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param apprYn
     *            처리여부
     * @param apprUserId
     *            사용자ID
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<Appr> getWhichApprs(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("apprYn") String apprYn, @Param("returnYn") String returnYn, @Param("apprUserId") String apprUserId, @Param("apprRqstNm") String apprRqstNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결재 할 문서 결재선 목록 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public List<ApprRqstLine> getWhichApprLine(@Param("apprRqstNo") int apprRqstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결재 처리 상태 수정
     * 
     * @param apprRqstLine
     * @return
     * @throws Exception
     */
    public int updateApprStep(ApprRqstLine apprRqstLine) throws Exception;

    /**
     * 결재 요청 결재선 개수 조회
     * 
     * @param apprRqstLine
     * @return
     * @throws Exception
     */
    public int getCountApprRqstLine(ApprRqstLine apprRqstLine) throws Exception;

    /**
     * 결재요청 결재진행단계 수정
     * 
     * @param apprRqst
     * @return
     * @throws Exception
     */
    public int updateBizApprStep(ApprRqst apprRqst) throws Exception;

    /**
     * 결재요청 상세 정보 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public Appr getApprRqstDetail(int apprRqstNo) throws Exception;

    /**
     * 결재선 결재자 조회
     * 
     * @param userId
     * @param dutyCd
     * @return
     * @throws Exception
     */
    public User getApprUserInfo(@Param("userId") String userId, @Param("dutyCd") String dutyCd) throws Exception;

    /**
     * 결재선 부서별 팀장 조회
     * 
     * @param deptCd
     * @param dutyCd
     * @return
     * @throws Exception
     */
    public User getApprUserInfoByDept(@Param("deptCd") String deptCd, @Param("dutyCd") String dutyCd) throws Exception;

    /**
     * 결재 요청 문서 조회
     * 
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param bizApprStepCd
     *            결재진행단계코드
     * @param apprUserId
     *            사용자ID
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<Appr> getApprRequestList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("bizApprStepCd") String bizApprStepCd, @Param("apprUserId") String apprUserId, @Param("apprRqstNm") String apprRqstNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 반려 결재선 목록 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public List<ApprRqstLine> getRejectApprLine(@Param("apprRqstNo") int apprRqstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 반려 결재 이력 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public List<ApprRqstLine> getApprLineHistory(@Param("apprRqstNo") int apprRqstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 그룹웨어 결재 할 문서 조회
     * 
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param apprYn
     *            처리여부
     * @param userId
     *            사용자ID
     * @param apprStepCd
     *            결재자처리상태코드
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<ApprGroupWare> getWhichApprsForGroupWare(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("apprYn") String apprYn, @Param("userId") String userId, @Param("apprStepCd") String apprStepCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 그룹웨어 결재 요청 문서 조회
     * 
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param bizApprStepCd
     *            결재진행단계코드
     * @param userId
     *            사용자ID
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<ApprRqstGroupWare> getApprRequestListForGroupWare(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("bizApprStepCd") String bizApprStepCd, @Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 상위결재자 삭제
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int deleteApprRqstLine(@Param("apprRqstNo") int apprRqstNo);

    /**
     * 결재 회수 가능한지 여부 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public List<ApprRqstLine> getCollectCheck(@Param("apprRqstNo") int apprRqstNo);

    /**
     * 결재 회수
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int deleteAppr(@Param("apprRqstNo") int apprRqstNo);

    /**
     * 결재 파라미터 수정
     * 
     * @param apprRqstNo
     * @param apprParam
     * @return
     * @throws Exception
     */
    public int updateApprParam(@Param("apprRqstNo") int apprRqstNo, @Param("apprParam") String apprParam);

    /**
     * 대리 결재자 등록
     * 
     * @param apprDelegate
     * @return
     * @throws Exception
     */
    public int createApprDelegate(ApprDelegate apprDelegate);

    /**
     * 대리 결재자 삭제
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    public int deleteApprDelegates(@Param("userId") String userId, @Param("delegatorId") String delegatorId, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd);

    /**
     * 대리 결재자가 이미 있는지 체크 A -> B, C-> A = false
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> checkApprDelegate(@Param("delegatorId") String delegatorId, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd);

    /**
     * 대리 결재자 불러오기
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> getApprDelegator(@Param("userId") String userId);

    /**
     * 결재 라인 번호에 해당되는거 들고오기
     * 
     * @param apprRqstNo
     * @param lineSeqNo
     * @return
     * @throws Exception
     */
    public ApprRqstLine getApprLine(@Param("apprRqstNo") int apprRqstNo, @Param("lineSeqNo") int lineSeqNo);

    /**
     * 결재 완료일 조회
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public String getApprEndDt(@Param("apprRqstNo") int apprRqstNo);

    /**
     * 결재진행상태 조회
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getApprProgress(@Param("apprRqstNo") int apprRqstNo);
}