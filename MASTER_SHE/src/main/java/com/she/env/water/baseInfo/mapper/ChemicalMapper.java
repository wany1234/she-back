package com.she.env.water.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.water.model.Chemical;

@Mapper
@Repository("com.she.env.water.baseInfo.mapper.ChemicalMapper")
public interface ChemicalMapper {

    /**
     * 수질약품 조회
     * 
     * @param useYn
     *            사용여부
     * @return 수질약품 목록
     * @throws Exception
     *             예외
     */
    public List<Chemical> getChemicals(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("title") String title, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 수질약품 상세조회
     * 
     * @param ewtrChemNo
     *            수질약품번호
     * @return Chemical 수질약품
     * @throws Exception
     *             예외
     */
    public Chemical getChemical(@Param("ewtrChemNo") int ewtrChemNo) throws Exception;

    /**
     * 수질약품 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getChemicalCheck(@Param("plantCd") String plantCd, @Param("ewtrChemNm") String ewtrChemNm, @Param("ewtrChemNo") int ewtrChemNo) throws Exception;

    /**
     * 수질약품 신규등록
     * 
     * @param Chemical
     *            수질약품
     * @return ewtrChemNo 수질약품번호
     * @throws Exception
     *             예외
     */
    public int createChemical(Chemical chemical) throws Exception;

    /**
     * 수질약품 수정
     * 
     * @param Chemical
     *            수질약품
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateChemical(Chemical chemical) throws Exception;

}
