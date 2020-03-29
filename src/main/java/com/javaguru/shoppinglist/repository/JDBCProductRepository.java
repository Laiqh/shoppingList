package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Profile("local")
public class JDBCProductRepository implements Repository<Product> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(Product product) {
        String query = "insert into products (name, category, price, discount, description) values (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"PRODUCT_ID"});
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setBigDecimal(3, product.getPrice());
            ps.setBigDecimal(4, product.getDiscount());
            ps.setString(5, product.getDescription());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Product get(Long id) throws NoSuchElementException {
        String query = "select * from products where product_id = ?";

        try {
            Product product = jdbcTemplate.queryForObject(query, new Object[]{id}, new ProductRowMapper());
            return product;
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("No item found for ID " + id);
        }
    }

    public void remove(Long id) {
        String query = "delete from products where product_id = ?";

        jdbcTemplate.update(query, new Object[]{id});
    }

    public List<Product> getAll() {
        String query = "select * from products";

        List<Product> products = jdbcTemplate.query(query, new ProductRowMapper());

        return products;
    }

    private class ProductRowMapper implements RowMapper<Product> {

        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("PRODUCT_ID"));
            product.setName(rs.getString("NAME"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setPrice(rs.getBigDecimal("PRICE"));
            product.setDiscount(rs.getBigDecimal("DISCOUNT"));
            product.setDescription(rs.getString("DESCRIPTION"));

            return product;
        }
    }
}
