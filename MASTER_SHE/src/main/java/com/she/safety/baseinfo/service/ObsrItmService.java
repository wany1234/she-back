package com.she.safety.baseinfo.service;

import com.she.safety.baseinfo.mapper.ObsrItmMapper;
import com.she.safety.model.ObsrItm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 안전 > 기준정보 > 안전관찰항목(SK E&S)
 *
 */
@Service
public class ObsrItmService {
    @Autowired
    private ObsrItmMapper obsrItmMapper;

    /**
     * 안전관찰항목 등록
     *
     * @param obsrItm
     *            안전관찰체크항목
     * @return 안전관찰체크항목번호
     * @throws Exception
     */
    @Transactional
    public int createObsrItm(ObsrItm obsrItm) throws Exception {
        return this.obsrItmMapper.createObsrItm(obsrItm) > 0 ? obsrItm.getSafObsrItmNo() : 0;
    }

    /**
     * 안전관찰항목 목록 조회
     *
     * @param plantCd
     *            사업장코드
     * @param obsrItmNm
     *            안전관찰 항목명
     * @param useYn
     *            사용여부
     * @return 안전관찰항목 목록
     * @throws Exception
     */
    public List<ObsrItm> getObsrItms(String plantCd, String obsrItmNm, String useYn) throws Exception {
        return this.obsrItmMapper.getObsrItms(plantCd, obsrItmNm, useYn);
    }

    /**
     * 안전관찰항목 상세 조회
     *
     * @param safObsrItmNo
     *            안전관찰체크항목번호
     * @return 안전관찰항목
     * @throws Exception
     */
    public ObsrItm getObsrItm(int safObsrItmNo) throws Exception {
        return this.obsrItmMapper.getObsrItm(safObsrItmNo);
    }

    /**
     * 안전관찰항목 수정
     *
     * @param obsrItm
     *            안전관찰체크항목
     * @return 안전관찰체크항목번호
     * @throws Exception
     */
    @Transactional
    public int updateObsrItm(ObsrItm obsrItm) throws Exception {
        return this.obsrItmMapper.updateObsrItm(obsrItm);
    }


    /**
     * 안전관찰항목 중복 검사
     *
     * @param plantCd
     *            사업장코드
     * @param itmTypeCd
     *            관찰항목분류 코드
     * @param obsrItmNm
     *            안전관찰 항목명
     * @param safObsrItmNo
     *            안전관찰체크항목번호
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkObsrItm(String plantCd, String itmTypeCd, String obsrItmNm, int safObsrItmNo) throws Exception {
        return this.obsrItmMapper.checkObsrItm(plantCd, itmTypeCd, obsrItmNm, safObsrItmNo);
    }
}
