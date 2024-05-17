package com.she.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.she.board.model.Bbs;
import com.she.board.model.BbsReply;

@Mapper
public interface BbsMapper {

    public List<Bbs> selectBbs(@Param("bbsNo") int bbsNo, @Param("word") String word);

    public int insertBbs(Bbs bbs);

    public int updateBbs(Bbs bbs);

    public int updateSearchCnt(Bbs bbs);

    public int insertBbsReply(BbsReply bbsReply);

    public List<BbsReply> selectBbsReply(@Param("notiId") int notiId);

    public int updateBbsReply(BbsReply bbsReply);

}
