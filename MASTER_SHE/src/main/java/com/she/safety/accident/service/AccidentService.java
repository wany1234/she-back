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
package com.she.safety.accident.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.baseInfo.model.FacilityMst;
import com.she.chm.model.Chemprod;
import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.config.GlobalSettings;
import com.she.impr.service.ImprService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.safety.accident.mapper.AccidentMapper;
import com.she.safety.model.Accident;
import com.she.safety.model.AccidentCauMeas;
import com.she.safety.model.AccidentChemprod;
import com.she.safety.model.AccidentFacility;
import com.she.safety.model.AccidentInvest;
import com.she.safety.model.AccidentInvestPsn;
import com.she.safety.model.AccidentKind;
import com.she.safety.model.AccidentOcctype;
import com.she.safety.model.AccidentRefPsn;
import com.she.safety.model.AccidentVictim;
import com.she.safety.model.AccidentVictimHumanInjuryCls;
import com.she.safety.model.AccidentVictimHumanInjuryPart;
import com.she.utils.ConstVal;
import com.she.utils.FileUtil;
import com.she.utils.Methods;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * 사사내고 기능정의
 *
 */
@Service
public class AccidentService {
    @Autowired
    private AccidentMapper accidentMapper;

    @Autowired
    private ImprService imprService;

    @Autowired
    private GlobalSettings globalSettings;

    // TODO : 파일 업로드 정보 처리용 서비스
    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 사내사고 속보/등록 조회
     *
     * @param accidentStepCd
     *            사내사고진행단계
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            발생부서
     * @param area
     *            장소
     * @param accidentTitle
     *            사고명
     * @param plantCd
     *            사업장명
     * @return 사내사고 목록
     * @throws Exception
     */
    public List<Accident> getAccidentOccurs(String accidentStepCd, String startDate, String endDate, String deptCd, String deptSubYn, String area, String accidentTitle, String plantCd, String bizApprStepNm, DefaultParam defaultParam) throws Exception {
        return accidentMapper.getAccidentOccurs(accidentStepCd, startDate, endDate, deptCd, deptSubYn, area, accidentTitle, plantCd, bizApprStepNm, defaultParam);
    }

    /**
     * 사내사고 접수 조회
     *
     * @param accidentStepCd
     *            사내사고진행단계
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            발생부서
     * @param area
     *            장소
     * @param accidentTitle
     *            사고명
     * @param plantCd
     *            사업장명
     * @return 사내사고 목록
     * @throws Exception
     */
    public List<Accident> getAccidentReceptions(String accidentStepCd, String startDate, String endDate, String deptCd, String deptSubYn, String area, String accidentTitle, String plantCd, String bizApprStepNm, DefaultParam defaultParam) throws Exception {
        return accidentMapper.getAccidentReceptions(accidentStepCd, startDate, endDate, deptCd, deptSubYn, area, accidentTitle, plantCd, bizApprStepNm, defaultParam);
    }

    /**
     * 사내사고 조사결과 조회
     *
     * @param accidentStepCd
     *            사내사고진행단계
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            발생부서
     * @param area
     *            장소
     * @param accidentTitle
     *            사고명
     * @param plantCd
     *            사업장명
     * @return 사내사고 목록
     * @throws Exception
     */
    public List<Accident> getAccidentResults(String accidentStepCd, String startDate, String endDate, String deptCd, String deptSubYn, String area, String accidentTitle, String plantCd, String accidentType, String occKindCd, String occAttCd, String immCauseCd, String indCauseCd, String assessYear, int monFlag, String bizApprStepNm,
            DefaultParam defaultParam) throws Exception {
        return accidentMapper.getAccidentResults(accidentStepCd, startDate, endDate, deptCd, deptSubYn, area, accidentTitle, plantCd, accidentType, occKindCd, occAttCd, immCauseCd, indCauseCd, assessYear, monFlag, bizApprStepNm, defaultParam);
    }

    /**
     * 사내사고 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고
     * @throws Exception
     */
    public Accident getAccident(int safAccidentNo, String searchFlag, DefaultParam defaultParam) throws Exception {
        Accident accident = new Accident();
        if (searchFlag.equals("occur")) {
            // 사고 상세 정보 (등록)
            accident = accidentMapper.getAccidentOccur(safAccidentNo, defaultParam);
        } else if (searchFlag.equals("reception")) {
            // 사고 상세 정보 (접수)
            accident = accidentMapper.getAccidentReception(safAccidentNo, defaultParam);
        } else if (searchFlag.equals("result")) {
            // 사고 상세 정보 (조사결과)
            accident = accidentMapper.getAccidentResult(safAccidentNo, defaultParam);
        }
        // 사고구분
        accident.setAccKindCds(accidentMapper.getAccidentKinds(safAccidentNo, defaultParam));
        // 사고종류
        accident.setAccidentTypes(accidentMapper.getAccidentOcctypes(safAccidentNo, defaultParam));
        // 조사계획 정보
        AccidentInvest accidentInvest = accidentMapper.getAccidentInvest(safAccidentNo);
        if (accidentInvest != null) {
            accident.setAccidentInvest(accidentInvest);
        }
        List<AccidentInvestPsn> accidentInvestPsns = accidentMapper.getAccidentInvestPsns(safAccidentNo);
        if (accidentInvestPsns != null && accidentInvestPsns.size() > 0) {
            accident.setAccidentInvestPsns(accidentInvestPsns);
        }
        // 사고자 정보 조회
        List<AccidentVictim> accidentVictims = this.getAccidentVictims(safAccidentNo, "", defaultParam);
        if (accidentVictims != null && accidentVictims.size() > 0) {
            accident.setAccidentVictims(accidentVictims);
        }
        return accident;
    }

