package com.she.common.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.model.Diagram;
import com.she.common.model.SafAttachFile;

@Mapper
@Repository("com.she.common.mapper.AttachFileMapper")
public interface AttachFileMapper {

    /**
     * 신규 첨부파일 아이디 생성
     * 
     * @return
     * @throws Exception
     */
    HashMap<String, Object> getNewAttachFileId() throws Exception;

    /**
     * 미리업로드된 파일 적용
     * 
     * @param attachFileGrp
     * @return
     * @throws Exception
     */
    int applyUploadFiles(AttachFileGrp attachFileGrp) throws Exception;

    /**
     * 미리업로드된 파일 적용
     * 
     * @param attachFileGrp
     * @return
     * @throws Exception
     */
    int applyUploadSafFiles(AttachFileGrp attachFileGrp) throws Exception;

    /**
     * 첨부파일 정보 등록
     * 
     * @param attachFile
     * @return
     * @throws Exception
     */
    int createAttachFile(AttachFile attachFile) throws Exception;

    /**
     * PSM 첨부파일 정보 등록
     * 
     * @param attachFile
     * @return
     * @throws Exception
     */
    int createSafAttachFile(AttachFile attachFile) throws Exception;

    /**
     * 업무그룹별 업로드 파일 조회
     * 
     * @param taskClassNm
     * @param taskKey
     * @return
     */
    List<AttachFile> getUploadFiles(@Param("taskClassNm") String taskClassNm, @Param("taskKey") String taskKey) throws Exception;

    /**
     * PSM 업무그룹별 업로드 파일 조회
     * 
     * @param taskClassNm
     * @param taskKey
     * @return
     */
    List<AttachFile> getSafUploadFiles(@Param("taskClassNm") String taskClassNm, @Param("taskKey") String taskKey) throws Exception;

    /**
     * 첨부파일아이디를 이용한 파일 조회
     * 
     * @param attachFileId
     * @return
     * @throws Exception
     */
    List<AttachFile> getUploadFilesById(@Param("attachFileId") String attachFileId) throws Exception;

    /**
     * PSM 첨부파일아이디를 이용한 파일 조회
     * 
     * @param attachFileId
     * @return
     * @throws Exception
     */
    List<AttachFile> getSafUploadFilesById(@Param("attachFileId") String attachFileId) throws Exception;

    /**
     * PSM 업무그룹별 업로드 파일 조회
     * 
     * @param taskClassNm
     * @param taskKey
     * @return
     */
    List<SafAttachFile> getSafAttachFileList(@Param("taskClassNm") String taskClassNm, @Param("docuKindCd") String docuKindCd, @Param("taskKey") String taskKey, @Param("taskFlag") String taskFlag, @Param("plantCd") String plantCd, @Param("docuTitle") String docuTitle, @Param("refTableId") String refTableId, @Param("mgtEditable") String mgtEditable,
            @Param("processCd") String processCd) throws Exception;

    /**
     * 도면 파일 List 조회
     * 
     * @param taskClassNm
     * @param taskKey
     * @return
     */
    List<Diagram> getDiagramList(@Param("taskClassNm") String taskClassNm, @Param("diagClassCd") String diagClassCd, @Param("taskKey") String taskKey, @Param("taskFlag") String taskFlag, @Param("plantCd") String plantCd, @Param("diagTitle") String diagTitle, @Param("processCd") String processCd, @Param("chngNum") String chngNum) throws Exception;

    /**
     * 단일 파일 조회
     * 
     * @param fileNo
     * @return
     * @throws Exception
     */
    AttachFile getUploadFile(String fileNo) throws Exception;

    /**
     * 단일 파일 조회
     *
     * @param fileNo
     * @return
     * @throws Exception
     */
    AttachFile getSafFile(@Param("fileNo") String fileNo) throws Exception;

    /**
     * 단일 파일 조회(개선 조치 첨부파일 제일 처음꺼)
     * 
     * @param taskClassNm
     * @param taskKey
     * @return
     * @throws Exception
     */
    AttachFile getFirstUploadFile(@Param("taskClassNm") String taskClassNm, @Param("taskKey") String taskKey) throws Exception;

    /**
     * 단일 파일 조회
     * 
     * @param fileNo
     * @return
     * @throws Exception
     */
    AttachFile getSafUploadFile(@Param("fileNo") String fileNo, @Param("taskClassNm") String taskClassNm) throws Exception;

    /**
     * PSM 단일 파일 조회
     * 
     * @param fileNo
     * @return
     * @throws Exception
     */
    AttachFile getSafUploadFile(String fileNo) throws Exception;

    /**
     * 단일 파일 삭제
     * 
     * @param fileNo
     * @return
     * @throws Exception
     */
    int deleteFile(String fileNo) throws Exception;

    /**
     * 여러 파일 삭제
     * 
     * @param fileNo
     * @return
     * @throws Exception
     */
    int deleteFilesAll(@Param("taskClassNm") String taskClassNm, @Param("taskKey") String taskKey) throws Exception;

    /**
     * 단일 파일 삭제
     * 
     * @param fileNo
     * @return
     * @throws Exception
     */
    int safDeleteFile(String fileNo) throws Exception;

    /**
     * 업무별 파일 경로 조회
     * 
     * @param code
     * @return
     * @throws Exception
     */
    public String getUploadFilePath(@Param("code") String code) throws Exception;

    /**
     * 이전버전 파일 리스트 조회
     * 
     * @param taskClassNm
     *            파일 구분자
     * @param taskKey
     *            파일 키값
     * @return
     * @throws Exception
     */
    List<AttachFile> getFileList(@Param("taskClassNm") String taskClassNm, @Param("taskKey") String taskKey) throws Exception;

    public int editExplain(AttachFile attachFile) throws Exception;
}
