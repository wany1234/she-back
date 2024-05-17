package com.she.manage.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.LblMapper;
import com.she.manage.model.LblDtl;
import com.she.manage.model.LblMst;

@Service
public class LblService {

    @Autowired
    LblMapper lblMapper;

    /**
     * 라벨타입 중복 확인
     *
     * @param mstCd
     *            라벨타입
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkLblMst(String mstCd) throws Exception {
        return lblMapper.checkLblMst(mstCd);
    }

    /**
     * 라벨타입 등록
     *
     * @param lblMst
     *            라벨타입
     * @return 등록 행 수
     * @throws Exception
     */
    public int createLblMst(LblMst lblMst) throws Exception {
        return lblMapper.createLblMst(lblMst);
    }

    /**
     * 라벨타입 목록 조회
     *
     * @return 라벨타입 목록
     * @throws Exception
     */
    public List<LblMst> getLblMsts() throws Exception {
        return lblMapper.getLblMsts();
    }

    /**
     * 라벨타입 상세 조회
     *
     * @param mstCd
     *            라벨타입
     * @return 라벨타입
     * @throws Exception
     */
    public LblMst getLblMst(String mstCd) throws Exception {
        return lblMapper.getLblMst(mstCd);
    }

    /**
     * 라벨타입 수정
     *
     * @param lblMst
     *            라벨타입
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateLblMst(LblMst lblMst) throws Exception {
        return lblMapper.updateLblMst(lblMst);
    }

    /**
     * 라벨타입 수정
     *
     * @param lblMst
     *            라벨타입
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMsgMst(LblMst lblMst) throws Exception {
        if ("Y".equals(lblMst.getDelYn())) {
            // 라벨타입 삭제 시 해당 라벨타입으로 등록된 라벨 전부 del_yn을 'Y'로 변경
            lblMapper.updateLblDtlDelYn(lblMst.getMstCd(), lblMst.getUpdateUserId());
        }
        return lblMapper.updateLblMst(lblMst);
    }

    /**
     * 라벨코드 중복 확인
     *
     * @param mstCd
     *            라벨타입
     * @param lblVal
     *            한국어
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkLblDtl(String mstCd, String lblVal) throws Exception {
        return lblMapper.checkLblDtl(mstCd, lblVal);
    }

    /**
     * 라벨코드 자동 생성
     *
     * @return 라벨코드
     * @throws Exception
     */
    public String getNewLblCd() throws Exception {
        String lblCd = "";
        HashMap<String, Object> map = this.lblMapper.getNewLblCd();
        if (map != null && map.containsKey("lbl_cd")) {
            lblCd = String.valueOf(map.get("lbl_cd"));
        }
        return lblCd;
    }

    /**
     * 라벨코드 등록
     *
     * @param lblDtl
     *            라벨코드
     * @return 등록 행 수
     * @throws Exception
     */
    public String createLblDtl(LblDtl lblDtl) throws Exception {
        lblDtl.setLblCd(this.getNewLblCd());
        lblMapper.createLblDtl(lblDtl);
        return lblDtl.getLblCd();
    }

    /**
     * 라벨코드 목록 조회
     *
     * @param mstCd
     *            라벨타입
     * @return 라벨코드 목록
     * @throws Exception
     */
    public List<LblDtl> getLblDtls(String mstCd) throws Exception {
        return lblMapper.getLblDtls(mstCd);
    }

    /**
     * 라벨코드 상세 조회
     *
     * @param lblCd
     *            라벨코드
     * @return 라벨코드
     * @throws Exception
     */
    public LblDtl getLblDtl(String lblCd) throws Exception {
        return lblMapper.getLblDtl(lblCd);
    }

    /**
     * 라벨코드 수정
     *
     * @param lblDtl
     *            라벨코드
     * @return 수정 행 수
     * @throws Exception
     */
    public String updateLblDtl(LblDtl lblDtl) throws Exception {
        lblMapper.updateLblDtl(lblDtl);
        return lblDtl.getLblCd();
    }
}
