package com.she.mgt.baseInfo.mapper;

import com.she.manage.model.CodeMaster;
import com.she.mgt.baseInfo.model.MgtMgBudgetActMst;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MgtMgBudgetActMstMapper {
    /**
     * 예산계정 관리목록 조회
     *
     * @param codeNm
     *      예산분류명
     * @param useYn
     *      사용여부
     * @return
     * @throws Exception
     */
    public List<CodeMaster> getBudgetActs(@Param("codeNm") String codeNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 예산계정 관리상세목록 조회
     *
     * @param budgetTypeCd
     *      예산분류코드
     * @return
     * @throws Exception
     */
    public List<MgtMgBudgetActMst> getBudgetActMsts(@Param("budgetTypeCd") String budgetTypeCd) throws Exception;

    /**
     * 예산계정 상세목록 저장
     *
     * @param mgtMgBudgetActMst
     *          예산계정 상세
     * @return
     * @throws Exception
     */
    public int saveBudgetActMst(MgtMgBudgetActMst mgtMgBudgetActMst) throws Exception;

    /**
     * 예산계정 상세목록 삭제
     *
     * @param mgtMgBudgetActMst
     *          예산계정 상세
     * @return
     * @throws Exception
     */
    public int deleteBudgetActMst(MgtMgBudgetActMst mgtMgBudgetActMst) throws Exception;
}
