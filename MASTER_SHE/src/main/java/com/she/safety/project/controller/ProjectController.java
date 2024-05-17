package com.she.safety.project.controller;

import com.she.safety.model.ProjectMaster;
import com.she.safety.project.service.ProjectService;
import com.she.utils.RequestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saf/project/")
@Api(value = "/api/saf/project/", description = "프로젝트(공사)")
public class ProjectController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ProjectService projectService;

    /**
     * 프로젝트마스터 상세 조회
     *
     * @param plantCd
     *            프로젝트 코드
     * @return 프로젝트마스터 상세
     */
    @ApiOperation(value = "프로젝트 마스터 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "프로젝트코드", required = false, dataType = "String", paramType = "path")})
    @GetMapping("projectmaster/{plantCd}")
    public ResponseEntity<ProjectMaster> getProjectMaster(@PathVariable("plantCd") String plantCd) throws Exception {
        return ResponseEntity.ok().body(projectService.getProjectMaster(plantCd));
    }
}
