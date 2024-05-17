package com.she.psm.buildingFacilitiesLayout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.FireProofs;

@Mapper
@Repository("com.she.psm.buildingFacilitiesLayout.mapper.FireProofsMapper")
public interface FireProofsMapper {
    /**
     * 내화구조명세조회
     * 
     * @param plantCd
     *            사업장
     * @param category
     *            구분
     * @param fireProofsNum
     *            지역번호
     * @param fireProofsNm
     *            지역명
     * @return 내화구조명세 목록
     * @throws Exception
     * @throws Exception
     */
    public List<FireProofs> getFireProofsLists(@Param("plantCd") String plantCd, @Param("category") String category,
            @Param("fireProofsNum") String fireProofsNum, @Param("fireProofsNm") String fireProofsNm) throws Exception;

    /**
     * 내화구조명세조회
     * 
     * @param fireProofsNo
     *            운전상태 No
     * @return 내화구조명세 상세정보
     * @throws Exception
     */
    public FireProofs getFireProofs(@Param("fireProofsNo") int fireProofsNo) throws Exception;

    /**
     * 내화구조명세 항목 생성
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 내화구조명세 항목 Key값
     * @throws Exception
     */
    public int createFireProofs(FireProofs fireProofs) throws Exception;

    /**
     * 내화구조명세 항목 수정
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 내화구조명세 항목 Key값
     * @throws Exception
     */
    public int updateFireProofs(FireProofs fireProofs) throws Exception;

    /**
     * 내화구조명세 항목 삭제
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteFireProofs(FireProofs fireProofs) throws Exception;
}
