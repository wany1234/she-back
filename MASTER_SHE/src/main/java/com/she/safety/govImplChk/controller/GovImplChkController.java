package com.she.safety.govImplChk.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.she.common.model.DefaultParam;
import com.she.safety.govImplChk.service.GovImplChkService;
import com.she.safety.model.GovImplChkPlan;
import com.she.utils.RequestMapper;

@RequestMapping("/api/saf/inspection")
@RestController
public class GovImplChkController {
    @Autowired
    private GovImplChkService govImplChkService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 정부지자체 시정조치 이행점검 관리 목록 조회
     * 
     * @param plantCd
     *            사업장
     * @param year
     *            대상년도
     * @param regRegdem
     *            구분
     * @param procStepCd
     *            단계
     * @param stateCd
     *            상태
     * @param chkNm
     *            점검명
     * @param mainDeptCd
     *            주관부서
     * @param mainDeptSubYn
     *            주관부서 하위부서 조회여부
     * @param targetDeptCd
     *            대상부서
     * @param targetDeptSubYn
     *            대상부서 하위부서 조회여부
     * @param refGovNm
     *            관련 정부지자체
     * @param overdueYn
     *            조치기한 초과 여부
     * @return 정부지자체 시정조치 이행점검 관리 목록 조회
     * @throws Exception
     */
    @GetMapping("/govImplChkResults")
    public ResponseEntity<List<GovImplChkPlan>> getGovImplChkResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 대상년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        // 구분
        String regRegdem = map.containsKey("regRegdem") ? map.get("regRegdem").toString() : "";

        // 단계
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";

        // 상태
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";

        // 점검명
        String chkNm = map.containsKey("chkNm") ? map.get("chkNm").toString() : "";

        // 주관부서
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";

        // 주관부서 하위부서 조회 여부
        String mainDeptSubYn = map.containsKey("mainDeptSubYn") ? map.get("mainDeptSubYn").toString() : "";

        // 대상부서
        String targetDeptCd = map.containsKey("targetDeptCd") ? map.get("targetDeptCd").toString() : "";

        // 대상부서 하위부서 조회 여부
        String targetDeptSubYn = map.containsKey("targetDeptSubYn") ? map.get("targetDeptSubYn").toString() : "";

        // 관련 정부지자체
        String refGovNm = map.containsKey("refGovNm") ? map.get("refGovNm").toString() : "";

        // 조치기한 초과
        String overdueYn = map.containsKey("overdueYn") ? map.get("overdueYn").toString() : "";

        // 월
        int month = map.containsKey("month") ? (!Strings.isNullOrEmpty(map.get("month").toString()) ? Integer.parseInt(map.get("month").toString()) : 0) : 0;

        // 대상부서별 조회여부
        String tgtDeptYn = map.containsKey("tgtDeptYn") ? map.get("tgtDeptYn").toString() : "";

        // 통계조회 여부
        String statusYn = map.containsKey("statusYn") ? map.get("statusYn").toString() : "";

        return ResponseEntity.ok().body(govImplChkService.getGovImplChkResults(plantCd, year, regRegdem, procStepCd, stateCd, chkNm, mainDeptCd, mainDeptSubYn, targetDeptCd, targetDeptSubYn, refGovNm, overdueYn, month, tgtDeptYn, statusYn, defaultParam));
    }

    @GetMapping("/govImplChkResult/{implChkPlanNo}")
    public ResponseEntity<GovImplChkPlan> getGovImplChkResult(@PathVariable("implChkPlanNo") int implChkPlanNo) throws Exception {
        return ResponseEntity.ok().body(govImplChkService.getGovImplChkResult(implChkPlanNo));
    }

    /**
     * 정부지자체 시정조치 이행점검 관리 신규등록
     * 
     * @param govImplChkPlan
     *            정부지자체 시정조치 이행점검
     * @return
     * @throws Exception
     */
    @PostMapping("/govImplChkResult")
    public ResponseEntity<GovImplChkPlan> createGovImplChkResult(@RequestBody GovImplChkPlan govImplChkPlan) throws Exception {
        return ResponseEntity.ok().body(govImplChkService.saveGovImplChkResult(govImplChkPlan));
    }

    /**
     * 정부지자체 시정조치 이행점검 관리 수정
     * 
     * @param govImplChkPlan
     *            정부지자체 시정조치 이행점검
     * @return
     * @throws Exception
     */
    @PutMapping("/govImplChkResult")
    public ResponseEntity<GovImplChkPlan> updateGovImplChkResult(@RequestBody GovImplChkPlan govImplChkPlan) throws Exception {
        return ResponseEntity.ok().body(govImplChkService.saveGovImplChkResult(govImplChkPlan));
    }

    /**
     * 정부지자체 시정조치 이행점검 관리 삭제
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    @DeleteMapping("/govImplChkResult/{implChkPlanNo}")
    public ResponseEntity<Integer> deleteGovImplChkResult(@PathVariable("implChkPlanNo") int implChkPlanNo) throws Exception {
        return ResponseEntity.ok().body(govImplChkService.deleteGovImplChkResult(implChkPlanNo));
    }

    /**
     * 정부지자체 시정조치 이행점검결과 현황
     * 
     * @param plantCd
     *            사업장
     * @param year
     *            대상년도
     * @param regRedgdem
     *            구분
     * @return
     * @throws Exception
     */
    @GetMapping("/govImplChkStatus")
    public ResponseEntity<List<HashMap<String, Object>>> getGovImplChkStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 대상년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        // 구분
        String regRegdem = map.containsKey("regRegdem") ? map.get("regRegdem").toString() : "";

        String totalFlag = map.containsKey("totalFlag") ? map.get("totalFlag").toString() : "";

        return ResponseEntity.ok().body(govImplChkService.getGovImplChkStatus(plantCd, year, regRegdem, totalFlag, defaultParam));
    }
}
