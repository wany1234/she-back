package com.she.mgt.mgtLaw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.model.MgtSheLawChk;

@Mapper
@Repository("com.she.mgt.mgtLaw.mapper.SheLawCheckMapper")
public interface SheLawCheckMapper {
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
    public List<MgtSheLawChk> getSheLawChecks(@Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd,
            @Param("lawClassCd") String lawClassCd, @Param("lawKindCd") String lawKindCd,
            @Param("stepCd") String stepCd) throws Exception;

    /**
     * 법규검토 상세조회
     * 
     * @param sheLawChkNo
     *            법규검토번호
     * @return MgtSheLawChk 법규검토
     * @throws Exception
     *             예외
     */
    public MgtSheLawChk getSheLawCheck(@Param("sheLawChkNo") int sheLawChkNo) throws Exception;

    /**
     * 법규검토 신규등록
     * 
     * @param MgtSheLawChk
     *            법규검토
     * @return sheLawChkNo 법규검토번호
     * @throws Exception
     *             예외
     */
    public int createSheLawCheck(MgtSheLawChk mgtSheLawChk) throws Exception;

    /**
     * 법규검토 수정
     * 
     * @param MgtSheLawChk
     *            법규검토
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateSheLawCheck(MgtSheLawChk mgtSheLawChk) throws Exception;

    /**
     * 법규검토 삭제
     * 
     * @param sheLawChkNo
     *            법규검토번호
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    public int deleteSheLawCheck(@Param("sheLawChkNo") int sheLawChkNo) throws Exception;
}
