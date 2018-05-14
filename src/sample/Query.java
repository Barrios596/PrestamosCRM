package sample;

import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BDConeccion;

import java.sql.*;
import java.util.ArrayList;

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

    public int[] grafica1Query(){
        int[] salida = new int[2];
        String SQL = "SELECT t1.cli_with, t2.cli_without\n" +
                "FROM (\tSELECT count(*) as cli_with \n" +
                "\tFROM cliente\n" +
                "\tWHERE usarname_twitter is not null) t1,\n" +
                "\t( \n" +
                "\tSELECT count(*) as cli_without \n" +
                "\tFROM cliente\n" +
                "\tWHERE usarname_twitter is null\n" +
                "\t) t2";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;
        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()) {
            System.out.println("llegó");
            System.out.println(SQL);
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    salida[0]=rs.getInt("cli_with");
                    salida[1]=rs.getInt("cli_without");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException ex) {
            System.out.print("ERROR: Hubo un error al conectar con la BD o ejecutar la query");
        }
        return salida;
    }

    public int[] grafica2Query(){
        int[] salida = new int[3];
        String SQL = "SELECT empl_nombre, count(*) as cant_cli\n" +
                "\tFROM (\n" +
                "\t\tSELECT *, concat(em.nombre,' ', em.apellido) as empl_nombre\n" +
                "\t\tFROM (cliente cl INNER JOIN empleado em ON (cl.id_empleado = em.id_empleado)\n" +
                "\t\t) INNER JOIN nacionalidad na ON (cl.id_nacionalidad = na.id_nacionalidad)\n" +
                "\n" +
                "\t\t) res \n" +
                "\tGROUP BY  empl_nombre";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;
        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()) {
            System.out.println(SQL);
            rs = stmt.executeQuery(SQL);
            try {
                for (int i = 0; i<3;i++){
                    rs.next();
                    salida[i]=rs.getInt("cant_cli");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException ex) {
            System.out.print("ERROR: Hubo un error al conectar con la BD o ejecutar la query");
        }
        return salida;
    }

    public int[] grafica3Query(){
        int[] salida = new int[4];
        String SQL = "SELECT  estado, count (*) as cant_usuarios\n" +
                "\tFROM (cliente cl INNER JOIN estadoc ec ON (cl.id_estadoc = ec.id_estadoc)) \n" +
                "\tGROUP BY estado";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;
        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()) {
            System.out.println(SQL);
            rs = stmt.executeQuery(SQL);
            try {
                for (int i = 0; i<4;i++){
                    rs.next();
                    salida[i]=rs.getInt("cant_usuarios");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException ex) {
            System.out.print("ERROR: Hubo un error al conectar con la BD o ejecutar la query");
        }
        return salida;
    }

    public int[] grafica4Query(){
        int[] salida = new int[22];
        String SQL = "SELECT  direccion, avg(monto_maximo) as promedio_monto_maximo\n" +
                "\tFROM cliente cl INNER JOIN nacionalidad n ON (cl.id_nacionalidad = n.id_nacionalidad)\n" +
                "\tWHERE nacionalidad = \'Guatemala\'\n" +
                "\tGROUP BY direccion";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;
        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()) {
            System.out.println(SQL);
            rs = stmt.executeQuery(SQL);
            try {
                for (int i = 0; i<22;i++){
                    rs.next();
                    salida[i]=rs.getInt("promedio_monto_maximo");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException ex) {
            System.out.print("ERROR: Hubo un error al conectar con la BD o ejecutar la query");
        }
        return salida;
    }

    public int[] grafica5Query(){
        int[] salida = new int[2];
        String SQL = "SELECT prestamos_arriba, prestamos_abajo\n" +
                "FROM (SELECT count(*) as prestamos_arriba\n" +
                "\tFROM cliente\n" +
                "\tWHERE monto_maximo >= 25000) t1,\n" +
                "\t(SELECT count(*) as prestamos_abajo\n" +
                "\tFROM cliente\n" +
                "\tWHERE monto_maximo < 25000) t2";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;
        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()) {
            System.out.println(SQL);
            rs = stmt.executeQuery(SQL);
            try {
                rs.next();
                salida[0]=rs.getInt("prestamos_arriba");
                salida[1]=rs.getInt("prestamos_abajo");

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException ex) {
            System.out.print("ERROR: Hubo un error al conectar con la BD o ejecutar la query");
        }
        return salida;
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

    public ArrayList<String> usuariosQuery () {

        ArrayList<String> lista = new ArrayList<>();
        String SQL = "SELECT usarname_twitter FROM cliente WHERE usarname_twitter is not null";

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
                            rs.getString("usarname_twitter")
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
                    "SET id_documento = ?, nombre = ?, apellido = ?, direccion = ?, id_nacionalidad = ?, id_estadoc = ?, id_empleado = ?" +
                    "WHERE documento = \'" + id_cliente+"\'";

            BDConeccion bd = new BDConeccion();

            try(Connection conn = bd.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)){

                pstmt.setInt(1,Integer.parseInt(valores[0]));
                pstmt.setString(2,valores[1]);
                pstmt.setString(3,valores[2]);
                pstmt.setString(4,valores[3]);
                pstmt.setInt(5,Integer.parseInt(valores[4]));
                pstmt.setInt(6,Integer.parseInt(valores[5]));
                pstmt.setString(7,valores[6]);

                pstmt.executeUpdate();

            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public void eliminarQuery(String id) {
        String SQL = "DELETE FROM cliente WHERE documento = ?";

        BDConeccion bd = new BDConeccion();

        try (Connection conn = bd.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, id);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public ObservableList<Detalle1> CallDetalle1 () {

        ObservableList<Detalle1> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM detalle1";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Detalle1(
                                    rs.getString("nacionalidad"),
                                    rs.getString("direccion"),
                                    rs.getFloat("promedio_monto_maximo")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }
    public ObservableList<Resumen1> CallResumen1 () {

        ObservableList<Resumen1> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM resumen1";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Resumen1(
                                    rs.getString("nacionalidad"),
                                    rs.getFloat("promedio_monto_maximo")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }

    public ObservableList<Detalle2> CallDetalle2 () {

        ObservableList<Detalle2> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM detalle2";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Detalle2(
                                    rs.getString("nacionalidad"),
                                    rs.getInt("edad"),
                                    rs.getInt("cant_cli_with")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }
    public ObservableList<Resumen2> CallResumen2 () {

        ObservableList<Resumen2> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM resumen2";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Resumen2(
                                    rs.getInt("edad"),
                                    rs.getInt("cant_cli_with")
                            )

                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }

    public ObservableList<Detalle3> CallDetalle3 () {

        ObservableList<Detalle3> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM detalle3";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Detalle3(
                                    rs.getString("nacionalidad"),
                                    rs.getString("estado"),
                                    rs.getInt("prestamos")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }

    public ObservableList<Resumen3> CallResumen3 () {

        ObservableList<Resumen3> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM resumen3";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Resumen3(
                                    rs.getInt("prestamos")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }

    public ObservableList<Detalle4> CallDetalle4 () {

        ObservableList<Detalle4> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM detalle4";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Detalle4(
                                    rs.getString("nacionalidad"),
                                    rs.getInt("mes"),
                                    rs.getInt("count"),
                                    rs.getString("direccion")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }

    public ObservableList<Resumen4> CallResumen4 () {

        ObservableList<Resumen4> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM resumen4";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Resumen4(
                                    rs.getInt("mes"),
                                    rs.getInt("count")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }
    public ObservableList<Detalle5> CallDetalle5 () {

        ObservableList<Detalle5> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM detalle5";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Detalle5(
                                    rs.getInt("cant_cli"),
                                    rs.getString("direccion"),
                                    rs.getString("empl_nombre")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }

    public ObservableList<Resumen5> CallResumen5 () {

        ObservableList<Resumen5> lista1 = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM resumen5";
        BDConeccion bd = new BDConeccion();
        ResultSet rs = null;

        try (Connection conn = bd.connect();
             Statement stmt = conn.createStatement()
        ) {
            rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {

                    lista1.add(
                            new Resumen5(

                                    rs.getString("nacionalidad"),
                                    rs.getInt("cant_cli")
                            )
                    );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lista1;
    }

}
