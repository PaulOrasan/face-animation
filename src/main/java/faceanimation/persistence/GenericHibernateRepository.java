package faceanimation.persistence;

import faceanimation.model.ModelEntity;
import faceanimation.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class GenericHibernateRepository<T, E extends ModelEntity<T>> implements Repository<T, E> {

    protected Class<E> elemType;

    public GenericHibernateRepository(Class<E> elemType) {
        this.elemType = elemType;
    }

    public E save(E elem) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error on saving "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return elem;
    }

    public void delete(E elem) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error on deleting "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    public void update(E elem) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    public E findOne(T id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(elemType, (Serializable) id);
        }
    }

    public List<E> findAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String className = elemType.getName();
                List<E> messages = session.createQuery(" from " + className + " C", elemType).list();
                tx.commit();
                return messages;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
                return null;
            }
        }
    }
}
