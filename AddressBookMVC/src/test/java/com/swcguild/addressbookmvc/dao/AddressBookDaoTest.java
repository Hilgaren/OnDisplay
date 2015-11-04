/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.model.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoTest {

    private AddressBookDao dao;

    public AddressBookDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("addressBookDao", AddressBookDao.class);
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from addresses");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressBookDao.
     */
    @Test
    public void addGetDeleteAddress() {
        System.out.println("addGetDeleteAddress");

        Address addr = new Address();
        addr.setAddressId(1);
        addr.setFirstName("First");
        addr.setLastName("Last");
        addr.setStreet("Street St.");
        addr.setCity("City");
        addr.setState("MN");
        addr.setZip("55555");

        dao.addAddress(addr);

        Address fromDB = dao.getAddressById(addr.getAddressId());
        assertEquals(fromDB, addr);
        dao.removeAddress(addr.getAddressId());
        assertNull(dao.getAddressById(addr.getAddressId()));

    }

    /**
     * Test of updateAddress method, of class AddressBookDao.
     */
    @Test
    public void addUpdateAddress() {
        System.out.println("addUpdateAddress");

        Address addr = new Address();
        addr.setAddressId(1);
        addr.setFirstName("First");
        addr.setLastName("Last");
        addr.setStreet("Street St.");
        addr.setCity("City");
        addr.setState("MN");
        addr.setZip("55555");

        dao.addAddress(addr);

        addr.setFirstName("newFirst");
        addr.setLastName("newLast");
        addr.setStreet("newStreet St.");
        addr.setCity("newCity");
        addr.setState("MN");
        addr.setZip("44444");

        dao.updateAddress(addr);

        Address newAddr = dao.getAddressById(addr.getAddressId());
        assertEquals(newAddr, addr);

    }

    /**
     * Test of getAllAddresses method, of class AddressBookDao.
     */
    @Test
    public void getAllAddresses() {
        System.out.println("getAllAddresses");

        Address addr = new Address();
        addr.setFirstName("First");
        addr.setLastName("Last");
        addr.setStreet("Street St.");
        addr.setCity("City");
        addr.setState("MN");
        addr.setZip("55555");
        dao.addAddress(addr);
        Address addr2 = new Address();
        addr2.setFirstName("First");
        addr2.setLastName("Last");
        addr2.setStreet("Street St.");
        addr2.setCity("City");
        addr2.setState("MN");
        addr2.setZip("55555");
        dao.addAddress(addr2);
        Address addr3 = new Address();
        addr3.setFirstName("First");
        addr3.setLastName("Last");
        addr3.setStreet("Street St.");
        addr3.setCity("City");
        addr3.setState("MN");
        addr3.setZip("55555");
        dao.addAddress(addr3);

        assertTrue(dao.getAllAddresses().size() == 3);

    }

    /**
     * Test of searchAddress method, of class AddressBookDao.
     */
    @Test
    public void searchAddress() {
        System.out.println("searchAddress");
        Address addr = new Address();
        addr.setFirstName("First");
        addr.setLastName("Last");
        addr.setStreet("Street St.");
        addr.setCity("City");
        addr.setState("MN");
        addr.setZip("55555");
        dao.addAddress(addr);
        
        Address addr2 = new Address();
        addr2.setFirstName("First");
        addr2.setLastName("Last");
        addr2.setStreet("Street St.");
        addr2.setCity("DifferentCity");
        addr2.setState("MN");
        addr2.setZip("55555");
        dao.addAddress(addr2);
        
        Address addr3 = new Address();
        addr3.setFirstName("First");
        addr3.setLastName("DifferentLast");
        addr3.setStreet("Street St.");
        addr3.setCity("City");
        addr3.setState("MN");
        addr3.setZip("55555");
        dao.addAddress(addr3);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.LAST_NAME, "DifferentLast");
        List<Address> addresses = dao.searchAddress(criteria);
        
        assertTrue(addresses.size()==1);
        assertEquals(addresses.get(0), addr3);
        
        criteria.put(SearchTerm.LAST_NAME, "Last");
        addresses = dao.searchAddress(criteria);
        assertTrue(addresses.size()==2);
        
        criteria.put(SearchTerm.CITY, "DifferentCity");
        addresses = dao.searchAddress(criteria);
        assertTrue(addresses.size()==1);
        assertEquals(addresses.get(0), addr2);
        
        criteria.put(SearchTerm.CITY, "City");
        addresses = dao.searchAddress(criteria);
        assertTrue(addresses.size()==1);
        assertEquals(addresses.get(0), addr);
        
        criteria.put(SearchTerm.CITY, "VeryDifferentCity");
        addresses = dao.searchAddress(criteria);
        assertTrue(addresses.isEmpty());
        
        
    }
}
