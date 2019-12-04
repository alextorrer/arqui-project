/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquiproject;

import Controller.Controller;
import UI.MainUI;
import UI.VentanaTemperatura;
import app.Com;
import app.Parameters;
import core.SerialPort;

/**
 *
 * @author jimmy
 */
public class OtherMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        //Serial
        String serial;
        String caracter;
        SerialPort puerto = new SerialPort();
        Com com5 = null;
        Parameters settings = new Parameters();
            settings.setPort("COM5");  
            settings.setBaudRate("115200");
        
        //UI
        Controller controller = new Controller();
        MainUI mainUI = new MainUI();
        VentanaTemperatura ventanaTemperatura = new VentanaTemperatura();

        controller.setMainUI(mainUI);
        controller.setVentanaTemperatura(ventanaTemperatura);
        mainUI.setController(controller);
        ventanaTemperatura.setController(controller);
        
        mainUI.setVisible(true);
        
        while(true){
            serial = "";
            while(!serial.endsWith("\n")){ 
                caracter = com5.receiveSingleString();
                serial += caracter;
            }
            Thread.sleep(100);
            controller.setSerial(serial);
        }  
    
        //serial = "255215000";
        
        
             
    }
    
}
