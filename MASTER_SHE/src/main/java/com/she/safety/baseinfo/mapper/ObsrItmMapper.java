package com.she.safety.baseinfo.mapper;

import com.she.safety.model.ObsrItm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 안전 > 기준정보 > 안전관찰항목(SK E&S)
 *
 */
@Mapper
@Repository("com.she.safety.baseinfo.mapper.ObsrItmMapper")
public interface ObsrItmMapper {

    /**
     * 안전관찰항목 등록
     *
     * @param obsrItm
     *            안전관찰체크항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int createObsrItm(ObsrItm obsrItm) throws Exception;

    /**
     * 안전관찰항목 목록 조회
     *
     * @param plantCd
     *            사업장코드
     * @param obsrItmNm
     *            안전관찰 항목명
     * @param useYn
     *            사용여부
     * @return 안전관찰항목 목록
     * @throws Exception
     */
    public List<ObsrItm> getObsrItms(@Param("plantCd") String plantCd, @Param("obsrItmNm") String obsrItmNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 안전관찰항목 상세 조회
     *
     * @param safObsrItmNo
     *            안전관찰체크항목번호
     * @return 안전관찰항목
     * @throws Exception
     */
    public ObsrItm getObsrItm(@Param("safObsrItmNo") int safObsrItmNo) throws Exception;

    /**
     * 안전관찰항목 수정
     *
     * @param obsrItm
     *            안전관찰체크항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int updateObsrItm(ObsrItm obsrItm) throws Exception;

    /**
     * 안전관찰항목 중복 검사
     *
     * @param plantCd
     *            사업장코드
     * @param itmTypeCd
     *            관찰항목분류 코드
     * @param obsrItmNm
     *            안전관찰 항목명
     * @param safObsrItmNo
     *            안전관찰체크항목번호
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkObsrItm(@Param("plantCd") String plantCd, @Param("itmTypeCd") String itmTypeCd, @Param("obsrItmNm") String obsrItmNm, @Param("safObsrItmNo") int safObsrItmNo) throws Exception;
}
