package org.eddy.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Justice-love on 2017/3/4.
 */
@ConfigurationProperties(prefix = "stock")
@Getter
@Setter
@ToString
public class StockSolverConfig {

    private String token;

    private String url;

    private String arg;
}
