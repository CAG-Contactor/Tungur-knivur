package se.caglabs.radbankir.exception;

public class AccountLockedException extends Exception {
    public AccountLockedException(String msg) {
        super(msg);
    }
}
