package com.mustafinsa.spring.eshop.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class UsersDaoHibernate implements UsersDao {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session().save(user);
    }

    @Override
    public User getUser(String username) {
        Criteria crit = session().createCriteria(User.class);
        crit.add(Restrictions.idEq(username));
        return (User) crit.uniqueResult();
    }

    public List<User> getAllUsers() {
        return session().createQuery("FROM User").list();
    }

    public boolean exists(String username) {
        return getUser(username) != null;
    }
}
