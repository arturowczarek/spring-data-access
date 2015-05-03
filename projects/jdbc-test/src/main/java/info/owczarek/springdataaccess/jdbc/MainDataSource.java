package info.owczarek.springdataaccess.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDataSource {

    public static final String DATABASE_NAME = "classicmodels";
    public static final String SERVER_NAME = "192.168.8.116";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setServerName(SERVER_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                String productName = resultSet.getString("productName");
                double buyPrice = resultSet.getDouble("buyPrice");

                System.out.println(productName + ": " + buyPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
