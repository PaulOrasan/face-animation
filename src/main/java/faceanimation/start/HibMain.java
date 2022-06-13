package faceanimation.start;

import faceanimation.model.DrivingVideo;
import faceanimation.model.SourceImage;
import faceanimation.persistence.DrivingVideoHibernateRepository;
import faceanimation.persistence.DrivingVideoRepository;
import faceanimation.persistence.SourceImageHibernateRepository;
import faceanimation.persistence.SourceImageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HibMain {

    private static void storeDrivingVideos() {
        DrivingVideoRepository drivingVideoRepository = new DrivingVideoHibernateRepository();
        List<DrivingVideo> drivingVideos = new ArrayList<>();
        drivingVideos.add(DrivingVideo.builder().dateTimeProduced(LocalDateTime.now()).fileName("confused.mp4")
                .emotionAnnotation("Confusion").build());
        drivingVideos.add(DrivingVideo.builder().dateTimeProduced(LocalDateTime.now()).fileName("shocked.mp4")
                .emotionAnnotation("Shock").build());
        drivingVideos.add(DrivingVideo.builder().dateTimeProduced(LocalDateTime.now()).fileName("smiling_blinking.mp4")
                .emotionAnnotation("Happy").build());
        drivingVideos.add(DrivingVideo.builder().dateTimeProduced(LocalDateTime.now()).fileName("winking.mp4")
                .emotionAnnotation("Flirty").build());
        drivingVideos.forEach(drivingVideoRepository::save);
    }
    public static void main(String[] args) {
        storeDrivingVideos();
    }
}
