/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package prog10_tarea;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class. Se utiliza JavaFX22. La clase dónde se van a realizar las operaciones de la calculadora.
 * Se puede hacer operaciones con más de dos operandos, ya que el resultado de una operación se guarda 
 * hasta que el usuario pulse C (clear). 
 * Se puede sumar, restar, multiplicar y dividir los números enteros.
 * Como una operación adicional, se puede exponer un número (EXP).
 * 
 * Para realizar más de dos operaciones, una después de otra, al pulsar un botón de la operación, se mostrará el resultado
 * de la operación anterior. Pero para realizar otra opción diferente, hay que pulsar el botón de la operación de nuevo,
 * alternativamente, pulsar "=" y luego realizar otra operación, el primer número de la cual va a ser el resultado de la
 * operación anterior, si el usuario no pulse "C" para limpiar los datos.
 * 
 * @todo Corregir el código para no tener que pulsar la operación dos veces o "=" para realizar la siguiente operación.
 * Por ejemplo: 2+3 + (muestra 5) +1 * (muestra 6)...
 * Si no pulsar el símbolo de la operación de nuevo, se mostrará el siguiente número pulsado: 2+3 + (->5) 4 (->54). 
 * Para concluir, no se puede realizar una operación hasta que aparezca su símbolo en la pantalla después del número.
 * También, si pulsar el botón de la operación, el símbolo aparece an la pantalla, y el usuario pulsa otra operación,
 * en la consola se mostrará error, pero la calculadora va a esperar el segundo número para realizar la operación
 * indicada anteriormente. Por ejemplo: 2+ - (error en la consola) 3 = 5. 
 * 
 * @author Yuliia Teterko Bobrytska
 */
public class CalculadoraFXMLController implements Initializable {

    //inicialización de las variables para la calculadora
    private int num1; //primer número insertado
    private int num2; //segundo número insertado o el número insertado después de obtener un resultado de una operación
    private String operacion; //lo que corresponde a los símbolos +,-,*,/ y EXP
    private int resultado; //resultado de la operación y el número que va a ser utilizado si realizar más de una operación
    
    //setters y getters para las variables 
    /**
     * @return the num1
     */
    public int getNum1() {
        return num1;
    }

    /**
     * @param num1 the num1 to set
     */
    public void setNum1(int num1) {
        this.num1 = num1;
    }

    /**
     * @return the num2
     */
    public int getNum2() {
        return num2;
    }

