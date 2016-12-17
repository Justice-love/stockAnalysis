package org.eddy.swing;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eddy on 2016/12/17.
 */
public class RegxTest {

    @Test
    public void test() {
        Pattern pattern = Pattern.compile("^\\{\\w+\\}$");
        String text = "asdfasdf";
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.matches());
    }

    @Test
    public void test2() {
        Pattern pattern = Pattern.compile("^\\{\\w+\\}\\s+[+|-|*|/]\\s+\\{\\w+\\}$");
        String text = "{aa} + {b}";
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.matches());
    }

    @Test
    public void test3() {
        System.out.println("{aa} + {b}".contains("{") && "{aa} + {b}".contains("}"));
    }

    @Test
    public void test4() {
        String content = "{aa}";
        content = content.substring(0, content.length() - 1).substring(1);
        System.out.println(content);
    }
}
