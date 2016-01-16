package com.mustafinsa.spring.eshop.model;

import javax.persistence.*;

@Entity
@Table(name = "ShoppingCarts")
public class ShoppingCart {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    private Integer itemId;
    private Integer itemQuantity;
    private Boolean purchased;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer itemId, User user, Integer itemQuantity, Boolean purchased) {
        this.itemId = itemId;
        this.user = user;
        this.itemQuantity = itemQuantity;
        this.purchased = purchased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (itemQuantity != null ? !itemQuantity.equals(that.itemQuantity) : that.itemQuantity != null) return false;
        return !(purchased != null ? !purchased.equals(that.purchased) : that.purchased != null);

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (itemQuantity != null ? itemQuantity.hashCode() : 0);
        result = 31 * result + (purchased != null ? purchased.hashCode() : 0);
        return result;
    }
}
