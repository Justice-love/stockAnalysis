package org.eddy.entity.provider;

import org.eddy.entity.provider.define.TypeProvider;

/**
 * Created by eddy on 2017/1/10.
 */
public class ObjectTypeProvider implements TypeProvider<Object>{

    private Object value;

    @Override
    public Object convert(String value) {
        return value;
    }

    @Override
    public boolean isNotBlank() {
        return value != null;
    }

    @Override
    public Object hold(Object object) {
        value = object;
        return object;
    }

    @Override
    public Object get() {
        return value;
    }
}
