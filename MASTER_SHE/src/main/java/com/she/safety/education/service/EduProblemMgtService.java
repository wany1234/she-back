package com.she.safety.education.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.safety.education.mapper.EduMasterMapper;
import com.she.safety.education.mapper.EduProblemMgtMapper;
import com.she.safety.model.EduCourseMat;
import com.she.safety.model.EduProblemMgt;
import com.she.utils.FileUtil;

@Service
public class EduProblemMgtService {
    @Autowired
    private EduProblemMgtMapper eduProblemMgtMapper;

    @Autowired
    private EduMasterMapper eduMasterMapper;

    /**
     * 교육자료관리 조회
     * 
     * @param safEduCourseNo
     *            교육과정번호
     * @param plantCd
     *            사업장코드
     * @param eduAttCd
     *            분류코드
     * @param eduNm
     *            제목
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduProblemMgt> getEduProblemMgts(int safEduCourseNo, String plantCd, String eduAttCd, String eduNm, String eduTypeCd, DefaultParam defaultParam) throws Exception {
        return eduProblemMgtMapper.getEduProblemMgts(safEduCourseNo, plantCd, eduAttCd, eduNm, eduTypeCd, defaultParam);
    }

    /**
     * 교육자료관리 조회
     * 
     * @param safEduCourseNo
     *            교육과정
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public List<EduProblemMgt> getEduPlanProblems(@Param("safEduCourseNo") int safEduCourseNo, @Param("safEduMstNo") int safEduMstNo, DefaultParam defaultParam) throws Exception {

        List<EduProblemMgt> eduProblems = eduProblemMgtMapper.getEduProblemMgts(safEduCourseNo, null, null, null, null, defaultParam);
        if (eduProblems.size() > 0) {
            eduProblems.get(0).setCheckData(eduMasterMapper.getPlanData(String.valueOf(safEduMstNo), defaultParam));
        }
        return eduProblems;
    }

    /**
     * 신규 교육자료관리 조회
     * 
     * @param safEduCourseNo
     * @param safEduMstNo
     * @return
     * @throws Exception
     */
    public List<EduProblemMgt> getEduPlanProblemsNew(@Param("safEduCourseNo") int safEduCourseNo, @Param("safEduMstNo") int safEduMstNo, DefaultParam defaultParam) throws Exception {

        List<EduProblemMgt> eduProblems = eduProblemMgtMapper.getEduProblemMgtsNew(safEduCourseNo, safEduMstNo, null, null, null, null, defaultParam);
        if (eduProblems.size() > 0) {
            eduProblems.get(0).setCheckData(eduMasterMapper.getPlanData(String.valueOf(safEduMstNo), defaultParam));
        }
        return eduProblems;
    }

    /**
     * 기본 교육자료 삭제
     * 
     * @param safEduMstNo
     * @param eduCourseMatList
     * @return
     * @throws Exception
     */
    public int updateEduPlanProblems(@Param("safEduMstNo") int safEduMstNo, @Param("EduProblemMgt") List<EduCourseMat> eduCourseMatList) throws Exception {
        int resultNo = 0;
        eduProblemMgtMapper.deleteEduPlanProblems(safEduMstNo);

        if (eduCourseMatList != null && eduCourseMatList.size() > 0) {
            for (EduCourseMat eduCourseMat : eduCourseMatList) {
                eduCourseMat.setSafEduMstNo(safEduMstNo);
                eduProblemMgtMapper.insertEduPlanProblems(eduCourseMat);
                resultNo++;
            }
        }
        return resultNo;
    }

