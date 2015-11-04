/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.dao;

import com.swcguild.vendingmachinemvc.model.Item;
import com.swcguild.vendingmachinemvc.model.Money;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.hibernate.validator.constraints.Range;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao {

    private static final String SQL_INSERT_ITEM
            = "insert into items (name, quantity, cost) values (?, ?, ?)";

    private static final String SQL_DELETE_ITEM
            = "delete from items where item_id = ?";

    private static final String SQL_SELECT_ITEM
            = "select * from items where item_id = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from items";

    private static final String SQL_UPDATE_ITEM
            = "update items set name = ?, quantity = ?, cost = ? where item_id = ?";

    private JdbcTemplate jdbcTemplate;
//    @Range(max = 50, min = 0, message ="Deposit must be no more than $50.")
//    private int deposit = 0;
    Money deposit = new Money();
    

    public void setjdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item addItem(Item item) {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                item.getName(),
                item.getQuantity(),
                item.getPennyCost());
        item.setItemId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return item;
    }

    @Override
    public void removeItem(int itemId) {
        jdbcTemplate.update(SQL_DELETE_ITEM, itemId);
    }

    @Override
    public void updateItem(Item item) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getName(),
                item.getQuantity(),
                item.getPennyCost(),
                item.getItemId());
    }

    @Override
    public void vendItem(Item item) {
        
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getName(),
                item.getQuantity()-1,
                item.getPennyCost(),
                item.getItemId());
    }
    
    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new ItemMapper());
    }

    @Override
    public Item getItemById(int itemId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new ItemMapper(), itemId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Item> searchItems(Map<SearchTerm, String> criteria) {
        if (criteria.size() == 0) {
            return getAllItems();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from items where ");

            // build the where clause
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];

            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }

                sQuery.append(currentKey);
                sQuery.append(" = ? ");

                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            return jdbcTemplate.query(sQuery.toString(), new ItemMapper(), paramVals);
        }
    }

    @Override
    public int updateMoney(int money) {
        deposit.setDeposit(money);
        return deposit.getDeposit();
    }

    @Override
    public int getMoney() {
        return deposit.getDeposit();
    }

    private static final class ItemMapper implements ParameterizedRowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setItemId(rs.getInt("item_id"));
            item.setName(rs.getString("name"));
            item.setQuantity(Integer.parseInt(rs.getString("quantity")));
            item.setPennyCost(BigDecimal.valueOf(Double.parseDouble(rs.getString("cost"))));

            return item;
        }

    }
    

}


