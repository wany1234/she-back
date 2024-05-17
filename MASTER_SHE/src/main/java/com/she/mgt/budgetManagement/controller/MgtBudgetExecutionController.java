package com.she.mgt.budgetManagement.controller;

import com.google.common.base.Strings;
import com.she.common.model.DefaultParam;
import com.she.mgt.budgetManagement.service.MgtBudgetExecutionService;
import com.she.mgt.model.MgtBudgetExec;
import com.she.mgt.model.MgtBudgetStat;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/mgt/budgetmanagement")
@RestController
public class MgtBudgetExecutionController {
    @Autowired
    private MgtBudgetExecutionService mgtBudgetExecutionService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 예산편성 관리 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param plantCd
     *          사업장코드
     * @param deptCd
     *          대상부서코드
     * @param deptSubYn
     *          하위부서 조회여부
     * @param budgetTypeCd
     *          예산분류코드
     * @param budgetClsCd
     *          예산구분코드
     * @param budgetActNm
     *          예산계정
     * @param procStepCd
     *          단계(편성/집행)
     * @param stateCd
     *          상태(미작성/작성중/결재중/결재완료)
     * @return 예산편성 관리 목록
     * @throws Exception
     */
    @GetMapping("/mgtbudgetactexecs")
    public ResponseEntity<List<MgtBudgetExec>> getMgtBudgetExecs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        // 기간(년도)
        String[] period = requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYear = "";
        String endYear = "";
        if (period != null && period.length == 2) {
            startYear = period[0];
            endYear = period[1];
        }

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 대상부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 하위부서 검색여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";

        // 예산 분류
        String budgetTypeCd = map.containsKey("budgetTypeCd") ? map.get("budgetTypeCd").toString() : "";

        // 예산 구분
        String budgetClsCd = map.containsKey("budgetClsCd") ? map.get("budgetClsCd").toString() : "";

        // 예산 계정
        String budgetActNm = map.containsKey("budgetActNm") ? map.get("budgetActNm").toString() : "";

        // 예산 계정 번호
        int budgetActMstNo = map.containsKey("budgetActMstNo") ? (map.get("budgetActMstNo") != null ? Integer.parseInt(map.get("budgetActMstNo").toString()) : 0) : 0;

        // 단계(편성/집행)
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";

        // 상태(미작성/작성중/결재중/결재완료)
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";

        // 편성현황 조회 여부(편성및집행 현황 메뉴에서 집행관리 조회할 경우 합계 추가)
        String statusYn = map.containsKey("statusYn") ? map.get("statusYn").toString() : "";

        // 월
        String month = map.containsKey("month") ? map.get("month").toString() : "";

