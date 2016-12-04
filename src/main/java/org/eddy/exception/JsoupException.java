package org.eddy.exception;

/**
 * Created by admin on 2016/12/4.
 */
public class JsoupException extends Exception{
    public JsoupException() {
    }

    public JsoupException(String message) {
        super(message);
    }

    public JsoupException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsoupException(Throwable cause) {
        super(cause);
    }

    public JsoupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
