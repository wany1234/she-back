package com.she.health.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.health.baseInfo.mapper.CheckListMapper;
import com.she.health.model.CheckList;

@Service
public class CheckListService {
    @Autowired
    private CheckListMapper checkListMapper;

    /**
     * 체크리스트 조회
     *
     * @return 체크리스트 목록
     * @throws Exception
     */
    public List<CheckList> getCheckLists(String plantCd, String chklistType, String useYn, DefaultParam defaultParam) throws Exception {
        return checkListMapper.getCheckLists(plantCd, chklistType, useYn, defaultParam);
    }

    /**
     * 체크리스트 상세 조회
     *
     * @param chklistNo
     *            체크리스트번호
     * @return 체크리스트
     * @throws Exception
     */
    public CheckList getCheckList(int chklistNo, DefaultParam defaultParam) throws Exception {
        // 체크리스트 조회
        CheckList checkList = this.checkListMapper.getCheckList(chklistNo);
        if (checkList != null) {
            // 체크리스트 항목 조회
            checkList.setCheckListItems(this.checkListMapper.getCheckListItems(chklistNo, "", defaultParam));
        }
        return checkList;
    }

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
    public List<HashMap<String, Object>> getCheckCheckList(String plantCd, String chklistNm, int chklistNo) throws Exception {
        return checkListMapper.getCheckCheckList(plantCd, chklistNm, chklistNo);
    }

}
