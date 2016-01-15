package com.mustafinsa.spring.test;

import com.mustafinsa.spring.eshop.model.User;
import com.mustafinsa.spring.eshop.model.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:spring-core-config.xml",
        "classpath:spring-database-config.xml",
        "classpath:spring-datasource-config.xml",
        "classpath:spring-security-config.xml",})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private DataSource dataSource;

    private User user1 = new User("Salavat1", "admin1@mail.ru", "12345678", true, "ROLE_USER", "user");
    private User user2 = new User("Salavat2", "admin2@mail.ru", "12345678", true, "ROLE_USER", "user");
    private User user3 = new User("Salavat3", "admin3@mail.ru", "123 78", true, "ROLE_USER", "user");

    @Before
    public void init() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("DELETE FROM Users");
        jdbcTemplate.execute("DELETE FROM Authorities");
    }

    @Test
    public void testCreateRetrive() {
        usersDao.create(user1);

        List<User> userList1 = usersDao.getAllUsers();
        assertEquals("One user should have been created and retrieved", 1, userList1.size());

        assertEquals("Inserted user should match retrieved", user1, userList1.get(0));

        usersDao.create(user2);
        usersDao.create(user3);
        List<User> userList = usersDao.getAllUsers();

        assertEquals("One user should be four retrieved users", 3, userList.size());

        assertEquals("Inserted user should match retrieved", user3, userList.get(2));

    }

    @Test
    public void testCreateUser() {
        User user = new User("Salavat1", "admin@mail.ru", "12345678", true, "ROLE_USER", "user");
        usersDao.create(user);

        List<User> userList = usersDao.getAllUsers();

        assertEquals("Number of users should be 1", 1, userList.size());

        assertTrue("User should exist", usersDao.exists(user.getUsername()));

        assertEquals("Created user should be identical to retrieved user", user, userList.get(0));
    }
}
