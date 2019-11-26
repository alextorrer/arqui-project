
package serialPortReader;

import app.*;
import core.*;
import java.util.List;

public class SerialPortReader {

    public static void main(String[] args) throws Exception {
       
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
        settings.setBaudRate("9600");
        
        com5 = new Com(settings); //Crear nuevo COM
        
        while(true){
            recibido = "";
            while(!recibido.endsWith("\n")){ //Concatenar 3 veces (CDU)
                caracter = com5.receiveSingleString();
                recibido += caracter;
            }
            Thread.sleep(100);
            celsius = convertirACelsius(recibido);
            System.out.println(celsius + " °C");
        }         
    }
    
    //Imprimir puertos disponibles
    public static void puertosDisponibles(List<String> listaPuertos){
        for(String string: listaPuertos){
            System.out.println("Puerto disponible: " + string);
        }
    }

    public static double convertirACelsius(String kelvinString){
            double celsius = 0;
            double kelvin = 0;
            kelvin = Double.parseDouble(kelvinString);
            celsius = kelvin - 273.15;
            return celsius;
    }
      
}
