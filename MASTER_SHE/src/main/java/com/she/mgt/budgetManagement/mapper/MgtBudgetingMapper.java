package com.she.mgt.budgetManagement.mapper;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.MgtBudgetAct;
import com.she.mgt.model.MgtBudgetActDept;
import com.she.mgt.model.MgtBudgetActDeptItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MgtBudgetingMapper {
    /**
     * 예산편성 관리 목록 조회
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
     * @param unOrganizeYn
     *          미편성 대상부서 여부
     * @param procStepCd
     *          단계(편성/집행)
     * @param stateCd
     *          상태(미작성/작성중/결재중/결재완료)
     * @return 예산편성 관리 목록
     * @throws Exception
     */
    public List<MgtBudgetAct> getMgtBudgetActs(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn
            , @Param("budgetTypeCd") String budgetTypeCd, @Param("budgetClsCd") String budgetClsCd, @Param("budgetActNm") String budgetActNm, @Param("unOrganizeYn") String unOrganizeYn
            , @Param("procStepCd") String procStepCd, @Param("stateCd") String stateCd, @Param("statusYn") String statusYn, @Param("budgetActMstNo") int budgetActMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 예산편성 상세 조회
     * @param budgetActNo
     *          예산편성관리 번호
     * @param year
     *          연도
     * @param plantCd
     *          사업장코드
     * @param flag
     *          신규등록 조회 여부
     *          신규등록일 경우 사업장코드/연도로 조회
     * @return 예산편성 상세
     * @throws Exception
     */
    public MgtBudgetAct getMgtBudgetAct(@Param("budgetActNo") int budgetActNo, @Param("year") String year, @Param("plantCd") String plantCd, @Param("flag") String flag) throws Exception;

    /**
     * 예산편성 상세 예산편성 대상부서 목록 조회
     * @param budgetActNo
     *          예산편성관리 번호
     * @return 예산편성 상세 예산편성 대상부서 목록 조회
     * @throws Exception
     */
    public List<MgtBudgetActDept> getMgtBudgetActDepts(@Param("budgetActDeptNo") int budgetActDeptNo, @Param("budgetActNo") int budgetActNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 예산편성 저장
     * @param mgtBudgetAct
     *          예산편성 관리
     * @return 결과
     * @throws Exception
     */
    public int createMgtBudgetAct(MgtBudgetAct mgtBudgetAct) throws Exception;

    /**
     * 예산편성 저장
     * @param mgtBudgetAct
     *          예산편성 관리
     * @return 결과
     * @throws Exception
     */
    public int updateMgtBudgetAct(MgtBudgetAct mgtBudgetAct) throws Exception;

    /**
     * 예산편성 삭제
     * @param budgetActNo
     *          예산편성 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteMgtBudgetAct(@Param("budgetActNo") int budgetActNo) throws Exception;

    /**
     * 예산편성 대상부서 저장
     * @param mgtBudgetActDept
     *          예산편성 관리
     * @return 결과
     * @throws Exception
     */
    public int saveMgtBudgetActDept(MgtBudgetActDept mgtBudgetActDept) throws Exception;

    /**
     * 예산편성 대상부서 삭제
     * @param budgetActDeptNo
     *          예산편성 대상부서 번호
     * @param budgetActNo
     *          예산편성 관리 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteMgtBudgetActDept(@Param("budgetActDeptNo") int budgetActDeptNo, @Param("budgetActNo") int budgetActNo) throws Exception;

    /**
     * 예산편성 대상부서별 예산계정 상세목록 조회
     * @param budgetActDeptNo
     *          예산편성 대상부서 번호
     * @param budgetActNo
     *          예산편성 관리 번호
     * @return 결과
     * @throws Exception
     */
    public List<MgtBudgetActDeptItem> getMgtBudgetActDeptItems(@Param("budgetActDeptNo") int budgetActDeptNo) throws Exception;

    /**
     * 예산편성 대상부서별 예산계정 저장
     * @param mgtBudgetActDeptItem
     *          예산편성 대상부서 예산계정
     * @return 결과
     * @throws Exception
     */
    public int saveMgtBudgetActDeptItem(MgtBudgetActDeptItem mgtBudgetActDeptItem) throws Exception;

    /**
     * 예산편성 대상부서별 예산계정 삭제
     * @param budgetActDeptItemNo
     *          예산편성부서계정번호
     * @param budgetActDeptNo
     *          예산편성부서번호
     * @return 결과
     * @throws Exception
     */
    public int deleteMgtBudgetActDeptItem(@Param("budgetActDeptItemNo") int budgetActDeptItemNo, @Param("budgetActDeptNo") int budgetActDeptNo) throws Exception;

    /**
     * 예산편성 대상부서 결재정보 업데이트
     * @param mgtBudgetActDept
     *          예산편성부서
     * @return 결과
     * @throws Exception
     */
    public int updateAppr(MgtBudgetActDept mgtBudgetActDept) throws Exception;

    /**
     * 예산편성 대상부서 상세 조회(현황 팝업시 조회)
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param deptCd
     *          대상부서코드
     * @return 결과
     * @throws Exception
     */
    public MgtBudgetActDept getMgtBudgetActDeptByStatInfo(@Param("year") String year, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
