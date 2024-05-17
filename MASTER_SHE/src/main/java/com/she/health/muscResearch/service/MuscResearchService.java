package com.she.health.muscResearch.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.SimpleDateFormat;
import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.health.model.MuscHarmful;
import com.she.health.model.MuscHarmfulEval;
import com.she.health.model.MuscResearch;
import com.she.health.model.MuscResearchChklist;
import com.she.health.model.MuscResearchDept;
import com.she.health.model.MuscResearchRslt;
import com.she.health.model.MuscResearchUnit;
import com.she.health.model.MuscSurveyChklist;
import com.she.health.model.MuscSurveyRslt;
import com.she.health.muscResearch.mapper.MuscResearchMapper;
import com.she.impr.service.ImprService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.mapper.DeptProcessMapper;
import com.she.manage.mapper.UserMapper;
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.manage.service.ProcessService;
import com.she.rsa.assess.service.AssessActionService;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;
import com.she.utils.ExcelUtil;
import com.she.utils.FileUtil;

// SK E&S
/**
 * 건강관리실 기능 정의
 *
 */
@Service
public class MuscResearchService {
    private static Logger logger = LoggerFactory.getLogger(AssessActionService.class);

    @Autowired
    private MuscCreateService muscCreateService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MuscResearchMapper muscResearchMapper;

    @Autowired
    private DeptProcessMapper deptProcessMapper;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private CodeMasterService codeMasterService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private ImprService imprService;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 근골격계 조사목록 조회
     *
     * @param plantCd
     * @param muscResearchStateCd
     * @param researchNm
     * @param startDate
     * @param endDate
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<MuscResearch> getMuscResearchs(String plantCd, String muscResearchStateCd, String researchNm, String startDate, String endDate, String deptCd, String deptSubYn) throws Exception {
        return muscResearchMapper.getMuscResearchs(plantCd, muscResearchStateCd, researchNm, startDate, endDate, deptCd, deptSubYn);
    }

    /**
     * 근골격계 사전조사 조회
     *
     * @param muscResearchNo
     *            기본조사번호
     * @return 근골격계 기본조사 정보
     * @throws Exception
     */
    public MuscResearch getMuscResearch(int muscResearchNo) throws Exception {
        MuscResearch muscResearch = muscResearchMapper.getMuscResearch(muscResearchNo);
        if (muscResearch != null) {
            // 조사부서 조회
            muscResearch.setMuscResearchDepts(muscResearchMapper.getMuscResearchDepts(muscResearchNo));
        }
        // 대상공정 조회
        // muscSurvey.setUnitItems(muscResearchMapper.getunitWork(muscResearchNo));

        return muscResearch;
    }

