package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BDConeccion;

import java.sql.*;

public class Query {

    public void insertarCliente(Cliente cliente) {

        String SQL = "INSERT INTO public.cliente(" +
                "id_documento, nombre, apellido, telefono, correo, direccion, fecha_nacimiento, id_estadoc, " +
                "monto_maximo, usarname_twitter, id_empleado, id_nacionalidad, documento)" +
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
            stmt.setDouble(9,cliente.getMontoMaximo());
            stmt.setString(10, cliente.getUsernameTwitter());
            stmt.setString(11, cliente.getIdEmpleado());
            stmt.setInt(12,cliente.getIdNacionalidad());
            stmt.setString(13,cliente.getIdDocumento());

            stmt.executeUpdate();
            stmt.close();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public ObservableList<Fila1> generalQuery (String [] condiciones, String [] valores) {

        ObservableList<Fila1> lista = FXCollections.observableArrayList();
        String SQL = "SELECT d.documento as tipoDocumento, c.documento as numDocumento, c.nombre, c.apellido, c.direccion, n.nacionalidad, " +
                "ec.estado, (em.nombre || ' ' || em.apellido) as empleado\n" +
                "FROM cliente c JOIN documento d ON (c.id_documento = d.id_documento)\n" +
                "JOIN nacionalidad n  ON (c.id_nacionalidad = n.id_nacionalidad) \n" +
                "JOIN estadoc ec ON (c.id_estadoc = ec.id_estadoc)\n" +
                "JOIN empleado em ON (c.id_empleado = em.id_empleado)\n";

        if (valores.length > 0) {
            SQL = SQL + "WHERE c." + condiciones[0] + " LIKE \'%" + valores[0] + "%\'";
            if (valores.length > 1) {
                for (int x = 1; x < valores.length; x++) {
                    SQL = SQL + " AND c." + condiciones[x] + " LIKE \'%" + valores[x] + "%\'";
                }
            }
        }
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()) {
            System.out.println("llegó");
            System.out.println(SQL);
            rs = stmt.executeQuery(SQL);
            try {
            while (rs.next()) {

                lista.add(
                        new Fila1(
                                rs.getString("tipodocumento"),
                                rs.getString("numdocumento"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("direccion"),
                                rs.getString("nacionalidad"),
                                rs.getString("estado"),
                                rs.getString("empleado")
                        )
                );
            }
            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException ex) {
            System.out.print("ERROR: Hubo un error al conectar con la BD o ejecutar la query");
        }
        return lista;
    }
    
        public void updateQuery (String [] valores, String id_cliente) {

        if (valores.length > 0) {
            String SQL = "UPDATE cliente " +
                    "SET id_documento = ?, nombre = ?, apellido = ?, departamento = ?, id_nacionalidad = ?, id_estadoc = ?, id_empleado = ?" +
                    "WHERE documento = " + id_cliente;

            BDConeccion bd = new BDConeccion();

            try(Connection conn = bd.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)){

                pstmt.setInt(1,Integer.parseInt(valores[0]));
                pstmt.setInt(2,Integer.parseInt(valores[1]));
                pstmt.setInt(3,Integer.parseInt(valores[2]));
                pstmt.setInt(4,Integer.parseInt(valores[3]));
                pstmt.setInt(5,Integer.parseInt(valores[4]));
                pstmt.setInt(6,Integer.parseInt(valores[5]));
                pstmt.setInt(7,Integer.parseInt(valores[6]));

                pstmt.executeUpdate();

            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
        public void eliminarQuery(int id) {
        String SQL = "DELETE FROM cliente WHERE documento = ?";

        BDConeccion bd = new BDConeccion();

        try (Connection conn = bd.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
