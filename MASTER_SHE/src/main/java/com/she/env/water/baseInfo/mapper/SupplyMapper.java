package com.she.env.water.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.water.model.Supply;

@Mapper
@Repository("com.she.env.water.baseInfo.mapper.SupplyMapper")
public interface SupplyMapper {

    /**
     * 공급수 조회
     *
     * @param useYn
     *            사용여부
     * @return 공급수 목록
     * @throws Exception
     *             예외
     */
    public List<Supply> getSupplys(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("ewtrCleanFacNo") String ewtrCleanFacNo, @Param("title") String title, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공급수 상세조회
     *
     * @param ewtrSuplNo
     *            공급수번호
     * @return Supply 공급수
     * @throws Exception
     *             예외
     */
    public Supply getSupply(@Param("ewtrSuplNo") int ewtrSuplNo) throws Exception;

    /**
     * 공급수 체크
     *
     * @return 체크 값
     * @throws Exception
     *             예외
     */
    public HashMap<String, Object> getSupplyCheck(@Param("plantCd") String plantCd, @Param("ewtrSuplClassCd") String ewtrSuplClassCd, @Param("ewtrSuplNm") String ewtrSuplNm, @Param("ewtrSuplNo") int ewtrSuplNo) throws Exception;

    /**
     * 공급수 신규등록
     *
     * @param Supply
     *            공급수
     * @return ewtrSuplNo 공급수번호
     * @throws Exception
     *             예외
     */
    public int createSupply(Supply supply) throws Exception;

    /**
     * 공급수 수정
     *
     * @param Supply
     *            공급수
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateSupply(Supply supply) throws Exception;
}
