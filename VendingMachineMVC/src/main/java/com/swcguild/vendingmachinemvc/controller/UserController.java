/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.controller;

/**
 *
 * @author apprentice
 */
import com.swcguild.vendingmachinemvc.dao.VendingMachineDao;
import com.swcguild.vendingmachinemvc.model.Item;
import com.swcguild.vendingmachinemvc.model.Money;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class UserController {

    private VendingMachineDao dao;

    @Inject
    public UserController(VendingMachineDao dao) {
        this.dao = dao;

    }

    @RequestMapping(value = {"/", "/user"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "user";

    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItem(@PathVariable("id") int id) {
        return dao.getItemById(id);

    }

    

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("id") int id) {
        dao.removeItem(id);

    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putItem(@PathVariable("id") int id, @RequestBody Item item) {
        item.setItemId(id);
        dao.updateItem(item);

    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public String vendItem(@PathVariable("id") int id) {

        if (dao.getItemById(id).getQuantity() != 0 && dao.getItemById(id).getPennyCost().intValueExact()*100 <= dao.getMoney()) {
            System.out.println(dao.getItemById(id).getPennyCost().intValueExact()*100);
            System.out.println("<="+dao.getMoney());
            dao.vendItem(dao.getItemById(id));
            dao.updateMoney(-dao.getItemById(id).getPennyCost().intValue()*100);
        return "purchase successful!";
        }
        else{
        return "purchase unsuccessful!";
        }

    }

    @RequestMapping(value = "/money", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public int updateMoney(@Valid @RequestBody Money money) {
        return dao.updateMoney((int) (money.getDeposit() * 100));

    }

    @RequestMapping(value = "/money", method = RequestMethod.GET)
    @ResponseBody
    public int getMoney() {
        return dao.getMoney();

    }
    
    @RequestMapping(value = "/money", method = RequestMethod.PATCH)
    @ResponseBody
    public int dispenseMoney() {
        return dao.updateMoney(-dao.getMoney());

    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems() {
        return dao.getAllItems();

    }
     @RequestMapping(value={"/mainAjaxPage"}, method=RequestMethod.GET)
 public String displayMainAjaxPage() {
 return "mainAjaxPage";
 }
}
