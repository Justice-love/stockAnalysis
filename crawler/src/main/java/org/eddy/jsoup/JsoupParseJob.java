package org.eddy.jsoup;

import org.eddy.ParseJob;
import org.eddy.entity.SelectType;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.exception.JsoupException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by eddy on 2016/12/8.
 */
public class JsoupParseJob extends ParseJob{

    @Override
    public Stock crawlPage(Url url) throws JsoupException{
        try {
            assert url != null;
            Document document = Jsoup.connect(url.getUrl()).get();
            if (!url.getTest().test(document)) {
                return null;
            }
            Stock result = new Stock();

            List<Url.UrlRule> ruleList =  url.getUrlRuleList();
            ruleList.stream().filter(r -> SelectType.JSOUP_TYPE.equals(r.getSelectType().getType())).forEach(r -> {
                String elementText = r.getSelectType().findElement(document, r.getExpression());
                try {
                    writePropertie(result, r.getProperty(), elementText);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return result;
        } catch (IOException e) {
            throw new JsoupException(e);
        } catch (Exception e) {
            throw new JsoupException(e);
        }
    }

}
