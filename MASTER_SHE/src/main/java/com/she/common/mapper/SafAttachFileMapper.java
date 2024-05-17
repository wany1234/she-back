package com.she.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.SafAttachFile;

@Mapper
@Repository("com.she.common.mapper.SafAttachFileMapper")
public interface SafAttachFileMapper {
    
    /**
     * she문서 rev 체크
     * @param safAttachFile she 문서정보
     * @return rev 존재 유무
     * @throws Exception
     */
    public int checkSheDocu(SafAttachFile safAttachFile) throws Exception;
    
    /**
     * she문서 생성
     * @param safAttachFile she 문서정보
     * @return she 문서번호
     * @throws Exception
     */
    public int createSafSheDocu(SafAttachFile safAttachFile) throws Exception;
    
    /**
     * she문서 수정
     * @param safAttachFile she 문서정보
     * @return she 문서번호
     * @throws Exception
     */
    public int updateSheDocu(SafAttachFile safAttachFile) throws Exception;
    
    public int createBeforeFile(@Param("newTaskKey") int newTaskKey, @Param("beforFileNo") int beforFileNo) throws Exception;
}
