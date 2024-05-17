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
package com.she.safety.change.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.Change;
import com.she.safety.model.ChangeCheckItemResult;
import com.she.safety.model.ChangeCommi;
import com.she.safety.model.ChangeCommiPsn;
import com.she.safety.model.ChangeDashboardBase;
import com.she.safety.model.ChangeEffect;
import com.she.safety.model.ChangeElectLaw;
import com.she.safety.model.ChangeElectResult;
import com.she.safety.model.ChangeOperation;
import com.she.safety.model.ChangeRefWork;
import com.she.safety.model.ChangeRefWorkView;
import com.she.safety.model.ChangeType;

@Mapper
@Repository("com.she.safety.change.mapper.ChangeMapper")
public interface ChangeMapper {

    /**
     * 변경관리 조회
     *
     * @return 변경관리 목록
     * @throws Exception
     */
    public List<Change> getChanges(@Param("plantCd") String plantCd, @Param("rqstStartDt") String rqstStartDt, @Param("rqstEndDt") String rqstEndDt, @Param("rqstDeptCd") String rqstDeptCd, @Param("rqstDeptSubYn") String rqstDeptSubYn, @Param("chngStepCd") String chngStepCd, @Param("chngAttCd") String chngAttCd, @Param("bizNm") String bizNm,
            @Param("chngRefWorkCd") String chngRefWorkCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 변경관리 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리
     * @throws Exception
     */
    public Change getChange(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 변경관리 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리
     * @throws Exception
     */
    public List<ChangeType> getChangeTypes(@Param("safChngNo") int safChngNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 변경관리 정비운전 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리 정비운전
     * @throws Exception
     */
    public List<ChangeOperation> getChangeOperations(@Param("safChngNo") int safChngNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 변경관리 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리
     * @throws Exception
     */
    public List<ChangeEffect> getChangeEffects(@Param("safChngNo") int safChngNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 변경관리 신규등록
     *
     * @param change
     *            변경관리
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChange(Change change) throws Exception;

    /**
     * 변경관리 수정
     *
     * @param change
     *            변경관리
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChange(Change change) throws Exception;

    /**
     * 변경관리 삭제
     *
     * @param regulItmNo
     *            변경관리번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChange(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 변경관리구분내역 신규등록
     *
     * @param changeType
     *            변경관리구분내역
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChangeType(ChangeType changeType) throws Exception;

    /**
     * 변경관리구분내역 삭제
     *
     * @param safChngNo
     *            변경관리번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChangeType(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 변경관리 정비운전 신규등록
     *
     * @param changeOperation
     *            변경관리 정비운전
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChangeOperation(ChangeOperation changeOperation) throws Exception;

    /**
     * 변경관리 정비운전 삭제
     *
     * @param safChngNo
     *            변경관리번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChangeOperation(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 변경 후 기대효과 신규등록
     *
     * @param changeType
     *            변경 후 기대효과
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChangeEffect(ChangeEffect changeEffect) throws Exception;

    /**
     * 변경 후 기대효과 삭제
     *
     * @param safChngNo
     *            변경관리번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChangeEffect(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 변경관리위원회 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리위원회
     * @throws Exception
     */
    public ChangeCommi getChangeCommi(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 변경관리위원회 신규등록
     *
     * @param changeCommi
     *            변경관리위원회
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChangeCommi(ChangeCommi changeCommi) throws Exception;

    /**
     * 변경관리위원회 수정
     *
     * @param changeCommi
     *            변경관리위원회
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChangeCommi(ChangeCommi changeCommi) throws Exception;

    /**
     * 변경관리위원회 참석자 조회
     *
     * @param safChngCommiNo
     *            변경관리위원회번호
     * @return 변경관리위원회 참석자 목록
     * @throws Exception
     */
    public List<ChangeCommiPsn> getChangeCommiPsn(@Param("safChngCommiNo") int safChngCommiNo) throws Exception;

    /**
     * 변경관리위원회 참석자 신규등록
     *
     * @param changeCommiPsn
     *            변경관리위원회 참석자
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChangeCommiPsn(ChangeCommiPsn changeCommiPsn) throws Exception;

    /**
     * 변경관리위원회 참석자 삭제
     *
     * @param safChngCommiNo
     *            변경관리위원회번호
     * @param userId
     *            참석자ID
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChangeCommiPsn(@Param("safChngCommiNo") int safChngCommiNo, @Param("userId") String userId) throws Exception;

    /**
     * 검토항목 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 검토항목
     * @throws Exception
     */
    public List<ChangeCheckItemResult> getChangeCheckItemResult(@Param("safChngNo") int safChngNo, @Param("order") String order, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 검토항목 신규등록
     *
     * @param changeCheckItemResult
     *            검토항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChangeCheckItemResult(ChangeCheckItemResult changeCheckItemResult) throws Exception;

    /**
     * 검토항목 수정
     *
     * @param changeCheckItemResult
     *            검토항목
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChangeCheckItemResult(ChangeCheckItemResult changeCheckItemResult) throws Exception;

    /**
     * 검토항목 삭제
     *
     * @param safChngNo
     *            변경관리번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChangeCheckItemResult(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 진행사항 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 진행사항
     * @throws Exception
     */
    public List<ChangeRefWork> getChangeRefWork(@Param("safChngNo") int safChngNo, @Param("chngRefWorkCd") String chngRefWorkCd, @Param("refTableId") String refTableId, @Param("refTableNm") String refTableNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 진행관리 삭제
     *
     * @param safChngNo
     *            변경관리번호
     * @return 등록 행 수
     * @throws Exception
     */
    public int deleteChangeRefWork(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 진행관리 신규등록
     *
     * @param changeRefWork
     *            진행관리
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChangeRefWork(ChangeRefWork changeRefWork) throws Exception;

    /**
     * 진행관리 수정
     *
     * @param changeRefWork
     *            진행관리
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChangeRefWork(ChangeRefWork changeRefWork) throws Exception;

    /**
     * 진행관리 수정
     *
     * @param changeRefWork
     *            진행관리
     * @return 수정 행 수
     * @throws Exception
     */
    public ChangeRefWorkView getRefWorkDetailInfo(@Param("refTableId") String refTableId, @Param("refTableNm") String refTableNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 변경관리 진행단계 변경
     *
     * @param safChngNo
     *            사업장
     * @param apprRqstNo
     *            구분
     * @param chngStepCd
     *            구분
     * @return 변경관리 진행단계 변경 행 수
     * @throws Exception
     */
    public int apprProcessChange(@Param("safChngNo") int safChngNo, @Param("apprRqstNo") int apprRqstNo, @Param("chngStepCd") String chngStepCd, @Param("confirmDt") String confirmDt) throws Exception;

    /**
     * 변경관리 완료
     *
     * @param change
     *            변경관리
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeChange(Change change) throws Exception;

    /**
     * 변경관리 조회
     *
     * @return 변경관리 목록
     * @throws Exception
     */
    public List<ChangeDashboardBase> getChangeDashboadBase(@Param("plantCd") String plantCd, @Param("rqstStartDt") String rqstStartDt, @Param("rqstEndDt") String rqstEndDt, @Param("rqstDeptCd") String rqstDeptCd, @Param("chngStepCd") String chngStepCd, @Param("lvlCd") String lvlCd, @Param("bizNm") String bizNm,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 인허가검토 관련 법규 조회
     *
     * @return 인허가검토 관련 법규 목록
     * @throws Exception
     */
    public List<ChangeElectLaw> getChangeElectLaws(@Param("safChngNo") int safChngNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 인허가검토 조회
     *
     * @return 인허가검토 목록
     * @throws Exception
     */
    public List<ChangeElectResult> getChangeElectResults(@Param("safChngElectLawResultNo") int safChngElectLawResultNo, @Param("refLawCd") String refLawCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 인허가검토 신규등록
     *
     * @return 변경 행 수
     * @throws Exception
     */
    public int createChangeElectLaw(ChangeElectLaw changeElectLaw) throws Exception;

    /**
     * 인허가검토 수정
     *
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateChangeElectLaw(ChangeElectLaw changeElectLaw) throws Exception;

    /**
     * 인허가검토항목 삭제
     *
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteChangeElectLaw(@Param("safChngNo") int safChngNo) throws Exception;

    /**
     * 인허가검토항목 신규등록
     *
     * @return 변경 행 수
     * @throws Exception
     */
    public int createChangeElectResult(ChangeElectResult changeElectResult) throws Exception;

    /**
     * 인허가검토항목 삭제
     *
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteChangeElectResult(@Param("safChngElectLawResultNo") int safChngElectLawResultNo) throws Exception;

    /**
     * 인허가검토항목 삭제
     *
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAllChangeElectResult(@Param("safChngNo") int safChngNo) throws Exception;

}
