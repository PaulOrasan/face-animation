package faceanimation.persistence;

import faceanimation.model.DrivingVideo;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DrivingVideoHibernateRepository extends GenericHibernateRepository<UUID, DrivingVideo> implements DrivingVideoRepository {

    public DrivingVideoHibernateRepository() {
        super(DrivingVideo.class);
    }
}
