package org.eddy.xml.org.eddy.extend.define;

import org.eddy.entity.Url;

import java.util.List;

/**
 * Created by eddy on 2016/12/10.
 */
public interface UrlExtendProvider {

    List<Url> extend(Url url);
}
