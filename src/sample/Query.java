import java.sql.*;

public class Query {

    public void insertarCliente(Cliente cliente) {

        String SQL = "INSERT INTO public.\"Cliente\"(" +
                "documento, nombre, apellido, telefono, correo, direccion, fecha_nacimiento, id_estadoc, " +
                "monto_maximo, usarname_twitter, id_empleado, id_nacionalidad, id_documento)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        BDConeccion bd = new BDConeccion();

        try(Connection conn = bd.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setInt(1,cliente.getDocumento());
            stmt.setString(2,cliente.getNombre());
            stmt.setString(3,cliente.getApellido());
            stmt.setString(4,cliente.getTelefono());
            stmt.setString(5,cliente.getCorreo());
            stmt.setString(6,cliente.getDireccion());
            stmt.setDate(7, cliente.getFechaNacimiento());
            stmt.setInt(8,cliente.getIdEstadoC());
            stmt.setFloat(9,cliente.getMontoMaximo());
            stmt.setString(10, cliente.getUsernameTwitter());
            stmt.setInt(11, cliente.getIdEmpleado());
            stmt.setInt(12,cliente.getIdNacionalidad());
            stmt.setInt(13,cliente.getIdDocumento());

            stmt.executeUpdate();
            stmt.close();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public ResultSet generalQuery (String [] condiciones, String [] valores) {

        String SQL = "SELECT (d.documento || ' ' || c.documento) as doc_tipo, c.nombre, c.apellido, c.direccion, n.nacionalidad, " +
                "ec.estado, (em.nombre || ' ' || em.apellido) as empleado\n" +
                "FROM cliente c JOIN documento d ON (c.id_documento = d.id_documento)\n" +
                "JOIN nacionalidad n  ON (c.id_nacionalidad = n.id_nacionalidad) \n" +
                "JOIN estadoc ec ON (c.id_estadoc = ec.id_estadoc)\n" +
                "JOIN empleado em ON (c.id_empleado = em.documento);";

        if (valores.length > 0) {
            SQL = SQL + " WHERE " + condiciones[0] + "LIKE %" + valores[0] + "%";
            if (valores.length > 1) {
                for (int x = 1; x < valores.length; x++) {
                    SQL = SQL + " AND " + condiciones[x] + "LIKE %" + valores[x] + "%";
                }
            }
        }
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.print("ERROR: Hubo un error al conectar con la BD o ejecutar la query");
        }
        return rs;
    }

    public Integer contadorRegistros (ResultSet rs) throws SQLException {
        int count = 0;

        while (rs.next()) {
            ++count;
        }

        if (count == 0) {
            System.out.println("No existen registros en la tabla");
        }
        return count;
    }
}
