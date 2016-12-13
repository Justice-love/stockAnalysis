package org.eddy.swing.entity;

/**
 * Created by eddy on 2016/12/13.
 */
public class Swing {

    //REQUIRED
    private String id;
    private String expression;
    private ValidateType validateType;
    //IMPLIED
    private boolean autoTrigger;
    private String orElse;
    private String executor;
    private Swing child;

    //method


    public Swing() {
    }
    public Swing(String id, String expression, ValidateType validateType) {
        this.id = id;
        this.expression = expression;
        this.validateType = validateType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean isAutoTrigger() {
        return autoTrigger;
    }

    public void setAutoTrigger(boolean autoTrigger) {
        this.autoTrigger = autoTrigger;
    }

    public String getOrElse() {
        return orElse;
    }

    public void setOrElse(String orElse) {
        this.orElse = orElse;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public ValidateType getValidateType() {
        return validateType;
    }

    public void setValidateType(ValidateType validateType) {
        this.validateType = validateType;
    }

    public Swing getChild() {
        return child;
    }

    public void setChild(Swing child) {
        this.child = child;
    }
}
