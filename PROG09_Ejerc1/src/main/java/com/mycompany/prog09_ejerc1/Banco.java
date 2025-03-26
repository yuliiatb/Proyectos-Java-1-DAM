/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog09_ejerc1;

/**
 * En esta clase se almacenarán las cuentas y se realizarán las operaciones con ellas. Dispone de constructores
 * y métodos para poder trabajar con las cuentas. Se implementa la interfaz Serializable para poder 
 * reconstruir valor de variables del objeto CuentaBancaria mediante otras clases de la aplicación.
 * Los datos de las cuentas se van a guardar en los archivos después de cerrar el programa.
 * Se puede generar el listado de clientes para mostrar sus nombres, apellidos, IBAN y el 
 * número de cuentas.
 * 
 * @author Yuliia T
 */

import com.mycompany.prog09_ejerc1.Cliente.*;
import java.lang.String;
import java.util.ArrayList;
//imports necesarios para trabajar con Stream
import java.io.Serializable;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Banco implements Serializable{
    
    //declaración del ArrayList con el nombre cuentasEnBanco que almacenará las cuentas
    private ArrayList <CuentaBancaria> cuentasEnBanco;
    private int numCuentas; //contador de cuentas
    
    
    //constructor de la clase; se ejecuta cuando se crea una nueva instancia de la clase Banco
    public Banco (){
        this.numCuentas = 0;
        this.cuentasEnBanco = new ArrayList<>(); 
    }
    
    //interfaz para poder devolver la información en cadena
    public interface Imprimible {
        public String devolverInfoString();
    } 
    
    /**
     * Clase padre. Posee los atributos comunes para las subclases e implementa interfaz para poder mostrar la info en cadena.
     * Se implementa la interfaz Serializable para poder reconstruir valor de variables del objeto. 
     */
    public class CuentaBancaria implements Imprimible, Serializable {
        private String iban; 
        private float saldo;
        private Cliente.Persona titular; //utilizar la clase Persona para crear el titular de la cuenta
        
        //constructor de la clase Cuenta
        public CuentaBancaria(String iban, Cliente.Persona titular, float saldo) {
            this.iban = iban;
            this.titular = titular;
            this.saldo = saldo;
        }
        
        //setters y getters para los atributos de la cuenta
        public void setIban (String iban){
            this.iban = iban;
        }
        
        public String getIban(){
            return iban;
        }
        
        public void setTitular (Cliente.Persona titular){
            this.titular = titular;
        }
        
        public Cliente.Persona getTitular(){
            return titular;
        }
        
        public void setSaldo (float saldo){
            this.saldo = saldo;
        }
        
        public float getSaldo(){
            return saldo;
        }
            
            //devolver toda la info en una cadena mças los atributos propios
            @Override
            public String devolverInfoString(){
                return "Titular de la cuenta:" + titular.devolverInfoString() + "\nIBAN de la cuenta: " + iban + "\nSaldo:" + saldo;
            }
    }
    
    //subclase que hereda de CuentaBancaris
    public class CuentaAhorro extends CuentaBancaria implements Serializable{
        //atributo propio, añadido a los atributos de la superclase
        private float intereses;
        
        //constructor que incluye los atributos de la subclase y uno propio
        public CuentaAhorro(String iban, Cliente.Persona titular, float saldo, float intereses) {
        super(iban, titular, saldo); //atributos de la superclase
            this.intereses = intereses; //declaración del atributo propio
        }
        //setters y getters para los atributos propios de este tipo de la cuenta
        public void setIntereses (float intereses){
            this.intereses = intereses;
        }
        
        public float getIntereses(){
            return intereses;
        }
        
        @Override
        public String devolverInfoString(){
            return super.devolverInfoString() + "\nEl tipo de interes: " + intereses;
        }
    }
    
    //subclase que hereda de CuentaBancaria
    public class CuentaCorriente extends CuentaBancaria implements Serializable{
        private String entidadesAut; //atributo propio, añadido a los atributos de la superclase
        
        public CuentaCorriente(String iban, Cliente.Persona titular, float saldo, String entidadesAut){
        super (iban, titular, saldo);
                this.entidadesAut = entidadesAut; //declaración del atributo propio
        }
        
        //setters y getters para los atributos propios de este tipo de la cuenta
        public void setEntidad (String entidadesAut){
            this.entidadesAut = entidadesAut;
        }
        
        public String getEntidad (){
            return entidadesAut;
        }
        
        @Override
        public String devolverInfoString(){
            return super.devolverInfoString() + "\nEntidades autorizadas: " + entidadesAut;
        }
    }
    
    //subclase que hereda de CuentaBancaria y CuentaCorriente
    public class CuentaCorPers extends CuentaCorriente implements Serializable{
        //atributos propios
        private float comision;
        
        //constructor que incluye los atributos de las 2 subclases
        public CuentaCorPers (String iban, Cliente.Persona titular, float saldo, String entidadesAut, float comision){
        super (iban, titular, saldo, entidadesAut);
                this.comision = comision;
        }
        
        //setters y getters para los atributos propios de este tipo de la cuenta
        public void setComision (float comision){
            this.comision = comision;
        }
        
        public float getComision (){
            return comision;
        }
        
        @Override
        public String devolverInfoString(){
            return super.devolverInfoString() + "\nComisión de mantenimiento: " + comision;
        }
    }
    
    public class CuentaCorEmp extends CuentaCorriente implements Serializable{
        //atributos propios
        float comFija;
        float interesDesc;
        float maxDesc;
        
        //constructor que incluye los atributos de las subclases y propios
        public CuentaCorEmp (String iban, Cliente.Persona titular, float saldo, String entidadesAut, float comFija, float interesDesc, float maxDesc){
            super(iban, titular, saldo, entidadesAut);
            this.comFija = comFija;
            this.interesDesc = interesDesc;
            this.maxDesc = maxDesc;
        }
        
        //setters y getters para los atributos propios de este tipo de la cuenta
        public void setComFija (float comFija){
            this.comFija = comFija;
        }
        
        public float getComFija(){
            return comFija;
        }
        
        public void setInteresDesc (float interesDesc){
            this.interesDesc = interesDesc;
        }
        
        public float getInteresDesc(){
            return interesDesc;
        }
        
        public void setMaxDesc (float maxDesc){
            this.maxDesc = maxDesc;
        }
        
        public float getMaxDesc(){
            return maxDesc;
        }
        
        @Override
        public String devolverInfoString() {
            return super.devolverInfoString() + "\nComisión fijapor descubrimiento: " + comFija + "\nInteres por descubierto: " + interesDesc
                    + "\nMáximo descuebierto permitido: " + maxDesc;
        }
    }
    
    /**
     * Método que permitirá o no abrir una cuenta. Se recorrerán todas las cuentas y se revisará si hay sitios 
     * en el banco (máx 100 cuentas)
     * @param nuevaCuenta instancia de la Cuentabancaria
     * @return si la cuenta ha sido añadida
     */
    public boolean abrirCuenta(CuentaBancaria nuevaCuenta){
               
        if (numCuentas >= 100) { //comprobar si el número de cuentas actuales es menor de 100
            return false; //no se puede abrir la cuenta, porque el límite máximo de la cantidad de cuentas ya está alcanzado
        }
        
        for (CuentaBancaria nuevaC : cuentasEnBanco) { //bucle for-each para recorrer el ArrayList
               //verificar si la cuenta con el iban introducido ya existe
               if (nuevaC.getIban().equals(nuevaCuenta.getIban())) {
            return false; //al encontrar una cuenta con el iban introducido, será imposible abrir otra cuenta con el mismo iban
               }
        } 
            cuentasEnBanco.add(nuevaCuenta); //se añade la cuenta a la lista
            numCuentas++; //el contador se incrementa en uno
            return true;
        
    }
    
    /**
     * El método devolverá un array con Strings que van a contener la info sobre las cuentas que están en el banco
     * @return devuelve arrays con la información sobre las cuentas almacenadas
     */
    public String[] listadoCuentas() { //no recibe parametros
        String[] listCuentas = new String [this.numCuentas]; //la longitud de este array es igual al número de cuentas
        int i = 0; //contador que indica la posicion en array
        
        for (CuentaBancaria nuevaCuenta : cuentasEnBanco){//el buscle recorrerá todas las cuentas en el banco
        listCuentas[i] = nuevaCuenta.devolverInfoString(); //para cada cuenta, el programa va a llamar el método devolverInfoString para mostrar la información
        i++; //aumentar el contador para recorrer todo el array
        }
        return listCuentas; //devuelve array que es el resultado del método
    }
    
    /**
     * El método muestra la información de la cuenta según el IBAN introducido
     * @param iban se va a comparar el iban introducido con uno del ArrayList. Si el iban no existe, el resultado será null
     * @return la información de la cuenta el el formato de cadena o null si la cuenta con el iban introducido no existe
     */
    public String informacionCuenta(String iban){
        for (CuentaBancaria nuevaCuenta : cuentasEnBanco){//el buscle recorrerá todas las cuentas en el banco
            if (nuevaCuenta.getIban().equals(iban)){
                return nuevaCuenta.devolverInfoString(); //si el programa encuentre el iban introducido, mostrará la información de la cuenta
            }
        }
        return null; //devuelve null si no encontrará el iban introducido
    }
    
    /**
     * El método para ingresar una cantidad a la cuenta
     * @param iban introducido por el usuario; se comprobará si la cuenta con este iban existe
     * @param cantidadIngreso la cantidad que el usuario va a ingresar
     * @return si la operación ha sido completada o no
     */
    public boolean ingresoCuenta (String iban, float cantidadIngreso){
            for (CuentaBancaria nuevaCuenta : cuentasEnBanco) { //recorrer el bucle para buscar la cuenta que corresponde con el iban introducido
            if (nuevaCuenta.getIban().equals(iban)){ //si encuentra un iban que corresponde con él introducido
                //establecer nuevo saldo -> ingresar: a la cuenta encontrada se va a añadir una cantidad
                //cuentasEnBanco[i].setSaldo(cuentasEnBanco[i].getSaldo() + cantidadIngreso); 
                nuevaCuenta.setSaldo(nuevaCuenta.getSaldo() + cantidadIngreso); //establecer el nuevo saldo
                return true; //devolver true si la operación ha sido completada con éxito
            }
        }
        return false; //si no fue posible realizar el ingreso
    }
    
    /**
     * El método para retirar una cantidad
     * @param iban introducido por el usuario; se comprobará si la cuenta con este iban existe
     * @param cantidadRetiro la cantidad que el usuario va a; se va a revisar si esta cantidad no es mayor que el saldo actual
     * @return si la operación ha sido completada o no
     */
    public boolean retiradaCuenta (String iban, float cantidadRetiro){
        for (CuentaBancaria nuevaCuenta : cuentasEnBanco) { //recorrer el bucle para buscar la cuenta que corresponde con el iban introducido
            if (nuevaCuenta.getIban().equals(iban)){ //si encuentra un iban que corresponde con él introducido
                //comprobar si la cantidad para retirar es menor o igual a saldo
                if (cantidadRetiro <= nuevaCuenta.getSaldo()){
                nuevaCuenta.setSaldo(nuevaCuenta.getSaldo() - cantidadRetiro); //establecer el nuevo saldo
                return true; //devolver true si la operación ha sido completada con éxito
                }
                else {
                   return false; //si la cantidad para retirar es mayor a saldo
                }
            }
        }
        return false; //si no fue posible realizar la retirada
    }
    
    /**
     * Se mostrará el saldo actual (puede ser después del ingreso, retirada o saldo inicial si lampersona no ha realizado ninguna operación).
     * @param iban introducido por el usuario; se comprobará si la cuenta con este iban existe
     * @return saldo de la cuenta con el IBAN introducido
     */
    public float obtenerSaldo (String iban){
        for (CuentaBancaria nuevaCuenta : cuentasEnBanco) { //recorrer el bucle para buscar la cuenta que corresponde con el iban introducido
            if (nuevaCuenta.getIban().equals(iban)){ //si encuentra un iban que corresponde con él introducido
                return nuevaCuenta.getSaldo(); //devolver saldo de la cuenta encontrada
            }
        }
        return -1; //si no se encontró la cuenta, devolverá -1
    }
    
    /**
     * Este método elimina la cuenta del banco. Para realizar esta operación, el saldo debe ser 0.0
     * @param iban se va a comprobar si la cuenta con el iban introducido existe en el banco
     * @return si la cuenta ha sido borrada con éxito o no
     */
    public boolean eliminarCuenta (String iban){
        //al encontrar el iban, el índice i va a ser utilizado para eliminar la cuenta de la lista 
        for (int i = 0; i < cuentasEnBanco.size(); i++) { //recorrer el bucle para buscar la cuenta que corresponde con el iban introducido
            if (cuentasEnBanco.get(i).getIban().equals(iban)){
                if (cuentasEnBanco.get(i).saldo == 0){//get devuelve el elemento en la posición específica de la lista
                cuentasEnBanco.remove(i);//metodo remove eliminará dicha cuenta.
                numCuentas --; //restar la cuenta eliminada del contador
                return true;
            }
        }
        }
        return false; //si la cuenta no existe o el saldo es mayor que 0
    }
    
    /**
     * El método se utiliza para guardar los datos de las cuentas en el archivo datoscuentasbancarias.dat.
     * 
     */
    public void datosCuentasGuardar(){
        try {
            FileOutputStream datosC = new FileOutputStream("datoscuentasbancarias.dat"); //escribir bytes en el fichero indicado
            ObjectOutputStream datos = new ObjectOutputStream(datosC); //escribir los datos de los objetos en OutputStream
        
            for (CuentaBancaria nuevaCuenta : cuentasEnBanco) {//recorrer todos los objetos
                datos.writeObject(nuevaCuenta);//escribir el objeto ObjectOutputStream para cada cuenta encontrada por el bucle
            }
        }
        catch (FileNotFoundException e) {//captar la excepción si el archivo no existe
            System.out.println("El archivo no existe.");
        }
        catch (IOException e) {//excepción si hay algún problema con el archivo (dañado, no hay acceso, etc)
            System.out.println("Hay un error al mostrar los datos.");
        } 
    }
    
    /**
     * El método se utiliza para leer los objetos serializados del archivo datoscuentasbancarias.dat
     * y mostrarlos.
     */
    public void datosCuentasMostrar(){
                   
        try {
            FileInputStream datos = new FileInputStream("datoscuentasbancarias.dat");
            ObjectInputStream mostrarCuentas = new ObjectInputStream(datos);
               
            while (true) {//mientras se cumple las condiciones de FileInputStream y ObjectInputStream
                    CuentaBancaria cuenta = (CuentaBancaria)mostrarCuentas.readObject();//leer los objetos de la clase CuentaBancaria
                    System.out.print(cuenta.devolverInfoString() + "\n");//mostrar las cuentas en la consola
            }
        }
        
        catch (EOFException e) {//para que el bucle pare al llegar al final del archivo
            System.out.println("Final del archivo.");
        }
        catch (ClassNotFoundException e) {//para mostrar un error si la clase CuentaBancaria no existe o tiene errores
            System.out.println("Clase no encontrada.");
        }
        catch (FileNotFoundException e) {//captar la excepción si el archivo no existe
            System.out.println("El archivo no existe.");
        }
        catch (IOException e) {//excepción si hay algún problema con el archivo (dañado, no hay acceso, etc)
            System.out.println("Hay un error al mostrar los datos.");
        }
    }
    
    
    /**
     * El método para guardar el nombre completo del titular de la cuenta y el IBAN de la cuenta en un documento de texto.
     * Los datos necesarios se guardarán en un String. Tambiém se mostrará el número total de cuentas añadidas.
     */
    public void listadoClientes(){
        try{
        
        FileWriter listadoC = new FileWriter("ListadoClientesCCC.txt", true);//para poder escribir carácteres en el archivo indicado 
        BufferedWriter listCl = new BufferedWriter(listadoC); //escribir en el archivo indicado
        
        String cuentab; //una cadena para almacenar la info sobre el titular
        
        for (CuentaBancaria nuevaCuenta : cuentasEnBanco) {//recorrer las cuentas
                //especificar qué información hay que almacenar
                cuentab = "Titular: " + nuevaCuenta.getTitular().devolverInfoString() + " IBAN: " + nuevaCuenta.getIban() + "\n";
                listCl.write(cuentab); //la cadena anterior se escribirá con el método write()
        }
        listCl.write("El número total de cuentas añadidas: " + cuentasEnBanco.size() + "\n");//añadir la información sobre la cantidad de cuentas
        listCl.close(); //cerrar el BufferedWriter para que se guarden los cambios
        }
        catch (FileNotFoundException e) {//captar la excepción si el archivo no existe
            System.out.println("El archivo no existe.");
        }
        catch (IOException e){//excepción si hay algún problema con el archivo (dañado, no hay acceso, etc)
            System.out.println("Hay un error.");
        }
    }
}

