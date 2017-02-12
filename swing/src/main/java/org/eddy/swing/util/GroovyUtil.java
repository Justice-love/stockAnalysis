package org.eddy.swing.util;

import groovy.lang.GroovyClassLoader;
import org.eddy.swing.entity.Validater;

/**
 * Created by eddy on 2017/2/12.
 */
public class GroovyUtil {

    public static Validater genGroovyValidater(String  groovyScript) throws IllegalAccessException, InstantiationException {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        Class glass = groovyClassLoader.parseClass(groovyScript);
        return (Validater) glass.newInstance();
    }
}
