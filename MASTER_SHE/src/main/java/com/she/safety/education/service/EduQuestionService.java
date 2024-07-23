package com.she.safety.education.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.waste.disposal.mapper.DisposalRequestMapper;
import com.she.env.waste.disposal.mapper.DisposalResultMapper;
import com.she.env.waste.model.DisposalResult;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.safety.education.mapper.EduMasterMapper;
import com.she.safety.education.mapper.EduQuestionMapper;
import com.she.safety.model.EduDetailPerson;
import com.she.safety.model.EduQuestion;
import com.she.safety.model.EduQuestionAnswer;
import com.she.safety.model.EduQuestionList;
import com.she.safety.model.UserEduAnswer;
import com.she.utils.ExcelReader;

@Service
public class EduQuestionService {
    @Autowired
    private EduQuestionMapper eduQuestionMapper;
    @Autowired
    private EduMasterMapper eduMasterMapper;

    @Autowired
    private DisposalRequestMapper disposalRequestMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private DisposalResultMapper disposalResultMapper;

    private String disposalResultExcelFileName = "폐기물_처리결과_양식_v1.0.xlsx";

    /**
     * 교육문제목록 조회
     * 
     * @param safEduCourseNo
     *            교육과정번호
     * @param plantCd
     *            사업장코드
     * @param eduAttCd
     *            분류코드
     * @param eduNm
     *            제목
     * @param eduTypeCd
     *            교육분류
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduQuestion> getEduQuestions(int safEduCourseNo, String eduAttCd, String examNm, String eduTypeCd, DefaultParam defaultParam) throws Exception {
        return eduQuestionMapper.getEduQuestions(safEduCourseNo, eduAttCd, examNm, eduTypeCd, defaultParam);
    }

    /**
     * 교육문제 단건 조회
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    public EduQuestion getEduQuestion(int eduQuestionNo, DefaultParam defaultParam) throws Exception {
        return eduQuestionMapper.getEduQuestion(eduQuestionNo, defaultParam);
    }

    /**
     * 교육문제 단건 삭제
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */

    @Transactional
    public int deleteEduQuestion(int eduQuestionNo) throws Exception {
        int deleteCount = eduQuestionMapper.deleteEduQuestion(eduQuestionNo);
        eduQuestionMapper.deleteEduQuestionList(eduQuestionNo);
        eduQuestionMapper.deleteEduQuestionAnswer(eduQuestionNo);

        return deleteCount;
    }

