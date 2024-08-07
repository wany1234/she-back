/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.safety.emergency.comtroller;

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

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.safety.emergency.service.EmergencyService;
import com.she.safety.model.Emergency;
import com.she.safety.model.EmergencyDept;
import com.she.safety.model.EmergencyOutsidePsn;
import com.she.safety.model.EmergencyPsn;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/emergency")
public class EmergencyController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private EmergencyService emergencyService;

    /**
     * 훈련계획 관리 목록 조회
     *
     * @param parameter
     * @return 훈련계획 관리 목록 조회
     * @throws Exception
     */
    @GetMapping("/emergencylists")
    public ResponseEntity<List<Emergency>> getEmergencyLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 훈련기간
        String[] trainDt = this.requestMapper.convertObjectListAsStringArray(map.get("trainDt"));
        String startDt = "";
        String endDt = "";

        if (trainDt != null && trainDt.length == 2) {
            startDt = trainDt[0];
            endDt = trainDt[1];
        }
        // 훈련구분
        String trainTypeCd = map.containsKey("trainTypeCd") ? map.get("trainTypeCd").toString() : "";
        // 평가명
        String trainNm = map.containsKey("trainNm") ? map.get("trainNm").toString() : "";
        // 평가장소
        String trainPlace = map.containsKey("trainPlace") ? map.get("trainPlace").toString() : "";
        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        // 단계
        String trainPlanState = map.containsKey("trainPlanState") ? map.get("trainPlanState").toString() : "";
        // 재교육여부
        String reTrainYn = map.containsKey("reTrainYn") ? map.get("reTrainYn").toString() : "";
        // 교육방법
        String trainMethodCd = map.containsKey("trainMethodCd") ? map.get("trainMethodCd").toString() : "";
        return ResponseEntity.ok().body(emergencyService.getEmergencyLists(plantCd, startDt, endDt, trainTypeCd, trainNm, trainPlace, deptCd, deptSubYn, trainPlanState, reTrainYn, trainMethodCd, defaultParam));
    }

    /**
     * 훈련계획 관리 계획 조회
     *
     * @param parameter
     * @return 훈련계획 관리 계획 조회
     * @throws Exception
     */
    @GetMapping("/emergencyInfo/{safTrainPlanNo}")
    public ResponseEntity<Emergency> getEmergencyInfo(@PathVariable("safTrainPlanNo") int safTrainPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(emergencyService.getEmergencyInfo(safTrainPlanNo, defaultParam));
    }

    /**
     * 훈련계획 대상자 목록 조회
     *
     * @param parameter
     * @return 훈련계획 대상자 목록 조회
     * @throws Exception
     */
    @GetMapping("/emergencyuserlist/{safTrainPlanNo}/{apprFlag}")
    public ResponseEntity<List<EmergencyPsn>> getEmergencyUserList(@PathVariable("safTrainPlanNo") int safTrainPlanNo, @PathVariable("apprFlag") String apprFlag, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(emergencyService.getEmergencyUserList(safTrainPlanNo, apprFlag, defaultParam));
    }

    /**
     * 훈련계획 관리 계획 등록
     *
     * @param parameter
     * @return 훈련계획 관리 계획 조회
     * @throws Exception
     */
    @PostMapping("/emergency")
    public ResponseEntity<Integer> createEmergency(@RequestBody Emergency emergency) throws Exception {
        return ResponseEntity.ok().body(emergencyService.createEmergency(emergency));
    }

    /**
     * 훈련계획 관리 계획 수정
     *
     * @param parameter
     * @return 훈련계획 관리 계획 수정
     * @throws Exception
     */
    @PutMapping("/emergency")
    public ResponseEntity<Integer> updateEmergency(@RequestBody Emergency emergency) throws Exception {
        return ResponseEntity.ok().body(emergencyService.updateEmergency(emergency));
    }

    /**
     * 훈련계획 관리 계획 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 계획 삭제
     * @throws Exception
     */
    @DeleteMapping("/emergency/{safTrainPlanNo}")
    public ResponseEntity<Integer> deleteEmergency(@PathVariable("safTrainPlanNo") int safTrainPlanNo) throws Exception {
        return ResponseEntity.ok().body(emergencyService.deleteEmergency(safTrainPlanNo));
    }

    /**
     * 훈련계획 관리 대상부서 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 대상부서 삭제
     * @throws Exception
     */
    @DeleteMapping("/emergencyDept")
    public ResponseEntity<Integer> deleteEmergencyDept(@RequestBody List<EmergencyDept> emergencyDept) throws Exception {
        return ResponseEntity.ok().body(emergencyService.deleteEmergencyDept(emergencyDept));
    }

    /**
     * 훈련계획 관리 계획 완료처리
     *
     * @param parameter
     * @return 훈련계획 관리 계획 완료처리
     * @throws Exception
     */
    @PutMapping("/emergencyComplete/{safTrainPlanNo}")
    public ResponseEntity<Integer> updateEmergencyComplete(@PathVariable("safTrainPlanNo") int safTrainPlanNo) throws Exception {
        return ResponseEntity.ok().body(emergencyService.updateEmergencyComplete(safTrainPlanNo));
    }

    /**
     * 훈련결과 관리 목록 조회
     *
     * @param parameter
     * @return 훈련결과 관리 목록 조회
     * @throws Exception
     */
    @GetMapping("/emergencyresultlists")
    public ResponseEntity<List<EmergencyDept>> getEmergencyResultLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        if ("합계".equals(plantCd)) {
            plantCd = "";
        }
        // 훈련기간
        String[] trainDt = this.requestMapper.convertObjectListAsStringArray(map.get("trainDt"));
        String startDt = "";
        String endDt = "";

        if (trainDt != null && trainDt.length == 2) {
            startDt = trainDt[0];
            endDt = trainDt[1];
        }
        // 훈련구분
        String trainTypeCd = map.containsKey("trainTypeCd") ? map.get("trainTypeCd").toString() : "";
        // 평가명
        String trainNm = map.containsKey("trainNm") ? map.get("trainNm").toString() : "";
        // 평가장소
        String trainPlace = map.containsKey("trainPlace") ? map.get("trainPlace").toString() : "";
        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        // 단계
        String trainPlanState = map.containsKey("trainPlanState") ? map.get("trainPlanState").toString() : "";

        int monFlag = parameter.containsKey("monFlag") ? Integer.parseInt(parameter.get("monFlag").toString()) : 0;
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String gubun = map.containsKey("gubun") ? map.get("gubun").toString() : "";
        String imprChk = map.containsKey("imprChk") ? map.get("imprChk").toString() : "";
        return ResponseEntity.ok().body(emergencyService.getEmergencyResultLists(plantCd, startDt, endDt, trainTypeCd, trainNm, trainPlace, deptCd, deptSubYn, trainPlanState, monFlag, year, gubun, imprChk, defaultParam));
    }

    /**
     * 훈련결과 관리 계획 조회
     *
     * @param parameter
     * @return 훈련결과 관리 계획 조회
     * @throws Exception
     */
    @GetMapping("/emergencyresultinfo/{safTrainPlanNo}")
    public ResponseEntity<Emergency> getEmergencyResultInfo(@PathVariable("safTrainPlanNo") int safTrainPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(emergencyService.getEmergencyResultInfo(safTrainPlanNo, defaultParam));
    }

    /**
     * 무계획훈련결과 등록
     *
     * @param parameter
     * @return 무계획훈련결과 등록
     * @throws Exception
     */
    @PostMapping("/emergencyResult")
    public ResponseEntity<Integer> createEmergencyResult(@RequestBody Emergency emergency) throws Exception {
        return ResponseEntity.ok().body(emergencyService.createEmergencyResult(emergency));
    }

    /**
     * 훈련결과 수정
     *
     * @param parameter
     * @return 훈련결과 수정
     * @throws Exception
     */
    @PutMapping("/emergencyResult")
    public ResponseEntity<Integer> updateEmergencyResult(@RequestBody Emergency emergency) throws Exception {
        return ResponseEntity.ok().body(emergencyService.updateEmergencyResult(emergency));
    }

    /**
     * 훈련결과 관리 완료처리
     *
     * @param parameter
     * @return 훈련결과 관리 완료처리
     * @throws Exception
     */
    @PutMapping("/emergencyResultComplete/{safTrainPlanNo}")
    public ResponseEntity<Integer> updateEmergencyResultComplete(@PathVariable("safTrainPlanNo") int safTrainPlanNo) throws Exception {
        return ResponseEntity.ok().body(emergencyService.updateEmergencyResultComplete(safTrainPlanNo));
    }

    /**
     * 훈련결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 훈련결과 현황 목록현황 목록
     * @throws Exception
     */
    @GetMapping("/emergencyresultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getResultstatusList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String trainTypeCd = map.containsKey("trainTypeCd") ? map.get("trainTypeCd").toString() : "";
        String totalFlag = map.containsKey("totalFlag") ? map.get("totalFlag").toString() : "";
        return ResponseEntity.ok().body(emergencyService.getResultstatusList(plantCd, year, trainTypeCd, totalFlag));
    }

    /**
     * 훈련결과 개선목록
     * 
     * @param parameter
     *            검색조건
     * @return 훈련결과 개선목록
     * @throws Exception
     */
    public ResponseEntity<List<ImprAct>> getEmergencyImprList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        int monFlag = parameter.containsKey("monFlag") ? Integer.parseInt(parameter.get("monFlag").toString()) : 0;

        String trainTypeCd = map.containsKey("trainTypeCd") ? map.get("trainTypeCd").toString() : "";

        String apprFlag = map.containsKey("apprFlag") ? map.get("apprFlag").toString() : "";

        return ResponseEntity.ok().body(emergencyService.getEmergencyImprList(plantCd, monFlag, trainTypeCd, apprFlag, defaultParam));
    }

    /**
     * 훈련이수자 조회 (외부)
     *
     * @param safTrainPlanNo
     *            훈련계획번호
     * @return 훈련이수자 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/emergencyresultoutsidepersons")
    public ResponseEntity<List<EmergencyOutsidePsn>> getEmergencyOutSideUsers(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safTrainPlanNo = map.containsKey("safTrainPlanNo") ? Integer.parseInt(map.get("safTrainPlanNo").toString()) : 0;

        return ResponseEntity.ok().body(emergencyService.getEmergencyOutSideUsers(safTrainPlanNo, defaultParam));
    }
}
