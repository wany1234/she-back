/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.env.waste.model;

import lombok.Data;

@Data
public class Waste {
    private int ewstWasteNo;

    private String ewstWasteNm;

    private String ewstClassCd;

    private String plantCd;

    private String plantNm;

    private String ewstClassNm;

    private String ewstDispoMtdCd;

    private String ewstDispoMtdNm;

    private String ewstPhaseCd;

    private String sapCd;

    private String ewstPhaseNm;

    private String ewstDispoDivCd;

    private String ewstDispoDivNm;

    private String ewstDispoCyCd;

    private String ewstDispoCyNm;

    private int sortOrder;

    private String useYn;

    private String useYnNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String writerUserNm;

    private String writerDt;

}
