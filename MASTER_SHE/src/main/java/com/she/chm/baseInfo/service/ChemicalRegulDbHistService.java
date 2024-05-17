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
package com.she.chm.baseInfo.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.she.chm.baseInfo.mapper.ChemicalRegulDbHistMapper;
import com.she.chm.baseInfo.mapper.ChemicalRegulDbMapper;
import com.she.chm.model.ChemicalRegulDb;
import com.she.chm.model.ChemicalRegulDbHist;
import com.she.chm.model.ChemicalRegulDbValid;
import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.service.AttachFileService;
import com.she.file.service.FileStorageService;
import com.she.manage.model.User;
import com.she.manage.service.UserService;
import com.she.utils.ConstVal.ChmRegulDbStatus;
import com.she.utils.SendMailUtil;
import com.she.utils.model.MailVo;

@Service
public class ChemicalRegulDbHistService {

	private static final String REGUL_DB_TASK_CLASS = "CHM_REGUL_DB_HIST";

	private static Logger logger = LoggerFactory.getLogger(ChemicalRegulDbHistService.class);

	@Autowired
	private ChemicalRegulDbHistMapper chemicalRegulDbHistMapper;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private ChemicalRegulDbService chemicalRegulDbService;

	@Autowired
	private ChemicalRegulDbMapper chemicalRegulDbMapper;

	@Autowired
	private ChemicalRegulDbHistService chemicalRegulDbHistService;

	@Autowired
	private AttachFileService attachFileService;

	@Autowired
	private UserService userService;

	/**
	 * 규제DB업로드 이력조회
	 *
	 * @return
	 * @throws Exception
	 */
	public List<ChemicalRegulDbHist> getChemicalRegulDbHists() throws Exception {
		return chemicalRegulDbHistMapper.getChemicalRegulDbHists();
	}

	/**
	 * 규제DB업로드 이력 상세조회
	 *
	 * @param chmRegulDbCd : 규제DB업로드코드
	 * @return
	 * @throws Exception
	 */
	public ChemicalRegulDbHist getChemicalRegulDbHist(String chmRegulDbCd) throws Exception {
		return this.chemicalRegulDbHistMapper.getChemicalRegulDbHist(chmRegulDbCd);
	}

