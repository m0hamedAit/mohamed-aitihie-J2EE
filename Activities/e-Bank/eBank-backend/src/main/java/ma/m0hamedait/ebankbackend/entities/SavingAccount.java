package ma.m0hamedait.ebankbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("SA") // utiliser seulement pour heritage single_table // affecter au "SA" au column "type" si account est un savingAccount
public class SavingAccount extends Account{
    private double interestRate;
}
