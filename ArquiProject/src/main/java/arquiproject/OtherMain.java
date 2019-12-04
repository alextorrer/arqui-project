/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquiproject;

import Controller.Controller;
import UI.MainUI;
import UI.VentanaTemperatura;

/**
 *
 * @author jimmy
 */
public class OtherMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Controller controller = new Controller();
        MainUI mainUI = new MainUI();
        VentanaTemperatura ventanaTemperatura = new VentanaTemperatura();

        controller.setMainUI(mainUI);
        controller.setVentanaTemperatura(ventanaTemperatura);
        mainUI.setController(controller);
        ventanaTemperatura.setController(controller);
        
        String serial = "255215000";
        controller.setSerial(serial);
        
        mainUI.setVisible(true);
        
        
    }
    
}
