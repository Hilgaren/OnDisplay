/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.model.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoImpl implements AddressBookDao {
    
    private Map<Integer, Address> addressBook = new HashMap<>();
    private static int addressIdCounter = 0;
    
    @Override
    public Address addAddress(Address address) {
        
        address.setAddressId(addressIdCounter);
        addressIdCounter++;
        
        addressBook.put(address.getAddressId(), address);
        return address;
    }

    @Override
    public void removeAddress(int addressId) {
        addressBook.remove(addressId);
    }

    @Override
    public void updateAddress(Address address) {
        addressBook.put(address.getAddressId(), address);
    }

    @Override
    public List<Address> getAllAddresses() {
        Collection<Address> c = addressBook.values();
        return new ArrayList(c);
    }

    @Override
    public Address getAddressById(int addressId) {
        return addressBook.get(addressId);
    }

    @Override
    public List<Address> searchAddress(Map<SearchTerm, String> criteria) {
        
        String fnameCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lnameCriteria = criteria.get(SearchTerm.LAST_NAME);
        String streetCriteria = criteria.get(SearchTerm.STREET);
        String cityCriteria = criteria.get(SearchTerm.CITY);
        String stateCriteria = criteria.get(SearchTerm.STATE);
        String zipCriteria = criteria.get(SearchTerm.ZIP);
        
        Predicate<Address> firstNameMatches;
        Predicate<Address> lastNameMatches;
        Predicate<Address> streetMatches;
        Predicate<Address> cityMatches;
        Predicate<Address> stateMatches;
        Predicate<Address> zipMatches;
        
        Predicate<Address> truePredicate = (a) -> {return true;};
        
        firstNameMatches = (fnameCriteria == null || fnameCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getFirstName().equals(fnameCriteria);
        
        lastNameMatches = (lnameCriteria == null || lnameCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getLastName().equals(lnameCriteria);
        
        streetMatches = (streetCriteria == null || streetCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getStreet().equals(streetCriteria);
        
        cityMatches = (cityCriteria == null || cityCriteria.isEmpty()) 
                ? truePredicate
                : (a) -> a.getCity().equals(cityCriteria);
        
        stateMatches = (stateCriteria == null || stateCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getState().equals(stateCriteria);
        
        zipMatches = (zipCriteria == null || zipCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getZip().equals(zipCriteria);
        
        return addressBook
                        .values()
                        .stream()
                        .filter(firstNameMatches
                                .and(lastNameMatches)
                                .and(streetMatches)
                                .and(cityMatches)
                                .and(stateMatches)
                                .and(zipMatches))
                        .collect(Collectors.toList());
    }
    
}
