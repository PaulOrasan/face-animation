package faceanimation.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class GeneratedVideo extends FileModel {

    @ManyToOne
    private AppUser owner;

    @ManyToOne
    private DrivingVideo drivingVideo;

    @ManyToOne
    private SourceImage sourceImage;

    @Builder
    public GeneratedVideo(LocalDateTime dateTimeProduced, String fileName, AppUser owner, DrivingVideo drivingVideo, SourceImage sourceImage) {
        super(dateTimeProduced, fileName);
        this.owner = owner;
        this.drivingVideo = drivingVideo;
        this.sourceImage = sourceImage;
    }
}
