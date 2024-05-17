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
package com.she.mgt.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.model.InvestItem;
import com.she.mgt.model.MgtMgInfoItm;
import com.she.mgt.model.MgtMgInfoItmDeptAtt;

@Mapper
@Repository("com.she.mgt.baseInfo.mapper.ElectIcnMapper")
public interface ElectIcnMapper {

    /**
     * 경영정보부서 구분명 조회
     *
     * @param plantCd
     *
     * @param infoItmAttCd
     *
     * @return 경영정보부서 구분명
     * @throws Exception
     */
    public List<MgtMgInfoItmDeptAtt> getMgtInfoItem(@Param("plantCd") String plantCd, @Param("infoItmAttCd") String infoItmAttCd) throws Exception;

    /**
     * 경영정보항목 상세 조회
     *
     * @param mgtMgInfoDeptAttNo
     *            경영정보부서 번호
     * @return mgtMgInfoDeptAttNo 경영정보부서
     * @throws Exception
     *             예외
     */
    public MgtMgInfoItmDeptAtt getMgtInfoItemDetail(@Param("mgtMgInfoDeptAttNo") String mgtMgInfoDeptAttNo) throws Exception;

    /**
     * 경영정보부서 항목 조회
     *
     * @param plantCd
     *
     * @param deptAttNm
     *
     * @return 경영정보부서 구분명
     * @throws Exception
     */
    public List<MgtMgInfoItm> getMgtMgInfoItemList() throws Exception;

    /**
     * 등록된 경영정보항목 확인
     *
     * @param plantCd
     * @param mgtMgInfoItmNo
     * @param deptAttNm
     * @return
     * @throws Exception
     */
    public int checkMgtinfoitem(@Param("plantCd") String plantCd, @Param("mgtMgInfoItmNo") int mgtMgInfoItmNo, @Param("deptAttNm") String deptAttNm) throws Exception;

    /**
     * 경영정보부서 항목 신규등록
     *
     * @param mgtMgInfoItmDeptAtt
     * @return
     * @throws Exception
     */
    public int createMgtinfoitem(MgtMgInfoItmDeptAtt mgtMgInfoItmDeptAtt) throws Exception;

    /**
     * 경영정보부서 항목 수정
     *
     * @param mgtMgInfoItmDeptAtt
     * @return
     * @throws Exception
     */
    public int updateMgtinfoitem(MgtMgInfoItmDeptAtt mgtMgInfoItmDeptAtt) throws Exception;

    /**
     * 투자항목관리 조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */

    public List<InvestItem> getInvestItems(@Param("codeNm") String codeNm, @Param("mgtMgInfoItmNo") int mgtMgInfoItmNo, @Param("useYn") String useYn) throws Exception;

    /**
     * 투자항목(중분류) 조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */

    public List<InvestItem> getInvestItemsMiddle(@Param("infoItmAttCd") String infoItmAttCd, @Param("mgtMgInfoItmNo") int mgtMgInfoItmNo, @Param("useYn") String useYn) throws Exception;

    /**
     * 투자항목관리 상세조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */

    public InvestItem getInvestItem(@Param("infoItmAttCd") String infoItmAttCd, @Param("mgtMgInfoItmNo") int mgtMgInfoItmNo) throws Exception;

    /**
     * 투자항목 등록
     *
     * @param
     *
     * @return
     * @throws Exception
     */

    public int createInvestItemAdd(InvestItem investItem) throws Exception;

    /**
     * 투자항목 수정
     *
     * @param
     *
     * @return
     * @throws Exception
     */

    public int updateInvestItemAdd(InvestItem investItem) throws Exception;
}
