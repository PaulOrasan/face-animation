package faceanimation.persistence;

import faceanimation.model.AppUser;

import java.util.UUID;

public interface AppUserRepository<E extends AppUser> extends Repository<UUID, E> {

    public E findUserByUsernameAndPassword(String username, String password);

    public E findUserByUsername(String username);
}
