package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.AttachSetting;

@Mapper
@Repository("com.she.manage.mapper.AttachSettingMapper")
public interface AttachSettingMapper {

    /**
     * 첨부파일 설정 목록조회
     * 
     * @param taskClassNm
     * @param taskClassDesc
     * @return
     * @throws Exception
     */
    public List<AttachSetting> getAttachSettings(@Param("taskClassNm") String taskClassNm, @Param("taskClassDesc") String taskClassDesc) throws Exception;

    /**
     * 첨부파일 설정 상세조회
     * 
     * @param taskClassNm
     * @return
     * @throws Exception
     */
    public AttachSetting getAttachSetting(@Param("taskClassNm") String taskClassNm) throws Exception;

    /**
     * 첨부파일 설정 등록
     * 
     * @param attachSetting
     * @return
     * @throws Exception
     */
    public int createAttachSetting(AttachSetting attachSetting) throws Exception;

    /**
     * 첨부파일 설정 수정
     * 
     * @param attachSetting
     * @return
     * @throws Exception
     */
    public int updateAttachSetting(AttachSetting attachSetting) throws Exception;

}
