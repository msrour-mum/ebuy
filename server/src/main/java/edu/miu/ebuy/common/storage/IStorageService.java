package edu.miu.ebuy.common.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface IStorageService {

    String uploadMultipartFile(MultipartFile multipartFile, String subDirs) throws IOException;
    public File uploadCSVFile(MultipartFile multipartFile) throws IOException ;
}
