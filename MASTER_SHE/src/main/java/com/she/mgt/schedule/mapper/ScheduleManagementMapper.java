package com.she.mgt.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.model.ScehduleManagement;
import com.she.mgt.model.ScehduleManagementPsn;


@Mapper
@Repository("com.she.mgt.schedule.mapper.ScheduleManagementMapper")
public interface ScheduleManagementMapper {

    /**
     * 일정 조회
     * 
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param plantCd
     *            사업장코드
     * @param mgtCalTypeCd
     *            업무유형
     * @param title
     *            제목
     * @param createUserId
     *            작성자
     * @param useYn
     *            사용여부
     * @return 일정 목록
     * @throws Exception
     */
    public List<ScehduleManagement> getScehduleManagements(@Param("startDt") String startDt, @Param("endDt") String endDt, @Param("plantCd") String plantCd, @Param("mgtCalTypeCd") String mgtCalTypeCd, @Param("title") String title, @Param("createUserId") String createUserId, @Param("useYn") String useYn)
            throws Exception;

    /**
     * 일정 상세 조회
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 일정
     * @throws Exception
     */
    public ScehduleManagement getScehduleManagement(@Param("mgtCalendarNo") int mgtCalendarNo) throws Exception;
    
    /**
     * 일정 참조자 조회
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 일정 참조자
     * @throws Exception
     */
    public List<ScehduleManagementPsn> getScehduleManagementPsns(@Param("mgtCalendarNo") int mgtCalendarNo) throws Exception;

    /**
     * 일정 신규등록
     * 
     * @param ScehduleManagement
     *            일정
     * @return 등록 행 수
     * @throws Exception
     */
    public int createScehduleManagement(ScehduleManagement scehduleManagement) throws Exception;

    /**
     * 일정 수정
     * 
     * @param ScehduleManagement
     *            일정
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateScehduleManagement(ScehduleManagement scehduleManagement) throws Exception;
    
    /**
     * 일정 삭제
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteScehduleManagement(@Param("mgtCalendarNo") int mgtCalendarNo) throws Exception;
    
    /**
     * 일정 참조자 신규등록
     * 
     * @param ScehduleManagementPsn
     *            일정 참조자
     * @return 등록 행 수
     * @throws Exception
     */
    public int createScehduleManagementPsn(ScehduleManagementPsn scehduleManagementPsn) throws Exception;
    
    /**
     * 일정 참조자 삭제
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteScehduleManagementPsn(@Param("mgtCalendarNo") int mgtCalendarNo) throws Exception;

}
