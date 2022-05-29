package ma.m0hamedait.ebankbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.m0hamedait.ebankbackend.enums.AccountStatus;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // pour l'inheritance (heritage) c'est mieux d'utiliser single_table meme si des column vont rester null (on peut aussi utiliser table_per_class ou joined_table
@DiscriminatorColumn(name="TYPE", length = 4 ) // utiliser seulement pour heritage single_table  // lorsqu'on utilise single table, on doit ajouter un column pour differencier entre les class heriter // un autre param : discriminatorType = DiscriminatorType.STRING (par defaut c'est string)
@Entity    // @Entity est utiliser pour le mapping objet relationnel, mais si on utilise MangoDB par exemple on doit utiliser d'autre annotations de mapping objet document
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class Account {   // on garde "@Entity" (pour indiquer que c'est une entité JPA) et on ajout 'abstract' pour l'heritage 'table_per_class', pour ne pas creer une table account
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;

    @ManyToOne
    private Customer owner;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)  // fetch=LAZY par defaut => charge uniquement les infos de account mais pas ses operations SAUF SI ON APPEL "account.getOperations()"
    private Collection<Operation> operations;
}
