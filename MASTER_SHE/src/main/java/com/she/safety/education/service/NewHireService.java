package com.she.safety.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.education.mapper.NewHireMapper;
import com.she.safety.model.NewHire;

@Service
public class NewHireService {

    @Autowired
    private NewHireMapper newHireMapper;

    /**
     * 신규채용직원 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 신규채용직원 목록
     * @throws Exception
     *             예외
     */
    public List<NewHire> getNewHires(String deptCd, String deptSubYn, String userNm, String plantCd, String employmentYn, String eduCompletYn, String checkupCompletYn, String startYmd, String endYmd, DefaultParam defaultParam) throws Exception {
        return newHireMapper.getNewHires(deptCd, deptSubYn, userNm, plantCd, employmentYn, eduCompletYn, checkupCompletYn, startYmd, endYmd, defaultParam);
    }

    /**
     * 신규채용직원 상세조회
     *
     * @param userId
     *            사용자아이디
     * @return 신규채용직원 상세조회
     * @throws Exception
     *             예외
     */
    public NewHire getNewHire(String userId) throws Exception {
        return newHireMapper.getNewHire(userId);
    }

    /**
     * 신규채용 교육목록 조회
     * 
     * @return 신규채용 교육목록
     * @throws Exception
     *             예외
     */
    public List<NewHire> getNewHireEduList(DefaultParam defaultParam) throws Exception {
        return newHireMapper.getNewHireEduList(defaultParam);
    }

    /**
     * 교육이수여부 확인
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @param userId
     *            사용자아이디
     * @return 교육이수여부 확인
     * @throws Exception
     *             예외
     */
    public int getCheckEduComplete(int safEduMstNo, String userId) throws Exception {
        return newHireMapper.getCheckEduComplete(safEduMstNo, userId);
    }

    /**
     * 신규채용 교육이수여부 확인
     * 
     * @param userId
     *            사용자아이디
     * @return 신규채용 교육이수여부 확인
     * @throws Exception
     *             예외
     */
    public int getCheckNewHireEduComplete(String userId) throws Exception {
        return newHireMapper.getCheckNewHireEduComplete(userId);
    }

    /**
     * 신규채용 교육 이수자 등록
     * 
     * @param List<NewHire>
     *            신규채용직원 목록
     * @return 신규채용 교육 이수자 등록
     * @throws Exception
     *             예외
     */
    public int createNewHireEduUser(List<NewHire> newHireList) throws Exception {
        int count = 0;
        for (int i = 0; i < newHireList.size(); i++) {
            NewHire newHire = newHireList.get(i);
            int result = newHireMapper.getCheckEduComplete(newHire.getSafEduMstNo(), newHire.getUserId());
            if (result == 0) {
                count += newHireMapper.createNewHireEduUser(newHireList.get(i));
            }

        }
        return count;
    }

    /**
     * 건강검진 대상자 등록 확인
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @return 건강검진 대상자 등록 확인
     * @throws Exception
     *             예외
     */
    public int getChkCheckupUser(int heaCheckupPlanNo, String userId) throws Exception {
        return newHireMapper.getChkCheckupUser(heaCheckupPlanNo, userId);
    }

    /**
     * 신규채용 건강검진 대상자 등록 확인
     * 
     * @param userId
     *            사용자아이디
     * @return 신규채용 건강검진 대상자 등록 확인
     * @throws Exception
     *             예외
     */
    public int getChkNewHireCheckupUser(String userId) throws Exception {
        return newHireMapper.getChkNewHireCheckupUser(userId);
    }
}
