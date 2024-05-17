package com.she.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "globals")
public class GlobalSettings {
    // 이미지 없는 경우 표시할 기본 이미지 경로 (소스의 resources/public/아래 )
    private String noImageFilePath;
    // 로고 이미지
    private String logoImageFilePath;
    // 루트 파일저장 경로
    private String fileStorePath;
    // MSDS 경고표시 라벨 파일
    private String reportChmMsdsWarnSign;
    // MSDS 경고표시 라벨 파일
    private String reportChmMsdsWarnSignDir;
    // MSDS 공정관리요령 파일
    private String reportChmProcessMng;
    // MSDS 공정관리요령 파일 경로
    private String reportChmProcessMngDir;
    // MSDS 그림문자 이미지 파일 경로
    private String reportChmMsdsImages;
    // 작업허가서 출력 파일
    private String reportWkod;
    // 작업허가서 파일 경로
    private String reportWkodDir;
    // 대기 운영일지 출력 파일
    private String reportEnvEair;
    // 대기 운영일지 파일 경로
    private String reportEnvEairDir;
    // 수질 운영일지 출력 파일
    private String reportEnvEwtrOplog;
    // 수질 운영일지 파일 경로
    private String reportEnvEwtrOplogDir;
    // 수질 자가측정 출력 파일
    private String reportEnvEwtrSelfMeasurement;
    // 수질 자가측정 파일 경로
    private String reportEnvEwtrSelfMeasurementDir;
    // 일일폐수량 출력 파일
    private String reportEnvEwtrWasteDayCk;
    // 일일폐수량 파일 경로
    private String reportEnvEwtrWasteDayCkDir;
    // 약품사용량 출력 파일
    private String reportEnvEwtrChem;
    // 약품사용량 파일 경로
    private String reportEnvEwtrChemDir;
    // 개선사항결과 출력 파일
    private String reportImprActResult;
    // 안전사고보고서 출력 파일
    private String reportAccident;
    // PSM감사결과 보고서
    private String selfAuditResult;
    // PSM 선임감사원
    private String reportAuditResultDir;
    // 직무스트레스검사 파일 위치
    private String reportStressPlanDir;
    // 직무스트레스검사 출력 파일
    private String reportStressPlan;
    // 유해,위험물질 출력 파일
    private String reportRiskHazard;
    // 유해,위험물질 파일 위치
    private String reportRiskHazardDir;
    // 가동전 점검 디렉토리
    private String preStartCheckDir;
    // 가동전 점검
    private String preStartCheck;
    // 가동전 점검 보고서
    private String preStartCheckReport;

    /**
     * 공용 메일주소
     */
    private String sendMailEm;

    /**
     * 공용 메일발송자명
     */
    private String sendMailNm;

    public String getLogoImageFilePath() {
        return logoImageFilePath;
    }

    public void setLogoImageFilePath(String logoImageFilePath) {
        this.logoImageFilePath = logoImageFilePath;
    }

    public String getReportImprActResult() {
        return reportImprActResult;
    }

    public void setReportImprActResult(String reportImprActResult) {
        this.reportImprActResult = reportImprActResult;
    }

    public String getReportEnvEwtrSelfMeasurement() {
        return reportEnvEwtrSelfMeasurement;
    }

    public void setReportEnvEwtrSelfMeasurement(String reportEnvEwtrSelfMeasurement) {
        this.reportEnvEwtrSelfMeasurement = reportEnvEwtrSelfMeasurement;
    }

    public String getReportEnvEwtrSelfMeasurementDir() {
        return reportEnvEwtrSelfMeasurementDir;
    }

    public void setReportEnvEwtrSelfMeasurementDir(String reportEnvEwtrSelfMeasurementDir) {
        this.reportEnvEwtrSelfMeasurementDir = reportEnvEwtrSelfMeasurementDir;
    }

    public String getReportEnvEwtrWasteDayCk() {
        return reportEnvEwtrWasteDayCk;
    }

    public void setReportEnvEwtrWasteDayCk(String reportEnvEwtrWasteDayCk) {
        this.reportEnvEwtrWasteDayCk = reportEnvEwtrWasteDayCk;
    }

    public String getReportEnvEwtrWasteDayCkDir() {
        return reportEnvEwtrWasteDayCkDir;
    }

    public void setReportEnvEwtrWasteDayCkDir(String reportEnvEwtrWasteDayCkDir) {
        this.reportEnvEwtrWasteDayCkDir = reportEnvEwtrWasteDayCkDir;
    }

    public String getReportEnvEwtrChem() {
        return reportEnvEwtrChem;
    }

    public void setReportEnvEwtrChem(String reportEnvEwtrChem) {
        this.reportEnvEwtrChem = reportEnvEwtrChem;
    }

    public String getReportEnvEwtrChemDir() {
        return reportEnvEwtrChemDir;
    }

    public void setReportEnvEwtrChemDir(String reportEnvEwtrChemDir) {
        this.reportEnvEwtrChemDir = reportEnvEwtrChemDir;
    }

    public String getReportEnvEwtrOplog() {
        return reportEnvEwtrOplog;
    }

