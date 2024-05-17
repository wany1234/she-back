package com.she.env.air.baseInfo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.air.baseInfo.mapper.OpLogBaseMapper;
import com.she.env.air.model.OplogBase;
import com.she.env.air.model.OplogBaseDept;
import com.she.env.air.model.OplogBaseMst;

/**
 *
 *
 *
 */
@Service("OpLogBaseService")
public class OpLogBaseService {

    @Autowired
    private OpLogBaseMapper opLogBaseMapper;

    /**
     * 운영일지작성구분 조회
     *
     * @param plantCd,
     *            mgDeptCd
     * @return
     * @throws Exception
     */
    public OplogBaseMst getOplogBaseMst(String plantCd, String mgDeptCd) throws Exception {

        OplogBaseMst oplogBaseMst = this.opLogBaseMapper.getOplogBaseMst(plantCd, mgDeptCd);

        int oplogBaseMstno = 0;
        if (oplogBaseMst != null) {
            oplogBaseMstno = oplogBaseMst.getEairOplogBaseMstNo();
        } else {
            oplogBaseMst = new OplogBaseMst();
        }
        oplogBaseMst.setOplogBaseDept(this.opLogBaseMapper.getOplogBaseDept(oplogBaseMstno));
        oplogBaseMst.setOplogBase(this.opLogBaseMapper.getOplogBases(plantCd, mgDeptCd));

        return oplogBaseMst;
    }

    /**
     * 운영일지 마스터 등록
     *
     * @param oplogBase
     * @return
     * @throws Exception
     */
    @Transactional
    public int createOplogBaseMst(OplogBaseMst oplogBaseMst) throws Exception {
        this.opLogBaseMapper.createOplogBaseMst(oplogBaseMst);

        // 운영일지-부서
        if (oplogBaseMst.getOplogBaseDept() != null && oplogBaseMst.getOplogBaseDept().size() > 0) {
            for (OplogBaseDept dept : oplogBaseMst.getOplogBaseDept()) {

                dept.setEairOplogBaseMstNo(oplogBaseMst.getEairOplogBaseMstNo());
                this.opLogBaseMapper.createOplogBaseDept(dept);

            }
        }
        // 운영일지 기본정보
        if (oplogBaseMst.getOplogBase() != null && oplogBaseMst.getOplogBase().size() > 0) {
            for (OplogBase base : oplogBaseMst.getOplogBase()) {
                if (base.getOplogDischYn().equals("Y") || base.getOplogPreventYn().equals("Y")) {
                    base.setEairOplogBaseMstNo(oplogBaseMst.getEairOplogBaseMstNo());
                    this.opLogBaseMapper.createOplogBase(base);
                }

            }
        }

        return oplogBaseMst.getEairOplogBaseMstNo();
    }

    /**
     * 운영일지 마스터 수정
     *
     * @param oplogBase
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateOPlogBaseMst(OplogBaseMst oplogBaseMst) throws Exception {
        this.opLogBaseMapper.updateOPlogBaseMst(oplogBaseMst);

        // 운영일지-부서
        this.opLogBaseMapper.delOplogBaseDept(oplogBaseMst.getEairOplogBaseMstNo());
        if (oplogBaseMst.getOplogBaseDept() != null && oplogBaseMst.getOplogBaseDept().size() > 0) {
            for (OplogBaseDept dept : oplogBaseMst.getOplogBaseDept()) {
                dept.setEairOplogBaseMstNo(oplogBaseMst.getEairOplogBaseMstNo());
                this.opLogBaseMapper.createOplogBaseDept(dept);

            }
        }
        // 운영일지 기본정보
        this.opLogBaseMapper.delOplogBase(oplogBaseMst.getEairOplogBaseMstNo());
        if (oplogBaseMst.getOplogBase() != null && oplogBaseMst.getOplogBase().size() > 0) {
            for (OplogBase base : oplogBaseMst.getOplogBase()) {
                if (base.getOplogDischYn().equals("Y") || base.getOplogPreventYn().equals("Y")) {
                    base.setEairOplogBaseMstNo(oplogBaseMst.getEairOplogBaseMstNo());
                    this.opLogBaseMapper.createOplogBase(base);
                }
            }
        }

        return oplogBaseMst.getEairOplogBaseMstNo();
    }
}
