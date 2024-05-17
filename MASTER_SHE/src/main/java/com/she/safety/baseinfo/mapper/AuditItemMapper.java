package com.she.safety.baseinfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.AuditItem;
import com.she.safety.model.AuditStd;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.AuditItemMapper")
public interface AuditItemMapper {

    /**
     * PSM 감사기준 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 감사기준 목록
     * @throws Exception
     */
    public List<AuditStd> getAuditStds(@Param("parameter") HashMap<String, Object> parameter, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 감사기준 등록
     *
     * @param auditStd
     *            PSM 감사기준
     * @return PSM 감사기준 키
     * @throws Exception
     */
    public void createAuditStd(AuditStd auditStd) throws Exception;

    /**
     * 감사항목 등록
     *
     * @param auditItem
     *            감사항목
     * @return 감사항목 키
     * @throws Exception
     */
    public void createAuditItem(AuditItem auditItem) throws Exception;

    /**
     * PSM 감사기준 상세 조회
     *
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return PSM 감사항목
     * @throws Exception
     */
    public AuditStd getAuditStd(@Param("auditStdNo") int auditStdNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 감사항목 조회
     *
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return 감사항목
     * @throws Exception
     */
    public List<AuditItem> getAuditItems(@Param("auditStdNo") int auditStdNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 감사기준 수정
     *
     * @param auditStd
     *            PSM 감사기준
     * @return PSM 감사기준 키
     * @throws Exception
     */
    public void updateAuditStd(AuditStd auditStd) throws Exception;

    /**
     * 감사항목 수정
     *
     * @param auditItem
     *            감사항목
     * @return 감사항목 키
     * @throws Exception
     */
    public int updateAuditItem(AuditItem auditItem) throws Exception;

    /**
     * 감사항목 삭제
     *
     * @param auditItem
     *            감사항목
     * @return 점검이력 키
     * @throws Exception
     */
    public int deleteAuditItem(AuditItem auditItem) throws Exception;

    /**
     * 감사항목 삭제
     *
     * @param auditStd
     *            PSM 감사기준
     * @return 감사항목 키
     * @throws Exception
     */
    public int deleteAuditItems(AuditStd auditStd) throws Exception;

    /**
     * PSM 감사기준 삭제
     *
     * @param auditStd
     *            PSM 감사기준
     * @return 감사기준 키
     * @throws Exception
     */
    public int deleteAuditStd(AuditStd auditStd) throws Exception;

    /**
     * PSM 감사항목 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 감사항목 목록
     * @throws Exception
     */
    public List<AuditItem> getAuditItemInfos(@Param("auditStdNo") int auditStdNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 감사기준 사용 확인
     *
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return 감사기준
     * @throws Exception
     */
    public int getAuditItemStdUseCheck(@Param("auditStdNo") int auditStdNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 감사항목 사용 확인
     *
     * @param auditItemNo
     *            PSM 감사항목 번호
     * @return 감사항목
     * @throws Exception
     */
    public int getAuditItemUseCheck(@Param("auditItemNo") int auditItemNo) throws Exception;

}
