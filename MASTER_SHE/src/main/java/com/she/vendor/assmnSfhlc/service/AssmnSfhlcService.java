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
package com.she.vendor.assmnSfhlc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.utils.ConstVal;
import com.she.vendor.assmnSfhlc.mapper.AssmnSfhlcMapper;
import com.she.vendor.model.AssmnSfhlc;
import com.she.vendor.model.AssmnSfhlcItemList;
import com.she.vendor.model.AssmnSfhlcVendorList;

@Service
public class AssmnSfhlcService {
    @Autowired
    private AssmnSfhlcMapper assmnSfhlcMapper;

    /**
     * 평가 및 안전보건 계획 목록 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 목록 조회
     * @throws Exception
     */
    public List<AssmnSfhlc> getPlanmgmtLists(String plantCd, String year, String evalClsCd, String evalTitle, String mainDeptCd, String deptSubYn, String stateCd, String procStepCd, String yearChk, String deptCd, String deptSubYn2, DefaultParam defaultParam) throws Exception {
        return assmnSfhlcMapper.getPlanmgmtLists(plantCd, year, evalClsCd, evalTitle, mainDeptCd, deptSubYn, stateCd, procStepCd, yearChk, deptCd, deptSubYn2, defaultParam);
    }

    /**
     * 평가 및 안전보건 계획 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 조회
     * @throws Exception
     */
    public AssmnSfhlc getPlanmgmtInfo(int vendorEvalPlanNo, DefaultParam defaultParam) throws Exception {
        AssmnSfhlc assmnSfhlc = assmnSfhlcMapper.getPlanmgmtInfo(vendorEvalPlanNo, defaultParam);
        List<AssmnSfhlcVendorList> assmnSfhlcVendorList = assmnSfhlcMapper.getAssmnSfhlVendorList(vendorEvalPlanNo);
        if (assmnSfhlcVendorList != null && assmnSfhlcVendorList.size() > 0) {
            for (int i = 0; i < assmnSfhlcVendorList.size(); i++) {
                HashMap<String, Object> actDate = new HashMap<String, Object>();
                actDate.put("deptCd", assmnSfhlcVendorList.get(i).getDeptCd());
                actDate.put("deptNm", assmnSfhlcVendorList.get(i).getDeptNm());
                assmnSfhlcVendorList.get(i).setActDept(actDate);
            }
        }
        assmnSfhlc.setAssmnSfhlcVendorList(assmnSfhlcVendorList);
        assmnSfhlc.setAssmnSfhlcItemList(assmnSfhlcMapper.getPlanEvalItemList(vendorEvalPlanNo, 0, null));

        return assmnSfhlc;
    }

    /**
     * 평가 및 안전보건 계획 > 평가항목(기본조회)
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 평가항목(기본조회)
     * @throws Exception
     */
    public List<AssmnSfhlcItemList> searchAssmnSfhlcItemList(int vendorEvalPlanNo, String plantCd, String evalClsCd) throws Exception {
        return assmnSfhlcMapper.searchAssmnSfhlcItemList(vendorEvalPlanNo, plantCd, evalClsCd);
    }

    /**
     * 평가 및 안전보건 계획 등록
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 등록
     * @throws Exception
     */
    @Transactional
    public Integer createAssmnSfhlc(AssmnSfhlc assmnSfhlc) throws Exception {
        assmnSfhlc.setStepCd("BAPSB");
        assmnSfhlc.setStateCd("STATE2");
        assmnSfhlcMapper.createAssmnSfhlc(assmnSfhlc);
        if (assmnSfhlc.getAssmnSfhlcVendorList() != null && assmnSfhlc.getAssmnSfhlcVendorList().size() > 0) {
            for (int i = 0; i < assmnSfhlc.getAssmnSfhlcVendorList().size(); i++) {
                // 부서 그리드 수정때문에 ..따로 받아서 담아야함
                assmnSfhlc.getAssmnSfhlcVendorList().get(i).setDeptCd(assmnSfhlc.getAssmnSfhlcVendorList().get(i).getActDept().get("deptCd").toString());
                assmnSfhlc.getAssmnSfhlcVendorList().get(i).setVendorEvalPlanNo(assmnSfhlc.getVendorEvalPlanNo());

                // 계획 등록시 평가완료일등록되는거 주석처리
                // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // String todate = sdf.format(new Date());
                // assmnSfhlc.getAssmnSfhlcVendorList().get(i).setEvalFinishDt(todate);
                assmnSfhlcMapper.createAssmnSfhlcVendor(assmnSfhlc.getAssmnSfhlcVendorList().get(i));
                // 평가 항목
                if (assmnSfhlc.getAssmnSfhlcItemList() != null && assmnSfhlc.getAssmnSfhlcItemList().size() > 0) {
                    for (int j = 0; j < assmnSfhlc.getAssmnSfhlcItemList().size(); j++) {
                        assmnSfhlc.getAssmnSfhlcItemList().get(j).setVendorEvalPlanNo(assmnSfhlc.getVendorEvalPlanNo());
                        assmnSfhlc.getAssmnSfhlcItemList().get(j).setEvalPlanVendorNo(assmnSfhlc.getAssmnSfhlcVendorList().get(i).getEvalPlanVendorNo());
                        assmnSfhlcMapper.createAssmnSfhlcItemList(assmnSfhlc.getAssmnSfhlcItemList().get(j));
                    }
                }
            }
        }

        return assmnSfhlc.getVendorEvalPlanNo();
    }

