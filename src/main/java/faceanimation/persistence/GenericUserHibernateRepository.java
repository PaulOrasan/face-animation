package faceanimation.persistence;

import faceanimation.model.User;
import faceanimation.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public abstract class GenericUserHibernateRepository<E extends User> extends GenericHibernateRepository<UUID, E> implements UserRepository<E> {

    public GenericUserHibernateRepository(Class<E> elemType) {
        super(elemType);
    }

    @Override
    public E findUserByEmailAndPassword(String email, String password) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String className = elemType.getName();
                List<E> entities = session.createQuery(" from " + className + " C where C.email = ? and C.password = ?",
                                elemType)
                        .setParameter(0, email)
                        .setParameter(1, password)
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

    public E findUserByEmail(String email) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String className = elemType.getName();
                List<E> entities = session.createQuery(" from " + className + " C where C.email = ?",
                                elemType)
                        .setParameter(0, email)
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
