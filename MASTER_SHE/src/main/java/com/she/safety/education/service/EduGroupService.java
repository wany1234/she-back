package com.she.safety.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.education.mapper.EduGroupMapper;
import com.she.safety.model.EduGroup;
import com.she.safety.model.EduGroupUser;

@Service
public class EduGroupService {
    @Autowired
    private EduGroupMapper eduGroupMapper;

    /**
     * 교육그룹결과목록
     * 
     * @param groupNm
     *            그룹명
     * @param plantCd
     *            사업장
     * @param useYn
     *            사용여부
     * 
     * @return 교육그룹결과목록
     * @throws Exception
     *             예외
     */
    public List<EduGroup> getEduGroups(String groupNm, String plantCd, String useYn, DefaultParam defaultParam) throws Exception {

        return eduGroupMapper.getEduGroups(groupNm, plantCd, useYn, defaultParam);
    }

    /**
     * 교육그룹결과조회
     * 
     * @param eduGrpNo
     *            그룹번호
     * 
     * 
     * @return 교육그룹결과
     * @throws Exception
     *             예외
     */
    public EduGroup getEduGroup(int eduGrpNo, DefaultParam defaultParam) throws Exception {
        EduGroup eduGroup = eduGroupMapper.getEduGroup(eduGrpNo, defaultParam);
        eduGroup.setEduGroupUsers(eduGroupMapper.getEduGroupUsers(eduGrpNo));

        return eduGroup;
    }

    /**
     * 교육그룹결과 등록
     * 
     * @param eduGroup
     *            교육그룹결과
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int createEduGroup(EduGroup eduGroup) throws Exception {
        int create = 0;
        create = eduGroupMapper.createEduGroup(eduGroup);
        if (create > 0) {
            for (EduGroupUser eduGroupUser : eduGroup.getEduGroupUsers()) {
                eduGroupUser.setEduGrpNo(eduGroup.getEduGrpNo());
                eduGroupMapper.createEduGroupUsers(eduGroupUser);
            }
        }
        return eduGroup.getEduGrpNo();
    }

    /**
     * 교육그룹결과 수정
     * 
     * @param eduGroup
     *            교육그룹결과
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateEduGroup(EduGroup eduGroup) throws Exception {

        int update = 0;
        update = eduGroupMapper.updateEduGroup(eduGroup);
        if (update > 0) {
            eduGroupMapper.deleteEduGroupUsers(eduGroup.getEduGrpNo());
            for (EduGroupUser eduGroupUser : eduGroup.getEduGroupUsers()) {
                eduGroupUser.setEduGrpNo(eduGroup.getEduGrpNo());
                eduGroupMapper.createEduGroupUsers(eduGroupUser);
            }
        }
        return update;
    }

    /**
     * 교육그룹결과 삭제
     * 
     * @param eduGroup
     *            교육그룹결과 번호
     * 
     * @return 교육그룹결과목록
     * @throws Exception
     *             예외
     */
    public int deleteEduGroup(int eduGroup) throws Exception {
        eduGroupMapper.deleteEduGroupUsers(eduGroup);
        return eduGroupMapper.deleteEduGroup(eduGroup);
    }

    /**
     * 교육그룹결과 사용자목록
     * 
     * @param eduGroup
     *            교육그룹결과 번호
     * 
     * @return 교육그룹결과 사용자목록
     * @throws Exception
     *             예외
     */
    public List<EduGroupUser> getEduGroupUsers(int eduGrpNo) throws Exception {
        return eduGroupMapper.getEduGroupUsers(eduGrpNo);
    }

    /**
     * 교육그룹결과 사용자목록 삭제
     * 
     * @param eduGrpNo
     *            교육그룹결과 번호
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int deleteEduGroupUsers(int eduGrpNo) throws Exception {
        return eduGroupMapper.deleteEduGroupUsers(eduGrpNo);
    }

}
