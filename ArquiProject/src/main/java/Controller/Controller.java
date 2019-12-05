/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import UI.MainUI;
import UI.VentanaTemperatura;
import arquiproject.OtherMain;

/**
 *
 * @author jimmy
 */
public class Controller {

    MainUI mainUI;
    VentanaTemperatura ventanaTemperatura = new VentanaTemperatura();
    String serial;

    public Controller() {
        
    }

    //Métodos SET
    public void setMainUI(MainUI mainUI) {
        this.mainUI = mainUI;
    }

    public void setVentanaTemperatura(VentanaTemperatura ventanaTemperatura) {
        this.ventanaTemperatura = ventanaTemperatura;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    

    
    //Métodos GET
    public MainUI getMainUI() {
        return mainUI;
    }

    public VentanaTemperatura getVentanaTemperatura() {
        return ventanaTemperatura;
    }

    public String getSerial() {
        return serial;
    }
    
    
    /*------------------------FUNCIONES DEL CONTROLER------------------------*/
    
    //FUNCIONES LOGICAS
    
    public float interpretarTemperaturaLS(){
        String temperatura="";
        for (int i=3;i<6;i++){
            char caracter = serial.charAt(i);
            temperatura = temperatura + caracter;
        }
        return Float.parseFloat(temperatura);
    }
    
    public float interpretarTemperaturaMS(){
        String temperatura="";
        float temperaturaCompleta=0;
        temperatura = temperatura + serial.charAt(2);
        switch (temperatura){
            case "0":
                temperaturaCompleta=0;
                break;
            case "1":
                temperaturaCompleta=256;
                break;
            case "2":
                temperaturaCompleta=512;
                break;
            case "3":
                temperaturaCompleta=768;
                break;
        }
        return temperaturaCompleta;
    }
    
    public int interpretarPotenciometro(){
        String potenciometro="";
        for (int i=6;i<9;i++){
            char caracter = serial.charAt(i);
            potenciometro = potenciometro + caracter;
        }
        double posicionEnPantalla = Integer.parseInt(potenciometro)/86;
        return (int)Math.floor(posicionEnPantalla);
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
    
    public float interpretarTemperatura(int temp){
        float temperatura = temp*5*100/1024;
        return temperatura;
    }
    
    
    
}
