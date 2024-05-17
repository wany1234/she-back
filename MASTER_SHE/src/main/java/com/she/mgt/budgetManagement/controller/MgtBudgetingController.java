package com.she.mgt.budgetManagement.controller;

import com.she.common.model.DefaultParam;
import com.she.mgt.budgetManagement.service.MgtBudgetingService;
import com.she.mgt.model.MgtBudgetAct;
import com.she.mgt.model.MgtBudgetActDept;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/mgt/budgetmanagement")
@RestController
public class MgtBudgetingController {
    @Autowired
    private MgtBudgetingService mgtBudgetingService;

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
     * @param unOrganizeYn
     *          미편성 대상부서 여부
     * @param procStepCd
     *          단계(편성/집행)
     * @param stateCd
     *          상태(미작성/작성중/결재중/결재완료)
     * @return 예산편성 관리 목록
     * @throws Exception
     */
    @GetMapping("/mgtbudgetacts")
    public ResponseEntity<List<MgtBudgetAct>> getMgtBudgetActs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
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

        // 미편성 대상부서 여부
        String unOrganizeYn = map.containsKey("unOrganizeYn") ? map.get("unOrganizeYn").toString() : "";

        // 단계(편성/집행)
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";

        // 상태(미작성/작성중/결재중/결재완료)
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";

        // 편성현황 조회 여부(편성및집행 현황 메뉴에서 편성관리 조회할 경우 합계 추가)
        String statusYn = map.containsKey("statusYn") ? map.get("statusYn").toString() : "";

        return ResponseEntity.ok().body(mgtBudgetingService.getMgtBudgetActs(startYear, endYear, plantCd, deptCd, deptSubYn, budgetTypeCd, budgetClsCd, budgetActNm, unOrganizeYn, procStepCd, stateCd, statusYn, budgetActMstNo, defaultParam));
    }

    /**
     * 예산편성 상세 조회
     * @param budgetActNo
     *          예산편성관리 번호
     * @param year
     *          연도
     * @param plantCd
     *          사업장코드
     * @param flag
     *          신규등록 조회 여부
     *          신규등록일 경우 사업장코드/연도로 조회
     * @return 예산편성 상세
     * @throws Exception
     */
    @GetMapping("/mgtbudgetact/{flag}")
    public ResponseEntity<MgtBudgetAct> getMgtBudgetAct(@PathVariable("flag") String flag, @RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 예산편성관리 번호
        int budgetActNo = map.containsKey("budgetActNo") ? Integer.parseInt(map.get("budgetActNo").toString().equals("") ? "0" : map.get("budgetActNo").toString()) : 0;

        // 연도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(mgtBudgetingService.getMgtBudgetAct(budgetActNo, year, plantCd, flag, defaultParam));
    }

    /**
     * 예산편성 저장
     * @param mgtMgBudgetAct
     *          예산편성 관리
     * @return 예산편성 관리
     * @throws Exception
     */
    @PostMapping("/mgtbudgetact")
    public ResponseEntity<MgtBudgetAct> createMgtBudgetAct(@RequestBody MgtBudgetAct mgtBudgetAct) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetingService.createMgtBudgetAct(mgtBudgetAct));
    }

    /**
     * 예산편성 저장
     * @param mgtMgBudgetAct
     *          예산편성 관리
     * @return 예산편성 관리
     * @throws Exception
     */
    @PutMapping("/mgtbudgetact")
    public ResponseEntity<MgtBudgetAct> updateMgtBudgetAct(@RequestBody MgtBudgetAct mgtBudgetAct) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetingService.updateMgtBudgetAct(mgtBudgetAct));
    }

    /**
     * 예산편성 대상부서 상세 조회
     * @param budgetActDeptNo
     *          예산편성 대상부서 번호
     * @param budgetActNo
     *          예산편성 관리 번호
     * @return 결과
     * @throws Exception
     */
    @GetMapping("/mgtbudgetactdept/{budgetActDeptNo}/{budgetActNo}")
    public ResponseEntity<MgtBudgetActDept> getMgtBudgetActDept(@PathVariable("budgetActDeptNo") int budgetActDeptNo, @PathVariable("budgetActNo") int budgetActNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetingService.getMgtBudgetActDept(budgetActDeptNo, budgetActNo, defaultParam));
    }

    /**
     * 예산편성 대상부서 예산평성 저장
     * @param mgtBudgetActDept
     *          예산편성 대상부서
     * @return 결과
     * @throws Exception
     */
    @PutMapping("/mgtbudgetactdept")
    public ResponseEntity<MgtBudgetActDept> saveMgtBudgetActDept(@RequestBody MgtBudgetActDept mgtBudgetActDept) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetingService.saveMgtBudgetActDept(mgtBudgetActDept));
    }

    @DeleteMapping("/mgtbudgetactdept/{budgetActDeptNo}/{budgetActNo}")
    public ResponseEntity<Integer> deleteBudgetActDept(@PathVariable("budgetActDeptNo") int budgetActDeptNo, @PathVariable("budgetActNo") int budgetActNo) throws Exception {
        return ResponseEntity.ok().body(mgtBudgetingService.deleteMgtBudgetActDept(budgetActDeptNo, budgetActNo));
    }

    /**
     * 예산편성 대상부서 상세 조회(현황 팝업시 조회)
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param deptCd
     *          대상부서코드
     * @return 결과
     * @throws Exception
     */
    @GetMapping("/mgtbudgetactdeptbystatinfo")
    public ResponseEntity<MgtBudgetActDept> getMgtBudgetActDeptByStatInfo(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 대상부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(mgtBudgetingService.getMgtBudgetActDeptByStatInfo(year, plantCd, deptCd, defaultParam));
    }
}
