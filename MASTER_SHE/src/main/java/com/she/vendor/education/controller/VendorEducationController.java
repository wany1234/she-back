package com.she.vendor.education.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.utils.RequestMapper;
import com.she.vendor.education.service.VendorEducationService;
import com.she.vendor.model.VendorEducation;

@RestController
@RequestMapping("/api/vendor/education")
public class VendorEducationController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private VendorEducationService vendorEducationService;

    /**
     * 협력업체 교육 목록 조회
     * 
     * @param vendorEducation
     * @return
     * @throws Exception
     */
    @GetMapping("/educationlist")
    public ResponseEntity<List<VendorEducation>> getVendorEducationList(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 교육분류코드
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        // 교육과정번호
        int safEduCourseNo = map.containsKey("safEduCourseNo")
                ? Integer.parseInt(
                        "".equals(map.get("safEduCourseNo").toString()) ? "0" : map.get("safEduCourseNo").toString())
                : 0;
        // from
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // to
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 협력업체명
        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";
        // 성명
        String workerNm = map.containsKey("workerNm") ? map.get("workerNm").toString() : "";
        // 협력업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";

        return ResponseEntity.ok().body(vendorEducationService.getVendorEducationList(plantCd, eduAttCd, safEduCourseNo,
                startDate, endDate, vendorNm, workerNm, vendorCd));
    }
}
