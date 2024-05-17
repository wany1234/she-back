package com.she.manage.model;

import java.util.List;

import lombok.Data;

/**
 * 사용자 권한 일괄 등록용 모델 클래스
 */
@Data
public class UserAuthGrps {
    // 사용자의 권한 그룹 일괄 삭제 후 재 부여 대상 userId
    private List<String> userIds;
    // 등록할 사용자 권한 그룹 번호
    private List<Integer> authGrpNos;
    private List<UserAuthGrp> userAuthGrps;
}
