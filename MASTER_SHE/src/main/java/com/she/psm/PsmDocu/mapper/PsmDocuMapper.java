package com.she.psm.PsmDocu.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.manage.model.CodeMaster;
import com.she.psm.model.DocuGrpNo;
import com.she.psm.model.PsmDocu;

@Mapper
@Repository("com.she.psm.PsmDocu.mapper.PsmDocuMapper")
public interface PsmDocuMapper {
    /**
     * 공정안전문서 목록 조회
     */
    public List<PsmDocu> getPsmDocuments(@Param("plantCd") String plantCd, @Param("categoryCd") String categoryCd, @Param("deptCd") String deptCd, @Param("docuTitle") String docuTitle, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("subCategoryCd") String subCategoryCd, @Param("useYn") String useYn,
            @Param("checkEffectYn") String checkEffectYn, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공정안전문서 목록 첨부파일 조회
     */
    public List<AttachFile> getPsmDocumentsFileList(@Param("taskKey") String taskKey) throws Exception;

    /**
     * PSM Portlet 목록 조회
     */
    public List<PsmDocu> getPsmPortletList(@Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * sub Category 목록 조회
     */
    public List<CodeMaster> getSubCategory(@Param("code") String code, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공정안전문서 상세 조회
     */
    public PsmDocu getPsmDocument(@Param("psmDocuNo") int psmDocuNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공정안전문서 개정이력 조회
     */
    public List<PsmDocu> getRevisionPsmDocument(@Param("docuId") String docuId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * sub Category 문서그룹번호 조회
     */
    public List<DocuGrpNo> getDocuGrpNoList() throws Exception;

    /**
     * 공정안전문서 생성
     */
    public int createPsmDocument(PsmDocu psmDocu) throws Exception;

    /**
     * 공정안전문서 개정이력 생성
     */
    public int revisionPsmDocument(PsmDocu psmDocu) throws Exception;

    /**
     * 공정안전문서 수정
     */
    public int updatePsmDocument(PsmDocu PsmDocu) throws Exception;

    /**
     * 공정안전문서 개정
     */
    public int renewalPsmDocument(PsmDocu PsmDocu) throws Exception;

    /**
     * 공정안전문서 결재
     */
    public int updatePsmDocumentForAppr(@Param("psmDocuNo") int psmDocuNo, @Param("apprRqstNo") int apprRqstNo, @Param("stepCd") String stepCd) throws Exception;

    /**
     * 공정안전문서 삭제
     */
    public int deletePsmDocument(@Param("psmDocuNo") int psmDocuNo, @Param("delYn") String delYn) throws Exception;

    /**
     * 부서코드로 상위,하위 부서코드 구하기
     *
     * @param depdCd
     *            부서코드
     *
     * @return 직계부서코드 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getDeptHierarchyList(@Param("deptCd") String deptCd) throws Exception;

}
