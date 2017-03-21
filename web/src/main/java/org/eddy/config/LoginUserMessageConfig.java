package org.eddy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/3/21.
 */
@ConfigurationProperties("stock.login")
@Component
public class LoginUserMessageConfig {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
