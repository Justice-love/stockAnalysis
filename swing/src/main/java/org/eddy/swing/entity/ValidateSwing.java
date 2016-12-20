package org.eddy.swing.entity;

/**
 * Created by eddy on 16/12/20.
 */
public class ValidateSwing {

    private Swing swing;
    private boolean success;

    public ValidateSwing(Swing swing) {
        this.swing = swing;
    }

    public Swing getSwing() {
        return swing;
    }

    public void setSwing(Swing swing) {
        this.swing = swing;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
