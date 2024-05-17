package com.she.vendor.warning.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.utils.RequestMapper;
import com.she.vendor.model.VendorWarning;
import com.she.vendor.warning.service.VendorWarningService;

@RestController
@RequestMapping("/api/vendor/warning")
public class VendorWarningController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private VendorWarningService vendorWarningService;

    /**
     * 경고 및 지적현황 목록 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/vendorwarninglist")
    public ResponseEntity<List<VendorWarning>> getVendorWarningList(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 주관부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // from
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // to
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 대상업체코드
        String tgtVendorCd = map.containsKey("tgtVendorCd") ? map.get("tgtVendorCd").toString() : "";
        // 순회명
        String checkTitle = map.containsKey("checkTitle") ? map.get("checkTitle").toString() : "";

        return ResponseEntity.ok()
                .body(vendorWarningService.getVendorWarningList(plantCd, deptCd, startDate, endDate, tgtVendorCd, checkTitle));
    }
}
