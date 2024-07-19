package com.she.mgt.budgetManagement.mapper;

import com.she.common.model.DefaultParam;
import com.she.mgt.baseInfo.model.MgtMgBudgetItem;
import com.she.mgt.model.MgtBudgetAct;
import com.she.mgt.model.MgtBudgetActDept;
import com.she.mgt.model.MgtBudgetActDeptCate;
import com.she.mgt.model.MgtBudgetActDeptItem;
import com.she.mgt.model.MgtBudgetActItem;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MgtBudgetingItemMapper {
	
	/**
	 * 예산편성 목록 조회
	 * @param year
	 * @param deptCd
	 * @param budgetCateCd
	 * @param budgetCateDtlNm
	 * @return
	 * @throws Exception
	 */
	public List<MgtBudgetActDeptCate> getBudgetActList(@Param("year") String year, @Param("deptCd") String deptCd, @Param("budgetCateCd") String budgetCateCd, @Param("budgetCateDtlNm") String budgetCateDtlNm) throws Exception;
	
	/**
	 * 항목조회
	 * @return
	 * @throws Exception
	 */
	public List<MgtMgBudgetItem> getBudgetItemList() throws Exception;
	
	/**
	 * 예산항목 조회
	 * @param budgetCateCd
	 * @param budgetCateDtlNm
	 * @return
	 * @throws Exception
	 */
	public List<MgtMgBudgetItem> getBudgetingItemList(@Param("budgetCateCd") String budgetCateCd, @Param("budgetCateDtlNm") String budgetCateDtlNm) throws Exception;
	
	// 예산편성부서 키 조회
	public int getBudgetActDeptNo() throws Exception;
	
	/**
	 * 예산편성부서 등록
	 * @param mgtBudgetActItem
	 * @return
	 * @throws Exception
	 */
	public int insertBudgetActDept(MgtBudgetActItem mgtBudgetActItem) throws Exception;
	
	/**
	 * 예산편성부서항목 등록
	 * @param mgtBudgetActDeptCate
	 * @return
	 * @throws Exception
	 */
	public int saveBudgetActDeptCate(MgtBudgetActDeptCate mgtBudgetActDeptCate) throws Exception;
	
	/**
	 * 예산편성부서항목 삭제
	 * @param mgtBudgetActDeptCate
	 * @return
	 * @throws Exception
	 */
	public int deleteBudgetActDeptCate(MgtBudgetActDeptCate mgtBudgetActDeptCate) throws Exception;
	
	/**
	 * 예산편성 상세
	 * @param budgetActNo
	 * @return
	 */
	public MgtBudgetActItem getBudgetActDetail(@Param("budgetActNo") String budgetActNo) throws Exception;
	public List<MgtBudgetActDeptCate> getBudgetActItemDetail(@Param("budgetActNo") String budgetActNo) throws Exception;
}
