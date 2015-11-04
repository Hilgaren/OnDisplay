/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

/**
 *
 * @author apprentice
 */
import com.swcguild.addressbookmvc.model.Address;
import java.util.List;
import java.util.Map;

public interface AddressBookDao {
// add the given Address to the data store

    public Address addAddress(Address contact);

        // remove the Address with the given id from the data store
    public void removeAddress(int contactId);

        // update the given Address in the data store
    public void updateAddress(Address contact);

        // retrieve all Contacts from the data store
    public List<Address> getAllAddresses();

        // retrieve the Address with the given id from the data store
    public Address getAddressById(int contactId);

        // search for Contacts by the given search criteria values
    public List<Address> searchAddress(Map<SearchTerm, String> criteria);
}
