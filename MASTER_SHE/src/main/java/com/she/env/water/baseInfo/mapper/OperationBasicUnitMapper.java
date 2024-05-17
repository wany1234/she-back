package com.she.env.water.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.water.model.OperationCleanFacDept;
import com.she.env.water.model.WasteBasicUnit;

@Mapper
@Repository("com.she.env.water.baseInfo.mapper.OperationBasicUnitMapper")
public interface OperationBasicUnitMapper {

    /**
     * 운영일지 설정관리 전체 조회
     * 
     * @param plantCd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<WasteBasicUnit> getEwtrCleans(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("ewtrCleanFacNm") String ewtrCleanFacNm) throws Exception;

    /**
     * 운영일지 설정관리 작성부서 조회
     * 
     * @param ewtrCleanFacNo
     * @return
     * @throws Exception
     */
    public List<OperationCleanFacDept> getDeptList(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    /**
     * 운영일지 설정관리 작성부서 삭제
     * 
     * @param ewtrCleanfacNo
     * @param dpetCd
     * @return
     * @throws Exception
     */
    public int deleteOplogBaseDept(@Param("ewtrCleanfacNo") int ewtrCleanfacNo, @Param("deptCd") String dpetCd) throws Exception;

    /**
     * 운영일지 설정관리 작성부서 등록
     * 
     * @param operationCleanFacDept
     * @return
     * @throws Exception
     */
    public int insertOplogBaseDept(OperationCleanFacDept operationCleanFacDept) throws Exception;
}
