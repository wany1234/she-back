package com.she.env.water.facility.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.water.model.WasteBasicUnit;
import com.she.env.water.model.WasteBasicUnitItem;

@Mapper
@Repository("com.she.env.water.facility.mapper.WasteBasicUnitMapper")
public interface WasteBasicUnitMapper {

    public List<WasteBasicUnit> getWasteBasicUnits(@Param("plantCd") String plantCd, @Param("useYn") String useYn, @Param("deptCd") String deptCd, @Param("ewtrCleanFacNm") String ewtrCleanFacNm, @Param("ewtrCleanFacClassCd") String ewtrCleanFacClassCd, @Param("ewtrCleanFacNo") String ewtrCleanFacNo, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    public WasteBasicUnit getWasteBasicUnit(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public int insertWasteBasicUnit(WasteBasicUnit wasteBasicUnit) throws Exception;

    public int updateWasteBasicUnit(WasteBasicUnit wasteBasicUnit) throws Exception;

    public int insertIngredient(WasteBasicUnitItem wasteBasicUnit) throws Exception;

    public int insertChemBase(WasteBasicUnitItem wasteBasicUnit) throws Exception;

    public int insertSupply(WasteBasicUnitItem wasteBasicUnit) throws Exception;

    public int insertDischarge(WasteBasicUnitItem wasteBasicUnit) throws Exception;

    public int insertMonPos(WasteBasicUnitItem wasteBasicUnit) throws Exception;

    public int deleteIngredient(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public int deleteChemBase(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public int deleteSupply(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public int deleteDischarge(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public int deleteMonPos(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public List<WasteBasicUnitItem> getIngredient(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public List<WasteBasicUnitItem> getChemBase(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public List<WasteBasicUnitItem> getSupply(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public List<WasteBasicUnitItem> getDischarge(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public List<WasteBasicUnitItem> getMonPos(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public int getCheck(@Param("plantCd") String plantCd, @Param("ewtrCleanFacNm") String ewtrCleanFacNm, @Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    public Integer getSequenceNumber() throws Exception;

}