    /**
     * 평가 및 안전보건 계획 수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 수정
     * @throws Exception
     */
    public Integer updateAssmnSfhlc(AssmnSfhlc assmnSfhlc) throws Exception {
        assmnSfhlcMapper.updateAssmnSfhlc(assmnSfhlc);
        if (assmnSfhlc.getAssmnSfhlcVendorList() != null && assmnSfhlc.getAssmnSfhlcVendorList().size() > 0) {
            assmnSfhlcMapper.deleteAssmnSfhlcVendor(assmnSfhlc.getVendorEvalPlanNo());
            assmnSfhlcMapper.deletePlanEvalItemList(assmnSfhlc.getVendorEvalPlanNo());
            for (int i = 0; i < assmnSfhlc.getAssmnSfhlcVendorList().size(); i++) {
                // 부서 그리드 수정때문에 ..따로 받아서 담아야함
                assmnSfhlc.getAssmnSfhlcVendorList().get(i).setDeptCd(assmnSfhlc.getAssmnSfhlcVendorList().get(i).getActDept().get("deptCd").toString());
                assmnSfhlc.getAssmnSfhlcVendorList().get(i).setVendorEvalPlanNo(assmnSfhlc.getVendorEvalPlanNo());
                assmnSfhlcMapper.createAssmnSfhlcVendor(assmnSfhlc.getAssmnSfhlcVendorList().get(i));
                // 평가 항목
                if (assmnSfhlc.getAssmnSfhlcItemList() != null && assmnSfhlc.getAssmnSfhlcItemList().size() > 0) {
                    for (int j = 0; j < assmnSfhlc.getAssmnSfhlcItemList().size(); j++) {
                        assmnSfhlc.getAssmnSfhlcItemList().get(j).setVendorEvalPlanNo(assmnSfhlc.getVendorEvalPlanNo());
                        assmnSfhlc.getAssmnSfhlcItemList().get(j).setEvalPlanVendorNo(assmnSfhlc.getAssmnSfhlcVendorList().get(i).getEvalPlanVendorNo());
                        assmnSfhlcMapper.createAssmnSfhlcItemList(assmnSfhlc.getAssmnSfhlcItemList().get(j));
                    }
                }
            }
        }
        return assmnSfhlc.getVendorEvalPlanNo();
    }

    /**
     * 평가 및 안전보건 계획 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 삭제
     * @throws Exception
     */
    public Integer deleteAssmnSfhlc(int vendorEvalPlanNo) throws Exception {
        return assmnSfhlcMapper.deleteAssmnSfhlc(vendorEvalPlanNo);
    }

    /**
     * 평가 및 안전보건 계획 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가 및 안전보건 계획 삭제 가능 확인
     * @throws Exception
     */
    public int getPlanmgmtStatus(int vendorEvalPlanNo) throws Exception {
        return assmnSfhlcMapper.getPlanmgmtStatus(vendorEvalPlanNo);
    }

    /**
     * 평가 및 안전보건 계획 대상협력업체 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 대상협력업체 삭제
     * @throws Exception
     */
    public int deleteAssmnSfhlcVendorEach(List<AssmnSfhlcVendorList> assmnSfhlcVendorList) throws Exception {
        int cnt = 0;
        if (assmnSfhlcVendorList != null && assmnSfhlcVendorList.size() > 0) {
            for (int i = 0; i < assmnSfhlcVendorList.size(); i++) {

                cnt += assmnSfhlcMapper.deleteAssmnSfhlcVendorEach(assmnSfhlcVendorList.get(i).getVendorEvalPlanNo(), assmnSfhlcVendorList.get(i).getEvalPlanVendorNo());
            }
        }
        return cnt;
    }

