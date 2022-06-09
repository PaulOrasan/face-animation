package faceanimation.persistence;

import faceanimation.model.User;

import java.util.UUID;

public interface UserRepository<E extends User> extends Repository<UUID, E> {

    public E findUserByEmailAndPassword(String email, String password);

    public E findUserByEmail(String email);
}
