package com.she.env.envEffectEval.service;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.DefaultParam;
import com.she.env.envEffectEval.mapper.EnvEffectEvalPlanMapper;
import com.she.env.envEffectEval.model.EnvEffectEvalPlan;
import com.she.env.envEffectEval.model.EnvEffectEvalRslt;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;

@Service
public class EnvEffectEvalPlanService {

    @Autowired
    private EnvEffectEvalPlanMapper envEffectEvalPlanMapper;

    /**
     * 환경영향평가 계획 목록조회
     * 
     * @param plantCd
     * @param deptCd
     * @param tgtDeptCd
     * @param envEffEvalPlanStepCd
     * @param envEffEvalDivCd
     * @param evalTitle
     * @param evalYearFrom
     * @param evalYearTo
     * @param apprStepCd
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<EnvEffectEvalPlan> getEnvEffectEvalPlans(String plantCd, String deptCd, String tgtDeptCd, String envEffEvalPlanStepCd, String envEffEvalDivCd, String evalTitle, String evalYearFrom, String evalYearTo, String apprStepCd, DefaultParam defaultParam) throws Exception {
        return this.envEffectEvalPlanMapper.getEnvEffectEvalPlans(plantCd, deptCd, tgtDeptCd, envEffEvalPlanStepCd, envEffEvalDivCd, evalTitle, evalYearFrom, evalYearTo, apprStepCd, defaultParam);
    }

    /**
     * 환경영향평가 계획 상세조회
     * 
     * @param evalPlanNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public EnvEffectEvalPlan getEnvEffectEvalPlan(int evalPlanNo, DefaultParam defaultParam) throws Exception {
        EnvEffectEvalPlan envEffectEvalPlan = this.envEffectEvalPlanMapper.getEnvEffectEvalPlan(evalPlanNo, defaultParam);
        if (envEffectEvalPlan != null) {
            envEffectEvalPlan.setEnvEffectEvalRslts(this.envEffectEvalPlanMapper.getEnvEffectEvalResults(evalPlanNo, defaultParam));
        }

        return envEffectEvalPlan;
    }

    /**
     * 환경영향평가 계획 등록
     * 
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    public int createEnvEffectEvalPlan(EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        this.envEffectEvalPlanMapper.createEnvEffectEvalPlan(envEffectEvalPlan);
        return envEffectEvalPlan.getEvalPlanNo();
    }

    /**
     * 환경영향평가 계획 수정
     * 
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalPlan(EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        this.envEffectEvalPlanMapper.updateEnvEffectEvalPlan(envEffectEvalPlan);
        return envEffectEvalPlan.getEvalPlanNo();
    }

    /**
     * 환경영향평가 계획 삭제
     * 
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    public int deleteEnvEffectEvalPlan(int evalPlanNo) throws Exception {
        int result = this.envEffectEvalPlanMapper.deleteEnvEffectEvalPlan(evalPlanNo);
        result += this.envEffectEvalPlanMapper.deleteEnvEffectEvalRslts(evalPlanNo);

        return result;
    }

    /**
     * 환경영향평가 결과 목록
     * 
     * @param evalPlanNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<EnvEffectEvalRslt> getEnvEffectEvalResults(int evalPlanNo, DefaultParam defaultParam) throws Exception {
        return this.envEffectEvalPlanMapper.getEnvEffectEvalResults(evalPlanNo, defaultParam);
    }

    /**
     * 환경영향평가 결과 상세
     * 
     * @param evalRsltNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public EnvEffectEvalRslt getEnvEffectEvalResult(int evalRsltNo, DefaultParam defaultParam) throws Exception {
        return this.envEffectEvalPlanMapper.getEnvEffectEvalResult(evalRsltNo, defaultParam);
    }

    /**
     * 환경영향평가 결과 등록
     * 
     * @param envEffectEvalRslt
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public EnvEffectEvalRslt createEnvEffectEvalRslt(EnvEffectEvalRslt envEffectEvalRslt, DefaultParam defaultParam) throws Exception {
        this.envEffectEvalPlanMapper.createEnvEffectEvalRslt(envEffectEvalRslt);
        return this.envEffectEvalPlanMapper.getEnvEffectEvalResult(envEffectEvalRslt.getEvalRsltNo(), defaultParam);
    }

    /**
     * 환경영향평가 결과 등록
     * 
     * @param envEffectEvalRslts
     * @return
     * @throws Exception
     */
    public int createEnvEffectEvalRslts(EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        int result = 0;
        if (envEffectEvalPlan.getEvalPlanNo() > 0 && envEffectEvalPlan.getEnvEffectEvalRslts() != null) {
            for (EnvEffectEvalRslt item : envEffectEvalPlan.getEnvEffectEvalRslts()) {
                // 엑셀업로드에서 생성함
                result += this.envEffectEvalPlanMapper.createEnvEffectEvalRslt(item);
            }
        }

        return result;
    }

