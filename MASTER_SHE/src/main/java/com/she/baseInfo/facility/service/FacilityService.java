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
package com.she.baseInfo.facility.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.baseInfo.facility.mapper.FacilityMapper;
import com.she.baseInfo.model.FacilityMst;
import com.she.baseInfo.model.FacilityTypeItem;
import com.she.baseInfo.model.FacilityTypeItemVal;
import com.she.chm.chem.service.ChemprodService;
import com.she.chm.model.Chemprod;
import com.she.common.model.DefaultParam;
import com.she.manage.model.User;
import com.she.manage.service.UserService;
import com.she.psm.processFacility.mapper.FacilityChemprodMapper;
import com.she.utils.model.MailVo;

/**
 * 안전설비마스터 기능정의
 */
@Service
public class FacilityService {

    @Autowired
    private FacilityMapper facilityMapper;

    @Autowired
    private FacilityChemprodMapper facilityChemprodMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ChemprodService chemprodService;

    /**
     * 안전설비마스터 조회
     *
     * @param safFacilityTypeCd
     *            설비유형코드
     * @param processCd
     *            공정번호
     * @param facilityNm
     *            설비명
     * @param deptCd
     *            설비관리부서
     * @param safFacilityCd
     *            설비코드
     * @param facilityMgrNum
     *            관리번호
     * @param installLocate
     *            설치위치
     * @param plantCd
     *            사업장
     * @return 안전설비마스터 목록
     * @throws Exception
     */
    public List<FacilityMst> getFacilityMsts(String safFacilityTypeCd, String facilityNm, String deptCd,
            String processCd, String safFacilityCd, String installLocate, String plantCd, String disuseYn,
            String safCheckTypeCd, String safLsnYn, String safChkYn, DefaultParam defaultParam) throws Exception {
        return facilityMapper.getFacilityMsts(safFacilityTypeCd, facilityNm, deptCd, processCd, safFacilityCd,
                installLocate, plantCd, disuseYn, safCheckTypeCd, safLsnYn, safChkYn, defaultParam);
    }

    /**
     * 안전설비마스터 상세조회
     *
     * @param safFacilityCd
     *            안전설비코드
     * @return 안전설비마스터
     * @throws Exception
     */
    public FacilityMst getFacilityMst(String safFacilityCd, DefaultParam defaultParam) throws Exception {
        return this.facilityMapper.getFacilityMst(safFacilityCd, defaultParam);
    }

    /**
     * 안전설비유형 관리항목 조회
     *
     * @param safFacilityCd
     *            설비코드
     * @param safFacilityTypeCd
     *            설비유형 코드
     * @return 안전설비유형 관리항목 목록
     * @throws Exception
     */
    public List<FacilityTypeItem> getFacilityTypeItems(String safFacilityCd, String safFacilityTypeCd)
            throws Exception {
        List<FacilityTypeItem> facilityTypeItems = facilityMapper.getFacilityTypeItems(safFacilityCd,
                safFacilityTypeCd);
        // 저장된 데이터 값이 있는 경우
        if (facilityTypeItems != null && facilityTypeItems.size() > 0) {
            // 저장된 데이터값에 설비유형의 값이 현재 설비의 설비유형값과 동일한지 체크
            for (FacilityTypeItem facilityTypeItem : facilityTypeItems) {
                // 동일한 값이 들어 있다면 저장된 데이터 값을 그대로 가져가 사용
                if (safFacilityTypeCd.equals(facilityTypeItem.getSafFacilityTypeCd())) {
                    return facilityTypeItems;
                }
            }
            // 동일한 값이 없어 반환 되지 않은 경우에는 현재 설비의 설비유형값에 따른 상세항목들을 저장된 값에 붙인다.
            facilityTypeItems.addAll(facilityMapper.getNewFacilityTypeItems(safFacilityTypeCd));
        } else {
            // 저장된 값이 없는 경우에는 현재 설비의 설비유형값에 따른 상세항목들을 가지고 온다. (사용하는 것들만)
            facilityTypeItems = facilityMapper.getNewFacilityTypeItems(safFacilityTypeCd);
        }
        return facilityTypeItems;
    }

    /**
     * 안전설비마스터 중복검사
     *
     * @param safFacilityCd
     * @return 중복 행 수
     * @throws Exception
     */
    public int countFacilityMst(String safFacilityCd) throws Exception {
        return this.facilityMapper.countFacilityMst(safFacilityCd);
    }

