package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String firstName;

    @Column(length = 25, nullable = false)
    private String lastName;

    @Column(length = 25, nullable = false, unique = true)
    private String userName;

    @Column(length = 255, nullable = false)
    private String password;


    @JoinTable(name = "tbl_user_role",
               joinColumns = @JoinColumn(name = "user_id"),//user'dan gelen sütun ismi
               inverseJoinColumns = @JoinColumn(name = "role_id")//role'den gelen sütun ismi
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> role = new HashSet<>(); //Admin; Student, Admin.  Set yapısı yaptım çünkü uniqe olması gerek içine gelen datalar.


    @JsonIgnore//student'tan user'a, user'dan student'a sonsuz döngüye girmemek için
    @OneToOne(mappedBy = "user")//student entitiy classıyla bağlantı olsun
    private Student student;//öğrencinin tek bir user bilgisi olur



}
