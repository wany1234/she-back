/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.vendor.subconNotice.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.she.utils.RequestMapper;
import com.she.vendor.model.VendorBoard;
import com.she.vendor.subconNotice.service.SubconNoticeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/vendor/subconnotice")
public class SubconNoticeController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SubconNoticeService subconNoticeService;

    /**
     * 협력업체 게시판 조회
     *
     * @param parameter
     * @return 협력업체 게시판 목록
     * @throws Exception
     */
    @ApiOperation(value = "협력업체 게시판 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "kindCd", value = "분류코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "vendorCd", value = "업체코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "searchPeriod", value = "기간", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "제목", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/subconnotices")
    public ResponseEntity<List<VendorBoard>> getVendorBoardList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 분류 코드
        String kindCd = map.containsKey("kindCd") ? map.get("kindCd").toString() : "";
        // 업체 코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 조회 기간
        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("searchPeriod"));
        String fromDt = "";
        String toDt = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromDt = searchPeriod[0];
            toDt = searchPeriod[1];
        }
        // 제목
        String title = map.containsKey("title") ? map.get("title").toString() : "";
        return ResponseEntity.ok().body(subconNoticeService.getVendorBoardList(plantCd, kindCd, vendorCd, title, fromDt, toDt));
    }

    /**
     * 협력업체 게시물 상세 조회
     *
     * @param boardNo
     *            게시물번호
     * @return 협력업체 게시물
     * @throws Exception
     */
    @ApiOperation(value = "협력업체 게시물 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "boardNo", value = "게시물번호", required = false, dataType = "int", paramType = "query"), })
    @GetMapping("/subconnotice/{boardNo}")
    public ResponseEntity<VendorBoard> getVendorBoard(@PathVariable(name = "boardNo") int boardNo) throws Exception {
        return ResponseEntity.ok().body(this.subconNoticeService.getVendorBoard(boardNo));
    }

    /**
     * 협력업체 게시판 게시글 등록
     * 
     * @param vendorBoard
     * @return
     * @throws Exception
     */
    @PostMapping("/subconnotice")
    public ResponseEntity<Integer> createVendorBoard(@RequestBody VendorBoard vendorBoard) throws Exception {
        return ResponseEntity.ok().body(subconNoticeService.createVendorBoard(vendorBoard));
    }

    /**
     * 협력업체 게시판 게시글 수정
     * 
     * @param vendorBoard
     * @return
     * @throws Exception
     */
    @PutMapping("/subconnotice")
    public ResponseEntity<Integer> updateVendorBoard(@RequestBody VendorBoard vendorBoard) throws Exception {
        return ResponseEntity.ok().body(subconNoticeService.updateVendorBoard(vendorBoard));
    }

    /**
     * 협력업체게시판 삭제
     * 
     * @param boardNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/subconnotice/{boardNo}")
    public ResponseEntity<Integer> deleteVendorBoard(@PathVariable("boardNo") int boardNo) throws Exception {
        return ResponseEntity.ok().body(subconNoticeService.deleteVendorBoard(boardNo));
    }
}
