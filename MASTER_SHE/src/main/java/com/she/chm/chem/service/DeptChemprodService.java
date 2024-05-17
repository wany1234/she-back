package com.she.chm.chem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.she.common.model.DefaultParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.she.chm.chem.mapper.DeptChemprodMapper;
import com.she.chm.model.Chemprod;
import com.she.chm.model.DeptChemprod;
import com.she.chm.model.DeptProcessChemProds;
import com.she.manage.model.User;
import com.she.manage.service.UserService;
import com.she.security.auth.IAuthenticationFacade;
import com.she.utils.model.MailVo;

@Service
public class DeptChemprodService {

    private static Logger logger = LoggerFactory.getLogger(DeptChemprodService.class);

    @Autowired
    private DeptChemprodMapper deptChemprodMapper;

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private ChemprodService chemprodService;

    /**
     * 부서공정별 취급자재 조회
     *
     * @param deptCd
     *            부서 코드
     * @param processCd
     *            공정코드
     * @param search
     *            검색어(취급자재명, 공급업체명, 제조업체명)
     * @param useYn
     *            사용여부
     * @return 부서공정별 취급자재 목록
     * @throws Exception
     */
    public List<DeptChemprod> getDeptChemprods(String plantCd, String deptCd, String processCd, String search, String useYn, DefaultParam defaultParam) throws Exception {
        return deptChemprodMapper.getDeptChemprods(plantCd, deptCd, processCd, search, useYn, defaultParam);
    }

    /**
     * 부서공정별 취급자재 상세 조회
     *
     * @param processCd
     *            공정 번호
     * @param chemProdNo
     *            취급자재 번호
     * @return 부서공정별 취급자재
     * @throws Exception
     */
    public DeptProcessChemProds getDeptChemprod(String processCd, DefaultParam defaultParam) throws Exception {
        List<DeptChemprod> lists = this.deptChemprodMapper.getDeptChemprod(processCd, defaultParam);

        DeptProcessChemProds deptProcessChemProds = new DeptProcessChemProds();
        if (lists != null && lists.size() > 0) {
            DeptChemprod deptChemprod = lists.get(0);
            deptProcessChemProds.setPlantCd(deptChemprod.getPlantCd());
            deptProcessChemProds.setPlantNm(deptChemprod.getPlantNm());
            deptProcessChemProds.setDeptCd(deptChemprod.getDeptCd());
            deptProcessChemProds.setDeptNm(deptChemprod.getDeptNm());
            deptProcessChemProds.setProcessCd(deptChemprod.getProcessCd());
            deptProcessChemProds.setProcessNm(deptChemprod.getProcessNm());
            deptProcessChemProds.setUseYn(deptChemprod.getUseYn());
            deptProcessChemProds.setUseYnNm(deptChemprod.getUseYnNm());
            deptProcessChemProds.setCreateUserId(deptChemprod.getCreateUserId());
            deptProcessChemProds.setCreateUserNm(deptChemprod.getCreateUserNm());
            deptProcessChemProds.setCreateDt(deptChemprod.getCreateDt());
            deptProcessChemProds.setUpdateUserId(deptChemprod.getUpdateUserId());
            deptProcessChemProds.setUpdateUserNm(deptChemprod.getUpdateUserNm());
            deptProcessChemProds.setUpdateDt(deptChemprod.getUpdateDt());
        }
        List<DeptChemprod> chemProds = new ArrayList<DeptChemprod>();
        for (DeptChemprod deptChemprod : lists) {
            chemProds.add(deptChemprod);
        }
        deptProcessChemProds.setChemProds(chemProds);
        return deptProcessChemProds;
    }