    /**
     * 사내사고 등록
     *
     * @param accident
     *            사내사고
     * @return 등록 행 수
     */
    @Transactional
    public HashMap<String, Object> createAccident(Accident accident) throws Exception {
        int resultNo = 0;
        // 사내사고 등록
        resultNo += accidentMapper.createAccident(accident);
        // 사내사고종류 등록
        if (accident.getAccidentTypes() != null && accident.getAccidentTypes().size() > 0) {
            for (AccidentOcctype accidentOcctype : accident.getAccidentTypes()) {
                accidentOcctype.setSafAccidentNo(accident.getSafAccidentNo());
                resultNo += accidentMapper.createAccidentOccurtype(accidentOcctype);
            }
        }
        // 사내사고구분 등록
        if (accident.getAccKindCds() != null && accident.getAccKindCds().size() > 0) {
            for (AccidentKind accidentKind : accident.getAccKindCds()) {
                accidentKind.setSafAccidentNo(accident.getSafAccidentNo());
                resultNo += accidentMapper.createAccidentKind(accidentKind);
            }
        }

        /*******************************************
         * 사고자 start
         ***************************************/
        if (accident.getAccidentVictims() != null) {
            if (accident.getAccidentVictims().size() > 0) {
                for (AccidentVictim accidentVictim : accident.getAccidentVictims()) {
                    // 사고자 등록
                    accidentVictim.setSafAccidentNo(accident.getSafAccidentNo());
                    resultNo += accidentMapper.createAccidentVictim(accidentVictim);

                    if (accidentVictim.getHumanInjuryPartCds() != null && accidentVictim.getHumanInjuryPartCds().length > 0) {
                        for (String humanInjuryPartCd : accidentVictim.getHumanInjuryPartCds()) {
                            AccidentVictimHumanInjuryPart accidentVictimHumanInjuryPart = new AccidentVictimHumanInjuryPart();
                            accidentVictimHumanInjuryPart.setHumanInjuryPartCd(humanInjuryPartCd);
                            accidentVictimHumanInjuryPart.setSafAccidentVictimNo(accidentVictim.getSafAccidentVictimNo());
                            resultNo += accidentMapper.createAccidentVictimInjuryPart(accidentVictimHumanInjuryPart);
                        }
                    }
                    // 사고자별 상해종류 일괄삭제
                    if (accidentVictim.getHumanInjuryClsCds() != null && accidentVictim.getHumanInjuryClsCds().length > 0) {
                        for (String humanInjuryClsCd : accidentVictim.getHumanInjuryClsCds()) {
                            AccidentVictimHumanInjuryCls accidentVictimHumanInjuryCls = new AccidentVictimHumanInjuryCls();
                            accidentVictimHumanInjuryCls.setHumanInjuryClsCd(humanInjuryClsCd);
                            accidentVictimHumanInjuryCls.setSafAccidentVictimNo(accidentVictim.getSafAccidentVictimNo());
                            resultNo += accidentMapper.createAccidentVictimInjuryCls(accidentVictimHumanInjuryCls);
                        }
                    }

                }
            }
        }
        /*******************************************
         * 사고자 end
         *****************************************/

        /*******************************************
         * 사고물질명 및 설비명 start
         ***************************************/
        // 설비
        if (accident.getAccidentFacility() != null && accident.getAccidentFacility().size() > 0) {
            for (AccidentFacility accidentFacility : accident.getAccidentFacility()) {
                accidentFacility.setSafAccidentNo(accident.getSafAccidentNo());
                accidentMapper.createAccidentFacility(accidentFacility);
            }
        }

        // 물질
        if (accident.getAccidentChemprod() != null && accident.getAccidentChemprod().size() > 0) {
            for (AccidentChemprod accidentChemprod : accident.getAccidentChemprod()) {
                accidentChemprod.setSafAccidentNo(accident.getSafAccidentNo());
                accidentMapper.createAccidentChemprod(accidentChemprod);
            }
        }
        /*******************************************
         * 사고물질명 및 설비명 end
         *****************************************/

        /**
         * 사고 등록 시 안전환경팀, 지원팀의 팀장, 담당에게 알림톡을 보낸다.
         */
        // String plantCd = accident.getPlantCd();
        // Accident saveAccident =
        // accidentMapper.getAccidentOccur(accident.getSafAccidentNo());
        // String[] deptNms = { "안전환경팀", "지원팀" };
        // String[] dutyCds = { "21", "31" };
        // List<User> users = userService.getDutyUserInfo(plantCd, deptNms,
        // dutyCds, null);
        // if (users != null && users.size() > 0) {
        // for (User user : users) {
        // String message = "";
        // message += "[SHE시스템] 사고발생 안내<br/><br/>";
        // message += "[ " + saveAccident.getPlantNm() + " " +
        // saveAccident.getAccidentTitle() + " ]<br/><br/>";
        // message += "ㅇ사고구분 : " + saveAccident.getAccKindNms() + "<br/>";
        // message += "ㅇ사고종류 : " + saveAccident.getAccidentTypeNms() + "<br/>";
        // message += "ㅇ발생일시 : " + saveAccident.getAccidentYmd() + " 시작시간 " +
        // saveAccident.getAccidentHour() + "시 " +
        // saveAccident.getAccidentMinute() + "분" + "<br/>";
        // message += "ㅇ발생부서/장소 : " + saveAccident.getDeptNm() + "/" +
        // saveAccident.getArea() + "<br/>";
        // // message += "ㅇ사고개요<br/>" + saveAccident.getContents() +
        // // "<br/>";
        // message += "ㅇ조치사항 : " + saveAccident.getFirstAct() + "<br/><br/>";
        // message += "세부내용은 안전보건환경 시스템에서 확인하실 수 있습니다";
        // /**
        // * 2020-02-07 메일 발송 로직 주석 처리 풍산의 메일 보내는 PC에 접속 하지 못할 뿐더러 접속이
        // * 이루어져서도 안됨 위의 사항이 있기 때문에 로직 주석처리하여 사전차단하고자 함
        // */
        // // sendTalkService.sendTalkProcedure(user.getPhoneNo(), message,
        // // "PS-SHE_02");
        // }
        // }

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("safAccidentNo", accident.getSafAccidentNo());
        data.put("accidentNum", accident.getAccidentNum());
        data.put("accidentStepCd", accident.getAccidentStepCd());

        return data;
    }

