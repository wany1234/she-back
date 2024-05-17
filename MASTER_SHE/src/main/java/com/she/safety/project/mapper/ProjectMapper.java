package com.she.safety.project.mapper;

import com.she.safety.model.ProjectMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.safety.project.mapper.ProjectMapper")
public interface ProjectMapper {

    /**
     * 프로젝트마스터 상세 조회
     *
     * @param plantCd
     *            프로젝트 코드
     * @return 프로젝트마스터 상세
     * @throws Exception
     */
    public ProjectMaster getProjectMaster(@Param("plantCd") String plantCd) throws Exception;
}
