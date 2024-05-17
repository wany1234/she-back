package com.she.manage.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.manage.mapper.UserAuthGrpMapper;
import com.she.manage.model.DeptAuthGrp;
import com.she.manage.model.DeptAuthGrps;
import com.she.manage.model.UserAuthGrp;
import com.she.manage.model.UserAuthGrps;

@Service
/**
 * 사용자 권한 그룹 처리 서비스
 */
public class UserAuthGrpService {
    @Autowired
    UserAuthGrpMapper userAuthGrpMapper;

    // 사용자 메뉴 서비스
    @Autowired
    UserMenuService userMenuService;

    /**
     * 권한에 따른 사용자/부서 정보 조회
     * 
     * @param authGrpNo
     *            권한번호
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> getAuthGrpUserDepts(int authGrpNo) throws Exception {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // 권한에 따른 사용자정보 조회
        data.put("user", userAuthGrpMapper.getAuthGrpUsers(authGrpNo));
        // 권한에 따른 부서정보 조회
        data.put("dept", userAuthGrpMapper.getAuthGrpDepts(authGrpNo));
        return data;
    }

    /**
     * 사업장/부서 접근 권한에 따른 부서 조회
     * 
     * @param authGrpNo
     *            권한번호
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> getAuthGrpPlantDepts(int authGrpNo) throws Exception {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // 권한에 따른 사용자정보 조회
        data.put("plant", userAuthGrpMapper.getAuthPlants(authGrpNo));
        // 권한에 따른 부서정보 조회
        data.put("dept", userAuthGrpMapper.getAuthDepts(authGrpNo));
        return data;
    }

    /**
     * 사업장/부서 접근 권한에 따른 부서 조회
     * 
     * @param authGrpNo
     *            권한번호
     * @return
     * @throws Exception
     */
    public DeptAuthGrp getAuth(String userId) throws Exception {
        return userAuthGrpMapper.getAuth(userId);
    }

    /**
     * 사용자 권한 그룹 생성 (부서)
     * 
     * @param deptAuthGrps
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer createDeptAuth(DeptAuthGrps deptAuthGrps) throws Exception {
        Integer count = 0;
        for (DeptAuthGrp deptAuthGrp : deptAuthGrps.getDeptAuthGrps()) {
            count += userAuthGrpMapper.saveDeptAuth(deptAuthGrp);
        }

        return count;
    }

    /**
     * 사용자 권한 그룹 삭제 (부서, 사용자)
     * 
     * @param deptAuthGrps
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer deleteDeptAuth(List<DeptAuthGrp> deptAuthGrps) throws Exception {
        Integer count = 0;
        if (deptAuthGrps != null && deptAuthGrps.size() > 0) {
            for (DeptAuthGrp deptAuthGrp : deptAuthGrps) {
                if (deptAuthGrp.getFlag().equals("p")) {
                    deptAuthGrp.setPlantAccessYn("N");
                } else {
                    deptAuthGrp.setDeptAccessYn("N");
                    deptAuthGrp.setPlantAccessYn("N");
                }
                if (deptAuthGrp.getUserId().equals("") || deptAuthGrp.getUserId() == null) {
                    count += userAuthGrpMapper.saveDeptAuth(deptAuthGrp);
                } else {
                    count += userAuthGrpMapper.saveUserAuth(deptAuthGrp);
                }
            }
        }

        return count;
    }

    /**
     * 사용자 권한 그룹 생성 (사용자)
     * 
     * @param deptAuthGrps
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer createUserAuth(DeptAuthGrps deptAuthGrps) throws Exception {
        Integer count = 0;
        for (DeptAuthGrp deptAuthGrp : deptAuthGrps.getDeptAuthGrps()) {
            count += userAuthGrpMapper.saveUserAuth(deptAuthGrp);
        }

        return count;
    }

    /**
     * 사용자 권한 그룹 생성
     * 
     * @param userAuthGrps
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer createUserAuthGrp(UserAuthGrps userAuthGrps) throws Exception {
        Integer count = 0;
        for (UserAuthGrp userAuthGrp : userAuthGrps.getUserAuthGrps()) {
            // 1. 기존 사용자 권한 그룹 삭제
            count += userAuthGrpMapper.createUserAuthGrp(userAuthGrp);
        }

        return count;
    }

    /**
     * 사용자 권한 그룹 삭제
     * 
     * @param userAuthGrps
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer deleteUserAuthGrp(List<UserAuthGrp> userAuthGrps) throws Exception {
        Integer count = 0;
        for (UserAuthGrp userAuthGrp : userAuthGrps) {
            // 1. 기존 사용자 권한 그룹 삭제
            count += userAuthGrpMapper.deleteUserAuthGrp(userAuthGrp.getAuthGrpNo(), userAuthGrp.getUserId());
        }

        return count;
    }

    /**
     * 사용자/부서별 권한 그룹 조회
     * 
     * @param userId
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<UserAuthGrp> getUserAuthGrps(String userId, String deptCd) throws Exception {
        return userAuthGrpMapper.getUserAuthGrps(userId, deptCd);
    }

    /**
     * 부서 권한 그룹 생성
     * 
     * @param deptAuthGrps
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer createDeptAuthGrp(DeptAuthGrps deptAuthGrps) throws Exception {
        Integer count = 0;
        for (DeptAuthGrp deptAuthGrp : deptAuthGrps.getDeptAuthGrps()) {
            // 1. 권한 그룹 생성
            count += userAuthGrpMapper.createDeptAuthGrp(deptAuthGrp);
        }

        return count;
    }

    /**
     * 부서 권한 그룹 삭제
     * 
     * @param deptAuthGrps
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer deleteDeptAuthGrpAll(List<DeptAuthGrp> deptAuthGrps) throws Exception {
        Integer count = 0;
        for (DeptAuthGrp deptAuthGrp : deptAuthGrps) {
            // 1. 기존 부서 권한 그룹 삭제
            count += userAuthGrpMapper.deleteDeptAuthGrp(deptAuthGrp.getAuthGrpNo(), deptAuthGrp.getDeptCd());
        }

        return count;
    }

    // /**
    // * 부서에 부여된 권한 일괄 삭제
    // *
    // * @param userIds
    // * @return
    // * @throws Exception
    // */
    // public Integer deleteDeptAuthGrp(List<String> deptCds) throws Exception {
    // Integer count = 0;
    // for (String deptCd : deptCds) {
    // count += userAuthGrpMapper.deleteDeptAuthGrp(deptCd);
    // }
    // return count;
    // }

    /**
     * 사업장/부서 접근 권한에 따른 부서 제외시 하위부서 조회
     * 
     * @param parameter
     * @return 사업장/부서 접근 권한에 따른 부서
     */
    public List<DeptAuthGrp> getAuthGrpSubDepts(String[] deptCds) throws Exception {
        return userAuthGrpMapper.getAuthGrpSubDepts(deptCds);
    }
}
