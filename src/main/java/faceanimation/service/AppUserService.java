package faceanimation.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import faceanimation.model.AppUser;
import faceanimation.persistence.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private AppUserRepository<AppUser> appUserRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserRepository<AppUser> appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO - validation checks
    public void saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
    }
}
