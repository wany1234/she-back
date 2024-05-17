package com.she.safety.baseinfo.controller;

import com.she.safety.baseinfo.service.ObsrItmService;
import com.she.safety.model.ObsrItm;
import com.she.utils.RequestMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;
import java.util.List;

/**
 * 안전 > 기준정보 > 안전관찰항목(SK E&S)
 *
 */
@RestController
@RequestMapping("/api/saf/baseinfo/")
@Api(value = "/api/saf/baseinfo/", description = "안전 기준정보 서비스")
public class ObsrItmController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ObsrItmService obsrItmService;

    /**
     * 안전관찰항목 등록
     *
     * @param obsrItm
     *            안전관찰체크항목
     * @return 안전관찰체크항목번호
     * @throws Exception
     */
    @PostMapping("obsrItm")
    public ResponseEntity<Integer> createObsrItm(@RequestBody ObsrItm obsrItm) throws Exception {
        return ResponseEntity.ok().body(this.obsrItmService.createObsrItm(obsrItm));
    }

    /**
     * 안전관찰항목 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 안전관찰항목 목록
     * @throws Exception
     */
    @GetMapping("obsrItms")
    public ResponseEntity<List<ObsrItm>> getObsrItms(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 안전관찰 항목명
        String obsrItmNm = map.containsKey("obsrItmNm") ? map.get("obsrItmNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.obsrItmService.getObsrItms(plantCd, obsrItmNm, useYn));
    }

    /**
     * 안전관찰항목 상세 조회
     *
     * @param safObsrItmNo
     *            안전관찰체크항목번호
     * @return 안전관찰항목
     * @throws Exception
     */
    @GetMapping("obsrItm/{safObsrItmNo}")
    public ResponseEntity<ObsrItm> getObsrItm(@PathVariable("safObsrItmNo") int safObsrItmNo) throws Exception {
        return ResponseEntity.ok().body(this.obsrItmService.getObsrItm(safObsrItmNo));
    }

    /**
     * 안전관찰항목 수정
     *
     * @param obsrItm
     *            안전관찰체크항목
     * @return 안전관찰체크항목번호
     * @throws Exception
     */
    @PutMapping("obsrItm")
    public ResponseEntity<Integer> updateObsrItm(@RequestBody ObsrItm obsrItm) throws Exception {
        return ResponseEntity.ok().body(this.obsrItmService.updateObsrItm(obsrItm));
    }

    /**
     * 안전관찰항목 중복 검사
     *
     * @param parameter
     *            parameter
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("obsrItm/check")
    public ResponseEntity<Integer> checkObsrItm(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 관찰항목분류 코드
        String itmTypeCd = map.containsKey("itmTypeCd") ? map.get("itmTypeCd").toString() : "";
        // 안전관찰 항목명
        String obsrItmNm = map.containsKey("obsrItmNm") ? map.get("obsrItmNm").toString() : "";
        // 안전관찰체크항목번호
        int safObsrItmNo = map.containsKey("safObsrItmNo") ? Integer.parseInt(map.get("safObsrItmNo").toString()) : 0;

        return ResponseEntity.ok().body(this.obsrItmService.checkObsrItm(plantCd, itmTypeCd, obsrItmNm, safObsrItmNo));
    }
}
