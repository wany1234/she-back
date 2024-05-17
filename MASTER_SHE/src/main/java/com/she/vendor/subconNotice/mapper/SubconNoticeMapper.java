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
package com.she.vendor.subconNotice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.vendor.model.VendorBoard;
import com.she.vendor.model.VendorBoardVendor;
import com.she.vendor.model.VendorBoardVendorAtt;

@Mapper
@Repository("com.she.vendor.subconNotice.mapper.SubconNoticeMapper")
public interface SubconNoticeMapper {
    /**
     * 협력업체 게시판 조회
     * 
     * @param fromDt
     *            조회시작일
     * @param toDt
     *            조회종료일
     * @param vendorCd
     *            업체코드
     * @param title
     *            제목
     * @param kindCd
     *            유형코드
     * @param plantCd
     *            사업장코드
     * @return 협력업체 게시판 목록
     * @throws Exception
     */
    public List<VendorBoard> getVendorBoardList(@Param("plantCd") String plantCd, @Param("kindCd") String kindCd,
            @Param("vendorCd") String vendorCd, @Param("title") String title, @Param("fromDt") String fromDt,
            @Param("toDt") String toDt) throws Exception;

    /**
     * 협력업체 게시물 상세 조회
     * 
     * @param boardNo
     *            게시물번호
     * @return 협력업체게시물
     * @throws Exception
     */
    public VendorBoard getVendorBoard(@Param("boardNo") int boardNo) throws Exception;

    /**
     * 협력업체 게시물 상세 조회 - 조회 업체 범위
     * 
     * @param boardNo
     *            게시물번호
     * @return 협력업체게시물 - 조회 업체 범위
     * @throws Exception
     */
    public List<VendorBoardVendor> getVendorBoardVendorList(@Param("boardNo") int boardNo) throws Exception;

    /**
     * 협력업체 게시물 상세 조회 - 조회 업체유형 범위
     * 
     * @param boardNo
     *            게시물번호
     * @return 협력업체게시물 - 조회 업체유형 범위
     * @throws Exception
     */
    public List<VendorBoardVendorAtt> getVendorBoardVendorAttList(@Param("boardNo") int boardNo) throws Exception;

    /**
     * 협력업체게시판 등록
     * 
     * @param vendorBoard
     * @return
     * @throws Exception
     */
    public int createVendorBoard(VendorBoard vendorBoard) throws Exception;

    /**
     * 협력업체게시판 조회업체 등록
     * 
     * @param vendorBoardVendor
     * @return
     * @throws Exception
     */
    public int createVendorBoardVendor(VendorBoardVendor vendorBoardVendor) throws Exception;

    /**
     * 협력업체게시판 조회업체유형 등록
     * 
     * @param vendorBoardVendorAtt
     * @return
     * @throws Exception
     */
    public int createVendorBoardVendorAtt(VendorBoardVendorAtt vendorBoardVendorAtt) throws Exception;

    /**
     * 협력업체게시판 수정
     * 
     * @param vendorBoard
     * @return
     * @throws Exception
     */
    public int updateVendorBoard(VendorBoard vendorBoard) throws Exception;

    /**
     * 협력업체게시판 삭제
     * 
     * @param boardNo
     * @return
     * @throws Exception
     */
    public int deleteVendorBoard(@Param("boardNo") int boardNo) throws Exception;

    /**
     * 협력업체게시판 조회업체 삭제
     * 
     * @param boardNo
     * @return
     * @throws Exception
     */
    public int deleteVendorBoardVendor(@Param("boardNo") int boardNo) throws Exception;

    /**
     * 협력업체게시판 조회업체유형 삭제
     * 
     * @param boardNo
     * @return
     * @throws Exception
     */
    public int deleteVendorBoardVendorAtt(@Param("boardNo") int boardNo) throws Exception;

}