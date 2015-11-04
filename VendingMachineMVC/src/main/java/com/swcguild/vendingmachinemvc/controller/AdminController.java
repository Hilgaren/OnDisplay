/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.controller;

import com.swcguild.vendingmachinemvc.dao.VendingMachineDao;
import com.swcguild.vendingmachinemvc.model.Item;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AdminController {

     private VendingMachineDao dao;

    @Inject
    public AdminController(@Valid VendingMachineDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "admin";
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Item createItem(@Valid @RequestBody Item item) {
        dao.addItem(item);
        return item;

    }
}
