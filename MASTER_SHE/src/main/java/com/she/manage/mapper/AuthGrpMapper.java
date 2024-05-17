package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.AuthGrp;
import com.she.manage.model.AuthGrpDept;
import com.she.manage.model.AuthGrpUser;
import com.she.manage.model.DeptAuthGrp;
import com.she.manage.model.UserAuthGrp;

@Mapper
@Repository("com.she.manage.mapper.AuthGrpMapper")
public interface AuthGrpMapper {
    /**
     * 권한 그룹 목록 조회 mapper
     *
     * @param keyword
     *            권한 그룹명/코드 키워드
     * @param useYn
     *            사용여부
     * @return 권한 그룹 목록
     * @throws Exception
     */
    public List<AuthGrp> getAuthGrps(@Param("keyword") String keyword, @Param("useYn") String useYn, @Param("searchFlag") String searchFlag) throws Exception;

    /**
     * 권한그룹 중복검사
     *
     * @param authGrpNm
     * @return 중복 행 수
     * @throws Exception
     */
    public int countAuthGrp(@Param("authGrpNm") String authGrpNm) throws Exception;

    /**
     * 권한 그룹 등록
     *
     * @param authGrp
     *            권한 그룹 모델
     * @return 신규 등록된 권한 코드
     */
    public int createAuthGrp(AuthGrp authGrp) throws Exception;

    /**
     * 권한 그룹 수정
     *
     * @param authGrp
     *            권한 그룹 모델
     * @return 업데이트 수
     * @throws Exception
     */
    public Integer updateAuthGrp(AuthGrp authGrp) throws Exception;

    public List<AuthGrpUser> getAuthGrpUsers(@Param("keyword") String keyword, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    public List<AuthGrp> getAuthGrpUserAuths(@Param("userId") String userId) throws Exception;

    public int deleteAuthGrpUser(UserAuthGrp userAuthGrp) throws Exception;

    public List<AuthGrpDept> getAuthGrpDepts(@Param("keyword") String keyword, @Param("plantCd") String plantCd) throws Exception;

    public List<AuthGrp> getAuthGrpDeptAuths(@Param("deptCd") String deptCd) throws Exception;

    public int deleteAuthGrpDept(DeptAuthGrp deptAuthGrp) throws Exception;

}
