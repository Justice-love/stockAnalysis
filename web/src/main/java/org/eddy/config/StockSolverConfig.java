package org.eddy.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Justice-love on 2017/3/4.
 */
@ConfigurationProperties(prefix = "stock.im")
@Component
public class StockSolverConfig {

    private String mainToken;

    private String subToken;

    private String saleToken;

    private String url;

    private String arg;

    public String getSubToken() {
        return subToken;
    }

    public void setSubToken(String subToken) {
        this.subToken = subToken;
    }

    public String getMainToken() {
        return mainToken;
    }

    public void setMainToken(String mainToken) {
        this.mainToken = mainToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getSaleToken() {
        return saleToken;
    }

    public void setSaleToken(String saleToken) {
        this.saleToken = saleToken;
    }
}
