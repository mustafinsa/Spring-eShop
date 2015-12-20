package com.mustafinsa.spring.eshop.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductDaoSpringJdbcImpl implements ProductDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    public static final String SELECT_BY_ID_QUERY = "SELECT id, name, price, quantity FROM Products WHERE id = :id";
    public static final String SELECT_ALL_QUERY = "SELECT id, name, price, quantity FROM Products";

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Product getById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, params, new RowMapper<Product>(){
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                return product;
            }
        });
    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new RowMapper<Product>(){
            @Override
            public Product mapRow(ResultSet rs, int i) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                return product;
            }
        });
    }

    @Override
    public void save(Product product) {
        //TODO
    }

    @Override
    public void update(Product product) {
        //TODO
    }

    @Override
    public void deleteById(int id) {
        //TODO
    }
}
