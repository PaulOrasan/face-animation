package faceanimation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class SourceImage extends FileModel {

    @Transient
    private MultipartFile file;

    @Builder
    public SourceImage(LocalDateTime dateTimeProduced, String fileName, MultipartFile file) {
        super(dateTimeProduced, fileName);
        this.file = file;
    }
}