	/**
	 * 규제DB 업로드 이력
	 *
	 * @param chemicalRegulDbHist
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ChemicalRegulDbHist createChemicalRegulDbHist(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String todate = formatter.format(new Date());
		logger.info(":::: 코드 : " + todate);

		chemicalRegulDbHist.setChmRegulDbCd(todate);
		this.chemicalRegulDbHistMapper.createChemicalRegulDbHist(chemicalRegulDbHist);
		return chemicalRegulDbHist;
	}

	/**
	 * 규제DB상태 업데이트
	 *
	 * @param chemicalRegulDbHist
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ChemicalRegulDbHist changeChemicalRegulDbHistStatus(ChemicalRegulDbHist chemicalRegulDbHist)
			throws Exception {
		this.chemicalRegulDbHistMapper.changeChemicalRegulDbHistStatus(chemicalRegulDbHist);
		return chemicalRegulDbHist;
	}

	/**
	 * 규제DB 적용처리
	 *
	 * @param chemicalRegulDbHist
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ChemicalRegulDbHist applyChemicalRegulDbHist(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception {
		boolean result = false;
		try {
			this.chemicalRegulDbMapper.executeIfRegulDbToChemUpdate(chemicalRegulDbHist.getChmRegulDbCd());
			chemicalRegulDbHist.setUploadStatus(ChmRegulDbStatus.APPLY_SUCCESS.code);
			this.chemicalRegulDbHistMapper.applyChemicalRegulDbHist(chemicalRegulDbHist);
			result = true;
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			chemicalRegulDbHist.setUploadStatus(ChmRegulDbStatus.APPLY_ERR.code);
			chemicalRegulDbHist.setLastMsg("규제db 적용시 오류가 발생했습니다.");
			this.chemicalRegulDbHistMapper.updateMsgChemicalRegulDbHist(chemicalRegulDbHist);
			result = false;
		}

		// 오류가 발생하지 않고 잘 적용이 된 경우에 메일을 보낸다.
		if (result) {
			List<User> users = userService.getChmMaliReceiveUserInfo("");

			// String[] recipients = { "dhkim@yullin.com" };
			String[] recipients = new String[users.size()];
			if (users != null && users.size() > 0) {
				for (int i = 0; i < users.size(); i++) {
					recipients[i] = users.get(i).getEmail();
				}

				MailVo mail = new MailVo();
				mail.setTitle("[SHE시스템] 화학물질 규제DB 변경안내");
				mail.setRecipientsEmailAddress(recipients);

				String contents = "";
				contents += "[SHE시스템] 화학물질 규제DB 변경안내<br/><br/><br/>";
				contents += "안전보건환경 시스템에서 알려 드립니다.<br/><br/>";
				contents += "화학물질 규제DB가 변경되어 규제영향자재 리포트가 생성되었습니다.<br/>";
				contents += "확인하여 후속 업무 진행 바랍니다.<br/><br/>";
				contents += "세부내용은 안전보건환경 시스템에서 확인하실 수 있습니다";

				mail.setContents(contents);
//                SendMailUtil.sendMail(mail);
			}
		}

		return chemicalRegulDbHist;
	}

	/**
	 * 규제DB 확인처리
	 *
	 * @param chemicalRegulDbHist
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ChemicalRegulDbHist confirmChemicalRegulDbHist(ChemicalRegulDbHist chemicalRegulDbHist) throws Exception {
		this.chemicalRegulDbHistMapper.confirmChemicalRegulDbHist(chemicalRegulDbHist);
		return chemicalRegulDbHist;
	}

	/**
	 * 규제디비 임시 테이블에 업로드
	 *
	 * @param createUserId : 현재 접속한 사용자 아이디
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ChemicalRegulDbHist uploadChemicalRegulDB(String createUserId, MultipartFile[] files) throws Exception {
		logger.debug("::: 1. 하나의 이력을 생성합니다. (상태 : 업로드중) ");
		ChemicalRegulDbHist chemicalRegulDbHist = new ChemicalRegulDbHist();
		chemicalRegulDbHist.setUploadStatus(ChmRegulDbStatus.PREPARE.code);
		chemicalRegulDbHist.setUploadUserId(createUserId);
		chemicalRegulDbHist = this.createChemicalRegulDbHist(chemicalRegulDbHist);

		logger.debug("::: 2. 생성된 이력PK를 이용하여 파일 첨부처리를 합니다.");
		AttachFileGrp attachFileGrp = new AttachFileGrp(REGUL_DB_TASK_CLASS, chemicalRegulDbHist.getChmRegulDbCd(), "",
				createUserId, "", new ArrayList<>(Arrays.asList(files)));
		List<AttachFile> attachFiles = this.attachFileService.uploadFiles(attachFileGrp);

		logger.debug("::: 3. 업로드된 파일을 이용하여 규제임시DB에 정보를 넣습니다. (유효성검사 및 로그처리를 포함)");
		return this.createChemicalRegulDB(chemicalRegulDbHist, attachFiles);
	}

	/**
	 * 규제DB의 정보를 DB테이블에 넣는다.
	 *
	 * @param chmRegulDbCd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	private ChemicalRegulDbHist createChemicalRegulDB(ChemicalRegulDbHist chemicalRegulDbHist,
			List<AttachFile> attachFiles) throws Exception {
		boolean isSuccess = true;

		// 1. chemRegulDBcd에 해당하는 첨부파일정보를 읽어온다.
		AttachFile attachFile = attachFiles.get(0);

		Resource resource = fileStorageService.loadFileAsResource(attachFile.getFilePath());

		FileInputStream fileInputStream = new FileInputStream(resource.getFile());

		try {
			List<ChemicalRegulDb> lists = this.shesChemicalDbReader(chemicalRegulDbHist.getChmRegulDbCd(),
					fileInputStream);
			Integer totCount = lists.size();
			Integer addCount = 0;
			Integer editCount = 0;
			Integer noCount = 0;
			Integer deleteCount = 0;
			for (ChemicalRegulDb chemicalRegulDb : lists) {
				if (chemicalRegulDb.getDbType().shortValue() != 4) {
					if (chemicalRegulDb.getErrMsg() != null && !chemicalRegulDb.getErrMsg().isEmpty()) {
						isSuccess = false;
					}
					chemicalRegulDbService.createChemicalRegulDb(chemicalRegulDb);
				}
				if (chemicalRegulDb.getDbType().shortValue() == 1) {
					addCount += 1;
				} else if (chemicalRegulDb.getDbType().shortValue() == 2) {
					deleteCount += 1;
				} else if (chemicalRegulDb.getDbType().shortValue() == 3) {
					editCount += 1;
				} else {
					noCount += 1;
				}
			}

			if (totCount.intValue() == noCount.intValue()) {
				isSuccess = false;
			}

			// 규제db검증을 실행하여 해당 결과에 오류가 있는 경우 상태를 불가로 변경해 준다.
			String retVal = this.executeIfRegulDbCheckValidUpdate(chemicalRegulDbHist.getChmRegulDbCd());

			if (!"0".equalsIgnoreCase(retVal)) {
				isSuccess = false;
			}

			if (isSuccess) {
				chemicalRegulDbHist.setUploadStatus(ChmRegulDbStatus.PREPARE.UPLOAD_SUCCESS.code);
				chemicalRegulDbHist.setSuccessYn("Y");
			} else {
				chemicalRegulDbHist.setUploadStatus(ChmRegulDbStatus.PREPARE.UPLOAD_ERR.code);
				chemicalRegulDbHist.setSuccessYn("N");
			}
			chemicalRegulDbHist.setTotCount(totCount);
			chemicalRegulDbHist.setAddCount(addCount);
			chemicalRegulDbHist.setEditCount(editCount);
			chemicalRegulDbHist.setDeleteCount(deleteCount);
			chemicalRegulDbHist.setNoCount(noCount);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			fileInputStream.close();
		}

		return chemicalRegulDbHistService.changeChemicalRegulDbHistStatus(chemicalRegulDbHist);
	}

	/**
	 * 업로드된 규제정보의 검증.
	 *
	 * @param chmRegulDbCd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String executeIfRegulDbCheckValidUpdate(String chmRegulDbCd) throws Exception {
		return this.chemicalRegulDbMapper.executeIfRegulDbCheckValidUpdate(chmRegulDbCd);
	}

	/**
	 * 업로드 규제정보 삭제
	 *
	 * @param chmRegulDbCd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String deleteChemicalRegulDbHist(String chmRegulDbCd) throws Exception {
		// 상태가 적용완료된 것은 삭제할 수 없음.

		ChemicalRegulDbHist chemicalRegulDbHist = this.getChemicalRegulDbHist(chmRegulDbCd);
		if ("CRD40".equals(chemicalRegulDbHist.getUploadStatus())
				|| "CRD60".equals(chemicalRegulDbHist.getUploadStatus())) {
			return "apply";
		}

		this.chemicalRegulDbMapper.deleteChemicalRegulDb(chmRegulDbCd);
		this.chemicalRegulDbHistMapper.deleteChemicalRegulDbHist(chmRegulDbCd);

		return "";
	}

	/**
	 * 영향받는 취급자재조회 (적용전)
	 *
	 * @param chmRegulDbCd
	 * @return
	 * @throws Exception
	 */
	public List<ChemicalRegulDbValid> getAffectedProdBefore(@Param("chmRegulDbCd") String chmRegulDbCd)
			throws Exception {
		return this.chemicalRegulDbHistMapper.getAffectedProdBefore(chmRegulDbCd);
	}

