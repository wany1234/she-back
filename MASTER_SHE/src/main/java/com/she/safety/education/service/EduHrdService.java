package com.she.safety.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.education.mapper.EduHrdMapper;
import com.she.safety.model.EduHrd;

@Service
public class EduHrdService {
    @Autowired
    private EduHrdMapper eduHrdMapper;

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

    public List<EduHrd> getEduHrd(int sqNo, String crsCode, String sqYear, DefaultParam defaultParam) throws Exception {
        return eduHrdMapper.getEduHrd(sqNo, crsCode, sqYear, defaultParam);

    }
}
