package org.eddy.web.stock;

import org.eddy.annotation.LoginCheck;
import org.eddy.entity.Stock;
import org.eddy.manager.StockBuyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    @LoginCheck
    public String ignore(@RequestParam("id") int id) {
        stockBuyManager.deleteOneById(id);
        return "redirect:/stock/toBuy.html";
    }

    @RequestMapping("/buy")
    @LoginCheck
    public String buy(@RequestParam("id") int id) {
        stockBuyManager.buyStock(id);
        return "redirect:/stock/show.html";
    }

    @RequestMapping("/show")
    @LoginCheck
    public String show(Model model) {
        model.addAttribute("bought", stockBuyManager.showBought());
        return "stock/hadBought";
    }

    @RequestMapping("/showDetail/{code}")
    @LoginCheck
    public String showDetail(Model model, @PathVariable("code") String code) {
        Stock stock = stockBuyManager.findStockDetail(code);
        model.addAttribute("stock", stock);
        model.addAttribute("code", code);
        return "stock/detail";
    }
}
