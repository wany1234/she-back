package com.she.chm.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalRegulDbHist;
import com.she.chm.model.ChemicalRegulDbValid;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalRegulDbHistMapper")
public interface ChemicalRegulDbHistMapper {

    /**
     * 규제DB업로드 이력조회
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDbHist> getChemicalRegulDbHists() throws Exception;

    /**
     * 규제DB업로드 이력 상세조회
     * @param chmRegulDbCd : 규제DB업로드코드
     * @return
     * @throws Exception
     */
    public ChemicalRegulDbHist getChemicalRegulDbHist(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;


    /**
     * 규제DB 업로드 이력
     * @param chemicalRegulDbHist
     * @return : 반영된 Row수
     * @throws Exception
     */
    public int createChemicalRegulDbHist(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception;

    /**
     * 규제DB상태 업데이트
     * @param chemicalRegulDbHist
     * @return : 반영된 Row수
     * @throws Exception
     */
    public int changeChemicalRegulDbHistStatus(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception;

    /**
     * 규제DB 적용처리
     * @param chemicalRegulDbHist
     * @return : 반영된 Row수
     * @throws Exception
     */
    public int applyChemicalRegulDbHist(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception;

    /**
     * 규제DB 확인처리
     * @param chemicalRegulDbHist
     * @return : 반영된 Row수
     * @throws Exception
     */
    public int confirmChemicalRegulDbHist(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception;

    /**
     * 규제DB 오류메시지 업데이트
     * @param chemicalRegulDbHist
     * @return
     * @throws Exception
     */
    public int updateMsgChemicalRegulDbHist(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception;

    /**
     * 규제DB이력 삭제
     * @param chmRegulDbCd
     * @return
     * @throws Exception
     */
    public int deleteChemicalRegulDbHist(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;

    /**
     * 영향받는 취급자재조회 (적용전)
     * @param chmRegulDbCd
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDbValid> getAffectedProdBefore(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;

    /**
     * 영향받는 화학물질 조회
     * @param chmRegulDbCd
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDbValid> getAffectedChemBefore(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;


    /**
     * 적용후 규제DB의 영향받을 자재 정보
     * @param chmRegulDbCd
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDbValid> getAffectedProdAfter(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;

    /**
     * 적용후 규제DB의 영향받은 화학물질 정보
     * @param chmRegulDbCd
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDbValid> getAffectedChemAfter(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;
}
