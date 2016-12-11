package org.eddy.job;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2016/12/10.
 */
@Component
@Configurable
//@EnableScheduling
public class crawlUrl {

    @Scheduled(fixedRate = 1_000,initialDelay = 3_000)
    public void crawlAllUrl() {
        System.out.println(System.currentTimeMillis());
    }

}
