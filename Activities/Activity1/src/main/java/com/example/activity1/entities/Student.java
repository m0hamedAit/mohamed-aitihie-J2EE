package com.example.activity1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Student{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Size(min=3)
    private String firstname;
    private String lastname;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    private boolean inRegle;
    private Sexe sexe;

}
