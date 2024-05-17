package com.she.file.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.she.file.exception.FileStorageException;
import com.she.file.exception.ZipParsingException;

public class ZipFiles {
    // 다운로드 zip 파일명
    public String zipFileName;

    // zip 대상 파일
    public List<String> srcFiles;

    // zip 대상 파일을 새 이름으로 변경 할 경우 파일명
    public List<String> targetFiles;

    // zip 저장 경로
    public String targetPath;

    // buffer 크기
    public final int byteSize = 1024;

    public String getZipFileName() {
        return zipFileName + ".zip";
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public List<String> getSrcFiles() {
        return srcFiles;
    }

    public void setSrcFiles(List<String> srcFiles) {
        this.srcFiles = srcFiles;
    }

    public List<String> getTargetFiles() {
        return targetFiles;
    }

    public void setTargetFiles(List<String> targetFiles) {
        this.targetFiles = targetFiles;
    }

    public String getTargetPath() {
        return targetPath + "/zips/";
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public ZipFiles() {
        this.srcFiles = new ArrayList<>();
    }

    public ZipFiles(String zipFileName) {
        this.zipFileName = zipFileName;
        this.srcFiles = new ArrayList<>();
        this.targetFiles = new ArrayList<>();
    }

    public ZipFiles(String zipFileName, String targetPath) {
        this.zipFileName = zipFileName;
        this.targetPath = targetPath;
        this.targetFiles = new ArrayList<>();
    }

    public ZipFiles(String zipFileName, List<String> srcFiles, String targetPath) {
        this.zipFileName = zipFileName;
        this.srcFiles = srcFiles;
        this.targetPath = targetPath;
        this.targetFiles = new ArrayList<>();
    }

    public ZipFiles(String zipFileName, List<String> srcFiles, List<String> targetFiles, String targetPath) {
        this.zipFileName = zipFileName;
        this.srcFiles = srcFiles;
        this.targetFiles = targetFiles;
        this.targetPath = targetPath;
    }

    /**
     * zipping 파일
     * 
     * @return
     * @throws IOException
     */
    public String filesToZip() throws IOException {
        File zipFileName = Paths.get(this.getTargetPath() + this.getZipFileName()).toFile();
        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        String targetName = "";
        try {
            int count = 0;
            for (String srcFile : this.srcFiles) {
                File fileToZip = new File(srcFile);
                if (fileToZip.exists()) {
                    if (this.srcFiles.size() == this.targetFiles.size()) {
                        targetName = this.targetFiles.get(count);
                    } else {
                        targetName = fileToZip.getName();
                    }
                    FileInputStream fis = new FileInputStream(fileToZip);
                    ZipEntry zipEntry = new ZipEntry(targetName);
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[byteSize];
                    int length;
                    try {
                        while ((length = fis.read(bytes)) >= 0) {
                            zipOut.write(bytes, 0, length);
                        }
                    } catch (Exception ie) {
                        throw ie;
                    } finally {
                        fis.close();
                    }

                    count++;
                }
            }

            if (count <= 0) {
                throw new FileStorageException("nofile");
            }

            return zipFileName.getPath();
        } catch (Exception e) {
            throw e;
        } finally {
            zipOut.close();
            fos.close();
        }
    }

    public String directoryToZip(String dirName) throws Exception {
        // the directory to be zipped
        Path directory = Paths.get(dirName);

        try {
            Files.createDirectories(Paths.get(this.getTargetPath()));
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }

        // the zip file name that we will create
        File zipFileName = Paths.get(this.getTargetPath() + this.getZipFileName()).toFile();

        // open the zip stream in a try resource block, no finally needed
        try (ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFileName))) {

            // traverse every file in the selected directory and add them
            // to the zip file by calling addToZipFile(..)
            DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory);
            dirStream.forEach(path -> addToZipFile(path, zipStream));

            zipStream.close();
            dirStream.close();

            return zipFileName.getPath();
        } catch (IOException | ZipParsingException e) {
            return null;
        }
    }

    /**
     * Adds an extra file to the zip archive, copying in the created date and a
     * comment.
     * 
     * @param file
     *            file to be archived
     * @param zipStream
     *            archive to contain the file.
     */
    public void addToZipFile(Path file, ZipOutputStream zipStream) {
        String inputFileName = file.toFile().getPath();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(inputFileName);

            // create a new ZipEntry, which is basically another file
            // within the archive. We omit the path from the filename
            ZipEntry entry = new ZipEntry(file.toFile().getName());
            entry.setCreationTime(FileTime.fromMillis(file.toFile().lastModified()));
            entry.setComment("Created by TheCodersCorner");
            zipStream.putNextEntry(entry);

            // LOG.info("Generated new entry for: " + inputFileName);

            // Now we copy the existing file into the zip archive. To do
            // this we write into the zip stream, the call to putNextEntry
            // above prepared the stream, we now write the bytes for this
            // entry. For another source such as an in memory array, you'd
            // just change where you read the information from.
            byte[] readBuffer = new byte[byteSize];
            int amountRead;
            int written = 0;

            while ((amountRead = inputStream.read(readBuffer)) > 0) {
                zipStream.write(readBuffer, 0, amountRead);
                written += amountRead;
            }

        } catch (IOException e) {
            throw new ZipParsingException("Unable to process " + inputFileName, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new ZipParsingException("Unable to process " + inputFileName, e);
            }
        }
    }

    // public void directoryToZip() throws IOException
    // {
    // File filesToZip = new File(this.getDirPath());
    // ZipEntry entry = new ZipEntry(filesToZip.getName());
    // FileOutputStream fos = new FileOutputStream(this.getZipFileName());
    // ZipOutputStream outputZip = new ZipOutputStream(fos);
    //
    // this.directoryToZip(filesToZip, filesToZip.getName(), outputZip);
    // outputZip.close();
    // fos.close();
    // }
    //
    // public void directoryToZip(File fileToZip, String fileName,
    // ZipOutputStream zipOut) throws IOException {
    // if (fileToZip.isHidden()) {
    // return;
    // }
    // if (fileToZip.isDirectory()) {
    // if (fileName.endsWith("/")) {
    // zipOut.putNextEntry(new ZipEntry(fileName));
    // zipOut.closeEntry();
    // } else {
    // zipOut.putNextEntry(new ZipEntry(fileName + "/"));
    // zipOut.closeEntry();
    // }
    // File[] children = fileToZip.listFiles();
    // for (File childFile : children) {
    // directoryToZip(childFile, fileName + "/" + childFile.getName(), zipOut);
    // }
    // return;
    // }
    //
    // FileInputStream fis = new FileInputStream(fileToZip);
    // ZipEntry zipEntry = new ZipEntry(fileName);
    // zipOut.putNextEntry(zipEntry);
    // byte[] bytes = new byte[this.byteSize];
    // int length;
    // while ((length = fis.read(bytes)) >= 0) {
    // zipOut.write(bytes, 0, length);
    // }
    // fis.close();
    // }

}
