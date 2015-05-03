package info.owczarek.spring.dataaccess;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "info.owczarek.spring.dataaccess")
public class AppConfiguration {

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("classicmodels");
        dataSource.setServerName("192.168.8.116");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
