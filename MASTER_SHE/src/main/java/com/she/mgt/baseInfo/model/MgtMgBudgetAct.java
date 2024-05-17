package com.she.mgt.baseInfo.model;

import com.she.manage.model.CodeMaster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "예산계정")
public class MgtMgBudgetAct {
    @ApiModelProperty(value = "예산계정")
    private CodeMaster mgtMgBudgetAct;

    @ApiModelProperty(value = "예산계정 상세목록")
    private List<MgtMgBudgetActMst> mgtMgBudgetActMsts;

    @ApiModelProperty(value = "예산계정 삭제목록")
    private List<MgtMgBudgetActMst> deleteMgtMgBudgetActMsts;
}
