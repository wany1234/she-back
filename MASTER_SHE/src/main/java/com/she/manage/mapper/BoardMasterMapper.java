package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.BoardMaster;

@Mapper
@Repository
public interface BoardMasterMapper {

    /*
     * 게시판마스터 조회 Params { boardNm : 게시판 명 }
     **/
    public List<BoardMaster> selectBoardMaster(@Param("bbsNm") String bbsNm, @Param("bbsNo") int bbsNo);

    public int insertBoardMaster(BoardMaster boardMaster);

    public int deleteBoardMaster(@Param("bbsNo") int bbsNo);

    public int updateBoardMaster(BoardMaster boardMaster);

}
