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

package com.she.health.checkup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.she.health.model.CheckupResult;
import com.she.health.model.CheckupResultDiag;
import com.she.health.model.TestItemResult;

/**
 * 건강검진결과 업로드 맵퍼
 *
 */
@Mapper
@Repository("com.she.health.checkup.mapper.CheckupResultUploadMapper")
public interface CheckupResultUploadMapper {

    /**
     * 건강검진결과 엑셀업로드
     * 
     * @param checkupResult
     *            건강검진결과
     * @return 수정 행 수
     * @throws Exception
     */
    public int uploadExcelCheckupResult(CheckupResult checkupResult) throws Exception;

    /**
     * 검사항목 검사결과 엑셀업로드
     * 
     * @param testItemResult
     *            개인별 검사항목 검사결과
     * @return 수정 행 수
     * @throws Exception
     */
    public int uploadExcelTestItemResult(TestItemResult testItemResult) throws Exception;

    /**
     * 건강검진결과 진단 엑셀업로드
     * 
     * @param checkupResultDiag
     *            건강검진결과 진단
     * @return 수정 행 수
     * @throws Exception
     */
    public int uploadExcelCheckupResultDiag(CheckupResultDiag checkupResultDiag) throws Exception;
}
