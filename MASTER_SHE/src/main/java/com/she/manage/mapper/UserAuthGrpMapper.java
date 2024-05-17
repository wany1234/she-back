package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.DeptAuthGrp;
import com.she.manage.model.UserAuthGrp;

@Mapper
@Repository("com.she.manage.mapper.UserAuthMapper")
public interface UserAuthGrpMapper {

    public List<UserAuthGrp> getAuthGrpUsers(@Param("authGrpNo") int authGrpNo) throws Exception;

    public List<DeptAuthGrp> getAuthGrpDepts(@Param("authGrpNo") int authGrpNo) throws Exception;

    public DeptAuthGrp getAuth(@Param("userId") String userId) throws Exception;

    public List<DeptAuthGrp> getAuthPlants(@Param("authGrpNo") int authGrpNo) throws Exception;

    public List<DeptAuthGrp> getAuthDepts(@Param("authGrpNo") int authGrpNo) throws Exception;

    public Integer saveDeptAuth(DeptAuthGrp deptAuthGrp) throws Exception;

    public Integer createUserAuthGrp(UserAuthGrp userAuthGrp) throws Exception;

    public Integer saveUserAuth(DeptAuthGrp deptAuthGrp) throws Exception;

    public List<UserAuthGrp> getUserAuthGrps(@Param("userId") String userId, @Param("deptCd") String deptCd) throws Exception;

    public Integer deleteUserAuthGrp(@Param("authGrpNo") String authGrpNo, @Param("userId") String userId) throws Exception;

    public Integer createDeptAuthGrp(DeptAuthGrp deptAuthGrp) throws Exception;

    public Integer deleteDeptAuthGrp(@Param("authGrpNo") String authGrpNo, @Param("deptCd") String deptCd) throws Exception;

    public List<DeptAuthGrp> getAuthGrpSubDepts(@Param("deptCds") String[] deptCds) throws Exception;
}
