package com.she.mgt.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.baseInfo.model.BizFieldItem;

@Mapper
@Repository("com.she.mgt.baseinfo.mapper.SafetyActionBizFieldItemMapper")
public interface SafetyActionBizFieldItemMapper {

    /**
     * 경영 기준정보 KPI분야별 항목 조회
     * 
     * @param parameter
     * @return 경영 기준정보 KPI분야별 항목 목록
     * @throws Exception
     */
    public List<BizFieldItem> getSafetyActionBizFieldItems(@Param("bizFieldCd") String bizFieldCd, @Param("bizFieldItemNm") String bizFieldItemNm, @Param("useYn") String useYn, @Param("from") String from, @Param("to") String to, @Param("createUserNm") String createUserNm) throws Exception;

    /**
     * 경영 기준정보 KPI분야별 항목 상세조회
     *
     * @param bizFieldItemNo
     * @return
     * @throws Exception
     */
    public BizFieldItem getSafetyActionBizFieldItem(@Param("bizFieldItemNo") int bizFieldItemNo) throws Exception;

    /**
     * 경영 기준정보 KPI분야별 항목 등록
     *
     * @param bizFieldItem
     * @return
     * @throws Exception
     */
    public int createSafetyActionBizFieldItem(BizFieldItem bizFieldItem) throws Exception;

    /**
     * 경영 기준정보 KPI분야별 항목 수정
     *
     * @param bizFieldItem
     * @return
     * @throws Exception
     */
    public int updateSafetyActionBizFieldItem(BizFieldItem bizFieldItem) throws Exception;

    /**
     * 경영 기준정보 KPI 분야별 항목 중복 검사
     * 
     * @param parameter
     * @return 경영 기준정보 KPI 분야별 항목관리 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkSafetyActionBizFieldItem(@Param("bizFieldCd") String bizFieldCd, @Param("bizFieldItemNm") String bizFieldItemNm, @Param("bizFieldItemNo") int bizFieldItemNo) throws Exception;

}
