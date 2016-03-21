package se.caglabs.radbankir;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String accountOwnerName;
    private long accountNumber;
    private int pinCode;
    private long balance;
    private int failedAttempts;
}