    /**
     * 부서공정별 취급자재 저장
     *
     * @param DeptChemprod
     *            부서공정별 취급자재
     * @return 부서공정별 취급자재
     * @throws Exception
     */
    @Transactional
    public DeptProcessChemProds createDeptChemprod(DeptProcessChemProds deptProcessChemProds) throws Exception {
        this.deptChemprodMapper.deleteDeptChemprod(deptProcessChemProds.getProcessCd());

        for (DeptChemprod deptChemprod : deptProcessChemProds.getChemProds()) {
            deptChemprod.setDeptCd(deptProcessChemProds.getDeptCd());
            deptChemprod.setProcessCd(deptProcessChemProds.getProcessCd());
            deptChemprod.setCreateUserId(iAuthenticationFacade.getUserName(""));
            deptChemprod.setUseYn(deptProcessChemProds.getUseYn());
            logger.info(deptChemprod.toString());
            this.deptChemprodMapper.createDeptChemprod(deptChemprod);
        }

        /**
         * 메일 (저장시 마다) ㅇ울산 : 김동호G4, 공성윤G3 ㅇ안강 : 신윤석G4, 이영진G4 ㅇ부산 : 신성용G3, 정한서G4
         */
        List<User> users = userService.getChmMaliReceiveUserInfo(deptProcessChemProds.getPlantCd());

        // String[] recipients = { "dhkim@yullin.com" };
        String[] recipients = new String[users.size()];
        if (users != null && users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                recipients[i] = users.get(i).getEmail();
            }

            List<DeptChemprod> lists = this.deptChemprodMapper.getDeptChemprod(deptProcessChemProds.getProcessCd(), new DefaultParam("kr"));

            if (lists != null && lists.size() > 0) {
                DeptChemprod deptChemprod = lists.get(0);

                List<String> changeChemProds = deptProcessChemProds.getChangeChemProds();
                if (changeChemProds != null && changeChemProds.size() > 0) {
                    for (String chemProdNo : changeChemProds) {
                        Chemprod chemprod = chemprodService.getChemprod(Integer.parseInt(chemProdNo), new DefaultParam("kr"));
                        if (chemprod != null) {
                            MailVo mail = new MailVo();
                            mail.setTitle("[SHE시스템] 공정-자재 매칭 정보 변경안내");
                            mail.setRecipientsEmailAddress(recipients);

                            String contents = "";
                            contents += "[SHE시스템] 공정-자재 매칭 정보 변경안내<br/><br/><br/>";
                            contents += "안전보건환경 시스템에서 알려 드립니다.<br/><br/>";
                            contents += deptChemprod.getDeptNm() + " " + deptChemprod.getProcessNm() + "에서 취급하는 자재가 변경되었습니다.<br/><br/>";
                            contents += "ㅇ변경내용 : " + chemprod.getChemProdNmKr() + " " + chemprod.getSapMatCd() + "<br/><br/>";
                            contents += "세부내용은 안전보건환경 시스템에서 확인하실 수 있습니다";

                            mail.setContents(contents);
                            /**
                             * 2020-02-07 메일 발송 로직 주석 처리 풍산의 메일 보내는 PC에 접속 하지 못할
                             * 뿐더러 접속이 이루어져서도 안됨 위의 사항이 있기 때문에 로직 주석처리하여 사전차단하고자
                             * 함
                             */
                            // SendMailUtil.sendMail(mail);
                        }
                    }
                }
            }
        }

        return deptProcessChemProds;
    }

    /**
     * 부서공정별 취급자재 체크
     *
     * @param deptCd
     *            부서 코드
     * @param processCd
     *            공정 코드
     * @return 부서공정별 취급자재 체크
     * @throws Exception
     */
    public HashMap<String, Object> getCheckDeptChemProd(String deptCd, String processCd) throws Exception {
        return deptChemprodMapper.getCheckDeptChemProd(deptCd, processCd);
    }

    /**
     * 부서공정별 취급자재 수정
     *
     * @param deptProcessChemProds
     *            부서공정별 취급자재
     * @return 부서공정별 취급자재
     * @throws Exception
     */
    @Transactional
    public DeptProcessChemProds updateDeptChemprod(DeptProcessChemProds deptProcessChemProds) throws Exception {
        for (DeptChemprod deptChemprod : deptProcessChemProds.getChemProds()) {
            deptChemprod.setDeptCd(deptProcessChemProds.getDeptCd());
            deptChemprod.setProcessCd(deptProcessChemProds.getProcessCd());
            deptChemprod.setUpdateUserId(iAuthenticationFacade.getUserName(""));
            deptChemprod.setUseYn(deptProcessChemProds.getUseYn());
            this.deptChemprodMapper.updateDeptChemprod(deptChemprod);
        }
        return deptProcessChemProds;
    }
}
