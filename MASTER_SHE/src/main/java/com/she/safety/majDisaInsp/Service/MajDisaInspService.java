package com.she.safety.majDisaInsp.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.safety.majDisaInsp.Mapper.MajDisaInspMapper;
import com.she.safety.model.MajDisaInsp;
import com.she.utils.ConstVal;

@Service
public class MajDisaInspService {

    @Autowired
    private MajDisaInspMapper majDisaInspMapper;

    @Autowired
    private ImprService imprService;

    /**
     * 중대시민재하점검 관리 등록
     * 
     * @param majDisaInsp
     * @return
     * @throws Exception
     */
    @Transactional
    public MajDisaInsp insertMajDisaInsp(MajDisaInsp majDisaInsp) throws Exception {
        majDisaInspMapper.insertMajDisaInsp(majDisaInsp);
        majDisaInsp.getMajDisaInspSafe().setMajDisaInspNo(majDisaInsp.getMajDisaInspNo());
        majDisaInspMapper.insertMajDisaInspSafe(majDisaInsp.getMajDisaInspSafe());
        majDisaInsp.getMajDisaInspRaw().setMajDisaInspNo(majDisaInsp.getMajDisaInspNo());
        majDisaInspMapper.insertMajDisaInspRaw(majDisaInsp.getMajDisaInspRaw());
        majDisaInsp.getMajDisaInspAct().setMajDisaInspNo(majDisaInsp.getMajDisaInspNo());
        majDisaInspMapper.insertMajDisaInspAct(majDisaInsp.getMajDisaInspAct());

        return majDisaInsp;
    }

    /**
     * 중대시민재하점검 관리 수정
     * 
     * @param majDisaInsp
     * @return
     * @throws Exception
     */
    public MajDisaInsp updateMajDisaInsp(MajDisaInsp majDisaInsp) throws Exception {
        majDisaInspMapper.updateMajDisaInsp(majDisaInsp);
        majDisaInsp.getMajDisaInspSafe().setMajDisaInspNo(majDisaInsp.getMajDisaInspNo());
        majDisaInspMapper.updateMajDisaInspSafe(majDisaInsp.getMajDisaInspSafe());
        majDisaInsp.getMajDisaInspRaw().setMajDisaInspNo(majDisaInsp.getMajDisaInspNo());
        majDisaInspMapper.updateMajDisaInspRaw(majDisaInsp.getMajDisaInspRaw());
        majDisaInsp.getMajDisaInspAct().setMajDisaInspNo(majDisaInsp.getMajDisaInspNo());
        majDisaInspMapper.updateMajDisaInspAct(majDisaInsp.getMajDisaInspAct());
        return majDisaInsp;
    }

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
    public List<MajDisaInsp> getMajDisaInsps(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("mainDeptCd") String mainDeptCd, @Param("chkNm") String chkNm, @Param("unRegiYn") String unRegiYn, @Param("stateCd") String stateCd, @Param("imprChk") String imprChk, DefaultParam defaultParam)
            throws Exception {
        return majDisaInspMapper.getMajDisaInsps(plantCd, startDt, endDt, mainDeptCd, chkNm, unRegiYn, stateCd, imprChk, defaultParam);
    }

    /**
     * 중대시민재해점검 관리 상세
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public MajDisaInsp getMajDisaInsp(@Param("majDisaInspNo") int majDisaInspNo) throws Exception {
        MajDisaInsp majDisaInsp = majDisaInspMapper.getMajDisaInsp(majDisaInspNo);
        majDisaInsp.setMajDisaInspSafe(majDisaInspMapper.getMajDisaInspSafe(majDisaInspNo));
        majDisaInsp.setMajDisaInspRaw(majDisaInspMapper.getMajDisaInspRaw(majDisaInspNo));
        majDisaInsp.setMajDisaInspAct(majDisaInspMapper.getMajDisaInspAct(majDisaInspNo));
        return majDisaInsp;
    }

    /**
     * 중대시민재해점검 삭제
     * 
     * @param majDisaInspNo
     * @return
     * @throws Exception
     */
    public int delMajDisaInsp(@Param("majDisaInspNo") int majDisaInspNo) throws Exception {
        int result = majDisaInspMapper.delMajDisaInsp(majDisaInspNo);
        result += majDisaInspMapper.delMajDisaInspSaf(majDisaInspNo);
        result += majDisaInspMapper.delMajDisaInspRaw(majDisaInspNo);
        result += majDisaInspMapper.delMajDisaInspAct(majDisaInspNo);

        return result;
    }

    @Transactional
    public int updateAppr(String majDisaInspNo, String apprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += majDisaInspMapper.updateAppr(majDisaInspNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);

        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += majDisaInspMapper.updateAppr(majDisaInspNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료 - 결재 요청 PSEND(완료) 으로 세팅
            resultNo += majDisaInspMapper.updateAppr(majDisaInspNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
            int[] arr = Stream.of(majDisaInspNo.split(",")).mapToInt(Integer::parseInt).toArray();
            for (int no : arr) {
                imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_MAJ_DISA, no, "");
            }

        }

        return resultNo;
    }

    /**
     * 중대시민재해점검 현황
     * 
     * @param plantCd
     * @param year
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getStatusList(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception {
        int preYear = Integer.valueOf(year) - 1;
        return majDisaInspMapper.getStatusList(plantCd, preYear, year);
    }

}
