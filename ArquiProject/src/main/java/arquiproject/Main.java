
package arquiproject;

import Controller.Controller;
import UI.MainUI;
import UI.VentanaTemperatura;
import app.Com;
import app.Parameters;
import core.SerialPort;
import java.util.List;
import static serialPortReader.SerialPortReader.puertosDisponibles;

public class Main {


    public static void main(String[] args) throws Exception{
        Controller controller = new Controller();
        MainUI mainUI = new MainUI();
        VentanaTemperatura ventanaTemperatura = new VentanaTemperatura();

        controller.setMainUI(mainUI);
        controller.setVentanaTemperatura(ventanaTemperatura);
        mainUI.setController(controller);
        ventanaTemperatura.setController(controller);
        
        mainUI.setVisible(true);
       
        SerialPort puerto = new SerialPort();
        List<String> listaPuertos;
        Com com5;
        String recibido = "";
        String caracter;
        Parameters settings = new Parameters();
        double celsius;
        
        listaPuertos = puerto.getFreeSerialPort(); //Obtener puertos disponibles y enlistarlos
        puertosDisponibles(listaPuertos);
        settings.setPort("COM5"); //Configurar 
        settings.setBaudRate("115200");
        
        com5 = new Com(settings); //Crear nuevo COM
        
        while(true){
            recibido = "";
            while(!recibido.endsWith("\n")){ //Concatenar 3 veces (CDU)
                caracter = com5.receiveSingleString();
                recibido += caracter;
            }
            Thread.sleep(100);
        }  
        
        
    }
    
}
