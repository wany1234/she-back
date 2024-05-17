package com.she.chm.chem.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemPermitProdHis;
import com.she.chm.model.Chemprod;
import com.she.common.model.DefaultParam;

@Mapper
@Repository("com.she.chm.chem.mapper.ChemPermitProdHisMapper")
public interface ChemPermitProdHisMapper {

    /**
     * 인허가신고 조회
     * 
     * @param useYn
     * @param plantCd
     * @param permitClsNo
     * @param fromYmd
     * @param toYmd
     * @return
     * @throws Exception
     */
    public List<ChemPermitProdHis> getChemPermitProdHises(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("permitClsNo") Integer permitClsNo, @Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd) throws Exception;

    /**
     * 인허가신고 상세조회
     * 
     * @param permitProdHisNo
     * @return
     * @throws Exception
     */
    public ChemPermitProdHis getChemPermitProdHis(@Param("permitProdHisNo") int permitProdHisNo) throws Exception;

    /**
     * 인허가신고 상세조회
     * 
     * @param permitProdHisNo
     * @return
     * @throws Exception
     */
    public List<Chemprod> getChemPermitProdHisProd(@Param("permitProdHisNo") int permitProdHisNo) throws Exception;

    /**
     * 인허가신고 중복검사
     * 
     * @param plantCd
     * @param permitClsNo
     * @param permitDt
     * @param permitProdHisNo
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkChemPermitProdHis(@Param("plantCd") String plantCd, @Param("permitClsNo") int permitClsNo, @Param("permitDt") String permitDt, @Param("permitProdHisNo") Integer permitProdHisNo) throws Exception;

    /**
     * 인허가신고 등록
     * 
     * @param chemPermitProdHis
     * @return
     * @throws Exception
     */
    public int createChemPermitProdHis(ChemPermitProdHis chemPermitProdHis) throws Exception;

    /**
     * 인허가신고 수정
     * 
     * @param chemPermitProdHis
     * @return
     * @throws Exception
     */
    public int updateChemPermitProdHis(ChemPermitProdHis chemPermitProdHis) throws Exception;

    /**
     * 인허가신고 취급자재 삭제
     * 
     * @param permitProdHisNo
     * @return
     * @throws Exception
     */
    public int deleteChemPermitProdHisProd(@Param("permitProdHisNo") int permitProdHisNo) throws Exception;

    /**
     * 인허가신고 취급자재 등록
     * 
     * @param permitProdHisNo
     * @param chemProdNo
     * @return
     * @throws Exception
     */
    public int createChemPermitProdHisProd(@Param("chemProdNo") int chemProdNo, @Param("permitProdHisNo") int permitProdHisNo) throws Exception;

    /**
     * 인허가현황 개정이력 삭제
     *
     * @param chemPermitProdHis
     * @return 생성 행수
     * @throws Exception
     */
    public int revDeleteChemPermitProdHisStatus(ChemPermitProdHis chemPermitProdHis) throws Exception;

    /**
     * 인허가현황 개정이력 조회
     *
     * @param permitProdHisNo
     * @return 생성 행수
     * @throws Exception
     */
    public List<ChemPermitProdHis> getChemPermitProdHisRevisionList(@Param("permitProdHisNo") String permitProdHisNo, DefaultParam defaultParam) throws Exception;

    /**
     * 인허가현황 삭제
     *
     * @param permitProdHisNo
     * @return 삭제 행수
     * @throws Exception
     */
    int deleteChemPermitProdHisStatus(@Param("permitProdHisNo") int permitProdHisNo);
}
