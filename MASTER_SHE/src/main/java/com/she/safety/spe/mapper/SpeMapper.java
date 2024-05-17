package com.she.safety.spe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.CodeMaster;
import com.she.safety.model.Spe;
import com.she.safety.model.SpeGiDtl;
import com.she.safety.model.SpeGive;
import com.she.safety.model.SpeGiveDetail;
import com.she.safety.model.SpeRqDtl;
import com.she.safety.model.SpeRqstGive;

@Mapper
@Repository("com.she.safety.spe.mapper.SpeMapper")
public interface SpeMapper {

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
    public List<Spe> getSpes(@Param("plantCd") String plantCd, @Param("speKindCd") String speKindCd, @Param("speNm") String speNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 보호구 상세조회
     *
     * @param safSpeNo
     *            보호구 번호
     * @return 보호구 상세
     * @throws Exception
     */
    public Spe getSpe(@Param("safSpeNo") String safSpeNo) throws Exception;

    /**
     * 보호구 신규등록
     *
     * @param spe
     * @return 등록 행 수
     * @throws Exception
     */
    public int createSpe(Spe spe) throws Exception;

    /**
     * 보호구 수정
     *
     * @param spe
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateSpe(Spe spe) throws Exception;

    /**
     * 보호구 신청 저장
     *
     * @param speRqstGive
     * @return 보호구 신청 번호
     * @throws Exception
     */
    public int createSpeRqstGive(SpeRqstGive speRqstGive) throws Exception;

    /**
     * 보호구 신청 상세 저장
     *
     * @param speRqDtl
     * @return 저장 행 수
     * @throws Exception
     */
    public int createSpeRqDtl(SpeRqDtl speRqDtl) throws Exception;

    /**
     * 보호구 신청 등록/수정
     *
     * @param speRqstGive
     * @return 보호구 신청 번호
     * @throws Exception
     */
    public int updateSpeRqstGive(SpeRqstGive speRqstGive) throws Exception;

    /**
     * 보호구 신청 상세 삭제
     *
     * @param safSpeRqstGiveNo
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteSpeRqDtl(@Param("safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception;

    /**
     * 보호구 신청 삭제
     *
     * @param safSpeRqstGiveNo
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteSpeRqstGive(@Param("safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception;

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
    public List<SpeRqstGive> getSpeRqstGiveList(@Param("plantCd") String plantCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("rqstDeptCd") String rqstDeptCd, @Param("rqstUserId") String rqstUserId, @Param("speStepCd") String speStepCd) throws Exception;

    /**
     * 보호구 신청 상세조회
     *
     * @param safSpeRqstGiveNo
     * @return 보호구 신청 상세
     * @throws Exception
     */
    public SpeRqstGive getSpeRqstGive(@Param("safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception;

    /**
     * 보호구 신청 상세 조회
     *
     * @return 보호구 신청 상세 목록
     * @throws Exception
     */
    public List<SpeRqDtl> getSpeRqDtlList(@Param("safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception;

    // 보호구 지급 목록
    public SpeGiDtl getPayList(@Param("safSpeNo") int safSpeNo) throws Exception;

    /**
     * 보호구 신청 지급 수정
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    public int updateSpeRqstGiveForGive(SpeRqstGive speRqstGive) throws Exception;

    /**
     * 보호구 신청 지급비고 수정
     *
     * @param speRqDtl
     * @return
     * @throws Exception
     */
    public int updateGiveSpeRemark(SpeRqDtl speRqDtl) throws Exception;

    /**
     * 보호구 지급상세 등록
     *
     * @param speGiDtl
     * @return
     * @throws Exception
     */
    public int insertSpeGiDtl(SpeGiDtl speGiDtl) throws Exception;

    /**
     * 보호구 지급상세 삭제
     *
     * @param safSpeRqstGiveNo
     * @return
     * @throws Exception
     */
    public int deleteSpeGiDtl(@Param("safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception;

    /**
     * 보호구 지급 상세 목록 조회
     *
     * @param safSpeRqstGiveNo
     * @return
     * @throws Exception
     */
    public List<SpeGiDtl> getSpeGiDtlList(@Param("safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception;

    /**
     * 보호구 무신청 등록
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    public int createSpeNoRqstGive(SpeRqstGive speRqstGive) throws Exception;

    /**
     * 보호구 무신청 수정
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    public int updateSpeNoRqstGive(SpeRqstGive speRqstGive) throws Exception;

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
    public List<SpeRqstGive> getSpeGives(@Param("plantCd") String plantCd, @Param("speKindCd") String speKindCd, @Param("speNm") String speNm, @Param("rqstDeptCd") String rqstDeptCd, @Param("rqstUserId") String rqstUserId, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("userId") String userId) throws Exception;

    /**
     * 보호구 출고 신규등록
     *
     * @param speGive
     *            보호구 출고
     * @return 등록 행 수
     * @throws Exception
     */
    public int createSpeGive(SpeGive speGive) throws Exception;

    /**
     * 보호구 출고 수정
     *
     * @param speGive
     *            보호구 출고
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateSpeGive(SpeGive speGive) throws Exception;

    /**
     * 보호구 출고 상세 등록
     *
     * @param speGiveDetail
     *            보호구 출고 상세
     * @return 등록 행 수
     * @throws Exception
     */
    public int createSpeGiveDetail(SpeGiveDetail speGiveDetail) throws Exception;

    /**
     * 보호구 입고 수
     *
     * @param safSpeInNo
     *            보호구 입고 번호
     * @return 보호구 입고 수
     * @throws Exception
     */
    public int countSpeIn(@Param("safSpeInNo") int safSpeInNo) throws Exception;

    /**
     * 보호구 종류 조회
     *
     * @param plantCd
     *            사업장 코드
     * @return 보호구 종류 목록
     * @throws Exception
     */
    public List<CodeMaster> getSpeKinds(@Param("plantCd") String plantCd) throws Exception;
}
