package org.eddy.web.stock;

import org.eddy.manager.StockBuyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eddy on 2016/12/17.
 */
@Controller
@RequestMapping("/stock")
public class ToBuy {

    @Autowired
    private StockBuyManager stockBuyManager;

    @RequestMapping("/toBuy")
    public String showToBuy(Model model) {
        model.addAttribute("toBuys", stockBuyManager.selectAll());
        return "/stock/canBuy";
    }
}