    /**
     * 평가 및 안전보건 계획 결재상태수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 결재상태수정
     * @throws Exception
     */
    @Transactional
    public int updateAppr(int vendorEvalPlanNo, String apprStepCd, int apprRqstNo) {
        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려
            resultNo += assmnSfhlcMapper.updateAppr(vendorEvalPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중
            resultNo += assmnSfhlcMapper.updateAppr(vendorEvalPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료
            resultNo += assmnSfhlcMapper.updateAppr(vendorEvalPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
        }

        return resultNo;
    }

    /**
     * 평가 및 안전보건 계획 대상협력업체 팝업
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 대상협력업체 팝업
     * @throws Exception
     */
    public List<AssmnSfhlcVendorList> getPlanmgmtVendorList(int vendorEvalPlanNo, String apprFlag, String procStepCd) throws Exception {
        return assmnSfhlcMapper.getPlanmgmtVendorList(vendorEvalPlanNo, apprFlag, procStepCd);
    }

    /**
     * 평가 및 안전보건 결과 목록
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 목록
     * @throws Exception
     */
    public List<AssmnSfhlcVendorList> getResultList(String plantCd, String evalClsCd, String startDt, String endDt, String evalTitle, String vendorNm, String procStepCd, String stateCd, String costRefYn, String deptSubYn, String deptCd, DefaultParam defaultParam) throws Exception {
        return assmnSfhlcMapper.getResultList(plantCd, evalClsCd, startDt, endDt, evalTitle, vendorNm, procStepCd, stateCd, costRefYn, deptSubYn, deptCd, defaultParam);
    }

    /**
     * 평가 및 안전보건 결과 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 조회
     * @throws Exception
     */
    public AssmnSfhlcVendorList getResultmgmtInfo(int evalPlanVendorNo, DefaultParam defaultParam) throws Exception {
        int allpnt = 0;
        AssmnSfhlcVendorList assmnSfhlcVendorList = assmnSfhlcMapper.getResultmgmtInfo(evalPlanVendorNo, defaultParam);
        List<AssmnSfhlcItemList> assmnSfhlcItemList = assmnSfhlcMapper.getPlanEvalItemList(0, evalPlanVendorNo, "result");
        if (assmnSfhlcItemList != null && assmnSfhlcItemList.size() > 0) {
            for (int i = 0; i < assmnSfhlcItemList.size(); i++) {
                allpnt += assmnSfhlcItemList.get(i).getSubconEvalItemPnt();
            }
        }
        assmnSfhlcVendorList.setEvalAllPnt(String.valueOf(allpnt));
        assmnSfhlcVendorList.setAssmnSfhlcItemList(assmnSfhlcItemList);
        return assmnSfhlcVendorList;
    }

    /**
     * 평가 및 안전보건 결과 등록
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 등록
     * @throws Exception
     */
    public Integer createAssmnSfhlcResult(AssmnSfhlcVendorList assmnSfhlcVendor) throws Exception {

        AssmnSfhlc assmnSfhlc = new AssmnSfhlc();
        if (assmnSfhlcVendor != null) {
            assmnSfhlc.setPlantCd(assmnSfhlcVendor.getPlantCd());
            // 대상년도정보없음 assmnSfhlc.setYear(assmnSfhlcVendor.getYear());
            assmnSfhlc.setEvalClsCd(assmnSfhlcVendor.getEvalClsCd());
            assmnSfhlc.setYear(assmnSfhlcVendor.getYear());
            assmnSfhlc.setEvalStartDt(assmnSfhlcVendor.getEvalStartDt());
            assmnSfhlc.setEvalEndDt(assmnSfhlcVendor.getEvalEndDt());
            assmnSfhlc.setMainDeptCd(assmnSfhlcVendor.getMainDeptCd());
            assmnSfhlc.setEvalTitle(assmnSfhlcVendor.getEvalTitle());
            assmnSfhlc.setEvalDesc(assmnSfhlcVendor.getEvalDesc());
            assmnSfhlc.setProcStepCd("RWRS1");
            assmnSfhlc.setStateCd("STATE4");
            assmnSfhlc.setStepCd("BAPSG");
            assmnSfhlc.setCreateUserId(assmnSfhlcVendor.getUpdateUserId());
            assmnSfhlc.setCreateDeptCd(assmnSfhlcVendor.getUpdateDeptCd());
            assmnSfhlc.setCreateDeptNm(assmnSfhlcVendor.getUpdateDeptNm());
            assmnSfhlc.setCreatePositionCd(assmnSfhlcVendor.getUpdatePositionCd());
            assmnSfhlc.setCreatePositionNm(assmnSfhlcVendor.getUpdatePositionNm());
            // 계획 등록
            assmnSfhlcMapper.createAssmnSfhlc(assmnSfhlc);
            // 대상협력업체 등록
            assmnSfhlcVendor.setVendorEvalPlanNo(assmnSfhlc.getVendorEvalPlanNo());
            assmnSfhlcMapper.createAssmnSfhlcVendor(assmnSfhlcVendor);
        }
        // 평가 항목
        if (assmnSfhlcVendor.getAssmnSfhlcItemList() != null && assmnSfhlcVendor.getAssmnSfhlcItemList().size() > 0) {
            assmnSfhlcMapper.deleteAssmnSfhlcItemList(assmnSfhlcVendor.getEvalPlanVendorNo());
            for (int i = 0; i < assmnSfhlcVendor.getAssmnSfhlcItemList().size(); i++) {

                assmnSfhlcVendor.getAssmnSfhlcItemList().get(i).setEvalPlanVendorNo(assmnSfhlcVendor.getEvalPlanVendorNo());
                assmnSfhlcVendor.getAssmnSfhlcItemList().get(i).setVendorEvalPlanNo(assmnSfhlc.getVendorEvalPlanNo());
                assmnSfhlcMapper.createAssmnSfhlcItemList(assmnSfhlcVendor.getAssmnSfhlcItemList().get(i));
            }
        }
        return assmnSfhlcVendor.getEvalPlanVendorNo();
    }

    /**
     * 평가 및 안전보건 결과 수정
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 수정
     * @throws Exception
     */
    public Integer updateAssmnSfhlcResult(AssmnSfhlcVendorList assmnSfhlcVendor) throws Exception {
        if (assmnSfhlcVendor != null) {
            assmnSfhlcMapper.updateAssmnSfhlcVendor(assmnSfhlcVendor);
        }

        if (assmnSfhlcVendor.getAssmnSfhlcItemList() != null && assmnSfhlcVendor.getAssmnSfhlcItemList().size() > 0) {
            // assmnSfhlcMapper.deleteAssmnSfhlcItemList(assmnSfhlcVendor.getEvalPlanVendorNo());
            for (int i = 0; i < assmnSfhlcVendor.getAssmnSfhlcItemList().size(); i++) {

                assmnSfhlcVendor.getAssmnSfhlcItemList().get(i).setEvalPlanVendorNo(assmnSfhlcVendor.getEvalPlanVendorNo());
                assmnSfhlcMapper.updateVendorEvalItemRslt(assmnSfhlcVendor.getAssmnSfhlcItemList().get(i));
            }
        }
        return assmnSfhlcVendor.getEvalPlanVendorNo();
    }

    /**
     * 평가 및 안전보건 결과 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 삭제
     * @throws Exception
     */
    public Integer deleteAssmnSfhlcResult(int vendorEvalPlanNo) throws Exception {
        return assmnSfhlcMapper.deleteAssmnSfhlc(vendorEvalPlanNo);
    }

    /**
     * 평가 및 안전보건 결과 결재상태수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 결재상태수정
     * @throws Exception
     */
    @Transactional
    public int updateResultAppr(int evalPlanVendorNo, String apprStepCd, int apprRqstNo) {
        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려
            resultNo += assmnSfhlcMapper.updateResultAppr(evalPlanVendorNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중
            resultNo += assmnSfhlcMapper.updateResultAppr(evalPlanVendorNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료
            resultNo += assmnSfhlcMapper.updateResultAppr(evalPlanVendorNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
        }

        return resultNo;
    }

    /**
     * 평가 및 안전보건 비용 현황 목록
     *
     * @param parameter
     * @return 평가 및 안전보건 비용 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAssmnSfhlcStatus(String plantCd, String year, String allSearchYn) throws Exception {
        List<HashMap<String, Object>> assmnSfhlcStatus = assmnSfhlcMapper.getAssmnSfhlcStatus(plantCd, year, allSearchYn);
        return assmnSfhlcStatus;
    }

    /**
     * 평가 및 안전보건 비용 현황 팝업
     *
     * @param parameter
     * @return 평가 및 안전보건 비용 현황 팝업
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAssmnSfhlcStatusPopupList(String plantCd, String year, String searchFlag, String monFlag) throws Exception {
        List<HashMap<String, Object>> assmnSfhlcStatus = new ArrayList<HashMap<String, Object>>();
        if (monFlag.length() == 1) {
            monFlag = "0" + monFlag;
        }
        if ("1".equals(searchFlag) || "2".equals(searchFlag) || "3".equals(searchFlag) || "9".equals(searchFlag)) {
            // 전월누적 협력업체(건수)
            assmnSfhlcStatus = assmnSfhlcMapper.getAssmnSfhlcResultStatusPopupList(plantCd, year, searchFlag, monFlag);
        } else {
            assmnSfhlcStatus = assmnSfhlcMapper.getAssmnSfhlcStatusPopupList(plantCd, year, searchFlag, monFlag);
        }

        return assmnSfhlcStatus;
    }

}
