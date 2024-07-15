package com.she.mgt.baseInfo.controller;

import com.she.mgt.baseInfo.model.MgtMgBudget;
import com.she.mgt.baseInfo.model.MgtMgBudgetItem;
import com.she.mgt.baseInfo.service.MgtMgBudgetItemService;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/mgt/baseinfo")
@RestController
public class MgtMgBudgetItemController {
	
	@Autowired
    private RequestMapper requestMapper;
	
	@Autowired
    private MgtMgBudgetItemService mgtMgBudgetItemService;
	
	/**
     * 예산항목 리스트 조회
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/budgetItemList")
    public ResponseEntity<List<MgtMgBudgetItem>> getBudgetItemList(@RequestParam HashMap<String, Object> parameter) throws Exception {
    	HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
    	
    	String budgetCateNm = map.containsKey("budgetCateNm") ? map.get("budgetCateNm").toString() : "";
    	String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
    	
    	return ResponseEntity.ok().body(mgtMgBudgetItemService.getBudgetItemList(budgetCateNm, useYn));
    }
    
    /**
     * 예산항목 등록
     * @param mgtMgBudget
     * @return
     * @throws Exception
     */
    @PostMapping("/budgetItem")
    public ResponseEntity<Integer> insertBudgetItem(@RequestBody MgtMgBudget mgtMgBudget) throws Exception{
    	return ResponseEntity.ok().body(mgtMgBudgetItemService.createBudget(mgtMgBudget));
    }
    
    /**
     * 예산항목 수정
     * @param mgtMgBudget
     * @return
     * @throws Exception
     */
    @PutMapping("/budgetItem")
    public ResponseEntity<Integer> updateBudgetItem(@RequestBody MgtMgBudget mgtMgBudget) throws Exception{
    	return ResponseEntity.ok().body(mgtMgBudgetItemService.updateBudgetItem(mgtMgBudget));
    }
    
    /**
     * 예산항목 상세 조회
     * @param budgetCateCd
     * @return
     * @throws Exception
     */
    @GetMapping("/budgetDetail/{budgetCateCd}")
    public ResponseEntity<MgtMgBudget> getBudgetDetail(@PathVariable("budgetCateCd") String budgetCateCd) throws Exception{
    	return ResponseEntity.ok().body(mgtMgBudgetItemService.getBudgetDetail(budgetCateCd));
    }

}
