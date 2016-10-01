/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import inventario.controlador.CMayorista;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import numerospseudoaleatorios.modelo.CongruencialCuadratico;

/**
 *
 * @author edgarcastro
 */
public class MainMayorista {
    
    public static void main(String[] args) {
        try{
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        CMayorista.iniciar(new CongruencialCuadratico(13, 26, 27, 27, 8));
    }
    
}
