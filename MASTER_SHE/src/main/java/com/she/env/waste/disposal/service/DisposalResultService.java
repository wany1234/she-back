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
package com.she.env.waste.disposal.service;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.waste.disposal.mapper.DisposalRequestMapper;
import com.she.env.waste.disposal.mapper.DisposalResultMapper;
import com.she.env.waste.model.DisposalMtd;
import com.she.env.waste.model.DisposalRequest;
import com.she.env.waste.model.DisposalResult;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;
import com.she.utils.Methods;

/**
 * 폐기물 처리결과 기능정의
 *
 */
@Service("EwstDisposalResultService")
public class DisposalResultService {
    @Autowired
    private DisposalRequestMapper disposalRequestMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private DisposalResultMapper disposalResultMapper;

    private String disposalResultExcelFileName = "폐기물_처리결과_양식_v1.0.xlsx";

    /**
     * 폐기물 처리결과 전체 조회
     *
     * @param reqYmdFrom
     *            검색시작일(요청)
     * @param reqYmdTo
     *            검색종료일(요청)
     * @param ewstDispoStCd
     *            처리상태
     * @param ewstClassCd
     *            폐기물분류
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @return 폐기물 처리결과 목록
     * @throws Exception
     */
    public List<DisposalResult> getDisposalResults(String dispoDeptCd, String reqYmdFrom, String reqYmdTo, String ewstDispoStCd, String ewstClassCd, int ewstDispoReqNo, String ewstDispoCoNo, String ewstFreightCoNo, String plantCd, String ewstWasteNo, DefaultParam defaultParam) throws Exception {
        return this.disposalResultMapper.getDisposalResults(dispoDeptCd, reqYmdFrom, reqYmdTo, ewstDispoStCd, ewstClassCd, ewstDispoReqNo, ewstDispoCoNo, ewstFreightCoNo, plantCd, ewstWasteNo, defaultParam);
    }

    /**
     * 선택된 폐기물 처리결과 상세 조회
     *
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 폐기물 처리결과
     * @throws Exception
     */
    public DisposalResult getDisposalResult(int ewstDispoResultNo, DefaultParam defaultParam) throws Exception {
        DisposalResult disposalResult = this.disposalResultMapper.getDisposalResult(ewstDispoResultNo, defaultParam);
        disposalResult.setDisporateDeptItem(this.disposalResultMapper.getDispoRateResult(ewstDispoResultNo));
        return disposalResult;
    }
    
    /**
     * 선택된 폐기물 처리요청 조회
     *
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 폐기물 처리요청 결과
     * @throws Exception
     */
    public List<DisposalRequest> getResult(int ewstDispoResultNo, DefaultParam defaultParam) throws Exception {
        return this.disposalResultMapper.getDisposalRequsts(ewstDispoResultNo, defaultParam);
    }

    /**
     * 폐기물 처리결과 신규 생성
     *
     * @param disposalResult
     *            폐기물 처리결과
     * @return 폐기물 처리결과 번호
     * @throws Exception
     */
    @Transactional
    public int createDisposalResult(DisposalResult disposalResult) throws Exception {
        // // 처리결과 등록시 처리요청의 상태를 처리중으로 변경
        // this.disposalRequestMapper.updateDisposalRequestStatus(disposalResult.getEwstDispoReqNo(),
        // ConstVal.ENV_WASTE_DISPOSAL_STATUS_PROCESS);

        disposalResult.setEwstDispoStCd(ConstVal.ENV_WASTE_DISPOSAL_STATUS_PROCESS);
        this.disposalResultMapper.createDisposalResult(disposalResult);
        if (disposalResult.getDisposalRequest() != null && disposalResult.getDisposalRequest().size() > 0) {
            for (DisposalRequest disposalRequest : disposalResult.getDisposalRequest()) {
                this.disposalResultMapper.createDispoRequests(disposalResult.getEwstDispoResultNo(), disposalRequest.getEwstDispoReqNo());
            }
        }
        this.disposalResultMapper.deleteDispoRequests(disposalResult.getEwstDispoResultNo());
        for (DisposalRequest disposalRequest : disposalResult.getDisposalRequest()) {
            this.disposalResultMapper.createDispoRequests(disposalResult.getEwstDispoResultNo(), disposalRequest.getEwstDispoReqNo());
            if (ConstVal.ENV_WASTE_DISPOSAL_STATUS_PROCESS.equals(disposalResult.getEwstDispoStCd())) {
                // 처리 결과 내역이 입력되는 동시에 처리요청의 단계가 완료 상태로 바뀌어야 한다.
                this.disposalResultMapper.updateDispoReqComplete(disposalRequest.getEwstDispoReqNo());
            }
        }

        return disposalResult.getEwstDispoResultNo();
    }

