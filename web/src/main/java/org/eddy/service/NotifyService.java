package org.eddy.service;

import org.eddy.swing.entity.SwingValidateContext;

/**
 * Created by eddy on 16/12/16.
 */
public interface NotifyService {

    /**
     *
     * @param context
     * @param others 0:template 1:to 2:subject
     */
    void notify(SwingValidateContext context, String ... others);

    void asyncNotify(SwingValidateContext context, String ... others);
}
