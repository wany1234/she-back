package com.she.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.she.common.model.Diagram;

@Mapper
@Repository("com.she.common.mapper.DiagramFileMapper")
public interface DiagramFileMapper {
    
    /**
     * 도면 생성
     * @param diagram 도면 정보
     * @return 도면 Seq
     * @throws Exception
     */
    public int createDiagram(Diagram diagram) throws Exception;
    
    /**
     * 도면 수정
     * @param diagram 도면 정보
     * @return 도면 Seq
     * @throws Exception
     */
    public int updateDiagram(Diagram diagram) throws Exception;
    
    /**
     * 도면 체크
     * @param diagram 도면 정보
     * @return 도면정보 일치 여부
     * @throws Exception
     */
    public int checkDiagram(Diagram diagram) throws Exception;
}
