package org.eddy.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.eddy.ParseJob;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.exception.JsoupException;
import org.eddy.httpclient.HttpClientPraseJob;
import org.eddy.jsoup.JsoupParseJob;
import org.eddy.service.CrawlerService;
import org.eddy.xml.XmlContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/11.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService{

    private static final Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class);

    private ParseJob jsoupJob = new JsoupParseJob();
    private ParseJob httpClientJob = new HttpClientPraseJob();

    @Override
    public List<Stock> crawlStock() {
        List<Url> urlList = XmlContext.getContext().getUrls();
        List<Stock> stockList = urlList.parallelStream().map(s -> {
            try {
                Stock stock =  null;
                if (StringUtils.equals(Url.HTTPCLIENT_TYPE, s.getType())) {
                    stock = httpClientJob.crawlPage(s);
                } else if (StringUtils.equals(Url.JSOUP_TYPE, s.getType())) {
                    stock = jsoupJob.crawlPage(s);
                }
                return stock;
            } catch (JsoupException e) {
                logger.error("crawlStock error,url:" + s.getUrl(), e);
                return null;
            }
        }).filter(stock -> stock != null).collect(Collectors.toList());
        return stockList;
    }
}
