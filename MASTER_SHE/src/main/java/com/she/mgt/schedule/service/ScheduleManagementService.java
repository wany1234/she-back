package com.she.mgt.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.mgt.model.ScehduleManagement;
import com.she.mgt.model.ScehduleManagementPsn;
import com.she.mgt.schedule.mapper.ScheduleManagementMapper;

@Service
public class ScheduleManagementService {
    @Autowired
    private ScheduleManagementMapper scheduleManagementMapper;

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
    public List<ScehduleManagement> getScehduleManagements(String startDt, String endDt, String plantCd, String mgtCalTypeCd, String title, String createUserId, String useYn) throws Exception {
        return scheduleManagementMapper.getScehduleManagements(startDt, endDt, plantCd, mgtCalTypeCd, title, createUserId, useYn);
    }

    /**
     * 일정 상세 조회
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 일정
     * @throws Exception
     */
    public ScehduleManagement getScehduleManagement(int mgtCalendarNo) throws Exception {
        ScehduleManagement scehduleManagement = new ScehduleManagement();
        scehduleManagement = this.scheduleManagementMapper.getScehduleManagement(mgtCalendarNo);
        if (scehduleManagement != null) {
            scehduleManagement.setScehduleManagementPsns(this.scheduleManagementMapper.getScehduleManagementPsns(mgtCalendarNo));
        }
        return scehduleManagement;
    }

    /**
     * 일정 신규등록
     * 
     * @param ScehduleManagement
     *            일정
     * @return 일정코드
     * @throws Exception
     */
    @Transactional
    public int createScehduleManagement(ScehduleManagement scehduleManagement) throws Exception {
        int reusltNo = 0;
        // 일정 등록
        reusltNo += this.scheduleManagementMapper.createScehduleManagement(scehduleManagement);

        if (scehduleManagement.getScehduleManagementPsns() != null && scehduleManagement.getScehduleManagementPsns().size() > 0) {
            for (ScehduleManagementPsn scehduleManagementPsn : scehduleManagement.getScehduleManagementPsns()) {
                scehduleManagementPsn.setMgtCalendarNo(scehduleManagement.getMgtCalendarNo());
                // 참조자 등록
                this.scheduleManagementMapper.createScehduleManagementPsn(scehduleManagementPsn);
            }
        }

        return reusltNo > 0 ? scehduleManagement.getMgtCalendarNo() : 0;
    }

    /**
     * 일정 수정
     * 
     * @param ScehduleManagement
     *            일정
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateScehduleManagement(ScehduleManagement scehduleManagement) throws Exception {
        int reusltNo = 0;
        // 일정 수정
        reusltNo += this.scheduleManagementMapper.updateScehduleManagement(scehduleManagement);

        if (scehduleManagement.getScehduleManagementPsns() != null) {
            this.scheduleManagementMapper.deleteScehduleManagementPsn(scehduleManagement.getMgtCalendarNo());
            if (scehduleManagement.getScehduleManagementPsns().size() > 0) {
                for (ScehduleManagementPsn scehduleManagementPsn : scehduleManagement.getScehduleManagementPsns()) {
                    scehduleManagementPsn.setMgtCalendarNo(scehduleManagement.getMgtCalendarNo());
                    // 참조자 등록
                    this.scheduleManagementMapper.createScehduleManagementPsn(scehduleManagementPsn);
                }
            }
        }
        return reusltNo;
    }

    /**
     * 일정 삭제
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteScehduleManagement(int mgtCalendarNo) throws Exception {
        int reusltNo = 0;
        // 참조자 삭제
        reusltNo += this.scheduleManagementMapper.deleteScehduleManagementPsn(mgtCalendarNo);
        // 일정삭제
        reusltNo += this.scheduleManagementMapper.deleteScehduleManagement(mgtCalendarNo);
        return reusltNo;
    }

}
