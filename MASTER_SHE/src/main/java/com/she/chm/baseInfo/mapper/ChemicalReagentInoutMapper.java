package com.she.chm.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalReagentInout;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalReagentInoutMapper")
public interface ChemicalReagentInoutMapper {

    /**
     * 시약입출고 조회
     * @param chemProdNm 시약명(취급자재명)
     * @param inoutFlag 입출고구분
     * @param labNo 실험실번호
     * @param fromYmd 입출고일 검색조건 시작일
     * @param toYmd 입출고일 검색조건 마침일
     * @return 시약입출고 목록
     * @throws Exception
     */
    public List<ChemicalReagentInout> getChemicalReagentInouts(
            @Param("chemProdNm") String chemProdNm,
            @Param("inoutFlag") String inoutFlag,
            @Param("plantCd") String plantCd,
            @Param("labNo") Integer labNo,
            @Param("fromYmd") String fromYmd,
            @Param("toYmd") String toYmd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 시약입출고 상세 조회
     * @param reagentInoutNo 시약입출고번호
     * @return 시약입출고
     * @throws Exception
     */
    public ChemicalReagentInout getChemicalReagentInout(@Param("reagentInoutNo") Integer reagentInoutNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 시약입출고 신규등록
     * @param chemicalReagentInout 시약입출고
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalReagentInout(ChemicalReagentInout chemicalReagentInout) throws Exception;

    /**
     * 시약입출고 수정
     * @param chemicalReagentInout 시약입출고
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalReagentInout(ChemicalReagentInout chemicalReagentInout) throws Exception;

    /**
     * 시약입출고 유니크 체크 (입출고일 + 실험실번호 + 입출고구분 + 시약번호)
     * @param inoutDt 입출고일
     * @param labNo 실험실번호
     * @param inoutFlag 입출고구분
     * @param chemProdNo 시약번호(취급자재)
     * @param reagentInoutNo 입출고번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalReagentInout(
            @Param("inoutDt")String inoutDt,
            @Param("labNo") Integer labNo,
            @Param("inoutFlag") String inoutFlag,
            @Param("chemProdNo") Integer chemProdNo,
            @Param("reagentInoutNo") Integer reagentInoutNo) throws Exception;

    /**
     * 시약입출고 삭제
     * @param reagentInoutNo 입출고번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChemicalReagentInout(@Param("reagentInoutNo") int reagentInoutNo) throws Exception;

}
