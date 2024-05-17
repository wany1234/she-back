package com.she.safety.spe.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.manage.model.CodeMaster;
import com.she.safety.model.Spe;
import com.she.safety.model.SpeGiDtl;
import com.she.safety.model.SpeRqDtl;
import com.she.safety.model.SpeRqstGive;
import com.she.safety.spe.mapper.SpeMapper;
import com.she.utils.ConstVal;

@Service
public class SpeService {
    @Autowired
    private SpeMapper speMapper;

    /**
     * 보호구 조회
     *
     * @param plantCd
     *            사업장 코드
     * @param speKindCd
     *            보호구 종류
     * @param speNm
     *            보호구명
     * @return 보호구 목록
     * @throws Exception
     */
    public List<Spe> getSpes(String plantCd, String speKindCd, String speNm, String useYn) throws Exception {
        return speMapper.getSpes(plantCd, speKindCd, speNm, useYn);
    }

    /**
     * 보호구 상세조회
     *
     * @param safSpeNo
     *            보호구 번호
     * @return 보호구 상세
     * @throws Exception
     */
    public Spe getSpe(String safSpeNo) throws Exception {
        return speMapper.getSpe(safSpeNo);
    }

    /**
     * 보호구 신규등록
     *
     * @param spe
     * @return 보호구 번호
     * @throws Exception
     */
    @Transactional
    public int createSpe(Spe spe) throws Exception {
        if (spe == null) {
            return 0;
        } else {
            int result = speMapper.createSpe(spe);
            if (result <= 0) {
                return 0;
            } else {
                return spe.getSafSpeNo();
            }
        }
    }

