package com.mustafinsa.spring.eshop.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:spring-core-config.xml",
        "classpath:spring-datasource-config.xml",
        "classpath:spring-security-config.xml",})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private DataSource dataSource;

    private Product product1 = new Product("Product1", 1, 1);
    private Product product2 = new Product("Product2", 2, 2);

    @Before
    public void setUp() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("DELETE FROM Products");
    }

    @Test
    public void testSaveOrUpdateAndGet() throws Exception {
        productDao.save(product1);
        assertEquals(product1, productDao.getProducts().get(0));

        Product updatedProduct = productDao.getProducts().get(0);
        updatedProduct.setName("Updated product");
        productDao.update(updatedProduct);
        assertEquals(updatedProduct, productDao.getProduct(updatedProduct.getId()));
    }

    @Test
    public void testDelete() throws Exception {
        productDao.save(product2);
        for (Product product : productDao.getProducts()) {
            assertTrue(productDao.delete(product.getId()));
        }
        assertEquals(0, productDao.getProducts().size());
    }
}