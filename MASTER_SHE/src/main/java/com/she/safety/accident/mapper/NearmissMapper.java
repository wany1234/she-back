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
package com.she.safety.accident.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.Nearmiss;
import com.she.safety.model.NearmissOcctype;
import com.she.safety.model.NearmissVictim;

@Mapper
@Repository("com.she.safety.accident.mapper.NearmissMapper")
public interface NearmissMapper {

    /**
     * 아차사고 목록 조회
     * 
     * @param startDate
     *            시작날짜
     * @param endDate
     *            끝날짜
     * @param deptCd
     *            부서코드
     * @param area
     *            장소명
     * @param nearmissTitle
     *            아차사고명
     * @param processStepCd
     *            진행단계코드
     * @param occTypeCd
     *            사고유형코드
     * @return 아차사고 목록
     * @throws Exception
     */
    public List<Nearmiss> getNearmissList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("area") String area, @Param("nearmissTitle") String nearmissTitle, @Param("processStepCd") String processStepCd, @Param("occTypeCd") String occTypeCd,
            @Param("plantCd") String plantCd, @Param("accidentType") String accidentType, @Param("occKindCd") String occKindCd, @Param("occAttCd") String occAttCd, @Param("immCauseCd") String immCauseCd, @Param("indCauseCd") String indCauseCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 아차사고 상세 조회
     * 
     * @param safNearmissNo
     *            사내사고번호
     * @return 아차사고
     * @throws Exception
     */
    public Nearmiss getNearmiss(@Param("safNearmissNo") int safNearmissNo) throws Exception;

    /**
     * 아차사고 사고자 목록 조회
     * 
     * @param safNearmissNo
     * @return
     * @throws Exception
     */
    public List<NearmissVictim> getNearmissVictim(@Param("safNearmissNo") int safNearmissNo) throws Exception;

    /**
     * 아차사고 발생유형 목록 조회
     * 
     * @param safNearmissNo
     * @return
     * @throws Exception
     */
    public List<NearmissOcctype> getNearmissOcctype(@Param("safNearmissNo") int safNearmissNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 신규 발행번호 생성
     * 
     * @param 발생일
     * @return 신규발행번호
     * @throws Exception
     */
    public Nearmiss createNearmissNum(@Param("nearmissYmd") String nearmissYmd) throws Exception;

    /**
     * 아차사고 신규
     * 
     * @param 아차사고
     * @return 아차사고번호
     * @throws Exception
     */
    public int createNearmiss(Nearmiss nearmiss) throws Exception;

    /**
     * 아차사고 사고자정보 등록
     * 
     * @param nearmissVictim
     * @return
     * @throws Exception
     */
    public int createNearmissVictim(NearmissVictim nearmissVictim) throws Exception;

    /**
     * 아차사고 사고자정보 삭제
     * 
     * @param safNearmissNo
     * @return
     * @throws Exception
     */
    public int deleteNearmissVictim(@Param("safNearmissNo") int safNearmissNo) throws Exception;

    /**
     * 아차사고 발생유형 등록
     * 
     * @param nearmissOcctype
     * @return
     * @throws Exception
     */
    public int createNearmissOcctype(NearmissOcctype nearmissOcctype) throws Exception;

    /**
     * 아차사고 발생유형 삭제
     * 
     * @param safNearmissNo
     * @return
     * @throws Exception
     */
    public int deleteNearmissOcctype(@Param("safNearmissNo") int safNearmissNo) throws Exception;

    /**
     * 아차사고 수정
     * 
     * @param 아차사고
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateNearmiss(Nearmiss nearmiss) throws Exception;

    /**
     * 아차사고 완료
     * 
     * @param 아차사고
     * @return 변경 행 수
     * @throws Exception
     */
    public int compleNearmiss(Nearmiss nearmiss) throws Exception;

    /**
     * 아차 사고 삭제
     * 
     * @param safNearmissNo
     *            아차사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteNearmiss(int safNearmissNo) throws Exception;

    /**
     * 개선조치 삭제
     * 
     * @param safNearmissNo
     *            아차사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteImprAct(int safNearmissNo) throws Exception;

    /**
     * 아차사고 진행상태 수정
     * 
     * @param checkStepCd
     *            설비점검진행상태
     * @param updateUserId
     *            수정자ID
     * @param safNearmissNo
     *            아차사고 번호
     * @return 아차사고 키값
     * @throws Exception
     */
    public int updateProcessStepCdStepCd(@Param("processStepCd") String processStepCd, @Param("updateUserId") String updateUserId, @Param("safNearmissNo") int safNearmissNo) throws Exception;

    /**
     * 아차사고 결재
     * 
     * @param nearmiss
     * @return
     * @throws Exception
     */
    public int apprNearmiss(Nearmiss nearmiss) throws Exception;

}
