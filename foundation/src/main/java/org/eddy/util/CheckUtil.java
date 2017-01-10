package org.eddy.util;

import org.eddy.entity.check.BlankCheck;
import org.eddy.entity.check.GroupCheck;
import org.eddy.entity.provider.define.TypeProvider;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by eddy on 2017/1/10.
 */
public class CheckUtil {

    public static void notBlank(Object object) {
        Map<String, List<String>> arg1 = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(f -> {
            if (f.isAnnotationPresent(BlankCheck.class)) {
                arg1.put(f.getName(), Arrays.asList(f.getName()));
            } else if (f.isAnnotationPresent(GroupCheck.class)) {
                String key = f.getAnnotation(GroupCheck.class).name();
                if (!arg1.containsKey(key)) {
                    arg1.put(key, new ArrayList<String>());
                }
                arg1.get(key).add(f.getName());
            }
        });

        arg1.values().stream().forEach(strings -> {
            try {
                if (strings.size() == 1) {
                    TypeProvider r = BeansUtil.readPropertieWithType(object, strings.get(0));
                    if (!r.isNotBlank()) {
                        throw new RuntimeException("empty filed");
                    }
                } else if (strings.size() > 1) {
                    int result = 0;
                    for (String s : strings) {
                        TypeProvider r = BeansUtil.readPropertieWithType(object, s);
                        if (r.isNotBlank()) {
                            result = result | 1;
                        }
                    }
                    if (result == 0) {
                        throw new RuntimeException("empty filed");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
