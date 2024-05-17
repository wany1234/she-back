package com.she.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.common.mapper.SendTalkMapper")
public interface SendTalkMapper {

    /**
     * 알림톡 전송 프로시져
     * 
     * @param phoneNo
     * @param message
     * @param templateCd
     * @param jobType
     * @param timeType
     * @return
     * @throws Exception
     */
    public int sendTalkProcedure(@Param("phoneNo") String phoneNo, @Param("message") String message,
            @Param("templateCd") String templateCd, @Param("jobType") String jobType,
            @Param("timeType") String timeType) throws Exception;
}
