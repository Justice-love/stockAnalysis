package org.eddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by admin on 2016/12/4.
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class);
    }
}
