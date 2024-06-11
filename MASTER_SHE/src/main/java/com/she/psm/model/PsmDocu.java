package com.she.psm.model;

import com.she.common.model.AttachFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "Country & Site")
@Data
public class PsmDocu {
    @ApiModelProperty(value = "PSM문서번호")
    private int psmDocuNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "첨부파일개수")
    private int fileCnt;

    @ApiModelProperty(value = "카테고리코드")
    private String categoryCd;

    @ApiModelProperty(value = "카테고리코드명")
    private String categoryNm;

    @ApiModelProperty(value = "카테고리문서제목")
    private String categoryDocuTitle;

    @ApiModelProperty(value = "서브카테고리코드")
    private String subCategoryCd;

    @ApiModelProperty(value = "서브카테고리코드명")
    private String subCategoryNm;

    @ApiModelProperty(value = "문서작성일")
    private String docWriteDt;

    @ApiModelProperty(value = "최근검토일")
    private String lastCheckDt;

    @ApiModelProperty(value = "차기검토일")
    private String nextCheckDt;

    @ApiModelProperty(value = "관리부서")
    private String deptCd;

    @ApiModelProperty(value = "관리부서명")
    private String deptNm;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "문서Id")
    private String docuId;

    @ApiModelProperty(value = "문서그룹번호")
    private String docuGrpNo;

    @ApiModelProperty(value = "시행일자")
    private int enfYmd;

    @ApiModelProperty(value = "문서제목")
    private String docuTitle;

    @ApiModelProperty(value = "개정번호")
    private String revNum;

    @ApiModelProperty(value = "개정일자")
    private String revYmd;

    @ApiModelProperty(value = "문서내용")
    private String docuContents;

    @ApiModelProperty(value = "개정내용")
    private String revContents;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "삭제여부")
    private String delYn;

    @ApiModelProperty(value = "참조테이블ID")
    private String refTableId;

    @ApiModelProperty(value = "문서진행상태코드")
    private String stepCd;

    @ApiModelProperty(value = "문서진행상태코드명")
    private String stepNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "결재요청번호")
    private String docId;

    @ApiModelProperty(value = "유효성여부")
    private String effectiveYn;

    @ApiModelProperty(value = "조치담당자ID")
    private String actMgrId;

    @ApiModelProperty(value = "조치담당자명")
    private String actMgrNm;

    @ApiModelProperty(value = "조치담당자이메일")
    private String actEmail;

    @ApiModelProperty(value = "조치이메일전송여부")
    private String actEmailSendYn;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "최종수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "최종수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "결재진행상태명")
    private String apprStep;

    @ApiModelProperty(value = "결재진행상태")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행상태명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "첨부파일ID")
    private String taskKey;

    @ApiModelProperty(value = "문서그룹번호리스트")
    private List<DocuGrpNo> docuGrpNoList;

    @ApiModelProperty(value = "첨부파일목록")
    private List<AttachFile> files;
}
