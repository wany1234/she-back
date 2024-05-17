package com.she.mgt.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.model.ReferenceRoom;

@Mapper
@Repository("com.she.mgt.baseInfo.mapper.ReferenceRoomMapper")
public interface ReferenceRoomMapper {

    /**
     * 자료실 전체 조회
     * 
     * @param plantCd,
     *            deptCd, kindCd, title
     * @return 자료실 목록
     * @throws Exception
     */

    public List<ReferenceRoom> getReferenceRoomList(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("kindCd") String roomTp, @Param("title") String title) throws Exception;

    /**
     * 자료실 단건 조회
     * 
     * @param dataBoardNo
     * @return 자료실 상세페이지
     * @throws Exception
     */

    public ReferenceRoom getReferenceRoom(@Param("dataBoardNo") int dataBoardNo) throws Exception;

    /**
     * 자료실 삭제
     * 
     * @param dataBoardNo
     * @return 삭제 개수
     * @throws Exception
     */

    public int deleteRefRoom(@Param("dataBoardNo") int dataBoardNo) throws Exception;

    /**
     * 자료실 등록
     * 
     * @param dataBoardNo
     * @return 등록 개수
     * @throws Exception
     */

    public int insertRefRoom(ReferenceRoom refRoom) throws Exception;

    /**
     * 사업장 범위 등록
     * 
     * @param dataBoardNo,
     *            plantCd
     * @return 등록 개수
     * @throws Exception
     */

    public int insertPlantTypes(@Param("dataBoardNo") int dataBoardNo, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 사업장 범위 삭제
     * 
     * @param dataBoardNo
     * @return 삭제 개수
     * @throws Exception
     */

    public int deletePlantTypes(@Param("dataBoardNo") int dataBoardNo) throws Exception;

    /**
     * 사업장 범위 조회
     * 
     * @param dataBoardNo
     * @return 사업장 범위
     * @throws Exception
     */

    public List<String> getPlantTypes(@Param("dataBoardNo") int dataBoardNo) throws Exception;

    public int updateRefRoom(ReferenceRoom refRoom) throws Exception;

    public List<ReferenceRoom> getRevision(@Param("dataBoardNo") String dataBoardNo) throws Exception;

    public int deleteReferenceRoom(@Param("dataBoardNo") int dataBoardNo, @Param("dataBoardGrpNo") int dataBoardGrpNo) throws Exception;

    public int revDeleteReferenceRoom(ReferenceRoom referenceRoom) throws Exception;

}
