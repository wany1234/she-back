package com.she.manage.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.LblDtl;
import com.she.manage.model.LblMst;

@Mapper
@Repository("com.she.manage.mapper.LblMapper")
public interface LblMapper {

    /**
     * 라벨타입 중복 확인
     *
     * @param mstCd
     *            라벨타입
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkLblMst(@Param("mstCd") String mstCd) throws Exception;

    /**
     * 라벨타입 등록
     *
     * @param lblMst
     *            라벨타입
     * @return 등록 행 수
     * @throws Exception
     */
    public int createLblMst(LblMst lblMst) throws Exception;

    /**
     * 라벨타입 목록 조회
     *
     * @return 라벨타입 목록
     * @throws Exception
     */
    public List<LblMst> getLblMsts() throws Exception;

    /**
     * 라벨타입 상세 조회
     *
     * @param mstCd
     *            라벨타입
     * @return 라벨타입
     * @throws Exception
     */
    public LblMst getLblMst(@Param("mstCd") String mstCd) throws Exception;

    /**
     * 라벨타입 수정
     *
     * @param lblMst
     *            라벨타입
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateLblMst(LblMst lblMst) throws Exception;

    /**
     * 라벨 삭제여부 수정
     *
     * @param mstCd
     *            라벨타입
     * @param updateUserId
     *            수정자
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateLblDtlDelYn(@Param("mstCd") String mstCd, @Param("updateUserId") String updateUserId) throws Exception;

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
    public int checkLblDtl(@Param("mstCd") String mstCd, @Param("lblVal") String lblVal) throws Exception;

    /**
     * 라벨코드 자동 생성
     *
     * @return 라벨코드
     * @throws Exception
     */
    HashMap<String, Object> getNewLblCd() throws Exception;

    /**
     * 라벨코드 등록
     *
     * @param lblDtl
     *            라벨코드
     * @return 등록 행 수
     * @throws Exception
     */
    public int createLblDtl(LblDtl lblDtl) throws Exception;

    /**
     * 라벨코드 목록 조회
     *
     * @param mstCd
     *            라벨타입
     * @return 라벨코드 목록
     * @throws Exception
     */
    public List<LblDtl> getLblDtls(@Param("mstCd") String mstCd) throws Exception;

    /**
     * 라벨코드 상세 조회
     *
     * @param lblCd
     *            라벨코드
     * @return 라벨코드
     * @throws Exception
     */
    public LblDtl getLblDtl(@Param("lblCd") String lblCd) throws Exception;

    /**
     * 라벨코드 수정
     *
     * @param lblDtl
     *            라벨코드
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateLblDtl(LblDtl lblDtl) throws Exception;
}
