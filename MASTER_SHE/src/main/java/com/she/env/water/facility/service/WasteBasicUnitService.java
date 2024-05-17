package com.she.env.water.facility.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.water.facility.mapper.WasteBasicUnitMapper;
import com.she.env.water.model.WasteBasicUnit;
import com.she.env.water.model.WasteBasicUnitItem;

@Service
public class WasteBasicUnitService {

    @Autowired
    private WasteBasicUnitMapper wasteBasicUnitMapper;

    public List<WasteBasicUnit> getWasteBasicUnits(String plantCd, String useYn, String deptCd, String ewtrCleanFacNm, String ewtrCleanFacClassCd, String ewtrCleanFacNo, DefaultParam defaultParam) throws Exception {
        return wasteBasicUnitMapper.getWasteBasicUnits(plantCd, useYn, deptCd, ewtrCleanFacNm, ewtrCleanFacClassCd, ewtrCleanFacNo, defaultParam);

    }

    public WasteBasicUnit getWasteBasicUnit(int ewtrCleanFacNo) throws Exception {
        WasteBasicUnit wasteBasicUnit = wasteBasicUnitMapper.getWasteBasicUnit(ewtrCleanFacNo);

        wasteBasicUnit.setEwtrCleanFacNo(ewtrCleanFacNo);
        wasteBasicUnit.setWtrChemRegCd(Optional.ofNullable(wasteBasicUnitMapper.getWasteBasicUnit(ewtrCleanFacNo).getWtrChemRegCd()).orElse(""));
        wasteBasicUnit.setIngredient(wasteBasicUnitMapper.getIngredient(ewtrCleanFacNo));
        wasteBasicUnit.setChemBase(wasteBasicUnitMapper.getChemBase(ewtrCleanFacNo));
        wasteBasicUnit.setSupply(wasteBasicUnitMapper.getSupply(ewtrCleanFacNo));
        wasteBasicUnit.setDischarge(wasteBasicUnitMapper.getDischarge(ewtrCleanFacNo));
        wasteBasicUnit.setMonPos(wasteBasicUnitMapper.getMonPos(ewtrCleanFacNo));

        return wasteBasicUnit;

    }

    @Transactional
    public int insertWasteBasicUnit(WasteBasicUnit wasteBasicUnit) throws Exception {

        wasteBasicUnit.setEwtrCleanFacNo(this.getSequence());
        wasteBasicUnitMapper.insertWasteBasicUnit(wasteBasicUnit);

        this.insertWasteBasicUnitItem(wasteBasicUnit);

        return wasteBasicUnit.getEwtrCleanFacNo();
    }

    public int getCheck(String plantCd, String ewtrCleanFacNm, int ewtrCleanFacNo) throws Exception {
        return wasteBasicUnitMapper.getCheck(plantCd, ewtrCleanFacNm, ewtrCleanFacNo);
    }

    public int updateWasteBasicUnit(WasteBasicUnit wasteBasicUnit) throws Exception {
        wasteBasicUnitMapper.updateWasteBasicUnit(wasteBasicUnit);

        this.updateWasteBasicUnitItem(wasteBasicUnit);

        return wasteBasicUnit.getEwtrCleanFacNo();
    }

    public int getSequence() throws Exception {
        return wasteBasicUnitMapper.getSequenceNumber();
    }

    @Transactional
    public int insertWasteBasicUnitItem(WasteBasicUnit wasteBasicUnit) throws Exception {

        int count = 0;
        if (wasteBasicUnit.getIngredient().size() > 0 && wasteBasicUnit.getIngredient() != null) {
            for (WasteBasicUnitItem ingredient : wasteBasicUnit.getIngredient()) {
                ingredient.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertIngredient(ingredient);
            }
        }
        if (wasteBasicUnit.getChemBase().size() > 0 && wasteBasicUnit.getChemBase() != null) {
            for (WasteBasicUnitItem chemBase : wasteBasicUnit.getChemBase()) {
                chemBase.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertChemBase(chemBase);
            }
        }
        if (wasteBasicUnit.getSupply().size() > 0 && wasteBasicUnit.getSupply() != null) {
            for (WasteBasicUnitItem supply : wasteBasicUnit.getSupply()) {
                supply.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertSupply(supply);
            }
        }
        if (wasteBasicUnit.getDischarge().size() > 0 && wasteBasicUnit.getDischarge() != null) {
            for (WasteBasicUnitItem discharge : wasteBasicUnit.getDischarge()) {
                discharge.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertDischarge(discharge);
            }
        }
        if (wasteBasicUnit.getMonPos().size() > 0 && wasteBasicUnit.getMonPos() != null) {
            for (WasteBasicUnitItem monPos : wasteBasicUnit.getMonPos()) {
                monPos.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertMonPos(monPos);
            }
        }
        return count;
    }

    @Transactional
    public int updateWasteBasicUnitItem(WasteBasicUnit wasteBasicUnit) throws Exception {

        int count = 0;

        wasteBasicUnitMapper.deleteIngredient(wasteBasicUnit.getEwtrCleanFacNo());
        if (wasteBasicUnit.getIngredient().size() > 0 && wasteBasicUnit.getIngredient() != null) {
            for (WasteBasicUnitItem ingredient : wasteBasicUnit.getIngredient()) {
                ingredient.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertIngredient(ingredient);
            }
        }
        wasteBasicUnitMapper.deleteChemBase(wasteBasicUnit.getEwtrCleanFacNo());
        if (wasteBasicUnit.getChemBase().size() > 0 && wasteBasicUnit.getChemBase() != null) {
            for (WasteBasicUnitItem chemBase : wasteBasicUnit.getChemBase()) {
                chemBase.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertChemBase(chemBase);
            }
        }
        wasteBasicUnitMapper.deleteSupply(wasteBasicUnit.getEwtrCleanFacNo());
        if (wasteBasicUnit.getSupply().size() > 0 && wasteBasicUnit.getSupply() != null) {
            for (WasteBasicUnitItem supply : wasteBasicUnit.getSupply()) {
                supply.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertSupply(supply);
            }
        }
        wasteBasicUnitMapper.deleteDischarge(wasteBasicUnit.getEwtrCleanFacNo());
        if (wasteBasicUnit.getDischarge().size() > 0 && wasteBasicUnit.getDischarge() != null) {
            for (WasteBasicUnitItem discharge : wasteBasicUnit.getDischarge()) {
                discharge.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertDischarge(discharge);
            }
        }
        wasteBasicUnitMapper.deleteMonPos(wasteBasicUnit.getEwtrCleanFacNo());
        if (wasteBasicUnit.getMonPos().size() > 0 && wasteBasicUnit.getMonPos() != null) {
            for (WasteBasicUnitItem monPos : wasteBasicUnit.getMonPos()) {
                monPos.setEwtrCleanFacNo(wasteBasicUnit.getEwtrCleanFacNo());
                count += wasteBasicUnitMapper.insertMonPos(monPos);
            }
        }

        return count;
    }
}
