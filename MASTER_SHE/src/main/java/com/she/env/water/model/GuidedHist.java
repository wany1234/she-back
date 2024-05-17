package com.she.env.water.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuidedHist {
    private int ewtrGuidedHistNo;

    private int ewtrCleanFacNo;
    
    private String measureYmd;

    private String regulator;

    private String regulatorWorker;

    private String violation;

    private String action;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

}
