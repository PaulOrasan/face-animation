package faceanimation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Getter
public class SourceImage extends FileModel {

    @Transient
    private MultipartFile file;

    @Builder
    public SourceImage(LocalDateTime dateTimeProduced, String fileName, MultipartFile file) {
        super(dateTimeProduced, fileName);
        this.file = file;
    }
}
