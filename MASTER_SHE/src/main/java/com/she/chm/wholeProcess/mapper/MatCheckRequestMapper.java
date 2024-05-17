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
package com.she.chm.wholeProcess.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.Chem;
import com.she.chm.model.MatCheckRequest;
import com.she.chm.model.MatCheckRequestCompo;
import com.she.chm.model.MatCheckRequestDbVerf;
import com.she.chm.model.RegulItmMatVal;
import com.she.common.model.DefaultParam;

@Mapper
@Repository("com.she.chm.wholeProcess.mapper.MatCheckRequestMapper")
public interface MatCheckRequestMapper {

    /**
     * 물질검토 조회
     * 
     * @param search
     *            검색어 (취급물질명 및 성분명 및 CAS NO.)
     * @param vendorNm
     *            공급/제조업체명
     * @param chkRqstState
     *            진행상태
     * @param rqstType
     *            요청구분
     * @param rqstStart
     *            검토요청일 from
     * @param rqstEnd
     *            검토요청일 to
     * @return 물질검토 목록
     * @throws Exception
     */
    public List<MatCheckRequest> getMatCheckRequests(@Param("search") String search, @Param("vendorNm") String vendorNm, @Param("chkRqstState") String chkRqstState, @Param("rqstType") String rqstType, @Param("plantCd") String plantCd, @Param("rqstStart") String rqstStart, @Param("rqstEnd") String rqstEnd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 물질검토 상세 조회
     * 
     * @param matChkRqstNo
     *            물질검토번호
     * @return 물질검토
     * @throws Exception
     */
    public MatCheckRequest getMatCheckRequest(@Param("matChkRqstNo") int matChkRqstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 물질검토 중복검사 : 제조업체 + 납품업체 + SAP자재코드
     * 
     * @param makecpCd
     *            : 제조업체코드
     * @param vendorCd
     *            : 납품업체코드
     * @param matChkRqstNo
     *            : 물질검토요청번호
     * @param sapMatCd
     *            : SAP자재코드
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkMatCheckRequest(@Param("makecpCd") String makecpCd, @Param("vendorCd") String vendorCd, @Param("matChkRqstNo") int matChkRqstNo, @Param("sapMatCd") String sapMatCd) throws Exception;

    /**
     * 물질검토 신규등록
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    public int createMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception;

    /**
     * 물질검토 수정
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception;

    /**
     * 물질검토 삭제
     * 
     * @param matChkRqstNo
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteMatCheckRequest(@Param("matChkRqstNo") int matChkRqstNo) throws Exception;

    public int getChmMatChkRqstSeq() throws Exception;

    /**
     * 물질검토 구성성분 조회
     * 
     * @param matChkRqstNo
     *            물질검토 번호
     * @return 물질검토 구성성분 목록
     * @throws Exception
     */
    public List<MatCheckRequestCompo> getMatCheckRequestCompos(@Param("matChkRqstNo") int matChkRqstNo) throws Exception;

    /**
     * 물질검토 규제항목 조회
     * 
     * @param matChkRqstNo
     *            물질검토 번호
     * @return 물질검토 규제항목 목록
     * @throws Exception
     */
    public List<RegulItmMatVal> getMatCheckRequestCompoRegul(@Param("matChkRqstNo") int matChkRqstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 물질검토 규제 신규등록
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    public int createMatCheckRequestCompoRegul(RegulItmMatVal regulItmMatVal) throws Exception;

    /**
     * 물질검토 규제 삭제
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteMatCheckRequestCompoRegul(@Param("matChkRqstNo") int matChkRqstNo) throws Exception;

    /**
     * 물질검토 구성성분 신규등록
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    public int createMatCheckRequestCompo(MatCheckRequestCompo matCheckRequestCompo) throws Exception;

    /**
     * 물질검토 구성성분 삭제
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteMatCheckRequestCompo(@Param("matChkRqstNo") int matChkRqstNo) throws Exception;

    /**
     * sap 요청자재 임시로 생성
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    public int saveSapMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception;

    /**
     * 규제DB검증
     * 
     * @param chemNos
     *            : 화학물질번호 배열
     * @param regulItmNos
     *            : 규제항목번호 배열
     * @return
     * @throws Exception
     */
    public List<MatCheckRequestDbVerf> getRegulDbVertification(@Param("chemNos") String chemNos, @Param("limitRepvals") String limitRepvals, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전/보건 검토완료처리
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    public int confirmMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception;

    /**
     * 환경 검토완료처리
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    public int confirmEnvMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception;

    /**
     * 검토반려
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    public int rejectMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception;

    public Chem searchChemNo(String chemNmkr) throws Exception;
}