    /**
     * 환경영향평가 결과 수정
     * 
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalRslts(EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        int result = 0;
        if (envEffectEvalPlan.getEvalPlanNo() > 0 && envEffectEvalPlan.getEnvEffectEvalRslts() != null) {
            for (EnvEffectEvalRslt item : envEffectEvalPlan.getEnvEffectEvalRslts()) {
                // 결과 추가시 데이터 즉시생성되므로 수정함
                result += this.envEffectEvalPlanMapper.updateEnvEffectEvalRslt(item);
            }
        }

        return result;
    }

    /**
     * 환경영향평가 결과 삭제
     * 
     * @param envEffectEvalRslts
     * @return
     * @throws Exception
     */
    public int deleteEnvEffectEvalRslt(List<EnvEffectEvalRslt> envEffectEvalRslts) throws Exception {
        int result = 0;
        if (envEffectEvalRslts != null) {
            for (EnvEffectEvalRslt item : envEffectEvalRslts) {
                result += this.envEffectEvalPlanMapper.deleteEnvEffectEvalRslt(item.getEvalRsltNo());
            }
        }
        return result;
    }

    /**
     * 환경영향평가 환경담당자 의견 등록
     * 
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalRsltManagerComment(EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        int result = 0;
        if (envEffectEvalPlan != null) {
            for (EnvEffectEvalRslt item : envEffectEvalPlan.getEnvEffectEvalRslts()) {
                result += this.envEffectEvalPlanMapper.updateEnvEffectEvalRsltManagerComment(item.getEvalRsltNo(), item.getManagerId(), item.getManagerComment());
            }
        }

        return result;
    }

    /**
     * 환경영형평가 등록부 목록
     * 
     * @param plantCd
     * @param deptCd
     * @param tgtDeptCd
     * @param tgtProcessCd
     * @param evalTitle
     * @param evalYearFrom
     * @param evalYearTo
     * @param safFacilityCd
     * @param impoRate
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<EnvEffectEvalRslt> getEnvEffectEvalImpoResults(String plantCd, String deptCd, String tgtDeptCd, String tgtProcessCd, String evalTitle, String evalYearFrom, String evalYearTo, String safFacilityCd, String impoRate, DefaultParam defaultParam) throws Exception {
        return this.envEffectEvalPlanMapper.getEnvEffectEvalImpoResults(plantCd, deptCd, tgtDeptCd, tgtProcessCd, evalTitle, evalYearFrom, evalYearTo, safFacilityCd, impoRate, defaultParam);
    }

    /**
     * 환경영향평가 결재 후속 프로세스
     * 
     * @param evalPlanNo
     * @param apprBizCd
     * @param bizApprStepCd
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int apprProcessEnvEffectEval(int evalPlanNo, String apprBizCd, String bizApprStepCd, int apprRqstNo) throws Exception {
        int result = 0;
        if (apprBizCd.equals("EN-EE-01")) {
            // 환경영향평가 계획
            result += this.envEffectEvalPlanMapper.updateEnvEffectEvalPlanAppr(evalPlanNo, apprRqstNo);
            if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
                // 반려시 상태 변경-계획 상태
                result += this.envEffectEvalPlanMapper.updateEnvEffectEvalPlanStatus(evalPlanNo, ConstVal.SAF_ENV_EFFECT_EVAL_STEP_PLAN);
            } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
                // 승인시 상태 변경-결과 상태
                result += this.envEffectEvalPlanMapper.updateEnvEffectEvalPlanStatus(evalPlanNo, ConstVal.SAF_ENV_EFFECT_EVAL_STEP_RSLT);
            }
        } else if (apprBizCd.equals("EN-EE-02")) {
            // 환경영향평가 결과
            result += this.envEffectEvalPlanMapper.updateEnvEffectEvalRsltAppr(evalPlanNo, apprRqstNo);
            if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
                // 반려시 상태 변경-결과 상태
                result += this.envEffectEvalPlanMapper.updateEnvEffectEvalPlanStatus(evalPlanNo, ConstVal.SAF_ENV_EFFECT_EVAL_STEP_RSLT);
            } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
                // 등록된 개선 건 상태 변경
                result += this.envEffectEvalPlanMapper.updateEnvEffectEvalImprStatus(evalPlanNo);
            }
        }

        return result;
    }

    /**
     * 환경영향평가 결과 엑셀업로드
     * 
     * @param files
     * @param lang
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    public Map<String, Object> uploadExcel(MultipartFile[] files, String lang, int evalPlanNo) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("message", "");
        map.put("uploadResult", null);

        // 메시지 조회
        Map<String, String> messageMap = this.envEffectEvalPlanMapper.getExcelMessageLbl(lang);
        try {
            List<EnvEffectEvalRslt> items = new ArrayList<EnvEffectEvalRslt>();
            InputStream stream = files[0].getInputStream();
            File tempFile = File.createTempFile(String.valueOf(stream.hashCode()), ".xlsx");
            tempFile.deleteOnExit();
            FileUtils.copyInputStreamToFile(stream, tempFile);

            ExcelReader reader = new ExcelReader();
            List<String[][]> sheets = reader.read(tempFile);
            if (sheets != null && sheets.size() > 0) {
                String[][] sheet = sheets.get(0);

                for (int i = 3; i < sheet.length; i++) {
                    EnvEffectEvalRslt item = new EnvEffectEvalRslt();
                    item.setEvalPlanNo(evalPlanNo);
                    item.setUploadFlag(true);
                    item.setUploadMessages(new ArrayList<String>());
                    for (int j = 0; j < sheet[0].length; j++) {
                        String val = sheet[i][j];
                        switch (j) {
                        case 0: // 설비
                            this.checkFacility(val, lang, item, messageMap);
                            break;
                        case 1: // 세부공정
                            this.checkDetailProcess(val, lang, item, messageMap);
                            break;
                        case 2: // 구동설비명
                            item.setFacilityNm(val);
                            break;
                        case 3: // 연료
                            item.setFacilityFuel(val);
                            break;
                        case 4: // 사용량
                            item.setFacilityAmt(val);
                            break;
                        case 5: // 입력물
                            item.setInputNm(val);
                            break;
                        case 6: // 입력량
                            item.setInputAmt(val);
                            break;
                        case 7: // 출력물
                            item.setOutputNm(val);
                            break;
                        case 8: // 출력량
                            item.setOutputAmt(val);
                            break;
                        case 9: // 부산물
                            item.setResidueNm(val);
                            break;
                        case 10: // 부산물량
                            item.setResidueAmt(val);
                            break;
                        case 11: // 발생조건
                            this.checkEnvGen(val, lang, item, messageMap);
                            break;
                        case 12: // 상세활동
                            item.setEnvAct(val);
                            break;
                        case 13: // 환경분야
                            this.checkEnvImpDiv(val, lang, item, messageMap);
                            break;
                        case 14: // 관리방안
                            item.setEnvImpMng(val);
                            break;
                        case 15: // 환경이슈
                            this.checkEnvImpIssue(val, lang, item, messageMap);
                            break;
                        case 16: // 발생가능성
                            this.checkImpoGen(val, lang, item, messageMap);
                            break;
                        case 17: // 부정적영향
                            item.setImpoEffect("-");
                            break;
                        case 18: // 유해성
                            this.checkImpoToxic(val, lang, item, messageMap);
                            break;
                        case 19: // 유해성점수
                            item.setImpoToxicScore("-");
                            break;
                        case 20: // 양
                            this.checkImpoQnt(val, lang, item, messageMap);
                            break;
                        case 21: // 양점수
                            item.setImpoQntScore("-");
                            break;
                        case 22: // 통제방안
                            this.checkImpoCtrl(val, lang, item, messageMap);
                            break;
                        case 23: // 통제방안점수
                            item.setImpoCtrlScore("-");
                            break;
                        case 24: // 가중치
                            this.checkImpoWeight(val, lang, item, messageMap);
                            break;
                        case 25: // 심각성
                            item.setImpoSeriousness("-");
                            break;
                        case 26: // 등급
                            item.setImpoRate("-");
                            break;
                        case 27: // 유형
                            this.checkImpoType(val, lang, item, messageMap);
                            break;
                        case 28: // 대응방안
                            item.setConfDesc(val);
                            break;
                        }
                    }

                    if (item.isUploadFlag()) {
                        item.getUploadMessages().add(messageMap.get("L0000004943"));
                    }
                    items.add(item);
                }
            }

            map.put("uploadResult", items);

        } catch (Exception e) {
            map.put("success", false);
            map.put("message", messageMap.get("L0000003887") + ": " + e.getMessage());
        }

        return map;
    }

    /**
     * 중요성평가 설비 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkFacility(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        String safFacilityCd = this.envEffectEvalPlanMapper.checkExcelFacilityNm(val, item.getEvalPlanNo());
        if (safFacilityCd == null || safFacilityCd.equals("")) {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004944")); // L0000004944
        } else {
            item.setSafFacilityCd(safFacilityCd);
            item.setSafFacilityNm(val);
        }
    }

    /**
     * 중요성평가 상세공정 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkDetailProcess(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        String detailProcess = this.envEffectEvalPlanMapper.checkExcelLbl(lang, "detailProcess", val);
        if (detailProcess == null || detailProcess.equals("")) {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004945")); // L0000004945
        } else {
            item.setDetailProcess(detailProcess);
        }
    }

    /**
     * 중요성평가 발생조건 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkEnvGen(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        String envGen = this.envEffectEvalPlanMapper.checkExcelLbl(lang, "envGen", val);
        if (envGen == null || envGen.equals("")) {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004946")); // L0000004946
        } else {
            item.setEnvGen(envGen);
        }
    }

    /**
     * 중요성평가 환경분야 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkEnvImpDiv(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        String envImpDiv = this.envEffectEvalPlanMapper.checkExcelLbl(lang, "envImpDiv", val);
        if (envImpDiv == null || envImpDiv.equals("")) {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004947")); // L0000004947
        } else {
            item.setEnvImpDiv(envImpDiv);
        }
    }

    /**
     * 중요성평가 환경이슈 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkEnvImpIssue(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        int envImpIssue = this.checkNumeric(val);
        if (envImpIssue > -1) {
            if (envImpIssue >= 1 && envImpIssue <= 2) {
                item.setEnvImpIssue(String.valueOf(envImpIssue));
            } else {
                item.setUploadFlag(false);
                item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004948")); // L0000004948
            }
        } else {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004948") + "(" + messageMap.get("L0000004955") + ")");
        }
    }

    /**
     * 중요성평가 발생가능성 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkImpoGen(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        int impoGen = this.checkNumeric(val);
        if (impoGen > -1) {
            if (impoGen >= 1 && impoGen <= 5) {
                item.setImpoGen(String.valueOf(impoGen));
            } else {
                item.setUploadFlag(false);
                item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004949")); // L0000004949
            }
        } else {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004949") + "(" + messageMap.get("L0000004955") + ")");
        }
    }

    /**
     * 중요성평가 유해성 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkImpoToxic(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        String impoToxic = this.envEffectEvalPlanMapper.checkExcelLbl(lang, "impoToxicCtrl", val);
        if (impoToxic == null || impoToxic.equals("")) {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004950")); // L0000004950
        } else {
            item.setImpoToxic(impoToxic);
        }
    }

    /**
     * 중요성평가 양 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkImpoQnt(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        String impoQnt = this.envEffectEvalPlanMapper.checkExcelLbl(lang, "impoQnt", val);
        if (impoQnt == null || impoQnt.equals("")) {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004951")); // L0000004951
        } else {
            item.setImpoQnt(impoQnt);
        }
    }

    /**
     * 중요성평가 통제방안 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkImpoCtrl(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        String impoCtrl = this.envEffectEvalPlanMapper.checkExcelLbl(lang, "impoToxicCtrl", val);
        if (impoCtrl == null || impoCtrl.equals("")) {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004952")); // L0000004952
        } else {
            item.setImpoCtrl(impoCtrl);
        }
    }

    /**
     * 중요성평가 가중치 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkImpoWeight(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        int impoWeight = this.checkNumeric(val);
        if (impoWeight > -1) {
            if (impoWeight >= 1 && impoWeight <= 2) {
                item.setImpoWeight(String.valueOf(impoWeight));
            } else {
                item.setUploadFlag(false);
                item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004953")); // L0000004953
            }
        } else {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004953") + "(" + messageMap.get("L0000004955") + ")");
        }
    }

    /**
     * 중요성평가 유형 체크
     * 
     * @param val
     * @param lang
     * @param item
     * @param messageMap
     * @return
     * @throws Exception
     */
    private void checkImpoType(String val, String lang, EnvEffectEvalRslt item, Map<String, String> messageMap) throws Exception {
        int impoType = this.checkNumeric(val);
        if (impoType > -1) {
            if (impoType >= 1 && impoType <= 4) {
                item.setImpoType(String.valueOf(impoType));
            } else {
                item.setUploadFlag(false);
                item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004954")); // L0000004954
            }
        } else {
            item.setUploadFlag(false);
            item.getUploadMessages().add(messageMap.get("L0000003887") + ": " + messageMap.get("L0000004954") + "(" + messageMap.get("L0000004955") + ")");
        }
    }

    /**
     * 숫자 반환
     * 
     * @param val
     * @return
     * @throws Exception
     */
    private int checkNumeric(String val) throws Exception {
        int result = -1;
        try {
            result = Integer.valueOf(val);
        } catch (Exception e) {
            try {
                double d = Double.valueOf(val);
                result = (int) Math.floor(d);
            } catch (Exception ee) {
                result = -1;
            }
        }
        return result;
    }
}
