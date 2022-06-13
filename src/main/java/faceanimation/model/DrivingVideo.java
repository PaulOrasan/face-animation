package faceanimation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class DrivingVideo extends FileModel {

    private String emotionAnnotation;

    @Builder
    public DrivingVideo(LocalDateTime dateTimeProduced, String fileName, String emotionAnnotation) {
        super(dateTimeProduced, fileName);
        this.emotionAnnotation = emotionAnnotation;
    }
}
