package com.she.safety.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduVideo {
    private int safEduMstNo;

    private int safEduCourseNo;

    private int eduVideoNo;

    private String plantCd;

    private String videoUrl;

    private int videoTotalTime;

    private int videoCompTime;

    private String useYn;

    private int sortOrder;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

}
