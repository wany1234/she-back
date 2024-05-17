package com.she.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.BoardMasterMapper;
import com.she.manage.model.BoardMaster;

@Service
public class BoardMasterService {
    @Autowired
    private BoardMasterMapper boardMasterMapper;

    // 게시판 마스터 조회
    public List<BoardMaster> getList(String bbsNm) {
        return boardMasterMapper.selectBoardMaster(bbsNm, 0);
    }

    public BoardMaster getBoardMaster(int bbsNo) {
        List<BoardMaster> boards = boardMasterMapper.selectBoardMaster(null, bbsNo);
        return (boards.size() > 0) ? boards.get(0) : null;
    }

    // 게시판 마스터 생성
    public BoardMaster setBoardMaster(BoardMaster boardMaster) {
        if (boardMaster.getBbsNo() == 0) {
            boardMasterMapper.insertBoardMaster(boardMaster);
        } else {
            boardMasterMapper.updateBoardMaster(boardMaster);
        }
        return boardMaster;
    }

    // 게시판 마스터 삭제
    public int delBoardMaster(int bbsNo) {
        return boardMasterMapper.deleteBoardMaster(bbsNo);
    }
}
