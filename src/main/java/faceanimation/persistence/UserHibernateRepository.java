package faceanimation.persistence;

import faceanimation.model.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserHibernateRepository extends GenericUserHibernateRepository<User> implements UserRepository<User> {

    public UserHibernateRepository() {
        super(User.class);
    }

}