    /**
     * 사내사고 저장
     *
     * @param accident
     *            사내사고
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public HashMap<String, Object> updateAccident(Accident accident) throws Exception {
        int resultNo = 0;
        // String stepCd = accident.getAccidentStepCd();

        // // 원인유형코드가 기타가 아닌 경우
        // if (!ConstVal.CAU_TYPE_ETC.equals(accident.getCauTypeCd()))
        // {
        // // 원인유형코드에 사용자가 기타를 선택하지 않은 경우 원인유형기타값을 비운다.
        // accident.setCauTypeEtc("");
        // }

        // // 사내사고유형코드가 기타가 아닌 경우
        if (accident.getAccidentTypes() != null) {
            if (accident.getAccidentTypes().size() > 0) {
                boolean isEtc = false;
                for (AccidentOcctype accidentOcctype : accident.getAccidentTypes()) {
                    if (ConstVal.ACCIDENT_TYPE_ETC.equals(accidentOcctype.getAccidentTypeCd())) {
                        isEtc = true;
                    }
                }
                if (!isEtc) {
                    accident.setAccidentTypeEtc("");
                }
            } else {
                accident.setAccidentTypeEtc("");
            }
        }

        // if (ConstVal.SAF_ACCIDENT_STEP_CHECK_COMPLETE.contentEquals(stepCd))
        // {
        // List<ImprAct> imprActs = imprService.getImprActs("",
        // ConstVal.SAF_IMPR_CLASS_ACCIDENT, "", "", "", "", "",
        // "", "", "", "", accident.getSafAccidentNo());
        //
        // if (imprActs != null && imprActs.size() > 0) {
        // accident.setAccidentStepCd(ConstVal.SAF_ACCIDENT_STEP_COMPLETE);
        // }
        // }

        // update
        resultNo += accidentMapper.updateAccident(accident);

        /*******************************************
         * 사고물질명 및 설비명 start
         ***************************************/
        // 설비
        if (accident.getAccidentFacility() != null) {
            accidentMapper.deleteAccidentFacility(accident.getSafAccidentNo());
            if (accident.getAccidentFacility().size() > 0) {
                for (AccidentFacility accidentFacility : accident.getAccidentFacility()) {
                    accidentFacility.setSafAccidentNo(accident.getSafAccidentNo());
                    accidentMapper.createAccidentFacility(accidentFacility);
                }
            }
        }

