package faceanimation.persistence;


import faceanimation.model.ModelEntity;

import java.util.List;

public interface Repository<T, E extends ModelEntity<T>> {

    public E save(E elem);
    public void delete(E elem);
    public void update(E elem);
    public E findOne(T id);
    public List<E> findAll();

}
