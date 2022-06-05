package ma.m0hamedait.ebankbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {
    private String id;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<OperationDTO> OperationDTOS;
}
