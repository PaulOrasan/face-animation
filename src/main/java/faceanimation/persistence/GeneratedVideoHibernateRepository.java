package faceanimation.persistence;

import faceanimation.model.GeneratedVideo;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class GeneratedVideoHibernateRepository extends GenericHibernateRepository<UUID, GeneratedVideo> implements GeneratedVideoRepository {

    public GeneratedVideoHibernateRepository() {
        super(GeneratedVideo.class);
    }
}
