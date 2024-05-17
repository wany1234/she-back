package com.she.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.Diagram;
import com.she.common.service.DiagramFileService;
import com.she.common.service.SafAttachFileService;

@RestController
public class DiagramFileController {
    // TODO : 파일 업로드 정보 처리용 서비스
    @Autowired
    private DiagramFileService diagramFileService;

    @Autowired
    private SafAttachFileService safAttachFileService;

    /**
     * 도면파일 생성
     * 
     * @param diagram
     *            도면 정보
     * @return 도면 Seq
     * @throws Exception
     */
    @PostMapping("/api/attachfile/diagramuploadfiles")
    public ResponseEntity<Diagram> createDiagram(@RequestBody Diagram diagram) throws Exception {
        boolean revision = false;
        if (diagram.getDiagGrpNo() > 0) {
            revision = true;
        }

        Diagram diagraminfo = diagramFileService.createDiagram(diagram, revision);

        if (revision && diagram.getSelectRow() != null) {
            safAttachFileService.createBeforeFile(diagraminfo.getDiagramSeq(), diagram.getSelectRow());
        }

        return ResponseEntity.ok().body(diagraminfo);
    }

    /**
     * 도면파일 수정
     * 
     * @param diagram
     *            도면 정보
     * @return 도면 Seq
     * @throws Exception
     */
    @PutMapping("/api/attachfile/diagramuploadfiles")
    public ResponseEntity<Integer> updateDiagram(@RequestBody Diagram diagram) throws Exception {
        int diagramSeq = diagramFileService.updateDiagram(diagram);

        return ResponseEntity.ok().body(diagramSeq);
    }

    /**
     * 도면파일 체크
     * 
     * @param diagram
     *            도면 정보
     * @return 도면 정보 일치 여부
     * @throws Exception
     */
    @PostMapping("/api/attachfile/checkdiagram")
    public ResponseEntity<Integer> checkDiagram(@RequestBody Diagram diagram) throws Exception {
        int cnt = diagramFileService.checkDiagram(diagram);

        return ResponseEntity.ok().body(cnt);
    }

}
