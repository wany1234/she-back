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
package com.she.baseInfo.SAPMAT.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.baseInfo.SAPMAT.mapper.SAPMATMapper;
import com.she.baseInfo.model.MatVendor;
import com.she.baseInfo.model.SAPMAT;
import com.she.baseInfo.model.SapMatGroup;
import com.she.common.model.DefaultParam;

@Service
public class SAPMATService {
    @Autowired
    private SAPMATMapper sAPMATMapper;

    /**
     * 자재 조회
     * 
     * @param matCode
     *            자재코드
     * @param matName
     *            자재명
     * @param useYn
     *            사용여부
     * @param matGroupCd
     *            자재그룹
     * @return 자재 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSapMats(String matCode, String matName, String useYn, String matGroupCd, int pageNumber,
                                                    int pageSize, String orderByExpression) throws Exception {
        orderByExpression = orderByExpression.toLowerCase();
        return sAPMATMapper.getSapMats(matCode, matName, useYn, matGroupCd, pageNumber, pageSize, orderByExpression);
    }

    /**
     * 자재 총 카운트 수 조회
     * 
     * @param matCode
     *            자재코드
     * @param matName
     *            자재명
     * @param useYn
     *            사용여부
     * @param matGroupCd
     *            자재그룹
     * @return 자재 총 카운트 수
     * @throws Exception
     */
    public int getSapMatTotalCounts(String matCode, String matName, String useYn, String matGroupCd) throws Exception {
        return sAPMATMapper.getSapMatTotalCounts(matCode, matName, useYn, matGroupCd);
    }

    /**
     * 자재 상세조회
     * 
     * @param matCode
     *            자재코드
     * @return 자재
     * @throws Exception
     */
    public SAPMAT getSapMat(String matCode, DefaultParam defaultParam) throws Exception {
        SAPMAT sapmat = sAPMATMapper.getSapMat(matCode);
        // List<MatVendor> matVendors = sAPMATMapper.getMatVendor(matCode);
        // if (matVendors != null && matVendors.size() > 0) {
        // sapmat.setMatVendors(matVendors);
        // } else {
        // sapmat.setMatVendors(sAPMATMapper.getSapMatVendor("", matCode, "",
        // ""));
        // }
        sapmat.setMatVendors(sAPMATMapper.getSapMatVendor("", matCode, "", "", defaultParam));
        return sapmat;
    }

    /**
     * 제조업체 조회
     * 
     * @param plantCd
     *            사업장
     * @param matCode
     *            자재코드
     * @param vendorNm
     *            제조업체명
     * @param vendorTypeCd
     *            업체분류
     * @return 제조업체 목록
     * @throws Exception
     */
    public List<MatVendor> getSapMatVendor(String plantCd, String matCode, String vendorNm, String vendorTypeCd, DefaultParam defaultParam)
            throws Exception {
        return sAPMATMapper.getSapMatVendor(plantCd, matCode, vendorNm, vendorTypeCd, defaultParam);
    }

    public int saveSapMatVendor(SAPMAT sapmat) throws Exception {
        int returnVal = 0;
        // 자재에 묶여 있는 제조업체 정보들을 지운다.
        returnVal += sAPMATMapper.deleteSapMatVendor(sapmat.getMatCode());
        if (sapmat.getMatVendors() != null && sapmat.getMatVendors().size() > 0) {
            for (MatVendor matVendor : sapmat.getMatVendors()) {
                matVendor.setMatCode(sapmat.getMatCode());
                returnVal += sAPMATMapper.createSapMatVendor(matVendor);
            }
        }
        return returnVal;
    }

    /**
     * 자재 그룹 조회
     * 
     * @return 자재 그룹 목록
     * @throws Exception
     */
    public List<SapMatGroup> getSapMatGroups() throws Exception {
        return sAPMATMapper.getSapMatGroups();
    }

}
