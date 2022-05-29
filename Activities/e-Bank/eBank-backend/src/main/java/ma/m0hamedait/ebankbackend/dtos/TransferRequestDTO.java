package ma.m0hamedait.ebankbackend.dtos;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private String senderId;
    private String recipientId;
    private double amount;
    private String description;
}