        return ResponseEntity.ok().body(mgtBudgetExecutionService.getMgtBudgetExecs(startYear, endYear, plantCd, deptCd, deptSubYn, budgetTypeCd, budgetClsCd, budgetActNm, procStepCd, stateCd, statusYn, budgetActMstNo, month, defaultParam));
    }

    /**
     * 예산집행 관리 상세 조회
     * @param budgetExecNo
     *          예산집행 번호
     * @return 예산집행 상세
     * @throws Exception
     */
    @GetMapping("/mgtbudgetactexec/{budgetExecNo}")
    public ResponseEntity<MgtBudgetExec> getMgtBudgetExec(@PathVariable("budgetExecNo") int budgetExecNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetExecutionService.getMgtBudgetExec(budgetExecNo, defaultParam));
    }

    /**
     * 예산계정별 집행가능 금액 조회
     * @param budgetActMstNo
     *          예산계정 번호
     * @return 집행가능 금액
     * @throws Exception
     */
    @GetMapping("/mgtbudgetactexec/execpsblamt")
    public ResponseEntity<String> getExecPsblAmt(@RequestParam HashMap<String, Object> parameter) throws Exception {
        Map<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 대상부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 예산분류
        String budgetTypeCd = map.containsKey("budgetTypeCd") ? map.get("budgetTypeCd").toString() : "";

        // 예산구분
        String budgetClsCd = map.containsKey("budgetClsCd") ? map.get("budgetClsCd").toString() : "";

        // 예산계정번호
        int budgetActMstNo = map.containsKey("budgetActMstNo") ? (!Strings.isNullOrEmpty(map.get("budgetActMstNo").toString()) ? Integer.parseInt(map.get("budgetActMstNo").toString()) : 0) : 0;

        // 예산집행번호
        int budgetExecNo = map.containsKey("budgetExecNo") ? (!Strings.isNullOrEmpty(map.get("budgetExecNo").toString()) ? Integer.parseInt(map.get("budgetExecNo").toString()) : 0) : 0;

        return ResponseEntity.ok().body(mgtBudgetExecutionService.getExecPsblAmt(deptCd, budgetTypeCd, budgetClsCd, budgetActMstNo, budgetExecNo));
    }

    /**
     * 예산편성된 예산계정 목록 조회
     * @param year
     *          년도
     * @param deptCd
     *          대상부서
     * @param pdeptCd
     *          상위대상부서
     * @param deptSubYn
     *          하위부서 조회여부
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActNm
     *          예산계정
     * @return 예산편성된 예산계정 목록
     * @throws Exception
     */
    @GetMapping("/budgeting/mgtbudgetactmsts")
    public ResponseEntity<List<HashMap<String, Object>>> getBudgetingActMst(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 대상부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 상위대상부서
        String pdeptCd = map.containsKey("pdeptCd") ? map.get("pdeptCd").toString() : "";

        // 하위부서 조회여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";

        // 예산분류
        String budgetTypeCd = map.containsKey("budgetTypeCd") ? map.get("budgetTypeCd").toString() : "";

        // 예산구분
        String budgetClsCd = map.containsKey("budgetClsCd") ? map.get("budgetClsCd").toString() : "";

        // 예산계정
        String budgetActNm = map.containsKey("budgetActNm") ? map.get("budgetActNm").toString() : "";

        return ResponseEntity.ok().body(mgtBudgetExecutionService.getBudgetingActMst(year, plantCd, deptCd, pdeptCd, deptSubYn, budgetTypeCd, budgetClsCd, budgetActNm, defaultParam));
    }

    /**
     * 예산집행 신규등록
     * @param mgtBudgetExec
     *          예산집행
     * @return 결과
     * @throws Exception
     */
    @PostMapping("/mgtbudgetactexec")
    public ResponseEntity<MgtBudgetExec> insertMgtBudgetExec(@RequestBody MgtBudgetExec mgtBudgetExec) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetExecutionService.insertMgtBudgetExec(mgtBudgetExec));
    }

    /**
     * 예산집행 수정
     * @param mgtBudgetExec
     *          예산집행
     * @return 결과
     * @throws Exception
     */
    @PutMapping("/mgtbudgetactexec")
    public ResponseEntity<MgtBudgetExec> updateMgtBudgetExec(@RequestBody MgtBudgetExec mgtBudgetExec) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetExecutionService.updateMgtBudgetExec(mgtBudgetExec));
    }

    /**
     * 예산집행 삭제
     * @param budgetExecNo
     *          예산집행 번호
     * @return 결과
     * @throws Exception
     */
    @DeleteMapping("/mgtbudgetactexec/{budgetExecNo}")
    public ResponseEntity<Integer> deleteMgtBudgetExec(@PathVariable("budgetExecNo") int budgetExecNo) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetExecutionService.deleteMgtBudgetExec(budgetExecNo));
    }

    /**
     * 예산 편성관리 현황 조회
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActMstNo
     *          예산계정
     * @return 결과
     * @throws Exception
     */
    @GetMapping("/mgtbudgetingstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getBudgetingStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 예산 분류
        String budgetTypeCd = map.containsKey("budgetTypeCd") ? map.get("budgetTypeCd").toString() : "";

        // 예산 구분
        String budgetClsCd = map.containsKey("budgetClsCd") ? map.get("budgetClsCd").toString() : "";

        // 예산계정
        int budgetActMstNo = map.containsKey("budgetActMstNo") ? Integer.parseInt("".equals(map.get("budgetActMstNo").toString()) ? "0" : map.get("budgetActMstNo").toString()) : 0;

        // 부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(mgtBudgetExecutionService.getBudgetingStatus(year, plantCd, budgetTypeCd, budgetClsCd, budgetActMstNo, deptCd, defaultParam));
    }

    /**
     * 예산 편성및집행 현황 조회
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActMstNo
     *          예산계정
     * @return 결과
     * @throws Exception
     */
    @GetMapping("/mgtbudgetstatus")
    public ResponseEntity<List<MgtBudgetStat>> getBudgetStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 예산 분류
        String budgetTypeCd = map.containsKey("budgetTypeCd") ? map.get("budgetTypeCd").toString() : "";

        // 예산 구분
        String budgetClsCd = map.containsKey("budgetClsCd") ? map.get("budgetClsCd").toString() : "";

        // 예산계정
        int budgetActMstNo = map.containsKey("budgetActMstNo") ? Integer.parseInt("".equals(map.get("budgetActMstNo").toString()) ? "0" : map.get("budgetActMstNo").toString()) : 0;

        return ResponseEntity.ok().body(mgtBudgetExecutionService.getBudgetStatus(year, plantCd, budgetTypeCd, budgetClsCd, budgetActMstNo, defaultParam));
    }
}