        // 물질
        if (accident.getAccidentChemprod() != null) {
            accidentMapper.deleteAccidentChemprod(accident.getSafAccidentNo());
            if (accident.getAccidentChemprod().size() > 0) {
                for (AccidentChemprod accidentChemprod : accident.getAccidentChemprod()) {
                    accidentChemprod.setSafAccidentNo(accident.getSafAccidentNo());
                    accidentMapper.createAccidentChemprod(accidentChemprod);
                }
            }
        }
        /*******************************************
         * 사고물질명 및 설비명 end
         *****************************************/

        /*******************************************
         * 사고종류 start
         ***************************************/
        // 사내사고번호에 따른 사내사고 종류 일괄삭제
        accidentMapper.deleteAccidentOccurtype("", accident.getSafAccidentNo());

        if (accident.getAccidentTypes() != null && accident.getAccidentTypes().size() > 0) {
            for (AccidentOcctype accidentOcctype : accident.getAccidentTypes()) {
                accidentOcctype.setSafAccidentNo(accident.getSafAccidentNo());
                resultNo += accidentMapper.createAccidentOccurtype(accidentOcctype);
            }
        }
        /*******************************************
         * 사고종류 end
         *****************************************/

        /*******************************************
         * 사고구분 start
         ***************************************/
        // 사내사고번호에 따른 사내사고 구분 일괄삭제
        accidentMapper.deleteAccidentKind(accident.getSafAccidentNo());

        // 사내사고구분 등록
        if (accident.getAccKindCds() != null && accident.getAccKindCds().size() > 0) {
            for (AccidentKind accidentKind : accident.getAccKindCds()) {
                accidentKind.setSafAccidentNo(accident.getSafAccidentNo());
                resultNo += accidentMapper.createAccidentKind(accidentKind);
            }
        }

        /*******************************************
         * 사고구분 end
         *****************************************/

        /*******************************************
         * 사고자 start
         ***************************************/
        if (accident.getAccidentVictims() != null) {
            // 사내사고번호에 따른 사내사고 피해자정보 일괄삭제
            accidentMapper.deleteAccidentVictim(0, accident.getSafAccidentNo());
            if (accident.getAccidentVictims().size() > 0) {
                for (AccidentVictim accidentVictim : accident.getAccidentVictims()) {
                    // 사고자 등록
                    accidentVictim.setSafAccidentNo(accident.getSafAccidentNo());
                    resultNo += accidentMapper.createAccidentVictim(accidentVictim);

                    // 사고자별 상해부위 일괄삭제
                    if (accidentVictim.getHumanInjuryPartCds() != null) {
                        accidentMapper.deleteAccidentVictimInjuryPart(accidentVictim.getSafAccidentVictimNo());
                        if (accidentVictim.getHumanInjuryPartCds().length > 0) {
                            for (String humanInjuryPartCd : accidentVictim.getHumanInjuryPartCds()) {
                                AccidentVictimHumanInjuryPart accidentVictimHumanInjuryPart = new AccidentVictimHumanInjuryPart();
                                accidentVictimHumanInjuryPart.setSafAccidentVictimNo(accidentVictim.getSafAccidentVictimNo());
                                accidentVictimHumanInjuryPart.setHumanInjuryPartCd(humanInjuryPartCd);
                                accidentMapper.createAccidentVictimInjuryPart(accidentVictimHumanInjuryPart);
                            }
                        }
                    }
                    // 사고자별 상해종류 일괄삭제
                    if (accidentVictim.getHumanInjuryClsCds() != null) {
                        accidentMapper.deleteAccidentVictimInjuryCls(accidentVictim.getSafAccidentVictimNo());
                        if (accidentVictim.getHumanInjuryClsCds().length > 0) {
                            for (String humanInjuryClsCd : accidentVictim.getHumanInjuryClsCds()) {
                                AccidentVictimHumanInjuryCls accidentVictimHumanInjuryCls = new AccidentVictimHumanInjuryCls();
                                accidentVictimHumanInjuryCls.setSafAccidentVictimNo(accidentVictim.getSafAccidentVictimNo());
                                accidentVictimHumanInjuryCls.setHumanInjuryClsCd(humanInjuryClsCd);
                                accidentMapper.createAccidentVictimInjuryCls(accidentVictimHumanInjuryCls);
                            }
                        }
                    }

                }
            }
        }
        /*******************************************
         * 사고자 end
         *****************************************/

        /*******************************************
         * 조사계획 start
         ***************************************/
        // 사내사고번호에 따른 조사계획이 있는지 확인한다.
        int countAccidentInvest = accidentMapper.countAccidentInvest(accident.getSafAccidentNo());
        AccidentInvest accidentInvest = accident.getAccidentInvest();

