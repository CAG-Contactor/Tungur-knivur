package se.caglabs.radbankir.exception;

public class LoginFailedException extends Exception {
    public LoginFailedException(String msg){
        super(msg);
    }
}
