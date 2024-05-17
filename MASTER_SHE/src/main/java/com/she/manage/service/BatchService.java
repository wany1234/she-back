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
package com.she.manage.service;

import com.she.common.model.DefaultParam;
import com.she.manage.mapper.BatchMapper;
import com.she.manage.model.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {
    @Autowired
    private BatchMapper batchMapper;

    public List<Batch> getBatchs(String batchCd, String batchNm, String useYn, DefaultParam defaultParam) throws Exception {
        return batchMapper.getBatchs(batchCd, batchNm, useYn, defaultParam);
    }

    public Batch getBatch(String batchCd) throws Exception {
        return batchMapper.getBatch(batchCd);
    }

    public int getBatchCheck(String batchCd, String batchCdSave) throws Exception {
        return batchMapper.getBatchCheck(batchCd, batchCdSave);
    }

    public String insertBatch(Batch batch) throws Exception {
        return batchMapper.insertBatch(batch) > 0 ? batch.getBatchCd() : "";
    }

    public int updateBatch(Batch batch) throws Exception {
        return batchMapper.updateBatch(batch);
    }
}
