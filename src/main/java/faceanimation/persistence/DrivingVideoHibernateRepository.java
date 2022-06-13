package faceanimation.persistence;

import faceanimation.model.DrivingVideo;
import faceanimation.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DrivingVideoHibernateRepository extends GenericHibernateRepository<UUID, DrivingVideo> implements DrivingVideoRepository {

    public DrivingVideoHibernateRepository() {
        super(DrivingVideo.class);
    }

    @Override
    public DrivingVideo findDrivingVideoByAnnotation(String annotation) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String className = elemType.getName();
                List<DrivingVideo> entities = session.createQuery(" from DrivingVideo D where D.emotionAnnotation = ?1",
                                elemType)
                        .setParameter(1, annotation)
                        .list();
                tx.commit();
                if (entities.isEmpty())
                    return null;
                return entities.get(0);
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
                return null;
            }
        }
    }
}
