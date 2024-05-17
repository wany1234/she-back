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
package com.she.baseInfo.SAPMAT.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.MatVendor;
import com.she.baseInfo.model.SAPMAT;
import com.she.baseInfo.model.SapMatGroup;
import com.she.common.model.DefaultParam;

@Mapper
@Repository("com.she.baseInfo.SAPMAT.mapper.SAPMATMapper")
public interface SAPMATMapper {

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
    public List<HashMap<String, Object>> getSapMats(@Param("matCode") String matCode, @Param("matName") String matName, @Param("useYn") String useYn, @Param("matGroupCd") String matGroupCd, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("orderByExpression") String orderByExpression) throws Exception;

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
    public int getSapMatTotalCounts(@Param("matCode") String matCode, @Param("matName") String matName, @Param("useYn") String useYn, @Param("matGroupCd") String matGroupCd) throws Exception;

    /**
     * 자재 상세조회
     * 
     * @param matCode
     *            자재코드
     * @return 자재
     * @throws Exception
     */
    public SAPMAT getSapMat(@Param("matCode") String matCode) throws Exception;

    /**
     * 자재별 제조업체 조회
     * 
     * @param matCode
     *            자재코드
     * @return 자재별 제조업체 목록
     * @throws Exception
     */
    public List<MatVendor> getMatVendor(@Param("matCode") String matCode) throws Exception;

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
    public List<MatVendor> getSapMatVendor(@Param("plantCd") String plantCd, @Param("matCode") String matCode, @Param("vendorNm") String vendorNm, @Param("vendorTypeCd") String vendorTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int deleteSapMatVendor(@Param("matCode") String matCode) throws Exception;

    public int createSapMatVendor(MatVendor matVendor) throws Exception;

    /**
     * 자재 그룹 조회
     * 
     * @return 자재 그룹 목록
     * @throws Exception
     */
    public List<SapMatGroup> getSapMatGroups() throws Exception;

}
