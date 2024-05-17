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
package com.she.chm.wholeProcess.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.chem.mapper.ChemprodMapper;
import com.she.chm.model.MatCheckRequest;
import com.she.chm.model.MatCheckRequestCompo;
import com.she.chm.model.MatCheckRequestCompoRegul;
import com.she.chm.model.MatCheckRequestDbVerf;
import com.she.chm.model.RegulItmMatVal;
import com.she.chm.wholeProcess.mapper.MatCheckRequestMapper;
import com.she.common.model.DefaultParam;
import com.she.security.auth.IAuthenticationFacade;
import com.she.utils.JcoUtil;

@Service
public class MatCheckRequestService {
    @Autowired
    private MatCheckRequestMapper matCheckRequestMapper;

    @Autowired
    private ChemprodMapper chemprodMapper;

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

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
    public List<MatCheckRequest> getMatCheckRequests(String search, String vendorNm, String chkRqstState, String rqstType, String plantCd, String rqstStart, String rqstEnd, DefaultParam defaultParam) throws Exception {
        return matCheckRequestMapper.getMatCheckRequests(search, vendorNm, chkRqstState, rqstType, plantCd, rqstStart, rqstEnd, defaultParam);
    }

    /**
     * 물질검토 상세 조회
     * 
     * @param matChkRqstNo
     *            물질검토번호
     * @return 물질검토
     * @throws Exception
     */
    public MatCheckRequest getMatCheckRequest(int matChkRqstNo, DefaultParam defaultParam) throws Exception {
        return matCheckRequestMapper.getMatCheckRequest(matChkRqstNo, defaultParam);
    }

    /**
     * 물질검토 하위정보 포함 모든 정보로 조회
     * 
     * @param matChkRqstNo
     * @return
     * @throws Exception
     */
    public MatCheckRequest getMatCheckRequestChilds(int matChkRqstNo, DefaultParam defaultParam) throws Exception {

        MatCheckRequestCompoRegul matCheckRequestCompoRegul = new MatCheckRequestCompoRegul();
        matCheckRequestCompoRegul.setMatCheckRequestCompos(matCheckRequestMapper.getMatCheckRequestCompos(matChkRqstNo));

        // 규제정보
        List<Integer> regulItmMatVals = new ArrayList<Integer>();
        List<RegulItmMatVal> lists = matCheckRequestMapper.getMatCheckRequestCompoRegul(matChkRqstNo, defaultParam);
        matCheckRequestCompoRegul.setMatCheckRequestRegulItms(lists);
        for (RegulItmMatVal obj : lists) {
            regulItmMatVals.add(obj.getRegulItmNo());
        }
        matCheckRequestCompoRegul.setRegulItmMatVals(regulItmMatVals);

        MatCheckRequest matCheckRequest = matCheckRequestMapper.getMatCheckRequest(matChkRqstNo, defaultParam);
        matCheckRequest.setMatCheckRequestCompoRegul(matCheckRequestCompoRegul);

        // 이미 등록되어 있는 정보가 있는지 여부도 반환한다.
        Integer existChemProdNo = chemprodMapper.getExistsChemprod(matCheckRequest.getSapMatCd(), matCheckRequest.getVendorCd(), matCheckRequest.getMakecpCd());

        if (existChemProdNo != null && existChemProdNo > 0) {
            matCheckRequest.setExistChemProdNo(existChemProdNo);
        }

        return matCheckRequest;
    }

