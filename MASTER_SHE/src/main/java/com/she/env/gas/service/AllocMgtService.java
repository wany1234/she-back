package com.she.env.gas.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.mapper.AllocMgtMapper;
import com.she.env.gas.model.GhgAlloc;

@Service
public class AllocMgtService {
    @Autowired
    private AllocMgtMapper allocMgtMapper;

    /**
     * 할당량 관리 신규 조회
     * 
     * @param ghgAlloc
     * @return
     * @throws Exception
     */
    public int createAllocMgt(GhgAlloc ghgAlloc) throws Exception {
        allocMgtMapper.createAllocMgt(ghgAlloc);
        return ghgAlloc.getGhgAllocNo();
    }

    /**
     * 할당량 관리 수정
     * 
     * @param ghgAlloc
     * @return
     * @throws Exception
     */
    public int updateAllocMgt(GhgAlloc ghgAlloc) throws Exception {
        allocMgtMapper.updateAllocMgt(ghgAlloc);
        return ghgAlloc.getGhgAllocNo();
    }

    /**
     * 할당량 관리 조회
     * 
     * @param plantCd
     * @param fromYear
     * @param toYear
     * @return
     * @throws Exception
     */
    public List<GhgAlloc> getAllocMgts(@Param("plantCd") String plantCd, @Param("fromYear") String fromYear, @Param("toYear") String toYear) throws Exception {
        return allocMgtMapper.getAllocMgts(plantCd, fromYear, toYear);
    }

    /**
     * 할당량 관리 상세
     * 
     * @param ghgAllocNo
     * @return
     * @throws Exception
     */
    public GhgAlloc getAllocMgt(@Param("ghgAllocNo") int ghgAllocNo) throws Exception {
        return allocMgtMapper.getAllocMgt(ghgAllocNo);
    }

    /**
     * 할당량 관리 중복체크
     * 
     * @param plantCd
     * @param year
     * @return
     */
    public int checkDuple(@Param("plantCd") String plantCd, @Param("year") String year) {
        return allocMgtMapper.checkDuple(plantCd, year);
    }

}
