package com.she.rsa.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.baseInfo.mapper.AssessTypeMapper;
import com.she.rsa.model.AssessType;

@Service
public class AssessTypeService {

    @Autowired
    private AssessTypeMapper assessTypeMapper;

    /**
     * 평가기법 조회
     * 
     * @param flag
     * @param assessTypeCd
     *            평가기법
     * @return 평가기법 목록
     * @throws Exception
     */
    public List<AssessType> getAssessTypes(String plantCd, String assessGroupCd, String assessTypeCd, String assessNm, DefaultParam defaultParam)
            throws Exception {
        return assessTypeMapper.getAssessTypes(plantCd, assessGroupCd, assessTypeCd, assessNm, defaultParam);
    }

    /**
     * 평가기법 이력 조회
     * 
     * @param assessGroupNo
     *            평가그룹번호
     * @return 평가기법 이력 목록
     * @throws Exception
     */
    public List<AssessType> getAssessTypeHistorys(int assessGroupNo, DefaultParam defaultParam) throws Exception {
        return assessTypeMapper.getAssessTypeHistorys(assessGroupNo, defaultParam);
    }

    /**
     * 평가기법 상세 조회
     * 
     * @param assessTypeNo
     *            평가기법 번호
     * @return 평가기법
     * @throws Exception
     */
    public AssessType getAssessType(String assessTypeNo, DefaultParam defaultParam) throws Exception {
        return this.assessTypeMapper.getAssessType(assessTypeNo, defaultParam);
    }

    /**
     * 평가기법 상세 조회
     * 
     * @param assessTypeNo
     *            평가기법 번호
     * @param assessNm
     *            평가기법 Matrix명
     * @return 중복 여부
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckAssessNm(String plantCd, String assessTypeNo, String assessNm,
            String assessTypeCd, int assessGroupNo, String revNo) throws Exception {
        return this.assessTypeMapper.getCheckAssessNm(plantCd, assessTypeNo, assessNm, assessTypeCd, assessGroupNo,
                revNo);
    }

    /**
     * 평가기법 신규등록
     * 
     * @param assessType
     *            평가기법
     * @return 평가기법 번호
     * @throws Exception
     */
    @Transactional
    public int createAssessType(AssessType assessType) throws Exception {
        this.assessTypeMapper.createAssessType(assessType);

        return assessType.getAssessTypeNo();
    }

    /**
     * 평가기법 수정
     * 
     * @param assessType
     *            평가기법
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateAssessType(AssessType assessType) throws Exception {
        return this.assessTypeMapper.updateAssessType(assessType);
    }
}
