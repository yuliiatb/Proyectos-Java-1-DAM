/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog11.bbdd;

/**
 *Clase para realizar las operaciones, como insertar, eliminar un propietario, 
 * o listar sus vehículos. Para hacerlo, se realizan las consultas SQL.
 * Cada método realiza la llamada a la BD y recibe los parametros necesarios para realizar la operación.
 * 
 * @author Yuliia Teterko Bobrytska
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.prog11.princ.Prog11_Principal;

/**
 * Clase contiene los métodos necesarios para realizar operaciones contra la tabla propietarios.
 * La conexión creada en la clase principal, se pasará a los métodos que la necesiten como parámetro.
 * @author Yuliia T
 */
public class PropietariosDAO {
    
    /**
     * Clase para insertar un nuevo propietario. 
     * @param conectar para conectarse a la BD
     * Paramentor del propietario:
     * @param id_prop su identificador en el concesionario
     * @param nombre_prop el nombre del nuevo propietario
     * @param dni_prop el dni del nuevo propietario
     * @return devuelve 0 si la operación se realizó con éxito o -1 en caso contrario 
     */
    public static int insertarPropietario(ConnectionDB conectar, int id_prop, String nombre_prop, String dni_prop) {
            
        try {
            conectar.openConnection();//se abre la conexión con la BD
            //mediante INSERT INTO se insertan los valores necesarios
            PreparedStatement ps = conectar.getConnection().prepareStatement("INSERT INTO PROPIETARIOS (id_prop, nombre_prop, dni_prop) "
            + "VALUES (?,?,?)");
                //establece los valores de parámetros de consulta
                ps.setInt(1, id_prop); 
                ps.setString(2, nombre_prop);
                ps.setString(3, dni_prop);
            
            ps.executeUpdate(); //ejecutar la consulta y actualizar la tabla con nuevos valores
            
            //se cierran los PrepareStatement y la conexión
            ps.close();
            conectar.closeConnection();
            
            return 0;//devuelve 0 si la operación se realizó con éxito
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;//devuelve -1 si la operación falló
    }//insertarPropietario
    
    /**
     * Muestra los vehículos de un propietario en particular
     * @param conectar para conectarse a la BD
     * @param dni_prop el dni del propietario
     * @return 
     */
    public static ArrayList<String> recuperarVehiculosDeProp(ConnectionDB conectar, String dni_prop) {
        try {
            ArrayList<String> vehic = new ArrayList<>();
            conectar.openConnection();
            //con SELECT seleccionamos los datos necesarios de las dos tablas, de las columnas, donde los IDs coinciden
            PreparedStatement ps = conectar.getConnection().prepareStatement("SELECT v.mat_veh, v.marca_veh, v.kms_veh, v.precio_veh "
            + "FROM VEHICULOS v INNER JOIN PROPIETARIOS p ON v.id_prop = p.id_prop WHERE p.dni_prop = ? ");
            
            ps.setString(1, dni_prop); //establece valor del primer parámetro de consulta
            ResultSet rs = ps.executeQuery(); //devuelve el resultado de consulta
            
            while (rs.next()) { //next() hace que dicho puntero avance al siguiente registro
                vehic.add("Matrícula: " + rs.getString("mat_veh") + "\nMarca: " + rs.getString("marca_veh") 
                + "\nKilometros: " + rs.getInt("kms_veh") + "\nPrecio: " + rs.getInt("precio_veh")); 
            }
            conectar.closeConnection();
            return vehic; //devolver la lista con los datos mencionados
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();//si hubo problemas, se mostrará la lista vacía
    }//recuperarVP
    
    /**
     * Método para eliminar un propietario. Si el propietario tiene vehículos, primero se eliminarán ellos, y luego - el propietario
     * @param conectar para conectarse a la BD
     * @param dni_prop el dni del propietario
     * @return si el propietario se ha borrado correctamente
     */
    public static int eliminarPropietario(ConnectionDB conectar, String dni_prop) {
        try {
            conectar.openConnection();
            //primero, eliminar los vehículos del dicho propietario
            PreparedStatement ps1 = conectar.getConnection().prepareStatement("DELETE FROM VEHICULOS WHERE id_prop IN (SELECT id_prop "
            + "FROM PROPIETARIOS WHERE dni_prop = ?)");
            ps1.setString(1, dni_prop);
            
            ps1.executeUpdate();//ejecuta la primera consulta si el propietario tiene vehículos
            ps1.close();

            //sentencia SQL DELETE FROM especificando el dni para borrar el propietario (después de borrar sus vehículos, si tiene)
            PreparedStatement ps2 = conectar.getConnection().prepareStatement("DELETE FROM PROPIETARIOS WHERE dni_prop = ?");
            ps2.setString(1, dni_prop);
            
            int propElim = ps2.executeUpdate(); //número de filas que se van a borrar al ejecutar esta consulta
            ps2.close();
            conectar.closeConnection();
            return propElim;
        } 
        catch (SQLException e) {
            System.out.println("Error al eliminar el propietario" + e.getMessage());
        }
        return 0; //si no se ha eliminado ningún propietario
    }
         
}//propdao
