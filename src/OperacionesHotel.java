import java.math.BigDecimal;
import java.sql.*;

public class OperacionesHotel {
    ConexionBaseDatos conexion = new ConexionBaseDatos();

    // Insertar
    public void insertarHotel(int id, String nombre, String ubicacion,
                              int habitaciones, BigDecimal tarifa, String telefono) throws SQLException {

        String consulta = "INSERT INTO Hoteles (hotel_id, nombre, ubicacion, numero_de_habitaciones, tarifa, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conectar = conexion.obtenerConexion();
             PreparedStatement sentencia = conectar.prepareStatement(consulta)) {
            sentencia.setInt(1, id);
            sentencia.setString(2, nombre);
            sentencia.setString(3, ubicacion);
            sentencia.setInt(4, habitaciones);
            sentencia.setBigDecimal(5, tarifa);
            sentencia.setString(6, telefono);
            sentencia.executeUpdate();
        }
    }

    // Actualizar
    public void actualizarHotel(int id, String nombre, String ubicacion,
                                int habitaciones, BigDecimal tarifa, String telefono) throws SQLException {

        String consulta = "UPDATE Hoteles SET nombre = ?, ubicacion = ?, numero_de_habitaciones = ?, tarifa = ?, telefono = ? " +
                "WHERE hotel_id = ?";

        try (Connection conectar = conexion.obtenerConexion();
             PreparedStatement sentencia = conectar.prepareStatement(consulta)) {
            sentencia.setString(1, nombre);
            sentencia.setString(2, ubicacion);
            sentencia.setInt(3, habitaciones);
            sentencia.setBigDecimal(4, tarifa);
            sentencia.setString(5, telefono);
            sentencia.setInt(6, id);
            sentencia.executeUpdate();
        }
    }

    // Eliminar
    public void borrarHotel(int id) throws SQLException {
        String consulta = "DELETE FROM Hoteles WHERE hotel_id = ?";

        try (Connection conectar = conexion.obtenerConexion();
             PreparedStatement sentencia = conectar.prepareStatement(consulta)) {
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        }
    }

    // Consultar
    public String consultarHoteles() throws SQLException {
        StringBuilder resultado = new StringBuilder();
        String consulta = "SELECT * FROM Hoteles";

        try (Connection conectar = conexion.obtenerConexion();
             Statement sentencia = conectar.createStatement();
             ResultSet rs = sentencia.executeQuery(consulta)) {

            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("hotel_id"))
                        .append(", Nombre: ").append(rs.getString("nombre"))
                        .append(", Ubicación: ").append(rs.getString("ubicacion"))
                        .append(", Habitaciones: ").append(rs.getInt("numero_de_habitaciones"))
                        .append(", Tarifa: ").append(rs.getBigDecimal("tarifa"))
                        .append(", Teléfono: ").append(rs.getString("telefono"))
                        .append("\n");
            }
        }
        return resultado.toString();
    }
}
