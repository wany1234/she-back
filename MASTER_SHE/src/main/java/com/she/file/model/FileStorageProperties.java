package com.she.file.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    private String zipDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getZipDir() {
        return zipDir;
    }

    public void setZipDir(String zipDir) {
        this.zipDir = zipDir;
    }
}
