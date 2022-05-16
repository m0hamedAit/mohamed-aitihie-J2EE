package ma.m0hamedait.ebankbackend.dtos;


import lombok.Data;
import ma.m0hamedait.ebankbackend.enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentAccountDTO extends AccountDTO {
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private CustomerDTO owner;
    private double overDraft;

}
