/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog11.princ;

/**
 *Clase para hacer las pruebas con los métodos de otras clases de este programa. 
 * Se añaden los mensajes para que el usuario sepa si la operación se ha realizado con éxito.
 * @author Yuliia Teterko Bobrytska
 */
import com.prog11.bbdd.ConnectionDB;
import com.prog11.bbdd.PropietariosDAO;
import com.prog11.bbdd.VehiculosDAO;
import java.util.ArrayList;

public class Prog11_Principal {
    
    public static void main(String[] args) {
        ConnectionDB conectar = new ConnectionDB();
        ArrayList<String> vehic = new ArrayList<>();
        
        //Insertar varios vehículos y propietarios.
        //insertar propietario
        //comprobar si el propietario que quiero insertar no existe
        if (PropietariosDAO.insertarPropietario(conectar, 55555, "Lucia Delgado Pérez", "55555555T") == 0) { 
            System.out.println("El propietario se ha insertado con éxito");
        } 
        else {  
            System.out.println("Se ha producido un error al insertar el propietario");
        } 
        System.out.println("------\n"); //línea separador entre los comandos
               
        //insertar propietario
        if (PropietariosDAO.insertarPropietario(conectar, 66666, "Iván Pérez Delgado", "66666666Y") == 0) {
            System.out.println("El propietario se ha insertado con éxito");
        } 
        else {  
            System.out.println("Se ha producido un error al insertar el propietario");
        } 
        System.out.println("------\n"); 
        
        //insertar vehiculo con la marca que ya hay en el concesionario y uno de los propietarios nuevos  
        if (VehiculosDAO.insertarNuevoVehiculo(conectar, "7777UUU", "Toyota", 250000, 10500, 
                "muy buen estado", 55555) == 0) {
            System.out.println("El vehículo se ha insertado con éxito");
        } 
        else {  
            System.out.println("Se ha producido un error al insertar el vehículo");
        } 
        System.out.println("------\n"); 
        
        //insertar vehiculo con la marca que ya hay en el concesionario y uno de los propietarios nuevos
        if (VehiculosDAO.insertarNuevoVehiculo(conectar, "8888III", "Mercedes", 350000, 14500, 
                "color rojo", 66666) == 0) {
            System.out.println("El vehículo se ha insertado con éxito");
        } 
        else {  
            System.out.println("Se ha producido un error al insertar el vehículo");
        } 
        System.out.println("------\n"); 
           
        //Listar todos los vehículos.
        vehic = VehiculosDAO.recuperarTodosVehic(conectar);
        int numVehic = 1; //contador de vehículos para poner el número al lado de cada vehiculo en la lista, para que sea más cómodo leer
        
        if (vehic != null) {
            System.out.println("---Lista de todos los vehículos---");
            for (String vehiculo : vehic){ //bucle for-each para iterar en la lista (aquí y en las siguientes consultas)
                System.out.println("Vehículo " + numVehic + ":\n");
                System.out.println(vehiculo);
                numVehic++; //incrementar el contador de vehiculos
            System.out.println("------\n");    
            }
        }
        System.out.println("------\n"); 
   
        //Actualizar propietario de un vehículo: el vehiculo con matricula 1111QQQ, en vez de propietario 11111, va a tener el propietario 22222
        if (VehiculosDAO.actualizarPropVehic(conectar, "1111QQQ", 22222) == 0) {
            System.out.println("El vehiculo tiene el nuevo propietario.");
        } 
        else {
            System.out.println("Error al cambiar el propietario");
        }
        System.out.println("------\n"); 
   
        //Listar todos los vehículos.
        vehic = VehiculosDAO.recuperarTodosVehic(conectar);
        int numVehic1 = 1; //contador de vehículos para poner el número al lado de cada vehiculo en la lista, para que sea más cómodo leer
        
        if (vehic != null) {
            System.out.println("---Lista de todos los vehículos---");
            for (String vehiculo : vehic){
                System.out.println("Vehículo " + numVehic1 + ":\n");
                System.out.println(vehiculo);
                numVehic1++; //incrementar el contador de vehiculos
            System.out.println("------\n");    
            }
        }
      
        //Eliminar un vehículo que exista: con matrícula 2222WWW
        if (VehiculosDAO.eliminarVehiculo(conectar, "2222WWW") == 0) { //si después del llamar el método el resultado será 0
            System.out.println("El vehiculo se ha eliminado con éxito");
        } 
        else {
            System.out.println("Error al eliminar el vehiculo");
        }
        System.out.println("------\n"); 
        
        //Eliminar un vehículo que NO exista.
        if (VehiculosDAO.eliminarVehiculo(conectar, "9999WWW") == 0) { //si después del llamar el método el resultado será 0
            System.out.println("El vehiculo se ha eliminado con éxito");
        } 
        else {
            System.out.println("Error al eliminar el vehiculo");
        }
        System.out.println("------\n"); 
       
        //Listar todos los vehículos.
        vehic = VehiculosDAO.recuperarTodosVehic(conectar);
        int numVehic2 = 1; //contador de vehículos para poner el número al lado de cada vehiculo en la lista, para que sea más cómodo leer
        
        if (vehic != null) {
            System.out.println("---Lista de todos los vehículos---");
            for (String vehiculo : vehic){
                System.out.println("Vehículo " + numVehic2 + ":\n");
                System.out.println(vehiculo);
                numVehic2++; //incrementar el contador de vehiculos
            System.out.println("------\n");    
            }
        }
        System.out.println("------\n"); 
       
        //Listar los vehículos de una marca.
        vehic = VehiculosDAO.recuperarVehicMarca(conectar, "Toyota");

        for (String vehiculos : vehic) {
            System.out.println("Los vehículos de la marca: " + vehiculos + "\n");
        }
        System.out.println("------\n"); 
       
        //Listar todos los vehículos de un propietario.
        vehic = PropietariosDAO.recuperarVehiculosDeProp(conectar, "33333333E");

        for (String vehiculos : vehic) {
            System.out.println("Los vehículos del propietario: " + vehiculos);
        }
        System.out.println("------\n"); 
            
        //Eliminar un propietario con vehículos, por ejemplo, id 44444
        if (PropietariosDAO.eliminarPropietario(conectar, "44444444R") == 0) {
            System.out.println("El propietario se ha eliminado con éxito");
        } 
        else {
            System.out.println("Error al eliminar el propietario");
        }
        System.out.println("------\n"); 
     
        //Eliminar un propietario sin vehículos: este será el propietario 11111, ya que después de las operaciones anteriores no tiene vehículos
        if (PropietariosDAO.eliminarPropietario(conectar, "11111111Q") == 0) {
            System.out.println("El propietario se ha eliminado con éxito");
        } 
        else {
            System.out.println("Error al eliminar el propietario");
        }
        System.out.println("------\n"); 
    
    }//main
}//principal
