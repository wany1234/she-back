package com.she.safety.patrol.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.Patrol;
import com.she.safety.model.PatrolMaster;

@Mapper
@Repository("com.she.safety.patrol.mapper.PatrolMstMapper")
public interface PatrolMstMapper {

	public List<PatrolMaster> getPatrolMsts(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("pgpDeptCd") String pgpDeptCd, @Param("pgpDeptSubYn") String pgpDeptSubYn,@Param("tgtVendorCd") String tgtVendorCd, @Param("plantCd") String plantCd,
            @Param("stepCd") String stepCd, @Param("safCheckKindNo") int safCheckKindNo, @Param("keyword") String keyword) throws Exception;

    public PatrolMaster getPatrolMst(@Param("patrolMstNo") int patrolMstNo) throws Exception;

    public List<Patrol> getPatrolPlans(@Param("patrolMstNo") int patrolMstNo) throws Exception;

    public int completeResultPatrolMst(@Param("patrolMstNo") int patrolMstNo) throws Exception;

    public int createPatrolMst(PatrolMaster master) throws Exception;

    public int getSequenceNumber() throws Exception;

    public int updatePatrolMst(PatrolMaster master) throws Exception;

    public int completePatrolMst(@Param("patrolMstNo") int patrolMstNo) throws Exception;

    public int updatePlanStep(@Param("patrolMstNo") int patrolMstNo) throws Exception;

    public int deletePatrolMst(@Param("patrolMstNo") int patrolMstNo) throws Exception;
}
