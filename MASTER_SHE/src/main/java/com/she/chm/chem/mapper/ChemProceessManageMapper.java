package com.she.chm.chem.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemProcessManage;
import com.she.chm.model.ChemProcessManageChemprodVal;
import com.she.chm.model.ProcessManagePicGraph;

@Mapper
@Repository("com.she.chm.chem.mapper.ChemProceessManageMapper")
public interface ChemProceessManageMapper {

    /**
     * 공정관리요령 조회
     * 
     * @param useYn
     * @param plantCd
     * @param deptCd
     * @param processCd
     * @return
     * @throws Exception
     */
    public List<ChemProcessManage> getChemProcessManages(@Param("search") String search, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd) throws Exception;

    /**
     * 공정관리요령 상세정보 조회
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public ChemProcessManage getChemProcessManage(@Param("chmProcessManageNo") int chmProcessManageNo) throws Exception;

    /**
     * 공정관리요령 신규등록
     * 
     * @param chemProcessManage
     * @return
     * @throws Exception
     */
    public int createChemProcessManage(ChemProcessManage chemProcessManage) throws Exception;

    /**
     * 공정관리요령 수정
     * 
     * @param chemProcessManage
     * @return
     * @throws Exception
     */
    public int updateChemProcessManage(ChemProcessManage chemProcessManage) throws Exception;

    /**
     * 공정관리요령 취급자재목록 조회
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public List<ChemProcessManageChemprodVal> getChemProcessManageChemprodVals(@Param("chmProcessManageNo") int chmProcessManageNo) throws Exception;

    /**
     * 공정관리요령 취급자재목록 등록
     * 
     * @param chemProcessManageChemprodVal
     * @return
     * @throws Exception
     */
    public int createChemProcessManageChemprodVal(ChemProcessManageChemprodVal chemProcessManageChemprodVal) throws Exception;

    /**
     * 공정관리요령 취급자재목록 삭제
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public int deleteChemProcessManageChemprodVals(@Param("chmProcessManageNo") int chmProcessManageNo) throws Exception;

    /**
     * 출력할 정보
     * 
     * @param chmProcessManageNos
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getPrintChemProcessManage(@Param("chmProcessManageNos") int[] chmProcessManageNos) throws Exception;

    /**
     * 공정관리요령 그림문자 등록
     * 
     * @param chemProcessManage
     * @return
     * @throws Exception
     */
    public int createProcessManagePicGraphs(ProcessManagePicGraph processManagePicGraph) throws Exception;

    /**
     * 공정관리요령 그림문자 삭제
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public int deleteProcessManagePicGraphs(@Param("chmProcessManageNo") int chmProcessManageNo) throws Exception;

    /**
     * 공정관리요령 그림문자 조회
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public List<String> getProcessManagePicGraphs(@Param("chmProcessManageNo") int chmProcessManageNo) throws Exception;

    /**
     * 공정관리요령 그림문자명 조회
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public List<String> getProcessManagePicGraphsNm(@Param("chmProcessManageNo") int chmProcessManageNo) throws Exception;
}
