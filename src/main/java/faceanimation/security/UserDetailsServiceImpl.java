package faceanimation.security;

import faceanimation.model.AppUser;
import faceanimation.persistence.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    AppUserRepository<AppUser> appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository<AppUser> appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findUserByUsername(email);
        if(user == null){
            throw new UsernameNotFoundException("User was not found");
        }else{
            // TODO logs here
            System.out.println("User was found");
        }
        // TODO create different roles in the future -> add roles
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
