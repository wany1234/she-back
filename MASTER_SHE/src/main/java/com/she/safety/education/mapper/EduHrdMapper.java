package com.she.safety.education.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.EduHrd;

@Mapper
@Repository("com.she.safety.education.mapper.EduHrdMapper")
public interface EduHrdMapper {

    /**
     * 해당 교육과정의 교육이수자 목록
     * 
     * @param sqNo
     *            고유 키
     * @param crsCode
     *            교육과정번호
     * @param sqYear
     *            연도
     * @return EduCourse 교육과정
     * @throws Exception
     *             예외
     */
    public List<EduHrd> getEduHrd(@Param("sqNo") int sqNo, @Param("crsCode") String crsCode, @Param("sqYear") String sqYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
