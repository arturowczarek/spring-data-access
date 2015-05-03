package info.owczarek.spring.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductsRepository {
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public int getNumberOfProducts() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM products", Integer.class);
    }

    public int getNumberOfProductsWithPriceGreaterThan(double priceLimit) {
        String sql = "SELECT count(*) FROM products WHERE buyPrice > ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, priceLimit);
    }

// won't work!
//    public int getNumberOfProductsWithLine(List<String> productLines) {
//        String sql = "select count(*) from products where productLine in (?)";
//        return jdbcTemplate.queryForObject(sql, Integer.class, productLines);
//    }

    public Map<String, Object> getProductByProductCode(String productCode) {
        String sql = "SELECT productCode, productName, buyPrice " +
                "FROM products WHERE productCode = ?";
        return jdbcTemplate.queryForMap(sql, productCode);
    }

    public List<Map<String, Object>> getProductsWithPriceGreaterThan(double priceLimit) {
        String sql = "SELECT * from products WHERE buyPrice > ?";
        return jdbcTemplate.queryForList(sql, priceLimit);
    }

    public List<Double> getProductPrices() {
        String sql = "SELECT buyPrice FROM products";
        return jdbcTemplate.queryForList(sql, Double.class);
    }

    public List<Map<String, Object>> getProductsWithPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT * FROM products WHERE buyPrice >= :minPrice AND buyPrice <= :maxPrice";

        // Passing simple map
        Map<String, Object> params = new HashMap<>();
        params.put("minPrice", minPrice);
        params.put("maxPrice", maxPrice);

        Map<String, Double> minPrice1 = Collections.singletonMap("minPrice", minPrice);
        //return namedParameterJdbcTemplate.queryForList(sql, params);

        // Using SqlParameterSource
        SqlParameterSource parameters = new MapSqlParameterSource("minPrice", minPrice).addValue("maxPrice", maxPrice);
//        SqlParameterSource parameters = new MapSqlParameterSource(params);
        return namedParameterJdbcTemplate.queryForList(sql, parameters);
    }
}
