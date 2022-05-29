package ma.m0hamedait.ebankbackend.dtos;


import lombok.Data;
import ma.m0hamedait.ebankbackend.enums.OperationType;

import java.util.Date;

@Data
public class OperationDTO {
    private Long id;
    private Date operationDate;
    private String description;
    private double amount;
    private OperationType type;
    private AccountDTO accountDTO;  /////////// a revoir
}
