package com.she.safety.education.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.EduGroup;
import com.she.safety.model.EduGroupUser;

@Mapper
@Repository("com.she.safety.education.mapper.EduGroupMapper")
public interface EduGroupMapper {

    /**
     * 교육그룹결과목록
     * 
     * @param groupNm
     *            그룹명
     * @param plantCd
     *            사업장
     * @param useYn
     *            사용여부
     * 
     * @return 교육그룹결과목록
     * @throws Exception
     *             예외
     */
    public List<EduGroup> getEduGroups(@Param("groupNm") String groupNm, @Param("plantCd") String plantCd, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육그룹결과조회
     * 
     * @param eduGrpNo
     *            그룹번호
     * 
     * 
     * @return 교육그룹결과
     * @throws Exception
     *             예외
     */
    public EduGroup getEduGroup(@Param("eduGrpNo") int eduGrpNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육그룹결과 등록
     * 
     * @param eduGroup
     *            교육그룹결과
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int createEduGroup(EduGroup eduGroup) throws Exception;

    /**
     * 교육그룹결과 수정
     * 
     * @param eduGroup
     *            교육그룹결과
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateEduGroup(EduGroup eduGroup) throws Exception;

    /**
     * 교육그룹결과 삭제
     * 
     * @param eduGroup
     *            교육그룹결과 번호
     * 
     * @return 교육그룹결과목록
     * @throws Exception
     *             예외
     */
    public int deleteEduGroup(@Param("eduGrpNo") int eduGrpNo) throws Exception;

    /**
     * 교육그룹결과 사용자목록
     * 
     * @param eduGroup
     *            교육그룹결과 번호
     * 
     * @return 교육그룹결과 사용자목록
     * @throws Exception
     *             예외
     */
    public List<EduGroupUser> getEduGroupUsers(@Param("eduGrpNo") int eduGrpNo) throws Exception;

    /**
     * 교육그룹결과 사용자목록 수정
     * 
     * @param eduGroup
     *            교육그룹결과 사용자목록
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int createEduGroupUsers(EduGroupUser eduGroupUser) throws Exception;

    /**
     * 교육그룹결과 사용자목록 삭제
     * 
     * @param eduGrpNo
     *            교육그룹결과 번호
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int deleteEduGroupUsers(@Param("eduGrpNo") int eduGrpNo) throws Exception;
}
