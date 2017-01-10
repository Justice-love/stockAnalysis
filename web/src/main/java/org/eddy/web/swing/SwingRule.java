package org.eddy.web.swing;

import org.eddy.manager.SwingScriptManager;
import org.eddy.web.requestEntity.SwingFormList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eddy on 2017/1/7.
 */
@Controller
@RequestMapping("/swing")
public class SwingRule {

    @Autowired
    private SwingScriptManager scriptManager;

    @RequestMapping("/rule")
    public String rule(Model model) {
        model.addAttribute("validates", scriptManager.getAllSwings());
        return "swing/swingRule";
    }

    @RequestMapping(value = "/swingAdd", method = RequestMethod.POST)
    public String swingAdd(SwingFormList formList, Model model) {
        scriptManager.insertSwings(formList.getList());
        return "swing/swingRule";
    }

}
