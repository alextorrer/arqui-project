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
    public void leerSerial(String serial){
        
    }
    
    public void interpretarTemperatura(String serial){
        for (int i=0;i<3;i++){
            
        }
    }
    
    //FUNCIONES DEL MainUI
    
    
    //FUNCIONES DEL VentanaTemperatura
    public void openVentanaTemperatura(){
        ventanaTemperatura.setVisible(true);
    }
    
    
    
}
