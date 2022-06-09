package faceanimation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class GeneratedVideo extends FileModel {

    private User owner;

    private DrivingVideo drivingVideo;

    private SourceImage sourceImage;

}
