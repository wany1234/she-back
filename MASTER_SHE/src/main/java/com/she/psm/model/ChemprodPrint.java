package com.she.psm.model;

import com.she.chm.model.Chemprod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

@ApiModel(description = "취급자재")
@Data
public class ChemprodPrint {

    private String chemProdNmEn;

    private String chemProdNmKr;

    private int chemProdNo;

    @ApiModelProperty(value = "PSM-폭발한계상한")
    private String explUpLimit;

    @ApiModelProperty(value = "PSM-폭발한계하한")
    private String explLowLimit;

    @ApiModelProperty(value = "PSM-노출기준")
    private String leakThresh;

    @ApiModelProperty(value = "PSM-독성치")
    private String lc50;

    @ApiModelProperty(value = "PSM-인화점")
    private String flashingPnt;

    @ApiModelProperty(value = "PSM-발화점")
    private String ignitionPnt;

    @ApiModelProperty(value = "PSM-증기압")
    private String vaporPressure;

    @ApiModelProperty(value = "PSM-이상반응유무")
    private String extraReactionYn;

    @ApiModelProperty(value = "PSM-이상반응유무명")
    private String extraReactionYnNm;

    @ApiModelProperty(value = "PSM-부식성유무")
    private String corrosivenessYn;

    @ApiModelProperty(value = "PSM-부식성유무명")
    private String corrosivenessYnNm;

    @ApiModelProperty(value = "일일취급량")
    private String dailyVol;

    @ApiModelProperty(value = "저장량")
    private String maxVol;

    @ApiModelProperty(value = "비고")
    private String remark;

    private List<Chemprod> chemProdList;

    private JRDataSource chemList;
}
