package ma.m0hamedait.ebankbackend.exceptions;

public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException(String msg) {
        super(msg);
    }
}
