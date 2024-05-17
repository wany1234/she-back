package com.she.mgt.perRptData.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.MgtPerRptData;

@Mapper
public interface MgtPerRptDataMapper {

    /**
     * 중처법 정기보고자료 목록 조회
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료
     * @return 결과
     * @throws Exception
     */
    public List<MgtPerRptData> getMgtPerRptDatas(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("halfTypeCd") String halfTypeCd, @Param("rptNm") String rptNm, @Param("procStepCd") String procStepCd,
            @Param("stepCd") String stepCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 중처법 정기보고자료 상세조회
     * 
     * @param perRptDataNo
     *            중처법 정기보고자료
     * @return 결과
     * @throws Exception
     */

    public MgtPerRptData getMgtPerRptData(@Param("perRptDataNo") int perRptDataNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 중처법 정기보고자료 신규등록
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료
     * @return 결과
     * @throws Exception
     */
    public int insertMgtPerRptData(MgtPerRptData mgtPerRptData) throws Exception;

    /**
     * 중처법 정기보고자료 수정
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료
     * @return 결과
     * @throws Exception
     */
    public int updateMgtPerRptData(MgtPerRptData mgtperRptData) throws Exception;

    /**
     * 중처법 정기보고자료 확정
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료
     * @return 결과
     * @throws Exception
     */
    public int compleMgtPerRptData(MgtPerRptData mgtperRptData) throws Exception;

    /**
     * 중처법 정기보고자료 삭제
     * 
     * @param perRptDataNo
     *            중처법 정기보고자료 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteMgtPerRptData(int perRptDataNo) throws Exception;
}
