package com.she.env.tms.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.tms.baseInfo.service.TmsStationItemStdService;
import com.she.env.tms.model.TmsStationItemStd;
import com.she.utils.Methods;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/baseinfo")
@Api(value = "/api/env/tms/baseinfo", description = "환경 tms 기준정보 서비스")
public class TmsStationItemStdController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TmsStationItemStdService tmsStationItemStdService;

    /**
     * TMS 측정소항목별 허용기준 조회
     *
     * @param parameter
     *            검색조건
     * @return TMS 측정소항목별 허용기준 목록
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소항목별 허용기준 조회[ENV14001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "tmsType", value = "측정소항목별 허용기준 구분", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/tmsstationitemstds")
    public ResponseEntity<List<HashMap<String, Object>>> getTmsStationItemStds(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 측정소항목별 허용기준 구분
        String tmsType = map.containsKey("tmsType") ? map.get("tmsType").toString() : "";
        // 측정년도
        String[] yearPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("yearPeriod"));
        String startYear = "";
        String endYear = "";
        String[] yearsLaw = null;
        String[] yearsWrn = null;
        String[] yearsOper = null;
        if (yearPeriod != null && yearPeriod.length == 2) {
            if (yearPeriod[0].length() > 4) {
                yearPeriod[0] = yearPeriod[0].substring(0, 4);
            }
            if (yearPeriod[1].length() > 4) {
                yearPeriod[1] = yearPeriod[1].substring(0, 4);
            }
            if (Methods.isInteger(yearPeriod[0])) {
                startYear = yearPeriod[0];
                endYear = yearPeriod[1];

                int start = Integer.parseInt(startYear);
                int end = Integer.parseInt(endYear);

                yearsLaw = new String[end - start + 1];
                yearsWrn = new String[end - start + 1];
                yearsOper = new String[end - start + 1];
                int index = 0;
                for (int i = start; i <= end; i++) {
                    yearsLaw[index] = "[" + String.valueOf(i) + "_law_max]";
                    yearsWrn[index] = "[" + String.valueOf(i) + "_wrn_max]";
                    yearsOper[index] = "[" + String.valueOf(i) + "_oper_max]";
                    index++;
                }
            }
        }
        // 측정소
        String stationCode = map.containsKey("stationCode") ? map.get("stationCode").toString() : "";
        // 측정항목
        String itemCode = map.containsKey("itemCode") ? map.get("itemCode").toString() : "";

        List<HashMap<String, Object>> list = null;
        if (!startYear.equals("") && yearsLaw.length != 0) {
            list = tmsStationItemStdService.getTmsStationItemStds(plantCd, tmsType, startYear, endYear, stationCode, itemCode, yearsLaw, yearsWrn, yearsOper);
        }

        return ResponseEntity.ok().body(list);
    }

    /**
     * TMS 측정소항목별 허용기준 상세 조회
     *
     * @return 측정소항목별 허용기준
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소항목별 허용기준 상세조회[ENV14002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "stationItemCode", value = "TMS 측정소항목별 허용기준코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("/tmsstationitemstd")
    public ResponseEntity<List<TmsStationItemStd>> getTmsStationItemStd(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // TMS 측정소항목별 허용기준코드
        String stationItemCode = map.containsKey("stationItemCode") ? map.get("stationItemCode").toString() : "";
        // 측정년도
        String dataYear = map.containsKey("dataYear") ? map.get("dataYear").toString() : "";

        return ResponseEntity.ok().body(this.tmsStationItemStdService.getTmsStationItemStd(stationItemCode, dataYear));
    }

    /**
     * TMS 측정소항목별 허용기준 신규등록
     *
     * @param chemicalBranch
     *            TMS 측정소항목별 허용기준
     * @return TMS 측정소항목별 허용기준 코드
     * @throws Exception
     */

    @ApiOperation(value = "TMS 측정소항목별 허용기준 등록[ENV14003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "TmsStationItemStd", value = "TMS 측정소항목별 허용기준정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PostMapping("/tmsstationitemstd")
    public ResponseEntity<Integer> saveTmsStationItemStd(@RequestBody TmsStationItemStd tmsStationItemStd) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationItemStdService.saveTmsStationItemStd(tmsStationItemStd));
    }

}
