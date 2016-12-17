package org.eddy.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eddy on 2016/12/17.
 */
@Controller
@RequestMapping("/v1")
public class User {

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("name", "eddy");
        model.addAttribute("id", "1");
        return "v1/user";
    }

}
