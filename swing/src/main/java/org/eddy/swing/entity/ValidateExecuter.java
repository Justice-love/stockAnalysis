package org.eddy.swing.entity;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.eddy.entity.Stock;
import org.junit.Assert;

import java.util.List;
import java.util.SortedMap;

/**
 * Created by eddy on 2017/1/7.
 */
public class ValidateExecuter {

    private String script;

    private String  scriptName;

    private String description;

    public boolean execute(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        binding.setVariable("groupStocks", groupStocks);
        binding.setVariable("expression", expression);
        binding.setVariable("expect", expect);
        return (boolean) shell.evaluate(script);
    }

    public ValidateExecuter(String script, String scriptName, String description) {
        Assert.assertNotNull(script);
        Assert.assertNotNull(scriptName);
        Assert.assertNotNull(description);
        this.script = script;
        this.scriptName = scriptName;
        this.description = description;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScript() {
        return script;
    }
}
