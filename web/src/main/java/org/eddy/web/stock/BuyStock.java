package org.eddy.web.stock;

import org.eddy.manager.StockBuyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by eddy on 2016/12/17.
 */
@Controller
@RequestMapping("/stock")
public class BuyStock {

    @Autowired
    private StockBuyManager stockBuyManager;

    @RequestMapping("/ignore")
    public String ignore(@RequestParam("id") int id) {
        stockBuyManager.deleteOneById(id);
        return "redirect:/stock/toBuy.html";
    }

    @RequestMapping("/buy")
    public String buy(@RequestParam("id") int id) {
        stockBuyManager.buyStock(id);
        return "redirect:/stock/show.html";
    }

    @RequestMapping("/show")
    public String show(Model model) {
        model.addAttribute("bought", stockBuyManager.showBought());
        return "stock/hadBought";
    }
}
