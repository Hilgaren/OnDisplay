/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.model;

import org.hibernate.validator.constraints.Range;

/**
 *
 * @author apprentice
 */
public class Money {

    @Range(max = 5, min = -5, message = "Deposit must be no more than $5.")
    private int deposit = 0;

    public Money() {
        deposit = 0;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int money) {
            deposit += money;

    }

}
