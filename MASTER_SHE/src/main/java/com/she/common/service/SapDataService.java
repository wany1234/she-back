package com.she.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.wholeProcess.mapper.MatCheckRequestMapper;
import com.she.common.mapper.SapDataMapper;
import com.she.common.model.LawElaw;
import com.she.utils.JcoUtil;

@Service
public class SapDataService {

    @Autowired
    private SapDataMapper sapDataMapper;
    @Autowired
    private MatCheckRequestMapper matCheckRequestMapper;

    /**
     * SAP 거래처 정보 등록
     * 
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapVendorData() throws Exception {
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_VENDOR", "ET_VEN");
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapVendorData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 자재마스터 등록
     * 
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapMatData() throws Exception {
        List<Map<String, Object>> sapDataList = new ArrayList<Map<String, Object>>(); // JcoUtil.getSapTableData("ZSHE_INTF_MAT",
        // "ET_MAT");
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("MATNR", "66309600103"); // 자재코드
        m.put("MAKTX", "L.N.G 용구손료 : BOILER"); // 자재명 한
        m.put("MAKTG", "L.N.G 용구손료 : BOILER"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "66309600118"); // 자재코드
        m.put("MAKTX", "L.N.G 용구손료 : 현장용"); // 자재명 한
        m.put("MAKTG", "L.N.G 용구손료 : 현장용"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "68305100103"); // 자재코드
        m.put("MAKTX", "L.N.G : 10500KCAL/HR"); // 자재명 한
        m.put("MAKTG", "L.N.G : 10500KCAL/HR"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91355320023"); // 자재코드
        m.put("MAKTX", "PROPANE GAS : 액화"); // 자재명 한
        m.put("MAKTG", "PROPANE GAS : 액화"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91400910073"); // 자재코드
        m.put("MAKTX", "BUNKER-C : 저유황 0.5%"); // 자재명 한
        m.put("MAKTG", "BUNKER-C : 저유황 0.5%"); // 자재명 영
        m.put("LVORM", "N"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91400910088"); // 자재코드
        m.put("MAKTX", "BUNKER-C : 저유황 0.3%"); // 자재명 한
        m.put("MAKTG", "BUNKER-C : 저유황 0.3%"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91401200016"); // 자재코드
        m.put("MAKTX", "보일러 등유 : 황함량 0.1% 이하"); // 자재명 한
        m.put("MAKTG", "보일러 등유 : 황함량 0.1% 이하"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91401210028"); // 자재코드
        m.put("MAKTX", "DIESEL : BIO DIESEL BCM-601"); // 자재명 한
        m.put("MAKTG", "DIESEL : BIO DIESEL BCM-601"); // 자재명 영
        m.put("LVORM", "N"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91401210029"); // 자재코드
        m.put("MAKTX", "DIESEL : 저유황 0.05%"); // 자재명 한
        m.put("MAKTG", "DIESEL : 저유황 0.05%"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91404410015"); // 자재코드
        m.put("MAKTX", "L.N.G : 10500KCAL/M3"); // 자재명 한
        m.put("MAKTG", "L.N.G : 10500KCAL/M3"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91404410016"); // 자재코드
        m.put("MAKTX", "LNG GAS : 10500KCAL/M3"); // 자재명 한
        m.put("MAKTG", "LNG GAS : 10500KCAL/M3"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91406840023"); // 자재코드
        m.put("MAKTX", "STEAM : 4KG/CM2G"); // 자재명 한
        m.put("MAKTG", "STEAM : 4KG/CM2G"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        m = new HashMap<String, Object>();
        m.put("MATNR", "91406840024"); // 자재코드
        m.put("MAKTX", "STEAM : 5KG/CM2G"); // 자재명 한
        m.put("MAKTG", "STEAM : 5KG/CM2G"); // 자재명 영
        m.put("LVORM", "Y"); // 사용유무
        m.put("MATKL", "G10"); // 자재그룹코드
        m.put("MTART", "ERSA"); // 자재유형
        m.put("ERSDA", ""); // 자재생성일
        m.put("MEINS", "ZSE"); // 기본단위
        sapDataList.add(m);
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapMatData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 근태정보 등록
     * 
     * @param yyyymm
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapWorkInfoData(String yyyymm) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_ZYYMM", yyyymm);
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WORK", "ET_WORK", params);

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWorkInfoData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 부서월별 무재해정보 등록
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertNacdInfoData(String startDate, String endDate) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_ZWKDTF", startDate);
        params.put("I_ZWKDTT", endDate);
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_NACD", "ET_NACD", params);

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapNacdInfoData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 자재검토요청 등록
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertMatChkRqstData(String startDate, String endDate) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_ERSDAF", startDate);
        params.put("I_ERSDAT", endDate);
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_MSDS", "ET_MAT", params);

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                int matChkRqstNo = matCheckRequestMapper.getChmMatChkRqstSeq();
                param.put("matChkRqstNo", matChkRqstNo);
                result += sapDataMapper.mergeSapMatChkRqstData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 입고시설(저장시설) 등록
     * 
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertMatStrgData() throws Exception {
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_LGORT", "ET_LGORT");

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapMatStrgData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * 화학물질실적 월 정보 등록
     * 
     * @param yyyymm
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapMatInoutData(String yyyymm) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_SPMON", yyyymm);
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_CHEMICAL_STOC", "ET_STOC", params);

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            sapDataMapper.deleteSapMatInoutData(yyyymm);

            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.insertSapMatInoutData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 화학물질식적 일 정보 등록
     * 
     * @param yyyymm
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapMatStrgStocData(String yyyymm) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_SPMON", yyyymm);
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_CHEMICAL_STOC", "ET_STOC", params);

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapMatStrgStocData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 수질분석결과 등록
     * 
     * @param reqDate
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapWtrMeasInfoData(String reqDate) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_ZMDATE", reqDate);
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WQMEASURE", "ET_MEASURE", params);

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWtrMeasInfoData(param);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 대기방지시설 등록
     * 
     * @param reqDate
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapEairInfoData(String reqDate) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_ZMDATE", reqDate);
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_AIRPREV", "ITAB", params);

        if (CollectionUtils.isNotEmpty(sapDataList)) {
            int result = 0;
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapEairInfoData(param);
            }

            // 대기운영일지 프로시져 호출
            if (result > 0) {
                // 대기운영일지 작성일자 프로시져
                sapDataMapper.intfAirPrevProcedure1(reqDate);
                // 대기운영일지 배출시설 가동시간 프로시져
                sapDataMapper.intfAirPrevProcedure2(reqDate);
                // 대기운영일지 방지시설 전력사용량 프로시져
                sapDataMapper.intfAirPrevProcedure3(reqDate);
                // 대기운영일지 배출시설 연료사용량 프로시져
                sapDataMapper.intfAirPrevProcedure4(reqDate);
                // 대기운영일지 원료사용량 프로시져
                sapDataMapper.intfAirPrevProcedure5(reqDate);
            }
            return result;
        } else {
            return 0;
        }
    }

    /**
     * SAP 수질용수량 등록
     * 
     * @param reqDate
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertSapWasteWaterData(String reqDate) throws Exception {
        int result = 0;
        HashMap<String, Object> params = new HashMap<>();
        params.put("I_ZMDATE", reqDate);

        // SAP 폐수처리시설 관리(Main) 등록
        List<Map<String, Object>> sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB0", params, true);
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWtrLog400Data(param);
            }
        }

        // SAP 폐수처리시설 관리(용수) 등록
        sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB1", params);
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWtrSupl401Data(param);
            }
        }

        // SAP 폐수처리시설 관리(폐수) 등록
        sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB2", params);
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWtrDisch402Data(param);
            }
        }

        // SAP 폐수처리시설 관리(전력) 등록
        sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB3", params);
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWtrPwr403Data(param);
            }
        }

        // SAP 폐수처리시설 관리(약품) 등록
        sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB4", params);
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWtrChem404Data(param);
            }
        }

        // SAP 폐수처리시설 관리(슬러지) 등록
        sapDataList = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB5", params);
        if (CollectionUtils.isNotEmpty(sapDataList)) {
            for (Map<String, Object> param : sapDataList) {
                result += sapDataMapper.mergeSapWtrSlu405Data(param);
            }
        }

        // 수질운영일지 관련 프로시져 호출
        if (result > 0) {
            // 수질운영일지 메인정보-날씨,온도,근무자 프로시져
            sapDataMapper.intfWasteWaterProcedure1(reqDate);
            // 수질운영일지 용수사용량, 폐수배출량(일부) 프로시져
            sapDataMapper.intfWasteWaterProcedure2(reqDate);
            // 수질운영일지 폐수배출량 프로시져
            sapDataMapper.intfWasteWaterProcedure3(reqDate);
            // 수질운영일지 전력사용량 프로시져
            sapDataMapper.intfWasteWaterProcedure4(reqDate);
            // 수질운영일지 약품사용량 프로시져
            sapDataMapper.intfWasteWaterProcedure5(reqDate);
            // 수질운영일지 슬러지처리량 프로시져
            sapDataMapper.intfWasteWaterProcedure6(reqDate);
            // 수질운영일지 배출시설가동시간 프로시져
            sapDataMapper.intfWasteWaterProcedure7(reqDate);
            // 수질운영일지 방지시설가동시간 프로시져
            sapDataMapper.intfWasteWaterProcedure8(reqDate);
            // 수질운영일지 자가측정 분석결과 프로시져
            sapDataMapper.intfWasteWaterProcedure9(reqDate);
        }

        return result;
    }

    /**
     * 개정법규 I/F 저장
     *
     * @param lawElaw
     *            개정법규
     * @return 변경행수
     * @throws Exception
     *             예외
     */
    public int mergeLawIF(LawElaw lawElaw) throws Exception {
        return sapDataMapper.mergeLawIF(lawElaw);
    }
}
