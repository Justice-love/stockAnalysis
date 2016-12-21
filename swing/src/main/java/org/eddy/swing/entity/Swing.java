package org.eddy.swing.entity;

/**
 * Created by eddy on 2016/12/13.
 */
public class Swing {

    //REQUIRED
    private String id;
    private String expression;
    private Validater validateType;
    private String expect;
    //IMPLIED
    private boolean autoTrigger;
    private String orElse;
    private String executor;
    private Swing child;
    //other
    private boolean root;

    //method
    public boolean hasChild() {
        return null != child;
    }

    public Swing() {
    }
    public Swing(String id, String expression, String expect, Validater validateType) {
        this.id = id;
        this.expression = expression;
        this.expect = expect;
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

    public Swing getChild() {
        return child;
    }

    public void setChild(Swing child) {
        this.child = child;
    }

    public Validater getValidateType() {
        return validateType;
    }

    public void setValidateType(Validater validateType) {
        this.validateType = validateType;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }
}
