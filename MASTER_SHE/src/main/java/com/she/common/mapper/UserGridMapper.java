package com.she.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.she.common.model.UserGridCol;

@Mapper
@Repository("com.she.common.mapper.UserGridMapper")
public interface UserGridMapper {

    /**
     * 사용자별 메뉴 그리드 컬럼설정 목록
     * 
     * @param UserGridCol
     * @return
     * @throws Exception
     */
    public List<UserGridCol> getUserGridCols(UserGridCol userGridCol) throws Exception;

    /**
     * 사용자별 메뉴 그리드 컬럼설정 등록
     * 
     * @param UserGridCol
     * @throws Exception
     */
    public Integer createUserGridCol(UserGridCol userGridCol) throws Exception;

    /**
     * 사용자별 메뉴 그리드 컬럼설정 삭제
     * 
     * @param UserGridCol
     * @throws Exception
     */
    public Integer deleteUserGridCol(UserGridCol userGridCol) throws Exception;

    /**
     * 사용자별 메뉴 그리드 컬럼설정 삭제
     * 
     * @param UserGridCol
     * @throws Exception
     */
    public Integer deleteUserGridColAll(UserGridCol userGridCol) throws Exception;

}