    /**
     * 교육문제목록 조회
     * 
     * @param eduQuestionNo
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getEduQuestionList(int eduQuestionNo) throws Exception {
        List<EduQuestionList> eduQuestionList = eduQuestionMapper.getEduQuestionList(eduQuestionNo);
        for (EduQuestionList eduQuestion : eduQuestionList) {
            eduQuestion.setEduQuestionAnswers(eduQuestionMapper.getEduQuestionAnswers(eduQuestion.getEduQuestionLstNo()));
        }

        return eduQuestionList;
    }

    /**
     * 교육문제목록 등록
     * 
     * @param eduQuestionNo
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    @Transactional
    public int createEduQuestionList(EduQuestion eduQuestion) throws Exception {
        int create = 0;
        create = eduQuestionMapper.createEduQuestion(eduQuestion);
        if (create > 0) {
            for (EduQuestionList eduQuestionlst : eduQuestion.getQuestions()) {
                eduQuestionlst.setEduQuestionNo(eduQuestion.getEduQuestionNo());
                eduQuestionMapper.createEduQuestionList(eduQuestionlst);
                for (EduQuestionAnswer eduQuestionAnswer : eduQuestionlst.getEduQuestionAnswers()) {
                    eduQuestionAnswer.setEduQuestionLstNo(eduQuestionlst.getEduQuestionLstSeq());
                    eduQuestionAnswer.setUseYn("Y");
                    eduQuestionMapper.createEduQuestionAnswers(eduQuestionAnswer);
                }
            }
        }
        return eduQuestion.getEduQuestionNo();
    }

    /**
     * 교육문제목록 수정
     * 
     * @param eduQuestionNo
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateEduQuestionList(EduQuestion eduQuestion) throws Exception {
        int update = 0;

        for (int lstNo : eduQuestion.getDelQuestion()) {
            eduQuestionMapper.deleteEduQuestionAnswers(lstNo);
        }

        for (int ansNo : eduQuestion.getDelAnswer()) {
            eduQuestionMapper.deleteEduAnswer(ansNo);
        }

        update = eduQuestionMapper.updateEduQuestion(eduQuestion);
        for (EduQuestionList eduQuestionlst : eduQuestion.getQuestions()) {
            eduQuestionlst.setEduQuestionNo(eduQuestion.getEduQuestionNo());
            eduQuestionMapper.createEduQuestionList(eduQuestionlst);
            for (EduQuestionAnswer eduQuestionAnswer : eduQuestionlst.getEduQuestionAnswers()) {
                if ("INSERT".equals(eduQuestionlst.getStatus())) {
                    eduQuestionAnswer.setEduQuestionLstNo(eduQuestionlst.getEduQuestionLstSeq());
                } else {
                    eduQuestionAnswer.setEduQuestionLstNo(eduQuestionlst.getEduQuestionLstNo());
                }
                eduQuestionAnswer.setUseYn("Y");
                eduQuestionMapper.createEduQuestionAnswers(eduQuestionAnswer);

            }
        }

        return update;
    }

    /**
     * 교육계획 문제 조회
     * 
     * @param safEduCourseNo
     *            교육과정
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getEduPlanQuestions(int safEduCourseNo, String eduAttCd, String plantCd, String useYn, DefaultParam defaultParam) throws Exception {
        List<EduQuestionList> eduQuestionList = eduQuestionMapper.getEduPlanQuestions(safEduCourseNo, eduAttCd, plantCd, useYn, defaultParam);
        // if (eduQuestionList.size() > 0) {
        // eduQuestionList.get(0).setCheckData(eduMasterMapper.getPlanQuestion(String.valueOf(safEduMstNo)));
        // }

        return eduQuestionList;
    }

    /**
     * 교육계획 문제 답안 조회
     * 
     * @param eduQuestionList
     *            교육계획 문제 항목
     * 
     * @return 질문/답변 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getEduPlanQuestion(List<EduQuestionList> eduQuestionList, DefaultParam defaultParam) throws Exception {
        for (EduQuestionList eduQuestion : eduQuestionList) {
            eduQuestion.setEduQuestionAnswers(eduQuestionMapper.getEduQuestionAnswers(eduQuestion.getEduQuestionLstNo()));

            eduQuestion.setCheckData(eduQuestionMapper.getEduPlanQuestionsCheckData(eduQuestion.getEduQuestionLstNo(), defaultParam));

        }

        return eduQuestionList;

    }

    /**
     * 교육계획 문제 답안 조회 ( 모바일 )
     * 
     * @param eduQuestionList
     *            교육계획 문제 항목
     * 
     * @return 질문/답변 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getMobileEduPlanQuestion(int safEduMstNo, String userId, DefaultParam defaultParam) throws Exception {
        List<EduQuestionList> eduQuestionList = eduQuestionMapper.getMobileEduPlanQuestions(safEduMstNo, userId, defaultParam);
        for (EduQuestionList eduQuestion : eduQuestionList) {
            eduQuestion.setEduQuestionAnswers(eduQuestionMapper.getEduQuestionAnswers(eduQuestion.getEduQuestionLstNo()));
        }

        return eduQuestionList;
    }

    /**
     * 교육계획 문제 답안 조회 ( 모바일 ) 교육계획 신규등록
     *
     * @param eduQuestionList
     *            교육계획 문제 항목
     *
     * @return 질문/답변 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getMobileEduPlanQuestionNew(int safEduCourseNo, DefaultParam defaultParam) throws Exception {
        List<EduQuestionList> eduQuestionList = eduQuestionMapper.getMobileEduPlanQuestionsNew(safEduCourseNo, defaultParam);
        for (EduQuestionList eduQuestion : eduQuestionList) {
            eduQuestion.setEduQuestionAnswers(eduQuestionMapper.getEduQuestionAnswers(eduQuestion.getEduQuestionLstNo()));
        }

        return eduQuestionList;
    }

    /**
     * 교육문제 사용자 제출 완료
     * 
     * @param eduQuestionList
     *            교육계획 문제 항목
     * 
     * @return 질문/답변 리스트
     * @throws Exception
     *             예외
     */
    public Float createEduUserAnswer(List<EduQuestionList> eduQuestionList, DefaultParam defaultParam) throws Exception {
        int create = 0;
        Float scoreSum = 0.0F;

        UserEduAnswer userEduAnswer = new UserEduAnswer();
        if (eduQuestionList.size() > 0) {
            eduQuestionMapper.deleteEduUserAnswer(eduQuestionList.get(0).getSafEduMstNo(), eduQuestionList.get(0).getUserId());

            for (EduQuestionList eduQuestion : eduQuestionList) {
                userEduAnswer.setUserId(eduQuestion.getUserId());
                userEduAnswer.setSafEduMstNo(eduQuestion.getSafEduMstNo());
                userEduAnswer.setEduQuestionLstNo(eduQuestion.getEduQuestionLstNo());
                userEduAnswer.setEduQuestionAnsNo(eduQuestion.getSelectData());
                userEduAnswer.setUpdateUserId(eduQuestion.getUpdateUserId());
                BigDecimal eduQuestionPnt = new BigDecimal(100).divide(new BigDecimal(eduQuestionList.size()), 3, BigDecimal.ROUND_UP);
                eduQuestionPnt.setScale(3, BigDecimal.ROUND_UP);

                userEduAnswer.setEduQuestionPnt(eduQuestionPnt);
                create = create + eduQuestionMapper.createEduUserAnswer(userEduAnswer);

                // 정답은 무조건 1개
                String answerNo = eduQuestionMapper.getEduPlanQuestionsCheckData(eduQuestion.getEduQuestionLstNo(), defaultParam)[0];

                if (eduQuestion.getSelectData() == Integer.parseInt(answerNo)) {
                    scoreSum = scoreSum + new BigDecimal(100).divide(new BigDecimal(eduQuestionList.size()), 1, BigDecimal.ROUND_UP).floatValue();
                }
            }

            if (scoreSum > 100) {
                scoreSum = 100f;
            }

            // 이수점수 조회
            int subconnScore = eduMasterMapper.getSubconnScore(eduQuestionList.get(0).getSafEduMstNo());
            if (scoreSum >= subconnScore) {
                eduQuestionMapper.updateAnswerChk("N", "Y", String.valueOf(scoreSum), null, eduQuestionList.get(0).getSafEduMstNo(), eduQuestionList.get(0).getUserId());

            } else {
                eduQuestionMapper.updateAnswerChk("N", "N", String.valueOf(scoreSum), null, eduQuestionList.get(0).getSafEduMstNo(), eduQuestionList.get(0).getUserId());
            }
        }
        return scoreSum;
    }

