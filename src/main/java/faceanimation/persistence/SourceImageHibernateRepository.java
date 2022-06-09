package faceanimation.persistence;

import faceanimation.model.SourceImage;
import faceanimation.utils.Constants;
import faceanimation.utils.FileIOUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SourceImageHibernateRepository extends GenericHibernateRepository<UUID, SourceImage> implements SourceImageRepository {

    public SourceImageHibernateRepository() {
        super(SourceImage.class);
    }

    @Override
    public SourceImage save(SourceImage elem) {
        super.save(elem);
        FileIOUtils.saveFile(Constants.MODEL_DIRECTORY_PATH + "\\source_image\\", elem);
        return elem;
    }

    @Override
    public void delete(SourceImage elem) {
        super.delete(elem);
        FileIOUtils.deleteFile(Constants.MODEL_DIRECTORY_PATH + "\\source_image\\", elem);
    }
}
