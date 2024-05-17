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
package com.she.env.waste.operationStatus.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.waste.model.WesteOperationStatus;
import com.she.env.waste.operationStatus.mapper.WstGenStatusMapper;

/**
 * 폐기물 관리대장 기능정의
 *
 */
@Service("wstGenStatusService")
public class WstGenStatusService {

    @Autowired
    private WstGenStatusMapper wstGenStatusMapper;

    public List<WesteOperationStatus> getOperationLogAmgGens(String plantCd, String measureYear, String ewstClassCd, String ewstWasteNo, String deptCd, DefaultParam defaultParam) throws Exception {
        return this.wstGenStatusMapper.getOperationLogAmgGens(plantCd, measureYear, ewstClassCd, ewstWasteNo, deptCd, defaultParam);
    }

}