	/**
	 * 영향받는 화학물질 조회 (적용전)
	 *
	 * @param chmRegulDbCd
	 * @return
	 * @throws Exception
	 */
	public List<ChemicalRegulDbValid> getAffectedChemBefore(@Param("chmRegulDbCd") String chmRegulDbCd)
			throws Exception {
		return this.chemicalRegulDbHistMapper.getAffectedChemBefore(chmRegulDbCd);
	}

	/**
	 * 적용후 규제DB의 영향받을 자재 정보
	 *
	 * @param chmRegulDbCd
	 * @return
	 * @throws Exception
	 */
	public List<ChemicalRegulDbValid> getAffectedProdAfter(@Param("chmRegulDbCd") String chmRegulDbCd)
			throws Exception {
		return this.chemicalRegulDbHistMapper.getAffectedProdAfter(chmRegulDbCd);
	}

	/**
	 * 적용후 규제DB의 영향받은 화학물질 정보
	 *
	 * @param chmRegulDbCd
	 * @return
	 * @throws Exception
	 */
	public List<ChemicalRegulDbValid> getAffectedChemAfter(@Param("chmRegulDbCd") String chmRegulDbCd)
			throws Exception {
		return this.chemicalRegulDbHistMapper.getAffectedChemAfter(chmRegulDbCd);
	}