    /**
     * 교육문제 사용자 재 제출 완료
     * 
     * @param eduQuestionList
     *            교육계획 문제 항목
     * 
     * @return 질문/답변 리스트
     * @throws Exception
     *             예외
     */
    public Float editEduUserAnswer(List<EduQuestionList> eduQuestionList, DefaultParam defaultParam) throws Exception {
        int create = 0;
        Float scoreSum = 0.0F;

        UserEduAnswer userEduAnswer = new UserEduAnswer();
        if (eduQuestionList.size() > 0) {
            eduQuestionMapper.deleteEduUserAnswer(eduQuestionList.get(0).getSafEduMstNo(), eduQuestionList.get(0).getUserId());

            for (EduQuestionList eduQuestion : eduQuestionList) {
                userEduAnswer.setUserId(eduQuestion.getUserId());
                userEduAnswer.setSafEduMstNo(eduQuestion.getSafEduMstNo());
                userEduAnswer.setEduQuestionLstNo(eduQuestion.getEduQuestionLstNo());
                userEduAnswer.setEduQuestionAnsNo(eduQuestion.getSelectData());

                BigDecimal eduQuestionPnt = new BigDecimal(100).divide(new BigDecimal(eduQuestionList.size()), 3, BigDecimal.ROUND_UP);
                eduQuestionPnt.setScale(3, BigDecimal.ROUND_UP);

                userEduAnswer.setEduQuestionPnt(eduQuestionPnt);
                create = create + eduQuestionMapper.createEduUserAnswer(userEduAnswer);

                // 정답은 무조건 1개
                String answerNo = eduQuestionMapper.getEduPlanQuestionsCheckData(eduQuestion.getEduQuestionLstNo(), defaultParam)[0];

                if (eduQuestion.getSelectData() == Integer.parseInt(answerNo)) {
                    scoreSum = scoreSum + new BigDecimal(100).divide(new BigDecimal(eduQuestionList.size()), 1, BigDecimal.ROUND_UP).floatValue();
                }

            }

            if (scoreSum > 100) {
                scoreSum = 100f;
            }

            // 이수점수 조회
            int subconnScore = eduMasterMapper.getSubconnScore(eduQuestionList.get(0).getSafEduMstNo());
            if (scoreSum >= subconnScore) {
                eduQuestionMapper.updateAnswerChk("Y", "Y", null, String.valueOf(scoreSum), eduQuestionList.get(0).getSafEduMstNo(), eduQuestionList.get(0).getUserId());

            } else {
                eduQuestionMapper.updateAnswerChk("Y", "N", null, String.valueOf(scoreSum), eduQuestionList.get(0).getSafEduMstNo(), eduQuestionList.get(0).getUserId());
            }
        }
        return scoreSum;
    }

