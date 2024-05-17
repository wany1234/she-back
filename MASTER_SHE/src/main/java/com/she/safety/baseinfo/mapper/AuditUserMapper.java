package com.she.safety.baseinfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.AuditUser;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.AuditUserMapper")
public interface AuditUserMapper {

    /**
     * PSM 감사원 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 감사원 목록
     * @throws Exception
     */
    public List<AuditUser> getAuditUsers(@Param("parameter") HashMap<String, Object> parameter, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 감사원 등록
     * 
     * @param auditUser
     *            PSM 감사원
     * @return PSM 감사원 키
     * @throws Exception
     */
    public void createAuditUser(AuditUser auditUser) throws Exception;

    /**
     * PSM 감사원 상세 조회
     * 
     * @param auditUserNo
     *            PSM 감사원 번호
     * @return PSM 감사원
     * @throws Exception
     */
    public AuditUser getAuditUser(@Param("auditUserNo") int auditUserNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 감사원 수정
     * 
     * @param auditUser
     *            PSM 감사원
     * @return PSM 감사원 키
     * @throws Exception
     */
    public void updateAuditUser(AuditUser auditUser) throws Exception;

    /**
     * 감사원 삭제
     * 
     * @param auditUser
     *            감사원
     * @return PSM 감사원 키
     * @throws Exception
     */
    public int deleteAuditUser(AuditUser auditUser) throws Exception;

    /**
     * 감사원 사용 확인
     * 
     * @param auditUserNo
     *            PSM 감사원 번호
     * @return 감사원
     * @throws Exception
     */
    public int getAuditUserCheck(@Param("auditUserNo") int auditUserNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
