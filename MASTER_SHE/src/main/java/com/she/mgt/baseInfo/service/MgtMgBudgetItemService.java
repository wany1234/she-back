package com.she.mgt.baseInfo.service;

import com.she.mgt.baseInfo.mapper.MgtMgBudgetItemMapper;
import com.she.mgt.baseInfo.model.MgtMgBudget;
import com.she.mgt.baseInfo.model.MgtMgBudgetDetailItem;
import com.she.mgt.baseInfo.model.MgtMgBudgetItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MgtMgBudgetItemService {
    
    @Autowired
    private MgtMgBudgetItemMapper mgtMgBudgetItemMapper;
    
    /**
     * 예산항목 리스트 조회
     * @param budgetCateNm
     * @param useYn
     * @return
     * @throws Exception
     */
    public List<MgtMgBudgetItem> getBudgetItemList(String budgetCateNm, String useYn) throws Exception {
    	return mgtMgBudgetItemMapper.getBudgetItemList(budgetCateNm, useYn);
    }
    
    /**
     * 예산항목 등록
     * @param mgtMgBudget
     * @return
     * @throws Exception
     */
    @Transactional
    public int createBudget(MgtMgBudget mgtMgBudget) throws Exception {
    	int resultNo = 0;
    	resultNo = mgtMgBudgetItemMapper.insertBudgetItem(mgtMgBudget.getMgtMgBudgetItem());
    	
    	if (mgtMgBudget.getMgtMgBudgetDetailItem() != null && mgtMgBudget.getMgtMgBudgetDetailItem().size() > 0) {
    		for (MgtMgBudgetDetailItem mgtMgBudgetDetailItem : mgtMgBudget.getMgtMgBudgetDetailItem()) {
    			mgtMgBudgetDetailItem.setBudgetCateCd(mgtMgBudget.getMgtMgBudgetItem().getBudgetCateCd());
    			mgtMgBudgetDetailItem.setCreateUserId(mgtMgBudget.getMgtMgBudgetItem().getCreateUserId());
    			resultNo += mgtMgBudgetItemMapper.insertBudgetDetailItem(mgtMgBudgetDetailItem);
    		}
    	}
    	
    	return resultNo;
    }
    
    /**
     * 예산항목 상세 조회
     * @param budgetCateCd
     * @return
     * @throws Exception
     */
    public MgtMgBudget getBudgetDetail(String budgetCateCd) throws Exception {
    	MgtMgBudget mgtMgBudget = new MgtMgBudget();
    	mgtMgBudget.setMgtMgBudgetItem(mgtMgBudgetItemMapper.getBudgetItem(budgetCateCd));
    	mgtMgBudget.setMgtMgBudgetDetailItem(mgtMgBudgetItemMapper.getBudgetDetailItem(budgetCateCd));
    	return mgtMgBudget;
    }
    
    
    /**
     * 예산항목 수정
     * @param mgtMgBudget
     * @return
     * @throws Exception
     */
    public int updateBudgetItem(MgtMgBudget mgtMgBudget) throws Exception {
    	int resultNo = 0;
    	resultNo = mgtMgBudgetItemMapper.updateBudgetItem(mgtMgBudget.getMgtMgBudgetItem());
    	
    	if (mgtMgBudget.getMgtMgBudgetDetailItem() != null && mgtMgBudget.getMgtMgBudgetDetailItem().size() > 0) {
    		for (MgtMgBudgetDetailItem mgtMgBudgetDetailItem : mgtMgBudget.getMgtMgBudgetDetailItem()) {
    			mgtMgBudgetDetailItem.setBudgetCateCd(mgtMgBudget.getMgtMgBudgetItem().getBudgetCateCd());
    			mgtMgBudgetDetailItem.setUpdateUserId(mgtMgBudget.getMgtMgBudgetItem().getUpdateUserId());
    			resultNo += mgtMgBudgetItemMapper.updateBudgetDetailItem(mgtMgBudgetDetailItem);
    		}
    	}
    	if(mgtMgBudget.getDeleteMgtMgBudgetDetailItem() != null && mgtMgBudget.getDeleteMgtMgBudgetDetailItem().size() > 0) {
    		for (MgtMgBudgetDetailItem deleteMgtMgBudgetDetailItem : mgtMgBudget.getDeleteMgtMgBudgetDetailItem()) {
    			deleteMgtMgBudgetDetailItem.setBudgetCateCd(mgtMgBudget.getMgtMgBudgetItem().getBudgetCateCd());
    			resultNo += mgtMgBudgetItemMapper.deleteBudgetDetailItem(deleteMgtMgBudgetDetailItem);
    		}
    	}
    	
    	return resultNo;
    }
}