    /**
     * 안전설비마스터 신규등록
     *
     * @param facilityMst
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createFacilityMst(FacilityMst facilityMst, DefaultParam defaultParam) throws Exception {

        int count = 0;
        if (this.facilityMapper.getFacilityMst(facilityMst.getSafFacilityCd(), defaultParam) == null) {
            count += this.facilityMapper.createFacilityMst(facilityMst);

            // 안전설비 관리항목값 삭제 및 신규등록
            this.createFacilityTypeItemVal(facilityMst);

            this.saveChemprod(facilityMst, defaultParam);
        }

        return count;
    }

    /**
     * 안전설비마스터 수정
     *
     * @param facilityMst
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public String updateFacilityMst(FacilityMst facilityMst, DefaultParam defaultParam) throws Exception {
        this.facilityMapper.updateFacilityMst(facilityMst);

        // 안전설비 관리항목값 삭제 및 신규등록
        this.createFacilityTypeItemVal(facilityMst);

        this.saveChemprod(facilityMst, defaultParam);

        return facilityMst.getSafFacilityCd();
    }

    /**
     * 안전설비 관리항목값 신규등록
     *
     * @param facilityMst
     *            안전설비마스터
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createFacilityTypeItemVal(FacilityMst facilityMst) throws Exception {
        int count = 0;

        if (facilityMst.getFacilityTypeItemVals() != null) {
            // 안전설비 관리항목값 삭제
            this.facilityMapper.deleteFacilityTypeItemVal(facilityMst.getSafFacilityCd(), 0);

            if (facilityMst.getFacilityTypeItemVals().size() > 0) {
                // 안전설비 관리항목값 신규등록
                for (FacilityTypeItemVal facilityTypeItemVal : facilityMst.getFacilityTypeItemVals()) {
                    facilityTypeItemVal.setSafFacilityCd(facilityMst.getSafFacilityCd());
                    count += this.facilityMapper.createFacilityTypeItemVal(facilityTypeItemVal);
                }
            }
        }

        return count;
    }

    /**
     * 유형별 설비현황 조회
     *
     * @param plantCd
     *            사업장
     * @param processCd
     *            공정코드
     * @param deptCd
     *            관리부서코드
     * @return 유형별 설비현황
     * @throws Exception
     */
    public List<FacilityMst> getFacilityMstStatus(String plantCd, String deptCd, String processCd, boolean specHealth, DefaultParam defaultParam)
            throws Exception {
        return facilityMapper.getFacilityMstStatus(plantCd, deptCd, processCd, specHealth, defaultParam);
    }

    public int saveChemprod(FacilityMst facilityMst, DefaultParam defaultParam) throws Exception {
        int resultNo = 0;
        // 취급물질 등록
        if (facilityMst.getChemprods() != null) {
            resultNo += this.facilityChemprodMapper.deleteFacilityChemprod(facilityMst.getSafFacilityCd(), 0, 0);
            if (facilityMst.isTubeCheck()) {
                resultNo += this.facilityChemprodMapper.deleteFacilityChemprod(facilityMst.getSafFacilityCd(), 0, 1);
            }
            if (facilityMst.getChemprods().size() > 0) {
                for (Chemprod chemprod : facilityMst.getChemprods()) {
                    chemprod.setSafFacilityCd(facilityMst.getSafFacilityCd());
                    resultNo += this.facilityChemprodMapper.createFacilityChemprod(chemprod);
                }
            }
        }

        /**
         * 메일 (저장시 마다) ㅇ울산 : 김동호G4, 공성윤G3 ㅇ안강 : 신윤석G4, 이영진G4 ㅇ부산 : 신성용G3, 정한서G4
         */
        List<User> users = userService.getChmMaliReceiveUserInfo(facilityMst.getPlantCd());

        // String[] recipients = { "dhkim@yullin.com" };
        String[] recipients = new String[users.size()];
        if (users != null && users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                recipients[i] = users.get(i).getEmail();
            }

            FacilityMst saveFacilityMst = facilityMapper.getFacilityMst(facilityMst.getSafFacilityCd(), defaultParam);

            if (saveFacilityMst != null) {
                List<String> changeChemProds = facilityMst.getChangeChemProds();
                if (changeChemProds != null && changeChemProds.size() > 0) {
                    for (String chemProdNo : changeChemProds) {
                        Chemprod chemprod = chemprodService.getChemprod(Integer.parseInt(chemProdNo), new DefaultParam("kr"));
                        if (chemprod != null) {
                            MailVo mail = new MailVo();
                            mail.setTitle("[SHE시스템] 설비-자재 매칭 정보 변경안내");
                            mail.setRecipientsEmailAddress(recipients);

                            String contents = "";
                            contents += "[SHE시스템] 설비-자재 매칭 정보 변경안내<br/><br/><br/>";
                            contents += "안전보건환경 시스템에서 알려 드립니다.<br/><br/>";
                            contents += saveFacilityMst.getDeptNm() + " " + saveFacilityMst.getProcessNm() + " "
                                    + saveFacilityMst.getFacilityNm() + "에서 취급하는 자재가 변경되었습니다.<br/><br/>";
                            contents += "ㅇ변경내용 : " + chemprod.getChemProdNmKr() + " " + chemprod.getSapMatCd()
                                    + "<br/><br/>";
                            contents += "세부내용은 안전보건환경 시스템에서 확인하실 수 있습니다";

                            mail.setContents(contents);
                            /**
                             * 2020-02-07 메일 발송 로직 주석 처리 풍산의 메일 보내는 PC에 접속 하지 못할
                             * 뿐더러 접속이 이루어져서도 안됨 위의 사항이 있기 때문에 로직 주석처리하여 사전차단하고자
                             * 함
                             */
                            // SendMailUtil.sendMail(mail);
                        }
                    }
                }
            }
        }

        return resultNo;
    }

}
