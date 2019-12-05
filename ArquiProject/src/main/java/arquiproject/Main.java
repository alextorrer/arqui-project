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
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        //Inicializar el controlador
        Controller controller = new Controller();

        //Inicializa ventanas
        MainUI mainUI = new MainUI();
        VentanaTemperatura ventanaTemperatura = new VentanaTemperatura();

        //Enlaza el controlador con el mainUI
        controller.setMainUI(mainUI);
        //Enlaza el controlador con la ventanaTemperatura
        controller.setVentanaTemperatura(ventanaTemperatura);
        //Setea el controlador a las ventanas
        mainUI.setController(controller);
        ventanaTemperatura.setController(controller);
        
        //Lanza el mainUI
        mainUI.setVisible(true);
    }
    
}
