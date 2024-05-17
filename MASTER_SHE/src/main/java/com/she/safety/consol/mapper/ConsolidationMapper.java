package com.she.safety.consol.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.consol.model.ConsolInspDept;
import com.she.safety.consol.model.Consolidation;

@Mapper
@Repository("com.she.safety.consol.mapper.ConsolidationMapper")
public interface ConsolidationMapper {

    public List<Consolidation> getConsolMsts(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("plantCd") String plantCd, @Param("stepCd") String stepCd, @Param("safCheckKindNo") int safCheckKindNo) throws Exception;

    public List<Consolidation> getConsolMst(@Param("safCongChkPlanNo") int safCongChkPlanNo) throws Exception;

    public List<ConsolInspDept> getConsolDept(@Param("safCongChkPlanNo") int safCongChkPlanNo) throws Exception;

    public List<Consolidation> getConsolPlans(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public int completeResultConsolMst(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public int createConsolMst(Consolidation master) throws Exception;

    public int createInspDept(ConsolInspDept dept) throws Exception;

    public int getResultSequenceNumber() throws Exception;

    public int getPlanSequenceNumber() throws Exception;

    public int getInspSequenceNumber() throws Exception;

    public int updateConsolMst(Consolidation master) throws Exception;

    public int completeConsolMst(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public int updatePlanStep(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public int deleteConsolMst(@Param("safCongChkRsltNo") int safCongChkRsltNo, @Param("safCongChkPlanNo") int safCongChkPlanNo) throws Exception;

    public int countConsol(@Param("safCongChkPlanNo") int safCongChkPlanNo, @Param("checkStepCd") String checkStepCd) throws Exception;

    public int completeResult(@Param("safCongChkPlanNo") int safCongChkPlanNo) throws Exception;

    public int apprConsolPlanByYear(@Param("safCongChkPlanNo") int safCongChkPlanNo, @Param("stepCd") String stepCd, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    public int apprConsolResultByYear(@Param("safCongChkRsltNo") int safCongChkPlanNo, @Param("stepCd") String stepCd, @Param("apprRqstNo") int apprRqstNo) throws Exception;
}
