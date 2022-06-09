package faceanimation.utils;

import faceanimation.model.SourceImage;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileIOUtils {

    public static void saveFile(String path, SourceImage sourceImage) {
        if (sourceImage.getFile() == null)
            return;
        Path root = Paths.get(path);
        try {
            Files.copy(sourceImage.getFile().getInputStream(), root.resolve(sourceImage.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    //TODO -- implement
    public static void deleteFile(String path, SourceImage sourceImage) {

    }
}
