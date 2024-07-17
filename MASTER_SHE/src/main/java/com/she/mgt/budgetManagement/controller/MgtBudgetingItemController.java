package com.she.mgt.budgetManagement.controller;

import com.she.common.model.DefaultParam;
import com.she.mgt.baseInfo.model.MgtMgBudgetItem;
import com.she.mgt.budgetManagement.service.MgtBudgetingItemService;
import com.she.mgt.budgetManagement.service.MgtBudgetingService;
import com.she.mgt.model.MgtBudgetAct;
import com.she.mgt.model.MgtBudgetActDept;
import com.she.mgt.model.MgtBudgetActDeptCate;
import com.she.mgt.model.MgtBudgetActItem;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/mgt/budgetmanagement")
@RestController
public class MgtBudgetingItemController {
	@Autowired
	MgtBudgetingItemService mgtBudgetingItemService;
	
	@Autowired
    private RequestMapper requestMapper;
    
	/**
	 * 예산편성 목록 조회
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/budgetActList")
	public ResponseEntity<List<MgtBudgetActDeptCate>> getBudgetActList(@RequestParam HashMap<String, Object> parameter) throws Exception {
		HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
		
		String year = map.containsKey("year") ? map.get("year").toString() : "";
		String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
		String budgetCateCd = map.containsKey("budgetCateCd") ? map.get("budgetCateCd").toString() : "";
		String budgetCateDtlNm = map.containsKey("budgetCateDtlNm") ? map.get("budgetCateDtlNm").toString() : "";
		
		return ResponseEntity.ok().body(mgtBudgetingItemService.getBudgetActList(year, deptCd, budgetCateCd, budgetCateDtlNm));
	}
	
	/**
	 * 예산편성 상세
	 * @param budgetActNo
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/budgetActDetail/{budgetActNo}")
	public ResponseEntity<MgtBudgetActItem> budgetItems(@PathVariable("budgetActNo") String budgetActNo) throws Exception {
		return ResponseEntity.ok().body(mgtBudgetingItemService.getBudgetActDetail(budgetActNo));
	}
	
	/**
	 * 항목조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/budgetItems")
	public ResponseEntity<List<MgtMgBudgetItem>> budgetItems() throws Exception {
		return ResponseEntity.ok().body(mgtBudgetingItemService.getBudgetItemList());
	}
	
	/**
	 * 예산항목 검색
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/budgetingItemList")
	public ResponseEntity<List<MgtMgBudgetItem>> getBudgetingItemList(@RequestParam HashMap<String, Object> parameter) throws Exception {
		HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
		
		String budgetCateCd = map.containsKey("budgetCateCd") ? map.get("budgetCateCd").toString() : "";
		String budgetCateDtlNm = map.containsKey("budgetCateDtlNm") ? map.get("budgetCateDtlNm").toString() : "";
		
		return ResponseEntity.ok().body(mgtBudgetingItemService.getBudgetingItemList(budgetCateCd, budgetCateDtlNm));
	}
	
	/**
	 * 예산편성 저장
	 * @param mgtBudgetActItem
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/mgtBudgetAct")
	public ResponseEntity<MgtBudgetActItem> saveBudgetAct(@RequestBody MgtBudgetActItem mgtBudgetActItem) throws Exception {
		return ResponseEntity.ok().body(mgtBudgetingItemService.saveBudgetAct(mgtBudgetActItem));
	}
	
	/**
	 * 예산편성 수정
	 * @param mgtBudgetActItem
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/mgtBudgetAct")
	public ResponseEntity<MgtBudgetActItem> updateBudgetAct(@RequestBody MgtBudgetActItem mgtBudgetActItem) throws Exception {
		return ResponseEntity.ok().body(mgtBudgetingItemService.updateBudgetAct(mgtBudgetActItem));
		
	}
}
