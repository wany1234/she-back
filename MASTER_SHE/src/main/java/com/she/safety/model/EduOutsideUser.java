package com.she.safety.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EduOutsideUser {

    private int subconnPsnNo;

    private int safEduMstNo;

    private String vendorNm;

    private String vendorCd;

    private String workerNm;

    private String birthYmd;

    private String genderCd;

    private String genderNm;
}
