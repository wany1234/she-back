package com.she.safety.baseinfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.DefaultParam;
import com.she.safety.baseinfo.mapper.AuditItemMapper;
import com.she.safety.model.AuditItem;
import com.she.safety.model.AuditStd;

/*
* PSM 감사항목 기능정의
*
*/
@Service
public class AuditItemService {

    @Autowired
    private AuditItemMapper auditItemMapper;

    @Autowired
    private AttachFileMapper attachFileMapper;

    /**
     * PSM 감사기준 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 감사기준 목록
     * @throws Exception
     */
    public List<AuditStd> getAuditStds(HashMap<String, Object> parameter, DefaultParam defaultParam) throws Exception {
        List<AuditStd> auditStds = new ArrayList<AuditStd>();
        auditStds = auditItemMapper.getAuditStds(parameter, defaultParam);
        if (auditStds != null) {
            for (AuditStd audiStd : auditStds) {
                audiStd.setFiles(attachFileMapper.getUploadFiles("AUDIT_STD", String.valueOf(audiStd.getAuditStdNo())));
            }
        }
        return auditStds;
    }

    /**
     * PSM 감사기준 등록
     * 
     * @param auditStd
     *            PSM 감사기준
     * @return PSM 감사기준 키
     */
    @Transactional
    public int createAuditStd(AuditStd auditStd) throws Exception {

        // PSM 감사기준 등록
        auditItemMapper.createAuditStd(auditStd);

        if (auditStd.getAuditItems() != null && auditStd.getAuditItems().size() > 0) {
            for (AuditItem auditItem : auditStd.getAuditItems()) {
                auditItem.setAuditStdNo(auditStd.getAuditStdNo());
                auditItem.setCreateUserId(auditStd.getCreateUserId());
                auditItemMapper.createAuditItem(auditItem);
            }
        }
        return auditStd.getAuditStdNo();
    }

    /**
     * PSM 감사기준 상세 조회
     * 
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return PSM 감사항목
     * @throws Exception
     */
    public AuditStd getAuditStd(int auditStdNo, DefaultParam defaultParam) throws Exception {
        AuditStd auditStd = auditItemMapper.getAuditStd(auditStdNo, defaultParam);

        if (auditStd != null) {
            auditStd.setAuditItems(auditItemMapper.getAuditItems(auditStdNo, defaultParam));
        }

        return auditStd;
    }

    /**
     * PSM 감사기준 수정
     * 
     * @param auditStd
     *            PSM 감사기준
     * @return PSM 감사기준 키
     */
    @Transactional
    public int updateAuditStd(AuditStd auditStd) throws Exception {

        // PSM 감사기준 수정
        auditItemMapper.updateAuditStd(auditStd);

        if (auditStd.getAuditItems() != null && auditStd.getAuditItems().size() > 0) {
            for (AuditItem auditItem : auditStd.getAuditItems()) {
                if (auditItem.getAuditItemNo() > 0) {
                    auditItem.setUpdateUserId(auditStd.getUpdateUserId());
                    auditItemMapper.updateAuditItem(auditItem);
                } else {
                    auditItem.setCreateUserId(auditStd.getUpdateUserId());
                    auditItem.setAuditStdNo(auditStd.getAuditStdNo());
                    auditItemMapper.createAuditItem(auditItem);
                }
            }
        }

        if (auditStd.getDeleteAuditItems() != null && auditStd.getDeleteAuditItems().size() > 0) {
            for (AuditItem auditItem : auditStd.getDeleteAuditItems()) {
                if (auditItem.getAuditItemNo() > 0) {
                    auditItemMapper.deleteAuditItem(auditItem);
                }
            }
        }
        return auditStd.getAuditStdNo();
    }

    /**
     * PSM 감사기준 삭제
     * 
     * @param auditStds
     *            삭제할 PSM 감사기준
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteAuditStd(List<AuditStd> auditStds) throws Exception {
        int resultNo = 0;
        if (auditStds != null && auditStds.size() > 0) {
            for (AuditStd auditStd : auditStds) {
                // 감사항목 삭제
                auditItemMapper.deleteAuditItems(auditStd);
                // PSM 감사기준 삭제
                resultNo += auditItemMapper.deleteAuditStd(auditStd);
            }
        }
        return resultNo;
    }

    /**
     * PSM 감사항목 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 감사항목 목록
     * @throws Exception
     */
    public List<AuditItem> getAuditItemInfos(int auditStdNo, DefaultParam defaultParam) throws Exception {
        return auditItemMapper.getAuditItemInfos(auditStdNo, defaultParam);
    }

    /**
     * PSM 감사기준 사용 확인
     * 
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return PSM 감사기준
     * @throws Exception
     */
    public int getAuditItemStdUseCheck(int auditStdNo, DefaultParam defaultParam) throws Exception {
        return auditItemMapper.getAuditItemStdUseCheck(auditStdNo, defaultParam);
    }

    /**
     * PSM 감사기준 사용 확인
     * 
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return PSM 감사기준
     * @throws Exception
     */
    public List<AuditItem> getAuditItemUseCheck(List<AuditItem> auditItems) throws Exception {
        for (AuditItem auditItem : auditItems) {
            if (auditItem.getAuditItemNo() > 0) {
                int cnt = auditItemMapper.getAuditItemUseCheck(auditItem.getAuditItemNo());
                if (cnt > 0) {
                    auditItem.setAuditUseYn("Y");
                } else {
                    auditItem.setAuditUseYn("N");
                }
            }
        }
        return auditItems;
    }
}
