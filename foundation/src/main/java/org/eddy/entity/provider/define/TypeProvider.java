package org.eddy.entity.provider.define;

/**
 * Created by eddy on 2016/12/8.
 */
public interface TypeProvider<T> {

    T convert(String value);

    boolean isNotBlank(Object object);
}
