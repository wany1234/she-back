/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.AlarmLog;
import com.she.manage.model.BatchLog;
import com.she.manage.model.CrudLog;
import com.she.manage.model.ErrorLog;
import com.she.manage.model.ImpoAction;
import com.she.manage.model.MailLog;
import com.she.manage.model.MenuLog;
import com.she.security.model.LoginLog;

@Mapper
@Repository("com.she.manage.mapper.LogMapper")
public interface LogListMapper {

    /**
     * 로그인로그 리스트 조회
     *
     * @param fromDt
     * @param toDt
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<LoginLog> getLoginLogList(@Param("fromDt") String fromDt, @Param("toDt") String toDt, @Param("deptCd") String deptCd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 에러로그 리스트 조회
     *
     * @param fromDt
     * @param toDt
     * @return
     * @throws Exception
     */
    public List<ErrorLog> getErrorLogList(@Param("fromDt") String fromDt, @Param("toDt") String toDt) throws Exception;

    public ErrorLog getErrorLogDetail(@Param("logNo") int logNo) throws Exception;

    public List<MailLog> getMailLogs(@Param("fromDt") String fromDt, @Param("toDt") String toDt, @Param("keyword") String keyword) throws Exception;

    public int resendMailLog(MailLog mailLog) throws Exception;

    public List<AlarmLog> getSmsLogs(@Param("fromDt") String fromDt, @Param("toDt") String toDt, @Param("word") String word) throws Exception;

    public int resendSmsLog(AlarmLog alarmLog) throws Exception;

    public List<BatchLog> getBatchLogs(@Param("fromDt") String fromDt, @Param("toDt") String toDt);

    public void insertMenuLog(@Param("menuId") String menuId, @Param("menuNm") String menuNm, @Param("menuPath") String menuPath, @Param("url") String url, @Param("createUserId") String createUserId) throws Exception;

    public List<MenuLog> getMenuLog(@Param("menuNm") String menuNm, @Param("fromDt") String fromDt, @Param("toDt") String toDt) throws Exception;

    public ImpoAction getImpoConform(@Param("servletPath") String servletPath, @Param("crudCd") String crudCd) throws Exception;

    public void insertCrudLog(@Param("crudCd") String crudCd, @Param("crudNm") String crudNm, @Param("crudDesc") String crudDesc, @Param("userId") String userId, @Param("url") String url, @Param("option") String option) throws Exception;

    public List<CrudLog> getCrudLog(@Param("crudNm") String crudNm, @Param("fromDt") String fromDt, @Param("toDt") String toDt) throws Exception;

    public List<ImpoAction> getImpoActionList() throws Exception;

    public void insertImpoAction(@Param("servletPath") String servletPath, @Param("crudCd") String crudCd, @Param("crudNm") String crudNm, @Param("crudDesc") String crudDesc, @Param("useYn") String useYn) throws Exception;

    public void updateImpoAction(@Param("servletPath") String servletPath, @Param("crudCd") String crudCd, @Param("crudNm") String crudNm, @Param("crudDesc") String crudDesc, @Param("useYn") String useYn) throws Exception;

}