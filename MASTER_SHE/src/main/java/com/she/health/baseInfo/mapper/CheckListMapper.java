package com.she.health.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.health.model.CheckList;
import com.she.health.model.CheckListItem;

@Mapper
@Repository("com.she.health.baseInfo.mapper.CheckListMapper")
public interface CheckListMapper {

    /**
     * 체크리스트 조회
     *
     * @return 체크리스트 목록
     * @throws Exception
     */
    public List<CheckList> getCheckLists(@Param("plantCd") String plantCd, @Param("chklistType") String chklistType, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 체크리스트 상세 조회
     *
     * @param chklistNo
     *            체크리스트번호
     * @return 체크리스트
     * @throws Exception
     */
    public CheckList getCheckList(@Param("chklistNo") int chklistNo) throws Exception;

    /**
     * 체크리스트명 체크
     *
     * @param plantCd
     *            사업장코드
     * @param chklistNm
     *            체크리스트명
     * @param chklistNo
     *            체크리스트번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckCheckList(@Param("plantCd") String plantCd, @Param("chklistNm") String chklistNm, @Param("chklistNo") int chklistNo) throws Exception;

    /**
     * 체크리스트 항목 조회
     *
     * @return 체크리스트 항목 목록
     * @throws Exception
     */
    public List<CheckListItem> getCheckListItems(@Param("chklistNo") int chklistNo, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
