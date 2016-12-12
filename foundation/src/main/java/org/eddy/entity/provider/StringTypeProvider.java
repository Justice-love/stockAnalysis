package org.eddy.entity.provider;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.provider.define.TypeProvider;

import java.util.Optional;

/**
 * Created by eddy on 2016/12/8.
 */
public class StringTypeProvider implements TypeProvider<String>{

    @Override
    public String convert(String value) {
        return Optional.ofNullable(value).orElse(StringUtils.EMPTY);
    }
}
