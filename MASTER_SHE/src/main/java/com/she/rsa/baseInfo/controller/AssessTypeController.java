package com.she.rsa.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.she.rsa.baseInfo.service.AssessTypeService;
import com.she.rsa.baseInfo.service.RiskMatrixService;
import com.she.rsa.model.AssessType;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/rsa/baseinfo")
public class AssessTypeController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AssessTypeService assessTypeService;

    @Autowired
    private RiskMatrixService riskMatrixService;

    /**
     * 평가기법 조회
     * 
     * @return 평가기법 목록
     * @throws Exception
     */
    @GetMapping("/assesstypes")
    public ResponseEntity<List<AssessType>> getAssessTypes(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가기법
        String assessTypeCd = map.containsKey("assessTypeCd") ? map.get("assessTypeCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String assessGroupCd = map.containsKey("assessGroupCd") ? map.get("assessGroupCd").toString() : "";
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";

        List<AssessType> assessTypes = assessTypeService.getAssessTypes(plantCd, assessGroupCd, assessTypeCd, assessNm, defaultParam);
        return ResponseEntity.ok().body(assessTypes);
    }

    /**
     * 평가기법 이력 조회
     * 
     * @return 평가기법 이력 목록
     * @throws Exception
     */
    @GetMapping("/assesstypehistorys")
    public ResponseEntity<List<AssessType>> getAssessTypeHistorys(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가그룹번호
        int assessGroupNo = map.containsKey("assessGroupNo")
                ? Integer.parseInt(
                        "".equals(map.get("assessGroupNo").toString()) ? "0" : map.get("assessGroupNo").toString())
                : 0;

        List<AssessType> assessTypes = assessTypeService.getAssessTypeHistorys(assessGroupNo, defaultParam);
        return ResponseEntity.ok().body(assessTypes);
    }

    /**
     * 평가기법 상세 조회
     * 
     * @param assessTypeNo
     *            평가기법 번호
     * @return 평가기법
     * @throws Exception
     */
    @GetMapping("/assesstype/{assessTypeNo}")
    public ResponseEntity<AssessType> getAssessType(@PathVariable(name = "assessTypeNo") String assessTypeNo, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        return ResponseEntity.ok().body(this.assessTypeService.getAssessType(assessTypeNo, defaultParam));
    }

    /**
     * 평가기법 상세 조회
     * 
     * @param assessTypeNo
     *            평가기법 번호
     * @param assessNm
     *            평가기법 Matrix명
     * @return 중복 여부
     * @throws Exception
     */
    @GetMapping("/checkassessnm")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckAssessNm(
            @RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String assessTypeNo = map.containsKey("assessTypeNo") ? map.get("assessTypeNo").toString() : "";
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";
        String assessTypeCd = map.containsKey("assessTypeCd") ? map.get("assessTypeCd").toString() : "";
        int assessGroupNo = map.containsKey("assessGroupNo")
                ? Integer.parseInt(
                        "".equals(map.get("assessGroupNo").toString()) ? "0" : map.get("assessGroupNo").toString())
                : 0;
        String revNo = map.containsKey("revNo") ? map.get("revNo").toString() : "";

        return ResponseEntity.ok().body(this.assessTypeService.getCheckAssessNm(plantCd, assessTypeNo, assessNm,
                assessTypeCd, assessGroupNo, revNo));
    }

    /**
     * 평가기법 신규등록
     * 
     * @param assessType
     *            평가기법
     * @return 평가기법 번호
     * @throws Exception
     */
    @PostMapping("/assesstype")
    public ResponseEntity<Integer> createAssessType(@RequestBody AssessType assessType) throws Exception {
        int assessTypeNo = this.assessTypeService.createAssessType(assessType);
        riskMatrixService.createRiskMatrixs(assessType);

        return ResponseEntity.ok().body(assessTypeNo);
    }

    /**
     * 평가기법 수정
     * 
     * @param assessType
     *            평가기법
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/assesstype")
    public ResponseEntity<Integer> updateAssessType(@RequestBody AssessType assessType) throws Exception {
        int assessTypeNo = this.assessTypeService.updateAssessType(assessType);
        riskMatrixService.createRiskMatrixs(assessType);

        return ResponseEntity.ok().body(assessTypeNo);
    }

}