    /**
     * 선택된 폐기물 처리결과 수정
     *
     * @param disposalResult
     *            폐기물 처리결과
     * @return 수정행수
     * @throws Exception
     */
    @Transactional
    public int updateDisposalResult(DisposalResult disposalResult, DefaultParam defaultParam) throws Exception {
        // // 처리결과 등록시 처리요청의 상태를 처리중으로 변경
        // this.disposalRequestMapper.updateDisposalRequestStatus(disposalResult.getEwstDispoReqNo(),
        // ConstVal.ENV_WASTE_DISPOSAL_STATUS_PROCESS);

        // 업데이트 전 처리건에 대한 폐기물 요청건이 변경되었다면 폐기물 요청건에 대한 처리건 유무를 확인 후 상태 업데이트
        DisposalResult orgDisposalResult = this.disposalResultMapper.getDisposalResult(disposalResult.getEwstDispoResultNo(), defaultParam);

        int count = this.disposalResultMapper.updateDisposalResult(disposalResult);
        if (disposalResult.getEwstDispoReqNo() != orgDisposalResult.getEwstDispoReqNo()) {
            List<DisposalResult> disposalResults = this.disposalResultMapper.getDisposalResults("", "", "", "", "", orgDisposalResult.getEwstDispoReqNo(), "", "", "", "", defaultParam);
            if (disposalResults.size() == 0) {
                this.disposalRequestMapper.updateDisposalRequestStatus(orgDisposalResult.getEwstDispoReqNo(), ConstVal.ENV_WASTE_DISPOSAL_STATUS_REQUEST);
            }
        }

        this.disposalResultMapper.deleteDispoRequests(disposalResult.getEwstDispoResultNo());
        for (DisposalRequest disposalRequest : disposalResult.getDisposalRequest()) {
            this.disposalResultMapper.createDispoRequests(disposalResult.getEwstDispoResultNo(), disposalRequest.getEwstDispoReqNo());
            if (ConstVal.ENV_WASTE_DISPOSAL_STATUS_PROCESS.equals(disposalResult.getEwstDispoStCd())) {
                // 처리 결과 내역이 입력되는 동시에 처리요청의 단계가 완료 상태로 바뀌어야 한다.
                this.disposalResultMapper.updateDispoReqComplete(disposalRequest.getEwstDispoReqNo());
            }
        }

        if (ConstVal.ENV_WASTE_DISPOSAL_STATUS_COMPLETE.equals(disposalResult.getEwstDispoStCd())) {
            // 처리요청 내역이 다 올라간 후 처리요청 목록 중 처리완료로 단계가 되어 있지만 폐기물 처리별 요청
            // 테이블(ewst_dispo_result_req)에서는 없어진 경우에는 처리요청 단계로 변경 한다.
            this.disposalResultMapper.updateDispoReqRequest();
        }

        return count;
    }

    /**
     * 선택된 폐기물 처리결과 상태 수정
     *
     * @param ewstDispoReqNo
     *            폐기물 처리결과 번호
     * @param ewstDispoStCd
     *            처리상태
     * @return 수정행수
     * @throws Exception
     */
    public int updateDisposalResultStatus(int ewstDispoResultNo, String ewstDispoStCd) throws Exception {
        return this.disposalResultMapper.updateDisposalResultStatus(ewstDispoResultNo, ewstDispoStCd);
    }

