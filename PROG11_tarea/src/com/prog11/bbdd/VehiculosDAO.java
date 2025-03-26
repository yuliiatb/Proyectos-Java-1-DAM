/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog11.bbdd;

/**
 * Clase para trabajar con los vehículos, en particular, insertar uno nuevo, actualizar su propietario, eliminar un vehículo,
 * mostrar todos los vehículos en la lista o mostrar vehículos según su marca.
 * 
 * @author Yuliia Teterko Bobrytska
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculosDAO {
        
    /**
     * Recibe por parámetro los datos del vehículo a insertar, trata de insertarlo en la BBDD.
     * La conexión creada en la clase principal, se pasará a los métodos que la necesiten como parámetro.
     * @param conectar para conectarse a la BD
     * @param mat_veh matricula del vehículo
     * @param marca_veh marca del vehículo
     * @param kms_veh kilometros que tiene el vehiculo
     * @param precio_veh precio del vehículo
     * @param desc_veh descripción del vehículo
     * @param id_prop el identificador del propietario
     * @return devuelve 0 si la operación se realizó con éxito o -1 en caso contrario
     */
    public static int insertarNuevoVehiculo(ConnectionDB conectar, String mat_veh, String marca_veh, int kms_veh, int precio_veh, 
            String desc_veh, int id_prop) {

        try {
            conectar.openConnection();
            //se prepara la sentencia para insertar los siguientes datos en la tabla vehiculos
            PreparedStatement ps = conectar.getConnection().prepareStatement("INSERT INTO VEHICULOS VALUES (?,?,?,?,?,?)");
            ps.setString(1, mat_veh);//se asignan los valores 
            ps.setString(2, marca_veh);
            ps.setInt(3, kms_veh);
            ps.setInt(4, precio_veh);
            ps.setString(5, desc_veh);
            ps.setInt(6, id_prop);

            ps.executeUpdate();// se ejecuta la consulta
            ps.close();
            conectar.closeConnection();
            return 0; //si la operación se realizó con éxito
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;//si la operación no se realizó con éxito
    }//insertar
    
    /**
     * Recibe por parámetro la matrícula de un vehículo junto al identificador de un propietario y trata de actualizar el vehículo en la BBDD.  
     * @param conectar para conectarse a la BD
     * @param mat_veh matricula del vehículo
     * @param id_prop el identificador del propietario
     * @return devuelve 0 si la operación se realizó con éxito o -1 en caso contrario
     */
    public static int actualizarPropVehic(ConnectionDB conectar, String mat_veh, int id_prop) {
        try {
            conectar.openConnection();
            //UPDATE para actualizar los datos
            PreparedStatement ps = conectar.getConnection().prepareStatement("UPDATE VEHICULOS SET id_prop = ? WHERE mat_veh = ?");
            ps.setInt(1, id_prop);
            ps.setString(2, mat_veh);

            int propAct = ps.executeUpdate(); //número de propietarios actualizadas - filas modificadas

            if (propAct == 0){
                return -1;
            }
            else {
                ps.close();
                conectar.closeConnection();
            return 0;
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    /**
     * Recibe por parámetro la matrícula de un vehículo y trata de eliminarlo de la BBDD. 
     * @param conectar para conectarse a la BD
     * @param mat_veh
     * @return Devuelve 0 si la operación se realizó con éxito o -1 si el vehículo no existe.
     */
    public static int eliminarVehiculo(ConnectionDB conectar, String mat_veh) {
        try {
            conectar.openConnection();

            PreparedStatement ps = conectar.getConnection().prepareStatement("DELETE FROM VEHICULOS WHERE mat_veh = ?");
            ps.setString(1, mat_veh);

            int vehicEliminado = ps.executeUpdate(); //número de vehículos eliminados
            if(vehicEliminado == 0){//si después eliminar el vehículo, no hay otro vehículo con los mismos credenciales
                return -1;
            }
            else {
                ps.close();
                conectar.closeConnection();
            return 0;
            } 
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    /**
     * El método devuelve una lista con todos los vehículos del concesionario. 
     * Para cada vehículo, la lista contendrá una cadena de caracteres con los datos del mismo, incluido el nombre del propietario.
     * @param conectar para conectarse a la BD
     * @return devuelve una lista con todos los vehículos del concesionario
     */
    public static ArrayList<String> recuperarTodosVehic(ConnectionDB conectar) {
        try {
            ArrayList<String> vehic = new ArrayList<>();
            conectar.openConnection();
            //INNER JOIN ... ON para juntar las tablas utilizando el campo en común 
            PreparedStatement ps = conectar.getConnection().prepareStatement("SELECT v.mat_veh, v.marca_veh, v.kms_veh, v.precio_veh, "
            + "v.desc_veh, p.id_prop, p.nombre_prop, "
            + "p.dni_prop FROM VEHICULOS v INNER JOIN PROPIETARIOS p ON v.id_prop = p.id_prop");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                vehic.add("Matricula: " + rs.getString("mat_veh") + "\nMarca: " + rs.getString("marca_veh") + "\nKilometros: " 
                + rs.getInt("kms_veh") + "\nPrecio: " + rs.getInt("precio_veh") + "\nDescripcion: " + rs.getString("desc_veh")
                + "\nID del propietario: " + rs.getInt("id_prop") + "\nNombre completo del propietario: " + rs.getString("nombre_prop"));
            }
            conectar.closeConnection();
            return vehic;
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>(); //para que muestre la lista vacía si no hay vehículos
    }//recuperarV
    
    /**
     * Recibe una marca por parámetro y devuelve una lista con el listado de vehículos de la citada marca.
     * @param conectar para conectarse a la BD
     * @param marca_veh
     * @return devuelve la lista con una cadena de caracteres con los datos del vehiculo, incluido el nombre del propietario. Si no existen vehículos, devuelve el ArrayList vacío.
     */
    public static ArrayList<String> recuperarVehicMarca(ConnectionDB conectar, String marca_veh) {
        try {
            ArrayList<String> vehic = new ArrayList<>();
            conectar.openConnection();
            
            PreparedStatement ps = conectar.getConnection().prepareStatement("SELECT v.mat_veh, v.marca_veh, v.kms_veh, v.precio_veh, "
            + "v.desc_veh, p.id_prop, p.nombre_prop "
            + "FROM VEHICULOS v INNER JOIN PROPIETARIOS p ON v.id_prop = p.id_prop WHERE v.marca_veh = ?");
            
            ps.setString(1, marca_veh);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                vehic.add("Matrícula: " + rs.getString("mat_veh") + "\nMarca: " + rs.getString("marca_veh") 
                + "\nKilometros: " + rs.getInt("kms_veh") + "\nPrecio: " + rs.getInt("precio_veh") + "\nDescripcion: " 
                + rs.getString("desc_veh") + "\nID del propietario: " + rs.getInt("id_prop") + 
                "\nNombre completo del propietario: " + rs.getString("nombre_prop"));
            }
            
            conectar.closeConnection();
            return vehic;
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }//recuperarmarca
    
    /**
     * Método retorna una lista con la matrícula, marca, kilómetros y precio de cada vehículo
     * @param conectar para conectarse a la BD
     * @return una lista con la información sobre los vehiculos
     */
    public static ArrayList<String> recuperarVehiculos(ConnectionDB conectar) {
        try {
            ArrayList<String> vehic = new ArrayList<>();
            conectar.openConnection();
            PreparedStatement ps = conectar.getConnection().prepareStatement("SELECT v.mat_veh, v.marca_veh, v.kms_veh, "
            + "v.precio_veh, v.desc_veh, v.id_prop FROM VEHICULOS v");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                vehic.add("Matrícula: " + rs.getString("mat_veh") + "\nMarca: " + rs.getString("marca_veh") 
                + "\nKilometros: " + rs.getInt("kms_veh") + "\nPrecio: " + rs.getInt("precio_veh"));
            }
            
            conectar.closeConnection();
            return vehic;
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
    
}//vehiculosdao
