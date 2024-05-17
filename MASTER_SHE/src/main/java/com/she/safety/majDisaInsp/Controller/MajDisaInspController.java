package com.she.safety.majDisaInsp.Controller;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
import com.she.safety.majDisaInsp.Service.MajDisaInspService;
import com.she.safety.model.MajDisaInsp;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/saf/majdisainsp/")
public class MajDisaInspController {

    @Autowired
    private MajDisaInspService majDisaInspService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 중대시민재하점검 관리 등록
     *
     * @param majDisaInsp
     * @return
     * @throws Exception
     */
    @PostMapping("majdisainsp")
    public ResponseEntity<MajDisaInsp> insertMajDisaInsp(@RequestBody MajDisaInsp majDisaInsp) throws Exception {
        return ResponseEntity.ok().body(majDisaInspService.insertMajDisaInsp(majDisaInsp));

    }

    /**
     * 중대시민재하점검 관리 수정
     *
     * @param majDisaInsp
     * @return
     * @throws Exception
     */
    @PutMapping("majdisainsp")
    public ResponseEntity<MajDisaInsp> updateMajDisaInsp(@RequestBody MajDisaInsp majDisaInsp) throws Exception {
        return ResponseEntity.ok().body(majDisaInspService.updateMajDisaInsp(majDisaInsp));

    }

    /**
     * 중대시민재해점검 관리 조회
     *
     * @param plantCd
     * @param startDt
     * @param endDt
     * @param mainDeptCd
     * @param chkNm
     * @param unRegiYn
     * @param stateCd
     * @return
     * @throws Exception
     */
    @GetMapping("majdisainsps")
    public ResponseEntity<List<MajDisaInsp>> getMajDisaInsps(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        String chkNm = map.containsKey("chkNm") ? map.get("chkNm").toString() : "";
        String unRegiYn = map.containsKey("unRegiYn") ? map.get("unRegiYn").toString() : "";
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        int monFlag = map.containsKey("monFlag") ? Integer.parseInt(map.get("monFlag").toString()) : 0;
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String imprChk = map.containsKey("imprChk") ? map.get("imprChk").toString() : "";
        String startDt = "";
        String endDt = "";

        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));

        if (period != null && period.length == 2) {
            startDt = period[0];
            endDt = period[1];
        }

        return ResponseEntity.ok().body(majDisaInspService.getMajDisaInsps(plantCd, startDt, endDt, mainDeptCd, chkNm, unRegiYn, stateCd, imprChk, defaultParam));
    }

    /**
     * 중대시민재해점검 관리 상세
     *
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    @GetMapping("majdisainsp/{majDisaInspNo}")
    public ResponseEntity<MajDisaInsp> getMajDisaInsp(@PathVariable("majDisaInspNo") int majDisaInspNo) throws Exception {
        return ResponseEntity.ok().body(majDisaInspService.getMajDisaInsp(majDisaInspNo));
    }

    /**
     * 중대시민재해점검 삭제
     *
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("majdisainsp/{majDisaInspNo}")
    public ResponseEntity<Integer> delMajDisaInsp(@PathVariable("majDisaInspNo") int majDisaInspNo) throws Exception {
        return ResponseEntity.ok().body(majDisaInspService.delMajDisaInsp(majDisaInspNo));
    }

    /**
     * 중대시민재해점검 현황
     *
     * @param plantCd
     * @param year
     * @return
     * @throws Exception
     */
    @GetMapping("majdisainspstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getStatusList(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception {

        return ResponseEntity.ok().body(majDisaInspService.getStatusList(plantCd, year));
    }

}
