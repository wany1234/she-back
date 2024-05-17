package com.she.chm.chem.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.she.chm.chem.service.ChemPermitProdHisService;
import com.she.chm.model.ChemPermitProdHis;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/chem")
public class ChemPermitProdHisController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemPermitProdHisService chemPermitProdHisService;

    /**
     * 인허가신고 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/permitprodhises")
    public ResponseEntity<List<ChemPermitProdHis>> getChemPermitProdHises(
            @RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 인허가종류번호
        int permitClsNo = map.containsKey("permitClsNo")
                ? Integer.parseInt(
                        "".equals(map.get("permitClsNo").toString()) ? "0" : map.get("permitClsNo").toString())
                : 0;

        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("searchPeriod"));
        String fromYmd = ""; // 신고일시작일
        String toYmd = ""; // 신고일마침일
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromYmd = searchPeriod[0];
            toYmd = searchPeriod[1];
        }

        return ResponseEntity.ok()
                .body(chemPermitProdHisService.getChemPermitProdHises(useYn, plantCd, permitClsNo, fromYmd, toYmd));
    }

    /**
     * 인허가신고 상세조회
     *
     * @param permitProdHisNo
     * @return
     * @throws Exception
     */
    @GetMapping("/permitprodhis/{permitProdHisNo}")
    public ResponseEntity<ChemPermitProdHis> getChemPermitProdHis(
            @PathVariable(name = "permitProdHisNo") int permitProdHisNo) throws Exception {
        return ResponseEntity.ok().body(this.chemPermitProdHisService.getChemPermitProdHis(permitProdHisNo));
    }

    /**
     * 인허가신고 중복검사
     *
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/checkpermitprodhis")
    public ResponseEntity<List<HashMap<String, Object>>> checkChemPermitProdHis(
            @RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 인허가종류번호
        int permitClsNo = map.containsKey("permitClsNo")
                ? Integer.parseInt(
                        "".equals(map.get("permitClsNo").toString()) ? "0" : map.get("permitClsNo").toString())
                : 0;
        // 인허가신고일
        String permitDt = map.containsKey("permitDt") ? map.get("permitDt").toString() : "";
        // 인허가신고번호
        int permitProdHisNo = map.containsKey("permitProdHisNo")
                ? Integer.parseInt(
                        "".equals(map.get("permitProdHisNo").toString()) ? "0" : map.get("permitProdHisNo").toString())
                : 0;

        return ResponseEntity.ok().body(
                this.chemPermitProdHisService.checkChemPermitProdHis(plantCd, permitClsNo, permitDt, permitProdHisNo));
    }

    /**
     * 인허가신고 등록
     *
     * @param chemPermitProdHis
     * @return
     * @throws Exception
     */
    @PostMapping("/permitprodhis")
    public ResponseEntity<ChemPermitProdHis> createMsds(@RequestBody ChemPermitProdHis chemPermitProdHis)
            throws Exception {
        return ResponseEntity.ok().body(this.chemPermitProdHisService.createChemPermitProdHis(chemPermitProdHis));
    }

    /**
     * 인허가신고 수정
     *
     * @param chemPermitProdHis
     * @return
     * @throws Exception
     */
    @PutMapping("/permitprodhis")
    public ResponseEntity<ChemPermitProdHis> updateMsds(@RequestBody ChemPermitProdHis chemPermitProdHis)
            throws Exception {
        return ResponseEntity.ok().body(this.chemPermitProdHisService.updateChemPermitProdHis(chemPermitProdHis));
    }

    /**
     * 인허가현황 개정 조회
     *
     * @param permitProdHisNo
     * @return 인허가현황
     * @throws Exception
     */

    @GetMapping("/revisions")
    public ResponseEntity<List<ChemPermitProdHis>> getRevision(@RequestParam String permitProdHisNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        List<ChemPermitProdHis> ChemPermitProdHisRevisionList = chemPermitProdHisService.getChemPermitProdHisRevisionList(permitProdHisNo, defaultParam);
        return ResponseEntity.ok().body(ChemPermitProdHisRevisionList);
    }

    /**
     * 인허가현황 개정이력 삭제 추가
     *
     * @param ChemPermitProdHisList
     * @return 생성 행 수
     * @throws Exception
     */
    @DeleteMapping("/revdelete")
    public ResponseEntity<Integer> revDeleteChemPermitProdHisStatus(@RequestBody List<ChemPermitProdHis> ChemPermitProdHisList) throws Exception {
        return ResponseEntity.ok().body(this.chemPermitProdHisService.revDeleteChemPermitProdHisStatus(ChemPermitProdHisList));
    }

    /**
     * 인허가현황 삭제
     *
     * @param permitProdHisNo
     * @return 생성 행 수
     * @throws Exception
     */
    @DeleteMapping("/permit-prod-his-status/{permitProdHisNo}")
    public ResponseEntity<Integer> deleteChemPermitProdHisStatus(@PathVariable( name = "permitProdHisNo") int permitProdHisNo) throws Exception {
        return ResponseEntity.ok().body(this.chemPermitProdHisService.deleteChemPermitProdHisStatus(permitProdHisNo));
    }
}
