package com.she.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.DiagramFileMapper;
import com.she.common.model.Diagram;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.ConstVal;

/**
 * 첨부파일 서비스 클래스
 */
@Service
public class DiagramFileService {
    @Autowired
    private DiagramFileMapper diagramFileMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 도면파일 생성
     * 
     * @param diagram
     *            도면 정보
     * @return 도면 Seq
     * @throws Exception
     */
    public Diagram createDiagram(Diagram diagram, boolean revision) throws Exception {
        if (revision) {
            diagram.setRevNum(diagram.getRevNum() + 1);
        }

        diagramFileMapper.createDiagram(diagram);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(diagram.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(diagram.getDiagramSeq()));
        changeRefWork.setRefTableNm("saf_diagram");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(diagram.getCreateUserId());
        changeService.taskChange(changeRefWork);
        return diagram;
    }

    /**
     * 도면파일 수정
     * 
     * @param diagram
     *            도면 정보
     * @return 도면 Seq
     * @throws Exception
     */
    public int updateDiagram(Diagram diagram) throws Exception {
        diagramFileMapper.updateDiagram(diagram);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(diagram.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(diagram.getDiagramSeq()));
        changeRefWork.setRefTableNm("saf_diagram");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(diagram.getUpdateUserId());
        changeService.taskChange(changeRefWork);
        return diagram.getDiagramSeq();
    }

    /**
     * 도면파일 체크
     * 
     * @param diagram
     *            도면 정보
     * @return 도면 정보 일치여부
     * @throws Exception
     */
    public int checkDiagram(Diagram diagram) throws Exception {
        return diagramFileMapper.checkDiagram(diagram);
    }
}
