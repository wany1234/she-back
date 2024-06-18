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
package com.she.mgt.elect.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.ElectHis;
import com.she.mgt.model.ElectTitlLcn;

@Mapper
@Repository("com.she.mgt.elect.mapper.ElectHisMapper")
public interface ElectHisMapper {

    /**
     * 선해임 조회
     * 
     * @param lcnGetStartDt
     *            선해임일자시작일
     * @param lcnGetEndDt
     *            선해임일자종료일
     * @param electTypeCd
     *            구분
     * @param userNm
     *            선임자
     * @param safElectTitlNo
     *            선해임명번호
     * @return 선해임 목록
     * @throws Exception
     */
    public List<ElectHis> getElectHises(@Param("lcnGetStartDt") String lcnGetStartDt, @Param("lcnGetEndDt") String lcnGetEndDt, @Param("plantCd") String plantCd, @Param("userNm") String userNm, @Param("safElectTitlNo") int safElectTitlNo, @Param("testEndDt") String testEndDt, @Param("employmentYn") String employmentYn,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 법정선임자 현황 통계 조회
     * 
     * @param plantCd
     * @param safElectHisNo
     * @return 선해임 목록
     * @throws Exception
     */

    public List<HashMap<String, Object>> getElectStatus(@Param("plantCd") String plantCd, @Param("safElectTitlNo") int safElectTitlNo) throws Exception;

    // getHrElectHises

    /**
     * 선해임 상세 조회
     * 
     * @param safElectHisNo
     *            선해임번호
     * @return 선해임
     * @throws Exception
     */
    public ElectHis getElectHis(@Param("safElectHisNo") int safElectHisNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선해임 이력 조회
     * 
     * @param safElectHisNo
     *            선해임번호
     * @return 선해임
     * @throws Exception
     */
    public List<ElectHis> getElectHisesRevs(@Param("safElectHisNo") int safElectHisNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선해임 신규등록
     * 
     * @param electHis
     *            선해임
     * @return 등록 행 수
     * @throws Exception
     */
    public int createElectHis(ElectHis electHis) throws Exception;

    /**
     * 이전 선해임 사용안함 처리
     * 
     * @param safElectHisGrpNo
     * @return
     * @throws Exception
     */
    public int updateOldElectHisUseYn(@Param("safElectHisGrpNo") int safElectHisGrpNo) throws Exception;

    /**
     * 선해임 수정
     * 
     * @param electHis
     *            선해임
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateElectHis(ElectHis electHis) throws Exception;

    /**
     * 선해임 삭제
     * 
     * @param safElectHisNo
     *            선해임번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteElectHis(@Param("safElectHisNo") int safElectHisNo) throws Exception;

    /**
     * 선해임 개정이력 삭제
     * 
     * @param ElectHis
     * @return 생성 행 수
     * @throws Exception
     */
    public int revDeleteElectHis(ElectHis electHis) throws Exception;

    /**
     * 선해임 체크
     * 
     * @param userId
     *            선해임자 ID
     * @param safElectTitlNo
     *            선해임명번호
     * @param safElectLcnNo
     *            자격증번호
     * @param safElectHisNo
     *            선해임번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckElectHis(@Param("userId") String userId, @Param("safElectTitlNo") int safElectTitlNo, @Param("safElectLcnNo") int safElectLcnNo, @Param("safElectHisNo") int safElectHisNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선해임 체크(중복)
     * 
     * @param userId
     *            선해임자 ID
     * @param plantCd
     *            사업장코드
     * @param safElectTitlNo
     *            선해임명번호
     * 
     * @return 체크 값
     * @throws Exception
     */
    public int countElectHisNm(@Param("plantCd") String plantCd, @Param("userId") String userId, @Param("safElectTitlNo") String safElectTitlNo) throws Exception;

    /**
     * HR 자격 조회
     * 
     * @param userId
     * @param userNm
     * @param licenseNm
     * @param plantCd
     * @param pageNumber
     * @param pageSize
     * @param orderByExpression
     * @return
     * @throws Exception
     */
    public List<ElectTitlLcn> getHrElectTitlLcns(@Param("userId") String userId, @Param("userNm") String userNm, @Param("licenseNm") String licenseNm, @Param("plantCd") String plantCd, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("orderByExpression") String orderByExpression,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int getHrElectTitlLcnsTotSize(@Param("userId") String userId, @Param("userNm") String userNm, @Param("licenseNm") String licenseNm, @Param("plantCd") String plantCd) throws Exception;

}
