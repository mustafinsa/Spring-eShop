package com.mustafinsa.spring.eshop.model;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ShoppingCartDaoHibernate implements ShoppingCartDao {

    @Autowired
    SessionFactory sessionFactory;
    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(ShoppingCart shoppingCart) {
        session().saveOrUpdate(shoppingCart);
    }

    @Override
    public boolean currentCartExist(String username) {
        Criteria criteria = session().createCriteria(ShoppingCart.class);
        criteria.createAlias("user", "u");
        criteria.add(Restrictions.eq("u.username", username));
        criteria.add(Restrictions.eq("purchased", false));
        return criteria.list() != null;
    }

    @Override
    public List<ShoppingCart> getCarts() {
        return session().createCriteria(ShoppingCart.class).list();
    }
}
