package com.she.safety.education.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.safety.education.service.EduStatusService;
import com.she.safety.model.EduCourseGrpSchedule;
import com.she.safety.model.EduMaster;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/education")
public class EduStatusController {

    @Autowired
    private EduStatusService eduStatusService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 교육 계획 현황 조회
     * 
     * @param fromYmd
     *            교육기간
     * @param toYmd
     *            교육기간
     * @param deptCd
     *            부서코드
     * @Param tgtDeptCd 대상부서코드
     * @param eduAttCd
     *            교육분류코드
     * @param safEduCourseNo
     *            교육과정번호
     * @param eduTypeCd
     *            교육구분코드
     * @param eduNm
     *            교육명
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/edustatuss")
    public ResponseEntity<List<EduMaster>> getEduStatuss(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String[] duration = this.requestMapper.convertObjectListAsStringArray(map.get("duration"));
        String fromYmd = "";
        String toYmd = "";
        if (duration != null && duration.length == 2) {
            fromYmd = duration[0];
            toYmd = duration[1];
        }
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String eduTypeCd = map.containsKey("eduTypeCd") ? map.get("eduTypeCd").toString() : "";
        String eduClassCd = map.containsKey("eduClassCd") ? map.get("eduClassCd").toString() : "";
        String eduNm = map.containsKey("eduNm") ? map.get("eduNm").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String pProcStepNm = map.containsKey("pProcStepNm") ? map.get("pProcStepNm").toString() : "";

        String pYear = map.containsKey("pYear") ? map.get("pYear").toString() : "";
        String pMonth = map.containsKey("pMonth") ? map.get("pMonth").toString() : "";

        String year = map.containsKey("year") ? map.get("year").toString() : "";

        String pStateCd = map.containsKey("pStateCd") ? map.get("pStateCd").toString() : "";
        String isTypeCd = map.containsKey("isTypeCd") ? map.get("isTypeCd").toString() : "";
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        String targetDeptCd = map.containsKey("targetDeptCd") ? map.get("targetDeptCd").toString() : "";
        String execDeptCd = map.containsKey("execDeptCd") ? map.get("execDeptCd").toString() : "";

        String mainDeptSubYn = map.containsKey("mainDeptSubYn") ? map.get("mainDeptSubYn").toString() : "";
        String targetDeptSubYn = map.containsKey("targetDeptSubYn") ? map.get("targetDeptSubYn").toString() : "";
        String execDeptSubYn = map.containsKey("execDeptSubYn") ? map.get("execDeptSubYn").toString() : "";
        String reEduYn = map.containsKey("reEduYn") ? map.get("reEduYn").toString() : "";
        String eduMethodCd = map.containsKey("eduMethodCd") ? map.get("eduMethodCd").toString() : "";

        List<EduMaster> eduMasterList = eduStatusService.getEduStatuss(year, pStateCd, isTypeCd, mainDeptCd, targetDeptCd, execDeptCd, fromYmd, toYmd, deptCd, deptSubYn, tgtDeptCd, eduAttCd, safEduCourseNo, eduTypeCd, eduNm, eduClassCd, plantCd, pProcStepNm, pYear, pMonth, mainDeptSubYn, targetDeptSubYn, execDeptSubYn, reEduYn, eduMethodCd,
                defaultParam);

        return ResponseEntity.ok().body(eduMasterList);
    }

    /**
     * 교육 결과 현황 조회
     */
    @GetMapping("/resultedus")
    public ResponseEntity<List<EduMaster>> getEduResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String[] duration = this.requestMapper.convertObjectListAsStringArray(map.get("duration"));
        String fromYmd = "";
        String toYmd = "";
        if (duration != null && duration.length == 2) {
            fromYmd = duration[0];
            toYmd = duration[1];
        }
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String eduTypeCd = map.containsKey("eduTypeCd") ? map.get("eduTypeCd").toString() : "";
        String eduClassCd = map.containsKey("eduClassCd") ? map.get("eduClassCd").toString() : "";
        String eduNm = map.containsKey("eduNm") ? map.get("eduNm").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String rProcStepNm = map.containsKey("rProcStepNm") ? map.get("rProcStepNm").toString() : "";

        String pYear = map.containsKey("pYear") ? map.get("pYear").toString() : "";
        String pMonth = map.containsKey("pMonth") ? map.get("pMonth").toString() : "";

        String year = map.containsKey("year") ? map.get("year").toString() : "";

