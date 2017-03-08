package org.eddy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Properties;

/**
 * Created by eddy on 2017/3/8.
 */
@ConfigurationProperties("stock.dataSource")
@Component
public class DataSourceConfig {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String minIdle;
    private String maxActive;
    private String initialSize;
    private String timeBetweenEvictionRunsMillis;
    private String minEvictableIdleTimeMillis;
    private String validationQuery;
    private String testWhileIdle;
    private String testOnBorrow;
    private String testOnReturn;

    public Properties genProperties() {
        Properties properties = new Properties();
        Optional.ofNullable(url).ifPresent(s -> properties.put("url", url));
        Optional.ofNullable(username).ifPresent(s -> properties.put("username", username));
        Optional.ofNullable(password).ifPresent(s -> properties.put("password", password));
        Optional.ofNullable(driverClassName).ifPresent(s -> properties.put("driverClassName", driverClassName));
        Optional.ofNullable(minIdle).ifPresent(s -> properties.put("minIdle", minIdle));
        Optional.ofNullable(maxActive).ifPresent(s -> properties.put("maxActive", maxActive));
        Optional.ofNullable(initialSize).ifPresent(s -> properties.put("initialSize", initialSize));
        Optional.ofNullable(timeBetweenEvictionRunsMillis).ifPresent(s -> properties.put("timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis));
        Optional.ofNullable(minEvictableIdleTimeMillis).ifPresent(s -> properties.put("minEvictableIdleTimeMillis", minEvictableIdleTimeMillis));
        Optional.ofNullable(validationQuery).ifPresent(s -> properties.put("validationQuery", validationQuery));
        Optional.ofNullable(testWhileIdle).ifPresent(s -> properties.put("testWhileIdle", testWhileIdle));
        Optional.ofNullable(testOnBorrow).ifPresent(s -> properties.put("testOnBorrow", testOnBorrow));
        Optional.ofNullable(testOnReturn).ifPresent(s -> properties.put("testOnReturn", testOnReturn));
        return properties;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(String initialSize) {
        this.initialSize = initialSize;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public String getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(String testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(String testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(String testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
