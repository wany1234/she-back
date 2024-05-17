package com.she.safety.majDisaInsp.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.MajDisaInsp;
import com.she.safety.model.MajDisaInspSafe;

@Mapper
@Repository("com.she.safety.majDisaInsp.Mapper.MajDisaInspMapper")
public interface MajDisaInspMapper {

    /**
     * 중대시민재하점검 관리 등록
     * 
     * @param majDisaInsp
     * @return
     * @throws Exception
     */
    public int insertMajDisaInsp(MajDisaInsp majDisaInsp) throws Exception;

    /**
     * 중대시민재하점검 관리 수정
     * 
     * @param majDisaInsp
     * @return
     * @throws Exception
     */
    public int updateMajDisaInsp(MajDisaInsp majDisaInsp) throws Exception;

    /**
     * 중대시민재해점검 관리 조회
     * 
     * @param plantCd
     * @param startDt
     * @param endDt
     * @param mainDeptCd
     * @param chkNm
     * @param unRegiYn
     * @param stateCd
     * @return
     * @throws Exception
     */
    public List<MajDisaInsp> getMajDisaInsps(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("mainDeptCd") String mainDeptCd, @Param("chkNm") String chkNm, @Param("unRegiYn") String unRegiYn, @Param("stateCd") String stateCd, @Param("imprChk") String imprChk,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 중대시민재해점검 관리 상세
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public MajDisaInsp getMajDisaInsp(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 증대시민재해점검 안전계획 등록
     * 
     * @param majDisaInspSafe
     * @return
     * @throws Exception
     */
    public int insertMajDisaInspSafe(MajDisaInspSafe majDisaInspSafe) throws Exception;

    /**
     * 중대시민재해점검 안전계획 수정
     * 
     * @param majDisaInspSafe
     * @return
     * @throws Exception
     */
    public int updateMajDisaInspSafe(MajDisaInspSafe majDisaInspSafe) throws Exception;

    /**
     * 중대시민재해점검 안전계획 조회
     * 
     * @param majDisaInspSafeNo
     * @return
     * @throws Exception
     */
    public MajDisaInspSafe getMajDisaInspSafe(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 중대시민재해점검 삭제
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public int delMajDisaInsp(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 중대시민재해점검 삭제
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public int delMajDisaInspSaf(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 중대시민재해점검 삭제
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public int delMajDisaInspRaw(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 중대시민재해점검 삭제
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public int delMajDisaInspAct(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 중대시민재해점검 법정점검 등록
     * 
     * @param majDisaInspSafe
     * @return
     * @throws Exception
     */
    public int insertMajDisaInspRaw(MajDisaInspSafe majDisaInspSafe) throws Exception;

    /**
     * 중대시민재해점검 법정점검 수정
     * 
     * @param majDisaInspSafe
     * @return
     * @throws Exception
     */
    public int updateMajDisaInspRaw(MajDisaInspSafe majDisaInspSafe) throws Exception;

    /**
     * 중대시민재해점검 법정점검 조회
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public MajDisaInspSafe getMajDisaInspRaw(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 중대시민재해점검 우려사항 조치 등록
     * 
     * @param majDisaInspSafe
     * @return
     * @throws Exception
     */
    public int insertMajDisaInspAct(MajDisaInspSafe majDisaInspSafe) throws Exception;

    /**
     * 중대시민재해점검 우려사항 조치 수정
     * 
     * @param majDisaInspSafe
     * @return
     * @throws Exception
     */
    public int updateMajDisaInspAct(MajDisaInspSafe majDisaInspSafe) throws Exception;

    /**
     * 중대시민재해점검 법정점검 조회
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public MajDisaInspSafe getMajDisaInspAct(@Param("majDisaInspNo") int majDisaInspNo) throws Exception;

    /**
     * 중대시민재해점검 결재
     * 
     * @param majDisaInspNo
     * @param apprRqstNo
     * @param apprStepCd
     * @return
     * @throws Exception
     */
    public int updateAppr(@Param("majDisaInspNo") String majDisaInspNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd) throws Exception;

    /**
     * 중대시민재해점검 현황
     * 
     * @param plantCd
     * @param year
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getStatusList(@Param("plantCd") String plantCd, @Param("preYear") int preYear, @Param("year") String year) throws Exception;

}
