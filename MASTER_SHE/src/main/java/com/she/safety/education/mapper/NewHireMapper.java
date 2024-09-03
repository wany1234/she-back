package com.she.safety.education.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.NewHire;

@Mapper
@Repository("com.she.safety.education.mapper.NewHireMapper")
public interface NewHireMapper {

    /**
     * 신규채용직원 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 신규채용직원 목록
     * @throws Exception
     *             예외
     */
    public List<NewHire> getNewHires(@Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("userNm") String userNm, @Param("plantCd") String plantCd, @Param("employmentYn") String employmentYn, @Param("eduCompletYn") String eduCompletYn, @Param("checkupCompletYn") String checkupCompletYn, @Param("startYmd") String startYmd,
            @Param("endYmd") String endYmd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 신규채용직원 상세조회
     *
     * @param userId
     *            사용자아이디
     * @return 신규채용직원 상세조회
     * @throws Exception
     *             예외
     */
    public NewHire getNewHire(@Param("userId") String userId) throws Exception;

    /**
     * 신규채용 교육목록 조회
     * 
     * @return 신규채용 교육목록
     * @throws Exception
     *             예외
     */
    public List<NewHire> getNewHireEduList(@Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public int getCheckEduComplete(@Param("safEduMstNo") int safEduMstNo, @Param("userId") String userId) throws Exception;

    /**
     * 신규채용 교육이수여부 확인
     * 
     * @param userId
     *            사용자아이디
     * @return 신규채용 교육이수여부 확인
     * @throws Exception
     *             예외
     */
    public int getCheckNewHireEduComplete(@Param("userId") String userId) throws Exception;

    /**
     * 신규채용 교육 이수자 등록
     * 
     * @param List<NewHire>
     *            신규채용직원 목록
     * @return 신규채용 교육 이수자 등록
     * @throws Exception
     *             예외
     */
    public int createNewHireEduUser(NewHire newHire) throws Exception;

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
    public int getChkCheckupUser(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId) throws Exception;

    /**
     * 신규채용 건강검진 대상자 등록 확인
     * 
     * @param userId
     *            사용자아이디
     * @return 신규채용 건강검진 대상자 등록 확인
     * @throws Exception
     *             예외
     */
    public int getChkNewHireCheckupUser(@Param("userId") String userId) throws Exception;

}
