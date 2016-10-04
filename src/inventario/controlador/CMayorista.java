/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.controlador;

import inventario.modelo.Mayorista;
import inventario.modelo.Orden;
import inventario.vista.VMayorista;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import numerospseudoaleatorios.modelo.Generador;

/**
 *
 * @author edgarcastro
 */
public class CMayorista {
    private static Mayorista mayorista;
    private static VMayorista ventana; 
    private static Generador generador;
    
    public static void iniciar(Generador generador)  {
        CMayorista.generador = generador;
        ventana = new VMayorista();
        ventana.setVisible(true);
    }
    
    public static void iniciarServidor(int p, int q,  int puerto) {
        mayorista = new Mayorista(p, q, generador);
        try {
            mayorista.iniciar(puerto);
        } catch (RemoteException ex) {
            Logger.getLogger(CMayorista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(CMayorista.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarDia();
        mostrarStock();
    }
    
    public static void actualizarClientes(List clientes){
        ventana.actualizarCliente(clientes);
    }
    
    public static void siguienteDia(){
        mayorista.pasarDia();
        mostrarDia();
        mostrarStock();
    }
    
    public static void mostrarDia(){
        ventana.mostrarDia(mayorista.getDiaActual());
    }
    
    public static void mostrarStock(){
        ventana.mostrarStock((int) mayorista.getInventario(), mayorista.getFaltantes());
    }
    
    public static void mostrarOrdenes(){
        Object[][] datos = new Object[mayorista.getOrdenes().size()][6];
        int i = 0;
        for (Orden orden : mayorista.getOrdenes()) {
            datos[i][0] = orden.getIdMinorista();
            datos[i][1] = orden.getCantidad();
            datos[i][2] = orden.getDiasEspera();
            datos[i][3] = orden.isAceptado();
            datos[i][4] = orden.isEntregado();
            i++;
        }
        ventana.mostrarOrdenes(datos, mayorista.getOrdenes().size());
    }
    
    public static void mostrarMiPedido(){
        Orden referencia = mayorista.getMisOrdenes().get(mayorista.getMisOrdenes().size()-1);
        if(referencia.isEntregado())
            ventana.mostrarMiPedido(0, 0);
        else 
            ventana.mostrarMiPedido(referencia.getCantidad(), referencia.getDiasEspera());
    }
    
    public static void mostrarMinoristas(){
        ventana.mostrarMinoristas(mayorista.getMinoristas());
        actualizarClientes(mayorista.getMinoristas());
    }
    
    public static void diaAuto(){
        // Clase en la que está el código a ejecutar 
        TimerTask timerTask = new TimerTask() 
        { 
            public void run()  
            { 
             siguienteDia();
            } 
        }; 
     
      // Aquí se pone en marcha el timer cada segundo. 
     Timer timer = new Timer(); 
     // Dentro de 0 milisegundos avísame cada 10000 milisegundos 
     timer.scheduleAtFixedRate(timerTask, 0, 10000);
    }
}
