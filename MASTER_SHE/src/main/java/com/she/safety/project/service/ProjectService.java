package com.she.safety.project.service;

import com.she.safety.model.ProjectMaster;
import com.she.safety.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 프로젝트마스터 상세 조회
     *
     * @param plantCd
     *            프로젝트 코드
     * @return 프로젝트마스터 상세
     * @throws Exception
     */
    public ProjectMaster getProjectMaster(String plantCd) throws Exception {
        ProjectMaster projectMaster = projectMapper.getProjectMaster(plantCd);

        return projectMaster;
    }
}
