package com.she.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.she.common.model.LangParam;
import com.she.manage.model.LblDtlInfo;
import com.she.manage.model.MsgDtlInfo;

@Mapper
@Repository("com.she.common.mapper.LangMapper")
public interface LangMapper {

    /**
     * 언어별 라벨 값을 반환
     * 
     * @param langParam
     *            언어 param
     * @return 언어별 라벨 값
     * @throws Exception
     */
    public List<LblDtlInfo> getLangInfoLabel(LangParam langParam) throws Exception;

    /**
     * 언어별 메시지 값을 반환
     * 
     * @param langParam
     *            언어 param
     * @return 언어별 메시지 값
     * @throws Exception
     */
    public List<MsgDtlInfo> getLangInfoMessage(LangParam langParam) throws Exception;

}
