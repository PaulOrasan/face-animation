package faceanimation.service;

import faceanimation.model.DrivingVideo;
import faceanimation.model.GeneratedVideo;
import faceanimation.model.SourceImage;
import faceanimation.model.AppUser;
import faceanimation.persistence.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceController {

    private SourceImageService sourceImageService;
    private DrivingVideoService drivingVideoService;
    private GeneratedVideoService generatedVideoService;
    private AppUserService appUserService;

    @Autowired
    public ServiceController(SourceImageService sourceImageService, DrivingVideoService drivingVideoService,
                             GeneratedVideoService generatedVideoService, AppUserService appUserService) {
        this.sourceImageService = sourceImageService;
        this.drivingVideoService = drivingVideoService;
        this.generatedVideoService = generatedVideoService;
        this.appUserService = appUserService;
    }

    public void saveSourceImage(MultipartFile sourceImage) {
        sourceImageService.saveSourceImage(sourceImage);
    }

    public List<String> findDrivingVideosAnnotations() {
        return drivingVideoService.findDrivingVideos().stream()
                .map(DrivingVideo::getEmotionAnnotation)
                .collect(Collectors.toList());
    }

    public GeneratedVideo generateVideo(MultipartFile file, String expression) {
        SourceImage sourceImage = sourceImageService.saveSourceImage(file);
        DrivingVideo drivingVideo = drivingVideoService.findDrivingVideoByExpression(expression);
        // video generation
        /*GeneratedVideo generatedVideo = generatedVideoService.generateVideo(sourceImage, drivingVideo);
        return generatedVideo;*/
        if (expression.equals("Confusion")) {
            return GeneratedVideo.builder().fileName("confused_3.mp4").build();
        }
        if (expression.equals("Shock")) {
            return GeneratedVideo.builder().fileName("shocked_1.mp4").build();
        }
        if (expression.equals("Happy")) {
            return GeneratedVideo.builder().fileName("smiling_blinking_1.mp4").build();
        }
        if (expression.equals("Flirty")) {
            return GeneratedVideo.builder().fileName("winking_1.mp4").build();
        }
        return null;
    }

    public void registerUser(AppUser user) {
        appUserService.saveUser(user);
    }
}
