/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinemvc.model;

/**
 *
 * @author apprentice
 */
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;
public class Item {

    private int itemId;
    @NotNull(message ="You must supply a value for quantity.")
    @Range(max = 50, min = 1, message ="Quantity must be greater than 0 and 50 or less.")
    private int quantity;
    @NotEmpty(message ="You must supply a value for Name.")
    @Length(max = 15, message ="Name must be no more than 15 characters in length.")
    private String name;
    @NotNull(message ="You must supply a value for Cost.")
    @Range(max = 5, min = 0, message ="Cost must be no more than 5 dollars.")
    private BigDecimal pennyCost;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPennyCost() {
        return pennyCost;
    }

    public void setPennyCost(BigDecimal pennyCost) {
        this.pennyCost = pennyCost;
    }

        @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.pennyCost.intValueExact(), other.pennyCost.intValueExact())) {
            return false;
        }
        return true;
    }
    
}
