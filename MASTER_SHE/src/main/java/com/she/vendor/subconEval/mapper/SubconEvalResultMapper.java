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
package com.she.vendor.subconEval.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.vendor.model.SubconEvalItemResult;
import com.she.vendor.model.SubconEvalResult;

@Mapper
@Repository("com.she.vendor.subconEval.mapper.SubconEvalResultMapper")
public interface SubconEvalResultMapper {

    /**
     * 협력업체평가 상세 조회
     * 
     * @param safSubconEvalResultNo
     *            협력업체평가번호
     * @return 협력업체평가
     * @throws Exception
     */
    public SubconEvalResult getSubconEvalResult(@Param("safSubconEvalResultNo") int safSubconEvalResultNo) throws Exception;

    /**
     * 협력업체평가 신규등록
     * 
     * @param subconEvalResult
     *            협력업체평가
     * @return 협력업체평가 코드
     * @throws Exception
     */
    public int createSubconEvalResult(SubconEvalResult subconEvalResult) throws Exception;

    /**
     * 협력업체평가 수정
     * 
     * @param subconEvalResult
     *            협력업체평가
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateSubconEvalResult(SubconEvalResult subconEvalResult) throws Exception;

    /**
     * 협력업체평가항목 등록
     * 
     * @param subconEvalItemResult
     *            협력업체평가항목
     * @return 변경행수
     * @throws Exception
     */
    public int createSubconEvalItemResult(SubconEvalItemResult subconEvalItemResult) throws Exception;

    /**
     * 협력업체평가항목 삭제
     * 
     * @param safSubconEvalResultNo
     *            협력업체평가번호
     * @return 삭제 행수
     * @throws Exception
     */
    public int deleteSubconEvalItemResult(@Param("safSubconEvalResultNo") int safSubconEvalResultNo) throws Exception;

    /**
     * 협력업체평가결과 삭제
     * 
     * @param safSubconEvalResultNo
     *            협력업체평가번호
     * @return 삭제 행수
     * @throws Exception
     */
    public int deleteSubconEvalResult(@Param("safSubconEvalResultNo") int safSubconEvalResultNo) throws Exception;
}