    /**
     * @param num2 the num2 to set
     */
    public void setNum2(int num2) {
        this.num2 = num2;
    }

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the resultado
     */
    public int getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    //los botones de la GUI
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;
    @FXML
    private Button boton6;
    @FXML
    private Button boton7;
    @FXML
    private Button boton8;
    @FXML
    private Button boton9;
    @FXML
    private Button boton0;
    @FXML
    private Button botonResultado;
    @FXML
    private Button botonSumar;
    @FXML
    private Button botonRestar;
    @FXML
    private Button botonMultiplicar;
    @FXML
    private Button botonDividir;
    @FXML
    private Button botonSalir;
    @FXML
    private Button botonLimpiar;
    @FXML
    private Button botonExponer;
    //campo de texto de la GUI
    @FXML
    private TextField mostrarResultadoTxtF;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    /*métodos para los botones de los números. se inicializa String númeroActual; cuando el usuario pulsa un número, aparece en la pantalla.
    Si el usuario quiere añadir otro dígito, por ejemplo, insertar el número 17, se concatena el nuevo número añadido y se muestran todos los números en la pantalla. 
    */
    @FXML
    private void clickBoton1(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "1");
    }

    @FXML
    private void clickBoton2(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "2");
    }

    @FXML
    private void clickBoton3(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "3");
    }

    @FXML
    private void clickBoton4(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "4");
    }

    @FXML
    private void clickBoton5(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "5");
    }

    @FXML
    private void clickBoton6(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "6");
    }

    @FXML
    private void clickBoton7(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "7");
    }

    @FXML
    private void clickBoton8(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "8");
    }

    @FXML
    private void clickBoton9(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "9");
    }

    @FXML
    private void clickBoton0(ActionEvent event) {
        String numeroActual = this.mostrarResultadoTxtF.getText();
        this.mostrarResultadoTxtF.setText(numeroActual + "0");
    }
    
    /**
     * Método para comprobar si el botón pulsado es un número. Se verifica si el texto del botón contiene el texto indicado en el método
     * @param btn
     * @return true si el botón es un número
     */
    private boolean botonEsNumero(Button btn) {
        String txt = btn.getText();
        if ("0123456789".contains(txt)) {
            return true;
        } 
        else {
            return false;
        }
    }
    
    /**
     * Método para comprobar si el botón pulsado es un símbolo de la operación. Se verifica si el texto del botón contiene el texto indicado en el método
     * @param texto
     * @return 
     */
    private boolean esOperacion(String texto) {
        if ("+/x-".contains(texto)) {
            return true;
        } 
        else {
            return false;
        }
    }
    
    //las acciones que hace el programa al pulsar cualquier botón
    @FXML
    private void pulsarBoton(ActionEvent event) { //al pulsar boton en la GUI, se llamará este método
        Button btnPulsado = (Button) event.getSource(); //obtiene el texto del botón pulsado
        //si el botón pulsado es número, lo muestra por pantalla
        if (botonEsNumero(btnPulsado)) { //llama al método para comprobar si el usuario ha insertado el número
                this.mostrarResultadoTxtF.setText(this.mostrarResultadoTxtF.getText() + btnPulsado.getText());
                System.out.println("Es un número"); //mensaje para controlar la ejecución por la consola
        }
        else { //si el boton pulsado no es número
            if (this.num1 == 0 && !this.mostrarResultadoTxtF.getText().isEmpty()) { //si se cumple esta condición, el programa está iniciando una operación (num1 debe ser igual a 0 para darle valor)
                setNum1(parseInt(this.mostrarResultadoTxtF.getText()));//se agrega primer número para la operación
                setOperacion(btnPulsado.getText()); //la operación que se va a realizar 
                this.mostrarResultadoTxtF.setText(this.mostrarResultadoTxtF.getText() + btnPulsado.getText()); //agrega el símbolo después del nçumero
                System.out.println("Es un simbolo");
            } 
            else { 
                System.out.println("Vamos a calcular");
                ejecutarCalculo(event);//se llama al método para realizar el cálculo
            }
        }
    }//pulsarBoton
    
    /**
     * el método para realizar los cálculos y la operación de exponer. Contiene las fórmulas para calcular el resultado de las operaciones dadas
     * @param num1 primer número de la operación
     * @param operacionActual2 operacion a realizar (+,-,*,/ o EXP)
     * @param num2 segundo número de la operación
     * @return devuelve el resultado de la operación 
     */
    private int calcular(int num1, String operacionActual2, int num2) {
        int resultado = 0;
            switch (operacionActual2) {
                case "+":
                    resultado = num1 + num2;
                   break;
                case "-":
                    resultado = num1 - num2;
                break;
                case "X":
                    resultado = num1 * num2;
                break;
                case "/":
                    resultado = num1 / num2;
                break;
                case "EXP":
                    resultado = (int) Math.pow(num1, num2); //método para exponer un número
                break;
            default:
                System.out.println("Operación desconocida");
            }
            return resultado;
    }
        
    /**
     * Método para realizar la operación. Está conectado al botón "=" de la calculadora
     * @param event 
     */
    @FXML	
    private void ejecutarCalculo(ActionEvent event) {
        //se establece el segundo número (o el siguiente) para las operaciones. Aparece en la pantalla después del num1 y símbolo de la operacion
	setNum2(parseInt(this.mostrarResultadoTxtF.getText().replace(this.num1 + this.operacion, "")));
	
        //se hace el cálculo, dependiendo de los números y el símbolo de la operación
	int resultadoOperacion = calcular(this.num1, this.operacion, this.num2);
        System.out.println("El resultado: " + resultadoOperacion); //se muestra en la consola el resultado
    }
    
    /**
     * El botón para limpiar los datos y empezar las operaciones de nuevo
     * @param event 
     */
    @FXML
    private void clickBotonLimpiar(ActionEvent event) {
        this.mostrarResultadoTxtF.setText("");
	this.num1 = 0;
	this.num2 = 0;
        this.operacion = null;
    }
    
    //el resto de botones de la GUI
    @FXML
    private void clickBotonResultado(ActionEvent event) {   
    }
   
    @FXML
    private void clickBotonSumar(ActionEvent event) {
    }

    @FXML
    private void clickBotonRestar(ActionEvent event) {   
    }

    @FXML
    private void clickBotonMultiplicar(ActionEvent event) {   
    }

    @FXML
    private void clickBotonDividir(ActionEvent event) {   
    }

    @FXML
    private void clickBotonExponer(ActionEvent event) {
    }
}//controller