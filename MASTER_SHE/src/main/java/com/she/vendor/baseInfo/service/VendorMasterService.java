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
package com.she.vendor.baseInfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.utils.SendMailUtil;
import com.she.utils.model.MailVo;
import com.she.vendor.baseInfo.mapper.VendorMasterMapper;
import com.she.vendor.model.ChemicalVendorMaster;
import com.she.vendor.model.VendorWorker;

@Service
public class VendorMasterService {
    @Autowired
    private VendorMasterMapper chemicalVendorMasterMapper;

    /**
     * 업체 조회
     * 
     * @param originCd
     *            원산지
     * @param vendorNm
     *            업체명
     * @param vendorTypeCd
     *            업체분류
     * @param useYn
     *            사용여부
     * @return 업체 목록
     * @throws Exception
     */
    public List<ChemicalVendorMaster> getChemicalVendorMasters(String plantCd, String vendorNm, String vendorTypeCd, String vendorAttCd, String useYn, Integer pageNumber, Integer pageSize, String orderByExpression, String authYn, String addYn, DefaultParam defaultParam) throws Exception {
        return chemicalVendorMasterMapper.getChemicalVendorMasters(plantCd, vendorNm, vendorTypeCd, vendorAttCd, useYn, pageNumber, pageSize, orderByExpression, authYn, addYn, defaultParam);
    }

    /**
     * 업체 상세 조회
     * 
     * @param vendorCd
     *            업체코드
     * @return 업체
     * @throws Exception
     */
    public ChemicalVendorMaster getChemicalVendorMaster(String vendorCd, DefaultParam defaultParam) throws Exception {
        ChemicalVendorMaster chemicalVendorMaster = this.chemicalVendorMasterMapper.getChemicalVendorMaster(vendorCd, defaultParam);

        List<Map<String, String>> plantData = this.chemicalVendorMasterMapper.getChemicalVendorMasterPlants(vendorCd);

        List<String> plants = new ArrayList<>();
        for (int i = 0; i < plantData.size(); i++) {
            plants.add(plantData.get(i).get("plantCd"));
        }
        chemicalVendorMaster.setPlantCds(plants);
        return chemicalVendorMaster;
    }

    /**
     * 업체 근무자 조회
     * 
     * @param vendorCd
     *            업체코드
     * @param workerNm
     *            작업자성명
     * @return 업체
     * @throws Exception
     */
    public List<VendorWorker> getChemicalVendorMasterWorkers(String vendorCd, String workerNm, String plantCd, String vendorTypeCd, String vendorAttCd, String vendorNm, DefaultParam defaultParam) throws Exception {
        return this.chemicalVendorMasterMapper.getChemicalVendorMasterWorkers(vendorCd, workerNm, plantCd, vendorTypeCd, vendorAttCd, vendorNm, defaultParam);
    }

    /**
     * 업체 신규등록
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 업체번호
     * @throws Exception
     */
    @Transactional
    public String createChemicalVendorMaster(ChemicalVendorMaster chemicalVendorMaster) throws Exception {
        this.chemicalVendorMasterMapper.createChemicalVendorMaster(chemicalVendorMaster);
        Map<String, String> plants = new HashMap<String, String>();
        for (int i = 0; i < chemicalVendorMaster.getPlantCds().size(); i++) {
            plants.clear();
            plants.put("vendorCd", chemicalVendorMaster.getVendorCd());
            plants.put("plantCd", chemicalVendorMaster.getPlantCds().get(i));
            this.chemicalVendorMasterMapper.createChemicalVendorMasterPlants(plants);
        }
        for (int i = 0; i < chemicalVendorMaster.getWorkers().size(); i++) {
            VendorWorker worker = chemicalVendorMaster.getWorkers().get(i);
            worker.setVendorCd(chemicalVendorMaster.getVendorCd());
            this.chemicalVendorMasterMapper.createChemicalVendorMasterWorkers(worker);
        }

        return chemicalVendorMaster.getVendorCd();
    }

