package com.she.env.water.model;

import java.util.List;

import com.she.env.air.model.Fuel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Discharge {

    private int ewtrDischNo;

    private String ewtrDischNm;

    private String pwrMeterCode;

    private int sortOrder;

    private String useYn;

    private String useYnNm;

    private String ewtrDischClassCd;

    private String ewtrDischClassNm;

    private String remark;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String mgrNum;

    private String plantCd;

    private String plantNm;

    private String[] fuelItem;

    private List<Fuel> fuelItems;

    private String writerUserNm;

    private String writerDt;
}
