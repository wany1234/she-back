package com.she.env.water.baseInfo.controller;

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
import com.she.env.water.baseInfo.service.OperationBasicUnitService;
import com.she.env.water.model.OperationCleanFacDept;
import com.she.env.water.model.WasteBasicUnit;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/baseinfo/basicunit")
public class OperationBasicUnitController {

    @Autowired
    private OperationBasicUnitService basicUnitService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 운영일지 설정관리 전체 조회
     *
     * @param plantCd
     * @param deptCd
     * @return
     * @throws Exception
     */
    @GetMapping("/getewtrCleans")
    public ResponseEntity<List<WasteBasicUnit>> getEwtrCleans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String ewtrCleanFacNm = map.containsKey("ewtrCleanFacNm") ? map.get("ewtrCleanFacNm").toString() : "";
        System.out.println("ewtrCleanFacNm@@@" + ewtrCleanFacNm);
        return ResponseEntity.ok().body(basicUnitService.getEwtrCleans(plantCd, deptCd, ewtrCleanFacNm));
    }

    /**
     * 운영일지 설정관리 작성부서 조회
     *
     * @param ewtrCleanFacNo
     * @return
     * @throws Exception
     */
    @GetMapping("/getewtrdeptlist/{ewtrCleanFacNo}")
    public ResponseEntity<List<OperationCleanFacDept>> getDeptList(@PathVariable("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception {
        return ResponseEntity.ok().body(basicUnitService.getDeptList(ewtrCleanFacNo));
    }

    /**
     * 운영일지 설정관리 작성부서 등록
     *
     * @param operationCleanFacDept
     * @return
     * @throws Exception
     */
    @PostMapping("/insertoplogbasedept")
    public ResponseEntity<Integer> insertOplogBaseDept(@RequestBody List<OperationCleanFacDept> operationCleanFacdept) throws Exception {

        return ResponseEntity.ok().body(basicUnitService.insertOplogBaseDept(operationCleanFacdept));
    }

}
