package com.she.mgt.baseInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.mgt.baseInfo.service.ReferenceRoomService;
import com.she.mgt.model.ReferenceRoom;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/saf/board/referenceroom")
public class ReferenceRoomController {

    @Autowired
    private ReferenceRoomService referenceRoomService;

    /**
     * 자료실 전체 조회
     * 
     * @param plantCd,
     *            deptCd, kindCd, title
     * @return 자료실 목록
     * @throws Exception
     */

    @GetMapping()
    public ResponseEntity<List<ReferenceRoom>> getReferenceRoomList(@RequestParam HashMap<String, Object> parameter) throws Exception {

        String plantCd = Optional.of(parameter.get("plantCd")).orElse("").toString();
        String deptCd = Optional.of(parameter.get("deptCd")).orElse("").toString();
        String roomTp = Optional.of(parameter.get("kindCd")).orElse("").toString();
        String title = Optional.of(parameter.get("title")).orElse("").toString();

        List<ReferenceRoom> refRoomList = referenceRoomService.getReferenceRoomList(plantCd, deptCd, roomTp, title);

        return ResponseEntity.ok().body(refRoomList);
    }

    /**
     * 자료실 단건 조회
     * 
     * @param dataBoardNo
     * @return 자료실 상세페이지
     * @throws Exception
     */

    @GetMapping("/{dataBoardNo}")
    public ResponseEntity<ReferenceRoom> getReferenceRoom(@PathVariable("dataBoardNo") int dataBoardNo) throws Exception {
        ReferenceRoom refRoom = referenceRoomService.getReferenceRoom(dataBoardNo);
        return ResponseEntity.ok().body(refRoom);
    }

    /**
     * 자료실 등록
     * 
     * @param dataBoardNo
     * @return 등록 개수
     * @throws Exception
     */

    @PostMapping()
    public ResponseEntity<Integer> insertRefRoom(@RequestBody ReferenceRoom refRoom) throws Exception {
        return ResponseEntity.ok().body(this.referenceRoomService.insertRefRoom(refRoom));
    }

    @DeleteMapping("/{dataBoardNo}/{dataBoardGrpNo}")
    public ResponseEntity<Integer> deleteReferenceRoom(@PathVariable("dataBoardNo") int dataBoardNo, @PathVariable("dataBoardGrpNo") int dataBoardGrpNo) throws Exception {
        return ResponseEntity.ok().body(this.referenceRoomService.deleteReferenceRoom(dataBoardNo, dataBoardGrpNo));
    }

    @PutMapping
    public ResponseEntity<Integer> updateRefRoom(@RequestBody ReferenceRoom refRoom) throws Exception {
        int updateCount = this.referenceRoomService.updateRefRoom(refRoom);
        return ResponseEntity.ok().body(updateCount);
    }

    @PostMapping("/plants")
    public ResponseEntity<Integer> insertPlantTypes(@RequestBody ReferenceRoom refRoom) throws Exception {
        int deleteCount = 0;

        return ResponseEntity.ok().body(deleteCount);
    }

    @GetMapping("/revision")
    public ResponseEntity<List<ReferenceRoom>> getRevision(@RequestParam HashMap<String, Object> parameter) throws Exception {

        String dataBoardNo = Optional.of(parameter.get("dataBoardNo")).orElse("").toString();
        List<ReferenceRoom> refRoomList = this.referenceRoomService.getRevision(dataBoardNo);

        return ResponseEntity.ok().body(refRoomList);
    }

    @DeleteMapping("/revdelete")
    public ResponseEntity<Integer> revDeleteReferenceRoom(@RequestBody List<ReferenceRoom> referenceRoom) throws Exception {
        return ResponseEntity.ok().body(this.referenceRoomService.revDeleteReferenceRoom(referenceRoom));
    }

}
