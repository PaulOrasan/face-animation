package faceanimation.persistence;

import faceanimation.model.AppUser;
import faceanimation.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public abstract class GenericAppUserHibernateRepository<E extends AppUser> extends GenericHibernateRepository<UUID, E> implements AppUserRepository<E> {

    public GenericAppUserHibernateRepository(Class<E> elemType) {
        super(elemType);
    }

    @Override
    public E findUserByUsernameAndPassword(String username, String password) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String className = elemType.getName();
                List<E> entities = session.createQuery(" from " + className + " C where C.username = ?1 and C.password = ?2",
                                elemType)
                        .setParameter(1, username)
                        .setParameter(2, password)
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

    public E findUserByUsername(String username) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String className = elemType.getName();
                List<E> entities = session.createQuery(" from " + className + " C where C.username = ?1",
                                elemType)
                        .setParameter(1, username)
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
