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
package com.she.safety.constSafe.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.Const;
import com.she.safety.model.ConstKind;
import com.she.safety.model.ConstKindSubconn;
import com.she.safety.model.ConstKindSubconnWorker;
import com.she.safety.model.LOTO;

@Mapper
@Repository("com.she.safety.constSafe.mapper.ConstMapper")
public interface ConstMapper {

    /**
     * 공사현황 조회
     * 
     * @param plantCd
     *            사업장
     * @param constStepCd
     *            공사진행단계
     * @param bizApprStepCd
     *            결재진행단계
     * @param constStartYmd
     *            작업시작일
     * @param constEndYmd
     *            작업종료일
     * @param constTitle
     *            공사명
     * @return 공사현황 목록
     * @throws Exception
     */
    public List<Const> getConsts(@Param("plantCd") String plantCd, @Param("constStepCd") String constStepCd, @Param("bizApprStepCd") String bizApprStepCd, @Param("constStartYmd") String constStartYmd, @Param("constEndYmd") String constEndYmd, @Param("constTitle") String constTitle, @Param("vendorCd") String vendorCd,
            @Param("showVendorYn") String showVendorYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공사현황 상세 조회
     * 
     * @param constNo
     *            공사번호
     * @return 공사현황
     * @throws Exception
     */
    public Const getConst(@Param("constNo") String constNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public HashMap<String, Object> getConstCheck(@Param("constNo") String constNo, @Param("constNum") String constNum) throws Exception;

    /**
     * 공사현황 작업구분 조회
     * 
     * @param constNo
     *            공사번호
     * @return 공사현황 작업구분
     * @throws Exception
     */
    public List<ConstKind> getConstKinds(@Param("constNo") String constNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공사현황 작업구분별 업체 조회
     * 
     * @param constNo
     *            공사번호
     * @param isDateCondition
     *            공사기간조건사용
     * @param vendorCd
     *            업체코드
     * @return 공사현황 작업구분별 업체
     * @throws Exception
     */
    public List<ConstKindSubconn> getConstKindSubconns(@Param("constNo") String constNo, @Param("isDateCondition") String isDateCondition, @Param("vendorCd") String vendorCd, @Param("plantCd") String plantCd, @Param("wkodKindCds") String[] wkodKindCds, @Param("constTitle") String constTitle, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 공사현황 작업구분별 업체 작업자 조회
     * 
     * @param constKindSubconnNo
     *            공사작업구분별_번호
     * @return 공사현황 작업구분별 업체 작업자
     * @throws Exception
     */
    public List<ConstKindSubconnWorker> getConstKindSubconnWorkers(@Param("constKindSubconnNo") int constKindSubconnNo) throws Exception;

    /**
     * 공사현황 작업구분별 업체번호 별 작업자들 조회
     * 
     * @param constKindSubconnNo
     *            공사작업구분별_번호
     * @return 공사현황 작업구분별 업체번호 별 작업자들
     * @throws Exception
     */
    public List<ConstKindSubconnWorker> getConstKindSubconnsWorkers(@Param("constKindSubconnNos") int[] constKindSubconnNos) throws Exception;

    /**
     * 공사현황 seq
     * 
     * @return 공사현황 seq
     * @throws Exception
     */
    public int getConstSeq() throws Exception;

    /**
     * 공사현황 신규등록
     * 
     * @param constData
     *            공사현황
     * @return 등록 행 수
     * @throws Exception
     */
    public int createConst(Const constData) throws Exception;

    /**
     * 공사현황 작업구분 신규등록
     * 
     * @param constKind
     *            공사현황 작업구분
     * @return 등록 행 수
     * @throws Exception
     */
    public int createConstKind(ConstKind constKind) throws Exception;

    /**
     * 공사현황 작업구분별 업체 신규등록
     * 
     * @param constKindSubconn
     *            공사현황 작업구분별 업체
     * @return 등록 행 수
     * @throws Exception
     */
    public int createConstKindSubconn(ConstKindSubconn constKindSubconn) throws Exception;

    /**
     * 공사현황 작업구분별 업체 작업자 신규등록
     * 
     * @param constKindSubconn
     *            공사현황 작업구분별 업체 작업자
     * @return 등록 행 수
     * @throws Exception
     */
    public int createConstKindSubconnWorker(ConstKindSubconnWorker constKindSubconnWorker) throws Exception;

    /**
     * LOTO 신규등록
     * 
     * @param loto
     *            LOTO
     * @return 등록 행 수
     * @throws Exception
     */
    public int createLoto(LOTO loto) throws Exception;

    /**
     * 공사현황 수정
     * 
     * @param constData
     *            공사현황
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateConst(Const constData) throws Exception;

    /**
     * 공사현황에 따른 작업구분별 업체 작업자들 삭제
     * 
     * @param constNo
     *            공사번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteConstKindSubconnWorker(@Param("constNo") String constNo) throws Exception;

    /**
     * 공사현황에 따른 작업구분별 업체 작업자들 삭제
     * 
     * @param constNo
     *            공사번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteConstKindSubconnWorkerKey(@Param("constKindSubconnNo") int constKindSubconnNo) throws Exception;

    /**
     * 공사현황에 따른 작업구분별 업체 삭제
     * 
     * @param constNo
     *            공사번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteConstKindSubconn(@Param("constNo") String constNo) throws Exception;

    /**
     * 공사현황에 따른 작업구분 삭제
     * 
     * @param constNo
     *            공사번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteConstKind(@Param("constNo") String constNo) throws Exception;

    /**
     * 공사현황 삭제
     * 
     * @param constNo
     *            공사번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteConst(@Param("constNo") String constNo) throws Exception;

    /**
     * LOTO 삭제
     * 
     * @param constNo
     *            공사번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteLoto(@Param("constNo") String constNo) throws Exception;

    /**
     * LOTO 조회
     * 
     * @param constNo
     *            공사번호
     * @return 공사현황 목록
     * @throws Exception
     */
    public List<LOTO> getLotos(@Param("constNo") String constNo) throws Exception;

    /**
     * 공사현황 작업구분별 업체번호 별 작업자들 조회
     * 
     * @param constKindSubconnNos
     *            공사작업구분별_번호 리스트
     * @return 공사현황 작업구분별 업체번호 별 작업자들
     * @throws Exception
     */
    public int updateConstSubconnStep(ConstKindSubconn constKindSubconn) throws Exception;

    /**
     * 공사 진행단계 변경
     *
     * @param constNo
     *            공사번호
     * @param apprRqstNo
     *            결재진행no
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeConstSafe(@Param("constNo") String constNo, @Param("apprRqstNo") int apprRqstNo, @Param("constStepCd") String constStepCd) throws Exception;

    /**
     * 공사 진행단계 완료
     *
     * @param constNo
     *            공사번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeConstSafeStep(@Param("constNo") String constNo, @Param("constStepCd") String constStepCd) throws Exception;

}
