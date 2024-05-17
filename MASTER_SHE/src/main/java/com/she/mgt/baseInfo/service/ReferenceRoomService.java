/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.mgt.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.mgt.baseInfo.mapper.ReferenceRoomMapper;
import com.she.mgt.model.ReferenceRoom;

@Service
public class ReferenceRoomService {
    @Autowired
    private ReferenceRoomMapper refRoomMapper;

    /**
     * 자료실 전체 조회
     * 
     * @param plantCd,
     *            deptCd, kindCd, title
     * @return 자료실 목록
     * @throws Exception
     */
    public List<ReferenceRoom> getReferenceRoomList(String plantCd, String deptCd, String kindCd, String title) throws Exception {
        return refRoomMapper.getReferenceRoomList(plantCd, deptCd, kindCd, title);
    }

    /**
     * 자료실 단건 조회
     * 
     * @param dataBoardNo
     * @return 자료실 상세페이지
     * @throws Exception
     */

    public ReferenceRoom getReferenceRoom(int dataBoardNo) throws Exception {
        ReferenceRoom refRoom = refRoomMapper.getReferenceRoom(dataBoardNo);
        refRoom.setPlantType(refRoomMapper.getPlantTypes(refRoom.getDataBoardNo()));

        return refRoom;
    }

    /**
     * 자료실 삭제
     * 
     * @param dataBoardNo
     * @return 삭제 개수
     * @throws Exception
     */

    public int deleteReferenceRoom(int dataBoardNo, int dataBoardGrpNo) throws Exception {

        return this.refRoomMapper.deleteReferenceRoom(dataBoardNo, dataBoardGrpNo);

    }

    /**
     * 자료실 등록
     * 
     * @param dataBoardNo
     * @return 등록 개수
     * @throws Exception
     */
    @Transactional
    public int insertRefRoom(ReferenceRoom refRoom) throws Exception {
        int insertCnt = this.refRoomMapper.insertRefRoom(refRoom);
        List<String> plantCds = refRoom.getPlantType();
        for (int i = 0; i < plantCds.size(); i++) {
            this.refRoomMapper.insertPlantTypes(refRoom.getDataBoardNo(), plantCds.get(i));
        }

        return refRoom.getDataBoardNo();
    }

    /**
     * 자료실 수정
     * 
     * @param dataBoardNo
     * @return 수정 개수
     * @throws Exception
     */

    @Transactional
    public int updateRefRoom(ReferenceRoom refRoom) throws Exception {
        this.refRoomMapper.deletePlantTypes(refRoom.getDataBoardNo());
        int updateCnt = this.refRoomMapper.updateRefRoom(refRoom);
        List<String> plantCds = refRoom.getPlantType();
        for (int i = 0; i < plantCds.size(); i++) {
            this.refRoomMapper.insertPlantTypes(refRoom.getDataBoardNo(), plantCds.get(i));
        }

        return updateCnt;
    }

    /**
     * 사업장 범위 등록
     * 
     * @param dataBoardNo,
     *            plantCd
     * @return 등록 개수
     * @throws Exception
     */

    public int insertPlantTypes(int dataBoardNo, String plantCd) throws Exception {
        return this.refRoomMapper.insertPlantTypes(dataBoardNo, plantCd);
    }

    /**
     * 사업장 범위 삭제
     * 
     * @param dataBoardNo,
     *            plantCd
     * @return 삭제 개수
     * @throws Exception
     */

    public int deletePlantTypes(int dataBoardNo) throws Exception {
        return this.refRoomMapper.deletePlantTypes(dataBoardNo);
    }

    public int deleteRefRoom(int dataBoardNo) throws Exception {
        int delCnt = this.refRoomMapper.deleteRefRoom(dataBoardNo);
        return delCnt;
    }

    public List<ReferenceRoom> getRevision(String dataBoardNo) throws Exception {
        return this.refRoomMapper.getRevision(dataBoardNo);
    }

    @Transactional
    public int revDeleteReferenceRoom(List<ReferenceRoom> referenceRoom) throws Exception {
        int count = 0;
        for (ReferenceRoom referenceRoomd : referenceRoom) {
            count += this.refRoomMapper.revDeleteReferenceRoom(referenceRoomd);
        }

        return count;
    }

}
