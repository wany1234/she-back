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
package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.manage.model.Notice;

/**
 * 공지사항 맵퍼
 *
 */
@Mapper
@Repository("com.she.manage.mapper.NoticeMapper")
public interface NoticeMapper {

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
    public List<Notice> getNotices(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("comNoticeClassCd") String comNoticeClassCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공지사항 상세 조회
     * 
     * @param noticeNo
     *            공지사항번호
     * @return 공지사항
     * @throws Exception
     */
    public Notice getNotice(@Param("noticeNo") int noticeNo) throws Exception;

    /**
     * 공지사항 등록
     * 
     * @param notice
     *            공지사항
     * @return 생성행수
     * @throws Exception
     */
    public int createNotice(Notice notice) throws Exception;

    /**
     * 공지사항 수정
     * 
     * @param notice
     *            공지사항
     * @return 수정행수
     * @throws Exception
     */
    public int updateNotice(Notice notice) throws Exception;

    /**
     * 공지사항 삭제
     * 
     * @param noticeNo
     *            공지사항번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteNotice(@Param("noticeNo") int noticeNo) throws Exception;
}
