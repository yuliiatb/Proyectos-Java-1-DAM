/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog09_ejerc1;

/**
 * El programa permite crear cuentas bancarias de tres tipos, ingresar y retirar dinero, además de consultar la información de la cuentas creadas.
 * Antes de mostrar el menú, se muestran las cuentas creadas anteriormente, si hay.
 * @author Yuliia T
 */
import com.mycompany.prog09_ejerc1.Banco.*;
import com.mycompany.prog09_ejerc1.Cliente.*;
import java.util.regex.*;
import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;

public class Principal {
        
    /**
     * Menú que se va a mostrar para ejecutar el programa
     */
    private static void mostrarMenu() {
        System.out.println("---MENÚ---\nElige una opción\n1. Abrir una nueva cuenta.\n2. Ver un listado de las cuentas disponibles.\n"
                + "3. Obtener los datos de una cuenta concreta.\n4. Realizar un ingreso en una cuenta.\n5. Retirar efectivo de una cuenta.\n"
                + "6. Consultar el saldo actual de una cuenta.\n7. Eliminar cuenta bancaria.\n"
                + "8. Generar listado de clientes.\n9. Salir de la aplicación");
    }
       
    /**
     * Aquí se instancian los objetos y sus atributos para poder utilizarlos al ejecutar la app
     * @param args 
     */
    public static void main(String[] args) {
        
        // Crear una instancia de Banco
        Banco banco = new Banco();
        CuentaBancaria nuevaCuenta = null; 
        //llamar al método antes de de mostrar el menú para cargar en la estructura de datos el contenido del fichero
        banco.datosCuentasMostrar(); 
                
        Scanner sca = new Scanner(System.in);
        int valorSca = 0; //valor inicial de la opción del menu
        
        //crear instancia de la clase padre PROG07_Tarea para poder utilizarla en esta clase
        Cliente.Persona titular; 
        Cliente titular1 = new Cliente();//crear instancia de la clase padre PROG07_Tarea para poder utilizarla en esta clase
        
        //atributos que se vam a utilizar para crear varias cuentas
        String dni;
        String nombre;
        String apellidos;
        float saldo;
        String iban;
        float intereses;
        String entidadesAut;
        float comision;
        float interesDesc;
        float maxDesc;
        float comFija;
        
        //el bucle do-while para que el programa no termine al ejecutar una de las opciones del menú
        do {
            
            mostrarMenu();
            valorSca = sca.nextInt();
            sca.nextLine(); /*limpia el buffer de scanner después de leer un número para permitir la
            lectura adecuada de la siguiente línea; controla los saltos de línea*/
            
            switch (valorSca){
                //Abrir una nueva cuenta e introducir los datos del titular
                case 1:
                    System.out.println("Ahora Usted va a abrir una nueva cuenta.\nIntroduce los datos del titular:\n");
                    System.out.println("DNI:");
                    dni=sca.nextLine();
                    System.out.println("Nombre:");
                    nombre=sca.nextLine();
                    System.out.println("Apellido(s):");
                    apellidos=sca.nextLine();
                    
                    //crear instancia de Persona
                    titular = titular1.new Persona(dni, nombre, apellidos);
                    
                    //para captar si el formato del IBAN es correcto, y si no - pedir introducirlo de nuevo
                    do {
                        System.out.println("Introduce el IBAN:");
                        iban = sca.nextLine();
                            try {
                                if (!formatoIban(iban)){ //llamar el método para validar el iban
                                    throw new Exception ("El IBAN debe empezar con ES y 20 números de 0 a 9"); //lanzar excepción si hay fallos
                                }
                            }
                            catch (Exception e){//captar la excepción y mostrar el mensaje
                                System.out.println("Hay un error: " + e.getMessage());
                            }
                        }
                        //el bucle se repite hasta que el usuario introduzca los datos correctos
                        while (!formatoIban(iban));
                    
                    System.out.println("Introduce el saldo inicial:");
                    saldo = sca.nextFloat();
                    
                    //elegir tipo de la cuenta: switch con las opciones         
                    System.out.println("Elige el tipo de la cuenta:\n1. Cuenta de ahorro\n2. Cuenta corriente personal\n3. Cuenta corriente de empresa");
                    int tipoCuenta = sca.nextInt(); //para poder elegir el número que corresponde a una de las cuentas
                    sca.nextLine();
                        
                    //en cada caso se va a pedir introducir los datos correspondientes a cada cuenta
                        switch (tipoCuenta){
                            case 1: 
                                System.out.println("---Cuenta de ahorro---\n");
                                System.out.println("Introduce el tipo de interés:");
                                intereses = sca.nextFloat();
                                sca.nextLine(); 
                                
                                //CuentaBancaria nuevaCuenta = new CuentaBancaria(iban, titular, saldo);
                                //instancia de la cuenta con los atributos correspondientes al constructor
                                nuevaCuenta = banco.new CuentaAhorro(iban, titular, saldo, intereses);
                            break;
                            
                            case 2:
                                System.out.println("---Cuenta corriente personal---\n");
                                System.out.println("Introduce las entidades autorizadas:");
                                entidadesAut = sca.nextLine();
                                System.out.println("Introduce el tipo de comisión:");
                                comision = sca.nextFloat();
                                sca.nextLine(); 
                                
                                 //instancia de la cuenta con los atributos correspondientes al constructor
                                nuevaCuenta = banco.new CuentaCorPers(iban, titular, saldo, entidadesAut, comision);
                            break;
                            
                            case 3:
                                System.out.println("---Cuenta corriente de empresa---\n");
                                System.out.println("Introduce las entidades autorizadas:");
                                entidadesAut = sca.nextLine();
                                System.out.println("Introduce la comisión fija:");
                                comFija = sca.nextFloat();
                                sca.nextLine(); 
                                System.out.println("Introduce los intereses de descubierto:");
                                interesDesc = sca.nextFloat();
                                sca.nextLine(); 
                                System.out.println("Introduce máximo descubierto permitido:");
                                maxDesc = sca.nextFloat();
                                sca.nextLine(); 
                                
                                 //instancia de la cuenta con los atributos correspondientes al constructor
                                nuevaCuenta = banco.new CuentaCorEmp(iban, titular, saldo, entidadesAut, comFija, interesDesc, maxDesc);
                            break;
                            
                            default: //si el usuario pulse algo diferente de 1,2 o 3
                                System.out.println("La opción es inválida");
                        }
                        //cuentas.add(nuevaCuenta);
                        
                        //revisar si todo está correcto y avisar al usuario si la cuenta ha sido creada o no
                        if (banco.abrirCuenta(nuevaCuenta)){
                            System.out.println("La cuenta está creada.");
                        }
                        else {
                            System.out.println("Error al abrir la cuenta. Los cambios no se han guardado");
                        }
                        break;
                    
                    //ver un listado de las cuentas disponibles (código de cuenta, titular y saldo actual).    
                    case 2:
                        String[] cuentasList = banco.listadoCuentas(); 
                        
                        if (cuentasList != null){                      
                            //almacenar las cadenas para imprimir cada una por separado
                            for (String cuenta : cuentasList) {
                            System.out.println("La información de las cuentas: " + cuenta);
                            }   
                        }
                        else {
                             System.out.println("Error. No hay cuentas.");   
                        }
                          
                    break;
                    
                    //Obtener los datos de una cuenta concreta.
                    case 3:
                        System.out.println("Introduce el IBAN:");
                        iban = sca.nextLine();
                        System.out.println("Información de la cuenta:\n" + banco.informacionCuenta(iban));
                    break;
                    
                    //Realizar un ingreso en una cuenta.
                    case 4:
                        float cantidadIngreso; //declarar la cantidad que se va a ingresar
                        
                        System.out.println("Introduce el IBAN:");
                        iban = sca.nextLine();
                        System.out.println("Introduce la cantidad para ingresar:");
                        cantidadIngreso = sca.nextFloat();
                        
                        if(banco.ingresoCuenta(iban, cantidadIngreso)){ //llamar al método correspondiente de la clase Banco
                            System.out.println("La cantidad ha sido ingresada.");
                        }
                        else{
                            System.out.println("Se ha producido un error.");
                        }
                        
                    break;
                    
                    //Retirar efectivo de una cuenta.
                    case 5:
                        float cantidadRetiro; //declarar la cantidad que se va a retirar
                        
                        System.out.println("Introduce el IBAN:");
                        iban = sca.nextLine();
                        System.out.println("Introduce la cantidad para retirar:");
                        cantidadRetiro = sca.nextFloat();
                        
                        if(banco.retiradaCuenta(iban, cantidadRetiro)){ //llamar al método para realizar la operación
                            System.out.println("La cantidad ha sido retirada. ");
                        }
                        else{
                            System.out.println("Se ha producido un error.");
                        }
                    break;
                    
                    case 6:
                        System.out.println("Introduce el IBAN:");
                        iban = sca.nextLine();
                        System.out.println(banco.obtenerSaldo(iban)); //llamar al método de la clase Banco para visualizar el saldo actual
                    break;
                    
                    case 7:
                        System.out.println("Introduce el IBAN:");
                        iban = sca.nextLine();
                        
                        if (banco.eliminarCuenta(iban)){
                           System.out.println("La cuenta ha sido eliminada con éxito"); 
                        }
                        else {
                            System.out.println("Error. Asegúrese que el IBAN es correcto o el saldo de la cuenta es 0.00");
                        }
                    break;
                    
                    case 8:
                        banco.listadoClientes(); //llamar al método que genere el listado de clientes
                        System.out.println("El listado de clientes se ha generado en un archivo.");
                    break;
                    
                    case 9:
                        banco.datosCuentasGuardar();//al salir se llamará al método que guarde los cambios de los datos 
                        System.out.println("Saliendo del programa. Los cambios se han guardado.");
                    break;
                        
                default:
                    System.out.println("La opción es inválida");
            }
        }//do
        
        while (valorSca != 9); //el bucle parará cuando el usuario pulse 7
    }//principal

/**
 * Método para validar el formato de IBAN
 * @param iban el IBAN introducido por el usuario que se va a validar
 * @return si el iban introducido cumple con el formato establecido
 */
public static boolean formatoIban (String iban) {
    Pattern patternIban = Pattern.compile("ES[0-9]{20}");
    Matcher matcher = patternIban.matcher(iban);
    return matcher.matches();
}

}
