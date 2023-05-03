package com.tpe.domain;

import com.tpe.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_role")//sql'ce kullanıcaksan ismi ==> tbl_name, java'ca kullanıcaksan ==> Role
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)//name isimli field'ı enum type'tan al ama intiger ile değil String ile
    @Column(length = 30, nullable = false)
    private UserRole name;//enum class'taki name'den alcak

    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }//id'yi cıkardım id bilgisinin bana gelmesini bi yararı yok




}
