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

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:spring-core-config.xml",
        "classpath:spring-database-config.xml",
        "classpath:spring-datasource-config.xml",
        "classpath:spring-security-config.xml",})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartDaoTest {

    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private DataSource dataSource;

    private User user1 = new User("Salavat2", "admin1@mail.ru", "12345678", true, "ROLE_USER", "user");
    private Product product1 = new Product("Product1", 1, 1);
    private Product product2 = new Product("Product2", 2, 2);

    @Before
    public void setUp() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("DELETE FROM Authorities");
        jdbcTemplate.execute("DELETE FROM Products");
        jdbcTemplate.execute("DELETE FROM ShoppingCarts");
        jdbcTemplate.execute("DELETE FROM Users");
        usersDao.create(user1);
        productDao.saveOrUpdate(product1);
        product1 = productDao.getProducts().get(0);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        productDao.saveOrUpdate(product2);
        product2 = productDao.getProducts().get(0);
        ShoppingCart shoppingCart1 = new ShoppingCart(product2.getId(), user1, 1, false);
        shoppingCartDao.saveOrUpdate(shoppingCart1);
        assertEquals(shoppingCart1, shoppingCartDao.getCart(user1.getUsername(), product2.getId()));
    }

    @Test
    public void testCurrentCartExist() throws Exception {
        shoppingCartDao.saveOrUpdate(new ShoppingCart(product1.getId(), user1, 1, false));
        assertFalse(shoppingCartDao.currentCartExist("fakeUser", 1));
        assertFalse(shoppingCartDao.currentCartExist(user1.getUsername(), -1));
        assertTrue(shoppingCartDao.currentCartExist(user1.getUsername(), product1.getId()));
    }

    @Test
    public void testGetCarts() throws Exception {
        assertEquals(0, shoppingCartDao.getCarts().size());

    }
}