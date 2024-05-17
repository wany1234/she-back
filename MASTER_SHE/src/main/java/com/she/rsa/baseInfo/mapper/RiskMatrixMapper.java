package com.she.rsa.baseInfo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.she.rsa.model.RiskMatrix;

@Mapper
@Repository("com.she.rsa.baseInfo.mapper.RiskMatrixMapper")
public interface RiskMatrixMapper {

    /**
     * 위험 Matrix 신규등록
     * 
     * @param riskMatrix
     *            위험 Matrix
     * @return 등록 행 수
     * @throws Exception
     */
    public int createRiskMatrix(RiskMatrix riskMatrix) throws Exception;

    /**
     * 위험 Matrix 삭제
     * 
     * @param assessTypeNo
     *            위험성평가 Matrix 번호
     * @return 수정 행 수
     * @throws Exception
     */
    public int deleteRiskMatrix(int assessTypeNo) throws Exception;

}
