package com.she.chm.chem.mapper;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.DeptChemprod;

@Mapper
@Repository("com.she.chm.chem.mapper.DeptChemprodMapper")
public interface DeptChemprodMapper {

    /**
     * 부서공정별 취급자재 조회
     * 
     * @param deptCd
     *            부서 코드
     * @param processCd
     *            공정번호
     * @param search
     *            검색어(취급자재명, 공급업체명, 제조업체명)
     * @param useYn
     *            사용여부
     * @return 부서공정별 취급자재
     * @throws Exception
     */
    public List<DeptChemprod> getDeptChemprods(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd,
            @Param("processCd") String processCd, @Param("search") String search, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 부서공정별 취급자재 상세 조회
     * 
     * @param processCd
     *            공정 번호
     * @param chemProdNo
     *            취급자재 번호
     * @return 부서공정별 취급자재
     * @throws Exception
     */
    public List<DeptChemprod> getDeptChemprod(@Param("processCd") String processCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 물질별 구성성분
     * 
     * @param chemProdNo
     *            취급자재 번호
     * @return 물질별 구성성분 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemNos(@Param("chemProdNo") int chemProdNo) throws Exception;

    /**
     * 부서공정별 취급자재 신규등록
     * 
     * @param deptChemprod
     *            부서공정별 취급자재
     * @return 등록 행 수
     * @throws Exception
     */
    public int createDeptChemprod(DeptChemprod deptChemprod) throws Exception;

    /**
     * 부서공정별 취급자재 삭제
     * 
     * @param processCd
     *            공정번호
     * @param chemProdNo
     *            취급자재 번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteDeptChemprod(@Param("processCd") String processCd) throws Exception;

    /**
     * 부서공정별 취급자재 체크
     *
     * @param deptCd
     *            부서 코드
     * @param processCd
     *            공정 코드
     * @return 부서공정별 취급자재 체크
     * @throws Exception
     */
    public HashMap<String, Object> getCheckDeptChemProd(@Param("deptCd") String deptCd, @Param("processCd") String processCd) throws Exception;

    /**
     * 부서공정별 취급자재 수정
     *
     * @param deptChemprod
     *            부서공정별 취급자재
     * @return 등록 행 수
     * @throws Exception
     */
    public int updateDeptChemprod(DeptChemprod deptChemprod) throws Exception;
}
