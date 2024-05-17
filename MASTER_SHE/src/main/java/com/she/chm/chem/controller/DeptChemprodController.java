package com.she.chm.chem.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.she.chm.chem.service.DeptChemprodService;
import com.she.chm.model.DeptChemprod;
import com.she.chm.model.DeptProcessChemProds;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/deptchemprod")
public class DeptChemprodController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DeptChemprodService deptChemprodService;

    /**
     * 부서공정별 취급자재 조회
     *
     * @param parameter
     *            검색조건
     * @return 부서공정별 취급자재 목록
     * @throws Exception
     */
    @GetMapping("/deptchemprods")
    public ResponseEntity<List<DeptChemprod>> getDeptChemprods(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 부서명
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 검색어 (취급자재명, 공급업체명, 제조업체명)
        String search = map.containsKey("search") ? map.get("search").toString() : "";

        List<DeptChemprod> deptChemprods = deptChemprodService.getDeptChemprods(plantCd, deptCd, processCd, search, useYn, defaultParam);
        return ResponseEntity.ok().body(deptChemprods);
    }

    /**
     * 부서공정별 취급자재 목록 조회
     *
     * @param processCd
     *            공정번호
     * @return 부서공정별 취급자재
     * @throws Exception
     */
    @GetMapping("/deptchemprod")
    public ResponseEntity<DeptProcessChemProds> getDeptChemprod(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        return ResponseEntity.ok().body(this.deptChemprodService.getDeptChemprod(processCd, defaultParam));
    }

    /**
     * 부서공정별 취급자재 저장
     *
     * @param DeptChemprod
     *            부서공정별 취급자재
     * @return 부서공정별 취급자재 번호
     * @throws Exception
     *
     */
    @PostMapping("/deptchemprod")
    public ResponseEntity<DeptProcessChemProds> createDeptChemprod(@RequestBody DeptProcessChemProds deptProcessChemProds) throws Exception {
        return ResponseEntity.ok().body(this.deptChemprodService.createDeptChemprod(deptProcessChemProds));
    }

    /**
     * 부서공정별 취급자재 수정
     *
     * @param DeptChemprod
     *            부서공정별 취급자재
     * @return 부서공정별 취급자재 번호
     * @throws Exception
     *
     */
    @PutMapping("/deptchemprod")
    public ResponseEntity<DeptProcessChemProds> updateDeptChemprod(@RequestBody DeptProcessChemProds deptProcessChemProds) throws Exception {
        return ResponseEntity.ok().body(this.deptChemprodService.updateDeptChemprod(deptProcessChemProds));
    }

    /**
     * 부서공정별 취급자재 체크
     *
     * @param parameter
     *            검색조건
     * @return 체크 목록
     * @throws Exception
     */
    @GetMapping("/checkdeptchemprod")
    public ResponseEntity<HashMap<String, Object>> getCheckDeptChemProd(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 부서 코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정 코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        return ResponseEntity.ok().body(deptChemprodService.getCheckDeptChemProd(deptCd, processCd));
    }

}
