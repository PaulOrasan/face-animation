package faceanimation.service;

import faceanimation.model.DrivingVideo;
import faceanimation.persistence.DrivingVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrivingVideoService {

    private DrivingVideoRepository drivingVideoRepository;

    @Autowired
    public DrivingVideoService(DrivingVideoRepository drivingVideoRepository) {
        this.drivingVideoRepository = drivingVideoRepository;
    }

    public List<DrivingVideo> findDrivingVideos() {
        return drivingVideoRepository.findAll();
    }

    public DrivingVideo findDrivingVideoByExpression(String expression) {
        return drivingVideoRepository.findDrivingVideoByAnnotation(expression);
    }
}
