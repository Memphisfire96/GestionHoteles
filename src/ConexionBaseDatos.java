import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private String url = "jdbc:mysql://localhost:3306/hoteles";
    private String usuario = "root";
    private String contraseña = "123456789";

    public  Connection obtenerConexion() throws SQLException{
        return DriverManager.getConnection(url,usuario,contraseña);

    }
}
