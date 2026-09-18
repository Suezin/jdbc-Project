package controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class ControllerConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/GerenciadorAcademia";
    private static final String USER =  System.getenv().getOrDefault("DB_USER","matheus");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "0000");
    private static final HikariDataSource dataSource;

    static{
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(5000);
        dataSource = new HikariDataSource(config);
    }
        private ControllerConnection() {

        }

        public static Connection getConnection() throws SQLException {

        return dataSource.getConnection();
    }



        public void closeConnections(){
        dataSource.close();
        }
    }



