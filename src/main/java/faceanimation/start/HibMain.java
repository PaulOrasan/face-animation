package faceanimation.start;

import faceanimation.model.SourceImage;
import faceanimation.persistence.SourceImageHibernateRepository;
import faceanimation.persistence.SourceImageRepository;

import java.time.LocalDateTime;

public class HibMain {

    public static void main(String[] args) {
        SourceImageRepository sourceImageRepository = new SourceImageHibernateRepository();
        SourceImage sourceImage = SourceImage.builder().fileName("test").dateTimeProduced(LocalDateTime.now()).build();
        sourceImageRepository.save(sourceImage);
    }
}
