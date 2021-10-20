/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controlador.UsuarioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import modelo.Usuario;
//import modelo.PasajeroPK;

/**
 *
 * @author user
 */
public class paginaPrincipalPanel extends javax.swing.JPanel {

   
    /**
     * Creates new form paginaPrincipalPanel
     */
    private Usuario usuario; // del modelo 
    private UsuarioJpaController usuarioJPAControler; 
    private EntityManagerFactory emf;
    //private EntitymanagerFactory emf;
    
    //private PasajeroPK pasajero_pk ;
    
    
    
    public paginaPrincipalPanel() {
        initComponents();   
        // instancia rmis objetos
        usuario = new Usuario();
        //pasajero_pk = new PasajeroPK();
        
        emf = Persistence.createEntityManagerFactory("JPASistemaAutobusPU");
        usuarioJPAControler = new UsuarioJpaController(emf);
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        contrasenaField = new javax.swing.JPasswordField();
        entrarBoton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        usuario_registro = new javax.swing.JTextField();
        contrasena_registro = new javax.swing.JPasswordField();
        contrasenaVerificacion_registro = new javax.swing.JPasswordField();
        registrarme_button = new javax.swing.JButton();

        setForeground(new java.awt.Color(0, 0, 102));

        jLabel1.setBackground(new java.awt.Color(0, 0, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema Autobuses Trans Parentes");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1.jpg"))); // NOI18N

        jLabel3.setText("Login:");

        usuarioField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuarioField.setText("Usuario");
        usuarioField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioFieldActionPerformed(evt);
            }
        });

        contrasenaField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contrasenaField.setText("Contraseña");

        entrarBoton.setText("Entrar");
        entrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarBotonActionPerformed(evt);
            }
        });

        jLabel4.setText("Registrar:");

        usuario_registro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuario_registro.setText("Nombre");
        usuario_registro.setToolTipText("");
        usuario_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuario_registroActionPerformed(evt);
            }
        });

        contrasena_registro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contrasena_registro.setText("primer contraseña");
        contrasena_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasena_registroActionPerformed(evt);
            }
        });

        contrasenaVerificacion_registro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contrasenaVerificacion_registro.setText("verifique contraseña");

        registrarme_button.setText("Registrarme");
        registrarme_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarme_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(contrasenaField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                        .addComponent(entrarBoton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuario_registro, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(contrasena_registro)
                            .addComponent(contrasenaVerificacion_registro)
                            .addComponent(registrarme_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(contrasenaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entrarBoton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuario_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(contrasena_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(contrasenaVerificacion_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(registrarme_button))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioFieldActionPerformed

    private void entrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrarBotonActionPerformed
        // si presiona el boton de entrar
        String usuarioBD = "usuario" , contrasenaBD = "123"; // antes de acceder a un bd esto va ser default
        String usuarioADMN = "admin" , contrasenaADMN = "admin123";
        String Key =  usuario.getContrasenauser();     
        int numeroIntentosLogin = 0 , cantidadIntentos = 5; // contador oportunidades , si no soporte tecnico de una 
        //evt.getActionCommand();
       
        numeroIntentosLogin++;
        
        String user = usuarioField.getText();
        String clave =   String.copyValueOf(contrasenaField.getPassword());
        
        // encontrar si el usuario esta almacenaod en la base de datos
        usuario = null;
        try{
            usuario = usuarioJPAControler.findUsuario(user);
                                   
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ocurrio excepcion" + e);
        }
        
        // si es posible acceder
        if( (user.equals(usuarioBD) && clave.equals(contrasenaBD))  || ((usuario != null) && Key.equals(clave) )){
            // entro a la pagina principal del usuario 
            paginaUsuarioFrame forma = new paginaUsuarioFrame();
            JOptionPane.showMessageDialog(null, "Bienvenido");
            forma.setVisible(true);
            // paginaPrincipalPanel.dispose();
                
        }        
        if(numeroIntentosLogin == cantidadIntentos)JOptionPane.showMessageDialog(null, "Estamos Contactando a servicio tecnico");
        /*
        do{
            JOptionPane.showMessageDialog(null, "Intente de nuevo");
        }while( (clave  != contrasenaBD) && (usuario  != usuarioBD) &&  (numeroIntentosLogin  <= cantidadIntentos)   );
        */
        
        // entrar como administrador
        if( user.equals(usuarioADMN)  && clave.equals(contrasenaADMN) ){
            AdminMainFrame forma = new AdminMainFrame();
            JOptionPane.showMessageDialog(null, "Bienvenido JEFAZO");
            forma.setVisible(true);
            
        }
        
        
        
        
        
    }//GEN-LAST:event_entrarBotonActionPerformed

    private void usuario_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuario_registroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_registroActionPerformed

    private void contrasena_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasena_registroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contrasena_registroActionPerformed

    private void registrarme_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarme_buttonActionPerformed
        // cuando el usuario crea un registro
         String contrasena =   String.copyValueOf(contrasena_registro.getPassword()); // tomo de la vista
         String contrasenaVerificacion =   String.copyValueOf(contrasenaVerificacion_registro.getPassword());
         String usuarioIngresado = usuario_registro.getText();
         
         // estoy anadiendo un usuario a la bd
         /*
        usuario.setNombreuser(usuarioIngresado);
        usuario.setContrasenauser(contrasena);
        */   
           try{
             usuarioJPAControler.create(usuario);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ocurrio excepcion" + e);
        }
         
         if(contrasena.equals(contrasenaVerificacion )){
         
            usuario.setNombreuser(usuarioIngresado);
            usuario.setContrasenauser(contrasena);
            
         
         }else{JOptionPane.showMessageDialog(null, "Contraseña debe coincidir");}
        
         
         
         
        
    }//GEN-LAST:event_registrarme_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField contrasenaField;
    private javax.swing.JPasswordField contrasenaVerificacion_registro;
    private javax.swing.JPasswordField contrasena_registro;
    private javax.swing.JButton entrarBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton registrarme_button;
    private javax.swing.JTextField usuarioField;
    private javax.swing.JTextField usuario_registro;
    // End of variables declaration//GEN-END:variables
}
