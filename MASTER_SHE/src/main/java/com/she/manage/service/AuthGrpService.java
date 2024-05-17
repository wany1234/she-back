package com.she.manage.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.AuthGrpMapper;
import com.she.manage.mapper.UserAuthGrpMapper;
import com.she.manage.model.AuthGrp;
import com.she.manage.model.AuthGrpDept;
import com.she.manage.model.AuthGrpUser;
import com.she.manage.model.DeptAuthGrp;
import com.she.manage.model.UserAuthGrp;

@Service
public class AuthGrpService {
    @Autowired
    AuthGrpMapper authGrpMapper;
    @Autowired
    UserAuthGrpMapper userAuthGrpMapper;

    /**
     * 권한 그룹 목록 조회 서비스
     *
     * @param keyword
     *            권한 그룹명/코드 키워드
     * @param useYn
     *            사용여부
     * @return 권한 그룹 목록
     * @throws Exception
     */
    public List<AuthGrp> getAuthGrps(String keyword, String useYn, String searchFlag) throws Exception {
        return authGrpMapper.getAuthGrps(keyword, useYn, searchFlag);
    }

    /**
     * 권한그룹 중복검사
     *
     * @param authGrpNm
     * @return 중복 행 수
     * @throws Exception
     */
    public int countAuthGrp(String authGrpNm) throws Exception {
        return this.authGrpMapper.countAuthGrp(authGrpNm);
    }

    /**
     * 권한 그룹 등록
     *
     * @param authGrp
     *            권한 그룹 모델
     * @return 신규 등록된 권한 코드
     */
    public int createAuthGrp(AuthGrp authGrp) throws Exception {
        return authGrpMapper.createAuthGrp(authGrp) > 0 ? authGrp.getAuthGrpNo() : 0;
    }

    /**
     * 권한 그룹 수정
     *
     * @param authGrp
     *            권한 그룹 모델
     * @return 업데이트 수
     * @throws Exception
     */
    public Integer updateAuthGrp(AuthGrp authGrp) throws Exception {
        return authGrpMapper.updateAuthGrp(authGrp);
    }

    public List<AuthGrpUser> getAuthGrpUsers(String keyword, String plantCd, String deptCd) throws Exception {
        List<AuthGrpUser> authGrpUsers = authGrpMapper.getAuthGrpUsers(keyword, plantCd, deptCd);
        if (authGrpUsers != null && authGrpUsers.size() > 0) {
            for (AuthGrpUser authGrpUser : authGrpUsers) {
                authGrpUser.setAuthGrps(authGrpMapper.getAuthGrpUserAuths(authGrpUser.getUserId()));
            }
        }
        return authGrpUsers;
    }

    public int createAuthGrpUser(AuthGrpUser authGrpUser) throws Exception {
        int result = 0;
        if (authGrpUser != null && CollectionUtils.isNotEmpty(authGrpUser.getAuthGrps())) {
            for (AuthGrp authGrp : authGrpUser.getAuthGrps()) {
                UserAuthGrp userAuthGrp = new UserAuthGrp();
                userAuthGrp.setUserId(authGrpUser.getUserId());
                userAuthGrp.setAuthGrpNo(String.valueOf(authGrp.getAuthGrpNo()));
                userAuthGrp.setCreateUserId(authGrp.getCreateUserId());
                result += userAuthGrpMapper.createUserAuthGrp(userAuthGrp);
            }
        }
        return result;
    }

    public int deleteAuthGrpUser(UserAuthGrp userAuthGrp) throws Exception {
        return authGrpMapper.deleteAuthGrpUser(userAuthGrp);
    }

    public List<AuthGrpDept> getAuthGrpDepts(String keyword, String plantCd) throws Exception {
        List<AuthGrpDept> authGrpDepts = authGrpMapper.getAuthGrpDepts(keyword, plantCd);
        if (authGrpDepts != null && authGrpDepts.size() > 0) {
            for (AuthGrpDept authGrpDept : authGrpDepts) {
                authGrpDept.setAuthGrps(authGrpMapper.getAuthGrpDeptAuths(authGrpDept.getDeptCd()));
            }
        }
        return authGrpDepts;
    }

    public int createAuthGrpDept(AuthGrpDept authGrpDept) throws Exception {
        int result = 0;
        if (authGrpDept != null && CollectionUtils.isNotEmpty(authGrpDept.getAuthGrps())) {
            for (AuthGrp authGrp : authGrpDept.getAuthGrps()) {
                DeptAuthGrp deptAuthGrp = new DeptAuthGrp();
                deptAuthGrp.setDeptCd(authGrpDept.getDeptCd());
                deptAuthGrp.setAuthGrpNo(String.valueOf(authGrp.getAuthGrpNo()));
                deptAuthGrp.setCreateUserId(authGrp.getCreateUserId());
                result += userAuthGrpMapper.createDeptAuthGrp(deptAuthGrp);
            }
        }
        return result;
    }

    public int deleteAuthGrpDept(DeptAuthGrp deptAuthGrp) throws Exception {
        return authGrpMapper.deleteAuthGrpDept(deptAuthGrp);
    }

}
