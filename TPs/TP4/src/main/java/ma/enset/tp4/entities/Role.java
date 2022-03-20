package ma.enset.tp4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data  @NoArgsConstructor @AllArgsConstructor
@Entity
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desc;
    @Column(unique = true, length=20)
    private String roleName;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="USERS_ROLES")
    private List<User> users = new ArrayList<>();
}
