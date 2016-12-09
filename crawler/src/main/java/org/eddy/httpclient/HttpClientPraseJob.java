package org.eddy.httpclient;

import org.eddy.ParseJob;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.exception.JsoupException;

/**
 * Created by eddy on 2016/12/9.
 */
public class HttpClientPraseJob implements ParseJob {

    @Override
    public Stock crawlPage(Url url) throws JsoupException {
        return null;
    }
}
