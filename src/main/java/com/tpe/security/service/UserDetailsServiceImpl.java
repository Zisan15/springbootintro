package com.tpe.security.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {//burası security'nin service katı

    /*
        !!! Bu classta 1. amacım : Security katmanına User objelerini verip UserDetails türüne cevrilmesine sağlamak
            Kısaca kendi User'larımı Security katmanına tanıtmış olacağız.

        !!! Bu classta 2. amacım : Role bilgilerini Granted Auth. a cevirmek
    */

    @Autowired
    UserRepository userRepository;//user'a ulaşmam lazım


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username).orElseThrow(() ->
                new ResourceNotFoundException("user not found with username :" + username));


        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    buildGrantedAuthority(user.getRole())
            );
        } else {
            throw new UsernameNotFoundException("User not found with username : " + username);

        }

    }


    private static List<SimpleGrantedAuthority> buildGrantedAuthority(final Set<Role> roles) {//null olmaması lazım oyüzden yapı bu rol bilgileri setlenmesi lazım

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        return authorities;
    }


}
