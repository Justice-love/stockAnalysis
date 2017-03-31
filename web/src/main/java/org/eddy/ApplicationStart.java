package org.eddy;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.eddy.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by admin on 2016/12/4.
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
public class ApplicationStart {

    @Autowired
    private DataSourceConfig config;

    @Bean
    public DataSource definitionDataSource() throws Exception {
        return DruidDataSourceFactory.createDataSource(config.genProperties());
    }

    @Bean
    public EmbeddedServletContainerCustomizer customizer() throws Exception {
        return (ConfigurableEmbeddedServletContainer container) -> container.setSessionTimeout(1 * 60 * 60);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationStart.class);
        springApplication.addListeners(new ApplicationPidFileWriter("stock.pid"));
        springApplication.run(args);
    }
}
