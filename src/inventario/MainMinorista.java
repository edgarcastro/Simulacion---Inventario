/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import inventario.modelo.Minorista;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgarcastro
 */
public class MainMinorista {
    public static void main(String[] args) {
        try {
            Minorista m = new Minorista(0, 0, "192.168.1.5", 5555);
            m.hacerPedido(50);
            m.confirmarPedido();
            
        } catch (RemoteException ex) {
            Logger.getLogger(MainMinorista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(MainMinorista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
