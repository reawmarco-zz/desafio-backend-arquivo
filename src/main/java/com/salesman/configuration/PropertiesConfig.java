package com.salesman.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    @Value("${separator}")
    private String separator;
    @Value("${file.out.regex}")
    private String fileOutputRegex;
    @Value("${file.out.replace}")
    private String fileOutputReplace;
    @Value("${file.out.folder}")
    private String fileOutputFolder;
    @Value("${file.in.folder}")
    private String fileInputFolder;
    @Value("${file.in.matcher}")
    private String fileInputMatcher;
    @Value("${file.base.path}")

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getFileOutputRegex() {
        return fileOutputRegex;
    }

    public void setFileOutputRegex(String fileOutputRegex) {
        this.fileOutputRegex = fileOutputRegex;
    }

    public String getFileOutputReplace() {
        return fileOutputReplace;
    }

    public void setFileOutputReplace(String fileOutputReplace) {
        this.fileOutputReplace = fileOutputReplace;
    }

    public String getFileOutputFolder() {
        return fileOutputFolder;
    }

    public void setFileOutputFolder(String fileOutputFolder) {
        this.fileOutputFolder = fileOutputFolder;
    }

    public String getFileInputFolder() {
        return fileInputFolder;
    }

    public void setFileInputFolder(String fileInputFolder) {
        this.fileInputFolder = fileInputFolder;
    }

    public String getFileInputMatcher() {
        return fileInputMatcher;
    }

    public void setFileInputMatcher(String fileInputMatcher) {
        this.fileInputMatcher = fileInputMatcher;
    }
}