	/**
	 * 쉬즈케미칼 규제DB업로드 엑셀파일 읽기
	 *
	 * @param chmRegulDbCd
	 * @param file
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	@SuppressWarnings({ "resource" })
	@Transactional
	private List<ChemicalRegulDb> shesChemicalDbReader(String chmRegulDbCd, FileInputStream file)
			throws NumberFormatException, IOException {
		@SuppressWarnings("unused")
		String sep = " @ ";
		List<ChemicalRegulDb> lists = new ArrayList<ChemicalRegulDb>();

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = null;
		sheet = workbook.getSheetAt(0);
		int startCol = 0;
		int startRow = 1;
		int rows = 0;

		// 컬럼순서
		AtomicInteger count = new AtomicInteger(0);
		int colLawnm = 0 + startCol; // 법규 [법규가 있는지 여부를 검사]
		int colRegulitmnm = count.incrementAndGet() + startCol; // 규제항목명 [규제항목이
																// 있는지 여부를 검사]
		int colDbtype = count.incrementAndGet() + startCol; // 구분자(1:추가, 2:삭제,
															// 3:변경(기준치), 4:변경없음
															// [구분자가
															// 1,2,3,4인지여부검사]
		int colChemid = count.incrementAndGet() + startCol; // Chem ID [누락된
															// CHEMID가 있는지 여부검사]
		int colAdminnum = count.incrementAndGet() + startCol; // 관리자번호 [누락된
																// 관리자번호가 있는지
																// 여부검사]
		int colChmnmen = count.incrementAndGet() + startCol; // 화학물질명 [비어 있는
																// 화학물질명이 있는지 여부검사]
		int colChmnmkr = count.incrementAndGet() + startCol; // 일반명
		int colChmnmsyn = count.incrementAndGet() + startCol; // 이명
		int colCasno = count.incrementAndGet() + startCol; // CAS No
		int colUnno = count.incrementAndGet() + startCol; // UN No
		int colEcno = count.incrementAndGet() + startCol; // EC No
		int colEtcno = count.incrementAndGet() + startCol; // 규제번호/고시번호(기존화학물질)
		int colDetailclass = count.incrementAndGet() + startCol; // 상세분류
		int colNoticedate = count.incrementAndGet() + startCol; // 고시일 [고시일이 날짜
																// 포맷에 맞는지 확인
																// YYYY-MM-DD]
		int colContstddesc = count.incrementAndGet() + startCol; // 함량기준설명
		int colContstdnum = count.incrementAndGet() + startCol; // 함량기준수치 [함량기준
																// 수치가 있는 경우
																// 수치값이 맞는지 여부
																// 확인]
		int colHazardinfo = count.incrementAndGet() + startCol; // 유해성정보
		int colHandleinfo = count.incrementAndGet() + startCol; // 취급정보
		int colFormula = count.incrementAndGet() + startCol; // 분자식/화학식/구조식
		int colEtcrefcont1 = count.incrementAndGet() + startCol; // 기타참고항목1
		int colEtcrefcont2 = count.incrementAndGet() + startCol; // 기타참고항목2
		int colEtcrefcont3 = count.incrementAndGet() + startCol; // 기타참고항목3
		int colEtcrefcont4 = count.incrementAndGet() + startCol; // 기타참고항목4
		int colEtcrefcont5 = count.incrementAndGet() + startCol; // 기타참고항목5
		int colEtcrefcont6 = count.incrementAndGet() + startCol; // 기타참고항목6
		int colEtcrefcont7 = count.incrementAndGet() + startCol; // 기타참고항목7
		int colEtcrefcont8 = count.incrementAndGet() + startCol; // 기타참고항목8
		int colEtcrefcont9 = count.incrementAndGet() + startCol; // 기타참고항목9
		int colEtcrefcont10 = count.incrementAndGet() + startCol; // 기타참고항목10

		for (int j = startRow; j < sheet.getPhysicalNumberOfRows(); j++) {
			if (sheet.getRow(j) == null) {
				break;
			}
			rows = j;
		}

		for (int j = startRow; j < rows + 1; j++) {

			if (sheet.getRow(j) == null) {
				break;
			}
			XSSFRow row = sheet.getRow(j);
			if (row.getCell(colChemid) == null) {
				break;
			}

			ChemicalRegulDb entity = ChemicalRegulDb.builder().chmRegulDbCd(chmRegulDbCd)
					.lawNm(this.getCellVal(row.getCell(colLawnm)))
					.regulItmNm(this.getCellVal(row.getCell(colRegulitmnm)))
					.dbType(this.getCellShortVal(this.getCellVal(row.getCell(colDbtype))))
					.chemId(this.getCellVal(row.getCell(colChemid))).adminNum(this.getCellVal(row.getCell(colAdminnum)))
					.chmNmEn(this.getCellVal(row.getCell(colChmnmen))).chmNmKr(this.getCellVal(row.getCell(colChmnmkr)))
					.chmNmSyn(this.getCellVal(row.getCell(colChmnmsyn))).casNo(this.getCellVal(row.getCell(colCasno)))
					.unNo(this.getCellVal(row.getCell(colUnno))).ecNo(this.getCellVal(row.getCell(colEcno)))
					.etcNo(this.getCellVal(row.getCell(colEtcno)))
					.detailClass(this.getCellVal(row.getCell(colDetailclass)))
					.noticeDate(this.getCellVal(row.getCell(colNoticedate)))
					.contStdDesc(this.getCellVal(row.getCell(colContstddesc)))
					.contStdNum(this.getCellVal(row.getCell(colContstdnum)))
					.hazardInfo(this.getCellVal(row.getCell(colHazardinfo)))
					.handleInfo(this.getCellVal(row.getCell(colHandleinfo)))
					.formula(this.getCellVal(row.getCell(colFormula)))
					.etcRefCont1(this.getCellVal(row.getCell(colEtcrefcont1)))
					.etcRefCont2(this.getCellVal(row.getCell(colEtcrefcont2)))
					.etcRefCont3(this.getCellVal(row.getCell(colEtcrefcont3)))
					.etcRefCont4(this.getCellVal(row.getCell(colEtcrefcont4)))
					.etcRefCont5(this.getCellVal(row.getCell(colEtcrefcont5)))
					.etcRefCont6(this.getCellVal(row.getCell(colEtcrefcont6)))
					.etcRefCont7(this.getCellVal(row.getCell(colEtcrefcont7)))
					.etcRefCont8(this.getCellVal(row.getCell(colEtcrefcont8)))
					.etcRefCont9(this.getCellVal(row.getCell(colEtcrefcont9)))
					.etcRefCont10(this.getCellVal(row.getCell(colEtcrefcont10))).build();

			// 유효성검사를 체크하여, 유효성검사에 맞지 않으면 errMsg 업데이트 처리
			// 한꺼번에 처리할 수 있도록 DB에 INSERT후에 처리하는 방법을 확인하자.

			lists.add(entity);
		}
		return lists;
	}

	private String getCellVal(XSSFCell cell) {
		String value = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if (cell == null) {
			return null;
		}

		switch (cell.getCellType()) {

		case XSSFCell.CELL_TYPE_FORMULA:
			value = "";
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			// value =cell.getNumericCellValue()+"";
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// 숫자- 날짜 타입이다.
				value = formatter.format(cell.getDateCellValue());
			} else {
				// 숫자 타입
				// 정수인지 아닌지 비교해서 정수인경우 Integer로 변환, 아닌경우 값 그대로 전달.
				Integer i = (int) cell.getNumericCellValue();
				if (cell.getNumericCellValue() - i == 0) {
					// 정수
					value = i.toString();
				} else {
					// 소수점
					value = cell.getNumericCellValue() + "";
				}
				// Integer i = (int)cell.getNumericCellValue(); // 리턴타입이 double
				// 임. integer로 형변환 해주고
				// value = i.toString(); // integer 로 형변환된 값을 String 으로 받아서
				// value값에 넣어준다.
			}
			break;
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue() + "";
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		case XSSFCell.CELL_TYPE_ERROR:
			value = "";
			break;
		default:
			value = "";
			break;
		}
		return value;
	}

	public Integer getCellIntVal(String val) {
		if (val == null || val.isEmpty()) {
			return null;
		} else {
			Double d = Double.parseDouble(val);
			return d.intValue();
		}
	}

	public Short getCellShortVal(String val) {
		if (val == null || val.isEmpty()) {
			return null;
		} else {
			Double d = Double.parseDouble(val);
			return d.shortValue();
		}
	}
}
