package com.mustafinsa.spring.eshop.model;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ProductDaoJpaImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public Product getProduct(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> getProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public boolean delete(int id) {
        Query query = entityManager.createQuery("DELETE FROM Product WHERE id=:id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
