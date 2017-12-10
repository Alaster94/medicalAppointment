package model;

import dba.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.*;

public class Usuarios {
    private StringProperty idUsuarios;
    private StringProperty nombres;
    private StringProperty apellidos;
    private Date birthDate;
    private StringProperty telefonoo;
    private StringProperty direccionn;
    private StringProperty emaill;
    private StringProperty usuarioo;
    private StringProperty password;
    private StringProperty estado;
    private TipoUsuario tipoUsuario;

    public Usuarios(String idUsuarios, String nombres, String apellidos, Date birthDate, String telefonoo, String direccionn, String email, String usuario, String password, String estado, TipoUsuario tipoUsuario) {
        this.idUsuarios = new SimpleStringProperty(idUsuarios);
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.birthDate = birthDate;
        this.telefonoo = new SimpleStringProperty(telefonoo);
        this.direccionn = new SimpleStringProperty(direccionn);
        this.emaill = new SimpleStringProperty(email);
        this.usuarioo = new SimpleStringProperty(usuario);
        this.password = new SimpleStringProperty(password);
        this.estado = new SimpleStringProperty(estado);
        this.tipoUsuario = tipoUsuario;
    }

    public String getIdUsuarios() {
        return idUsuarios.get();
    }

    public StringProperty idUsuariosProperty() {
        return idUsuarios;
    }

    public void setIdUsuarios(String idUsuarios) {
        this.idUsuarios.set(idUsuarios);
    }

    public String getNombres() {
        return nombres.get();
    }

    public StringProperty nombresProperty() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getTelefonoo() {
        return telefonoo.get();
    }

    public StringProperty telefonooProperty() {
        return telefonoo;
    }

    public void setTelefonoo(String telefonoo) {
        this.telefonoo.set(telefonoo);
    }

    public String getDireccionn() {
        return direccionn.get();
    }

    public StringProperty direccionnProperty() {
        return direccionn;
    }

    public void setDireccionn(String direccionn) {
        this.direccionn.set(direccionn);
    }

    public String getEmaill() {
        return emaill.get();
    }

    public StringProperty emaillProperty() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill.set(emaill);
    }

    public String getUsuarioo() {
        return usuarioo.get();
    }

    public StringProperty usuariooProperty() {
        return usuarioo;
    }

    public void setUsuarioo(String usuarioo) {
        this.usuarioo.set(usuarioo);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


    public static void llenarInformacionUsuarios(Connection connection,
                                                ObservableList<Usuarios> lista){
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT U.idUsuarios, U.nombres, U.apellidos, U.birthDate, U.telefono, U.direccion, U.email, U.usuario, U.password, U.estado, T.id, T.nombre FROM usuarios U INNER JOIN tipousuario T ON (U.tipoUsuario_id = T.id)"
            );
            while(resultado.next()){
                lista.add(
                        new Usuarios(
                                resultado.getString("idUsuarios"),
                                resultado.getString("nombres"),
                                resultado.getString("apellidos"),
                                resultado.getDate("birthDate"),
                                resultado.getString("telefono"),
                                resultado.getString("direccion"),
                                resultado.getString("email"),
                                resultado.getString("usuario"),
                                resultado.getString("password"),
                                resultado.getString("estado"),
                                new TipoUsuario(resultado.getInt("id"),
                                        resultado.getString("nombre")
                                )
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int guardarUsuario(Connection connection){
        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO usuarios (idUsuarios, nombres, apellidos, birthDate, telefono, direccion, email, usuario, password, estado, tipoUsuario_id) "+
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, idUsuarios.get());
            statement.setString(2, nombres.get());
            statement.setString(3, apellidos.get());
            statement.setDate(4, birthDate);
            statement.setString(5, telefonoo.get());
            statement.setString(6, direccionn.get());
            statement.setString(7, emaill.get());
            statement.setString(8, usuarioo.get());
            statement.setString(9, password.get());
            statement.setString(10, estado.get());
            statement.setInt(11, tipoUsuario.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int actualizarRegistro(Connection connection){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE usuarios SET nombres = ?, apellidos = ?, birthDate = ?, telefono = ?, direccion = ?, email = ?, usuario = ?, password = ?, estado = ?, tipoUsuario_id = ? WHERE idUsuarios = ?"
                    );
            preparedStatement.setString(1, nombres.get());
            preparedStatement.setString(2, apellidos.get());
            preparedStatement.setDate(3, birthDate);
            preparedStatement.setString(4, telefonoo.get());
            preparedStatement.setString(5, direccionn.get());
            preparedStatement.setString(6, emaill.get());
            preparedStatement.setString(7, usuarioo.get());
            preparedStatement.setString(8, password.get());
            preparedStatement.setString(9, estado.get());
            preparedStatement.setInt(10, tipoUsuario.getId());
            preparedStatement.setString(11, idUsuarios.get());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void eliminarRegistro(String idUsuario) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "DELETE FROM usuarios WHERE idUsuarios = ?";
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, idUsuario);
            prepareStatement.execute();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException(e);
        }

    }
}