    /**
     * 물질검토 중복검사
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
    public List<HashMap<String, Object>> checkMatCheckRequest(String makecpCd, String vendorCd, int matChkRqstNo, String sapMatCd) throws Exception {
        return this.matCheckRequestMapper.checkMatCheckRequest(makecpCd, vendorCd, matChkRqstNo, sapMatCd);
    }

    /**
     * 물질검토 신규등록
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public MatCheckRequest createMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception {
        matCheckRequestMapper.createMatCheckRequest(matCheckRequest);
        if (matCheckRequest.getMatCheckRequestCompoRegul() != null) {
            createMatCheckRequestCompo(matCheckRequest.getMatCheckRequestCompoRegul(), matCheckRequest.getMatChkRqstNo());
        }
        return this.getMatCheckRequest(matCheckRequest.getMatChkRqstNo(), new DefaultParam("kr"));
    }

    /**
     * 물질검토 수정
     * 
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public MatCheckRequest updateMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception {
        matCheckRequestMapper.updateMatCheckRequest(matCheckRequest);
        if (matCheckRequest.getMatCheckRequestCompoRegul() != null) {
            createMatCheckRequestCompo(matCheckRequest.getMatCheckRequestCompoRegul(), matCheckRequest.getMatChkRqstNo());
        }
        // 2021-12-06 kdh 솔루션에서는 검토결과를 전송하는 로직을 수행하지 않게 처리
        // // 검토완료로 들어올 시에 검토 결과를 요청한다.
        // if ("MCRS3".equals(matCheckRequest.getChkRqstState())) {
        // Map<String, Object> params = new HashMap<>();
        // params.put("I_BUKRS", matCheckRequest.getBranchCd()); // 회사 코드
        // params.put("I_RQNUM", matCheckRequest.getRqstNo()); // 요청번호
        // params.put("I_RQITM", matCheckRequest.getRqstItmNo()); // 요청품목번호
        //
        // Map<String, Object> datas =
        // JcoUtil.setSapTableData("ZSHE_INTF_MSDS2", params);
        // }
        return this.getMatCheckRequest(matCheckRequest.getMatChkRqstNo(), new DefaultParam("kr"));
    }

    /**
     * 물질검토 삭제
     * 
     * @param matChkRqstNo
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteMatCheckRequest(int matChkRqstNo) throws Exception {
        int resultNo = 0;
        resultNo += this.matCheckRequestMapper.deleteMatCheckRequestCompo(matChkRqstNo);
        resultNo += this.matCheckRequestMapper.deleteMatCheckRequestCompoRegul(matChkRqstNo);
        resultNo += matCheckRequestMapper.deleteMatCheckRequest(matChkRqstNo);
        return resultNo;
    }

    /**
     * 물질검토 구성성분 조회
     * 
     * @param matChkRqstNo
     *            물질검토 번호
     * @return 물질검토 구성성분 목록
     * @throws Exception
     */
    public List<MatCheckRequestCompo> getMatCheckRequestCompos(int matChkRqstNo) throws Exception {
        return matCheckRequestMapper.getMatCheckRequestCompos(matChkRqstNo);
    }

    /**
     * 물질검토 규제항목 조회
     * 
     * @param matChkRqstNo
     *            물질검토 번호
     * @return 물질검토 규제항목 목록
     * @throws Exception
     */
    public List<RegulItmMatVal> getMatCheckRequestCompoRegul(int matChkRqstNo, DefaultParam defaultParam) throws Exception {
        return matCheckRequestMapper.getMatCheckRequestCompoRegul(matChkRqstNo, defaultParam);
    }

