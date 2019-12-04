/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import UI.MainUI;
import UI.VentanaTemperatura;

/**
 *
 * @author jimmy
 */
public class Controller {

    MainUI mainUI;
    VentanaTemperatura ventanaTemperatura = new VentanaTemperatura();

    public Controller() {
        
    }

    //Métodos SET
    public void setMainUI(MainUI mainUI) {
        this.mainUI = mainUI;
    }

    public void setVentanaTemperatura(VentanaTemperatura ventanaTemperatura) {
        this.ventanaTemperatura = ventanaTemperatura;
    }
    
    //Métodos GET
    public MainUI getMainUI() {
        return mainUI;
    }

    public VentanaTemperatura getVentanaTemperatura() {
        return ventanaTemperatura;
    }
    
    /*------------------------FUNCIONES DEL CONTROLER------------------------*/
    
    //FUNCIONES LOGICAS
    
    public int interpretarTemperaturaLS(String serial){
        String temperatura="";
        for (int i=0;i<3;i++){
            char caracter = serial.charAt(i);
            temperatura = temperatura + caracter;
        }
        return Integer.parseInt(temperatura);
    }
    
    public int interpretarTemperaturaMS(String serial){
        String temperatura="";
        temperatura = temperatura + serial.charAt(3);
        return Integer.parseInt(temperatura);
    }
    
    public int interpretarPotenciometro(String serial){
        String potenciometro="";
        for (int i=4;i<7;i++){
            char caracter = serial.charAt(i);
            potenciometro = potenciometro + caracter;
        }
        float posicionEnPantalla = Integer.parseInt(potenciometro)/85;
        return (int)posicionEnPantalla;
    }
    
    public boolean interpretarBotonSalir(char serial){
        boolean boton = false;
        if (serial=='1'){
            boton = true;
        }
        return boton;
    }
    
    public boolean interpretarBotonEntrar(char serial){
        boolean boton = false;
        if (serial=='1'){
            boton = true;
        }
        return boton;
    }
    
    //FUNCIONES DEL MainUI
    
    
    //FUNCIONES DEL VentanaTemperatura
    public void openVentanaTemperatura(){
        ventanaTemperatura.setVisible(true);
    }
    
    
    
}
