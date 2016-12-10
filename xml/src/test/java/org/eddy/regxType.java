package org.eddy;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eddy on 2016/12/10.
 */
public class regxType {

    @Test
    public void test() {
//        String content = "var hq_str_sh601006=\"大秦铁路,7.270,7.220,7.350,7.400,7.230,7.350,7.360,71578410,524244144.000,538600,7.350,101130,7.340,30500,7.330,140437,7.320,5400,7.310,213300,7.360,276903,7.370,575029,7.380,680700,7.390,1307048,7.400,2016-12-09,15:00:00,00\";";
        String content = "var hq_str_sh601006=\"\";";
        System.out.println(content);
        Pattern pattern = Pattern.compile("\\w*\\s+\\w+=\".+\";");
        Matcher matcher = pattern.matcher(content);
        System.out.println(matcher.matches());
    }

    @Test
    public void test2() {
        String content = "var hq_str_sh601006 aaaaaaa";
        System.out.println(content);
        Pattern pattern = Pattern.compile("var{1}+\\s{1}+\\w+(\\s{1,})+\\w*");
        Matcher matcher = pattern.matcher(content);
        System.out.println(matcher.matches());
        System.out.println(matcher.groupCount());
        System.out.println(matcher.find());
    }

    @Test
    public void test3() {
        String content = "asdfsadf";
        Pattern pattern = Pattern.compile("a{1}+(\\w)");
        Matcher matcher = pattern.matcher(content);
        System.out.println(matcher.matches());
        System.out.println(matcher.groupCount());
        System.out.println(matcher.find());
    }

    @Test
    public void test4() {
        String content = "var hq_str_sh601006=\"大秦铁路,7.270,7.220,7.350,7.400,7.230,7.350,7.360,71578410,524244144.000,538600,7.350,101130,7.340,30500,7.330,140437,7.320,5400,7.310,213300,7.360,276903,7.370,575029,7.380,680700,7.390,1307048,7.400,2016-12-09,15:00:00,00\";";
        System.out.println(content.substring(content.indexOf("\"") + 1));
        String temp = content.substring(content.indexOf("\"") + 1);
        System.out.println(temp.substring(0, temp.indexOf("\"")));
        String a = temp.substring(0, temp.indexOf("\""));
        System.out.println(a.split(",").length);
    }

    @Test
    public void test5() {
        String content = "asdf+asdf";
        Pattern pattern = Pattern.compile("(\\w*)([\\+|\\-|\\*|/])(\\w*)");
        Matcher matcher = pattern.matcher(content);
        System.out.println(matcher.matches());
        System.out.println(matcher.find());
    }

    @Test
    public void test6() {
        String content = "asdf + asdf";
        String[] arr = content.split("\\s");
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
