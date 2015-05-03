package info.owczarek.spring.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ProductsRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
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
}