    /**
     * 교육계획등록-교육자료 등록
     * 
     * @param taskKey
     * @throws Exception
     */
    public void updateEduPlanProblemMat(String taskClassNm, int taskKey) throws Exception {
        // 기존 교육자료 데이터 삭제
        eduProblemMgtMapper.deleteEduPlanProblemMat(taskKey);
        // 교육자료 데이터 저장
        eduProblemMgtMapper.insertEduPlanProblemMat(taskKey);

        // 교육자료 파일 복사
        List<AttachFile> attachFiles = eduProblemMgtMapper.getMatFileData(taskKey);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = new java.util.Date();
        String today = format.format(date);

        if (attachFiles != null && attachFiles.size() > 0) {
            for (AttachFile attachFile : attachFiles) {
                File file = new File(attachFile.getFilePath());
                File directory = new File(FileUtil.getStoreFilePath() + File.separator + "eduregrefference" + today);
                File newFile = new File(FileUtil.getStoreFilePath() + File.separator + "eduregrefference" + today + File.separator + attachFile.getFileSaveNm());

                directory.setExecutable(false, true);
                directory.setReadable(true);
                directory.setWritable(false, true);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                copyFile(file, newFile);

                // String copyFileName = attachFile.getFilePath();
                // String newFileName = FileUtil.getStoreFilePath() +
                // File.separator + "eduregreference" + File.separator +
                // attachFile.getFileSaveNm();
                // FileInputStream fileIn = new FileInputStream(copyFileName);
                // FileOutputStream fileOut = new FileOutputStream(newFileName);
                // InputStream dd = new InputStream("dd");
                //
                //
                // byte[] buffer = new byte[1024];
                //
                // int length;
                //
                // while((length = fileIn))
                // fileIn = fileIn;
            }
        }
    }

    private void copyFile(File file, File mfile) throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new FileInputStream(file); // 원본파일
            outStream = new FileOutputStream(mfile); // 이동시킬 위치

            byte[] buffer = new byte[1024];

            int length;

            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            inStream.close();
            outStream.close();
        }
    }

    /**
     * 교육자료결과 조회
     * 
     * @param safEduCourseNo
     *            교육과정
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public List<EduProblemMgt> getEduPlanProblemResults(@Param("safEduMstNo") int safEduMstNo, DefaultParam defaultParam) throws Exception {
        List<EduProblemMgt> eduProblems = eduProblemMgtMapper.getEduProblemMgtResults(safEduMstNo, defaultParam);

        return eduProblems;

    }

    /**
     * 교육자료관리 개정 조회
     * 
     * @param eduMatNo
     *            교육자료관리 번호
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduProblemMgt> getEduProblemMgtRevisions(int eduMatNo, DefaultParam defaultParam) throws Exception {
        return eduProblemMgtMapper.getEduProblemMgtRevisions(eduMatNo, defaultParam);
    }

    /**
     * 교육자료관리 상세 조회
     * 
     * @param eduMatNo
     *            교육자료관리 번호
     * @return getEduProblemMgt 교육자료관리
     * @throws Exception
     *             예외
     */
    public EduProblemMgt getEduProblemMgt(int eduMatNo, DefaultParam defaultParam) throws Exception {
        return eduProblemMgtMapper.getEduProblemMgt(eduMatNo, defaultParam);
    }

    public EduProblemMgt getEduRegProblemMgt(int eduCourseMatNo, DefaultParam defaultParam) throws Exception {
        return eduProblemMgtMapper.getEduRegProblemMgt(eduCourseMatNo, defaultParam);
    }

    /**
     * 교육자료관리 신규등록
     * 
     * @param getEduProblemMgt
     *            교육자료관리
     * @return eduMatNo 교육자료관리 번호
     * @throws Exception
     *             예외
     */
    @Transactional
    public int createEduProblemMgt(EduProblemMgt eduProblemMgt) throws Exception {
        eduProblemMgtMapper.createEduProblemMgt(eduProblemMgt);
        return eduProblemMgt.getEduMatNo();

    }

    /**
     * 교육자료관리 수정
     * 
     * @param EduMaster
     *            교육마스터
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateEduProblemMgt(EduProblemMgt eduProblemMgt) throws Exception {
        eduProblemMgtMapper.updateEduProblemMgt(eduProblemMgt);
        return eduProblemMgt.getEduMatNo();
    }

    /**
     * 교육자료관리 삭제
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    public int deleteEduProblemMgt(int safEduMstNo) throws Exception {
        return eduProblemMgtMapper.deleteEduProblemMgt(safEduMstNo);
    }
}
