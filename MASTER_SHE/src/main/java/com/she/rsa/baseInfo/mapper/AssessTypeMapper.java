package com.she.rsa.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.AssessType;

@Mapper
@Repository("com.she.rsa.baseInfo.mapper.AssessTypeMapper")
public interface AssessTypeMapper {

    /**
     * 평가기법 조회
     * 
     * @param flag
     * @param assessTypeCd
     *            평가기법
     * @return 평가기법 목록
     * @throws Exception
     */
    public List<AssessType> getAssessTypes(@Param("plantCd") String plantCd,
            @Param("assessGroupCd") String assessGroupCd, @Param("assessTypeCd") String assessTypeCd,
            @Param("assessNm") String assessNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가기법 이력 조회
     * 
     * @param assessGroupNo
     *            평가그룹번호
     * @return 평가기법 이력 목록
     * @throws Exception
     */
    public List<AssessType> getAssessTypeHistorys(@Param("assessGroupNo") int assessGroupNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가기법 상세 조회
     * 
     * @param assessTypeNo
     *            평가기법 번호
     * @return 평가기법
     * @throws Exception
     */
    public AssessType getAssessType(@Param("assessTypeNo") String assessTypeNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public List<HashMap<String, Object>> getCheckAssessNm(@Param("plantCd") String plantCd,
            @Param("assessTypeNo") String assessTypeNo, @Param("assessNm") String assessNm,
            @Param("assessTypeCd") String assessTypeCd, @Param("assessGroupNo") int assessGroupNo,
            @Param("revNo") String revNo) throws Exception;

    /**
     * 평가기법 신규등록
     * 
     * @param assessType
     *            평가기법
     * @return 등록 행 수
     * @throws Exception
     */
    public int createAssessType(AssessType assessType) throws Exception;

    /**
     * 평가기법 수정
     * 
     * @param assessType
     *            평가기법
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateAssessType(AssessType assessType) throws Exception;

}
