package org.eddy.swing.entity.exception;

/**
 * Created by eddy on 2016/12/13.
 */
public class SwingException extends Exception{
    public SwingException() {
        super();
    }

    public SwingException(String message) {
        super(message);
    }

    public SwingException(String message, Throwable cause) {
        super(message, cause);
    }

    public SwingException(Throwable cause) {
        super(cause);
    }

    protected SwingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
