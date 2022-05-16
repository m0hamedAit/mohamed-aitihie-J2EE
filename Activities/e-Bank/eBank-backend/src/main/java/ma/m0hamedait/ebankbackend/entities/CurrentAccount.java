package ma.m0hamedait.ebankbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("CA") // utiliser seulement pour heritage single_table // affecter au "CA" au column "type" si account est un currentAccount
public class CurrentAccount extends Account{
    private double overDraft;
}
