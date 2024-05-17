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
package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.manage.model.Notice;
import com.she.manage.service.NoticeService;
import com.she.utils.RequestMapper;

/**
 * 공지사항 컨트롤러
 *
 */
@RestController
@RequestMapping("api/manage")
public class NoticeController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private NoticeService noticeService;

    /**
     * 공지사항 목록 조회
     * 
     * @param parameter
     *            검색조건
     * @return 공지사항 목록
     * @throws Exception
     */
    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String comNoticeClassCd = map.containsKey("comNoticeClassCd") ? map.get("comNoticeClassCd").toString() : "";
        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("searchPeriod"));
        String fromDate = "";
        String toDate = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromDate = searchPeriod[0];
            toDate = searchPeriod[1];
        }

        return ResponseEntity.ok().body(this.noticeService.getNotices(fromDate, toDate, comNoticeClassCd, defaultParam));
    }

    /**
     * 공지사항 상세 조회
     * 
     * @param noticeNo
     *            공지사항번호
     * @return 공지사항
     * @throws Exception
     */
    @GetMapping("/notice/{noticeNo}")
    public ResponseEntity<Notice> getNotice(@PathVariable(name = "noticeNo") int noticeNo) throws Exception {
        return ResponseEntity.ok().body(this.noticeService.getNotice(noticeNo));
    }

    /**
     * 공지사항 등록
     * 
     * @param notice
     *            공지사항
     * @return 공지사항번호
     * @throws Exception
     */
    @PostMapping("/notice")
    public ResponseEntity<Integer> createNotice(@RequestBody Notice notice) throws Exception {
        return ResponseEntity.ok().body(this.noticeService.createNotice(notice));
    }

    /**
     * 공지사항 수정
     * 
     * @param notice
     *            공지사항
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/notice")
    public ResponseEntity<Integer> updateNotice(@RequestBody Notice notice) throws Exception {
        return ResponseEntity.ok().body(this.noticeService.updateNotice(notice));
    }

    /**
     * 공지사항 삭제
     * 
     * @param noticeNo
     *            공지사항번호
     * @return 삭제행수
     * @throws Exception
     */
    @DeleteMapping("/notice/{noticeNo}")
    public ResponseEntity<Integer> deleteNotice(@PathVariable(name = "noticeNo") int noticeNo) throws Exception {
        return ResponseEntity.ok().body(this.noticeService.deleteNotice(noticeNo));
    }
}
