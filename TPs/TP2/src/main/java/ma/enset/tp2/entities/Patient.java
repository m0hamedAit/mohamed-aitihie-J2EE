package ma.enset.tp2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Patient {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length=50)
    private String nom;
    @Temporal(value=TemporalType.DATE)  // annotation pour specifier type date(Date, Time, Timestamp)
    private Date dateNaissance;
    private boolean malade;
    private int score;


}
