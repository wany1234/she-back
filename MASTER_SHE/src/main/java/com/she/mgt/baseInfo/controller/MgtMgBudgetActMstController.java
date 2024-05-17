package com.she.mgt.baseInfo.controller;

import com.she.manage.model.CodeMaster;
import com.she.mgt.baseInfo.model.MgtMgBudgetAct;
import com.she.mgt.baseInfo.model.MgtMgBudgetActMst;
import com.she.mgt.baseInfo.service.MgtMgBudgetActMstService;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/mgt/baseinfo")
@RestController
public class MgtMgBudgetActMstController {
    @Autowired
    private MgtMgBudgetActMstService mgtMgBudgetActMstService;

    @Autowired
    private RequestMapper requestMapper;

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
    @GetMapping("/budgetacts")
    public ResponseEntity<List<CodeMaster>> getBudgetActs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 예산분류
        String codeNm = map.containsKey("codeNm") ? map.get("codeNm").toString() : "";

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(mgtMgBudgetActMstService.getBudgetActs(codeNm, useYn));
    }

    /**
     * 예산계정 관리 조회
     *
     * @param codeNm
     *      예산분류명
     * @param useYn
     *      사용여부
     * @return
     * @throws Exception
     */
    @GetMapping("/budgetact/{codeGroupCd}/{code}")
    public ResponseEntity<MgtMgBudgetAct> getBudgetAct(@PathVariable("codeGroupCd") String codeGroupCd, @PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok().body(mgtMgBudgetActMstService.getBudgetAct(codeGroupCd, code));
    }

    /**
     * 예산계정 상세목록 저장
     *
     * @param mgtMgBudgetAct
     *          예산계정 상세
     * @return
     * @throws Exception
     */
    @PostMapping("/budgetact")
    public ResponseEntity<Integer> createBudgetAct(@RequestBody MgtMgBudgetAct mgtMgBudgetAct) throws Exception {
        return ResponseEntity.ok().body(mgtMgBudgetActMstService.createBudgetAct(mgtMgBudgetAct));
    }

    /**
     * 예산계정 상세목록 저장
     *
     * @param mgtMgBudgetAct
     *          예산계정 상세
     * @return
     * @throws Exception
     */
    @PutMapping("/budgetact")
    public ResponseEntity<Integer> updateBudgetAct(@RequestBody MgtMgBudgetAct mgtMgBudgetAct) throws Exception {
        return ResponseEntity.ok().body(mgtMgBudgetActMstService.updateBudgetAct(mgtMgBudgetAct));
    }

    /**
     * 예산계정 목록 조회
     *
     * @param budgetTypeCd
     *          예산분류 코드
     * @return
     * @throws Exception
     */
    @GetMapping("/budgetactmsts")
    public ResponseEntity<List<MgtMgBudgetActMst>> getBudgetActMsts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String budgetTypeCd = map.containsKey("budgetTypeCd") ? map.get("budgetTypeCd").toString() : "";

        return ResponseEntity.ok().body(mgtMgBudgetActMstService.getBudgetActMsts(budgetTypeCd));
    }
}
