package com.she.env.water.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.water.model.MonPos;
import com.she.env.water.model.MonPosTestItem;

@Mapper
@Repository("com.she.env.water.baseInfo.mapper.MonPosMapper")
public interface MonPosMapper {

    /**
     * 측정위치 조회
     * 
     * @param useYn
     *            사용여부
     * @return 측정위치 목록
     * @throws Exception
     *             예외
     */
    public List<MonPos> getMonPoss(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("ewtrMonPosNm") String ewtrMonPosNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 측정위치 상세조회
     * 
     * @param ewtrMonPosNo
     *            측정위치번호
     * @return MonPos 측정위치
     * @throws Exception
     *             예외
     */
    public MonPos getMonPos(@Param("ewtrMonPosNo") int ewtrMonPosNo) throws Exception;

    /**
     * 측정위치 신규등록
     * 
     * @param MonPos
     *            측정위치
     * @return ewtrMonPosNo 측정위치번호
     * @throws Exception
     *             예외
     */
    public int createMonPos(MonPos monPos) throws Exception;

    /**
     * 측정위치 수정
     * 
     * @param MonPos
     *            측정위치
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateMonPos(MonPos monPos) throws Exception;

    /**
     * 측정위치별 검사항목 조회
     * 
     * @param ewtrMonPosNo
     *            측정위치번호
     * @return 측정위치별 검사항목 목록
     * @throws Exception
     *             예외
     */
    public List<MonPosTestItem> getMonPosTestItems(@Param("ewtrMonPosNo") int ewtrMonPosNo, @Param("ewtrTestItemCd") String ewtrTestItemCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 측정위치별 검사항목 신규등록
     * 
     * @param MonPosTestItem
     *            측정위치별 검사항목
     * @return 등록행수
     * @throws Exception
     *             예외
     */
    public int createMonPosTestItem(MonPosTestItem monPosTestItem) throws Exception;

    /**
     * 측정위치별 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getMonPosCheck(@Param("plantCd") String plantCd, @Param("ewtrMonPosNm") String ewtrMonPosNm, @Param("deptCd") String deptCd, @Param("ewtrMonPosNo") int ewtrMonPosNo) throws Exception;

    /**
     * 측정위치별 검사항목 삭제
     * 
     * @param ewtrMonPosNo
     *            측정위치번호
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    public int deleteMonPosTestItem(@Param("ewtrMonPosNo") int ewtrMonPosNo) throws Exception;

}
