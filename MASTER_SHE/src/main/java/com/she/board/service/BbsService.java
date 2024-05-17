package com.she.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.board.mapper.BbsMapper;
import com.she.board.model.Bbs;
import com.she.board.model.BbsReply;

@Service
public class BbsService {
    @Autowired
    private BbsMapper bbsMapper;

    public List<Bbs> getList(int bbsNo, String word) {
        return bbsMapper.selectBbs(bbsNo, word);
    }

    public Bbs setBbs(Bbs bbs) {
        if (bbs.getNotiNo() == 0) {
            bbsMapper.insertBbs(bbs);
        } else {
            bbsMapper.updateBbs(bbs);
        }
        return bbs;
    }

    public Object setBbsSearchCount(Bbs bbs) {
        bbsMapper.updateSearchCnt(bbs);
        return new Object() {
            public String getMessage() {
                return "Success";
            }
        };
    }

    public Object delBbs(Bbs bbs) {
        bbs.setUseYn("N");
        bbsMapper.updateBbs(bbs);
        return new Object() {
            public String getMessage() {
                return "Success";
            }
        };
    }

    /*
     * 댓글 목록 조회
     */
    public List<BbsReply> getReplyList(int notiId) {
        return bbsMapper.selectBbsReply(notiId);
    }

    /*
     * 댓글 등록(replyNo==0), 댓글 수정(replyNo!=0)
     */
    public BbsReply setBbsReply(BbsReply bbsReply) {
        if (bbsReply.getReplyNo() == 0) {
            bbsMapper.insertBbsReply(bbsReply);
        } else {
            bbsMapper.updateBbsReply(bbsReply);
        }
        return bbsReply;
    }

    /*
     * 댓글 삭제
     */
    public Object delBbsReply(BbsReply bbsReply) {
        bbsReply.setUseYn("N");
        bbsMapper.updateBbsReply(bbsReply);
        return new Object() {
            public String getMessage() {
                return "Success";
            }
        };
    }

}
