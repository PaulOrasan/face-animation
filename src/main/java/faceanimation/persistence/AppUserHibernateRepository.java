package faceanimation.persistence;

import faceanimation.model.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserHibernateRepository extends GenericAppUserHibernateRepository<AppUser> implements AppUserRepository<AppUser> {

    public AppUserHibernateRepository() {
        super(AppUser.class);
    }

}
