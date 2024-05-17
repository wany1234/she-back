package com.she.safety.consol.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.consol.model.ConsolInspDept;
import com.she.safety.consol.model.ConsolPerson;
import com.she.safety.consol.model.Consolidation;
import com.she.safety.model.PatrolItemResult;

@Mapper
@Repository("com.she.safety.consol.mapper.ConsolidationPlanMapper")
public interface ConsolidationPlanMapper {

    public List<Consolidation> getConsolPlans(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("plantCd") String plantCd, @Param("checkStepCd") String checkStepCd, @Param("safCheckKindNo") int safCheckKindNo) throws Exception;

    public List<Consolidation> getConsolSigs(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("userId") String userId) throws Exception;

    public Consolidation getConsolPlan(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public Consolidation getConsolSig(@Param("safCongChkRsltNo") int safCongChkRsltNo, @Param("safChkInspPsnNo") int safChkInspPsnNo, @Param("safChkInspDeptNo") int safChkInspDeptNo) throws Exception;

    public Integer updateUserSignature(@Param("safChkInspPsnNo") int safChkInspPsnNo, @Param("signImg") String signImg) throws Exception;

    public int completeSign(@Param("safChkInspPsnNo") int safChkInspPsnNo) throws Exception;

    public List<ConsolInspDept> getConsolInspectors(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public List<ConsolPerson> getConsolPersons(@Param("safChkInspDeptNo") int safChkInspDeptNo) throws Exception;

    public int createInspPsn(ConsolPerson person) throws Exception;

    public int createInspPsnNoImg(ConsolPerson person) throws Exception;

    public int createConsolResultItem(PatrolItemResult result) throws Exception;

    public int deleteConsolResultItem(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public int updateConsolResultItem(PatrolItemResult result) throws Exception;

    public int createResultItem(@Param("safCongChkRsltNo") int safCongChkRsltNo, @Param("safCheckKindNo") int safCheckKindNo) throws Exception;

    public int getPsnSequenceNumber() throws Exception;

    public List<PatrolItemResult> getConsolResultItems(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public List<PatrolItemResult> getNewConsolResultItems(@Param("safCheckKindNo") int safCheckKindNo) throws Exception;

    public int deleteInspPsn(@Param("safChkInspDeptNo") int safChkInspDeptNo) throws Exception;

    public int completeConsolPlan(@Param("safCongChkRsltNo") int safCongChkRsltNo) throws Exception;

    public int deleteConsolPlan(@Param("safChkInspDeptNo") int safChkInspDeptNo) throws Exception;
}
