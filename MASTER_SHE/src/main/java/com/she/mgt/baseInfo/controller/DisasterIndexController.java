package com.she.mgt.baseInfo.controller;

import com.she.mgt.baseInfo.model.DisasterIndex;
import com.she.mgt.baseInfo.model.MgtMgBudget;
import com.she.mgt.baseInfo.model.MgtMgBudgetItem;
import com.she.mgt.baseInfo.service.DisasterIndexService;
import com.she.mgt.baseInfo.service.MgtMgBudgetItemService;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/mgt/baseinfo")
@RestController
public class DisasterIndexController {
	
	@Autowired
    private RequestMapper requestMapper;
	
	@Autowired
	private DisasterIndexService disasterIndexService;
	
	/**
	 * 종합재해지수 목록
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/disasterindex")
	public ResponseEntity<List<DisasterIndex>> getDisasterIndex(@RequestParam HashMap<String, Object> parameter) throws Exception {
    	HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
    	
    	String year = map.containsKey("year") ? map.get("year").toString() : "";
    	
    	return ResponseEntity.ok().body(disasterIndexService.getDisasterIndexList(year));
    }
	
	/**
	 * 종합재해지수 상세
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
    @GetMapping("/disasterindexdetail")
    public ResponseEntity<DisasterIndex> getDisasterIndexDetail(@RequestParam HashMap<String, Object> parameter) throws Exception {
    	HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
    	
    	String year = map.containsKey("year") ? map.get("year").toString() : "";
    	String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
    	
    	return ResponseEntity.ok().body(disasterIndexService.getDisasterIndexDetail(year, plantCd));
    }
    
    /**
     * 종합재해지수 등록
     * @param disasterIndex
     * @return
     * @throws Exception
     */
    @PostMapping("/disasterindexsave")
    public ResponseEntity<DisasterIndex> insertDisasterIndex(@RequestBody DisasterIndex disasterIndex) throws Exception {
    	return ResponseEntity.ok().body(disasterIndexService.insertDisasterIndex(disasterIndex));
    }
    
    /**
     * 종합재해지수 수정
     * @param disasterIndex
     * @return
     * @throws Exception
     */
    @PutMapping("/disasterindexsave")
    public ResponseEntity<DisasterIndex> updateDisasterIndex(@RequestBody DisasterIndex disasterIndex) throws Exception {
    	return ResponseEntity.ok().body(disasterIndexService.updateDisasterIndex(disasterIndex));
    }
    
//    /**
//     * 예산항목 등록
//     * @param mgtMgBudget
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("/budgetItem")
//    public ResponseEntity<Integer> insertBudgetItem(@RequestBody MgtMgBudget mgtMgBudget) throws Exception{
//    	return ResponseEntity.ok().body(mgtMgBudgetItemService.createBudget(mgtMgBudget));
//    }
//    
//    /**
//     * 예산항목 수정
//     * @param mgtMgBudget
//     * @return
//     * @throws Exception
//     */
//    @PutMapping("/budgetItem")
//    public ResponseEntity<Integer> updateBudgetItem(@RequestBody MgtMgBudget mgtMgBudget) throws Exception{
//    	return ResponseEntity.ok().body(mgtMgBudgetItemService.updateBudgetItem(mgtMgBudget));
//    }
//    
//    /**
//     * 예산항목 상세 조회
//     * @param budgetCateCd
//     * @return
//     * @throws Exception
//     */
//    @GetMapping("/budgetDetail/{budgetCateCd}")
//    public ResponseEntity<MgtMgBudget> getBudgetDetail(@PathVariable("budgetCateCd") String budgetCateCd) throws Exception{
//    	return ResponseEntity.ok().body(mgtMgBudgetItemService.getBudgetDetail(budgetCateCd));
//    }

}
