package com.she.chm.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalReagentLab;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalReagentLabMapper")
public interface ChemicalReagentLabMapper {

    /**
     * 시약실험실 조회
     * @param labNm 시약실험실명
     * @param useYn 사용여부
     * @return 시약실험실 목록
     * @throws Exception
     */
    public List<ChemicalReagentLab> getChemicalReagentLabs(
            @Param("labNm") String labNm,
            @Param("plantCd") String plantCd,
            @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 시약실험실 상세 조회
     * @param labNo 시약실험실번호
     * @return 시약실험실
     * @throws Exception
     */
    public ChemicalReagentLab getChemicalReagentLab(@Param("labNo") Integer labNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 시약실험실 신규등록
     * @param chemicalReagentLab 시약실험실
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalReagentLab(ChemicalReagentLab chemicalReagentLab) throws Exception;

    /**
     * 시약실험실 수정
     * @param chemicalReagentLab 시약실험실
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalReagentLab(ChemicalReagentLab chemicalReagentLab) throws Exception;

    /**
     * 시약실험실명 체크 (사업장코드 + 시약실험실명)
     * @param labNm 바꿀 시약실험실명
     * @param labNo 시약실험실번호
     * @param plantCd 사업장코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalReagentLab(
            @Param("labNm")String labNm,
            @Param("labNo") Integer labNo,
            @Param("plantCd") String plantCd) throws Exception;

}
