package com.she.safety.education.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.safety.education.service.NewHireService;
import com.she.safety.model.NewHire;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/education")
public class NewHireController {

    @Autowired
    private NewHireService newHireService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 신규채용직원 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 신규채용직원 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/newhires")
    public ResponseEntity<List<NewHire>> getNewHires(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String employmentYn = map.containsKey("employmentYn") ? map.get("employmentYn").toString() : "";
        String eduCompletYn = map.containsKey("eduCompletYn") ? map.get("eduCompletYn").toString() : "";
        String checkupCompletYn = map.containsKey("checkupCompletYn") ? map.get("checkupCompletYn").toString() : "";
        // 입사일
        String[] entryYmd = this.requestMapper.convertObjectListAsStringArray(map.get("entryYmd"));
        String startYmd = "";
        String endYmd = "";

        if (entryYmd != null && entryYmd.length == 2) {
            startYmd = entryYmd[0];
            endYmd = entryYmd[1];
        }

        List<NewHire> newHireList = newHireService.getNewHires(deptCd, deptSubYn, userNm, plantCd, employmentYn, eduCompletYn, checkupCompletYn, startYmd, endYmd, defaultParam);

        return ResponseEntity.ok().body(newHireList);
    }

    /**
     * 신규채용직원 상세조회
     *
     * @param userId
     *            사용자아이디
     * @return 신규채용직원 상세조회
     * @throws Exception
     *             예외
     */
    @GetMapping("/newhire/{userId}")
    public ResponseEntity<NewHire> getNewHire(@PathVariable String userId) throws Exception {
        return ResponseEntity.ok().body(newHireService.getNewHire(userId));
    }

    /**
     * 신규채용 교육목록 조회
     * 
     * @return 신규채용 교육목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/newhireedulist")
    public ResponseEntity<List<NewHire>> getNewHireEduList(@ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(newHireService.getNewHireEduList(defaultParam));
    }

    /**
     * 교육이수여부 확인
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @param userId
     *            사용자아이디
     * @return 교육이수여부 확인
     * @throws Exception
     *             예외
     */
    @GetMapping("/checkeducomplete")
    public ResponseEntity<Integer> getCheckEduComplete(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 교육마스터번호
        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt("".equals(map.get("safEduMstNo").toString()) ? "0" : map.get("safEduMstNo").toString()) : 0;
        // 사용자id
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(newHireService.getCheckEduComplete(safEduMstNo, userId));
    }

    /**
     * 신규채용 교육이수여부 확인
     * 
     * @param userId
     *            사용자아이디
     * @return 신규채용 교육이수여부 확인
     * @throws Exception
     *             예외
     */
    @GetMapping("/checknewhireeducomplete")
    public ResponseEntity<Integer> getCheckNewHireEduComplete(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사용자id
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(newHireService.getCheckNewHireEduComplete(userId));
    }

    /**
     * 신규채용 교육 이수자 등록
     * 
     * @param List<NewHire>
     *            신규채용직원 목록
     * @return 신규채용 교육 이수자 등록
     * @throws Exception
     *             예외
     */
    @PostMapping("/newhireeduuser")
    public ResponseEntity<Integer> createNewHireEduUser(@RequestBody List<NewHire> newHireList) throws Exception {
        return ResponseEntity.ok().body(newHireService.createNewHireEduUser(newHireList));
    }

    /**
     * 건강검진 대상자 등록 확인
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @return 건강검진 대상자 등록 확인
     * @throws Exception
     *             예외
     */
    @GetMapping("/chkcheckupuser")
    public ResponseEntity<Integer> getChkCheckupUser(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 건강검진계획번호
        int heaCheckupPlanNo = map.containsKey("safEduMstNo") ? Integer.parseInt("".equals(map.get("heaCheckupPlanNo").toString()) ? "0" : map.get("heaCheckupPlanNo").toString()) : 0;
        // 사용자id
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(newHireService.getChkCheckupUser(heaCheckupPlanNo, userId));
    }

    /**
     * 신규채용 건강검진 대상자 등록 확인
     * 
     * @param userId
     *            사용자아이디
     * @return 신규채용 건강검진 대상자 등록 확인
     * @throws Exception
     *             예외
     */
    @GetMapping("/chkNewHireCheckupUser")
    public ResponseEntity<Integer> getChkNewHireCheckupUser(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사용자id
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(newHireService.getChkNewHireCheckupUser(userId));
    }
}
