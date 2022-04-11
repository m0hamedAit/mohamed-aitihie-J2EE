package com.example.activity1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Student{
    @Id
    private String id;
    private String firstname;
    private String lastname;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    private boolean InRegle;
    private Sexe sexe;

}
