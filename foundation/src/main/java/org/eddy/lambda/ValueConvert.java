package org.eddy.lambda;

/**
 * Created by admin on 2016/12/4.
 */
@FunctionalInterface
public interface ValueConvert<T> {

    T convert(String content);
}