        String rStateCd = map.containsKey("rStateCd") ? map.get("rStateCd").toString() : "";
        String isTypeCd = map.containsKey("isTypeCd") ? map.get("isTypeCd").toString() : "";
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        String targetDeptCd = map.containsKey("targetDeptCd") ? map.get("targetDeptCd").toString() : "";
        String execDeptCd = map.containsKey("execDeptCd") ? map.get("execDeptCd").toString() : "";

        String mainDeptSubYn = map.containsKey("mainDeptSubYn") ? map.get("mainDeptSubYn").toString() : "";
        String targetDeptSubYn = map.containsKey("targetDeptSubYn") ? map.get("targetDeptSubYn").toString() : "";
        String execDeptSubYn = map.containsKey("execDeptSubYn") ? map.get("execDeptSubYn").toString() : "";

        List<EduMaster> eduMasterList = eduStatusService.getEduResults(year, rStateCd, isTypeCd, mainDeptCd, targetDeptCd, execDeptCd, fromYmd, toYmd, deptCd, deptSubYn, tgtDeptCd, eduAttCd, safEduCourseNo, eduTypeCd, eduNm, eduClassCd, plantCd, rProcStepNm, pYear, pMonth, mainDeptSubYn, targetDeptSubYn, execDeptSubYn, defaultParam);