    /**
     * 보호구 수정
     *
     * @param spe
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateSpe(Spe spe) throws Exception {
        if (spe == null) {
            return 0;
        } else {
            int result = speMapper.updateSpe(spe);
            if (result <= 0) {
                return 0;
            } else {
                return spe.getSafSpeNo();
            }
        }
    }

    /**
     * 보호구 신청 등록
     *
     * @param speRequest
     * @return 보호구 신청 번호
     * @throws Exception
     */
    @Transactional
    public int createSpeRqstGive(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            // 신규등록
            speRqstGive.setSpeStepCd(ConstVal.SAF_SPE_STEP_REQ_ING); // 신청작성중
            speRqstGive.setRqstYn("Y"); // 신청지급
            int result = speMapper.createSpeRqstGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeRqDtlList())) {
                    // 보호구 신청 상세 신규등록
                    for (SpeRqDtl speRqDtl : speRqstGive.getSpeRqDtlList()) {
                        speRqDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.createSpeRqDtl(speRqDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 신청 수정
     *
     * @param speRequest
     *            보호구 신청
     * @return 보호구 신청 번호
     * @throws Exception
     */
    @Transactional
    public int updateSpeRequest(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            int result = speMapper.updateSpeRqstGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeRqDtlList())) {
                    // 보호구 신청 상세 삭제
                    speMapper.deleteSpeRqDtl(speRqstGive.getSafSpeRqstGiveNo());

                    // 보호구 신청 상세 신규등록
                    for (SpeRqDtl speRqDtl : speRqstGive.getSpeRqDtlList()) {
                        speRqDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.createSpeRqDtl(speRqDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 신청 완료
     *
     * @param speRequest
     *            보호구 신청
     * @return 보호구 신청 번호
     * @throws Exception
     */
    @Transactional
    public int completeSpeRequest(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            speRqstGive.setSpeStepCd(ConstVal.SAF_SPE_STEP_GIV_ING); // 신청완료,지급처리중통일
            int result = speMapper.updateSpeRqstGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeRqDtlList())) {
                    // 보호구 신청 상세 삭제
                    speMapper.deleteSpeRqDtl(speRqstGive.getSafSpeRqstGiveNo());

                    // 보호구 신청 상세 신규등록
                    for (SpeRqDtl speRqDtl : speRqstGive.getSpeRqDtlList()) {
                        speRqDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.createSpeRqDtl(speRqDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 신청 삭제
     *
     * @param safSpeRqstNo
     *            보호구 신청 번호
     * @return 삭제 행수
     * @throws Exception
     */
    @Transactional
    public int deleteSpeRqstGive(int safSpeRqstGiveNo) throws Exception {
        if (safSpeRqstGiveNo > 0) {
            int count = 0;
            // 보호구 신청 상세 삭제
            count += speMapper.deleteSpeRqDtl(safSpeRqstGiveNo);
            // 보호구 지급 상세 삭제
            count += speMapper.deleteSpeGiDtl(safSpeRqstGiveNo);
            // 보호구 신청 삭제
            count += speMapper.deleteSpeRqstGive(safSpeRqstGiveNo);
            return count;
        } else {
            return 0;
        }
    }

    /**
     * 보호구 신청 조회
     *
     * @param plantCd
     * @param startYmd
     * @param endYmd
     * @param rqstDeptCd
     * @param rqstUserId
     * @param speStepCd
     * @return
     * @throws Exception
     */
    public List<SpeRqstGive> getSpeRqstGiveList(String plantCd, String startYmd, String endYmd, String rqstDeptCd, String rqstUserId, String speStepCd) throws Exception {
        return speMapper.getSpeRqstGiveList(plantCd, startYmd, endYmd, rqstDeptCd, rqstUserId, speStepCd);
    }

    /**
     * 보호구 신청 상세조회
     *
     * @param safSpeRqstGiveNo
     * @return 보호구 신청 상세
     * @throws Exception
     */
    public SpeRqstGive getSpeRqstGive(int safSpeRqstGiveNo) throws Exception {
        if (safSpeRqstGiveNo <= 0) {
            return null;
        } else {
            SpeRqstGive speRqstGive = speMapper.getSpeRqstGive(safSpeRqstGiveNo);
            if (speRqstGive != null) {
                // 보호구 신청 상세 목록 조회
                speRqstGive.setSpeRqDtlList(speMapper.getSpeRqDtlList(safSpeRqstGiveNo));
                // 보호구 지급 상세 목록 조회
                speRqstGive.setSpeGiDtlList(speMapper.getSpeGiDtlList(safSpeRqstGiveNo));
            }
            return speRqstGive;
        }
    }

    // 보호구 지급 목록
    public SpeGiDtl getPayList(int safSpeNo) throws Exception {
        return speMapper.getPayList(safSpeNo);
    }

    /**
     * 보호구 지급 등록
     *
     * @param speGive
     * @return
     * @throws Exception
     */
    @Transactional
    public int createSpeGive(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            // 보호구신청및지급 진행단계 수정
            speRqstGive.setSpeStepCd(ConstVal.SAF_SPE_STEP_GIV_ING); // 지급처리중
            int result = speMapper.updateSpeRqstGiveForGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                // 보호구 신청 지급비고 수정
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeRqDtlList())) {
                    for (SpeRqDtl speRqDtl : speRqstGive.getSpeRqDtlList()) {
                        speMapper.updateGiveSpeRemark(speRqDtl);
                    }
                }
                // 보호구 지급상세 등록
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeGiDtlList())) {
                    for (SpeGiDtl speGiDtl : speRqstGive.getSpeGiDtlList()) {
                        speGiDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.insertSpeGiDtl(speGiDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 지급 수정
     *
     * @param speRqstGive
     * @return 보호구 지급 번호
     * @throws Exception
     */
    @Transactional
    public int updateSpeGive(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            // 보호구신청및지급 진행단계 수정
            speRqstGive.setSpeStepCd(ConstVal.SAF_SPE_STEP_GIV_ING); // 지급처리중
            int result = speMapper.updateSpeRqstGiveForGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                // 보호구 신청 지급비고 수정
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeRqDtlList())) {
                    for (SpeRqDtl speRqDtl : speRqstGive.getSpeRqDtlList()) {
                        speMapper.updateGiveSpeRemark(speRqDtl);
                    }
                }
                // 보호구 지급상세 등록
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeGiDtlList())) {
                    // 기등록된 지급상세 내역 삭제
                    speMapper.deleteSpeGiDtl(speRqstGive.getSafSpeRqstGiveNo());
                    // 지급상세 등록
                    for (SpeGiDtl speGiDtl : speRqstGive.getSpeGiDtlList()) {
                        speGiDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.insertSpeGiDtl(speGiDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 지급 완료
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @Transactional
    public int completeSpeGive(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            // 보호구신청및지급 진행단계 수정
            speRqstGive.setSpeStepCd(ConstVal.SAF_SPE_STEP_GIV_CMPLT); // 지급완료
            int result = speMapper.updateSpeRqstGiveForGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                // 보호구 신청 지급비고 수정
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeRqDtlList())) {
                    for (SpeRqDtl speRqDtl : speRqstGive.getSpeRqDtlList()) {
                        speMapper.updateGiveSpeRemark(speRqDtl);
                    }
                }
                // 보호구 지급상세 등록
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeGiDtlList())) {
                    // 기등록된 지급상세 내역 삭제
                    speMapper.deleteSpeGiDtl(speRqstGive.getSafSpeRqstGiveNo());
                    // 지급상세 등록
                    for (SpeGiDtl speGiDtl : speRqstGive.getSpeGiDtlList()) {
                        speGiDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.insertSpeGiDtl(speGiDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 무신청 등록
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @Transactional
    public int createSpeNoRqstGive(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            // 보호구 무신청 등록
            speRqstGive.setRqstYn("N"); // 무신청지급
            speRqstGive.setSpeStepCd(ConstVal.SAF_SPE_STEP_GIV_ING); // 지급처리중
            int result = speMapper.createSpeNoRqstGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                // 보호구 지급상세 등록
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeGiDtlList())) {
                    // 지급상세 등록
                    for (SpeGiDtl speGiDtl : speRqstGive.getSpeGiDtlList()) {
                        speGiDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.insertSpeGiDtl(speGiDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 무신청 수정
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateSpeNoRqstGive(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            // 보호구 무신청 수정
            int result = speMapper.updateSpeNoRqstGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                // 보호구 지급상세 등록
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeGiDtlList())) {
                    // 기등록된 지급상세 내역 삭제
                    speMapper.deleteSpeGiDtl(speRqstGive.getSafSpeRqstGiveNo());
                    // 지급상세 등록
                    for (SpeGiDtl speGiDtl : speRqstGive.getSpeGiDtlList()) {
                        speGiDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.insertSpeGiDtl(speGiDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 무신청 완료
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @Transactional
    public int completeSpeNoRqstGive(SpeRqstGive speRqstGive) throws Exception {
        if (speRqstGive == null) {
            return 0;
        } else {
            // 보호구신청및지급 진행단계 수정
            speRqstGive.setSpeStepCd(ConstVal.SAF_SPE_STEP_GIV_CMPLT); // 지급완료
            int result = speMapper.updateSpeRqstGiveForGive(speRqstGive);
            if (result <= 0) {
                return 0;
            } else {
                // 보호구 지급상세 등록
                if (CollectionUtils.isNotEmpty(speRqstGive.getSpeGiDtlList())) {
                    // 기등록된 지급상세 내역 삭제
                    speMapper.deleteSpeGiDtl(speRqstGive.getSafSpeRqstGiveNo());
                    // 지급상세 등록
                    for (SpeGiDtl speGiDtl : speRqstGive.getSpeGiDtlList()) {
                        speGiDtl.setSafSpeRqstGiveNo(speRqstGive.getSafSpeRqstGiveNo());
                        speMapper.insertSpeGiDtl(speGiDtl);
                    }
                }
                return speRqstGive.getSafSpeRqstGiveNo();
            }
        }
    }

    /**
     * 보호구 무신청 삭제
     *
     * @param safSpeRqstGiveNo
     * @return
     * @throws Exception
     */
    @Transactional
    public int deleteSpeNoRqstGive(int safSpeRqstGiveNo) throws Exception {
        if (safSpeRqstGiveNo <= 0) {
            return 0;
        } else {
            int count = 0;
            // 보호구 지급상세 삭제
            count += speMapper.deleteSpeGiDtl(safSpeRqstGiveNo);
            // 보호구 신청 삭제
            count += speMapper.deleteSpeRqstGive(safSpeRqstGiveNo);
            return count;
        }
    }

    /**
     * 보호구 지급현황 목록 조회
     *
     * @param plantCd
     * @param speKindCd
     * @param speNm
     * @param rqstDeptCd
     * @param rqstUserId
     * @param startYmd
     * @param endYmd
     * @param userId
     * @return
     * @throws Exception
     */
    public List<SpeRqstGive> getSpeGives(String plantCd, String speKindCd, String speNm, String rqstDeptCd, String rqstUserId, String startYmd, String endYmd, String userId) throws Exception {
        return speMapper.getSpeGives(plantCd, speKindCd, speNm, rqstDeptCd, rqstUserId, startYmd, endYmd, userId);
    }

    /**
     * 보호구 종류 조회
     *
     * @param plantCd
     *            사업장 코드
     * @return 보호구 종류 목록
     * @throws Exception
     */
    public List<CodeMaster> getSpeKinds(String plantCd) throws Exception {
        return speMapper.getSpeKinds(plantCd);
    }

}
