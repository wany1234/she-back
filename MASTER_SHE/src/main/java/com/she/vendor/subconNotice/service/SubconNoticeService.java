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
package com.she.vendor.subconNotice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.utils.ConstVal;
import com.she.vendor.model.VendorBoard;
import com.she.vendor.model.VendorBoardVendor;
import com.she.vendor.model.VendorBoardVendorAtt;
import com.she.vendor.subconNotice.mapper.SubconNoticeMapper;

@Service
public class SubconNoticeService {
    @Autowired
    private SubconNoticeMapper subconNoticeMapper;

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
    public List<VendorBoard> getVendorBoardList(String plantCd, String kindCd, String vendorCd, String title,
            String fromDt, String toDt) throws Exception {
        return subconNoticeMapper.getVendorBoardList(plantCd, kindCd, vendorCd, title, fromDt, toDt);
    }

    /**
     * 협력업체 게시물 상세 조회
     * 
     * @param boardNo
     *            게시물번호
     * @return 협력업체게시물
     * @throws Exception
     */
    public VendorBoard getVendorBoard(int boardNo) throws Exception {
        if (boardNo <= 0) {
            return null;
        } else {
            // 협력업체 게시판 상세 조회
            VendorBoard vendorBoard = subconNoticeMapper.getVendorBoard(boardNo);
            if (vendorBoard != null) {
                // 협력업체 게시판 조회업체 목록 조회
                vendorBoard.setVendorBoardVendorList(subconNoticeMapper.getVendorBoardVendorList(boardNo));
                // 협력업체 게시판 조회업체유형 목록 조회
                vendorBoard.setVendorBoardVendorAttList(subconNoticeMapper.getVendorBoardVendorAttList(boardNo));
            }
            return vendorBoard;
        }
    }

    /**
     * 협력업체게시판 등록
     * 
     * @param vendorBoard
     * @return
     * @throws Exception
     */
    @Transactional
    public int createVendorBoard(VendorBoard vendorBoard) throws Exception {
        if (vendorBoard == null) {
            return 0;
        } else {
            // 협력업체게시판 등록
            int result = subconNoticeMapper.createVendorBoard(vendorBoard);
            if (result <= 0) {
                return 0;
            } else {
                if (StringUtils.equals(vendorBoard.getVendorRgeCd(), ConstVal.COM_VENDOR_RGE_VENDOR)) {
                    // 협력업체 게시판 조회업체 목록 등록
                    if (CollectionUtils.isNotEmpty(vendorBoard.getVendorBoardVendorList())) {
                        for (VendorBoardVendor vendorBoardVendor : vendorBoard.getVendorBoardVendorList()) {
                            vendorBoardVendor.setBoardNo(vendorBoard.getBoardNo());
                            subconNoticeMapper.createVendorBoardVendor(vendorBoardVendor);
                        }
                        return vendorBoard.getBoardNo();
                    } else {
                        return 0;
                    }
                } else if (StringUtils.equals(vendorBoard.getVendorRgeCd(), ConstVal.COM_VENDOR_RGE_ATTRIBUTE)) {
                    // 협력업체 게시판 조회업체유형 목록 등록
                    if (CollectionUtils.isNotEmpty(vendorBoard.getVendorBoardVendorAttList())) {
                        for (VendorBoardVendorAtt vendorBoardVendorAtt : vendorBoard.getVendorBoardVendorAttList()) {
                            vendorBoardVendorAtt.setBoardNo(vendorBoard.getBoardNo());
                            subconNoticeMapper.createVendorBoardVendorAtt(vendorBoardVendorAtt);
                        }
                        return vendorBoard.getBoardNo();
                    } else {
                        return 0;
                    }
                } else {
                    return vendorBoard.getBoardNo();
                }
            }
        }
    }

    /**
     * 협력업체게시판 수정
     * 
     * @param vendorBoard
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateVendorBoard(VendorBoard vendorBoard) throws Exception {
        if (vendorBoard == null) {
            return 0;
        } else {
            // 협력업체게시판 수정
            int result = subconNoticeMapper.updateVendorBoard(vendorBoard);
            if (result <= 0) {
                return 0;
            } else {
                // 협력업체 게시판 조회업체 삭제
                subconNoticeMapper.deleteVendorBoardVendor(vendorBoard.getBoardNo());
                // 협력업체 게시판 조회업체유형 삭제
                subconNoticeMapper.deleteVendorBoardVendorAtt(vendorBoard.getBoardNo());

                if (StringUtils.equals(vendorBoard.getVendorRgeCd(), ConstVal.COM_VENDOR_RGE_VENDOR)) {
                    // 협력업체 게시판 조회업체 목록 등록
                    if (CollectionUtils.isNotEmpty(vendorBoard.getVendorBoardVendorList())) {
                        for (VendorBoardVendor vendorBoardVendor : vendorBoard.getVendorBoardVendorList()) {
                            vendorBoardVendor.setBoardNo(vendorBoard.getBoardNo());
                            subconNoticeMapper.createVendorBoardVendor(vendorBoardVendor);
                        }
                        return vendorBoard.getBoardNo();
                    } else {
                        return 0;
                    }
                } else if (StringUtils.equals(vendorBoard.getVendorRgeCd(), ConstVal.COM_VENDOR_RGE_ATTRIBUTE)) {
                    // 협력업체 게시판 조회업체유형 목록 등록
                    if (CollectionUtils.isNotEmpty(vendorBoard.getVendorBoardVendorAttList())) {
                        for (VendorBoardVendorAtt vendorBoardVendorAtt : vendorBoard.getVendorBoardVendorAttList()) {
                            vendorBoardVendorAtt.setBoardNo(vendorBoard.getBoardNo());
                            subconNoticeMapper.createVendorBoardVendorAtt(vendorBoardVendorAtt);
                        }
                        return vendorBoard.getBoardNo();
                    } else {
                        return 0;
                    }
                } else {
                    return vendorBoard.getBoardNo();
                }
            }
        }
    }

    /**
     * 협력업체게시판 삭제
     * 
     * @param boardNo
     * @return
     * @throws Exception
     */
    @Transactional
    public int deleteVendorBoard(int boardNo) throws Exception {
        if (boardNo <= 0) {
            return 0;
        } else {
            // 협력업체게시판 조회업체 삭제
            int result = subconNoticeMapper.deleteVendorBoardVendor(boardNo);
            // 협력업체게시판 조회업체유형 삭제
            result += subconNoticeMapper.deleteVendorBoardVendorAtt(boardNo);
            // 협력업체게시판 삭제
            result += subconNoticeMapper.deleteVendorBoard(boardNo);
            return result;
        }
    }

}
