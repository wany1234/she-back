package com.she.mgt.industrialSafetyHealthCommittee.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.mgt.industrialSafetyHealthCommittee.service.IndustrialSafetyHealthCommitteeService;
import com.she.mgt.model.IndustrialSafetyHealthCommittee;
import com.she.mgt.model.MgtCommitteeAgendaImpr;
import com.she.mgt.model.MgtCommitteePsn;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 산업안전보건위원회 회의록
 */
@RestController
@RequestMapping("/api/mgt/industrialsafetyhealthcommittee/")
@Api(value = "/api/mgt/industrialsafetyhealthcommittee/", description = "산업안전보건위원회 회의록 서비스")
public class IndustrialSafetyHealthCommitteeController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private IndustrialSafetyHealthCommitteeService industrialSafetyHealthCommitteeService;

    /**
     * 회의록 조회
     * 
     * @param parameter
     *            검색조건
     * @return 회의록 목록
     */
    @ApiOperation(value = "회의록 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "년도(기간)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cmiClsCd", value = "구분", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "confNm", value = "제목(회의록명)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "progressStepCd", value = "진행단계", required = false, dataType = "string", paramType = "query") })
    @GetMapping("industrialsafetyhealthcommittees")
    public ResponseEntity<List<IndustrialSafetyHealthCommittee>> getIndustrialSafetyHealthCommittees(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 시작일
        String startDt = map.containsKey("startDt") ? map.get("startDt").toString() : "";
        // 종료일
        String endDt = map.containsKey("endDt") ? map.get("endDt").toString() : "";
        // 구분
        String cmiClsCd = map.containsKey("cmiClsCd") ? map.get("cmiClsCd").toString() : "";
        // 제목
        String confNm = map.containsKey("confNm") ? map.get("confNm").toString() : "";
        // 진행단계
        String progressStepCd = map.containsKey("progressStepCd") ? map.get("progressStepCd").toString() : "";
        // 주간부서
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        // 하위부서 여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        // 상태
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        // 상/하반기 코드
        String halfTypeCd = map.containsKey("halfTypeCd") ? map.get("halfTypeCd").toString() : "";
        // 개선조치 기한초과
        String imprChk = map.containsKey("imprChk") ? map.get("imprChk").toString() : "";

        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getIndustrialSafetyHealthCommittees(plantCd, startDt, endDt, cmiClsCd, confNm, progressStepCd, mainDeptCd, deptSubYn, stateCd, halfTypeCd, imprChk, defaultParam));
    }

    /**
     * 회의록 등록
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 키
     */
    @ApiOperation(value = "회의록 등록[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "industrialSafetyHealthCommittee", value = "회의록", required = false, dataType = "IndustrialSafetyHealthCommittee", paramType = "body") })
    @PostMapping("industrialsafetyhealthcommittee")
    public ResponseEntity<Integer> createIndustrialSafetyHealthCommittee(@RequestBody IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.createIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittee));
    }

    /**
     * 회의록 상세 조회
     * 
     * @param committeeConfNo
     *            회의록번호
     * @return 회의록
     */
    @ApiOperation(value = "회의록 상세조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "committeeConfNo", value = "회의록번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("industrialsafetyhealthcommittee/{committeeConfNo}")
    public ResponseEntity<IndustrialSafetyHealthCommittee> getIndustrialSafetyHealthCommittee(@PathVariable("committeeConfNo") int committeeConfNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getIndustrialSafetyHealthCommittee(committeeConfNo, defaultParam));
    }

    /**
     * 회의록 수정
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 키
     */
    @ApiOperation(value = "회의록 수정[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "industrialSafetyHealthCommittee", value = "회의록", required = false, dataType = "IndustrialSafetyHealthCommittee", paramType = "body") })
    @PutMapping("industrialsafetyhealthcommittee")
    public ResponseEntity<Integer> updateIndustrialSafetyHealthCommittee(@RequestBody IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.updateIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittee));
    }

    /**
     * 회의록 완료
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 키
     */
    @ApiOperation(value = "회의록 완료[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "industrialSafetyHealthCommittee", value = "회의록", required = false, dataType = "IndustrialSafetyHealthCommittee", paramType = "body") })
    @PutMapping("completeindustrialsafetyhealthcommittee")
    public ResponseEntity<Integer> completeIndustrialSafetyHealthCommittee(@RequestBody IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.completeIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittee));
    }

    /**
     * 회의록 삭제
     * 
     * @param industrialSafetyHealthCommittees
     *            회의록
     * @return 변경 행 수
     */
    @ApiOperation(value = "회의록 삭제[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "industrialSafetyHealthCommittees", value = "회의록", required = false, dataType = "List", paramType = "path") })
    @DeleteMapping("industrialsafetyhealthcommittee")
    public ResponseEntity<Integer> deleteIndustrialSafetyHealthCommittee(@RequestBody List<IndustrialSafetyHealthCommittee> industrialSafetyHealthCommittees) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.deleteIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittees));
    }

    /**
     * 개선조치사항 항목 조회
     * 
     * @param parameter
     *            (개선분류코드 코드, 개선진행간계 코드, 조치구분, 제목, 개선요청부서 코드, 조치부서 코드, 요청일,
     *            관련테이블 PKID)
     * @return PSM 감사결과_개선사항 목록
     * @throws Exception
     */
    @GetMapping("meetingAgendaImpr")
    public ResponseEntity<List<MgtCommitteeAgendaImpr>> getMeetingAgendaImpr(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String imprClassCd = map.containsKey("imprClassCd") ? map.get("imprClassCd").toString() : "";
        int committeeConfNo = map.containsKey("committeeConfNo") ? Integer.parseInt(map.get("committeeConfNo").toString()) : 0;
        String apprFlag = map.containsKey("apprFlag") ? map.get("apprFlag").toString() : "";

        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getMeetingAgendaImpr(imprClassCd, committeeConfNo, apprFlag, defaultParam));

    }

    /**
     * 회의록 조회(협력업체)
     *
     * @param parameter
     *            검색조건
     * @return 회의록 목록
     */
    @ApiOperation(value = "회의록 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "년도(기간)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cmiClsCd", value = "구분", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "confNm", value = "제목(회의록명)", required = false, dataType = "string", paramType = "query") })
    @GetMapping("vendor")
    public ResponseEntity<List<IndustrialSafetyHealthCommittee>> getVendorCommittees(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 시작일
        String startDt = map.containsKey("startDt") ? map.get("startDt").toString() : "";
        // 종료일
        String endDt = map.containsKey("endDt") ? map.get("endDt").toString() : "";
        // 업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 제목
        String confNm = map.containsKey("confNm") ? map.get("confNm").toString() : "";

        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getVendorCommittees(plantCd, startDt, endDt, vendorCd, confNm, defaultParam));
    }

    /**
     * 참석자 조회(모바일)
     *
     * @param parameter
     *            검색조건
     * @return 참석자 목록
     */
    @GetMapping("committeepsn")
    public ResponseEntity<List<MgtCommitteePsn>> getMgtCommitteePsnSigns(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 참석자구분코드
        String psnClsCd = map.containsKey("psnClsCd") ? map.get("psnClsCd").toString() : "";
        // 시작일
        String startDt = map.containsKey("startDt") ? map.get("startDt").toString() : "";
        // 종료일
        String endDt = map.containsKey("endDt") ? map.get("endDt").toString() : "";
        // 사용자ID
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 제목
        String confNm = map.containsKey("confNm") ? map.get("confNm").toString() : "";

        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getMgtCommitteePsnSigns(plantCd, psnClsCd, startDt, endDt, userId, vendorCd, confNm, defaultParam));
    }

    /**
     * 참석자 상세 조회(모바일)
     *
     * @param mgtCommitteePsnNo
     *            참석자번호
     * @return 산업안전보건위원회 회의록 참석자 상세
     * @throws Exception
     */
    @ApiOperation(value = "참석자 상세 조회(모바일)", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtCommitteePsnNo", value = "참석자번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("committeepsn/{mgtCommitteePsnNo}")
    public ResponseEntity<MgtCommitteePsn> getMgtCommitteePsnSign(@PathVariable("mgtCommitteePsnNo") int mgtCommitteePsnNo) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getMgtCommitteePsnSign(mgtCommitteePsnNo));
    }

    /**
     * 참석자 사인이미지, 사인완료여부, 사인등록일시 수정
     *
     * @param mgtCommitteePsn
     *            산업안전보건위원회_회의록_참석자
     * @return 참석자 번호
     * @throws Exception
     */
    @ApiOperation(value = "참석자 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtCommitteePsn", value = "참석자", required = false, dataType = "MgtCommitteePsn", paramType = "body") })
    @PutMapping("committeepsn")
    public ResponseEntity<Integer> updateMgtCommitteePsnSign(@RequestBody MgtCommitteePsn mgtCommitteePsn) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.updateMgtCommitteePsnSign(mgtCommitteePsn));
    }

    /**
     * 종사자의견청취 현황
     * 
     * @param parameter
     *            검색조건
     * @return 종사자의견청취 현황
     * @throws Exception
     */
    @GetMapping("/status")
    public ResponseEntity<List<HashMap<String, Object>>> getListenStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String halfTypeCd = map.containsKey("halfTypeCd") ? map.get("halfTypeCd").toString() : "";
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getListenStatus(plantCd, year, halfTypeCd, defaultParam));
    }

    /**
     * 개선조치사항 팝업항목 조회
     * 
     * @param parameter
     * 
     * @return 개선조치사항 팝업항목 조회
     * @throws Exception
     */
    @GetMapping("/meetingAgendaImprPopup")
    public ResponseEntity<List<MgtCommitteeAgendaImpr>> getMeetingAgendaImprPopup(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String imprClassCd = map.containsKey("imprClassCd") ? map.get("imprClassCd").toString() : "";
        String gubunType = map.containsKey("gubun_type") ? map.get("gubun_type").toString() : "";
        String imprFlag = map.containsKey("imprFlag") ? map.get("imprFlag").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getMeetingAgendaImprPopup(imprClassCd, gubunType, imprFlag, plantCd, year, defaultParam));

    }

    /**
     * 청취 상태 목록 팝업
     * 
     * @param parameter
     * 
     * @return 청취 상태 목록 팝업
     * @throws Exception
     */
    @GetMapping("/listenResultMgmtPopup")
    public ResponseEntity<List<IndustrialSafetyHealthCommittee>> getListenResultMgmtPopup(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String halfTypeCd = map.containsKey("halfTypeCd") ? map.get("halfTypeCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String progressStepCd = map.containsKey("progressStepCd") ? map.get("progressStepCd").toString() : "";

        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.getListenResultMgmtPopup(halfTypeCd, plantCd, year, progressStepCd, defaultParam));

    }

    /**
     * 개선요청 삭제
     *
     * @param parameter
     * @return 개선요청 삭제
     * @throws Exception
     */
    @DeleteMapping("/imprDelete/{refTableId}/{imprClassCd}")
    public ResponseEntity<Integer> deleteImpr(@PathVariable("refTableId") int refTableId, @PathVariable("imprClassCd") String imprClassCd) throws Exception {
        return ResponseEntity.ok().body(industrialSafetyHealthCommitteeService.deleteImpr(refTableId, imprClassCd));
    }
}
