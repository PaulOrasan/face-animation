package faceanimation.service;

import faceanimation.model.SourceImage;
import faceanimation.persistence.SourceImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SourceImageService {

    public SourceImageRepository sourceImageRepository;

    @Autowired
    public SourceImageService(SourceImageRepository sourceImageRepository) {
        this.sourceImageRepository = sourceImageRepository;
    }

    private String generateUUIDFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }
    public void saveSourceImage(MultipartFile sourceImageFile) {
        SourceImage sourceImage = SourceImage.builder()
                .file(sourceImageFile)
                .dateTimeProduced(LocalDateTime.now())
                .fileName(generateUUIDFileName(sourceImageFile))
                .build();
        sourceImageRepository.save(sourceImage);
    }
}
