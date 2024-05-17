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
package com.she.vendor.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.vendor.model.SubconEvalItem;

@Mapper
@Repository("com.she.vendor.baseInfo.mapper.VendorSubconEvalItemMapper")
public interface VendorSubconEvalItemMapper {
    /**
     * 협력업체평가항목 조회
     * 
     * @param subconEvalClsCd
     *            평가구분코드
     * @param subconEvalItemNm
     *            평가항목명
     * @param useYn
     *            사용여부
     * @return 협력업체평가항목 목록
     * @throws Exception
     */
    public List<SubconEvalItem> getSubconEvalItems(@Param("subconEvalPlantCd") String subconEvalPlantCd,
            @Param("subconEvalClsCd") String subconEvalClsCd, @Param("subconEvalAttCd") String subconEvalAttCd,
            @Param("subconEvalItemNm") String subconEvalItemNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 협력업체평가항목 상세 조회
     * 
     * @param safSubconEvalItemNo
     *            협력업체평가항목번호
     * @return 협력업체평가항목
     * @throws Exception
     */
    public SubconEvalItem getSubconEvalItem(@Param("safSubconEvalItemNo") int safSubconEvalItemNo) throws Exception;

    /**
     * 협력업체평가항목 신규등록
     * 
     * @param subconEvalItem
     *            협력업체평가항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int createSubconEvalItem(SubconEvalItem subconEvalItem) throws Exception;

    /**
     * 협력업체평가항목 수정
     * 
     * @param subconEvalItem
     *            협력업체평가항목
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateSubconEvalItem(SubconEvalItem subconEvalItem) throws Exception;

    /**
     * 협력업체평가항목명 체크
     * 
     * @param subconEvalItemNm
     *            협력업체평가항목명
     * @param safSubconEvalItemNo
     *            협력업체평가항목번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckSubconEvalItem(@Param("subconEvalItemNm") String subconEvalItemNm,
            @Param("safSubconEvalItemNo") int safSubconEvalItemNo) throws Exception;

}
