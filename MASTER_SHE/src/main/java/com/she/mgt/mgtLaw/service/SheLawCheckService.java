package com.she.mgt.mgtLaw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.mgt.mgtLaw.mapper.SheLawCheckMapper;
import com.she.mgt.model.MgtSheLawChk;

@Service
public class SheLawCheckService {
    @Autowired
    private SheLawCheckMapper sheLawCheckMapper;

    /**
     * 법규검토 조회
     * 
     * @param fromYmd
     *            등록기간
     * @param toYmd
     *            등록기간
     * @param lawClassCd
     *            관련법규
     * @param lawKindCd
     *            법규분류
     * @param stepCd
     *            진행단계
     * @return 법규검토 목록
     * @throws Exception
     *             예외
     */
    public List<MgtSheLawChk> getSheLawChecks(String fromYmd, String toYmd, String lawClassCd, String lawKindCd,
            String stepCd) throws Exception {
        return sheLawCheckMapper.getSheLawChecks(fromYmd, toYmd, lawClassCd, lawKindCd, stepCd);
    }

    /**
     * 법규검토 상세조회
     * 
     * @param sheLawChkNo
     *            법규검토번호
     * @return MgtSheLawChk 법규검토
     * @throws Exception
     *             예외
     */
    public MgtSheLawChk getSheLawCheck(int sheLawChkNo) throws Exception {
        return sheLawCheckMapper.getSheLawCheck(sheLawChkNo);
    }

    /**
     * 법규검토 신규등록
     * 
     * @param MgtSheLawChk
     *            법규검토
     * @return sheLawChkNo 법규검토번호
     * @throws Exception
     *             예외
     */
    public int createSheLawCheck(MgtSheLawChk mgtSheLawChk) throws Exception {
        this.sheLawCheckMapper.createSheLawCheck(mgtSheLawChk);
        return mgtSheLawChk.getSheLawChkNo();
    }

    /**
     * 법규검토 수정
     * 
     * @param MgtSheLawChk
     *            법규검토
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateSheLawCheck(MgtSheLawChk mgtSheLawChk) throws Exception {
        return sheLawCheckMapper.updateSheLawCheck(mgtSheLawChk);
    }

    /**
     * 법규검토 삭제
     * 
     * @param sheLawChkNo
     *            법규검토번호
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    public int deleteSheLawCheck(int sheLawChkNo) throws Exception {
        return sheLawCheckMapper.deleteSheLawCheck(sheLawChkNo);
    }
}
