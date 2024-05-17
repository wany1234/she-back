package com.she.env.gas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.GhgAlloc;

@Mapper
@Repository("com.she.env.gas.mapper.AllocMgtMapper")
public interface AllocMgtMapper {

    /**
     * 할당량 관리 신규 조회
     * 
     * @param ghgAlloc
     * @return
     * @throws Exception
     */
    public int createAllocMgt(GhgAlloc ghgAlloc) throws Exception;

    /**
     * 할당량 관리 수정
     * 
     * @param ghgAlloc
     * @return
     * @throws Exception
     */
    public int updateAllocMgt(GhgAlloc ghgAlloc) throws Exception;

    /**
     * 할당량 관리 조회
     * 
     * @param plantCd
     * @param fromYear
     * @param toYear
     * @return
     * @throws Exception
     */
    public List<GhgAlloc> getAllocMgts(@Param("plantCd") String plantCd, @Param("fromYear") String fromYear, @Param("toYear") String toYear) throws Exception;

    /**
     * 할당량 관리 상세
     * 
     * @param ghgAllocNo
     * @return
     * @throws Exception
     */
    public GhgAlloc getAllocMgt(@Param("ghgAllocNo") int ghgAllocNo) throws Exception;

    /**
     * 할당량 관리 중복체크
     * 
     * @param plantCd
     * @param year
     * @return
     */
    public int checkDuple(@Param("plantCd") String plantCd, @Param("year") String year);

}
