package dba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/medicalAppointment", "root", "");

        return con;
    }

}
