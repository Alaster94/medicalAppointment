package dba;

import java.sql.*;

public class DBConnection {
    private static String con = "jdbc:mysql://localhost/medicalAppointment";
    private static String user = "root";
    private static String clave = "";
    private static Connection connection;
    public static Statement conn;
    private static ResultSet resultSet;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        connection = DriverManager.getConnection(con, user, clave);
        conn=connection.createStatement();

//Connection con = null;
//        Class.forName("com.mysql.jdbc.Driver");
//        con = DriverManager.getConnection("jdbc:mysql://localhost/medicalAppointment", "root", "");

        return connection;
    }

//    private Connection connection;
//    private String url = "jdbc:mysql://localhost/medicalAppointment";
//    private String usuario = "root";
//    private String contrasena = "";
//    private static Connection conexion;
//
//    public Connection getConnection() {
//        return connection;
//    }
//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void establecerConexion(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(url, usuario, contrasena);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void cerrarConexion(){
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
