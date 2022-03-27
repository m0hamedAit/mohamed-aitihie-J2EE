package ma.enset.tp4.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    private String userId;
    @Column(unique = true, length=20)   // username va etre unique
    private String username;
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(mappedBy="users", fetch=FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();  // pour eviter NullPointerException
}

