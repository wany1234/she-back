package com.she.mgt.budgetManagement.service;

import com.she.mgt.baseInfo.model.MgtMgBudgetItem;
import com.she.mgt.budgetManagement.mapper.MgtBudgetingItemMapper;
import com.she.mgt.model.MgtBudgetActDeptCate;
import com.she.mgt.model.MgtBudgetActItem;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MgtBudgetingItemService {
	
	@Autowired
	MgtBudgetingItemMapper mgtBudgetingItemMapper;
	
	/**
	 * 예산편성 목록 조회
	 * @param year
	 * @param deptCd
	 * @param budgetCateCd
	 * @param budgetCateDtlNm
	 * @return
	 * @throws Exception
	 */
	public List<MgtBudgetActDeptCate> getBudgetActList(String year, String deptCd, String budgetCateCd, String budgetCateDtlNm) throws Exception{
		return mgtBudgetingItemMapper.getBudgetActList(year, deptCd, budgetCateCd, budgetCateDtlNm);
	}
	
	/**
	 * 항목조회
	 * @return
	 * @throws Exception
	 */
	public List<MgtMgBudgetItem> getBudgetItemList() throws Exception {
		return mgtBudgetingItemMapper.getBudgetItemList();
	}
	
	/**
	 * 예산항목 조회
	 * @param budgetCateCd
	 * @param budgetCateDtlNm
	 * @return
	 * @throws Exception
	 */
	public List<MgtMgBudgetItem> getBudgetingItemList(String budgetCateCd, String budgetCateDtlNm) throws Exception {
		return mgtBudgetingItemMapper.getBudgetingItemList(budgetCateCd, budgetCateDtlNm);
	}
	
	/**
	 * 예산편성 등록
	 * @param mgtBudgetActItem
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public MgtBudgetActItem saveBudgetAct(MgtBudgetActItem mgtBudgetActItem) throws Exception {
		
		// 예산편성부서번호
		int actNo = mgtBudgetingItemMapper.getBudgetActDeptNo();
		mgtBudgetActItem.setBudgetActNo(actNo);
		mgtBudgetingItemMapper.insertBudgetActDept(mgtBudgetActItem);
		
		// 예산편성부서항목 저장
		if(CollectionUtils.isNotEmpty(mgtBudgetActItem.getBudgetActDeptCateList())) {
			for(MgtBudgetActDeptCate mgtBudgetActDeptCate : mgtBudgetActItem.getBudgetActDeptCateList()) {
				mgtBudgetActDeptCate.setBudgetActNo(actNo);
				mgtBudgetActDeptCate.setCreateUserId(mgtBudgetActItem.getCreateUserId());
				mgtBudgetingItemMapper.saveBudgetActDeptCate(mgtBudgetActDeptCate);
			}
		}
		
		return mgtBudgetActItem;
	}
	
	/**
	 * 예산편성 수정
	 * @param mgtBudgetActItem
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public MgtBudgetActItem updateBudgetAct(MgtBudgetActItem mgtBudgetActItem) throws Exception {
		
		// 예산편성부서항목 저장
		if(CollectionUtils.isNotEmpty(mgtBudgetActItem.getBudgetActDeptCateList())) {
			for(MgtBudgetActDeptCate mgtBudgetActDeptCate : mgtBudgetActItem.getBudgetActDeptCateList()) {
				mgtBudgetActDeptCate.setCreateUserId(mgtBudgetActItem.getCreateUserId());
				mgtBudgetActDeptCate.setUpdateUserId(mgtBudgetActItem.getUpdateUserId());
				mgtBudgetingItemMapper.saveBudgetActDeptCate(mgtBudgetActDeptCate);
			}
		}
		
		// 예산편성부서항목 삭제
		if(CollectionUtils.isNotEmpty(mgtBudgetActItem.getDeleteBudgetActDeptCateList())) {
			for(MgtBudgetActDeptCate deleteMgtBudgetActDeptCate : mgtBudgetActItem.getDeleteBudgetActDeptCateList()) {
				mgtBudgetingItemMapper.deleteBudgetActDeptCate(deleteMgtBudgetActDeptCate);
			}
		}
		
		return mgtBudgetActItem;
	}
	
	/**
	 * 예산편성 상세
	 * @param budgetActNo
	 * @return
	 * @throws Exception
	 */
	public MgtBudgetActItem getBudgetActDetail(String budgetActNo) throws Exception {
		MgtBudgetActItem result = mgtBudgetingItemMapper.getBudgetActDetail(budgetActNo);
		result.setBudgetActDeptCateList(mgtBudgetingItemMapper.getBudgetActItemDetail(budgetActNo));
		return result;
	}
}
