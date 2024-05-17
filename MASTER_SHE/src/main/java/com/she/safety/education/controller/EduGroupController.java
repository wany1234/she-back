package com.she.safety.education.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.she.safety.education.service.EduGroupService;
import com.she.safety.model.EduGroup;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/edugroup")
public class EduGroupController {
    @Autowired
    private EduGroupService eduGroupService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 교육그룹결과목록
     * 
     * @param groupNm
     *            그룹명
     * @param plantCd
     *            사업장
     * 
     * @return 교육그룹결과목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/edugroups")
    public ResponseEntity<List<EduGroup>> getEduCourses(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String groupNm = map.containsKey("groupNm") ? map.get("groupNm").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<EduGroup> eduGroups = eduGroupService.getEduGroups(groupNm, plantCd, useYn, defaultParam);

        return ResponseEntity.ok().body(eduGroups);
    }

    /**
     * 교육그룹결과조회
     * 
     * @param eduGrpNo
     *            그룹번호
     * 
     * 
     * @return 교육그룹결과
     * @throws Exception
     *             예외
     */
    @GetMapping("/edugroup/{eduGrpNo}")
    public ResponseEntity<EduGroup> getEduGroup(@PathVariable("eduGrpNo") int eduGrpNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        EduGroup eduGroup = eduGroupService.getEduGroup(eduGrpNo, defaultParam);
        return ResponseEntity.ok().body(eduGroup);
    }

    /**
     * 교육그룹결과 등록
     * 
     * @param eduGroup
     *            교육그룹결과
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    @PostMapping("/edugroup")
    public ResponseEntity<Integer> createEduGroup(@RequestBody EduGroup eduGroup) throws Exception {
        return ResponseEntity.ok().body(eduGroupService.createEduGroup(eduGroup));
    }

    /**
     * 교육그룹결과 수정
     * 
     * @param eduGroup
     *            교육그룹결과
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    @PutMapping("/edugroup")
    public ResponseEntity<Integer> updateEduGroup(@RequestBody EduGroup eduGroup) throws Exception {
        return ResponseEntity.ok().body(eduGroupService.updateEduGroup(eduGroup));
    }

    /**
     * 교육그룹결과 삭제
     * 
     * @param eduGroup
     *            교육그룹결과 번호
     * 
     * @return 교육그룹결과목록
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/edugroup/{eduGrpNo}")
    public ResponseEntity<Integer> deleteEduGroup(@PathVariable("eduGrpNo") int eduGrpNo) throws Exception {
        return ResponseEntity.ok().body(eduGroupService.deleteEduGroup(eduGrpNo));
    }

}