        if (accidentInvest != null && !"".equals(accidentInvest.getLeaderUserId()) && accidentInvest.getLeaderUserId() != null) {
            accidentInvest.setSafAccidentNo(accident.getSafAccidentNo());
            // update
            if (countAccidentInvest > 0) {
                accidentInvest.setUpdateUserId(accident.getUpdateUserId());
                resultNo += accidentMapper.updateAccidentInvest(accidentInvest);
            } else {
                // insert
                accidentInvest.setCreateUserId(accident.getUpdateUserId());
                resultNo += accidentMapper.createAccidentInvest(accidentInvest);
            }
        }

        if (accident.getAccidentInvestPsns() != null) {
            // 사내사고번호에 따른 사내사고조사인원을 일괄삭제한다.
            accidentMapper.deleteAccidentInvestPsn(accident.getSafAccidentNo());
            if (accident.getAccidentInvestPsns().size() > 0) {
                for (AccidentInvestPsn accidentInvestPsn : accident.getAccidentInvestPsns()) {
                    accidentInvestPsn.setSafAccidentNo(accident.getSafAccidentNo());
                    resultNo += accidentMapper.createAccidentInvestPsn(accidentInvestPsn);
                }
            }
        }
        /*******************************************
         * 조사계획 end
         *****************************************/

        /*******************************************
         * 사고관계자 start
         ***************************************/
        if (accident.getAccidentRefPsns() != null) {
            // 사고관계자 일괄삭제
            resultNo += accidentMapper.deleteAccidentRefPsn(accident.getSafAccidentNo());
            if (accident.getAccidentRefPsns().size() > 0) {
                for (AccidentRefPsn accidentRefPsn : accident.getAccidentRefPsns()) {
                    // 사고관계자 등록
                    accidentRefPsn.setSafAccidentNo(accident.getSafAccidentNo());
                    resultNo += accidentMapper.createAccidentRefPsn(accidentRefPsn);
                }
            }
        }
        /*******************************************
         * 사고관계자 end
         *****************************************/

        /*******************************************
         * 사고결과 start
         *************************************/
        if (accident.getAccidentCauMeass() != null && accident.getAccidentCauMeass().size() > 0) {
            for (AccidentCauMeas accidentCauMeas : accident.getAccidentCauMeass()) {
                accidentCauMeas.setSafAccidentNo(accident.getSafAccidentNo());
                if (accidentCauMeas.getSafAccidentCauMeasNo() > 0) {
                    resultNo += accidentMapper.updateAccidentCauMeas(accidentCauMeas);
                } else {
                    resultNo += accidentMapper.createAccidentCauMeas(accidentCauMeas);
                }

                // if
                // (ConstVal.SAF_ACCIDENT_STEP_COMPLETE.equals(accident.getAccidentStepCd()))
                // {
                // imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_ACCIDENT,
                // accident.getSafAccidentNo(),
                // accident.getUpdateUserId());
                // }
            }
        }
        /*******************************************
         * 사고결과 end
         ***************************************/

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("safAccidentNo", accident.getSafAccidentNo());
        data.put("accidentNum", accident.getAccidentNum());
        data.put("accidentStepCd", accident.getAccidentStepCd());

