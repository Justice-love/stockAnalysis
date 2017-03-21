package org.eddy.web.login;

import org.apache.commons.lang3.StringUtils;
import org.eddy.aop.LoginCheckAspect;
import org.eddy.config.LoginUserMessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by eddy on 2017/3/21.
 */
@Controller
@RequestMapping("/login")
public class Login {

    @Autowired
    private LoginUserMessageConfig loginConfig;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String login(HttpSession session, String password) {
        if (StringUtils.equals(password, loginConfig.getPassword())) {
            session.setAttribute(LoginCheckAspect.LOGIN_ATTR_KEY, true);
        }
        return "redirect:/stock/show.html";
    }

    @RequestMapping("/toLogin")
    public String login() {
        return "login/login";
    }
}
