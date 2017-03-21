package org.eddy.annotation;

import java.lang.annotation.*;

/**
 * 用来标记需要登陆校验的接口
 * Created by eddy on 2017/3/21.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginCheck {
}
