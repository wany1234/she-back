package com.she.mgt.baseInfo.mapper;

import com.she.mgt.baseInfo.model.MgtMgBudgetDetailItem;
import com.she.mgt.baseInfo.model.MgtMgBudgetItem;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MgtMgBudgetItemMapper {
	
	/**
	 * 예산항목 리스트 조회
	 * @param budgetCateNm
	 * @param useYn
	 * @return
	 * @throws Exception
	 */
	public List<MgtMgBudgetItem> getBudgetItemList(@Param("budgetCateNm") String budgetCateNm, @Param("useYn") String useYn) throws Exception;
    
    /**
     * 예산항목 등록
     * @param mgtMgBudgetItem
     * @return
     * @throws Exception
     */
    public int insertBudgetItem(MgtMgBudgetItem mgtMgBudgetItem) throws Exception;
    
    /**
     * 예산항목상세 등록
     * @param mgtMgBudgetDetailItem
     * @return
     * @throws Exception
     */
    public int insertBudgetDetailItem(MgtMgBudgetDetailItem mgtMgBudgetDetailItem) throws Exception;
    
    /**
     * 예산항목수정
     * @param mgtMgBudgetItem
     * @return
     * @throws Exception
     */
    public int updateBudgetItem(MgtMgBudgetItem mgtMgBudgetItem) throws Exception;
    
    /**
     * 예산항목상세 수정
     * @param mgtMgBudgetDetailItem
     * @return
     * @throws Exception
     */
    public int updateBudgetDetailItem(MgtMgBudgetDetailItem mgtMgBudgetDetailItem) throws Exception;
    
    /**
     * 예산항목 삭제
     * @param mgtMgBudgetDetailItem
     * @return
     * @throws Exception
     */
    public int deleteBudgetDetailItem(MgtMgBudgetDetailItem mgtMgBudgetDetailItem) throws Exception;
    
    /**
     * 예산항목 정보 조회
     * @param budgetCateCd
     * @return
     * @throws Exception
     */
    public MgtMgBudgetItem getBudgetItem(@Param("budgetCateCd") String budgetCateCd) throws Exception;
    
    /**
     * 예산항목상세 정보 조회
     * @param budgetCateCd
     * @return
     * @throws Exception
     */
    public List<MgtMgBudgetDetailItem> getBudgetDetailItem(@Param("budgetCateCd") String budgetCateCd) throws Exception;
}
