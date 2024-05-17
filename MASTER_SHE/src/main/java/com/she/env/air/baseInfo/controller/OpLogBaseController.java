package com.she.env.air.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.air.baseInfo.service.OpLogBaseService;
import com.she.env.air.model.OplogBaseMst;
import com.she.utils.RequestMapper;

/**
 * 대기 운여일지 기본정보
 *
 *
 */
@RestController("OpLogBaseController")
@RequestMapping("api/env/air/baseinfo/oplogbase")
public class OpLogBaseController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private OpLogBaseService opLogBaseService;

    /**
     * 운영일지작성구분 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/oplogbase")
    public ResponseEntity<OplogBaseMst> getOplogBaseMst(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 관리부서
        String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";

        return ResponseEntity.ok().body(this.opLogBaseService.getOplogBaseMst(plantCd, mgDeptCd));
    }

    /**
     * 운영일지작성구분 생성
     *
     * @param oplogBaseMst
     * @return
     * @throws Exception
     */
    @PostMapping("/oplogbase")
    public ResponseEntity<Integer> createOplogBase(@RequestBody OplogBaseMst oplogBaseMst) throws Exception {
        return ResponseEntity.ok().body(this.opLogBaseService.createOplogBaseMst(oplogBaseMst));
    }

    /**
     * 운영일지작성구분 수정
     *
     * @param oplogBaseMst
     * @return
     * @throws Exception
     */
    @PutMapping("/oplogbase")
    public ResponseEntity<Integer> updateOPlogBaseMst(@RequestBody OplogBaseMst oplogBaseMst) throws Exception {
        return ResponseEntity.ok().body(this.opLogBaseService.updateOPlogBaseMst(oplogBaseMst));
    }

}
