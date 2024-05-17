package com.she.safety.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.CheckKind;

@Mapper
@Repository("com.she.safety.check.mapper.CheckKindMapper")
public interface CheckKindMapper {

    /**
     * 안전점검종류 조회
     * 
     * @param 안전점검
     *            종류명
     * @param plantCd
     *            사업장코드
     * @param planUseYn
     *            점검계획사용여부
     * @param facilityUseYn
     *            설비점검해당여부
     * @param useYn
     *            사용여부
     * @param patrolYn
     *            순회여부
     * @return 안전점검종류 목록
     * @throws Exception
     */
    public List<CheckKind> getCheckKindList(@Param("plantCd") String plantCd,
            @Param("safCheckKindNm") String safCheckKindNm, @Param("planUseYn") String planUseYn,
            @Param("facilityUseYn") String facilityUseYn, @Param("useYn") String useYn,
            @Param("patrolYn") String patrolYn, @Param("subconUseYn") String subconUseYn,
            @Param("congYn") String congYn, @Param("chngKind") String chngKind,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전점검종류 상세 조회
     * 
     * @param 안전점검
     *            종류번호
     * @return 안전점검종류 목록
     * @throws Exception
     */
    public CheckKind getCheckKindItem(@Param("safCheckKindNo") String safCheckKindNo) throws Exception;

    /**
     * 안전 점검 종류 생성
     * 
     * @param CheckKind
     *            안전점검종류
     * @return 변경 행 수
     * @throws Exception
     */
    public int createCheckKind(CheckKind checkKind) throws Exception;

    /**
     * 안전 점검 종류 수정
     * 
     * @param CheckKind
     *            안전점검종류
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateCheckKind(CheckKind checkKind) throws Exception;

    /**
     * 안전점검종류명 중복 조회
     * 
     * @param CheckKind
     *            안전점검종류
     * @return 안전점검종류 번호
     * @throws Exception
     */
    public List<CheckKind> checkCheckKind(CheckKind checkKind) throws Exception;

}
