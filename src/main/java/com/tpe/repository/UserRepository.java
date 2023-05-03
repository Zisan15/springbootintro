package com.tpe.repository;

import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //tüm unique işlerimi username üzerinden yapacağım için Optional
    Optional<User> findByUserName(String userName) throws ResourceNotFoundException;//ya yaksa yani null'sa throws ResourceNotFoundException yapabiliriz

    /*
        UserService gibi bir class'ım olmayacağı icin (UserDetailService) exception kısmını burada handle ettik
     */
}
