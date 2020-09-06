package srs.appliance.api;

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg,null, false, false);
        this.code = code;
    }
}
