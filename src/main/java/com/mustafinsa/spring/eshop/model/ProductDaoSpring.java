package com.mustafinsa.spring.eshop.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
@Transactional
public class ProductDaoSpring implements ProductDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String INSERT_PRODUCT = "INSERT INTO Products (name, price, quantity) VALUE (:name, :price, :quantity)";
    private static final String UPDATE_PRODUCT = "UPDATE Products SET name = :name, price = :price, quantity = :quantity WHERE id = :id";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, name, price, quantity FROM Products WHERE id = :id";
    private static final String DELETE_PRODUCT = "DELETE FROM Products WHERE id = :id";
    private static final String SELECT_ALL_QUERY = "SELECT id, name, price, quantity FROM Products";

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Product getProduct(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, params, this::mapProductRow);
    }

    @Override
    public List<Product> getProducts() {

        return jdbcTemplate.query(SELECT_ALL_QUERY, this::mapProductRow);
    }

    @Override
    public void save(Product product) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(product);
        jdbcTemplate.update(INSERT_PRODUCT, params);
    }

    @Override
    public void update(Product product) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(product);
        jdbcTemplate.update(UPDATE_PRODUCT, params);
    }

    @Override
    public boolean delete(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(DELETE_PRODUCT, params) == 1;
    }

    private Product mapProductRow(ResultSet rs, int rowNum) throws SQLException {

        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getInt("price"));
        product.setQuantity(rs.getInt("quantity"));
        return product;
    }
}
