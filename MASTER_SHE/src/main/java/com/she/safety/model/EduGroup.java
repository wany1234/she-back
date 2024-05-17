package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EduGroup {

    private int eduGrpNo;

    private String plantCd;

    private String plantNm;

    private String grpNm;

    private String useYn;

    private String sortOrder;

    private String createUserId;

    private String useYnNm;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private List<EduGroupUser> eduGroupUsers;

    private String writerUserNm;

    private String writerDt;

}
