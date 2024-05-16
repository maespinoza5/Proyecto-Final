
    package conexion.bd;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.swing.JOptionPane;
/**
 *
 * @author ferna
 */
    public class conexionbd {
        private static conexionbd instancia;
        private Connection conexion;
    
    private conexionbd() {
        String usuario = "avnadmin";
        String contra = "AVNS_YmwLFO3Wb9B1o4Qqdbc"; // Cambia la contraseña.
        String bd = "defaultdb";      // Cambia el nombre de la base de datos.
        String ip = "mysql-programacionweb-tes-pweb.d.aivencloud.com";
        String puerto = "23988";
        String url = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, contra);
            //JOptionPane.showMessageDialog(null, "¡Conexión a la base de datos establecida con éxito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos ERROR: " + e.toString());
        }
    }

    public static conexionbd getInstancia() {
        if (instancia == null) {
            instancia = new conexionbd();
        }
        return instancia;
    }

    public Connection obtenerConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                //JOptionPane.showMessageDialog(null, "¡Conexión a la base de datos cerrada!");
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }

    public java.sql.Statement createStatement() throws SQLException{
        return this.conexion.createStatement();
    }
}
        
        