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

import com.she.common.model.DefaultParam;
import com.she.mgt.model.LicensingStatus;

@Mapper
@Repository("com.she.mgt.baseInfo.mapper.LicensingStatusMapper")
public interface LicensingStatusMapper {
    /**
     * 경영 인허가현황 조회
     * 
     * @param fromDate,
     *            toDate, title, kindCd
     * @return 인허가현황
     * @throws Exception
     */

    public List<LicensingStatus> getLicensingStatusList(@Param("fromDate") String toDate, @Param("toDate") String fromDate, @Param("title") String title, @Param("kindCd") String roomTp, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 경영 인허가현황 개정 조회
     * 
     * @param fromDate,
     *            toDate, title, kindCd
     * @return 인허가현황
     * @throws Exception
     */

    public List<LicensingStatus> getLicensingStatusRevisionList(@Param("lcnBoardNo") String lcnBoardNo, @Param("defaultParam") DefaultParam defaultParam);

    /**
     * 경영 인허가현황 단건 조회
     * 
     * @param lcnBoardNo
     * @return 인허가현황
     * @throws Exception
     */
    public LicensingStatus getLicensingStatus(@Param("lcnBoardNo") int lcnBoardNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 경영 인허가현황 등록
     * 
     * @param lcnBoardNo
     * @return 인허가현황
     * @throws Exception
     */
    public int insertLicensingStatus(LicensingStatus licensingStatus) throws Exception;

    /**
     * 경영 인허가현황 수정
     * 
     * @param lcnBoardNo
     * @return 인허가현황
     * @throws Exception
     */
    public int updateLicensingStatus(LicensingStatus licensingStatus) throws Exception;

    /**
     * 경영 인허가현황 삭제
     * 
     * @param lcnBoardNo
     * @return 인허가현황번호
     * @throws Exception
     */
    public int deleteLicensingStatus(@Param("lcnBoardNo") int lcnBoardNo);

    /**
     * 경영 인허가현황 개정이력 삭제
     * 
     * @param LicensingStatus
     * @return 생성 행수
     * @throws Exception
     */
    public int revDeleteLicensingStatus(LicensingStatus licensingStatus) throws Exception;

}
