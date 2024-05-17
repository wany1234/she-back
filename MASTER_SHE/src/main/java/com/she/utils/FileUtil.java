package com.she.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.she.security.util.StringUtil;

/**
 *
 * @클래스명 : FileUtil.java
 * @설명 : 파일 공통 유틸리티
 * @작성일 : 2018
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */
@SuppressWarnings("unused")
@Component
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static final int BUFF_SIZE = 2048;

    public static final String THUMBNAIL_FOLDER_NAME = "THUMBNAIL";

    public static final String THUMBNAIL_FILE_EXT = "png";

    private String uploadPath;

    public void setFileUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @SuppressWarnings({ "finally" })
    public static File getFileFixedBase64(String filePath, String prefix, String suffix) {

        InputStream inputStream = null;
        File somethingFile = null;
        try {

            ClassPathResource classPathResource = new ClassPathResource(filePath);
            inputStream = classPathResource.getInputStream();
            somethingFile = File.createTempFile(prefix, suffix);

            FileUtils.copyInputStreamToFile(inputStream, somethingFile);

        } finally {
            return somethingFile;
        }
    }

    //
    // /**
    // * 첨부파일에 대한 목록 정보를 취득한다.
    // *
    // * @param files
    // * @return
    // * @throws Exception
    // */
    // public List<AttachFileVO> parseFileInf(Map<String, MultipartFile> files
    // , String KeyStr
    // , Long KeyPk
    // , int fileKeyParam
    // , String atchFileId
    // , boolean isImage) throws Exception {
    // return this.parseFileInfExecute(files, KeyStr, KeyPk, fileKeyParam,
    // atchFileId, isImage, null);
    // }
    //
    // public List<AttachFileVO> parseFileInf(Map<String, MultipartFile> files
    // , String KeyStr
    // , Long KeyPk
    // , int fileKeyParam
    // , String atchFileId
    // , boolean isImage
    // , String[] filenames) throws Exception {
    // return this.parseFileInfExecute(files, KeyStr, KeyPk, fileKeyParam,
    // atchFileId, isImage, filenames);
    // }
    //
    // /**
    // * 첨부파일업로드 리눅스서버
    // * @param files
    // * @param KeyStr
    // * @param KeyPk
    // * @param fileKeyParam
    // * @param atchFileId
    // * @param isImage
    // * @param filenames
    // * @return
    // * @throws Exception
    // */
    // public List<AttachFileVO> parseFileInfExecute(Map<String, MultipartFile>
    // files
    // , String KeyStr
    // , Long KeyPk
    // , int fileKeyParam
    // , String atchFileId
    // , boolean isImage
    // , String[] filenames) throws Exception {
    // int fileKey = fileKeyParam;
    //
    // String storePathString = "";
    // String atchFileIdString = "";
    //
    // logger.debug("################ uploadPath :" + this.uploadPath);
    //
    // // 첨부유형, YYYYMMDD로 폴더생성
    // if (StringUtil.isEmpty(atchFileId)) {
    // atchFileIdString = UUID.randomUUID().toString().replaceAll("-", "");
    // } else {
    // atchFileIdString = atchFileId;
    // }
    //
    // logger.debug("################ atchFileIdString :" + atchFileIdString);
    //
    // storePathString = this.makeFolder(KeyStr);
    //
    //
    // Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
    // MultipartFile file;
    // String filePath = "";
    // List<AttachFileVO> result = new ArrayList<AttachFileVO>();
    // AttachFileVO fvo;
    //
    // int idx = 0;
    // while (itr.hasNext()) {
    // Entry<String, MultipartFile> entry = itr.next();
    //
    // file = entry.getValue();
    // String orginFileName = file.getOriginalFilename();
    // logger.debug("#### orginFileName : " + orginFileName);
    //
    // if (filenames != null && filenames.length > 0) {
    // orginFileName = filenames[idx];
    // }
    // idx ++;
    // logger.debug("#### orginFileName : " + orginFileName);
    //
    // //--------------------------------------
    // // 원 파일명이 없는 경우 처리
    // // (첨부가 되지 않은 input file type)
    // //--------------------------------------
    // if ("".equals(orginFileName)) {
    // continue;
    // }
    // ////------------------------------------
    //
    // int index = orginFileName.lastIndexOf(".");
    // //String fileName = orginFileName.substring(0, index);
    // String fileExt = orginFileName.substring(index + 1);
    // String newName = KeyStr + SwingUtil.getTimeStamp() + fileKey;
    // long size = file.getSize();
    //
    // if (!"".equals(orginFileName)) {
    // filePath = storePathString + File.separator + newName;
    // file.transferTo(new File(WebUtil.filePathBlackList(filePath)));
    // }
    //
    // if (isImage) {
    // // 썸네일 이미지 생성 - 동일한 폴더아래 썸네일 폴더를 별도로 생성
    // @SuppressWarnings("static-access")
    // String strThumbnailPath = storePathString + File.separator +
    // this.THUMBNAIL_FOLDER_NAME + File.separator + newName;
    // logger.debug("################ strThumbnailPath : " + strThumbnailPath);
    //
    // @SuppressWarnings("static-access")
    // File saveFolder = new File(WebUtil.filePathBlackList(storePathString +
    // File.separator + this.THUMBNAIL_FOLDER_NAME));
    // if (!saveFolder.exists() || saveFolder.isFile()) {
    // saveFolder.mkdirs();
    // }
    //
    // logger.debug("################ MakeFolder");
    // logger.debug("################ filePath : " + filePath);
    // logger.debug("################ strThumbnailPath : " + strThumbnailPath);
    //
    // File image = new File(filePath);
    // /*File thumbnail = new File(strThumbnailPath);*/
    // if (image.exists()) {
    // logger.debug("################ image.exists() ");
    // Thumbnails.of(filePath).size(140,140).outputFormat(fileExt).toFile(strThumbnailPath);
    // logger.debug("################ make thumbnail ");
    // }
    // }
    //
    // fvo = new AttachFileVO();
    // fvo.setFileExt(fileExt);
    // fvo.setFileStreCours(storePathString);
    // fvo.setFileSize(size);
    // fvo.setFileOrgNm(orginFileName);
    // fvo.setFileStreNm(newName);
    // fvo.setAttachFileGrpCd(atchFileIdString);
    // fvo.setFileOrder(fileKey);
    // fvo.setAttachType(KeyStr);
    // fvo.setAttachPk(KeyPk);
    //
    // logger.debug("################ fvo :" + fvo.toString());
    //
    // result.add(fvo);
    //
    // fileKey++;
    // }
    //
    //
    // return result;
    // }
    //
    // /**
    // * 첨부파일을 서버에 저장한다.
    // *
    // * @param file
    // * @param newName
    // * @param stordFilePath
    // * @throws Exception
    // */
    // protected void writeUploadedFile(MultipartFile file, String newName,
    // String stordFilePath) throws Exception {
    // InputStream stream = null;
    // OutputStream bos = null;
    //
    // try {
    // stream = file.getInputStream();
    // File cFile = new File(stordFilePath);
    //
    // if (!cFile.isDirectory()) {
    // boolean _flag = cFile.mkdir();
    // if (!_flag) {
    // throw new IOException("Directory creation Failed ");
    // }
    // }
    //
    // bos = new FileOutputStream(stordFilePath + File.separator + newName);
    //
    // int bytesRead = 0;
    // byte[] buffer = new byte[BUFF_SIZE];
    //
    // while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
    // bos.write(buffer, 0, bytesRead);
    // }
    // } finally {
    // ResourceCloseHelper.close(bos, stream);
    // }
    // }
    //
    // /**
    // * 첨부로 등록된 파일을 서버에 업로드한다.
    // *
    // * @param file
    // * @return
    // * @throws Exception
    // */
    // public static HashMap<String, String> uploadFile(MultipartFile file,
    // FileSaveParam param) throws Exception {
    //
    // HashMap<String, String> map = new HashMap<String, String>();
    // //Write File 이후 Move File????
    // String newName = "";
    // String stordFilePath = param.getFileStorePath();
    // String orginFileName = file.getOriginalFilename();
    //
    // int index = orginFileName.lastIndexOf(".");
    // //String fileName = orginFileName.substring(0, _index);
    // String fileExt = orginFileName.substring(index + 1);
    // long size = file.getSize();
    //
    // //newName 은 Naming Convention에 의해서 생성
    // newName = SwingUtil.getTimeStamp(); // 2012.11 KISA 보안조치
    // writeFile(file, newName, stordFilePath);
    // //storedFilePath는 지정
    // map.put(param.getOriginFileNm(), orginFileName);
    // map.put(param.getUploadFileNm(), newName);
    // map.put(param.getFileExt(), fileExt);
    // map.put(param.getFilePath(), stordFilePath);
    // map.put(param.getFileSize(), String.valueOf(size));
    //
    // return map;
    // }
    //
    // /**
    // * 마이그레이션 임시파일 업로드
    // * @param file
    // * @param stordFilePath
    // * @param ext
    // * @return
    // * @throws Exception
    // */
    // public static String uploadMigrationFile(MultipartFile file, String
    // stordFilePath, String ext) throws Exception {
    //
    // String newName = SwingUtil.getTimeStamp(); // 2012.11 KISA 보안조치
    // writeFile(file, newName + ext, stordFilePath);
    //
    // return newName;
    // }
    //
    // /**
    // * 파일을 실제 물리적인 경로에 생성한다.
    // *
    // * @param file
    // * @param newName
    // * @param stordFilePath
    // * @throws Exception
    // */
    // protected static void writeFile(MultipartFile file, String newName,
    // String stordFilePath) throws Exception {
    // InputStream stream = null;
    // OutputStream bos = null;
    //
    // try {
    // stream = file.getInputStream();
    // File cFile = new File(WebUtil.filePathBlackList(stordFilePath));
    //
    // if (!cFile.isDirectory())
    // cFile.mkdir();
    //
    // bos = new FileOutputStream(WebUtil.filePathBlackList(stordFilePath +
    // File.separator + newName));
    //
    // int bytesRead = 0;
    // byte[] buffer = new byte[BUFF_SIZE];
    //
    // while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
    // bos.write(buffer, 0, bytesRead);
    // }
    // } finally {
    // ResourceCloseHelper.close(bos, stream);
    // }
    // }
    //
    //
    //
    //
    // /**
    // * 폴더생성
    // * @param rootPath
    // * @param preFix
    // * @return
    // */
    // @SuppressWarnings("unused")
    // public String makeFolder(String preFix) {
    // String rtnStr = null;
    //
    // // 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
    // String pattern = "yyyyMMdd";
    // SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern,
    // Locale.KOREA);
    // Timestamp ts = new Timestamp(System.currentTimeMillis());
    // rtnStr = sdfCurrent.format(ts.getTime());
    //
    // String strFullPath = this.uploadPath + File.separator + preFix +
    // File.separator + rtnStr;
    // File saveFolder = new File(WebUtil.filePathBlackList(strFullPath));
    //
    // if (!saveFolder.exists() || saveFolder.isFile()) {
    // boolean ismake = saveFolder.mkdirs();
    // }
    //
    // return strFullPath;
    // }
    //
    // /**
    // *
    // * <pre>
    // * 개요 : 테스트용 함수 (안씀)
    // * </pre>
    // * @ClassName : FileUtil.java
    // * @MethodName : copyFile
    // * @param filePath
    // * @param prefix
    // * @param suffix
    // * @return
    // */
    // @Deprecated
    // @SuppressWarnings({ "finally"})
    // public static File copyFile(String filePath, String prefix, String
    // suffix) {
    //
    // File destFile = new File("d:\\\\swing_files\\test.csv");
    //
    // try {
    // ClassPathResource classPathResource = new ClassPathResource(filePath);
    // File srcFile = classPathResource.getFile();
    // FileUtils.copyFile(srcFile, destFile);
    //
    // } finally {
    // return destFile;
    // }
    // }
    //
    //
    @SuppressWarnings({ "finally", "resource" })
    public static InputStream getStreamFixedBase64(String filePath, String prefix, String suffix) throws Exception {
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        try {

            ClassPathResource classPathResource = new ClassPathResource(filePath);
            inputStream = classPathResource.getInputStream();
            File somethingFile = File.createTempFile(prefix, suffix);

            FileUtils.copyInputStreamToFile(inputStream, somethingFile);
            inputStream2 = new FileInputStream(somethingFile);
            IOUtils.closeQuietly(inputStream);
            return inputStream2;
        } catch (IOException ie) {
        	logger.error(ie.getMessage());
        	throw ie;
        } catch (Exception e) {
        	logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
//            inputStream2.close();
        }
		
    }
    //
    // /**
    // * filePath위치에 있는 xxx.json 파일을 읽어 String으로 반환한다.
    // * @param filePath
    // * @return
    // */
    // @SuppressWarnings("unchecked")
    // public static String getJsonFileToString(String filePath) {
    //
    //
    // //read json file data to String
    // try {
    // byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
    //
    // Map<String, String> myMap = new HashMap<String, String>();
    //
    // ObjectMapper objectMapper = new ObjectMapper();
    // myMap = objectMapper.readValue(jsonData, HashMap.class);
    // return myMap.toString();
    //
    // } catch (IOException e) {
    // return "";
    // }
    // }

    /**
     * 목적 : 파일 저장 path 반환
     */
    @SuppressWarnings({ "finally", "resource" })
    public static String getStoreFilePath() throws Exception {
        String fileStorePath = "";

        Properties prop = new Properties();
        String resource = "application.properties";
        Reader reader = Resources.getResourceAsReader(resource);
        prop.load(reader);

        String active = prop.getProperty("spring.profiles.active");

        if ("dev".equals(active)) {
            prop = new Properties();
            reader = Resources.getResourceAsReader("application-dev.properties");
            prop.load(reader);
        } else {
            prop = new Properties();
            reader = Resources.getResourceAsReader("application-prd.properties");
            prop.load(reader);
        }
        fileStorePath = prop.getProperty("globals.fileStorePath");
        if (StringUtil.isNotEmpty(fileStorePath)) {
            fileStorePath = fileStorePath.replaceAll("/", "");
            fileStorePath = fileStorePath.replaceAll("\\\\", "");
            fileStorePath = fileStorePath.replaceAll(".", "");
            fileStorePath = fileStorePath.replaceAll("&", "");
        } else {
            fileStorePath = "she_files";
        }

        File folder = new File(fileStorePath);
        folder.setExecutable(false, true);
        folder.setReadable(true);
        folder.setWritable(false, true);
        if (!folder.exists()) {
            folder.mkdir(); // 폴더 생성
        }

        return fileStorePath;
    }
}