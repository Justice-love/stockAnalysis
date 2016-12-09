package org.eddy;

import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.exception.JsoupException;

/**
 * Created by eddy on 2016/12/9.
 */
public interface ParseJob {
    Stock crawlPage(Url url) throws JsoupException;
}
