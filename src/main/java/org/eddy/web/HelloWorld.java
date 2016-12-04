package org.eddy.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/12/4.
 */
@RestController
@RequestMapping
public class HelloWorld {

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }
}
