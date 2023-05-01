package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpe.repository.StudentRepository;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor//parametresiz constructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonProperty("bookName")
//sadece bu class'ın Json çıktısını bookName olarak yazar. sadee Json cıktısındaki key değerini degiştirmiş olduk
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    // Getter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Student getStudent(){
        return student;
    }



}
