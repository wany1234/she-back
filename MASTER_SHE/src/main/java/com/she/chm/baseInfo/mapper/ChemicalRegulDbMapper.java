package com.she.chm.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalRegulDb;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalRegulDbMapper")
public interface ChemicalRegulDbMapper {

    /**
     * 규제DB업로드건별 규제정보 조회
     * @param chmRegulDbCd : 규제DB업로드코드
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDb> getChemicalRegulDbs(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;
    
    /**
     * 규제DB업로드건별 규제정보 페이징조회
     * @param chmRegulDbCd : 규제DB업로드코드
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDb> getChemicalRegulDbsPage(@Param("chmRegulDbCd") String chmRegulDbCd, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("orderByExpression") String orderByExpression) throws Exception;
    
    /**
     * 규제DB업로드건별 규제정보 페이징조회
     * @param chmRegulDbCd : 규제DB업로드코드
     * @return
     * @throws Exception
     */
    public int getChemicalRegulDbsTotalSize(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;
    


    /**
     * 규제정보등록
     * @param chemicalRegulDb
     * @return
     * @throws Exception
     */
    public int createChemicalRegulDb(ChemicalRegulDb chemicalRegulDb) throws Exception;

    /**
     * 업로드 하려는 규제정보의 유효성 검사 처리
     * @param vChmRegulDbCd
     * @return
     * @throws Exception
     */
    public String executeIfRegulDbCheckValidUpdate(@Param("vChmRegulDbCd") String chmRegulDbCd) throws Exception;

    /**
     * 적용처리
     * @param vChmRegulDbCd
     * @return
     * @throws Exception
     */
    public String executeIfRegulDbToChemUpdate(@Param("vChmRegulDbCd") String chmRegulDbCd) throws Exception;

    /**
     * 규제업로드 이력 삭제
     * @param vChmRegulDbCd
     * @return
     * @throws Exception
     */
    public int deleteChemicalRegulDb(@Param("chmRegulDbCd") String chmRegulDbCd) throws Exception;

}
