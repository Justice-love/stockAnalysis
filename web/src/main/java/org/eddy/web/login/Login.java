package org.eddy.web.login;

import org.eddy.aop.LoginCheckAspect;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by eddy on 2017/3/21.
 */
@Controller
@RequestMapping("/login")
public class Login {

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String login(HttpSession session) {
        session.setAttribute(LoginCheckAspect.LOGIN_ATTR_KEY, true);
        return "stock/hadBought";
    }
}
