package com.she.mgt.perRptData.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.MgtPerRptData;
import com.she.mgt.perRptData.mapper.MgtPerRptDataMapper;

@Service
public class MgtPerRptDataService {
    @Autowired
    private MgtPerRptDataMapper mgtPerRptDataMapper;

    /**
     * 중처법 정기보고자료 목록조회
     * 
     * @param startYear
     *            시작년도
     * @param endYear
     *            종료년도
     * @param startDt
     *            보고시작일
     * @param endDt
     *            보고끝
     * @param deptCd
     *            주관부서
     * @param halfTypeCd
     *            구분 코드
     * @param rptNm
     *            보고명
     * @param procStepCd
     *            단계
     * @param stepCd
     *            상태(작성중/확정)
     * @return 중처법 정기보고자료 관리 목록
     * @throws Exception
     */
    public List<MgtPerRptData> getMgtPerRptDatas(String startYear, String endYear, String startDt, String endDt, String deptCd, String halfTypeCd, String rptNm, String procStepCd, String stepCd, DefaultParam defaultParam) throws Exception {
        return mgtPerRptDataMapper.getMgtPerRptDatas(startYear, endYear, startDt, endDt, deptCd, halfTypeCd, rptNm, procStepCd, stepCd, defaultParam);
    }

    /**
     * 중처법 정기보고자료 관리 상세 조회
     * 
     * @param perRptDataNo
     *            중처법 정기보고자료 관리 번호
     * @return 예산집행 상세
     * @throws Exception
     */
    public MgtPerRptData getMgtPerRptData(int perRptDataNo, DefaultParam defaultParam) throws Exception {
        MgtPerRptData mgtPerRptData = mgtPerRptDataMapper.getMgtPerRptData(perRptDataNo, defaultParam);
        return mgtPerRptData;
    }

    /**
     * 중처법 정기보고자료 신규등록
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료 관리
     * @return 결과
     * @throws Exception
     */
    public MgtPerRptData insertMgtPerRptData(MgtPerRptData mgtPerRptData) throws Exception {
        mgtPerRptDataMapper.insertMgtPerRptData(mgtPerRptData);
        return mgtPerRptData;
    }

    /**
     * 중처법 정기보고자료 수정
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료 관리
     * @return 결과
     * @throws Exception
     */
    public MgtPerRptData updateMgtPerRptData(MgtPerRptData mgtPerRptData) throws Exception {
        mgtPerRptDataMapper.updateMgtPerRptData(mgtPerRptData);
        return mgtPerRptData;
    }

    /**
     * 중처법 정기보고자료 확정
     * 
     * @param mgtPerRptData
     *            중처법 정기보고자료 관리
     * @return 결과
     * @throws Exception
     */
    public MgtPerRptData compleMgtPerRptData(MgtPerRptData mgtPerRptData) throws Exception {
        mgtPerRptDataMapper.compleMgtPerRptData(mgtPerRptData);
        return mgtPerRptData;
    }

    /**
     * 중처법 정기보고자료 삭제
     * 
     * @param perRptDataNo
     *            중처법 정기보고자료 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteMtgPerRptData(int perRptDataNo) throws Exception {
        return mgtPerRptDataMapper.deleteMgtPerRptData(perRptDataNo);
    }
}
