package ma.enset.tp5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor  //@Data genere constructeur sans args
@Entity // entity Jpa exige construteur sans params
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private boolean sick;
    private int score;

}
