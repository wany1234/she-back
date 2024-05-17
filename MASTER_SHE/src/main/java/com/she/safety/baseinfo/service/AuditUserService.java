package com.she.safety.baseinfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.safety.baseinfo.mapper.AuditUserMapper;
import com.she.safety.model.AuditUser;

/*
* PSM 감사원 기능정의
*
*/
@Service
public class AuditUserService {

    @Autowired
    private AuditUserMapper auditUserMapper;

    /**
     * PSM 감사원 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 감사원 목록
     * @throws Exception
     */
    public List<AuditUser> getAuditUsers(HashMap<String, Object> parameter, DefaultParam defaultParam) throws Exception {
        return auditUserMapper.getAuditUsers(parameter, defaultParam);
    }

    /**
     * PSM 감사원 등록
     * 
     * @param auditUser
     *            PSM 감사원
     * @return PSM 감사원 키
     */
    @Transactional
    public int createAuditUser(AuditUser auditUser) throws Exception {
        auditUserMapper.createAuditUser(auditUser);
        return auditUser.getAuditUserNo();
    }

    /**
     * PSM 감사원 상세 조회
     * 
     * @param auditStdNo
     *            PSM 감사원 번호
     * @return PSM 감사원
     * @throws Exception
     */
    public AuditUser getAuditUser(int auditUserNo, DefaultParam defaultParam) throws Exception {
        return auditUserMapper.getAuditUser(auditUserNo, defaultParam);
    }

    /**
     * PSM 감사원 수정
     * 
     * @param auditUser
     *            PSM 감사원
     * @return PSM 감사원 키
     */
    @Transactional
    public int updateAuditUser(AuditUser auditUser) throws Exception {
        auditUserMapper.updateAuditUser(auditUser);
        return auditUser.getAuditUserNo();
    }

    /**
     * PSM 감사원 삭제
     * 
     * @param auditUsers
     *            삭제할 PSM 감사원
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteAuditUser(List<AuditUser> auditUsers) throws Exception {
        int resultNo = 0;
        if (auditUsers != null && auditUsers.size() > 0) {
            for (AuditUser auditUser : auditUsers) {
                // 감사원 삭제
                resultNo += auditUserMapper.deleteAuditUser(auditUser);
            }
        }
        return resultNo;
    }

    /**
     * PSM 감사원 사용 확인
     * 
     * @param auditStdNo
     *            PSM 감사원 번호
     * @return PSM 감사항목
     * @throws Exception
     */
    public int getAuditUserCheck(int auditUserNo, DefaultParam defaultParam) throws Exception {
        return auditUserMapper.getAuditUserCheck(auditUserNo, defaultParam);
    }
}
