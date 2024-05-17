package com.she.mgt.mgtLaw.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.mgt.mgtLaw.service.SheLawCheckService;
import com.she.mgt.model.MgtSheLawChk;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/mgt/mgtlaw")
public class SheLawCheckController {
    @Autowired
    private SheLawCheckService sheLawCheckService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 법규검토 조회
     * 
     * @param fromYmd
     *            등록기간
     * @param toYmd
     *            등록기간
     * @param lawClassCd
     *            관련법규
     * @param lawKindCd
     *            법규분류
     * @param stepCd
     *            진행단계
     * @return 법규검토 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/shelawchecks")
    public ResponseEntity<List<MgtSheLawChk>> getSheLawChecks(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("searchPeriod"));
        String fromYmd = "";
        String toYmd = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromYmd = searchPeriod[0];
            toYmd = searchPeriod[1];
        }
        String lawClassCd = map.containsKey("lawClassCd") ? map.get("lawClassCd").toString() : "";
        String lawKindCd = map.containsKey("lawKindCd") ? map.get("lawKindCd").toString() : "";
        String stepCd = map.containsKey("stepCd") ? map.get("stepCd").toString() : "";
        List<MgtSheLawChk> mgtSheLawChkList = sheLawCheckService.getSheLawChecks(fromYmd, toYmd, lawClassCd, lawKindCd,
                stepCd);

        return ResponseEntity.ok().body(mgtSheLawChkList);
    }

    /**
     * 법규검토 상세조회
     * 
     * @param sheLawChkNo
     *            법규검토번호
     * @return MgtSheLawChk 법규검토
     * @throws Exception
     *             예외
     */
    @GetMapping("/shelawcheck/{sheLawChkNo}")
    public ResponseEntity<MgtSheLawChk> getSheLawCheck(@PathVariable("sheLawChkNo") int sheLawChkNo) throws Exception {
        MgtSheLawChk mgtSheLawChk = sheLawCheckService.getSheLawCheck(sheLawChkNo);
        return ResponseEntity.ok().body(mgtSheLawChk);
    }

    /**
     * 법규검토 신규등록
     * 
     * @param MgtSheLawChk
     *            법규검토
     * @return sheLawChkNo 법규검토번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/shelawcheck")
    public ResponseEntity<Integer> createSheLawCheck(@RequestBody MgtSheLawChk mgtSheLawChk) throws Exception {
        return ResponseEntity.ok().body(sheLawCheckService.createSheLawCheck(mgtSheLawChk));
    }

    /**
     * 법규검토 수정
     * 
     * @param MgtSheLawChk
     *            법규검토
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/shelawcheck")
    public ResponseEntity<Integer> updateSheLawCheck(@RequestBody MgtSheLawChk mgtSheLawChk) throws Exception {
        return ResponseEntity.ok().body(sheLawCheckService.updateSheLawCheck(mgtSheLawChk));
    }

    /**
     * 법규검토 삭제
     * 
     * @param sheLawChkNo
     *            법규검토번호
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/shelawcheck/{sheLawChkNo}")
    public ResponseEntity<Integer> deleteSheLawCheck(@PathVariable("sheLawChkNo") int sheLawChkNo) throws Exception {
        return ResponseEntity.ok().body(sheLawCheckService.deleteSheLawCheck(sheLawChkNo));
    }
}
