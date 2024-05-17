package com.she.common.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.AttachFile;
import com.she.common.service.AttachFileService;
import com.she.config.GlobalSettings;
import com.she.file.service.FileStorageService;
import com.she.security.util.StringUtil;
import com.she.utils.FileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/file/down")
@Api(value = "/api/file/down", description = "첨부파일처리")
public class RestAttachFileController {

    private static final Logger logger = LoggerFactory.getLogger(RestAttachFileController.class);

	@Autowired
	private GlobalSettings globalSettings;

	@Autowired
	private AttachFileService attachFileService;

	@Autowired
	private FileStorageService fileStorageService;

	// @RequestMapping(value = "/appdown", method = RequestMethod.GET)
	// public ResponseEntity<Resource> fileDownAppDown(HttpServletRequest
	// request, @RequestParam("fileNo") long fileNo) throws Exception {
	//
	// AttachFile attachFile =
	// attachFileService.getUploadFile(String.valueOf(fileNo));
	// Path path = Paths.get(attachFile.getFilePath());
	// Resource resource = new ByteArrayResource(Files.readAllBytes(path));
	//
	// Map<String, String> mineType = new HashMap<>();
	// mineType.put("pdf", "application/pdf");
	// mineType.put("zip", "application/zip");
	// mineType.put("doc", "application/msword");
	// mineType.put("docx", "application/msword");
	// mineType.put("xls", "application/vnd.ms-excel");
	// mineType.put("xlsx", "application/vnd.ms-excel");
	// mineType.put("ppt", "application/vnd.ms-powerpoint");
	// mineType.put("pptx", "application/vnd.ms-powerpoint");
	// mineType.put("gif", "image/gif");
	// mineType.put("png", "image/png");
	// mineType.put("jpeg", "image/jpg");
	// mineType.put("jpg", "image/jpg");
	// mineType.put("mp4", "video/mp4");
	// mineType.put("apk", "application/vnd.android.package-archive");
	//
	// HttpHeaders headers = new HttpHeaders();
	// String filename = new String(attachFile.getFileOrgNm().getBytes("UTF-8"),
	// "ISO-8859-1");
	// headers.add("Content-Disposition", "attachment; filename=\"" + filename +
	// "\"");
	// headers.add("Content-Type", mineType.get(attachFile.getFileExt()));
	// return
	// ResponseEntity.ok().headers(headers).contentLength(attachFile.getFileSize()).body(resource);
	// }

	@ApiOperation(value = "첨부파일을 Base64로 변환한뒤 UTF-8로 인코딩하여 바이트형식으로 전달.", notes = "SHE FrontEnd에서 스트림이 깨지는 문제로 이 형식으로 파일 다운로드를 처리하고 있음.")
	@RequestMapping(value = "/base64byte", method = RequestMethod.GET)
	public @ResponseBody byte[] fileDownBase64ToByte(HttpServletRequest request, @RequestParam("fileNo") long fileNo,
			@RequestParam("taskFlag") String taskFlag) throws Exception {
		InputStream inputStream = null;
		try {
			AttachFile attachFile = attachFileService.getUploadFile(String.valueOf(fileNo), taskFlag);
			File file = attachFileService.getFile(attachFile);

			inputStream = new BufferedInputStream(new FileInputStream(file));

			byte[] buffer = IOUtils.toByteArray(inputStream);
			if (attachFile.getEncryptYn() != null && attachFile.getEncryptYn().equals("Y")) {
				buffer = this.fileStorageService.decryptFileData(buffer);
			}

			byte[] encoded = Base64.encodeBase64(buffer);
			String encodedString = new String(encoded);
			return encodedString.getBytes("UTF-8");
		} catch (FileNotFoundException fe) {
			logger.error(fe.getMessage());
			return null;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage());
			return null;
		} catch (IOException ie) {
			logger.error(ie.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}


	@SuppressWarnings("finally")
	@ApiOperation(value = "이미지를 Base64로 변환한뒤 UTF-8로 인코딩하여 바이트형식으로 전달.", notes = "SHE FrontEnd에서 스트림이 깨지는 문제로 이 형식으로 파일 다운로드를 처리하고 있음.")
	@RequestMapping(value = "/image64byte", method = RequestMethod.GET, produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE })
	public @ResponseBody byte[] downloadOriginalImageBase64Byte(HttpServletRequest request,
			@RequestParam("fileNo") Long fileNo, @RequestParam("taskFlag") String taskFlag) throws Exception {
		FileInfoObject fileInfo;
		InputStream inputStream = null;
		String encodedString = "";
		byte[] bytes = new byte[0];
		try {
			fileInfo = this.getImageInfo(String.valueOf(fileNo), taskFlag);
			File file = new File(fileInfo.getDownOriginFileFullPath());

			inputStream = new BufferedInputStream(new FileInputStream(file));

			byte[] buffer = IOUtils.toByteArray(inputStream);
			if (fileInfo.getEncryptYn() != null && fileInfo.getEncryptYn().equals("Y")) {
				buffer = this.fileStorageService.decryptFileData(buffer);
			}

			byte[] encoded = Base64.encodeBase64(buffer);
			encodedString = new String(encoded);
			bytes = encodedString.getBytes("UTF-8");
		} catch (FileNotFoundException e) {
			// Blank이미지로 처리함.
			if (encodedString.isEmpty()) {
				try {
					inputStream = FileUtil.getStreamFixedBase64(globalSettings.getNoImageFilePath(), "noimage", ".png");
					byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
					encodedString = new String(encoded);
					bytes = encodedString.getBytes("UTF-8");
				} catch (IllegalArgumentException iae) {
					logger.error(iae.getMessage());
				} catch (Exception ex) {
					logger.error(ex.getMessage());
					throw ex;
				} finally {
					if (inputStream != null)
						inputStream.close();
				}
			}
		} finally {
			if (!encodedString.isEmpty() && inputStream != null) {
				inputStream.close();
			}
		}
		return bytes;
	}

