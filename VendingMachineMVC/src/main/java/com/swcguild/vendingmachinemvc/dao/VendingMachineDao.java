/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.dao;

import com.swcguild.vendingmachinemvc.model.Item;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface VendingMachineDao {
    
    public Item addItem(Item item);

        // remove the Item with the given id from the data store
    public void removeItem(int itemId);

        // update the given Item in the data store
    public void updateItem(Item item);
    
    public void vendItem(Item item);

        // retrieve all Contacts from the data store
    public List<Item> getAllItems();

        // retrieve the Item with the given id from the data store
    public Item getItemById(int itemId);

        // search for Contacts by the given search criteria values
    public List<Item> searchItems(Map<SearchTerm, String> criteria);
    
    public int updateMoney(int money);
    
    public int getMoney();
}
