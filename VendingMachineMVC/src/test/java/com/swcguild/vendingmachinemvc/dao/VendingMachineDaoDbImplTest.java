/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.dao;

import com.swcguild.vendingmachinemvc.model.Item;
import java.math.BigDecimal;
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
public class VendingMachineDaoDbImplTest {
    
    VendingMachineDao dao;
    
    public VendingMachineDaoDbImplTest() {
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
        dao = ctx.getBean("VendingMachineDao", VendingMachineDao.class);
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from items");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class ItemBookDao.
     */
    @Test
    public void addGetDeleteItem() {
        System.out.println("addGetDeleteItem");

        Item item = new Item();
        item.setItemId(1);
        item.setName("Name");
        item.setQuantity(50);
        item.setPennyCost(new BigDecimal(100));

        dao.addItem(item);

        Item fromDB = dao.getItemById(item.getItemId());
        assertEquals(fromDB, item);
        dao.removeItem(item.getItemId());
        assertNull(dao.getItemById(item.getItemId()));

    }

    /**
     * Test of updateItem method, of class ItemBookDao.
     */
    @Test
    public void addUpdateItem() {
        System.out.println("addUpdateItem");

        Item item = new Item();
        item.setName("Name");
        item.setQuantity(50);
        item.setPennyCost(new BigDecimal(100));

        dao.addItem(item);

        Item item2 = new Item();
        item2.setName("Name");
        item2.setQuantity(50);
        item2.setPennyCost(new BigDecimal(100));

        dao.addItem(item2);

        Item newItem = dao.getItemById(item.getItemId());
        assertEquals(newItem, item);

    }

    /**
     * Test of getAllItemes method, of class ItemBookDao.
     */
    @Test
    public void getAllItemes() {
        System.out.println("getAllItems");

        Item item = new Item();
        item.setName("Name");
        item.setQuantity(50);
        item.setPennyCost(new BigDecimal(100));
        dao.addItem(item);
        Item item2 = new Item();
        item2.setName("Name");
        item2.setQuantity(50);
        item2.setPennyCost(new BigDecimal(100));
        dao.addItem(item2);
       Item item3 = new Item();
        item3.setName("Name");
        item3.setQuantity(50);
        item3.setPennyCost(new BigDecimal(100));
        dao.addItem(item3);

        assertTrue(dao.getAllItems().size() == 3);

    }

    /**
     * Test of searchItem method, of class ItemBookDao.
     */
    @Test
    public void searchItem() {
        System.out.println("searchItem");
        Item item = new Item();
        item.setName("DifferentName");
        item.setQuantity(50);
        item.setPennyCost(new BigDecimal(100));
        dao.addItem(item);
        Item item2 = new Item();
        item2.setName("DifferentName");
        item2.setQuantity(40);
        item2.setPennyCost(new BigDecimal(100));
        dao.addItem(item2);
        Item item3 = new Item();
        item3.setName("Name");
        item3.setQuantity(50);
        item3.setPennyCost(new BigDecimal(100));
        dao.addItem(item3);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.NAME, "DifferentName");
        List<Item> items = dao.searchItems(criteria);
        assertTrue(items.size() == 2);
        assertEquals(items.get(0), item);
        criteria.put(SearchTerm.QUANTITY, "40");
        items = dao.searchItems(criteria);
        assertTrue(items.size() == 1);
        assertEquals(items.get(0), item2);
        criteria.put(SearchTerm.COST, "100");
        items = dao.searchItems(criteria);
        assertTrue(items.size() == 1);
        assertEquals(items.get(0), item2);

    }
    
}