    /**
     * 업체 수정
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalVendorMaster(ChemicalVendorMaster chemicalVendorMaster) throws Exception {
        String vendorCd = chemicalVendorMaster.getVendorCd();

        this.chemicalVendorMasterMapper.deleteChemicalVendorMasterPlants(vendorCd);
        this.chemicalVendorMasterMapper.deleteChemicalVendorMasterWorkers(vendorCd);

        Map<String, String> plants = new HashMap<String, String>();

        for (int i = 0; i < chemicalVendorMaster.getPlantCds().size(); i++) {
            plants.clear();
            plants.put("vendorCd", chemicalVendorMaster.getVendorCd());
            plants.put("plantCd", chemicalVendorMaster.getPlantCds().get(i));
            this.chemicalVendorMasterMapper.createChemicalVendorMasterPlants(plants);
        }
        if (chemicalVendorMaster.getWorkers() != null) {
            for (int i = 0; i < chemicalVendorMaster.getWorkers().size(); i++) {
                VendorWorker worker = chemicalVendorMaster.getWorkers().get(i);
                worker.setVendorCd(chemicalVendorMaster.getVendorCd());
                this.chemicalVendorMasterMapper.createChemicalVendorMasterWorkers(worker);
            }
        }

        return this.chemicalVendorMasterMapper.updateChemicalVendorMaster(chemicalVendorMaster);
    }

    /**
     * 포털 비밀번호 초기화
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalVendorMasterPwdReset(ChemicalVendorMaster chemicalVendorMaster) throws Exception {
        return this.chemicalVendorMasterMapper.updateChemicalVendorMasterPwdReset(chemicalVendorMaster);
    }

    /**
     * 업체명 체크
     * 
     * @param vendorNm
     *            업체명
     * @param vendorCdOrigin
     *            업체코드 (저장된)
     * @param vendorCd
     *            업체코드 (수정할)
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalVendorMaster(String vendorNm, String vendorCdOrigin, String vendorCd) throws Exception {
        return chemicalVendorMasterMapper.getCheckChemicalVendorMaster(vendorNm, vendorCdOrigin, vendorCd);
    }

    /**
     * 협력업체 상세 정보 조회
     * 
     * @param vendorCd
     * @return
     * @throws Exception
     */
    public ChemicalVendorMaster getVendorInfoDetail(String vendorCd, DefaultParam defaultParam) throws Exception {
        ChemicalVendorMaster chemicalVendorMaster = this.chemicalVendorMasterMapper.getChemicalVendorMaster(vendorCd, defaultParam);
        if (chemicalVendorMaster != null) {
            chemicalVendorMaster.setWorkers(this.chemicalVendorMasterMapper.getChemicalVendorMasterWorkers(vendorCd, "", "", "", "", "", defaultParam));
        }
        return chemicalVendorMaster;
    }

    /**
     * 협력업체 정보 수정
     * 
     * @param chemicalVendorMaster
     * @return
     * @throws Exception
     */
    public int updateVendorInfo(ChemicalVendorMaster chemicalVendorMaster) throws Exception {
        if (chemicalVendorMaster == null) {
            return 0;
        } else {
            int result = 0;
            result += this.chemicalVendorMasterMapper.updateVendorMasterInfo(chemicalVendorMaster);
            if (StringUtils.isNotBlank(chemicalVendorMaster.getPortalPwd())) {
                // 비밀번호 변경 처리
                String portalPwd = DigestUtils.sha256Hex(chemicalVendorMaster.getPortalPwd()); // 암호화
                chemicalVendorMaster.setPortalPwd(portalPwd);
                result += this.chemicalVendorMasterMapper.updateChemicalVendorMasterPwdReset(chemicalVendorMaster);
            }
            if (CollectionUtils.isNotEmpty(chemicalVendorMaster.getWorkers())) {
                // 근무자 삭제
                this.chemicalVendorMasterMapper.deleteChemicalVendorMasterWorkers(chemicalVendorMaster.getVendorCd());
                // 근무자 등록
                for (VendorWorker vendorWorker : chemicalVendorMaster.getWorkers()) {
                    vendorWorker.setVendorCd(chemicalVendorMaster.getVendorCd());
                    result += this.chemicalVendorMasterMapper.createChemicalVendorMasterWorkers(vendorWorker);
                }
            }
            return result;
        }
    }

    /**
     * 포탈아이디 중복 체크
     *
     * @param portalId
     *            포탈아이디
     * @return 체크 값
     * @throws Exception
     */
    public Integer getCheckPortalId(String portalId) throws Exception {
        return chemicalVendorMasterMapper.getCheckPortalId(portalId);
    }

    /**
     * 사업자번호 중복 체크
     *
     * @param bizNum
     *            사업자번호
     * @return 체크 값
     * @throws Exception
     */
    public Integer getCheckBizNum(String bizNum) throws Exception {
        return chemicalVendorMasterMapper.getCheckBizNum(bizNum);
    }

