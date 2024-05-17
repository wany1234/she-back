package com.she.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.AttachSettingMapper;
import com.she.manage.model.AttachSetting;

@Service
public class AttachSettingService {
    @Autowired
    private AttachSettingMapper attachSettingMapper;

    /**
     * 첨부파일 설정 목록조회
     * 
     * @param taskClassNm
     * @param taskClassDesc
     * @return
     * @throws Exception
     */
    public List<AttachSetting> getAttachSettings(String taskClassNm, String taskClassDesc) throws Exception {
        return this.attachSettingMapper.getAttachSettings(taskClassNm, taskClassDesc);
    }

    /**
     * 첨부파일 설정 상세조회
     * 
     * @param taskClassNm
     * @return
     * @throws Exception
     */
    @Cacheable(cacheNames = "UploadFileSettingCashe", key = "'TASKCLASSNM' + #taskClassNm")
    public AttachSetting getAttachSetting(String taskClassNm) throws Exception {
        return this.attachSettingMapper.getAttachSetting(taskClassNm);
    }

    /**
     * 첨부파일 설정 등록
     * 
     * @param attachSetting
     * @return
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "UploadFileSettingCashe" }, allEntries = true)
    public int createAttachSetting(AttachSetting attachSetting) throws Exception {
        return this.attachSettingMapper.createAttachSetting(attachSetting);
    }

    /**
     * 첨부파일 설정 수정
     * 
     * @param attachSetting
     * @return
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "UploadFileSettingCashe" }, allEntries = true)
    public int updateAttachSetting(AttachSetting attachSetting) throws Exception {
        return this.attachSettingMapper.updateAttachSetting(attachSetting);
    }

    /**
     * 첨부파일 구분명 중복체크
     * 
     * @param taskClassNm
     * @return
     * @throws Exception
     */
    public boolean checkDuplicateTaskClassNm(String taskClassNm) throws Exception {
        AttachSetting attachSetting = this.attachSettingMapper.getAttachSetting(taskClassNm);
        if (attachSetting == null || attachSetting.getTaskClassNm() == null || attachSetting.getTaskClassNm().equals("")) {
            return false;
        } else {
            return true;
        }
    }

}
