package com.she.safety.education.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.safety.education.service.EduHrdService;
import com.she.safety.model.EduHrd;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/education")
public class EduHrdController {
    @Autowired
    private EduHrdService eduHrdService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 해당 교육과정의 교육이수자 목록
     * 
     * @param sqNo
     *            고유 키
     * @param crsCode
     *            교육과정번호
     * @param sqYear
     *            연도
     * @return EduCourse 교육과정
     * @throws Exception
     *             예외
     */

    @GetMapping("/eduhrd")
    public ResponseEntity<List<EduHrd>> getEduHrd(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int sqNo = map.containsKey("sqNo") ? Integer.parseInt(map.get("sqNo").toString()) : 0;

        String crsCode = map.containsKey("crsCode") ? map.get("crsCode").toString() : "";
        String sqYear = map.containsKey("sqYear") ? map.get("sqYear").toString() : "";

        List<EduHrd> eduHrds = eduHrdService.getEduHrd(sqNo, crsCode, sqYear, defaultParam);

        return ResponseEntity.ok().body(eduHrds);
    }

}
