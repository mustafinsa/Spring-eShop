package com.mustafinsa.spring.eshop.model;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class ShoppingCartDaoJpaImpl implements ShoppingCartDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrUpdate(ShoppingCart shoppingCart) {
        entityManager.persist(shoppingCart);
    }

    @Override
    public boolean currentCartExist(String username, int itemId) {
        return entityManager.createQuery("SELECT COUNT(*) FROM ShoppingCart WHERE user.username = :username AND itemId = :itemId", Long.class)
                .setParameter("username", username)
                .setParameter("itemId", itemId)
                .getSingleResult() > 0;
    }

    @Override
    public ShoppingCart getCart(String username, int itemId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ShoppingCart> criteria = cb.createQuery(ShoppingCart.class);
        Root<ShoppingCart> shoppingCart = criteria.from(ShoppingCart.class);
        criteria.select(shoppingCart);
        criteria.where(
                cb.equal(shoppingCart.get("user").get("username"), username),
                cb.equal(shoppingCart.get("itemId"), itemId),
                cb.equal(shoppingCart.get("purchased"), false));

        return entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<ShoppingCart> getCart(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ShoppingCart> criteria = cb.createQuery(ShoppingCart.class);
        Root<ShoppingCart> root = criteria.from(ShoppingCart.class);
        criteria.select(root);
        criteria.where(
                cb.equal(root.get("user").get("username"), username),
                cb.equal(root.get("purchased"), false));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<ShoppingCart> getCarts() {

        return entityManager.createQuery("SELECT sc FROM ShoppingCart sc", ShoppingCart.class).getResultList();
    }
}