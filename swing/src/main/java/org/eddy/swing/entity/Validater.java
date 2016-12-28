package org.eddy.swing.entity;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.Stock;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * Created by eddy on 2016/12/13.
 */
public abstract class Validater {

    private static Map<String, Validater> validaterMap = new ConcurrentHashMap<>();

    protected static String CHAR = "#";

    public abstract boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect);

    public abstract String getDescription();

    protected void register(String name) {
        validaterMap.put(name, this);
    }

    public static Validater valueOf(String name) {
        if (StringUtils.isBlank(name) || !validaterMap.containsKey(name)) {
            throw new IllegalArgumentException("name is incorrect, name:" + name);
        }
        return validaterMap.get(name);
    }

    public abstract String name();

}
