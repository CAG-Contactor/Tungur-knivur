/*
 * NYPS 2020
 * 
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:30
 */
package se.caglabs.radbankir;

import lombok.*;

import java.util.*;

@Data
public class Accounts {
    private Map<Long, Account> accounts = new HashMap<>();

    {
        accounts.put(1234567L, new Account("Jakob Þór Einarsson", 1234567L, 1423, 1000L, 0));
        accounts.put(9999999L, new Account("Edda Björgvinsdóttir", 9999999L, 1111, 9823565L, 0));
        accounts.put(9898989L, new Account("Helgi Skúlason", 9898989L, 9898, 10000L, 0));
        accounts.put(7654321L, new Account("Egill Ólafsson", 7654321L, 1234, 56000L, 0));
        accounts.put(1111111L, new Account("Flosi Ólafsson", 1111111L, 4321, 80000L, 0));
        accounts.put(2222222L, new Account("Gotti Sigurdarson", 2222222L, 2323, 200000L, 0));
        accounts.put(4242424L, new Account("Utvecklurs", 4242424L, 4242, 0L, 0));
    }

    @Data
    @AllArgsConstructor
    public static class Account {
        private String accountOwnerName;
        private long accountNumber;
        private int pinCode;
        private long balance;
        private int failedAttempts;
    }


}
