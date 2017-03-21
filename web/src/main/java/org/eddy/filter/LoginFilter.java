package org.eddy.filter;

import org.eddy.aop.LoginCheckAspect;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by eddy on 2017/3/21.
 */
@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if (Optional.ofNullable(session.getAttribute(LoginCheckAspect.LOGIN_ATTR_KEY)).map(s -> s instanceof Boolean ? Boolean.valueOf(s.toString()) : false).orElseGet(() -> false)) {
            httpServletRequest.setAttribute(LoginCheckAspect.LOGIN_ATTR_KEY, true);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
