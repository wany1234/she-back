package com.she.mgt.perRptData.controller;

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
import com.she.mgt.model.MgtPerRptData;
import com.she.mgt.perRptData.service.MgtPerRptDataService;
import com.she.utils.RequestMapper;

@RequestMapping("/api/mgt")
@RestController
public class MgtPerRptDataController {
    @Autowired
    private MgtPerRptDataService mgtPerRptDataSerivce;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 중처법 정기보고자료 목록조회
     * 
     * @param startYear
     *            시작년도
     * @param endYear
     *            종료년도
     * @param startDt
     *            보고시작일
     * @param endDt
     *            보고끝
     * @param deptCd
     *            주관부서
     * @param halfTypeCd
     *            구분 코드
     * @param rptNm
     *            보고명
     * @param procStepCd
     *            단계
     * @param stepCd
     *            상태(작성중/확정)
     * @return 중처법 정기보고자료 관리 목록
     * @throws Exception
     */
    @GetMapping("/perrptdatas")
    public ResponseEntity<List<MgtPerRptData>> getMtgPerRptDatas(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String[] year = requestMapper.convertObjectListAsStringArray(map.get("year"));
        String startYear = "";
        String endYear = "";
        if (year != null && year.length == 2) {
            startYear = year[0];
            endYear = year[1];
        }

        String[] rptDt = requestMapper.convertObjectListAsStringArray(map.get("rptDt"));
        String startDt = "";
        String endDt = "";
        if (rptDt != null && rptDt.length == 2) {
            startDt = rptDt[0];
            endDt = rptDt[1];
        }

        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 구분
        String halfTypeCd = map.containsKey("halfTypeCd") ? map.get("halfTypeCd").toString() : "";

        // 보고명
        String rptNm = map.containsKey("rptNm") ? map.get("rptNm").toString() : "";

        // 단계
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";

        // 상태
        String stepCd = map.containsKey("stepCd") ? map.get("stepCd").toString() : "";

        return ResponseEntity.ok().body(mgtPerRptDataSerivce.getMgtPerRptDatas(startYear, endYear, startDt, endDt, deptCd, halfTypeCd, rptNm, procStepCd, stepCd, defaultParam));
    }

    /**
     * 중처법 정기보고자료 관리 상세조회
     * 
     * @param perRptDataNo
     *            예산집행 번호
     * @return 예산집행 상세
     * @throws Exception
     */
    @GetMapping("/perrptdata/{perRptDataNo}")
    public ResponseEntity<MgtPerRptData> getMgtPerRptData(@PathVariable("perRptDataNo") int perRptDataNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(mgtPerRptDataSerivce.getMgtPerRptData(perRptDataNo, defaultParam));
    }

    /**
     * 중처법 정기보고자료 신규등록
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료 관리
     * @return 결과
     * @throws Exception
     */
    @PostMapping("/perrptdata")
    public ResponseEntity<MgtPerRptData> insertMgtPerRptData(@RequestBody MgtPerRptData mgtPerRptData) throws Exception {
        return ResponseEntity.ok().body(mgtPerRptDataSerivce.insertMgtPerRptData(mgtPerRptData));
    }

    /**
     * 중처법 정기보고자료 수정
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료 관리
     * @return 결과
     * @throws Exception
     */
    @PutMapping("/perrptdata")
    public ResponseEntity<MgtPerRptData> updateMgtPerRptData(@RequestBody MgtPerRptData mgtPerRptData) throws Exception {
        return ResponseEntity.ok().body(mgtPerRptDataSerivce.updateMgtPerRptData(mgtPerRptData));
    }

    /**
     * 중처법 정기보고자료 확정
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료 관리
     * @return 결과
     * @throws Exception
     */
    @PutMapping("/perrptdata/complete")
    public ResponseEntity<MgtPerRptData> compleMgtPerRptData(@RequestBody MgtPerRptData mgtPerRptData) throws Exception {
        return ResponseEntity.ok().body(mgtPerRptDataSerivce.compleMgtPerRptData(mgtPerRptData));
    }

    /**
     * 중처법 정기보고자료 삭제
     * 
     * @param perRptDataNo
     *            중처법 정기보고자료 번호
     * @return 결과
     * @throws Exception
     */
    @DeleteMapping("/perrptdata/{perRptDataNo}")
    public ResponseEntity<Integer> deleteMtgPerRptData(@PathVariable("perRptDataNo") int perRptDataNo) throws Exception {
        return ResponseEntity.ok().body(mgtPerRptDataSerivce.deleteMtgPerRptData(perRptDataNo));
    }
}
