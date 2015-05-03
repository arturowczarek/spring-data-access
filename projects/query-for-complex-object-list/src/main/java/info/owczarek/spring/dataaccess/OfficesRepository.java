package info.owczarek.spring.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OfficesRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Office getOfficeByOfficeCode(String officeCode) {
        String sql = "SELECT * FROM offices WHERE officeCode = ?";
        return jdbcTemplate.queryForObject(sql, new OfficeRowMapper(), officeCode);
    }

    public List<Office> getAllOffices() {
        String sql = "SELECT * FROM offices";
        return jdbcTemplate.query(sql, new OfficeRowMapper());
    }

    private static class OfficeRowMapper implements RowMapper<Office> {
        public Office mapRow(ResultSet rs, int rowNum) throws SQLException {
            Office office = new Office();
            office.setOfficeCode(rs.getString("officeCode"));
            office.setAddressLine1(rs.getString("addressLine1"));
            office.setAddressLine2(rs.getString("addressLine2"));
            office.setCity(rs.getString("city"));
            office.setCountry(rs.getString("country"));
            office.setPhone(rs.getString("phone"));
            office.setState(rs.getString("state"));
            office.setPostalCode(rs.getString("postalCode"));
            office.setTerritory(rs.getString("territory"));
            return office;
        }
    }
}
