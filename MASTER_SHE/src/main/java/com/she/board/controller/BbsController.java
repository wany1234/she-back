package com.she.board.controller;

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

import com.she.board.model.Bbs;
import com.she.board.model.BbsReply;
import com.she.board.service.BbsService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/board")
@RestController
public class BbsController {
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private BbsService bbsService;

    @ApiOperation(value = "게시글 목록 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "word", value = "검색어", required = true, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "notierName", value = "작성자", required = true, dataType = "String", paramType = "query") })
    @GetMapping("/{bbsNo}/list")
    public ResponseEntity<List<Bbs>> getBbsList(@RequestParam HashMap<String, Object> parameter, @PathVariable int bbsNo) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String word = map.containsKey("word") ? map.get("word").toString() : "";

        return ResponseEntity.ok().body(bbsService.getList(bbsNo, word));
    }

    @ApiOperation(value = "게시글 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bbs", value = "게시글", required = true, dataType = "com.she.board.model.Bbs", paramType = "body") })
    @PostMapping("/{bbsNo}/save")
    public ResponseEntity<Bbs> setBbs(@RequestBody Bbs bbs, @PathVariable int bbsNo) throws Exception {
        return ResponseEntity.ok().body(bbsService.setBbs(bbs));
    }

    @ApiOperation(value = "게시글 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bbs", value = "게시글", required = true, dataType = "com.she.board.model.Bbs", paramType = "body") })
    @PutMapping("/{bbsNo}/save")
    public ResponseEntity<Bbs> setBbsById(@RequestBody Bbs bbs, @PathVariable int bbsNo) throws Exception {
        return ResponseEntity.ok().body(bbsService.setBbs(bbs));
    }

    @ApiOperation(value = "게시글 조회수 증가", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bbs", value = "게시글", required = true, dataType = "com.she.board.model.Bbs", paramType = "body") })
    @PutMapping("/{bbsNo}/cntup")
    public ResponseEntity<Object> setBbsSearchCnt(@RequestBody Bbs bbs, @PathVariable int bbsNo) throws Exception {
        return ResponseEntity.ok().body(bbsService.setBbsSearchCount(bbs));
    }

    @ApiOperation(value = "게시글 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bbs", value = "게시글", required = true, dataType = "com.she.board.model.Bbs", paramType = "body") })
    @DeleteMapping("/{bbsNo}/delete")
    public ResponseEntity<Object> delBbsById(@RequestBody Bbs bbs, @PathVariable int bbsNo) throws Exception {
        return ResponseEntity.ok().body(bbsService.delBbs(bbs));
    }

    @ApiOperation(value = "댓글 목록 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/{notiId}/replylist")
    public ResponseEntity<List<BbsReply>> getBbsReplyList(@PathVariable int notiId) throws Exception {
        return ResponseEntity.ok().body(bbsService.getReplyList(notiId));
    }

    @ApiOperation(value = "댓글 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "reply", value = "댓글", required = true, dataType = "com.she.board.model.BbsReply", paramType = "body") })
    @PostMapping("/{notiId}/replysave")
    public ResponseEntity<BbsReply> setBbsReply(@RequestBody BbsReply bbsReply, @PathVariable int notiId) throws Exception {
        return ResponseEntity.ok().body(bbsService.setBbsReply(bbsReply));
    }

    @ApiOperation(value = "댓글 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "reply", value = "댓글", required = true, dataType = "com.she.board.model.BbsReply", paramType = "body") })
    @PutMapping("/{notiId}/replysave")
    public ResponseEntity<BbsReply> setBbsReplyById(@RequestBody BbsReply bbsReply, @PathVariable int notiId) throws Exception {
        return ResponseEntity.ok().body(bbsService.setBbsReply(bbsReply));
    }

    @ApiOperation(value = "댓글 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "reply", value = "댓글", required = true, dataType = "com.she.board.model.BbsReply", paramType = "body") })
    @DeleteMapping("/{notiId}/replydelete")
    public ResponseEntity<Object> delBbsReplyById(@RequestBody BbsReply bbsReply, @PathVariable int notiId) throws Exception {
        return ResponseEntity.ok().body(bbsService.delBbsReply(bbsReply));
    }

}
