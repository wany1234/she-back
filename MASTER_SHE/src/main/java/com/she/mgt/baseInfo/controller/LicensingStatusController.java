package com.she.mgt.baseInfo.controller;

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
import com.she.mgt.baseInfo.service.LicensingStatusService;
import com.she.mgt.model.LicensingStatus;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/board/licensingStatus")
public class LicensingStatusController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private LicensingStatusService licensingStatusService;

    /**
     * 경영 인허가현황 조회
     * 
     * @param fromDate,
     *            toDate, title, kindCd
     * @return 인허가현황
     * @throws Exception
     */

    @GetMapping()
    public ResponseEntity<List<LicensingStatus>> getLicensingStatusList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {

        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("duration"));
        String kindCd = map.containsKey("kindCd") ? map.get("kindCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        String title = parameter.containsKey("title") ? parameter.get("title").toString() : "";

        String fromDate = "";
        String toDate = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromDate = searchPeriod[0];
            toDate = searchPeriod[1];
        }

        List<LicensingStatus> licensingStatusList = licensingStatusService.getLicensingStatusList(fromDate, toDate, title, kindCd, plantCd, defaultParam);

        return ResponseEntity.ok().body(licensingStatusList);

    }

    /**
     * 경영 인허가현황 개정 조회
     * 
     * @param fromDate,
     *            toDate, title, kindCd
     * @return 인허가현황
     * @throws Exception
     */

    @GetMapping("/revision")
    public ResponseEntity<List<LicensingStatus>> getRevision(@RequestParam String lcnBoardNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        List<LicensingStatus> licensingStatusRevisionList = licensingStatusService.getLicensingStatusRevisionList(lcnBoardNo, defaultParam);
        return ResponseEntity.ok().body(licensingStatusRevisionList);
    }

    /**
     * 경영 인허가현황 단건 조회
     * 
     * @param lcnBoardNo
     * @return 인허가현황
     * @throws Exception
     */

    @GetMapping("/{lcnBoardNo}")
    public ResponseEntity<LicensingStatus> getLicensingStatus(@PathVariable("lcnBoardNo") int lcnBoardNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(licensingStatusService.getLicensingStatus(lcnBoardNo, defaultParam));
    }

    /**
     * 경영 인허가현황 삭제
     * 
     * @param lcnBoardNo
     * @return 건수
     * @throws Exception
     */
    @DeleteMapping("/{lcnBoardNo}")
    public ResponseEntity<Integer> deleteLicensingStatus(@PathVariable("lcnBoardNo") int dataBoardNo) throws Exception {
        return ResponseEntity.ok().body(licensingStatusService.deleteLicensingStatus(dataBoardNo));
    }

    /**
     * 경영 인허가현황 개정이력 삭제 추가
     * 
     * @param LicensingStatus
     * @return 생성 행 수
     * @throws Exception
     */
    @DeleteMapping("/revdelete")
    public ResponseEntity<Integer> revDeleteLicensingStatus(@RequestBody List<LicensingStatus> licensingStatus) throws Exception {
        return ResponseEntity.ok().body(this.licensingStatusService.revDeleteLicensingStatus(licensingStatus));
    }

    /**
     * 경영 인허가현황 등록
     * 
     * @param LicensingStatus
     * @return 인허가현황 등록 수
     * @throws Exception
     */
    @PostMapping()
    public ResponseEntity<Integer> insertLicensingStatus(@RequestBody LicensingStatus dto) throws Exception {
        int insertCount = this.licensingStatusService.insertLicensingStatus(dto);
        return ResponseEntity.ok().body(dto.getLcnBoardNo());
    }

    /**
     * 경영 인허가현황 수정
     * 
     * @param LicensingStatus
     * @return 인허가현황 수정 수
     * @throws Exception
     */
    @PutMapping
    public ResponseEntity<Integer> updateLicensingStatus(@RequestBody LicensingStatus dto) throws Exception {
        int insertCount = this.licensingStatusService.updateLicensingStatus(dto);
        return ResponseEntity.ok().body(dto.getLcnBoardNo());
    }

}
