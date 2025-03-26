/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog11.bbdd;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *La clase para establecer la conexión con la base de datos.
 * @author Yuliia Teterko Bobrytska
 */
public class ConnectionDB {
    //objeto de la clase ConnectionDB
    private Connection conectar;
       
    //abrir la conexion
    public void openConnection() throws SQLException{
                    
            System.setProperty("java.library.path", "C:\\Program Files (x86)\\Common Files\\MariaDBShared\\HeidiSQL\\libmariadb.dll");
            this.conectar = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Concesionario?user=root&password=root");
            System.out.println("connected");
    }
    
    //cerrar la conexion
    public void closeConnection() throws SQLException{
        this.conectar.close();
    }
    //para establecer la conexión
    public Connection getConnection(){
       return this.conectar;
    }
}