    /**
     * 선택된 폐기물 처리결과 완료처리
     *
     * @param disposalResult
     *            폐기물 처리결과
     * @return 수정행수
     * @throws Exception
     */
    @Transactional
    public int completeDisposalResult(DisposalResult disposalResult) throws Exception {

        disposalResult.setEwstDispoStCd(ConstVal.ENV_WASTE_DISPOSAL_STATUS_COMPLETE);
        if (disposalResult.getDispoYmd() == null || disposalResult.getDispoYmd().equals("")) {
            // 처리일이 공란일 경우 현재일 기준으로 저장
            disposalResult.setDispoYmd(Methods.convertDateToString(new Date()));
        }

        if (disposalResult.getDisposalRequest().size() != 0) {
            for (DisposalRequest result : disposalResult.getDisposalRequest()) {
                this.disposalRequestMapper.updateDisposalRequestStatus(result.getEwstDispoReqNo(), ConstVal.ENV_WASTE_DISPOSAL_STATUS_COMPLETE);
            }

            this.disposalResultMapper.updateDisposalResult(disposalResult);
        }

        return disposalResult.getEwstDispoResultNo();
    }

    /**
     * 선택된 폐기물 처리결과 삭제
     *
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 삭제행수
     * @throws Exception
     */
    @Transactional
    public int deleteDisposalResult(int ewstDispoResultNo) throws Exception {
        this.disposalResultMapper.deleteDispoRequests(ewstDispoResultNo);
        return this.disposalResultMapper.deleteDisposalResult(ewstDispoResultNo);
    }

    /**
     * 폐기물 처리결과 엑셀 조회
     *
     * @param reqYmdFrom
     *            요청시작일
     * @param reqYmdTo
     *            요청종료일
     * @return 폐기물 처리결과 엑셀
     * @throws Exception
     */
    public File createDisposalResultExcel(String reqYmdFrom, String reqYmdTo, DefaultParam defaultParam) throws Exception {
        CodeMaster tmpletFilePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");
        CodeMaster tempFilePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_TEMP, "Y");

        String orgfilePath = tmpletFilePath + "\\" + this.disposalResultExcelFileName;
        int dotIndex = this.disposalResultExcelFileName.lastIndexOf('.');
        String fileName = this.disposalResultExcelFileName.substring(0, dotIndex) + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + this.disposalResultExcelFileName.substring(dotIndex);
        String filePath = tempFilePath + "\\" + fileName;

        File orgFile = new File(orgfilePath);
        File file = new File(filePath);

        Files.copy(orgFile.toPath(), file.toPath());

        ExcelReader reader = new ExcelReader();
        List<List<String>> appendRows = new ArrayList<List<String>>();
        List<DisposalResult> disposalResults = this.disposalResultMapper.getDisposalResultsExcel(reqYmdFrom, reqYmdTo, defaultParam);
        for (DisposalResult disposalResult : disposalResults) {
            List<String> appendRow = new ArrayList<String>();
            appendRow.add(this.convertExcelCellData(disposalResult.getDispoDeptNm()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEwstDispoResultNo()));
            appendRow.add(this.convertExcelCellData(disposalResult.getDispoYmd()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEwstClassNm()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEwstPhaseNm()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEwstDivNm()));
            appendRow.add(""); // 구분은?
            appendRow.add(this.convertExcelCellData(disposalResult.getAmtGen()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEnvUnitNm()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEwstFreightCoNm()));
            appendRow.add(this.convertExcelCellData(disposalResult.getCarrier()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEwstDispoCoNm()));
            appendRow.add(this.convertExcelCellData(disposalResult.getEwstDispoMtdNm()));
            appendRow.add(this.convertExcelCellData(disposalResult.getDispoUserNm()));

            appendRows.add(appendRow);
        }

        if (appendRows.size() > 0) {
            reader.appendExcelRows(file, 0, appendRows);
        }

        return file;
    }

    /**
     * 폐기물 처리방법 전체 조회
     *
     * @return 폐기물 처리결과 목록
     * @throws Exception
     */
    public List<DisposalMtd> getDisposalResultsMtd() throws Exception {
        return this.disposalResultMapper.getDisposalResultsMtd();
    }

    /**
     * null 문구 제거를 위한 변환함수
     *
     * @param o
     * @return
     */
    private String convertExcelCellData(Object o) {
        if (o == null) {
            return "";
        } else {
            return String.valueOf(o);
        }
    }
}
