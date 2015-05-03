package info.owczarek.spring.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductsRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getNumberOfProducts() {
        return jdbcTemplate.queryForObject("select count(*) from products", Integer.class);
    }

    public int getNumberOfProductsWithPriceGreaterThan(double priceLimit) {
        String sql = "select count(*) from products where buyPrice > ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, priceLimit);
    }

// won't work!
//    public int getNumberOfProductsWithLine(List<String> productLines) {
//        String sql = "select count(*) from products where productLine in (?)";
//        return jdbcTemplate.queryForObject(sql, Integer.class, productLines);
//    }
}