    /**
     * 협력사 가입요청
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 업체번호
     * @throws Exception
     */
    @Transactional
    public String getJoinRequest(String vendorCd, String email) throws Exception {

        Random random = new Random(); // 랜덤 객체 생성
        String rst = "";
        String[] strNumber = new String[6];

        // 반복문을 사용 6개의 난수 생성
        for (int i = 0; i < 6; i++) {
            int temp;
            temp = random.nextInt(9) + 1;
            strNumber[i] = Integer.toString(temp);
            rst += temp;
        }

        String cnt = chemicalVendorMasterMapper.getAuthNumber(rst);
        while (!cnt.equals("0")) {
            int temp;
            rst = "";
            // 반복문을 사용 6개의 난수 생성
            for (int i = 0; i < 6; i++) {
                temp = random.nextInt(9) + 1;
                strNumber[i] = Integer.toString(temp);
                rst += temp;
            }
            cnt = chemicalVendorMasterMapper.getAuthNumber(rst);
            if (cnt.equals("0")) {
                break;
            }
        }

        if ("0".equals(cnt)) {
            ChemicalVendorMaster chemicalVendorMaster = new ChemicalVendorMaster();
            chemicalVendorMaster.setVendorCd(vendorCd);
            chemicalVendorMaster.setAuthNumber(rst);
            chemicalVendorMasterMapper.updateAuthNumber(chemicalVendorMaster);
        }

        MailVo mail = new MailVo();
        String[] recipients = new String[1];
        recipients[0] = email;
        mail.setTitle("협력업체 가입요청 인증번호");
        mail.setMailTitle("가입요청 인증번호");
        mail.setRecipientsEmailAddress(recipients);
        mail.setSenderEmail(email);

        String contents = "";
        contents += "<div style='display: flex; justify-content: center'>";
        contents += "   <div style='border: 1px solid #48BAE4; height: 100px; width: 100px; font-size: 5.0em; text-align: center;'>" + strNumber[0] + "</div>";
        contents += "   <div style='border: 1px solid #48BAE4; height: 100px; width: 100px; font-size: 5.0em; text-align: center;'>" + strNumber[1] + "</div>";
        contents += "   <div style='border: 1px solid #48BAE4; height: 100px; width: 100px; font-size: 5.0em; text-align: center;'>" + strNumber[2] + "</div>";
        contents += "   <div style='border: 1px solid #48BAE4; height: 100px; width: 100px; font-size: 5.0em; text-align: center;'>" + strNumber[3] + "</div>";
        contents += "   <div style='border: 1px solid #48BAE4; height: 100px; width: 100px; font-size: 5.0em; text-align: center;'>" + strNumber[4] + "</div>";
        contents += "   <div style='border: 1px solid #48BAE4; height: 100px; width: 100px; font-size: 5.0em; text-align: center;'>" + strNumber[5] + "</div>";
        contents += "</div>";

        mail.setContents(contents);
        SendMailUtil.sendMail(mail);

        return rst;
    }
    
    /**
     * 인증번호 체크
     * @param authNumber
     * @return
     * @throws Exception
     */
    public ChemicalVendorMaster getVendorAuth(String authNumber) throws Exception{ return chemicalVendorMasterMapper.getVendorAuth(authNumber);}
    
    /**
     * 협력사 ID/PWD 등록
     * @param chemicalVendorMaster
     * @return
     * @throws Exception
     */
    public int updateVendorMasterIdPwd(ChemicalVendorMaster chemicalVendorMaster) throws Exception{
    	if (chemicalVendorMaster == null) {
            return 0;
        } else {
        	int result = 0;
        	if (StringUtils.isNotBlank(chemicalVendorMaster.getPortalPwd())) {
                String portalPwd = DigestUtils.sha256Hex(chemicalVendorMaster.getPortalPwd()); // 암호화
                chemicalVendorMaster.setPortalPwd(portalPwd);
                result += this.chemicalVendorMasterMapper.updateVendorMasterIdPwd(chemicalVendorMaster);
                
                if(result > 0) {
                	// ID/PWD등록후 인증번호 지우기
                	chemicalVendorMaster.setAuthNumber("");
                	result += chemicalVendorMasterMapper.updateAuthNumber(chemicalVendorMaster);
                }
            }
        	return result; 
        }
    }
}
