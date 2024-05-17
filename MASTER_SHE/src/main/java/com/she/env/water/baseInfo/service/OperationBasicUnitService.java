package com.she.env.water.baseInfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.water.baseInfo.mapper.OperationBasicUnitMapper;
import com.she.env.water.model.OperationCleanFacDept;
import com.she.env.water.model.WasteBasicUnit;

@Service
public class OperationBasicUnitService {

    @Autowired
    private OperationBasicUnitMapper basicUnitMapper;

    /**
     * 운영일지 설정관리 전체 조회
     * 
     * @param plantCd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<WasteBasicUnit> getEwtrCleans(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("ewtrCleanFacNm") String ewtrCleanFacNm) throws Exception {
        return basicUnitMapper.getEwtrCleans(plantCd, deptCd, ewtrCleanFacNm);
    }

    /**
     * 운영일지 설정관리 작성부서 조회
     * 
     * @param ewtrCleanFacNo
     * @return
     * @throws Exception
     */
    public List<OperationCleanFacDept> getDeptList(@Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception {
        return basicUnitMapper.getDeptList(ewtrCleanFacNo);
    }

    /**
     * 운영일지 설정관리 작성부서 등록
     * 
     * @param operationCleanFacDept
     * @return
     * @throws Exception
     */
    public int insertOplogBaseDept(List<OperationCleanFacDept> operationCleanFacDept) throws Exception {
        int result = 0;

        if (operationCleanFacDept.size() > 0) {
            basicUnitMapper.deleteOplogBaseDept(operationCleanFacDept.get(0).getEwtrCleanFacNo(), null);
        }
        for (OperationCleanFacDept list : operationCleanFacDept) {

            result += basicUnitMapper.insertOplogBaseDept(list);
        }

        return result;
    }

}
