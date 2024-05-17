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
package com.she.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.manage.mapper.NoticeMapper;
import com.she.manage.model.Notice;

/**
 * 공지사항 기능정의
 *
 */
@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 공지사항 목록 조회
     * 
     * @param fromDate
     *            검색시작일
     * @param toDate
     *            검색종료일
     * @param comNoticeClassCd
     *            공지분류
     * @return 공지사항 목록
     * @throws Exception
     */
    public List<Notice> getNotices(String fromDate, String toDate, String comNoticeClassCd, DefaultParam defaultParam) throws Exception {
        return this.noticeMapper.getNotices(fromDate, toDate, comNoticeClassCd, defaultParam);
    }

    /**
     * 공지사항 상세 조회
     * 
     * @param noticeNo
     *            공지사항번호
     * @return 공지사항
     * @throws Exception
     */
    public Notice getNotice(int noticeNo) throws Exception {
        return this.noticeMapper.getNotice(noticeNo);
    }

    /**
     * 공지사항 등록
     * 
     * @param notice
     *            공지사항
     * @return 공지사항번호
     * @throws Exception
     */
    public int createNotice(Notice notice) throws Exception {
        this.noticeMapper.createNotice(notice);
        return notice.getNoticeNo();
    }

    /**
     * 공지사항 수정
     * 
     * @param notice
     *            공지사항
     * @return 수정행수
     * @throws Exception
     */
    public int updateNotice(Notice notice) throws Exception {
        return this.noticeMapper.updateNotice(notice);
    }

    /**
     * 공지사항 삭제
     * 
     * @param noticeNo
     *            공지사항번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteNotice(int noticeNo) throws Exception {
        return this.noticeMapper.deleteNotice(noticeNo);
    }
}
