package com.she.env.gas.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.ActDataMaster;

@Mapper
@Repository("com.she.env.gas.baseInfo.mapper.ActDataMasterMapper")
public interface ActDataMasterMapper {
    /**
     * 안전정보 전체 조회
     * 
     * @param plantCd,
     *            deptCd, kindCd, title
     * @return 자료실 목록
     * @throws Exception
     */

    public List<ActDataMaster> getActDataMasters(@Param("actClsCd") String actClsCd, @Param("actDataCd") String actDataCd, @Param("dataLvlCd") String dataLvlCd, @Param("pActDataCd") String pActDataCd, @Param("useYn") String useYn) throws Exception;

    public List<ActDataMaster> getActDataMaster(@Param("actDataCd") String actDataCd) throws Exception;

    public List<ActDataMaster> getDataCategoryParents(@Param("actDataNm") String actDataNm, @Param("actClsCd") String actClsCd) throws Exception;

    public int insertActDataMaster(ActDataMaster actDataMaster) throws Exception;

    public int updateActDataMaster(ActDataMaster actDataMaster) throws Exception;

    public List<ActDataMaster> getDataCategoryItems(@Param("dataLvlCd") String dataLvlCd, @Param("pActDataCd") String pActDataCd, @Param("useYn") String useYn) throws Exception;

    public List<ActDataMaster> getLowDataCategories(@Param("actDataCd") String actDataCd) throws Exception;

    public int countDataCategory(@Param("actDataCd") String actDataCd) throws Exception;

}
