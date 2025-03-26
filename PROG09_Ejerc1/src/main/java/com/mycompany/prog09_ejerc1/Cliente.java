/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog09_ejerc1;

/**
 *la clase para crear el objeto Persona para utilizarlo como el titular de la cuenta bancaria.
 * Se implementa la interfaz Serializable para poder 
 * reconstruir valor de variables del objeto Persona mediante otras clases de la aplicación.
 * @author Yuliia T
 */
import java.io.Serializable;

public class Cliente implements Serializable {
    
    //interfaz para poder devolver la información en cadena
    //public interface Imprimible {
      //  public String devolverInfoString();
    //}

/**
     * clase Persona con los atributos necesarios y la interfaz Serializable implementada
     */
    public class Persona implements Banco.Imprimible, Serializable{
        private String dni;
        private String nombre;
        private String apellidos;
        
        //constructor de la clase Persona
        public Persona(String dni, String nombre, String apellidos) {
            this.dni = dni;
            this.nombre = nombre;
            this.apellidos = apellidos;
        }
        
        //setters y getters para los datos de una  (establecer y obtener)
        public void setDni(String dni) {
        this.dni = dni;
        }
        
        public String getDni() {
        return dni;
        }
        
        public void setNombre(String nombre) {
        this.nombre = nombre;
        }
        
        public String getNombre() {
        return nombre;
        }

        public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
        }

        public String getApellidos() {
        return apellidos;
        }

        //para devolver la información en una cadena
        @Override
        public String devolverInfoString() {
            return "DNI: " + dni + "\nNombre: " + nombre + "\nApellido(s): " + apellidos;
        }       
    }
    
}