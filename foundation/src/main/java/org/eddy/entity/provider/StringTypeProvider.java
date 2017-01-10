package org.eddy.entity.provider;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.provider.define.TypeProvider;

import java.util.Optional;

/**
 * Created by eddy on 2016/12/8.
 */
public class StringTypeProvider implements TypeProvider<String>{

    private String value;

    @Override
    public String convert(String value) {
        return Optional.ofNullable(value).orElse(StringUtils.EMPTY);
    }

    @Override
    public boolean isNotBlank() {
        if (null == value) {
            return false;
        }
        return StringUtils.isNotBlank(value.toString());
    }

    @Override
    public String hold(Object object) {
        value = (String) object;
        return value;
    }

    @Override
    public String get() {
        return value;
    }


}
