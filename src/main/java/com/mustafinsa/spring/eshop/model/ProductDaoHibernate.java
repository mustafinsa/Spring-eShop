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
public class ProductDaoHibernate implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Product product) {
        session().saveOrUpdate(product);
    }

    @Override
    public Product getProduct(int id) {
        Criteria criteria = session().createCriteria(Product.class);
        criteria.add(Restrictions.idEq(id));
        return (Product) criteria.uniqueResult();
    }

    @Override
    public List<Product> getProducts() {
        return session().createCriteria(Product.class).list();
    }

    @Override
    public boolean delete(int id) {
        Query query = session().createQuery("DELETE FROM Product WHERE id=:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }
}
