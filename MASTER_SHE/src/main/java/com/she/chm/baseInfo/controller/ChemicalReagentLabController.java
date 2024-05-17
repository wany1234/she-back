package com.she.chm.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.she.chm.baseInfo.service.ChemicalReagentLabService;
import com.she.chm.model.ChemicalReagentLab;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/api/chm/baseinfo", description = "시약실험실")
@RestController
@RequestMapping("api/chm/baseinfo")
public class ChemicalReagentLabController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalReagentLabService chemicalReagentLabService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String ADD_JSON = "시약실험실 등록 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"plantCd\": \"1000\", " + STR_ENTER + PADDING_FOUR + "\"labNm\": \"OO실험실\", " + STR_ENTER + PADDING_FOUR + "\"deptCd\": \"30E1300\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR
            + "\"useYn\": \"Y\" " + STR_ENTER + "} ";

    private static final String EDIT_JSON = "시약실험실 수정 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"labNo\": \"1\", " + STR_ENTER + PADDING_FOUR + "\"plantCd\": \"1000\", " + STR_ENTER + PADDING_FOUR + "\"labNm\": \"OO실험실\", " + STR_ENTER + PADDING_FOUR + "\"deptCd\": \"30E1300\", " + STR_ENTER + PADDING_FOUR
            + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER + "} ";

    /**
     * 시약실험실 조회
     *
     * @param labNm
     *            시약실험실명
     * @param useYn
     *            사용여부
     * @return 시약실험실 목록
     */
    @ApiOperation(value = "시약실험실 조회[CHM04001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "labNm", value = "시약실험실명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemicalreagentlabs")
    public ResponseEntity<List<ChemicalReagentLab>> getChemicalReagentLabs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 시약실험실명
        String labNm = map.containsKey("labNm") ? map.get("labNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(chemicalReagentLabService.getChemicalReagentLabs(labNm, plantCd, useYn, defaultParam));
    }

    /**
     * 시약실험실 상세 조회
     *
     * @param labNo
     *            시약실험실번호
     * @return 시약실험실
     * @throws Exception
     */
    @ApiOperation(value = "시약실험실 상세조회[CHM04002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "labNo", value = "시약실험실번호", required = true, dataType = "long", paramType = "path") })
    @GetMapping("/chemicalreagentlab/{labNo}")
    public ResponseEntity<ChemicalReagentLab> getChemicalReagentLab(@PathVariable(name = "labNo") int labNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemicalReagentLabService.getChemicalReagentLab(labNo, defaultParam));
    }

    /**
     * 시약실험실 신규등록
     *
     * @param chemicalReagentLab
     *            시약실험실
     * @return 등록 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시약실험실 등록[CHM04003]", notes = ADD_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalReagentLab", value = "시약실험실정보", required = false, dataType = "ChemicalReagentLab", paramType = "body") })
    @PostMapping("/chemicalreagentlab")
    public ResponseEntity<Integer> createChemicalReagentLab(@RequestBody ChemicalReagentLab chemicalReagentLab) throws Exception {
        return ResponseEntity.ok().body(this.chemicalReagentLabService.createChemicalReagentLab(chemicalReagentLab));
    }

    /**
     * 시약실험실 수정
     *
     * @param chemicalReagentLab
     *            시약실험실
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시약실험실 수정[CHM04004]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalReagentLab", value = "시약실험실정보", required = false, dataType = "ChemicalReagentLab", paramType = "body") })
    @PutMapping("/chemicalreagentlab")
    public ResponseEntity<Integer> updateChemicalReagentLab(@RequestBody ChemicalReagentLab chemicalReagentLab) throws Exception {
        return ResponseEntity.ok().body(this.chemicalReagentLabService.updateChemicalReagentLab(chemicalReagentLab));
    }

    /**
     * 시약실험실명 체크 (사업장코드 + 시약실험실명)
     *
     * @param labNm
     *            바꿀 시약실험실명
     * @param labNmOrigin
     *            이전 시약실험실명
     * @param labNo
     *            시약실험실번호
     * @param plantCd
     *            사업장코드
     * @return 체크 값
     * @throws Exception
     */
    @ApiOperation(value = "시약실험실명 체크[CHM04005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "labNm", value = "시약실험실명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "labNo", value = "시약실험실번호", required = false, dataType = "long", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checkchemicalreagentlab")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckChemicalReagentLab(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 시약실험실명
        String labNm = map.containsKey("labNm") ? map.get("labNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 시약실험실번호
        int labNo = map.containsKey("labNo") ? Integer.parseInt("".equals(map.get("labNo").toString()) ? "0" : map.get("labNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalReagentLabService.getCheckChemicalReagentLab(labNm, labNo, plantCd));
    }

}