        return data;
    }

    /**
     * 사내사고 삭제
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccident(int safAccidentNo) throws Exception {
        int resultNo = 0;
        resultNo += accidentMapper.deleteAccidentChemprod(safAccidentNo);
        resultNo += accidentMapper.deleteAccidentFacility(safAccidentNo);
        resultNo += accidentMapper.deleteAccidentKind(safAccidentNo);
        resultNo += accidentMapper.deleteAccidentOccurtype("", safAccidentNo);
        resultNo += accidentMapper.deleteAccidentVictim(0, safAccidentNo);
        resultNo += accidentMapper.deleteAccidentVictimInjuryCls(safAccidentNo);
        resultNo += accidentMapper.deleteAccidentVictimInjuryPart(safAccidentNo);
        resultNo += accidentMapper.deleteAccident(safAccidentNo);
        return resultNo;
    }

    /**
     * 사내사고 설비 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 설비 목록
     * @throws Exception
     */
    public List<FacilityMst> getAccidentFacilitys(int safAccidentNo) throws Exception {
        return accidentMapper.getAccidentFacilitys(safAccidentNo);
    }

    /**
     * 사내사고 취급물질 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 취급물질 목록
     * @throws Exception
     */
    public List<Chemprod> getAccidentChemprods(int safAccidentNo, DefaultParam defaultParam) throws Exception {
        return accidentMapper.getAccidentChemprods(safAccidentNo, defaultParam);
    }

    /**
     * 사내사고조사 인원 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고조사
     * @throws Exception
     */
    public List<AccidentInvestPsn> getAccidentInvestPsns(int safAccidentNo) throws Exception {
        return accidentMapper.getAccidentInvestPsns(safAccidentNo);
    }

    /**
     * 사내사고 피해자정보 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @param victimTypeCd
     *            피해자구분코드
     * @return 사내사고 피해자정보 목록
     * @throws Exception
     */
    public List<AccidentVictim> getAccidentVictims(int safAccidentNo, String victimTypeCd, DefaultParam defaultParam) throws Exception {
        List<AccidentVictim> accidentVictims = accidentMapper.getAccidentVictims(safAccidentNo, victimTypeCd, defaultParam);

        // 사고자 별 상해부위, 상해종류 리스트 조회
        if (accidentVictims != null && accidentVictims.size() > 0) {
            for (AccidentVictim accidentVictim : accidentVictims) {
                accidentVictim.setHumanInjuryClsCds(accidentMapper.getAccidentVictimHumanInjuryClss(accidentVictim.getSafAccidentVictimNo(), defaultParam));
                accidentVictim.setHumanInjuryPartCds(accidentMapper.getAccidentVictimHumanInjuryParts(accidentVictim.getSafAccidentVictimNo(), defaultParam));
            }
        }
        return accidentVictims;
    }

    /**
     * 사내사고 원인 및 대책 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 원인 및 대책 목록
     * @throws Exception
     */
    public List<AccidentCauMeas> getAccidentCauMeass(int safAccidentNo, DefaultParam defaultParam) throws Exception {
        return accidentMapper.getAccidentCauMeass(safAccidentNo, defaultParam);
    }

    /**
     * 사내사고 원인 및 대책 삭제
     *
     * @param safAccidentCauMeasNo
     *            원인및대책번호
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     */
    @Transactional
    public int deleteAccidentCauMeas(int safAccidentCauMeasNo, int safAccidentNo) throws Exception {
        int resultNo = 0;
        // 원인및대책번호로 엮여 있는 개선사항 혹은 즉시조치건들을 삭제
        resultNo += imprService.deleteImprActRefTableId(safAccidentCauMeasNo);
        // 원인및대책 삭제
        resultNo += accidentMapper.deleteAccidentCauMeas(safAccidentCauMeasNo, safAccidentNo);
        return resultNo;
    }

    /**
     * 사내사고 관계자 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 관계자 목록
     * @throws Exception
     */
    public List<AccidentRefPsn> getAccidentRefPsns(int safAccidentNo, DefaultParam defaultParam) throws Exception {
        return accidentMapper.getAccidentRefPsns(safAccidentNo, defaultParam);
    }

    /**
     * 사내사고집계 조회
     *
     * @param accidentYear
     *            accidentYear
     * @param yearMonth
     *            년월
     * @param plantCd
     *            사업장명
     * @param deptCd
     *            발생부서
     * @param accidentGubun
     *            사고구분(아차사고, 사고)
     * @param accidentType
     *            사고유형
     * @return 사내사고집계 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAccidentStatus(String accidentYear, String plantCd, String deptCd, String deptSubYn, String accidentGubun, String accidentType, String occKindCd, String occAttCd, String immCauseCd, String indCauseCd, DefaultParam defaultParam) throws Exception {
        List<String> yearMonth = new ArrayList<>();

        String fromYmd = accidentYear + "-01-01";
        String toYmd = accidentYear + "-12-31";

        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();
        String yearMonthStr = "";

        fromDate.setTime(Methods.convertStringToDate(fromYmd));
        toDate.setTime(Methods.convertStringToDate(toYmd));
        for (; fromDate.compareTo(toDate) < 1; fromDate.add(Calendar.MONTH, 1)) {
            String year = fromDate.get(Calendar.YEAR) + "";
            String month = Methods.leftPad(String.valueOf(fromDate.get(Calendar.MONTH) + 1), 2, '0');
            yearMonth.add(year + "-" + month);
        }

        yearMonthStr = String.join(", ", yearMonth);

        return accidentMapper.getAccidentStatus(accidentYear, yearMonth, plantCd, deptCd, deptSubYn, accidentGubun, accidentType, occKindCd, occAttCd, immCauseCd, indCauseCd, yearMonthStr, defaultParam);
    }

    /**
     * 사고등록 진행단계 변경
     *
     * @param safAccidentNo
     *            사고 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessAccidentOccur(int safAccidentNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String accidentStepCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            Accident accident = accidentMapper.getAccidentOccur(safAccidentNo, new DefaultParam("kr"));
            if (accident != null) {
                if (accident.getAccidentStepCd().equals(ConstVal.SAF_ACCIDENT_STEP_OCCURW)) {
                    accidentStepCd = ConstVal.SAF_ACCIDENT_STEP_RECEIPT;
                }
            }
        }

        // 사고등록 > 사고 접수
        resultNo += accidentMapper.completeAccidentOccur(safAccidentNo, apprRqstNo, accidentStepCd);
        return resultNo;
    }

    /**
     * 사고등록 진행단계 변경
     *
     * @param safAccidentNo
     *            사고 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessAccidentReception(int safAccidentNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String accidentStepCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            Accident accident = accidentMapper.getAccidentReception(safAccidentNo, new DefaultParam("kr"));
            if (accident != null) {
                if (accident.getAccidentStepCd().equals(ConstVal.SAF_ACCIDENT_STEP_RECEIPT)) {
                    if (accident.getInvestYn().equals("Y")) {
                        // 조사팀을 만든경우
                        accidentStepCd = ConstVal.SAF_ACCIDENT_STEP_RESULT;
                    } else {
                        // 조사팀을 만들지 않은 경우
                        // 사고 완료 설정
                        accidentStepCd = ConstVal.SAF_ACCIDENT_STEP_COMPLETE;
                        // 상세의 진행단계가 완료로 바뀔 시에 해당 상세 엮여 있는 개선조치사항건에 대해서 접수 상태로
                        // 변경
                        imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_ACCIDENT, safAccidentNo, "");
                    }
                }
            }
        }

        // 사고접수 > 사고 조사결과 or 사고완료
        resultNo += accidentMapper.completeAccidentReception(safAccidentNo, apprRqstNo, accidentStepCd);
        return resultNo;
    }

    /**
     * 사고등록 진행단계 변경
     *
     * @param safAccidentNo
     *            사고 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessAccidentResult(int safAccidentNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String accidentStepCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            Accident accident = accidentMapper.getAccidentResult(safAccidentNo, new DefaultParam("kr"));
            if (accident != null) {
                if (accident.getAccidentStepCd().equals(ConstVal.SAF_ACCIDENT_STEP_RESULT)) {
                    accidentStepCd = ConstVal.SAF_ACCIDENT_STEP_COMPLETE;
                }
                // 상세의 진행단계가 완료로 바뀔 시에 해당 상세 엮여 있는 개선조치사항건에 대해서 접수 상태로 변경
                imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_ACCIDENT_RSLT, safAccidentNo, "");
                imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_ACCIDENT, safAccidentNo, "");
            }
        }

        // 사고 조사결과 > 사고 완료
        resultNo += accidentMapper.completeAccidentResult(safAccidentNo, apprRqstNo, accidentStepCd);
        return resultNo;
    }

    /**
     * 안전사고 보고서 출력
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 안전사고 보고서 파일
     * @throws Exception
     */
    public File getAccidentPrint(int safAccidentNo) throws Exception {
        // 사고결과 상세정보
        List<Accident> accidents = new ArrayList<>();
        Accident accident = this.getAccident(safAccidentNo, "result", new DefaultParam("kr"));
        accidents.add(accident);

        // 사고 구분
        String accKindNms = "";
        List<AccidentKind> accidentKinds = accident.getAccKindCds();
        for (int i = 0; i < accidentKinds.size(); i++) {
            accKindNms += "," + accidentKinds.get(i).getAccKindNm();
        }
        accident.setAccKindNms(accKindNms.substring(1));

        // 사고 종류
        String accTypeNms = "";
        List<AccidentOcctype> accidentOcctypes = accident.getAccidentTypes();
        for (int i = 0; i < accidentOcctypes.size(); i++) {
            accTypeNms += "," + accidentOcctypes.get(i).getAccidentTypeNm();
        }
        accident.setAccidentTypeNms(accTypeNms.substring(1));

        // 공통코드로 공통코드명 조회
        // 중분류
        String occAttNm = "";
        // 대분류코드
        String occKindCd = accident.getOccKindCd();
        List<CodeMaster> occAttCodeMasters = codeMasterMapper.getSelect(occKindCd, "Y", new DefaultParam("kr"));
        for (int i = 0; i < occAttCodeMasters.size(); i++) {
            String code = occAttCodeMasters.get(i).getCode();
            String codeNm = occAttCodeMasters.get(i).getCodeNm();
            if (code.equals(accident.getOccAttCd())) {
                occAttNm = codeNm;
            }
        }
        accident.setOccAttNm(occAttNm);

        // 공정상태
        String prcsStateNm = "";
        List<CodeMaster> prcsStateCodeMasters = codeMasterMapper.getSelect("SAF_PRCS_STATE", "Y", new DefaultParam("kr"));
        for (int i = 0; i < prcsStateCodeMasters.size(); i++) {
            String code = prcsStateCodeMasters.get(i).getCode();
            String codeNm = prcsStateCodeMasters.get(i).getCodeNm();
            if (code.equals(accident.getPrcsStateCd())) {
                prcsStateNm = codeNm;
            }
        }
        // 기인물
        String originMatNm = "";
        List<CodeMaster> originMatCodeMasters = codeMasterMapper.getSelect("SAF_ACCIDENT_ORGIN_MAT", "Y", new DefaultParam("kr"));
        for (int i = 0; i < originMatCodeMasters.size(); i++) {
            String code = originMatCodeMasters.get(i).getCode();
            String codeNm = originMatCodeMasters.get(i).getCodeNm();
            if (code.equals(accident.getOriginMatCd())) {
                originMatNm = codeNm;
            }
        }
        // 가해물
        String assMatNm = "";
        List<CodeMaster> assMatCodeMasters = codeMasterMapper.getSelect("SAF_ACCIDENT_ASS_MAT", "Y", new DefaultParam("kr"));
        for (int i = 0; i < assMatCodeMasters.size(); i++) {
            String code = assMatCodeMasters.get(i).getCode();
            String codeNm = assMatCodeMasters.get(i).getCodeNm();
            if (code.equals(accident.getAssMatCd())) {
                assMatNm = codeNm;
            }
        }

        // 사고자정보
        AccidentVictim accidentVictim = accident.getAccidentVictims().get(0);

        // 이미지 없을때 경로
        String noImage = globalSettings.getNoImageFilePath();
        // 전체,근거리범위 이미지경로
        String allImage = "";
        String nearImage = "";

        AttachFile allAttachFile = this.attachFileMapper.getFirstUploadFile("ACCIDENT_ALL", Integer.toString(accident.getSafAccidentNo())) == null ? new AttachFile() : this.attachFileMapper.getFirstUploadFile("ACCIDENT_ALL", Integer.toString(accident.getSafAccidentNo()));
        AttachFile nearAttachFile = this.attachFileMapper.getFirstUploadFile("ACCIDENT_NEAR", Integer.toString(accident.getSafAccidentNo())) == null ? new AttachFile() : this.attachFileMapper.getFirstUploadFile("ACCIDENT_NEAR", Integer.toString(accident.getSafAccidentNo()));

        if (allAttachFile.getFileDownPath() == null) {
            allImage = noImage;
        } else {
            File check = new File(FileUtil.getStoreFilePath() + File.separator + allAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\"));
            allImage = check.isFile() && check.exists() ? FileUtil.getStoreFilePath() + File.separator + allAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\") : noImage;
        }
        if (nearAttachFile.getFileDownPath() == null) {
            nearImage = noImage;
        } else {
            File check = new File(FileUtil.getStoreFilePath() + File.separator + nearAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\"));
            nearImage = check.isFile() && check.exists() ? FileUtil.getStoreFilePath() + File.separator + nearAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\") : noImage;
        }
        // allImage = allAttachFile.getFileDownPath() == null ? noImage :
        // FileUtil.getStoreFilePath() + File.separator +
        // allAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\");
        // nearImage = nearAttachFile.getFileDownPath() == null ? noImage :
        // FileUtil.getStoreFilePath() + File.separator +
        // nearAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\");

        // List to Datasource
        JRDataSource accidentData = new JRBeanCollectionDataSource(accidents);
        // 파일경로 setting
        String logoPath = globalSettings.getLogoImageFilePath();
        String reportPath = globalSettings.getReportAccident();
        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = file.getAbsolutePath();
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        String outputFileNamepdf = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        // 파라미터값
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo_dir", logoPath);
        parameters.put("prcsStateNm", prcsStateNm);
        parameters.put("originMatNm", originMatNm);
        parameters.put("assMatNm", assMatNm);
        parameters.put("victimDeptNm", accidentVictim.getVictimDeptNm());
        parameters.put("victimNm", accidentVictim.getVictimNm());
        parameters.put("victimBirthday", accidentVictim.getVictimBirthday());
        parameters.put("victimUserId", accidentVictim.getVictimUserId());
        parameters.put("position", accidentVictim.getPosition());
        parameters.put("victimDutyNm", accidentVictim.getVictimDutyNm());
        parameters.put("entDt", accidentVictim.getEntDt());
        parameters.put("workHis", accidentVictim.getWorkHis());
        parameters.put("jobHis", accidentVictim.getJobHis());
        parameters.put("accidentHis", accidentVictim.getAccidentHis());
        parameters.put("allImg", allImage);
        parameters.put("nearImg", nearImage);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, accidentData);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileNamepdf);
        // Export the report to a excel
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File outputFile = new File(outputFileName);
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setAutoFitPageHeight(true);
        configuration.setCollapseRowSpan(false);

        exporter.setConfiguration(configuration);
        exporter.exportReport();

        return new File(outputFileNamepdf);
    }

    /**
     * 조사결과 현황
     * 
     * @param parameter
     *            검색조건
     * @return 조사결과 현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAccidentResultsStatusList(String plantCd, String assessYear, String regRegdemCd, String riskType, String totalFlag, DefaultParam defaultParam) throws Exception {
        List<HashMap<String, Object>> resultStatusLists = accidentMapper.getAccidentResultsStatusList(plantCd, assessYear, regRegdemCd, riskType, totalFlag, defaultParam);
        return resultStatusLists;
    }
}