package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://mysql-guardian:3306/guardian";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";

public static Connection conectar() throws SQLException {

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("DRIVER MYSQL CARREGADO!");

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    System.out.println("URL: " + URL);

    return DriverManager.getConnection(URL, USUARIO, SENHA);
}

}