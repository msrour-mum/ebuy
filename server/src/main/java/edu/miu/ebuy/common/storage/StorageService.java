package edu.miu.ebuy.common.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import com.google.common.io.Files;

@Component
public class StorageService implements IStorageService {

    @Value("${user.image.physical.base.url}")
    String imagePhysicalBaseUrl;
    @Value("${user.image.relative.base.url}")
    String imageRelativeBaseUrl;

    @Override
    public String uploadMultipartFile(MultipartFile multipartFile, String subDirs) throws IOException {
        String extension = Files.getFileExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        UUID randomUUID = UUID.randomUUID();
        String filePhysicalPath =String.format("%s/%s/%s.%s",
                imagePhysicalBaseUrl,
                subDirs,
                randomUUID,
                extension);

        File file = new File(String.format("%s%s", "C:/", filePhysicalPath));
        if(!file.exists())
        {
            file.mkdirs();
        }

        multipartFile.transferTo(file);

        String fileRelativePath = String.format("%s/%s/%s.%s",
                imageRelativeBaseUrl,
                subDirs,
                randomUUID,
                extension);
        return fileRelativePath;
    }


    @Override
    public File uploadCSVFile(MultipartFile multipartFile) throws IOException {

        String subDirs = "C:\\uploads\\ebuy_uploads\\ftp\\";
        String extension = Files.getFileExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        UUID randomUUID = UUID.randomUUID();
//        String filePhysicalPath =String.format("%s/%s/%s.%s",subDirs,
//                "",
//                "",
//                randomUUID,
//                extension);

        String filePhysicalPath = subDirs+ randomUUID+extension;
        File file = new File(filePhysicalPath);
        if(!file.exists())
        {
            file.mkdirs();
        }
        multipartFile.transferTo(file);
        return file;
    }
}
