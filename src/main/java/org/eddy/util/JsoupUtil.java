package org.eddy.util;

import org.eddy.entity.JsoupParam;
import org.eddy.exception.JsoupException;
import org.eddy.lambda.ValueConvert;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by admin on 2016/12/4.
 */
public class JsoupUtil {

    public static <T> T findElementValue(List<JsoupParam> paramList, ValueConvert<T> convert) throws JsoupException{
        assert paramList != null && paramList.size() != 0;
        Element endElement = null;
        for (JsoupParam jsoupParam : paramList) {
            endElement = jsoupParam.getSelectType().findElement(jsoupParam.getElement(), jsoupParam.getExpression());
        }
        if (null == endElement) {
            throw new JsoupException("do not find any element");
        }
        return convert.convert(endElement.text());
    }
}