	/**
	 * 공통 파일정보
	 * 
	 * @param filePk
	 * @return
	 * @throws FileNotFoundException
	 * @throws ValidationException
	 */
	private FileInfoObject getImageInfo(String fileNo, String taskFlag) throws FileNotFoundException, Exception {

		AttachFile attachFile = attachFileService.getUploadFile(fileNo, taskFlag);
		if (attachFile == null || StringUtil.isIntegerNullOrZero(attachFile.getFileNo())) {
			throw new FileNotFoundException("File information not found.");
		}

		FileUtil fileUtil = new FileUtil();
		fileUtil.setFileUploadPath(FileUtil.getStoreFilePath());

		/*
		 * file_down_path : FORMAT [TASK_CLASS_NM/YYYYMMDD/FILENAME]
		 * -------------------------------------- EX1) /CHECK_RESULT/20190910/XXX.JPG
		 * EX1) /CHM_REGUL_DB_HIST/20190910/AAA.XLXS 2019.09.10 file_down_path는 불필요한 정보로
		 * 이 컬럼을 활용하여 위와 같이 루트Path를 제외하고 전체파일 경로+파일명을 넣어서 처리되도록 한다.
		 */
		String downOriginFileFullPath = FileUtil.getStoreFilePath() + File.separator
				+ attachFile.getFileDownPath().replaceAll("\\\\+", "\\\\");

		@SuppressWarnings("static-access")
		String downThumbnailFileFullPath = File.separator + fileUtil.THUMBNAIL_FOLDER_NAME + File.separator
				+ attachFile.getFileDownPath();

		if (attachFile == null || StringUtil.isEmpty(attachFile.getFileOrgNm())) {
			throw new FileNotFoundException("File information not found.");
		}

		return new FileInfoObject(downOriginFileFullPath, downThumbnailFileFullPath, attachFile.getFileSaveNm(),
				attachFile.getFileOrgNm(), attachFile.getFileExt(), attachFile.getContentType(),
				attachFile.getEncryptYn());
	}

	/**
	 * 파일정보 반환 VO
	 * 
	 * @author bearsv5
	 *
	 */
	private class FileInfoObject {
		private String downOriginFileFullPath;
		private String downThumbnailFileFullPath;
		private String savedFileName;
		private String originFileName;
		private String fileExtension;
		private String contentType;
		private String encryptYn;

		public FileInfoObject(String downOriginFileFullPath, String downThumbnailFileFullPath, String savedFileName,
				String originFileName, String fileExtension, String contentType, String encryptYn) {
			super();
			this.downOriginFileFullPath = downOriginFileFullPath;
			this.downThumbnailFileFullPath = downThumbnailFileFullPath;
			this.savedFileName = savedFileName;
			this.originFileName = originFileName;
			this.fileExtension = fileExtension;
			this.encryptYn = encryptYn;
		}

		public String getDownOriginFileFullPath() {
			return downOriginFileFullPath;
		}

		public String getDownThumbnailFileFullPath() {
			return downThumbnailFileFullPath;
		}

		@SuppressWarnings("unused")
		public String getSavedFileName() {
			return savedFileName;
		}

		public String getOriginFileName() {
			return originFileName;
		}

		public String getFileExtension() {
			return fileExtension;
		}

		public String getContentType() {
			return contentType;
		}

		public String getEncryptYn() {
			return encryptYn;
		}

		@Override
		public String toString() {
			return "FileInfoObject [downOriginFileFullPath= " + downOriginFileFullPath + ", downThumbnailFileFullPath= "
					+ downThumbnailFileFullPath + ", savedFileName= " + savedFileName + ", originFileName= "
					+ originFileName + ", fileExtension= " + fileExtension + ", encryptYn= " + encryptYn + "]";
		}
	}

}
