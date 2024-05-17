package com.she.mgt.budgetManagement.mapper;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.MgtBudgetExec;
import com.she.mgt.model.MgtBudgetStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MgtBudgetExecutionMapper {
    /**
     * 예산집행 관리 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param plantCd
     *          사업장코드
     * @param deptCd
     *          대상부서코드
     * @param deptSubYn
     *          하위부서 조회여부
     * @param budgetTypeCd
     *          예산분류코드
     * @param budgetClsCd
     *          예산구분코드
     * @param budgetActNm
     *          예산계정
     * @param procStepCd
     *          단계(편성/집행)
     * @param stateCd
     *          상태(미작성/작성중/결재중/결재완료)
     * @return 예산집행 관리 목록
     * @throws Exception
     */
    public List<MgtBudgetExec> getMgtBudgetExecs(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd
            , @Param("deptSubYn") String deptSubYn, @Param("budgetTypeCd") String budgetTypeCd, @Param("budgetClsCd") String budgetClsCd, @Param("budgetActNm") String budgetActNm
            , @Param("procStepCd") String procStepCd, @Param("stateCd") String stateCd, @Param("statusYn") String statusYn, @Param("budgetActMstNo") int budgetActMstNo, @Param("month") String month, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 예산집행 관리 상세 조회
     * @param budgetExecNo
     *          예산집행 번호
     * @return 예산집행 상세
     * @throws Exception
     */
    public MgtBudgetExec getMgtBudgetExec(@Param("budgetExecNo") int budgetExecNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 예산계정별 집행가능 금액 조회
     * @param budgetActMstNo
     *          예산계정 번호
     * @return 집행가능 금액
     * @throws Exception
     */
    public String getExecPsblAmt(@Param("deptCd") String deptCd, @Param("budgetTypeCd") String budgetTypeCd, @Param("budgetClsCd") String budgetClsCd, @Param("budgetActMstNo") int budgetActMstNo, @Param("budgetExecNo") int budgetExecNo) throws Exception;

    /**
     * 예산편성된 예산계정 목록 조회
     * @param year
     *          년도
     * @param deptCd
     *          대상부서
     * @param deptSubYn
     *          하위부서 조회여부
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActNm
     *          예산계정
     * @return 예산편성된 예산계정 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getBudgetingActMst(@Param("year") String year, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn,
            @Param("budgetTypeCd") String budgetTypeCd, @Param("budgetClsCd") String budgetClsCd, @Param("budgetActNm") String budgetActNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 예산집행 신규등록
     * @param mgtBudgetExec
     *          예산집행
     * @return 결과
     * @throws Exception
     */
    public int insertMgtBudgetExec(MgtBudgetExec mgtBudgetExec) throws Exception;

    /**
     * 예산집행 수정
     * @param mgtBudgetExec
     *          예산집행
     * @return 결과
     * @throws Exception
     */
    public int updateMgtBudgetExec(MgtBudgetExec mgtBudgetExec) throws Exception;

    /**
     * 예산집행 삭제
     * @param budgetExecNo
     *          예산집행 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteMgtBudgetExec(int budgetExecNo)throws Exception;

    /**
     * 예산집행 결재정보 업데이트
     * @param mgtBudgetExec
     *          예산집행
     * @return 결과
     * @throws Exception
     */
    public int updateAppr(MgtBudgetExec mgtBudgetExec) throws Exception;

    /**
     * 예산 편성관리 현황 조회
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActMstNo
     *          예산계정
     * @return 결과
     * @throws Exception
     */
    public List<HashMap<String, Object>> getBudgetingStatus(@Param("year") String year, @Param("plantCd") String plantCd, @Param("budgetTypeCd") String budgetTypeCd, @Param("budgetClsCd") String budgetClsCd, @Param("budgetActMstNo") int budgetActMstNo, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 예산 편성및집행 현황 조회
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActMstNo
     *          예산계정
     * @return 결과
     * @throws Exception
     */
    public List<MgtBudgetStat> getBudgetStatus(@Param("year") String year, @Param("plantCd") String plantCd, @Param("budgetTypeCd") String budgetTypeCd, @Param("budgetClsCd") String budgetClsCd, @Param("budgetActMstNo") int budgetActMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
