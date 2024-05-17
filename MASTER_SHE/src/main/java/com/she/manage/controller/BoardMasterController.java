package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.BoardMaster;
import com.she.manage.service.BoardMasterService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/manage/boardMaster")
public class BoardMasterController {
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private BoardMasterService boardMasterService;

    @ApiOperation(value = "게시판 마스터 목록 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bbsNm", value = "게시판명", required = false, dataType = "String", paramType = "query") })
    @GetMapping("/boards")
    public ResponseEntity<List<BoardMaster>> getBoardMasterList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String bbsNm = map.containsKey("bbsNm") ? map.get("bbsNm").toString() : "";
        return ResponseEntity.ok().body(boardMasterService.getList(bbsNm));
    }

    @ApiOperation(value = "게시판 마스터 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bbsNo", value = "게시판번호", required = false, dataType = "String", paramType = "query") })
    @GetMapping("/board")
    public ResponseEntity<BoardMaster> getBoardMaster(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int bbsNo = map.containsKey("bbsNo") ? Integer.parseInt(map.get("bbsNo").toString()) : 0;
        return ResponseEntity.ok().body(boardMasterService.getBoardMaster(bbsNo));
    }

    @ApiOperation(value = "게시판 신규 생성", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "boardMaster", value = "게시판마스터", required = true, dataType = "com.she.manage.model.BoardMaster", paramType = "body") })
    @PostMapping("/board")
    public ResponseEntity<BoardMaster> setBoardMaster(@RequestBody BoardMaster boardMaster) {
        return ResponseEntity.ok().body(boardMasterService.setBoardMaster(boardMaster));
    }

    @ApiOperation(value = "게시판 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "boardMaster", value = "게시판마스터", required = true, dataType = "com.she.manage.model.BoardMaster", paramType = "body") })
    @PutMapping("/board")
    public ResponseEntity<BoardMaster> setBoardMasterById(@RequestBody BoardMaster boardMaster) {
        return ResponseEntity.ok().body(boardMasterService.setBoardMaster(boardMaster));
    }

    @ApiOperation(value = "게시판 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "boardMaster", value = "게시판마스터", required = false, dataType = "com.she.manage.model.BoardMaster", paramType = "body") })
    @DeleteMapping("/board")
    public ResponseEntity<Integer> delBoardMasterById(@RequestBody BoardMaster boardMaster) throws Exception {
        return ResponseEntity.ok().body(boardMasterService.delBoardMaster(boardMaster.getBbsNo()));
    }
}
