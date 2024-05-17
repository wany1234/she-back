package com.she.common.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.UserGridMapper;
import com.she.common.model.UserGridCol;

@Service
public class UserGridService {

    @Autowired
    private UserGridMapper userGridMapper;

    /**
     * 사용자별 메뉴 그리드 컬럼설정 목록
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    public List<UserGridCol> getUserGridCols(UserGridCol userGridCol) throws Exception {
        return this.userGridMapper.getUserGridCols(userGridCol);
    }

    /**
     * 사용자별 메뉴 그리드 컬럼설정 등록
     * 
     * @param userGridCol
     * @throws Exception
     */
    @Transactional
    public Integer createUserGridCol(final UserGridCol userGridCol) throws Exception {

        this.userGridMapper.deleteUserGridCol(userGridCol);

        return this.userGridMapper.createUserGridCol(userGridCol);
    }

    /**
     * 사용자별 메뉴 그리드 컬럼설정 삭제
     * 
     * @param userGridCol
     * @throws Exception
     */
    @Transactional
    public Integer deleteUserGridCol(final UserGridCol userGridCol) throws Exception {
        int retCnt = 0;
        if (userGridCol.getType().equals("ALL_INIT")) {
            retCnt = this.userGridMapper.deleteUserGridColAll(userGridCol);
        } else {
            retCnt = this.userGridMapper.deleteUserGridCol(userGridCol);
        }
        return retCnt;
    }
}