        return ResponseEntity.ok().body(eduMasterList);
    }

    /**
     * 교육 문제 조회 (모바일 )
     * 
     * @param userId
     *            사용자아이디
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/mobile/edustatuss")
    public ResponseEntity<List<EduMaster>> getMobileEduStatuss(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<EduMaster> eduMasterList = eduStatusService.getMobileEduStatuss(userId, plantCd, defaultParam);

        return ResponseEntity.ok().body(eduMasterList);
    }

    /**
     * 교육 계획 및 실적 조회
     * 
     * @param year
     *            교육연도
     * @param safEduCourseNo
     *            교육과정번호
     * @Param tgtDeptCd 대상부서코드
     * @param eduAttCd
     *            교육분류코드
     * @param eduNm
     *            교육명
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/edudeptstatuss")
    public ResponseEntity<List<EduMaster>> getEduDeptStatuss(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        String eduClassCd = map.containsKey("eduClassCd") ? map.get("eduClassCd").toString() : "";
        String completYn = map.containsKey("completYn") ? map.get("completYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<EduMaster> eduMasterList = eduStatusService.getEduDeptStatuss(year, eduAttCd, safEduCourseNo, userId, deptCd, deptSubYn, eduClassCd, completYn, plantCd, defaultParam);

        return ResponseEntity.ok().body(eduMasterList);
    }

    /**
     * 개인별 교육 현황 조회
     * 
     * @param year
     *            교육연도
     * @param safEduCourseNo
     *            교육과정번호
     * @param eduAttCd
     *            교육분류코드
     * @param userId
     *            이수자
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/edupersonstatuss")
    public ResponseEntity<List<EduMaster>> getEduPersonStatuss(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String eduClassCd = map.containsKey("eduClassCd") ? map.get("eduClassCd").toString() : "";
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String eduNm = map.containsKey("eduNm") ? map.get("eduNm").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        List<EduMaster> eduMasterList = eduStatusService.getEduPersonStatuss(year, safEduCourseNo, eduAttCd, userId, deptCd, eduClassCd, eduNm, plantCd, defaultParam);

        return ResponseEntity.ok().body(eduMasterList);
    }

    /**
     * 교육 대상자 여부 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/mobile/edudtlpsncnt")
    public ResponseEntity<Integer> getEduDtlPsnCnt(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt(map.get("safEduMstNo").toString()) : 0;
        return ResponseEntity.ok().body(eduStatusService.getEduDtlPsnCnt(userId, safEduMstNo, defaultParam));
    }

    /**
     * 개인별 교육도래일 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/eduuserschedules")
    public ResponseEntity<List<EduCourseGrpSchedule>> getUserEduSchedules(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        String eduClassCd = map.containsKey("eduClassCd") ? map.get("eduClassCd").toString() : "";
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<EduCourseGrpSchedule> eduCourseGrpSchedules = eduStatusService.getUserEduSchedules(safEduCourseNo, eduAttCd, userId, deptCd, deptSubYn, eduClassCd, plantCd, defaultParam);

        return ResponseEntity.ok().body(eduCourseGrpSchedules);
    }

    /**
     * 교육 문제 풀이 조회
     * 
     * @param fromYmd
     *            교육기간
     * @param toYmd
     *            교육기간
     * @param eduAttCd
     *            교육분류코드
     * @param safEduCourseNo
     *            교육과정번호
     * @param eduTypeCd
     *            교육구분코드
     * @param eduNm
     *            교육명
     * @param processStepNm
     *            진행상태
     * @return 교육풀이 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduProblemss")
    public ResponseEntity<List<EduMaster>> getEduProblemss(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String[] duration = this.requestMapper.convertObjectListAsStringArray(map.get("duration")); // 교육기간
        String fromYmd = "";
        String toYmd = "";
        if (duration != null && duration.length == 2) {
            fromYmd = duration[0];
            toYmd = duration[1];
        }

        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : ""; // 교육대분류
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0; // 교육과정
        String eduTypeCd = map.containsKey("eduTypeCd") ? map.get("eduTypeCd").toString() : ""; // 교육구분
        String eduClassCd = map.containsKey("eduClassCd") ? map.get("eduClassCd").toString() : ""; // 교육분류
        String eduNm = map.containsKey("eduNm") ? map.get("eduNm").toString() : ""; // 교육명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : ""; // 사업자
        String processStepNm = map.containsKey("processStepNm") ? // 진행상태
                map.get("processStepNm").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : ""; // 대상자
        String pYear = map.containsKey("pYear") ? map.get("pYear").toString() : "";
        String pMonth = map.containsKey("pMonth") ? map.get("pMonth").toString() : "";

        List<EduMaster> eduMasterList = eduStatusService.getEduProblemss(fromYmd, toYmd, eduAttCd, safEduCourseNo, eduTypeCd, eduNm, eduClassCd, plantCd, processStepNm, pYear, pMonth, userId, defaultParam);

        return ResponseEntity.ok().body(eduMasterList);
    }

    /**
     * 교육 내부인원
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @param completYn
     *            이수여부
     */
    @GetMapping("/eduuserlists")
    public ResponseEntity<List<EduMaster>> getEduUserList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduMstNo = parameter.containsKey("safEduMstNo") ? Integer.parseInt(parameter.get("safEduMstNo").toString()) : 0;
        String completCheckYn = map.containsKey("completCheckYn") ? map.get("completCheckYn").toString() : "";
        String notReEduYn = map.containsKey("notReEduYn") ? map.get("notReEduYn").toString() : "";
        return ResponseEntity.ok().body(eduStatusService.getEduUserList(safEduMstNo, completCheckYn, notReEduYn, defaultParam));
    }

    /**
     * 교육 외부인원
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     */
    @GetMapping("/eduoutuserlists")
    public ResponseEntity<List<EduMaster>> getEduOutUserList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduMstNo = parameter.containsKey("safEduMstNo") ? Integer.parseInt(parameter.get("safEduMstNo").toString()) : 0;
        return ResponseEntity.ok().body(eduStatusService.getEduOutUserList(safEduMstNo, defaultParam));
    }

    /**
     * 교육 이수Y 상세조회
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @param completYn
     *            이수여부
     */
    @GetMapping("/educomytotallists")
    public ResponseEntity<List<EduMaster>> getEduComYList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduMstNo = parameter.containsKey("safEduMstNo") ? Integer.parseInt(parameter.get("safEduMstNo").toString()) : 0;
        String completCheckYn = map.containsKey("completCheckYn") ? map.get("completCheckYn").toString() : "";
        return ResponseEntity.ok().body(eduStatusService.getEduComYList(safEduMstNo, completCheckYn, defaultParam));
    }

    /**
     * 교육결과 현황 목록 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 현황 목록
     * @throws Exception
     */
    @GetMapping("/resultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getEduResultStatusList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String isTypeCd = map.containsKey("isTypeCd") ? map.get("isTypeCd").toString() : "";
        String totalFlag = map.containsKey("totalFlag") ? map.get("totalFlag").toString() : "";
        return ResponseEntity.ok().body(eduStatusService.getEduResultStatusList(plantCd, year, isTypeCd, totalFlag, defaultParam));
    }

    /**
     * 교육결과 관리 목록 상세 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 관리 목록
     * @throws Exception
     */
    @GetMapping("/eduresultlists")
    public ResponseEntity<List<EduMaster>> getEduResultLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String completCheckYn = map.containsKey("completCheckYn") ? map.get("completCheckYn").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String isTypeCd = map.containsKey("isTypeCd") ? map.get("isTypeCd").toString() : "";
        int monFlag = parameter.containsKey("monFlag") ? Integer.parseInt(parameter.get("monFlag").toString()) : 0;
        String gubun = map.containsKey("gubun") ? map.get("gubun").toString() : "";
        return ResponseEntity.ok().body(eduStatusService.getEduResultLists(plantCd, completCheckYn, year, isTypeCd, monFlag, gubun, defaultParam));
    }

}
