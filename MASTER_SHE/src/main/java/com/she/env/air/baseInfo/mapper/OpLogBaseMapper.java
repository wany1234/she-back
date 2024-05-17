package com.she.env.air.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.OplogBase;
import com.she.env.air.model.OplogBaseDept;
import com.she.env.air.model.OplogBaseMst;

/**
 * 
 * 대기 운영일지 기본정보
 *
 */

@Mapper
@Repository("com.she.env.air.baseInfo.mapper.OpLogBaseMapper")
public interface OpLogBaseMapper {

    /**
     * 운영일지 마스터 조회
     * 
     * @param plantCd
     * @param mgDeptCd
     * @return
     * @throws Exception
     */
    public OplogBaseMst getOplogBaseMst(@Param("plantCd") String plantCd, @Param("mgDeptCd") String mgDeptCd) throws Exception;

    /**
     * 운영일지 작성부서 조회
     * 
     * @param eairOplogBaseMstNo
     * @return
     * @throws Exception
     */
    public List<OplogBaseDept> getOplogBaseDept(@Param("eairOplogBaseMstNo") int eairOplogBaseMstNo) throws Exception;

    /**
     * 운영일지 기본정보 조회
     * 
     * @param plantCd
     * @param deptCd
     * @param mgDeptCd
     * @return
     * @throws Exception
     */
    public List<OplogBase> getOplogBases(@Param("plantCd") String plantCd, @Param("mgDeptCd") String mgDeptCd) throws Exception;

    /**
     * 운영일지 마스터 등록
     * 
     * @param oplogBaseMst
     * @return
     * @throws Exception
     */
    public int createOplogBaseMst(OplogBaseMst oplogBaseMst) throws Exception;

    /**
     * 운영일지 마스터 수정
     * 
     * @param oplogBaseMst
     * @return
     * @throws Exception
     */
    public int updateOPlogBaseMst(OplogBaseMst oplogBaseMst) throws Exception;

    /**
     * 운영일지-작성부서 등록
     * 
     * @param oplogBaseMst
     * @return
     * @throws Exception
     */
    public int createOplogBaseDept(OplogBaseDept oplogBaseDept) throws Exception;

    /**
     * 운영일지-작성부서 삭제
     * 
     * @param eairOplogBaseMstNo
     * @return
     * @throws Exception
     */
    public int delOplogBaseDept(@Param("eairOplogBaseMstNo") int eairOplogBaseMstNo) throws Exception;

    /**
     * 운영일지기본 정보 등록
     * 
     * @param oplogBase
     * @return
     * @throws Exception
     */
    public int createOplogBase(OplogBase oplogBase) throws Exception;

    /**
     * 운영일지기본정보-삭제
     * 
     * @param eairOplogBaseMstNo
     * @return
     */
    public int delOplogBase(@Param("eairOplogBaseMstNo") int eairOplogBaseMstNo) throws Exception;

}
