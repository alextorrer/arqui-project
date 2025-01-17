
package UI;
import Controller.Controller;
import app.Com;
import app.Parameters;
import arquiproject.Main;
import core.SerialPort;
import java.awt.Color;

public class MainUI extends javax.swing.JFrame {
    Controller controller;
    
    /**
     * Creates new form MainUI
     */
    public MainUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        /**/
    }
    
    //Crea un nuevo hilo para hacer la parte logica sin detener la parte grafica
    Thread ciclo = new Thread(){
        public void run(){
            try{
                cicloCheck();
            }
            catch(Exception e){
                
            }
        }
    };
    
    
    public void cicloCheck() throws Exception{
        //Instancio las dos ventanas
        VentanaTemperatura ventanaTemp = new VentanaTemperatura();
        VentanaEnviarCalificacion ventanaEC = new VentanaEnviarCalificacion();
        
        //Inicializo el serial
        String serial="";
        
        //Inicializo las variables que guardaran la informacion de la parte fisica
        int potenciometro;
        boolean botonSalir;
        boolean botonEntrar;
        
        //Configuro el COM5
        Parameters settings = new Parameters();
        settings.setPort("COM5");  
        settings.setBaudRate("115200");
        Com com5 = new Com(settings);
        
        //Espacio para variables de temperatura
        float tempLS;
        float tempMS;
        float temperatura;
        
        //Inicio el ciclo infinito de chequeo a los componentes fisicos
        while (true){
            
            //Serial  
            String caracter;
            SerialPort puerto = new SerialPort();
            
            try{
                //Lee el serial
                serial = "";
                while(!serial.endsWith("\n")){ 
                    caracter = com5.receiveSingleString();
                    serial += caracter;
                }
                if(serial.length()!=12){
                    serial = "00000000000";
                }
                //Guarda el serial en el controller
                controller.setSerial(serial);
            }
            catch(Exception e){
                System.out.println("No se pudo obtener el serial");
            }
            
            //Guarda el estados de los componentes fisicos
            potenciometro = controller.interpretarPotenciometro();
            botonSalir = controller.interpretarBotonSalir(serial.charAt(9));
            botonEntrar = controller.interpretarBotonEntrar(serial.charAt(10));
            
            //Revisa el estado del potenciometro y te posiciona en el boton virtual corrrespondiente
            switch (potenciometro){
                case 0:
                    this.temperature_btn.setBackground(Color.GRAY);
                    this.grade_btn.setBackground(Color.lightGray);
                    this.exit_btn.setBackground(Color.lightGray);
                    break;
                case 1:
                    this.grade_btn.setBackground(Color.GRAY);
                    this.exit_btn.setBackground(Color.lightGray);
                    this.temperature_btn.setBackground(Color.lightGray);
                    break;
                case 2:
                    this.exit_btn.setBackground(Color.GRAY);
                    this.grade_btn.setBackground(Color.lightGray);
                    this.temperature_btn.setBackground(Color.lightGray);
                    break;
            }
            
            //Checa el estado del boton salir
            if (botonSalir==true){
                //Si se encuentra en alguna ventana cierra la ventana
                if(ventanaTemp.isVisible()){
                    ventanaTemp.setVisible(false);
                }
                if (ventanaEC.isVisible()){
                    ventanaEC.limpiarCalificacionMensaje();
                    ventanaEC.setVisible(false);  
                } 
            }
            
            //Checa el estado del boton entrar
            if (botonEntrar==true){
                //Si se encuentra abierta la pantalla de calificacion
                //Manda la calificacion escrita
                if(ventanaEC.isVisible()){
                    ventanaEC.calificacionEnviada();
                }else{
                    //Checa que boton se esta seleccionando con el potenciometro
                    //Si esta en un boton de ventana la despliega 
                    switch (potenciometro){
                        case 0:
                            ventanaTemp.setVisible(true);
                            break;
                        case 1:
                            ventanaEC.setVisible(true);
                            break;
                        case 2:
                            System.exit(0);
                            this.dispose();
                            break;
                    }
                }
                
            }
            
            //Lee la parte alta y baja de la temperatura,
            //las suma y las interpreta (0-1023)
            if (ventanaTemp.isVisible()){
                tempLS=controller.interpretarTemperaturaLS();
                tempMS=controller.interpretarTemperaturaMS();
                temperatura=tempLS+tempMS;
                //Setea el numero (entre dos) en la pantalla de temperatura
                ventanaTemp.setTextoTemperatura(temperatura/2);
            }
            
            
            //Se detiene 50 milisegundos (nop chido)
            try {
                Thread.sleep(50);
            }catch(InterruptedException e){
                System.out.println("Waiting...");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcome_lbl = new javax.swing.JLabel();
        grade_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();
        temperature_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        welcome_lbl.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        welcome_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcome_lbl.setText("Bienvenido");
        welcome_lbl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        welcome_lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        grade_btn.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        grade_btn.setText("Enviar Califiación");
        grade_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_btnActionPerformed(evt);
            }
        });

        exit_btn.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        exit_btn.setText("Salir");
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        temperature_btn.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        temperature_btn.setText("Mostrar Temperatura");
        temperature_btn.setFocusPainted(false);
        temperature_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temperature_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(welcome_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(temperature_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(grade_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {exit_btn, grade_btn, temperature_btn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(welcome_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grade_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temperature_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {exit_btn, grade_btn, temperature_btn});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void temperature_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temperature_btnActionPerformed
        //Botón Mostrar temperatura:
        new VentanaTemperatura().setVisible(true);
    }//GEN-LAST:event_temperature_btnActionPerformed

    private void grade_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_btnActionPerformed
        //Botón Enviar calificación:
        new VentanaEnviarCalificacion().setVisible(true);
    }//GEN-LAST:event_grade_btnActionPerformed

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        //Botón Salir:
        this.dispose();
    }//GEN-LAST:event_exit_btnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Cuando la JFrame se abre activa el ciclo de revision infinita
        ciclo.start();
    }//GEN-LAST:event_formWindowOpened

    //NUESTRAS FUNCIONES
    public void setController(Controller controller){
        this.controller = controller;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit_btn;
    private javax.swing.JButton grade_btn;
    private javax.swing.JButton temperature_btn;
    private javax.swing.JLabel welcome_lbl;
    // End of variables declaration//GEN-END:variables
}
