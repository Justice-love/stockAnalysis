package org.eddy.web;

import org.eddy.dao.mapper.test.UserMapper;
import org.eddy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/12/4.
 */
@RestController
@RequestMapping("/hello")
@Profile("dev")
public class HelloWorld {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestService testService;

    @RequestMapping("/helloWorld")
    @Transactional
    public String helloWorld(@RequestParam String name) {
        userMapper.insert("helloWorld");
        System.out.println(1);
        testService.test();
        System.out.println(2);
//        throw new RuntimeException("1");
        return "test";
    }

    @Transactional
    public String t() {
        return userMapper.selectById(2).get(0).getName();
    }
}