    /**
     * 근골격계 사전조사 저장
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    @Transactional
    public int createMuscResearch(MuscResearch muscResearch) throws Exception {
        int resultNo = 0;
        muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_STATE_WRITE);
        resultNo += muscResearchMapper.createMuscResearch(muscResearch);

        // 조사부서 저장
        if (CollectionUtils.isNotEmpty(muscResearch.getMuscResearchDepts())) {
            for (MuscResearchDept muscResearchDept : muscResearch.getMuscResearchDepts()) {
                muscResearchDept.setMuscResearchNo(muscResearch.getMuscResearchNo());
                muscResearchDept.setCreateUserId(muscResearch.getCreateUserId());
                resultNo += muscResearchMapper.createMuscResearchDept(muscResearchDept);
            }
        }

        return resultNo > 0 ? muscResearch.getMuscResearchNo() : 0;
    }

    /**
     * 근골격계 사전조사 수정
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer updateMuscResearch(MuscResearch muscResearch) throws Exception {
        int resultNo = 0;
        // 근골격계 계획 수정
        resultNo += muscResearchMapper.updateMuscResearch(muscResearch);

        // 조사부서 저장
        if (muscResearch.getMuscResearchDepts() != null && !ConstVal.MUSC_RESEARCH_HARMFUL_SUR_COMPLETE.equals(muscResearch.getMuscResearchStateCd())) {
            resultNo += muscResearchMapper.deleteMuscResearchDept(muscResearch.getMuscResearchNo());
            if (muscResearch.getMuscResearchDepts().size() > 0) {
                for (MuscResearchDept muscResearchDept : muscResearch.getMuscResearchDepts()) {
                    muscResearchDept.setMuscResearchNo(muscResearch.getMuscResearchNo());
                    muscResearchDept.setCreateUserId(muscResearch.getUpdateUserId());
                    resultNo += muscResearchMapper.createMuscResearchDept(muscResearchDept);
                }
            }
        }

        return resultNo;
    }

    /**
     * 근골격계 결과등록에서 결과완료
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer completeResearchChklist(MuscResearch muscResearch) throws Exception {
        int result = 0;

        /**
         * [근골격계 진행상태 변경]
         *
         * 조사부서의 제출여부 Y 처리
         *
         * 전체 조사부서의 제출여부가 Y인 경우 결과작성중 >> 조사결과제출완료 상태변경
         */
        if (ConstVal.MUSC_RESEARCH_RESULT_STATE_WRITE.equals(muscResearch.getMuscResearchStateCd())) {
            // 조사부서의 제출여부를 Y처리
            MuscResearchDept muscResearchDept = new MuscResearchDept();
            muscResearchDept.setMuscResearchDeptNo(muscResearch.getMuscResearchDeptNo());
            muscResearchDept.setDeptCd(muscResearch.getDeptCd());
            muscResearchDept.setSendYn("Y");
            muscResearchDept.setSendUserId(muscResearch.getUpdateUserId());
            muscResearchDept.setUpdateUserId(muscResearch.getUpdateUserId());
            result += muscResearchMapper.updateMuscResearchDept(muscResearchDept);

            List<MuscResearchDept> savedMuscResearchDepts = muscResearchMapper.getMuscResearchDepts(muscResearch.getMuscResearchNo());

            if (savedMuscResearchDepts != null && savedMuscResearchDepts.size() > 0) {
                boolean check = true;
                // 전체 조사부서제 제출 했는지 확인
                for (MuscResearchDept savedMuscResearchDept : savedMuscResearchDepts) {
                    if (!"Y".equals(savedMuscResearchDept.getSendYn())) {
                        check = false;
                        break;
                    }
                }

                // 전체 조사부서가 제출한 경우
                if (check) {
                    muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_HARMFUL_SEND_COMP);
                    result += muscResearchMapper.completeMuscResearch(muscResearch);

                    /**
                     * [유해요인조사 등록]
                     *
                     */
                    int harmfulInsertCnt = 0;
                    MuscHarmful muscHarmful = new MuscHarmful();
                    muscHarmful.setCreateUserId(muscResearch.getCreateUserId());
                    muscHarmful.setMuscResearchNo(muscResearch.getMuscResearchNo());
                    harmfulInsertCnt = muscResearchMapper.createHarmful(muscHarmful);

                    if (harmfulInsertCnt == 0) {
                        /**
                         * 유해요인 조사할 것이 없는 경우
                         *
                         * 유해요인조사중 단계로 변경
                         */
                        muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_HARMFUL_SUR_WRITE);

                        result += muscResearchMapper.completeMuscResearch(muscResearch);
                    }
                }
            }
        } else if (ConstVal.MUSC_RESEARCH_HARMFUL_SUR_WRITE.equals(muscResearch.getMuscResearchStateCd())) {
            /**
             * [근골격계 진행상태 변경]
             *
             * 유해요인조사중 >> 유해요인조사완료
             */
            muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_HARMFUL_SUR_COMPLETE);

            result += muscResearchMapper.completeMuscResearch(muscResearch);

            /**
             * 21.12.27 LHJ 기존에 결재 프로세스를 진행할 때 결재완료시 개선사항 접수상태로 업데이트에서 결재 프로세스
             * 제거 후 유해요인 조사완료시 개선사항 접수상태로 업데이트
             */
            result += imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_MUSC_RESEARCH, muscResearch.getMuscResearchNo(), muscResearch.getUpdateUserId());

        }
        return result;

    }

    /**
     * 근골격계 결과등록에서 전부서 결과완료(보건담당자, 시스템관리자)
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer completeResearchAllChklist(MuscResearch muscResearch) throws Exception {
        int result = 0;

        /**
         * [근골격계 진행상태 변경]
         *
         * 조사부서의 제출여부 Y 처리
         *
         * 전체 조사부서의 제출여부가 Y인 경우 결과작성중 >> 조사결과제출완료 상태변경
         */
        if (ConstVal.MUSC_RESEARCH_RESULT_STATE_WRITE.equals(muscResearch.getMuscResearchStateCd())) {

            for (MuscResearchDept muscResearchDept : muscResearch.getMuscResearchDepts()) {
                muscResearchDept.setSendYn("Y");
                muscResearchDept.setSendUserId(muscResearch.getUpdateUserId());
                muscResearchDept.setUpdateUserId(muscResearch.getUpdateUserId());
                result += muscResearchMapper.updateMuscResearchDept(muscResearchDept);
            }

            List<MuscResearchDept> savedMuscResearchDepts = muscResearchMapper.getMuscResearchDepts(muscResearch.getMuscResearchNo());

            if (savedMuscResearchDepts != null && savedMuscResearchDepts.size() > 0) {
                boolean check = true;
                // 전체 조사부서제 제출 했는지 확인
                for (MuscResearchDept savedMuscResearchDept : savedMuscResearchDepts) {
                    if (!"Y".equals(savedMuscResearchDept.getSendYn())) {
                        check = false;
                        break;
                    }
                }

                // 전체 조사부서가 제출한 경우
                if (check) {
                    muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_HARMFUL_SEND_COMP);
                    result += muscResearchMapper.completeMuscResearch(muscResearch);

                    /**
                     * [유해요인조사 등록]
                     *
                     */
                    int harmfulInsertCnt = 0;
                    MuscHarmful muscHarmful = new MuscHarmful();
                    muscHarmful.setCreateUserId(muscResearch.getCreateUserId());
                    muscHarmful.setMuscResearchNo(muscResearch.getMuscResearchNo());
                    harmfulInsertCnt = muscResearchMapper.createHarmful(muscHarmful);

                    if (harmfulInsertCnt == 0) {
                        /**
                         * 유해요인 조사할 것이 없는 경우
                         *
                         * 유해요인조사중 단계로 변경
                         */
                        muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_HARMFUL_SUR_WRITE);

                        result += muscResearchMapper.completeMuscResearch(muscResearch);
                    }
                }
            }
        }
        return result;

    }

    @Transactional
    public Integer completeMuscResearch(MuscResearch muscResearch) throws Exception {
        int resultNo = 0;
        muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_STATE_END);
        resultNo += muscResearchMapper.updateMuscResearch(muscResearch);

        // 조사부서 저장
        if (muscResearch.getMuscResearchDepts() != null) {
            resultNo += muscResearchMapper.deleteMuscResearchDept(muscResearch.getMuscResearchNo());
            if (muscResearch.getMuscResearchDepts().size() > 0) {
                for (MuscResearchDept muscResearchDept : muscResearch.getMuscResearchDepts()) {
                    muscResearchDept.setMuscResearchNo(muscResearch.getMuscResearchNo());
                    muscResearchDept.setCreateUserId(muscResearch.getUpdateUserId());
                    resultNo += muscResearchMapper.createMuscResearchDept(muscResearchDept);
                }
            }
        }

        return resultNo;
    }

    /**
     * 근골격계 기본정보 삭제
     *
     * @param muscResearchNo
     * @return
     * @throws Exception
     */
    public Integer deleteMuscResearch(int muscResearchNo) throws Exception {
        Integer result = 0;
        // 주관부서 삭제
        result += this.muscResearchMapper.deleteMuscResearchDept(muscResearchNo);
        // 근골격계 삭제
        result += this.muscResearchMapper.deleteMuscResearch(muscResearchNo);
        return result;
    }

    /**
     * 근골격계 결과등록 조사목록 조회
     *
     * @param plantCd
     * @param muscResearchStateCd
     * @param researchNm
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<MuscResearch> getMuscResearchResults(String plantCd, String muscResearchStateCd, String researchNm, String startDate, String endDate, String deptCd, String deptSubYn) throws Exception {
        return muscResearchMapper.getMuscResearchResults(plantCd, muscResearchStateCd, researchNm, startDate, endDate, deptCd, deptSubYn);
    }

    /**
     * 근골격계 단위작업 목록 조회
     *
     * @param muscResearchNo
     * @return
     * @throws Exception
     */
    public List<MuscResearchUnit> getunitWorks(int muscResearchNo, String deptCd) throws Exception {
        return muscResearchMapper.getunitWorks(muscResearchNo, deptCd);
    }

    public int getCheckUnitWorks(int muscResearchUnitNo, int muscResearchNo, String deptCd, String unitWorkNm) throws Exception {
        return muscResearchMapper.getCheckUnitWorkCnt(muscResearchUnitNo, muscResearchNo, deptCd, unitWorkNm);
    }

    /**
     * 근골격계 단위작업 순번
     *
     * @param muscResearchNo
     * @return
     * @throws Exception
     */
    public int getCountunitWork(int muscResearchNo) throws Exception {
        return muscResearchMapper.getCountunitWork(muscResearchNo);
    }

    /**
     * 근골격계 단위작업 상세조회
     *
     * @param muscResearchUnitNo
     * @return
     * @throws Exception
     */
    public MuscResearchUnit getunitWork(int muscResearchUnitNo) throws Exception {
        MuscResearchUnit muscResearchUnit = muscResearchMapper.getunitWork(muscResearchUnitNo);

        if (muscResearchUnit != null) {
            muscResearchUnit.setMuscResearchChklists(this.muscResearchMapper.getResearchChklist(muscResearchUnitNo));
        }
        // 대상공정 조회
        // muscSurvey.setUnitItems(muscResearchMapper.getunitWork(muscResearchNo));

        // MuscResearchUnit muscResearchUnit =
        // muscResearchMapper.getunitWork(muscResearchUnitNo);
        // if(muscResearchUnit != null) {
        // muscResearchUnit.setChklistItems(this.muscResearchMapper.getResearchChklists(muscResearchUnitNo));
        // }
        //

        return muscResearchUnit;
    }

    /**
     * 근골격계 단위작업 등록
     *
     * @param muscResearchUnit
     * @return
     * @throws Exception
     */
    @Transactional
    public int createUnitWork(MuscResearchUnit muscResearchUnit) throws Exception {
        int resultNo = 0;
        resultNo += this.muscResearchMapper.createUnitWork(muscResearchUnit);

        if (muscResearchUnit.getMuscResearchChklists() != null && muscResearchUnit.getMuscResearchChklists().size() > 0) {
            for (MuscResearchRslt muscResearchRslt : muscResearchUnit.getMuscResearchChklists()) {
                muscResearchRslt.setMuscResearchUnitNo(muscResearchUnit.getMuscResearchUnitNo());
                resultNo += this.muscResearchMapper.createResearchUnitRslt(muscResearchRslt);
            }

        }

        return resultNo > 0 ? muscResearchUnit.getMuscResearchUnitNo() : 0;
    }

    /**
     * 근골격계 단위작업 수정
     *
     * @param muscResearchUnit
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer updateUnitWork(MuscResearchUnit muscResearchUnit) throws Exception {

        int resultNo = 0;

        resultNo += muscResearchMapper.updateUnitWork(muscResearchUnit);

        if (muscResearchUnit.getMuscResearchChklists() != null) {
            resultNo += muscResearchMapper.deleteResearchChklist(muscResearchUnit.getMuscResearchUnitNo());

            if (muscResearchUnit.getMuscResearchChklists().size() > 0) {
                for (MuscResearchRslt muscResearchRslt : muscResearchUnit.getMuscResearchChklists()) {
                    muscResearchRslt.setMuscResearchUnitNo(muscResearchUnit.getMuscResearchUnitNo());
                    resultNo += this.muscResearchMapper.createResearchUnitRslt(muscResearchRslt);
                }
            }
        }

        return resultNo;

    }

    /**
     * 근골격계 단위작업 삭제
     *
     * @param muscResearchUnits
     * @return
     * @throws Exception
     */
    public int deleteUnitWork(List<MuscResearchUnit> muscResearchUnits) throws Exception {
        int resultNo = 0;

        if (muscResearchUnits != null && muscResearchUnits.size() > 0) {
            for (MuscResearchUnit muscResearchUnit : muscResearchUnits) {
                resultNo += this.muscResearchMapper.deleteResearchChklist(muscResearchUnit.getMuscResearchUnitNo());
                resultNo += this.muscResearchMapper.deleteUnitWork(muscResearchUnit.getMuscResearchUnitNo());
            }
        }

        return resultNo;
    }

    /**
     * 근골격계 부담작업no 리스트 조회
     *
     * @return
     * @throws Exception
     */
    public List<MuscResearchChklist> getResearchChklists() throws Exception {
        return muscResearchMapper.getResearchChklists();
    }

    /**
     * 작업분류표 조회
     *
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRsltChklist(int muscResearchNo) throws Exception {
        return muscResearchMapper.getRsltChklist(muscResearchNo);
    }

    /**
     * 근골격계 부담작업no 수정
     *
     * @param muscResearchRslts
     * @param taskClassNm
     * @param tempIds
     * @param files
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer updateResearchChklist(List<MuscResearchRslt> muscResearchRslts, String taskClassNm, List<String> tempIds, MultipartFile[] files) throws Exception {

        int muscSurveyResultCnt = 0;

        if (muscResearchRslts != null && muscResearchRslts.size() > 0) {
            // 근골격계의 상태를 계획완료 >> 조사결과제출중 변경
            muscSurveyResultCnt += muscResearchMapper.updateMuscResearchChklist(muscResearchRslts.get(0).getMuscResearchRsltNo());

            for (MuscResearchRslt muscResearchRslt : muscResearchRslts) {
                float total = 0;
                total = muscResearchRslt.getWorkCnt() * muscResearchRslt.getWorkTime();
                muscResearchRslt.setTotExposureTime(total);

                muscSurveyResultCnt += muscResearchMapper.updateResearchChklist(muscResearchRslt);
                attachFileService.uploadTableFiles(String.valueOf(muscResearchRslt.getMuscResearchRsltNo()), muscResearchRslt.getTempId(), muscResearchRslt.getUpdateUserId(), taskClassNm, tempIds, files);

            }
        }

        return muscSurveyResultCnt;

    }

    /**
     * 근골격계 조사결과 목록 조회
     *
     * @return
     * @throws Exception
     */
    public List<MuscResearchRslt> getResearchRslt(int muscResearchNo, String deptCd) throws Exception {
        List<MuscResearchRslt> muscResearchRslts = muscResearchMapper.getResearchRslt(muscResearchNo, deptCd);

        if (muscResearchRslts != null) {
            for (MuscResearchRslt muscResearchRslt : muscResearchRslts) {
                muscResearchRslt.setFiles(attachFileMapper.getUploadFiles("HEA_MUSC_RESEARCH_RSLT", String.valueOf(muscResearchRslt.getMuscResearchRsltNo())));

            }
        }
        return muscResearchRslts;
        // return muscResearchMapper.getResearchRslt(muscResearchNo);
    }

    /**
     * 유해인자 목록 조회
     *
     * @return
     * @throws Exception
     */
    public List<MuscHarmful> getHarmfuls(int muscResearchNo) throws Exception {
        return muscResearchMapper.getHarmfuls(muscResearchNo);
    }

    /**
     * 유해인자 상세 조회
     *
     * @param muscHarmfulNo
     * @return
     * @throws Exception
     */
    public MuscHarmful getHarmful(int muscHarmfulNo) throws Exception {
        MuscHarmful muscharmful = muscResearchMapper.getHarmful(muscHarmfulNo);

        if (muscharmful != null) {
            List<MuscHarmfulEval> muscHarmfulEvals = muscResearchMapper.getHarmfulSurvey(muscharmful.getMuscResearchUnitNo());
            for (MuscHarmfulEval muscHarmfulEval : muscHarmfulEvals) {
                muscHarmfulEval.setFiles(attachFileMapper.getUploadFiles("HEA_MUSC_HARMFUL_EVAL", String.valueOf(muscHarmfulEval.getMuscHarmfulEvalNo())));
            }
            muscharmful.setMuscHarmfulEvals(muscHarmfulEvals);
        }

        return muscharmful;
    }

    /**
     * 유해인자 수정
     *
     * @param muscHarmful
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer updateHarmful(MuscHarmful muscHarmful, String taskClassNm, List<String> tempIds, MultipartFile[] files) throws Exception {

        int resultNo = 0;
        if (muscHarmful != null) {

            resultNo += muscResearchMapper.updateHarmful(muscHarmful);

            if (muscHarmful.getMuscHarmfulEvals() != null && muscHarmful.getMuscHarmfulEvals().size() > 0) {
                for (MuscHarmfulEval muscHarmfulEval : muscHarmful.getMuscHarmfulEvals()) {
                    if (muscHarmfulEval.getMuscHarmfulEvalNo() > 0) {
                        int total = 0;
                        total = muscHarmfulEval.getWorkCnt() * muscHarmfulEval.getWorkload();
                        muscHarmfulEval.setTotalScore(total);
                        muscHarmfulEval.setMuscHarmfulNo(muscHarmful.getMuscHarmfulNo());
                        muscHarmfulEval.setUpdateUserId(muscHarmful.getUpdateUserId());
                        resultNo += muscResearchMapper.updateHarmfulSurvey(muscHarmfulEval);

                    } else {
                        int total = 0;
                        total = muscHarmfulEval.getWorkCnt() * muscHarmfulEval.getWorkload();
                        muscHarmfulEval.setTotalScore(total);
                        muscHarmfulEval.setMuscHarmfulNo(muscHarmful.getMuscHarmfulNo());
                        muscHarmfulEval.setUpdateUserId(muscHarmful.getUpdateUserId());
                        resultNo += muscResearchMapper.createHarmfulSurvey(muscHarmfulEval);

                    }
                    attachFileService.uploadTableFiles(String.valueOf(muscHarmfulEval.getMuscHarmfulEvalNo()), muscHarmfulEval.getTempId(), muscHarmfulEval.getUpdateUserId(), taskClassNm, tempIds, files);

                }
            }

            /**
             * 상태값 변경
             *
             * 조사결과제출완료 >> 유해요인조사중
             */
            int muscResearchNo = muscResearchMapper.getHarmfulResearchNo(muscHarmful.getMuscHarmfulNo());

            if (muscResearchNo > 0) {
                // 조회한 근골격계 조사의 상태가 조사결과제출완료인 경우에만 유해요인조사중으로 변경 처리한다.
                MuscResearch muscResearch = new MuscResearch();
                muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_HARMFUL_SUR_WRITE);
                muscResearch.setMuscResearchNo(muscResearchNo);
                muscResearch.setUpdateUserId(muscHarmful.getUpdateUserId());

                resultNo += muscResearchMapper.completeMuscResearch(muscResearch);
            }
        }

        return resultNo;

    }

    /**
     * 유해인자 삭제
     *
     * @param muscHarmfuls
     * @return
     * @throws Exception
     */

    public Integer deleteHarmful(List<MuscHarmful> muscHarmfuls) throws Exception {

        int resultNo = 0;
        if (muscHarmfuls != null && muscHarmfuls.size() > 0) {
            for (MuscHarmful muscHarmful : muscHarmfuls) {
                List<MuscHarmfulEval> muscHarmfulEvals = this.muscResearchMapper.getHarmfulSurvey(muscHarmful.getMuscResearchUnitNo());

                if (muscHarmfulEvals != null && muscHarmfulEvals.size() > 0) {
                    for (MuscHarmfulEval muscHarmfulEval : muscHarmfulEvals) {
                        // 직무스트레스 계획 항목 삭제
                        resultNo += this.muscResearchMapper.deleteHarmfulSurvey(muscHarmfulEval.getMuscHarmfulEvalNo());
                    }
                }
                // 직무스트레스 계획 삭제
                resultNo += this.muscResearchMapper.deleteHarmful(muscHarmful.getMuscHarmfulNo());
            }
        }
        // if (muscHarmfuls != null && muscHarmfuls.size() > 0) {
        // for (MuscHarmful muscHarmful : muscHarmfuls) {
        // List<MuscHarmfulEval> muscHarmfulEvals =
        // muscHarmful.getMuscHarmfulEvals();
        // if (muscHarmfulEvals != null) {
        //
        // for (MuscHarmfulEval muscHarmfulEval : muscHarmfulEvals) {
        // resultNo +=
        // this.muscResearchMapper.deleteHarmfulSurvey(muscHarmfulEval.getMuscHarmfulEvalNo());
        // }
        // }
        // resultNo +=
        // this.muscResearchMapper.deleteHarmful(muscHarmful.getMuscHarmfulNo());
        //
        // }
        // // for (MuscHarmful muscHarmful : muscHarmful) {
        // //
        // // resultNo +=
        // //
        // this.muscResearchMapper.deleteHarmful(muscHarmful.getMuscHarmfulNo());
        // //
        // // }
        // }
        return resultNo;
    }

    /**
     *
     * 유해요인 조사평가 조회
     *
     * @return
     * @throws Exception
     */
    // public List<MuscHarmfulEval> getHarmfulSurvey(int muscResearchUnitNo)
    // throws Exception {
    //
    // return muscResearchMapper.getHarmfulSurvey(muscResearchUnitNo);
    // }

    /**
     * 근골격계 결재 요청
     *
     * @param muscResearchNo
     * @param bizApprStepCd
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int appMuscAppr(int muscResearchNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String muscResearchStateCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            // 개선 사항 진행상태 변경
            imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_MUSC, muscResearchNo, null);
        }

        // 변경관리 결재 반영
        return muscResearchMapper.completeMuscImpr(muscResearchNo, apprRqstNo, muscResearchStateCd);
    }

    public List<MuscResearchDept> getMuscResearchDepts(int muscResearchNo) throws Exception {
        return muscResearchMapper.getMuscResearchDepts(muscResearchNo);
    }

    public File createExcelFile(List<Map<String, Object>> excelData, String title, String path) throws Exception {
        File resultFile = null;

        HSSFWorkbook writebook = new HSSFWorkbook();// 새 엑셀파일만들기
        createSheet(writebook, title + " 시트", excelData, title);

        path += File.separator + "excelDown";

        // 경로
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir(); // 폴더 생성합니다.
        }
        String uuid = UUID.randomUUID().toString();
        FileOutputStream output = new FileOutputStream(path + "\\" + uuid + ".xls");
        writebook.write(output);// 파일 생성
        output.close();
        resultFile = new File(path + "\\" + uuid + ".xls");

        return resultFile;
    }

    public void createSheet(HSSFWorkbook writebook, String sheetName, List<Map<String, Object>> list, String title) throws Exception {
        HSSFSheet sheet = writebook.createSheet(sheetName);// 새 시트 만들기//눈금선 없애기
        sheet.setDisplayGridlines(false);

        HSSFRow row;
        int rowIndex = 0;

        CellStyle titleStyle = ExcelUtil.getCellStyle(writebook, 1);
        CellStyle subTitleStyle = ExcelUtil.getCellStyle(writebook, 2);
        CellStyle headerStyle = ExcelUtil.getCellStyle(writebook, 10);
        CellStyle nomalAllStyle = ExcelUtil.getCellStyle(writebook, 4);

        /**
         * 제목 setting
         **/
        row = sheet.createRow(rowIndex);// 행 생성
        HSSFCell cell = row.createCell(0);// 해당 행의 1열
        cell.setCellValue(title);// 값넣기
        cell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19)); // 제목
        ++rowIndex; // 개행

        /**
         * 소제목 setting
         **/
        row = sheet.createRow(++rowIndex);// 행 생성
        cell = row.createCell(0);// 해당 행의 1열
        cell.setCellValue(title + " 목록");// 값넣기
        cell.setCellStyle(subTitleStyle);

        /**
         * 헤더 setting
         **/
        List<MuscResearchChklist> headerInfo = muscResearchMapper.getResearchChklists();

        String[] header1 = { "조사부서명", "단위작업명", "구분", "", "", "", "", "", "", "", "", "", "", "" };
        String[] header2 = { "", "", "노출시간", "", "", "", "", "", "", "", "", "", "", "" };
        String[] header3 = { "", "", "노출빈도", "", "", "", "", "", "", "", "", "", "", "" };
        String[] header4 = { "", "", "신체부위", "", "", "", "", "", "", "", "", "", "", "" };
        String[] header5 = { "", "", "작업자세 및 내용", "", "", "", "", "", "", "", "", "", "", "" };
        String[] header6 = { "", "", "무게", "", "", "", "", "", "", "", "", "", "", "" };
        if (headerInfo != null && headerInfo.size() > 0) {
            int index = 3;
            for (MuscResearchChklist muscResearchChklist : headerInfo) {
                header1[index] = muscResearchChklist.getChklistNm();
                header2[index] = String.valueOf(headerInfo.get(0).getExposureTime());
                header3[index] = muscResearchChklist.getExopsureCnt();
                header4[index] = muscResearchChklist.getBodyDesc();
                header5[index] = muscResearchChklist.getPositionDesc();
                header6[index] = muscResearchChklist.getWeightDesc();
                index++;
            }
        }

        createRow(sheet, rowIndex, headerStyle, header1);
        ++rowIndex;
        // 항 그림 넣기
        HSSFRow imageRow = sheet.createRow(rowIndex + 1);// 행 생성
        imageRow.setHeight((short) 1600);
        CellStyle style = headerStyle;
        for (int i = 0; i < 14; i++) {
            HSSFCell imageCell = imageRow.createCell(i);// 해당 행의 1열
            if (i != 0 && i != 1 && i != 2) {
                sheet.setColumnWidth(i, 1600);
                try {
                    InputStream inputStream = new URL(frontendUrl + "/src/assets/images/musculoskeletal_" + (i - 2) + ".jpg").openStream();
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    int pictureIdx = writebook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG);
                    inputStream.close();

                    HSSFCreationHelper helper = writebook.getCreationHelper();
                    HSSFPatriarch drawing = sheet.createDrawingPatriarch();
                    HSSFClientAnchor anchor = helper.createClientAnchor();

                    // 이미지를 출력할 CELL 위치 선정
                    anchor.setCol1(i);
                    anchor.setRow1(rowIndex + 1);
                    anchor.setCol2(i);
                    anchor.setRow2(rowIndex + 1);
                    anchor.setDx2(255 * 25);
                    anchor.setDy2(255 * 25);

                    // 이미지 그리기
                    drawing.createPicture(anchor, pictureIdx);

                } catch (Exception e) {
                    cell.setCellValue("파일을 읽을수 없습니다.");
                }
            }
            if (i == 0) {
                style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            } else if (i == 13) {
                style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            } else {
                style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            }
            imageCell.setCellStyle(style);
        }
        ++rowIndex;
        createRow(sheet, rowIndex, headerStyle, header2);
        ++rowIndex;
        createRow(sheet, rowIndex, headerStyle, header3);
        ++rowIndex;
        createRow(sheet, rowIndex, headerStyle, header4);
        ++rowIndex;
        createRow(sheet, rowIndex, headerStyle, header5);
        sheet.getRow(rowIndex).setHeight((short) 500);
        ++rowIndex;
        createRow(sheet, rowIndex, headerStyle, header6);

        sheet.addMergedRegion(new CellRangeAddress(3, 4, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(3, 9, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(3, 9, 1, 1));
        ++rowIndex;

        /**
         * data setting
         **/
        String[] column = { "dept_nm", "unit_work_nm", "work_nm", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                // 헤더 순서대로 값을 셋팅
                String[] dataValue = new String[14];
                for (int i = 0; i < 14; i++) {
                    String value = String.valueOf(map.get(column[i]));
                    dataValue[i] = value != null && !"null".equals(value.toLowerCase()) ? value : "";
                }

                createRow(sheet, rowIndex, nomalAllStyle, dataValue);
                ++rowIndex;
            }

        }
        HSSFRow colAutoWidthRow = writebook.getSheetAt(0).getRow(3);
        for (int i = 0; i < colAutoWidthRow.getLastCellNum(); i++) {
            // sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, Math.min(255 * 256, (sheet.getColumnWidth(i)) + 512 * 8));
            // if (i == 0 || i == 1 || i == 2) {
            // }
        }
    }

    public void createRow(HSSFSheet sheet, int rowIndex, CellStyle style, String[] values) throws Exception {
        if (values != null && values.length > 0) {
            HSSFRow row = sheet.createRow(++rowIndex);// 행 생성
            // if (height > 0) {
            // row.setHeight((short) height);
            // }
            for (int i = 0; i < values.length; i++) {
                HSSFCell cell = row.createCell(i);// 해당 행의 1열
                if (i < values.length) {
                    cell.setCellValue(values[i]);// 값넣기
                }
                if (i == 0) {
                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                } else if (i == values.length - 1) {
                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                } else {
                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                }
                cell.setCellStyle(style);
            }
        }
    }

    /** 여기에서 update 서비스 만들면 됨 **/
    /************************************************************************************************************/

    /**
     * 조사결과 저장
     *
     * @param muscSurveyChklist
     *            조사결과 정보
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public Integer updateMuscSurveyChklist(MuscSurveyChklist muscSurveyChklist) throws Exception {

        int updateChklistCnt = 0;
        updateChklistCnt = muscResearchMapper.updateMuscSurveyChklist(muscSurveyChklist);

        List<MuscSurveyRslt> muscSurveyRslts = muscSurveyChklist.getMuscSurveyRslts();

        int updateRsltCnt = 0;
        if (updateChklistCnt > 0) {
            for (int i = 0; i < muscSurveyRslts.size(); i++) {
                // 대상공정의 항목별 조사결과 저장
                updateRsltCnt = muscResearchMapper.updateMuslSurveyRslt(muscSurveyRslts.get(i));
            }
        }
        return updateRsltCnt;

    }

    /**
     * 문자열더하기(\r\n추가 후)
     *
     * @param source
     *            원문자열
     * @param append
     *            추가문자열
     * @return 반환문자
     */
    private String appendString(String source, String append) {
        // if (source!=null && !source.equals(""))
        // {
        // source += "<br/>■" + append;
        // }
        // else
        // {
        // source = "■" + append;
        // }
        source += "■" + append;

        return source;
    }

    public Integer collectAppr(int heaMuscSurveyNo, int apprRqstNo) {
        return muscResearchMapper.updateApprRqstNo(heaMuscSurveyNo, apprRqstNo);
    }

    public Map<String, Object> uploadExcelAssess(String taskClassNm, String taskKey, String uploadUserId, String muscResearchNo) throws Exception {

        List<AttachFile> uploadedPerson = attachFileMapper.getUploadFiles(taskClassNm, taskKey);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadResult", null);
        map.put("errorInfo", null);

        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;

        String failMsg = "";
        File excel = new File(uploadedPerson.get(0).getFilePath());
        ExcelReader reader = new ExcelReader();
        List<String[][]> sheets = reader.read(excel);
        if (sheets != null) {
            String sheetName = "단위작업 및 조사결과 업로드 양식";
            String[][] sheet = sheets.get(0);
            String[] sheetHeader = new String[] { "조사대상부서", "공정", "단위작업명", "조사일자", "작업인원", "조사담당자", "작업내용", "비고", "부담작업NO", "작업명", "작업시간", "작업횟수" };
            // 헤더 양식확인
            if (sheet.length > 0 && !reader.excelHeaderCheck(sheet[0], sheetHeader)) {
                map.put("message", "업로드양식이 오류: " + sheetName + " 시트의 헤더가 다릅니다." + "( " + String.join(", ", sheetHeader) + " )");
            } else {
                List<Map<String, Object>> uploadResults = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> errorInfos = new ArrayList<Map<String, Object>>();
                List<MuscResearchUnit> muscResearchUnitList = new ArrayList<>();
                List<MuscResearchRslt> muscResearchRsltList = new ArrayList<>();
                List<MuscResearchDept> muscResearchDeptList = new ArrayList<>();
                List<HashMap<String, String>> insertInfos = new ArrayList<HashMap<String, String>>();

                Map<String, Object> resultMap = new HashMap<String, Object>();
                StringBuilder rowCheck;

                for (int row = 1; row < sheet.length; row++) {
                    rowCheck = new StringBuilder();

                    for (int i = 0; i < sheet[row].length; i++) {
                        rowCheck.append(sheet[row][i].trim());
                    }

                    if (rowCheck.length() > 0) {
                        totalCount += 1;

                        String failMessage = "";
                        String deptCd = sheet[row][0].trim();
                        String processCd = sheet[row][1].trim();
                        String unitWorkNm = sheet[row][2].trim();
                        String researchDt = sheet[row][3].trim();
                        String workerCnt = sheet[row][4].trim();
                        String researchUserId = sheet[row][5].trim();
                        String processDesc = sheet[row][6].trim();
                        String remark = sheet[row][7].trim();
                        String muscResearchChklistNo = sheet[row][8].trim();
                        String workNm = sheet[row][9].trim();
                        String workTime = sheet[row][10].trim();
                        String workCnt = sheet[row][11].trim();

                        if (StringUtils.isNotBlank(deptCd) && StringUtils.isNotBlank(processCd) && StringUtils.isNotBlank(unitWorkNm) && StringUtils.isNotBlank(researchDt) && StringUtils.isNotBlank(workerCnt) && StringUtils.isNotBlank(researchUserId) && StringUtils.isNotBlank(muscResearchChklistNo) && StringUtils.isNotBlank(workNm)
                                && StringUtils.isNotBlank(workTime) && StringUtils.isNotBlank(workCnt)) {
                            try {
                                failMsg = "작업인원";
                                int cnt = Math.round(Float.parseFloat(sheet[row][4].trim()));
                                failMsg = "부담작업NO";
                                int cnt2 = Math.round(Float.parseFloat(sheet[row][8].trim()));
                                failMsg = "작업시간";
                                int cnt3 = Math.round(Float.parseFloat(sheet[row][10].trim()));
                                failMsg = "작업횟수";
                                int cnt4 = Math.round(Float.parseFloat(sheet[row][11].trim()));

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date formattedDate = dateFormat.parse(researchDt);

                                workerCnt = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][4].trim())));
                                muscResearchChklistNo = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][8].trim())));
                                workTime = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][10].trim())));
                                workCnt = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][11].trim())));

                                List<MuscResearchDept> muscResearchDepts = muscResearchMapper.getMuscResearchDepts(Integer.parseInt(muscResearchNo));

                                MuscResearchDept matchingDept = muscResearchDepts.stream().filter((item) -> item.getDeptCd().equals(deptCd)).findFirst().orElse(null);

                                if (matchingDept != null) {

                                    if (deptProcessMapper.getDeptProcessesMatching(deptCd, processCd).size() == 0) {
                                        failMessage += "■ 공정 코드가 유효하지 않습니다.";
                                        failCount++;
                                        Map<String, Object> errorInfo = new HashMap<String, Object>();
                                        errorInfo.put("classNm", sheetName);
                                        errorInfo.put("failRow", row + 1);
                                        errorInfo.put("failMessage", failMessage);
                                        errorInfo.put("remark", processCd);
                                        errorInfos.add(errorInfo);
                                        continue;
                                    } else if (userMapper.getUser(researchUserId) == null) {
                                        failMessage += "■ 유저아이디가 유효하지 않습니다.";
                                        failCount++;
                                        Map<String, Object> errorInfo = new HashMap<String, Object>();
                                        errorInfo.put("classNm", sheetName);
                                        errorInfo.put("failRow", row + 1);
                                        errorInfo.put("failMessage", failMessage);
                                        errorInfo.put("remark", researchUserId);
                                        errorInfos.add(errorInfo);
                                        continue;
                                    }

                                    MuscResearchUnit muscResearchUnit = new MuscResearchUnit();
                                    muscResearchUnit.setMuscResearchNo(Integer.parseInt(muscResearchNo));
                                    muscResearchUnit.setMuscResearchDeptNo(matchingDept.getMuscResearchDeptNo());
                                    muscResearchUnit.setProcessCd(processCd);
                                    muscResearchUnit.setProcessDesc(processDesc);
                                    muscResearchUnit.setUnitWorkNm(unitWorkNm);
                                    muscResearchUnit.setResearchDt(researchDt);
                                    muscResearchUnit.setWorkerCnt(workerCnt);
                                    muscResearchUnit.setDeptCd(deptCd);
                                    muscResearchUnit.setResearchUserId(researchUserId);
                                    muscResearchUnit.setRemark(remark);
                                    muscResearchUnit.setCreateUserId(uploadUserId);

                                    if (muscResearchMapper.getCheckUnitWorkCnt(0, Integer.parseInt(muscResearchNo), deptCd, unitWorkNm) == 0) {
                                        muscResearchUnitList.add(muscResearchUnit);
                                        HashMap<String, String> infoMap = new HashMap<>();
                                        infoMap.put("deptCd", deptCd);
                                        infoMap.put("unitWorkNm", unitWorkNm);
                                        insertInfos.add(infoMap);
                                        // muscResearchMapper.createUnitWork(muscResearchUnit);
                                    } else {
                                        int muscResearchUnitNo = muscResearchMapper.getUnitWorkNo(Integer.parseInt(muscResearchNo), deptCd, unitWorkNm);
                                        muscResearchUnit.setMuscResearchUnitNo(muscResearchUnitNo);
                                    }

                                    muscResearchDeptList.add(matchingDept);

                                    List<MuscResearchChklist> researchChklists = muscResearchMapper.getResearchChklists();
                                    MuscResearchChklist matchingChk = null;
                                    for (MuscResearchChklist chk : researchChklists) {
                                        String chklistNm = chk.getChklistNm();
                                        String substring = chklistNm.substring(0, chklistNm.length() - 1);
                                        if (substring.equals(muscResearchChklistNo)) {
                                            matchingChk = chk;
                                            break;
                                        }
                                    }

                                    float totalExposureTime = Integer.parseInt(workTime) * Integer.parseInt(workCnt);
                                    String finalValue = "";
                                    if (matchingChk != null && totalExposureTime > matchingChk.getTotExposureTime()) {
                                        finalValue = "O";
                                    } else
                                        finalValue = "X";

                                    MuscResearchRslt muscResearchRslt = new MuscResearchRslt();
                                    muscResearchRslt.setMuscResearchUnitNo(muscResearchUnit.getMuscResearchUnitNo());
                                    muscResearchRslt.setMuscResearchChklistNo(Integer.parseInt(muscResearchChklistNo));
                                    muscResearchRslt.setWorkNm(workNm);
                                    muscResearchRslt.setWorkTime(Float.parseFloat(workTime));
                                    muscResearchRslt.setWorkCnt(Integer.parseInt(workCnt));
                                    muscResearchRslt.setTotExposureTime(totalExposureTime);
                                    muscResearchRslt.setFinalValue(finalValue);
                                    muscResearchRslt.setCreateUserId(uploadUserId);
                                    muscResearchRslt.setUpdateUserId(uploadUserId);
                                    muscResearchRslt.setUnitWorkNm(unitWorkNm);
                                    muscResearchRslt.setProcessCd(processCd);
                                    muscResearchRslt.setDeptCd(deptCd);

                                    String finalMuscResearchChklistNo = muscResearchChklistNo;

                                    List<MuscResearchRslt> collect = muscResearchRsltList.stream().filter((item) -> {
                                        return (item.getDeptCd().equals(deptCd)) && (item.getProcessCd().equals(processCd)) && (item.getUnitWorkNm().equals(unitWorkNm)) && (item.getMuscResearchChklistNo() == Integer.parseInt(finalMuscResearchChklistNo));
                                    }).collect(Collectors.toList());

                                    if (collect.size() == 0) {
                                        muscResearchRsltList.add(muscResearchRslt);
                                    } else {
                                        failMessage += "■ 부담작업No가 중복입니다.";
                                        failCount++;
                                        Map<String, Object> errorInfo = new HashMap<String, Object>();
                                        errorInfo.put("classNm", sheetName);
                                        errorInfo.put("failRow", row + 1);
                                        errorInfo.put("failMessage", failMessage);
                                        errorInfo.put("remark", muscResearchChklistNo);
                                        errorInfos.add(errorInfo);
                                        continue;
                                    }
                                    // int createSuccessCnt =
                                    // muscResearchMapper.createResearchUnitRslt(muscResearchRslt);
                                    successCount++;
                                } else {
                                    failMessage += "■ 조사대상부서 코드가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", deptCd);
                                    errorInfos.add(errorInfo);
                                    continue;
                                }
                            } catch (NumberFormatException e) {
                                // 숫자 입력이 아닐시
                                failMessage += "■ " + failMsg + "는 숫자로 입력해야합니다.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row + 1);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");
                                errorInfos.add(errorInfo);
                            } catch (ParseException ex) {
                                // date 타입이 아닐시
                                failMessage += "■ 평가일자형식(yyyy-mm-dd)이 유효하지 않습니다.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row + 1);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");
                                errorInfos.add(errorInfo);
                            }
                        } else {
                            // 입력이 비어있을때
                            failMsg = "조사대상부서";
                            if (processCd.equals("")) {
                                failMsg = "공정";
                            } else if (unitWorkNm.equals("")) {
                                failMsg = "단위작업명";
                            } else if (researchDt.equals("")) {
                                failMsg = "조사일자";
                            } else if (workerCnt.equals("")) {
                                failMsg = "작업인원";
                            } else if (researchUserId.equals("")) {
                                failMsg = "조사담당자";
                            } else if (muscResearchChklistNo.equals("")) {
                                failMsg = "부담작업NO";
                            } else if (workNm.equals("")) {
                                failMsg = "작업명";
                            } else if (workTime.equals("")) {
                                failMsg = "작업시간";
                            } else if (workCnt.equals("")) {
                                failMsg = "작업횟수";
                            }
                            failMessage += "■ 필수값을 입력하세요.";
                            failCount++;
                            Map<String, Object> errorInfo = new HashMap<String, Object>();
                            errorInfo.put("classNm", sheetName);
                            errorInfo.put("failRow", row + 1);
                            errorInfo.put("failMessage", failMessage);
                            errorInfo.put("remark", failMsg);
                            errorInfos.add(errorInfo);
                        }
                    } else {
                        continue;
                    }
                }
                resultMap.put("classNm", sheetName);
                resultMap.put("totalCount", totalCount);
                resultMap.put("successCount", successCount);
                resultMap.put("failCount", failCount);

                // 업로드결과
                uploadResults.add(resultMap);

                map.put("success", true);
                map.put("uploadResult", uploadResults);
                map.put("errorInfo", errorInfos);
                if (errorInfos.size() == 0) {

                    try {
                        muscCreateService.execDbWork(uploadUserId, muscResearchNo, muscResearchUnitList, muscResearchRsltList, muscResearchDeptList, insertInfos);
                    } catch (Exception e) {
                        int value = Integer.valueOf(e.getMessage());

                        String failMessage = "■ 행 데이터 삽입중 오류발생.";
                        Map<String, Object> errorInfo = new HashMap<String, Object>();
                        errorInfo.put("classNm", sheetName);
                        errorInfo.put("failRow", value + 2);
                        errorInfo.put("failMessage", failMessage);
                        errorInfo.put("remark", String.valueOf(value + 2) + "번째 행");
                        errorInfos.add(errorInfo);
                        failCount++;
                        successCount--;
                        resultMap.remove("successCount");
                        resultMap.remove("failCount");
                        resultMap.put("successCount", successCount);
                        resultMap.put("failCount", failCount);
                    }
                }
            }
        } else {
            map.put("message", "업로드 오류: 파일을 읽을 수 없습니다.");
        }
        return map;
    }

    public Map<String, Object> uploadExcelHarmful(String taskClassNm, String taskKey, String uploadUserId, String muscResearchNo, DefaultParam defaultParam) throws Exception {

        List<AttachFile> uploadedPerson = attachFileMapper.getUploadFiles(taskClassNm, taskKey);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadResult", null);
        map.put("errorInfo", null);

        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;

        String failMsg = "";
        File excel = new File(uploadedPerson.get(0).getFilePath());
        ExcelReader reader = new ExcelReader();
        List<String[][]> sheets = reader.read(excel);
        if (sheets != null) {
            String sheetName = "유해요인 조사 업로드 양식";
            String[][] sheet = sheets.get(0);
            String[] sheetHeader = new String[] { "유해요인조사번호$", "단위작업번호$", "단위작업$", "조사대상부서코드$", "조사자ID$", "작업내용$", "조사구분코드*", "조사일시*", "작업설비 변화유무", "작업설비 내용요약", "작업량 변화유무", "작업량 내용요약", "작업속도 변화유무", "작업속도 내용요약", "업무형태 변화유무", "업무형태 내용요약", "기타 변화유무", "기타 내용요약", "작업명$", "부담작업No$", "작업부하*", "작업빈도*", "유해요인코드", "발생원인", "비고" };
            // 헤더 양식확인
            if (sheet.length > 0 && !reader.excelHeaderCheck(sheet[0], sheetHeader)) {
                map.put("message", "업로드양식 오류: " + sheetName + " 시트의 헤더가 다릅니다." + "( " + String.join(", ", sheetHeader) + " )");
            } else {
                List<Map<String, Object>> uploadResults = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> errorInfos = new ArrayList<Map<String, Object>>();
                List<MuscHarmful> muscResearchHarmfulList = new ArrayList<>();
                List<MuscHarmfulEval> muscResearchEvalList = new ArrayList<>();

                Map<String, Object> resultMap = new HashMap<String, Object>();
                StringBuilder rowCheck;

                for (int row = 1; row < sheet.length - 3; row++) {
                    rowCheck = new StringBuilder();

                    for (int i = 0; i < sheet[row].length; i++) {
                        rowCheck.append(sheet[row][i].trim());
                    }

                    if (rowCheck.length() > 0) {
                        totalCount += 1;
                        String failMessage = "";

                        String muscHarmfulNo = sheet[row][0].trim();
                        String muscResearchUnitNo = sheet[row][1].trim();
                        String unitWorkNm = sheet[row][2].trim();
                        String deptCd = sheet[row][3].trim();
                        String surveryUserId = sheet[row][4].trim();
                        String processDesc = sheet[row][5].trim();
                        String surveryType = sheet[row][6].trim();
                        String surveryDt = sheet[row][7].trim();
                        String changeYnEquip = sheet[row][8].trim();
                        String summaryEquip = sheet[row][9].trim();
                        String changeYnWorkload = sheet[row][10].trim();
                        String summaryWorkload = sheet[row][11].trim();
                        String changeYnSpeed = sheet[row][12].trim();
                        String summarySpeed = sheet[row][13].trim();
                        String changeYnBusiness = sheet[row][14].trim();
                        String summaryBusiness = sheet[row][15].trim();
                        String changeYnEtc = sheet[row][16].trim();
                        String summaryEtc = sheet[row][17].trim();
                        String workNm = sheet[row][18].trim();
                        String muscResearchChklistNo = sheet[row][19].trim();
                        String workload = sheet[row][20].trim();
                        String workCnt = sheet[row][21].trim();
                        String harmfulCode = sheet[row][22].trim();
                        String causes = sheet[row][23].trim();
                        String remark = sheet[row][24].trim();

                        if (StringUtils.isNotBlank(surveryType) && StringUtils.isNotBlank(surveryDt) && StringUtils.isNotBlank(workload) && StringUtils.isNotBlank(workCnt)) {
                            try {
                                failMsg = "작업부하";
                                int cnt = Math.round(Float.parseFloat(sheet[row][20].trim()));
                                failMsg = "작업빈도";
                                int cnt2 = Math.round(Float.parseFloat(sheet[row][21].trim()));

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date formattedDate = dateFormat.parse(surveryDt);

                                muscHarmfulNo = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][0].trim())));
                                muscResearchUnitNo = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][1].trim())));
                                muscResearchChklistNo = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][19].trim())));
                                workload = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][20].trim())));
                                workCnt = String.valueOf((int) Math.floor(Float.valueOf(sheet[row][21].trim())));

                                List<CodeMaster> heaHarmfulFactors = codeMasterMapper.getSelect("HEA_HARMFUL_FACTORS", null, defaultParam);

                                surveryType = StringUtils.upperCase(surveryType);
                                changeYnEquip = StringUtils.upperCase(changeYnEquip);
                                changeYnWorkload = StringUtils.upperCase(changeYnWorkload);
                                changeYnSpeed = StringUtils.upperCase(changeYnSpeed);
                                changeYnBusiness = StringUtils.upperCase(changeYnBusiness);
                                changeYnEtc = StringUtils.upperCase(changeYnEtc);

                                if (!(surveryType.equals("I") || surveryType.equals("R"))) {
                                    failMessage += "■ 조사구분코드(I/R)가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", surveryType);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (StringUtils.isNotBlank(changeYnEquip) && !(changeYnEquip.equals("Y") || changeYnEquip.equals("N"))) {
                                    failMessage += "■ 작업설비 변화유무(Y/N)가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", changeYnEquip);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (StringUtils.isNotBlank(changeYnWorkload) && !(changeYnWorkload.equals("Y") || changeYnWorkload.equals("N"))) {
                                    failMessage += "■ 작업량 변화유무(Y/N)가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", changeYnWorkload);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (StringUtils.isNotBlank(changeYnSpeed) && !(changeYnSpeed.equals("Y") || changeYnSpeed.equals("N"))) {
                                    failMessage += "■ 작업속도 변화유무(Y/N)가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", changeYnSpeed);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (StringUtils.isNotBlank(changeYnBusiness) && !(changeYnBusiness.equals("Y") || changeYnBusiness.equals("N"))) {
                                    failMessage += "■ 업무형태 변화유무(Y/N)가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", changeYnBusiness);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (StringUtils.isNotBlank(changeYnEtc) && !(changeYnEtc.equals("Y") || changeYnEtc.equals("N"))) {
                                    failMessage += "■ 기타 변화유무(Y/N)가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", changeYnEtc);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (Integer.valueOf(workload) <= 0 || Integer.valueOf(workload) > 6) {
                                    failMessage += "■ 작업부하는 0에서 5사이의 숫자를 입력해주세요.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", workload);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (Integer.valueOf(workCnt) <= 0 || Integer.valueOf(workCnt) > 6) {
                                    failMessage += "■ 작업빈도는 0에서 5사이의 숫자를 입력해주세요.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", workCnt);
                                    errorInfos.add(errorInfo);
                                    continue;
                                } else if (StringUtils.isNotBlank(harmfulCode) && heaHarmfulFactors.stream().noneMatch((item) -> item.getCode().equals(harmfulCode))) {

                                    failMessage += "■ 유해요인코드가 유효하지 않습니다.";
                                    failCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", sheetName);
                                    errorInfo.put("failRow", row + 1);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", harmfulCode);
                                    errorInfos.add(errorInfo);
                                    continue;
                                }

                                MuscHarmful harmful = muscResearchMapper.getHarmful(Integer.parseInt(muscHarmfulNo));
                                harmful.setUpdateUserId(uploadUserId);
                                harmful.setSurveryType(surveryType);
                                harmful.setSurveryDt(surveryDt);
                                harmful.setChangeYnEquip(changeYnEquip);
                                harmful.setSummaryEquip(summaryEquip);
                                harmful.setChangeYnWorkload(changeYnWorkload);
                                harmful.setSummaryWorkload(summaryWorkload);
                                harmful.setChangeYnSpeed(changeYnSpeed);
                                harmful.setSummarySpeed(summarySpeed);
                                harmful.setChangeYnBusiness(changeYnBusiness);
                                harmful.setSummaryBusiness(summaryBusiness);
                                harmful.setChangeYnEtc(changeYnEtc);
                                harmful.setSummaryEtc(summaryEtc);

                                muscResearchHarmfulList.add(harmful);

                                MuscHarmfulEval muscHarmfulEval = new MuscHarmfulEval();
                                muscHarmfulEval.setMuscHarmfulNo(Integer.parseInt(muscHarmfulNo));
                                muscHarmfulEval.setMuscResearchChklistNo(Integer.parseInt(muscResearchChklistNo));
                                muscHarmfulEval.setWorkload(Integer.parseInt(workload));
                                muscHarmfulEval.setWorkCnt(Integer.parseInt(workCnt));
                                muscHarmfulEval.setTotalScore(Integer.parseInt(workload) * Integer.parseInt(workCnt));
                                muscHarmfulEval.setCauses(causes);
                                muscHarmfulEval.setRemark(remark);
                                muscHarmfulEval.setHarmfulCode(harmfulCode);
                                muscHarmfulEval.setCreateUserId(uploadUserId);

                                muscResearchEvalList.add(muscHarmfulEval);

                            } catch (NumberFormatException e) {
                                // 숫자 입력이 아닐시
                                failMessage += "■ " + failMsg + "는 숫자로 입력해야합니다.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row + 1);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");
                                errorInfos.add(errorInfo);
                            } catch (ParseException ex) {
                                // date 타입이 아닐시
                                failMessage += "■ 조사일시 날짜형식(yyyy-mm-dd)이 유효하지 않습니다.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row + 1);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");
                                errorInfos.add(errorInfo);
                            }
                        } else {
                            // 입력이 비어있을때
                            failMsg = "조사구분코드";
                            if (surveryDt.equals("")) {
                                failMsg = "조사일시";
                            } else if (workload.equals("")) {
                                failMsg = "작업부하";
                            } else if (workCnt.equals("")) {
                                failMsg = "작업빈도";
                            }
                            failMessage += "■ 필수값을 입력하세요.";
                            failCount++;
                            Map<String, Object> errorInfo = new HashMap<String, Object>();
                            errorInfo.put("classNm", sheetName);
                            errorInfo.put("failRow", row + 1);
                            errorInfo.put("failMessage", failMessage);
                            errorInfo.put("remark", failMsg);
                            errorInfos.add(errorInfo);
                        }
                    } else {
                        continue;
                    }
                }
                resultMap.put("classNm", sheetName);
                resultMap.put("totalCount", totalCount);
                resultMap.put("successCount", successCount);
                resultMap.put("failCount", failCount);

                // 업로드결과
                uploadResults.add(resultMap);

                map.put("success", true);
                map.put("uploadResult", uploadResults);
                map.put("errorInfo", errorInfos);
                if (errorInfos.size() == 0) {

                    try {
                        muscCreateService.execHarmfulDbWork(uploadUserId, muscResearchNo, muscResearchHarmfulList, muscResearchEvalList);
                    } catch (Exception e) {
                        int value = Integer.valueOf(e.getMessage());

                        String failMessage = "■ 행 데이터 삽입중 오류발생.";
                        Map<String, Object> errorInfo = new HashMap<String, Object>();
                        errorInfo.put("classNm", sheetName);
                        errorInfo.put("failRow", value + 2);
                        errorInfo.put("failMessage", failMessage);
                        errorInfo.put("remark", String.valueOf(value + 2) + "번째 행");
                        errorInfos.add(errorInfo);
                        failCount++;
                        successCount--;
                        resultMap.remove("successCount");
                        resultMap.remove("failCount");
                        resultMap.put("successCount", successCount);
                        resultMap.put("failCount", failCount);
                    }
                }
            }
        } else {
            map.put("message", "업로드 오류: 파일을 읽을 수 없습니다.");
        }
        return map;
    }

    public File createExcelAssessSupply(List<Integer> muscHarmfulNoList) throws Exception {

        XSSFWorkbook objWorkBook = new XSSFWorkbook();
        XSSFSheet objSheet = objWorkBook.createSheet("유해요인 업로드 양식");

        XSSFRow objRow = null;
        XSSFCell objCell = null;

        int rowIndex1 = 0;

        // 헤더부분
        objRow = objSheet.createRow(rowIndex1++);

        CellStyle titleStyle = this.getCellStyle(objWorkBook, 1);

        if (objRow.getRowNum() == 0) {
            titleStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        }

        this.createCellAndSetHeaderRow(objRow, "유해요인조사번호$/단위작업번호$/단위작업$/조사대상부서코드$/조사자ID$/작업내용$/조사구분코드*/조사일시*/작업설비 변화유무/작업설비 내용요약/작업량 변화유무/작업량 내용요약/작업속도 변화유무/작업속도 내용요약/업무형태 변화유무/업무형태 내용요약/기타 변화유무/기타 내용요약/작업명$/부담작업No$/작업부하*/작업빈도*/유해요인코드/발생원인/비고".split("/"));
        // 데이터부분
        List<LinkedHashMap<String, Object>> HarmfulList = this.muscResearchMapper.getHarmfulExcel(muscHarmfulNoList);
        for (LinkedHashMap<String, Object> item : HarmfulList) {
            List<Object> list = new ArrayList<>(item.values());
            objRow = objSheet.createRow(rowIndex1++);
            this.createCellAndSetDataRow(objRow, list, 0, 1, 19, 20, 21);
        }
        // 셀 너비
        XSSFRow colAutoWidthRow = objWorkBook.getSheetAt(0).getRow(1);
        for (int i = colAutoWidthRow.getFirstCellNum(); i < colAutoWidthRow.getLastCellNum(); i++) {
            objSheet.setColumnWidth(i, objSheet.getColumnWidth(i) + 512 * 5);
        }
        objRow = objSheet.createRow(rowIndex1++);
        objCell = objRow.createCell(0);
        objCell.setCellValue("*주의사항*");
        objCell.setCellStyle(titleStyle);
        objSheet.addMergedRegion(new CellRangeAddress(HarmfulList.size() + 1, HarmfulList.size() + 1, 0, 24)); // 제목

        objRow = objSheet.createRow(rowIndex1++);
        objCell = objRow.createCell(0);
        objCell.setCellValue("필수입력행(*),    수정금지($),    조사구분코드: I/R = 수시/정기,    변화유무코드: Y/N,    날짜형식: yyyy-mm-dd,    작업부하/작업빈도는 1이상 5이하의 숫자입력 \n\n\n");
        objCell.setCellStyle(titleStyle);
        objSheet.addMergedRegion(new CellRangeAddress(HarmfulList.size() + 2, HarmfulList.size() + 3, 0, 24)); // 제목

        String path = FileUtil.getStoreFilePath() + File.separator + "assessactionexcel" + com.she.security.util.DateUtil.currentDateYYYYMMDD();// 폴더
        // 경로
        File folder = new File(path);
        folder.setExecutable(false, true);
        folder.setReadable(true);
        folder.setWritable(false, true);
        if (!folder.exists()) {
            folder.mkdir(); // 폴더 생성합니다.
        }

        String uuid = UUID.randomUUID().toString();
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(path + File.separator + uuid + ".xlsx");
            objWorkBook.write(output);// 파일 생성

            // this.readOfXlsx(path + File.separator + uuid + ".xlsx");

            output.close();
            return new File(path + File.separator + uuid + ".xlsx");
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            output.close();
        }
        return null;
    }

    public CellStyle getCellStyle(XSSFWorkbook writebook, int type) throws Exception {

        // 셀 스타일 및 폰트 설정
        CellStyle style = writebook.createCellStyle();

        if (type == 1) { // 제목 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (15 * 20)); // 사이즈
            font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드
            // (굵게)
            style.setFont(font);
        }
        if (type == 2) { // 헤더 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 배경색
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        }
        return style;
    }

    /**
     *
     * @param row
     *            셀을 넣어줄 row
     * @param cellValueList
     *            셀에 넣어줄 값(String) 배열
     */
    private void createCellAndSetDataRow(XSSFRow row, List<Object> cellValueList) {
        String[] stringCellValue = new String[cellValueList.size()];
        int i = 0;
        for (Object o : cellValueList) {
            stringCellValue[i++] = (String) o;
        }
        createCellAndSetHeaderRow(row, stringCellValue);
    }

    /**
     *
     * @param row
     *            셀을 넣어줄 row
     * @param cellValueList
     *            셀에 넣어줄 값(Object) 배열
     * @param num
     *            숫자 타입으로 바꿔줄 index 번호(복수개가능)
     */
    private void createCellAndSetDataRow(XSSFRow row, List<Object> cellValueList, int... num) {
        for (int i = 0; i < cellValueList.size(); i++) {
            if (isContainsNumber(i, num)) {
                if (StringUtils.isEmpty(String.valueOf(cellValueList.get(i)))) {
                    row.createCell(i).setCellValue("");
                } else
                    row.createCell(i).setCellValue(Double.parseDouble(String.valueOf(cellValueList.get(i))));
            } else {
                row.createCell(i).setCellValue((String) cellValueList.get(i));
            }
        }
    }

    /**
     *
     * @param row
     *            셀을 넣어줄 row
     * @param cellValueList
     *            셀에 넣어줄 값(String) 배열
     */
    private void createCellAndSetHeaderRow(XSSFRow row, String... cellValueList) {
        for (int i = 0; i < cellValueList.length; i++) {
            row.createCell(i).setCellValue((String) cellValueList[i]);
        }
    }

    private boolean isContainsNumber(int value, int... compareValue) {
        for (int i = 0; i < compareValue.length; i++) {
            if (value == compareValue[i]) {
                return true;
            }
        }
        return false;
    }

}