    public void setReportEnvEwtrOplog(String reportEnvEwtrOplog) {
        this.reportEnvEwtrOplog = reportEnvEwtrOplog;
    }

    public String getReportEnvEwtrOplogDir() {
        return reportEnvEwtrOplogDir;
    }

    public void setReportEnvEwtrOplogDir(String reportEnvEwtrOplogDir) {
        this.reportEnvEwtrOplogDir = reportEnvEwtrOplogDir;
    }

    public String getReportEnvEair() {
        return reportEnvEair;
    }

    public void setReportEnvEair(String reportEnvEair) {
        this.reportEnvEair = reportEnvEair;
    }

    public String getReportEnvEairDir() {
        return reportEnvEairDir;
    }

    public void setReportEnvEairDir(String reportEnvEairDir) {
        this.reportEnvEairDir = reportEnvEairDir;
    }

    public String getReportWkod() {
        return reportWkod;
    }

    public void setReportWkod(String reportWkod) {
        this.reportWkod = reportWkod;
    }

    public String getReportWkodDir() {
        return reportWkodDir;
    }

    public void setReportWkodDir(String reportWkodDir) {
        this.reportWkodDir = reportWkodDir;
    }

    public String getNoImageFilePath() {
        return noImageFilePath;
    }

    public void setNoImageFilePath(String noImageFilePath) {
        this.noImageFilePath = noImageFilePath;
    }

    public String getFileStorePath() {
        return fileStorePath;
    }

    public void setFileStorePath(String fileStorePath) {
        this.fileStorePath = fileStorePath;
    }

    public String getReportChmMsdsWarnSign() {
        return reportChmMsdsWarnSign;
    }

    public void setReportChmMsdsWarnSign(String reportChmMsdsWarnSign) {
        this.reportChmMsdsWarnSign = reportChmMsdsWarnSign;
    }

    public String getReportChmMsdsWarnSignDir() {
        return reportChmMsdsWarnSignDir;
    }

    public void setReportChmMsdsWarnSignDir(String reportChmMsdsWarnSignDir) {
        this.reportChmMsdsWarnSignDir = reportChmMsdsWarnSignDir;
    }

    public String getReportChmProcessMng() {
        return reportChmProcessMng;
    }

    public void setReportChmProcessMng(String reportChmProcessMng) {
        this.reportChmProcessMng = reportChmProcessMng;
    }

    public String getReportChmProcessMngDir() {
        return reportChmProcessMngDir;
    }

    public void setReportChmProcessMngDir(String reportChmProcessMngDir) {
        this.reportChmProcessMngDir = reportChmProcessMngDir;
    }

    public String getReportChmMsdsImages() {
        return reportChmMsdsImages;
    }

    public void setReportChmMsdsImages(String reportChmMsdsImages) {
        this.reportChmMsdsImages = reportChmMsdsImages;
    }

    public String getReportAccident() {
        return reportAccident;
    }

    public void setReportAccident(String reportAccident) {
        this.reportAccident = reportAccident;
    }

    public String getSendMailEm() {
        return sendMailEm;
    }

    public void setSendMailEm(String sendMailEm) {
        this.sendMailEm = sendMailEm;
    }

    public String getSendMailNm() {
        return sendMailNm;
    }

    public void setSendMailNm(String sendMailNm) {
        this.sendMailNm = sendMailNm;
    }

    public String getSelfAuditResult() {
        return selfAuditResult;
    }

    public void setSelfAuditResult(String selfAuditResult) {
        this.selfAuditResult = selfAuditResult;
    }

    public String getReportAuditResultDir() {
        return reportAuditResultDir;
    }

    public void setReportAuditResultDir(String reportAuditResultDir) {
        this.reportAuditResultDir = reportAuditResultDir;
    }

    public String getReportStressPlanDir() {
        return reportStressPlanDir;
    }

    public void setReportStressPlanDir(String reportStressPlanDir) {
        this.reportStressPlanDir = reportStressPlanDir;
    }

    public String getReportStressPlan() {
        return reportStressPlan;
    }

    public void setReportStressPlan(String reportStressPlan) {
        this.reportStressPlan = reportStressPlan;
    }

    public String getReportRiskHazard() {
        return reportRiskHazard;
    }

    public void setReportRiskHazard(String reportRiskHazard) {
        this.reportRiskHazard = reportRiskHazard;
    }

    public String getReportRiskHazardDir() {
        return reportRiskHazardDir;
    }

    public void setReportRiskHazardDir(String reportRiskHazardDir) {
        this.reportRiskHazardDir = reportRiskHazardDir;
    }

    public String getPreStartCheckDir() {
        return preStartCheckDir;
    }

    public void setPreStartCheckDir(String preStartCheckDir) {
        this.preStartCheckDir = preStartCheckDir;
    }

    public String getPreStartCheck() {
        return preStartCheck;
    }

    public void setPreStartCheck(String preStartCheck) {
        this.preStartCheck = preStartCheck;
    }

    public String getPreStartCheckReport() {
        return preStartCheckReport;
    }

    public void setPreStartCheckReport(String preStartCheckReport) {
        this.preStartCheckReport = preStartCheckReport;
    }
}
