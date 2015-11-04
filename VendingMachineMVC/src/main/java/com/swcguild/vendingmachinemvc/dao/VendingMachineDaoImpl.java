/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.dao;

import com.swcguild.vendingmachinemvc.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.hibernate.validator.constraints.*;
/**
 *
 * @author apprentice
 */
public class VendingMachineDaoImpl implements VendingMachineDao{

    private Map<Integer, Item> itemList = new HashMap<>();
    @Range(max = 50, min = 0, message ="Deposit must be no more than $50.")
    private int deposit = 0;
    private static int itemIdCounter = 0;

    
    @Override
    public int updateMoney(int money){
        deposit +=money;
        return deposit;
    }
    
    @Override
    public int getMoney(){
        return deposit;
    }
    
    @Override
    public Item addItem(Item item) {
        item.setItemId(itemIdCounter);
        itemIdCounter++;

        itemList.put(item.getItemId(), item);
        return item;
    }

    @Override
    public void removeItem(int addressId) {
        itemList.remove(addressId);
    }

    @Override
    public void updateItem(Item address) {
        itemList.put(address.getItemId(), address);
    }

    @Override
    public List<Item> getAllItems() {
        Collection<Item> c = itemList.values();
        return new ArrayList(c);
    }

    @Override
    public Item getItemById(int addressId) {
        return itemList.get(addressId);
    }

    @Override
    public List<Item> searchItems(Map<SearchTerm, String> criteria) {

        String nameCriteria = criteria.get(SearchTerm.NAME);

        Predicate<Item> nameMatches;

        Predicate<Item> truePredicate = (a) -> {
            return true;
        };

        nameMatches = (nameCriteria == null || nameCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getName().equals(nameCriteria);

        return itemList
                .values()
                .stream()
                .filter(nameMatches)
                .collect(Collectors.toList());
    }


    @Override
    public void vendItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
