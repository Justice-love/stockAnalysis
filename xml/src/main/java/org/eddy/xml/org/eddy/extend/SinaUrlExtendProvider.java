package org.eddy.xml.org.eddy.extend;

import org.eddy.entity.Url;
import org.eddy.xml.org.eddy.extend.define.UrlExtendProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eddy on 2016/12/10.
 */
public class SinaUrlExtendProvider implements UrlExtendProvider {

    private int min = 600_000;

    private int max = 603_999;

    private String perfix = "sh";

    @Override
    public List<Url> extend(Url url) {
        String u = url.getUrl();
        List<Url> result = new ArrayList<>(11_000);
        for (int i = min; i <= max; i++) {
            result.add(url.copy(String.format(u, perfix + i)));
        }
        return result;
    }
}
