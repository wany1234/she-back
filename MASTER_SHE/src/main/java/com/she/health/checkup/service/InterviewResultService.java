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
package com.she.health.checkup.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.she.health.checkup.mapper.InterviewResultMapper;
import com.she.health.model.InterviewResult;
import com.she.utils.ConstVal;

/**
 * 문진결과 기능정의 - 사용하지 않음. 문진항목 마스터 개발완료, 결과 업로드 및 조회의 경우 구현하지 않기로함.
 *
 */
@Service
public class InterviewResultService {
    @Autowired
    private InterviewResultMapper interviewResultMapper;

    /**
     * 문진결과 조회
     *
     * @param checkupYear
     *            검진년도
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param retirementYn
     *            재직/퇴직여부
     * @param userNm
     *            사용자명
     * @param heaCheckedOrgNos
     *            검진기관
     * @param smokingCd
     *            흡연여부
     * @param drinkingCd
     *            음주여부
     * @return 문진결과 목록
     * @throws Exception
     */
    public List<InterviewResult> getInterviewResults(String checkupYear, int heaCheckupPlanNo, String retirementYn,
            String userNm, int[] heaCheckedOrgNos, String smokingYn, String drinkingYn) throws Exception {
        String smokingCd = "";
        String drinkingCd = "";
        if (smokingYn.equals("Y")) {
            smokingCd = ConstVal.INTERVIEW_ITEM_SMOKING_CD;
        }

        if (drinkingYn.equals("Y")) {
            drinkingCd = ConstVal.INTERVIEW_ITEM_DRINKING_CD;
        }

        return this.interviewResultMapper.getInterviewResults(checkupYear, heaCheckupPlanNo, retirementYn, userNm,
                heaCheckedOrgNos, smokingCd, drinkingCd);
    }

    /**
     * 문진결과 상세 조회
     *
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param userId
     *            사용자아이디
     * @return 문진결과
     * @throws Exception
     */
    public InterviewResult getInterviewResult(int heaCheckupPlanNo, String userId) throws Exception {
        return this.interviewResultMapper.getInterviewResult(heaCheckupPlanNo, userId);
    }

    /**
     * 문진결과 신규 생성
     *
     * @param interviewResult
     *            문진결과
     * @return 생성행수
     * @throws Exception
     */
    public int createInterviewResult(InterviewResult interviewResult) throws Exception {
        return this.interviewResultMapper.createInterviewResult(interviewResult);
    }

    /**
     * 문진결과 수정
     *
     * @param interviewResult
     *            문진결과
     * @return 수정행수
     * @throws Exception
     */
    public int updateInterviewResult(InterviewResult interviewResult) throws Exception {
        return this.interviewResultMapper.updateInterviewResult(interviewResult);
    }

    /**
     * 문진결과 삭제
     *
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param userId
     *            사용자아이디
     * @param heaInteItemCd
     *            문진항목코드
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteInterviewResult(int heaCheckupPlanNo, String userId, String heaInteItemCd) throws Exception {
        return this.interviewResultMapper.deleteInterviewResult(heaCheckupPlanNo, userId, heaInteItemCd);
    }

    /**
     * 문진결과 엑셀업로드
     *
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param file
     *            엑셀파일
     * @return 업로드결과
     */
    public Map<String, Object> uploadExcelCheckupResult(int heaCheckupPlanNo, MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadResult", null);
        map.put("errorInfo", null);

        // 업로드 개발 안함. 구현하지 않기로 했음.

        return map;
    }
}
