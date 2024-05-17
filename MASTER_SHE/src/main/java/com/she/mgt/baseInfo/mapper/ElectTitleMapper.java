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
package com.she.mgt.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.ElectTitlItem;
import com.she.mgt.model.ElectTitlLcn;
import com.she.mgt.model.ElectTitle;

@Mapper
@Repository("com.she.mgt.baseInfo.mapper.ElectTitleMapper")
public interface ElectTitleMapper {

    /**
     * 선해임명 조회
     *
     * @param refLawCd
     *            구분
     * @param electAttCd
     *            분야
     * @param electTitlNm
     *            선해임명명
     * @param useYn
     *            사용여부
     * @return 선해임명 목록
     * @throws Exception
     */
    public List<ElectTitle> getElectTitles(@Param("refLawCd") String refLawCd, @Param("electAttCd") String electAttCd, @Param("electTitlNm") String electTitlNm, @Param("useYn") String useYn, @Param("electClsCd") String electClsCd, @Param("evalTgtYn") String evalTgtYn, @Param("plantCd") String plantCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선해임명 상세 조회
     *
     * @param safElectTitlNo
     *            선해임명번호
     * @return 선해임명
     * @throws Exception
     */
    public ElectTitle getElectTitle(@Param("safElectTitlNo") int safElectTitlNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선해임명 자격증 코드 조회
     *
     * @param safElectTitlNo
     *            선해임명번호
     * @return 선해임명 자격증 코드 목록
     * @throws Exception
     */
    public List<String> getLcnTypeCds(@Param("safElectTitlNo") int safElectTitlNo) throws Exception;

    /**
     * 선해임명 신규등록
     *
     * @param electTitle
     *            선해임명
     * @return 등록 행 수
     * @throws Exception
     */
    public int createElectTitle(ElectTitle electTitle) throws Exception;

    /**
     * 선해임명 수정
     *
     * @param electTitle
     *            선해임명
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateElectTitle(ElectTitle electTitle) throws Exception;

    /**
     * 선해임명별 자격증 등록
     *
     * @param electTitlLcn
     *            선해임명별 자격증
     * @return 수정 행 수
     * @throws Exception
     */
    public int createElectTitlLcn(ElectTitlLcn electTitlLcn) throws Exception;

    /**
     * 선해임명별 자격증 일괄 삭제
     *
     * @param safElectTitlNo
     *            선해임명번호
     * @return 수정 행 수
     * @throws Exception
     */
    public int deleteElectTitlLcn(@Param("safElectTitlNo") int safElectTitlNo) throws Exception;

    /**
     * 선해임명명 체크
     *
     * @param electTitlNm
     *            선해임명명
     * @param safElectTitlNo
     *            선해임명번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckElectTitle(@Param("electTitlNm") String electTitlNm, @Param("safElectTitlNo") int safElectTitlNo) throws Exception;

    /**
     * 평가항목 리스트 삭제
     *
     * @param safElectTitlNo
     *            선해임명번호
     * @return 체크 값
     * @throws Exception
     */
    public int deleteElectTitlItems(@Param("safElectTitlNo") int safElectTitlNo) throws Exception;

    /**
     * 평가항목 데이터 입력
     *
     * @param electTitlLcn
     *            선해임명별 자격증
     * @return 수정 행 수
     * @throws Exception
     */
    public int createElectTitlItems(ElectTitlItem electTitlItem) throws Exception;

    /**
     * 평가항목 상세 조회
     *
     * @param electTitlLcn
     *            선해임명별 자격증
     * @return 수정 행 수
     * @throws Exception
     */
    public List<ElectTitlItem> getElectTitlItems(@Param("safElectTitlNo") int safElectTitlNo) throws Exception;

    /**
     * 평가항목 데이터 수정
     *
     * @param electTitlLcn
     *            선해임명별 자격증
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateElectTitleItems(ElectTitlItem electTitlItem) throws Exception;
}