    /**
     * 물질검토 구성성분 & 규제항목 신규등록
     * 
     * @param chemprod
     *            취급물질
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int createMatCheckRequestCompo(MatCheckRequestCompoRegul matCheckRequestCompoRegul, int matChkRqstNo) throws Exception {
        int resultNo = 0;
        if (matCheckRequestCompoRegul.getRegulItmMatVals() != null) {
            this.matCheckRequestMapper.deleteMatCheckRequestCompoRegul(matChkRqstNo);
        }
        if (matCheckRequestCompoRegul.getRegulItmMatVals() != null && matCheckRequestCompoRegul.getRegulItmMatVals().size() != 0) {
            for (int regulItmNo : matCheckRequestCompoRegul.getRegulItmMatVals()) {
                RegulItmMatVal regulItmMatVal = new RegulItmMatVal();
                // 물질검토 규제 등록
                regulItmMatVal.setRegulItmNo(regulItmNo);
                regulItmMatVal.setMatChkRqstNo(matChkRqstNo);
                // regulItmMatVal.setCreateUserId(matCheckRequestCompoRegul.getCreateUserId());
                regulItmMatVal.setCreateUserId(iAuthenticationFacade.getUserName(""));
                resultNo += this.matCheckRequestMapper.createMatCheckRequestCompoRegul(regulItmMatVal);
            }
        }

        if (matCheckRequestCompoRegul.getMatCheckRequestCompos() != null) {
            this.matCheckRequestMapper.deleteMatCheckRequestCompo(matChkRqstNo);
        }
        if (matCheckRequestCompoRegul.getMatCheckRequestCompos() != null && matCheckRequestCompoRegul.getMatCheckRequestCompos().size() != 0) {
            for (MatCheckRequestCompo matCheckRequestCompo : matCheckRequestCompoRegul.getMatCheckRequestCompos()) {
                // 물질검토 구성성분 등록
                // matCheckRequestCompo.setCreateUserId(matCheckRequestCompoRegul.getCreateUserId());
                matCheckRequestCompo.setCreateUserId(iAuthenticationFacade.getUserName(""));
                matCheckRequestCompo.setMatChkRqstNo(matChkRqstNo);
                if (matCheckRequestCompo.getChemNo() == 0) {
                    matCheckRequestCompo.setChemNo(matCheckRequestMapper.searchChemNo(matCheckRequestCompo.getChemNmKr()).getChemNo());
                }
                resultNo += this.matCheckRequestMapper.createMatCheckRequestCompo(matCheckRequestCompo);
            }
        }

        return resultNo;
    }

    /**
     * sap 요청자재 임시로 생성
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveSapMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception {
        int resultNo = 0;
        // 자재 검토 요청
        Map<String, Object> params = new HashMap<>();
        // List<Map<String, Object>> paramsx = new HashMap<>();
        // Map<String, Object> x = new HashMap<>();
        // x.put("BUKRS", "PSC");
        // x.put("RQNUM", "0000054354");
        // paramsx.put("RQITM", "0001");
        // paramsx.put("MSDSC", "X");
        params.put("I_FINSH", "X");
        // params.put("ET_MAT", paramsx);
        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_MSDS", "", params); // 자재검토요청

        // if (datas != null && datas.size() > 0) {
        // for (Map<String, Object> data : datas) {
        // matCheckRequest.setBranchCd(String.valueOf(data.get("BUKRS"))); //
        // 회사코드
        // matCheckRequest.setRqstNo(String.valueOf(data.get("RQNUM"))); // 요청번호
        // matCheckRequest.setRqstItmNo(String.valueOf(data.get("RQITM"))); //
        // 요청품목번호
        // matCheckRequest.setRqstDt(String.valueOf(data.get("ERSDA"))); // 요청일
        // matCheckRequest.setChkRqsterId(String.valueOf(data.get("USRNB"))); //
        // 요청자
        // matCheckRequest.setSapMatCd(String.valueOf(data.get("MATNR"))); //
        // 자재코드
        // matCheckRequest.setPlantCd(String.valueOf(data.get("WERKS"))); // 사업장
        // matCheckRequest.setChemProdNmKr(String.valueOf(data.get("MAKTX")));
        // // 자재명
        // matCheckRequest.setChemProdNmEn(String.valueOf(data.get("MAKTX")));
        // // 자재명
        // resultNo +=
        // matCheckRequestMapper.saveSapMatCheckRequest(matCheckRequest);
        // }
        // }
        return resultNo;
    }

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
    public List<MatCheckRequestDbVerf> getRegulDbVertification(String chemNos, String limitRepvals, int chemCount, DefaultParam defaultParam) throws Exception {
        return matCheckRequestMapper.getRegulDbVertification(chemNos, limitRepvals, defaultParam);
    }

    /**
     * 안전/보건 검토완료처리
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    public MatCheckRequest confirmMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception {
        matCheckRequest.setChkResultFinUserId(iAuthenticationFacade.getUserName(""));
        matCheckRequestMapper.confirmMatCheckRequest(matCheckRequest);
        return matCheckRequestMapper.getMatCheckRequest(matCheckRequest.getMatChkRqstNo(), new DefaultParam("kr"));
    }

    /**
     * 환경 검토완료처리
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    public MatCheckRequest confirmEnvMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception {
        matCheckRequest.setChkResultEnvFinUserId(iAuthenticationFacade.getUserName(""));
        matCheckRequestMapper.confirmEnvMatCheckRequest(matCheckRequest);
        return matCheckRequestMapper.getMatCheckRequest(matCheckRequest.getMatChkRqstNo(), new DefaultParam("kr"));
    }

    /**
     * 검토반려
     * 
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    public int rejectMatCheckRequest(MatCheckRequest matCheckRequest) throws Exception {
        int resultNo = 0;
        matCheckRequest.setUpdateUserId(iAuthenticationFacade.getUserName(""));
        resultNo += matCheckRequestMapper.rejectMatCheckRequest(matCheckRequest);
        return resultNo > 0 ? matCheckRequest.getMatChkRqstNo() : 0;
    }
}
