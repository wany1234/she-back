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
package com.she.safety.cto.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.Cto;
import com.she.safety.model.CtoAct;
import com.she.safety.model.CtoActCause;
import com.she.safety.model.CtoCheckResult;

@Mapper
@Repository("com.she.safety.cto.mapper.CtoMapper")
public interface CtoMapper {

    /**
     * cto 조회
     *
     * @param plantCd
     *            사업장
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            관찰부서
     * @param processCd
     *            관찰공정
     * @param jobNm
     *            작업명
     * @return cto 목록
     * @throws Exception
     */
    public List<Cto> getCtos(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("jobNm") String jobNm, @Param("stepCd") String stepCd, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * cto 상세조회
     *
     * @param safCtoNo
     *            cto번호
     * @return cto
     * @throws Exception
     */
    public Cto getCto(@Param("safCtoNo") int safCtoNo) throws Exception;

    /**
     * cto 신규등록
     *
     * @param cto
     *            cto
     * @return 등록 행 수
     * @throws Exception
     */
    public int createCto(Cto cto) throws Exception;

    /**
     * cto 수정
     *
     * @param cto
     *            cto
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateCto(Cto cto) throws Exception;

    /**
     * cto 삭제
     *
     * @param safCtoNo
     *            cto번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteCto(@Param("safCtoNo") int safCtoNo) throws Exception;

    /**
     * 핵심행동 조회
     *
     * @param safCtoNo
     *            cto번호
     * @return 핵심행동 목록
     * @throws Exception
     */
    public List<CtoAct> getCtoActs(@Param("safCtoNo") int safCtoNo) throws Exception;

    /**
     * 핵심행동 신규등록
     *
     * @param ctoAct
     *            핵심행동
     * @return 신규등록 행 수
     * @throws Exception
     */
    public int createCtoAct(CtoAct ctoAct) throws Exception;

    /**
     * 핵심행동 삭제
     *
     * @param safCtoNo
     *            cto번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteCtoAct(@Param("safCtoNo") int safCtoNo) throws Exception;

    /**
     * CTO체크리스트 조회
     *
     * @param safCtoNo
     *            cto번호
     * @return CTO체크리스트 목록
     * @throws Exception
     */
    public List<CtoCheckResult> getCtoCheckResults(@Param("safCtoNo") int safCtoNo) throws Exception;

    /**
     * 체크리스트 신규등록
     *
     * @param ctoCheckResult
     *            체크리스트
     * @return 신규등록 행 수
     * @throws Exception
     */
    public int createCtoCheckResult(CtoCheckResult ctoCheckResult) throws Exception;

    /**
     * 체크리스트 삭제
     *
     * @param safCtoNo
     *            cto번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteCtoCheckResult(@Param("safCtoNo") int safCtoNo) throws Exception;

    /**
     * 근본원인 조회
     *
     * @param safCtoNo
     *            cto번호
     * @return 근본원인 목록
     * @throws Exception
     */
    public List<CtoActCause> getCtoActCauses(@Param("safCtoNo") int safCtoNo) throws Exception;

    /**
     * 근본원인 신규등록
     *
     * @param ctoAct
     *            근본원인
     * @return 신규등록 행 수
     * @throws Exception
     */
    public int createCtoActCause(CtoActCause ctoActCause) throws Exception;

    /**
     * 근본원인 삭제
     *
     * @param safCtoNo
     *            cto번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteCtoActCause(@Param("safCtoNo") int safCtoNo) throws Exception;

    List<HashMap<String, Object>> checkCheckListItem(String codeGroupCd, String code, String codeNm);
}
