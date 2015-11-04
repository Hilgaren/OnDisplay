/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.controller;

/**
 *
 * @author apprentice
 */
import com.swcguild.addressbookmvc.dao.AddressBookDao;
import com.swcguild.addressbookmvc.model.Address;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private AddressBookDao dao;

    @Inject
    public HomeController(AddressBookDao dao) {

        this.dao = dao;

    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String displayHomePage() {

        return "home";

    }
    
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String displayPost() {

        return "home";

    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address getAddress(@PathVariable("id") int id) {

        return dao.getAddressById(id);

    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Address createAddress(@Valid @RequestBody Address address) {

        dao.addAddress(address);

        return address;

    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable("id") int id) {

        dao.removeAddress(id);

    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAddress(@PathVariable("id") int id, @Valid  @RequestBody Address address) {

        address.setAddressId(id);

        dao.updateAddress(address);
        
    }
    
    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    @ResponseBody
    public List<Address> getAllAddresses() {
        
        return dao.getAllAddresses();
        
    }
}