    /**
     * 교육문제 사용자 제출 삭제
     * 
     * @param eduQuestionList
     *            교육계획 문제 항목
     * 
     * @return 질문/답변 리스트
     * @throws Exception
     *             예외
     */
    public int deleteEduUserAnswer(int safEduMstNo, String userId, DefaultParam defaultParam) throws Exception {
        return eduQuestionMapper.deleteEduUserAnswer(safEduMstNo, userId);
    }

    /**
     * 교육문제 다시풀기 상태 체크
     * 
     * @param safEduCourseNo
     *            교육과정
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public String selectAnswerChk(int safEduMstNo, String userId, DefaultParam defaultParam) throws Exception {
        return eduQuestionMapper.selectAnswerChk(safEduMstNo, userId);
    }

    /**
     * 교육문제 채점
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * @param userId
     *            사용자아이디
     * 
     * @return 채점
     * @throws Exception
     *             예외
     */
    public String selectScore(int safEduMstNo, String userId, DefaultParam defaultParam) throws Exception {
        return eduQuestionMapper.selectScore(safEduMstNo, userId, defaultParam);
    }

    /**
     * 교육문제 조회 전 교육기간 체크
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    public HashMap<String, Object> selectQuestionYmdChk(int safEduMstNo, DefaultParam defaultParam) throws Exception {
        return eduQuestionMapper.selectQuestionYmdChk(safEduMstNo, defaultParam);
    }

    public File createDisposalResultExcel(DefaultParam defaultParam) throws Exception {
        File file = new File("dsad.xslx");

        ExcelReader reader = new ExcelReader();
        List<List<String>> appendRows = new ArrayList<List<String>>();
        List<DisposalResult> disposalResults = this.disposalResultMapper.getDisposalResultsExcel(null, null, defaultParam);
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

    private String convertExcelCellData(Object o) {
        if (o == null) {
            return "";
        } else {
            return String.valueOf(o);
        }
    }

    /**
     * 교육이수자 교육동영상 시청 등록/수정
     * 
     * @param eduDetailPerson
     *            교육이수자
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateViewUserVideo(EduDetailPerson eduDetailPerson, DefaultParam defaultParam) throws Exception {
        return eduQuestionMapper.updateViewUserVideo(eduDetailPerson);
    }

}
