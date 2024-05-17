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
import org.springframework.web.bind.annotation.DeleteMapping;

import com.she.chm.baseInfo.service.ChemicalReagentInoutService;
import com.she.chm.model.ChemicalReagentInout;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/api/chm/baseinfo", description = "시약입출고")
@RestController
@RequestMapping("api/chm/baseinfo")
public class ChemicalReagentInoutController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalReagentInoutService chemicalReagentInoutService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String ADD_JSON = "시약입출고 등록 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"plantCd\": \"1000\", " + STR_ENTER + PADDING_FOUR + "\"labNm\": \"OO실험실\", " + STR_ENTER + PADDING_FOUR + "\"deptCd\": \"30E1300\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR
            + "\"useYn\": \"Y\" " + STR_ENTER + "} ";

    private static final String EDIT_JSON = "시약입출고 수정 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"labNo\": \"1\", " + STR_ENTER + PADDING_FOUR + "\"plantCd\": \"1000\", " + STR_ENTER + PADDING_FOUR + "\"labNm\": \"OO실험실\", " + STR_ENTER + PADDING_FOUR + "\"deptCd\": \"30E1300\", " + STR_ENTER + PADDING_FOUR
            + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER + "} ";

    /**
     * 시약입출고 조회
     *
     * @param chemProdNm
     *            시약명(취급자재명)
     * @param inoutFlag
     *            입출고구분
     * @param labNo
     *            실험실번호
     * @param fromYmd
     *            입출고일 검색조건 시작일
     * @param toYmd
     *            입출고일 검색조건 마침일
     * @return 시약입출고 목록
     */
    @ApiOperation(value = "시약입출고 조회[CHM05001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemProdNm", value = "시약명(취급자재명)", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "inoutFlag", value = "입출고구분", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "labNo", value = "실험실번호", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "searchPeriod", value = "입출고일 검색조건", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemicalreagentinouts")
    public ResponseEntity<List<ChemicalReagentInout>> getChemicalReagentInouts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("searchPeriod"));
        String fromYmd = "";
        String toYmd = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromYmd = searchPeriod[0];
            toYmd = searchPeriod[1];
        }

        String chemProdNm = map.containsKey("chemProdNm") ? map.get("chemProdNm").toString() : "";
        String inoutFlag = map.containsKey("inoutFlag") ? map.get("inoutFlag").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        int labNo = map.containsKey("labNo") ? Integer.parseInt("".equals(map.get("labNo").toString()) ? "0" : map.get("labNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalReagentInoutService.getChemicalReagentInouts(chemProdNm, inoutFlag, plantCd, labNo, fromYmd, toYmd, defaultParam));
    }

    /**
     * 시약입출고 상세 조회
     *
     * @param reagentInoutNo
     *            시약입출고번호
     * @return 시약입출고
     * @throws Exception
     */
    @ApiOperation(value = "시약입출고 상세조회[CHM05002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "reagentInoutNo", value = "시약입출고번호", required = true, dataType = "long", paramType = "path") })
    @GetMapping("/chemicalreagentinout/{reagentInoutNo}")
    public ResponseEntity<ChemicalReagentInout> getChemicalReagentInout(@PathVariable(name = "reagentInoutNo") int reagentInoutNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemicalReagentInoutService.getChemicalReagentInout(reagentInoutNo, defaultParam));
    }

    /**
     * 시약입출고 신규등록
     *
     * @param chemicalReagentInout
     *            시약입출고
     * @return 등록 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시약입출고 등록[CHM05003]", notes = ADD_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalReagentInout", value = "시약입출고정보", required = false, dataType = "ChemicalReagentInout", paramType = "body") })
    @PostMapping("/chemicalreagentinout")
    public ResponseEntity<Integer> createChemicalReagentInout(@RequestBody ChemicalReagentInout chemicalReagentInout) throws Exception {
        return ResponseEntity.ok().body(this.chemicalReagentInoutService.createChemicalReagentInout(chemicalReagentInout));
    }

    /**
     * 시약입출고 수정
     *
     * @param chemicalReagentInout
     *            시약입출고
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시약입출고 수정[CHM05004]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalReagentInout", value = "시약입출고정보", required = false, dataType = "ChemicalReagentInout", paramType = "body") })
    @PutMapping("/chemicalreagentinout")
    public ResponseEntity<Integer> updateChemicalReagentInout(@RequestBody ChemicalReagentInout chemicalReagentInout) throws Exception {
        return ResponseEntity.ok().body(this.chemicalReagentInoutService.updateChemicalReagentInout(chemicalReagentInout));
    }

    /**
     * 시약입출고 삭제
     *
     * @param chemicalReagentInout
     *            시약입출고
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시약입출고 삭제[CHM05006]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "reagentInoutNo", value = "시약입출고번호", required = true, dataType = "long", paramType = "path") })
    @DeleteMapping("/chemicalreagentinout/{reagentInoutNo}")
    public ResponseEntity<Integer> updateChemicalReagentInout(@PathVariable(name = "reagentInoutNo") int reagentInoutNo) throws Exception {
        return ResponseEntity.ok().body(this.chemicalReagentInoutService.deleteChemicalReagentInout(reagentInoutNo));
    }

    /**
     * 시약입출고 유니크 체크 (입출고일 + 실험실번호 + 입출고구분 + 시약번호)
     *
     * @param inoutDt
     *            입출고일
     * @param labNo
     *            실험실번호
     * @param inoutFlag
     *            입출고구분
     * @param chemProdNo
     *            시약번호(취급자재)
     * @param reagentInoutNo
     *            입출고번호
     * @return 체크 값
     * @throws Exception
     */
    @ApiOperation(value = "시약입출고명 체크[CHM05005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "inoutDt", value = "입출고일", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "labNo", value = "실험실번호", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "inoutFlag", value = "입출고구분", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "chemProdNo", value = "시약번호(취급자재)", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "reagentInoutNo", value = "입출고번호", required = false, dataType = "long", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checkchemicalreagentinout")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckChemicalReagentInout(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String inoutDt = map.containsKey("inoutDt") ? map.get("inoutDt").toString() : "";
        int labNo = map.containsKey("labNo") ? Integer.parseInt("".equals(map.get("labNo").toString()) ? "0" : map.get("labNo").toString()) : 0;
        String inoutFlag = map.containsKey("inoutFlag") ? map.get("inoutFlag").toString() : "";
        int chemProdNo = map.containsKey("chemProdNo") ? Integer.parseInt("".equals(map.get("chemProdNo").toString()) ? "0" : map.get("chemProdNo").toString()) : 0;
        int reagentInoutNo = map.containsKey("reagentInoutNo") ? Integer.parseInt("".equals(map.get("reagentInoutNo").toString()) ? "0" : map.get("reagentInoutNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalReagentInoutService.getCheckChemicalReagentInout(inoutDt, labNo, inoutFlag, chemProdNo, reagentInoutNo));
    }

}
