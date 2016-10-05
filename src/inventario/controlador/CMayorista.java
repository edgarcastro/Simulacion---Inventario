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
    /**
     * Método que recibe el generador a utilizar y se inicia la ventana del mayorista.
     * 
     * @param generador Generador de numeros aleatorios que se va a utilizar.
     */
    public static void iniciar(Generador generador)  {
        CMayorista.generador = generador;
        ventana = new VMayorista();
        ventana.setVisible(true);
    }
    /**
     * Método que instancia un mayorista e inicia el servidor.
     * 
     * @param p Punto reorden.
     * @param q Cantidad a ordenar.
     * @param puerto Puerto donde se ejecutará el servidor.
     */
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
    /**
     * Método para actualizar en la ventana el número de minoristas conectados.
     * 
     * @param minoristas Lista de minoristas.
     */
    public static void actualizarClientes(List minoristas){
        ventana.actualizarCliente(minoristas);
    }
    /**
     * Método para pasar de día.
     */
    public static void siguienteDia(){
        mayorista.pasarDia();
        mostrarDia();
        mostrarStock();
    }
    /**
     * Método para actualizar en la ventana el día actual.
     */
    public static void mostrarDia(){
        ventana.mostrarDia(mayorista.getDiaActual());
    }
    /**
     * Método para actualizar en la ventana el inventario y faltantes.
     */
    public static void mostrarStock(){
        ventana.mostrarStock(mayorista.getInventario(), mayorista.getFaltantes());
    }
    /**
     * Método para mostrar las ordenes que se han solicitado.
     */
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
    /**
     * Método para mostrar pedido del mayorista en la ventana.
     */
    public static void mostrarMiPedido(){
        Orden referencia = mayorista.getMisOrdenes().get(mayorista.getMisOrdenes().size()-1);
        if(referencia.isEntregado())
            ventana.mostrarMiPedido(0, 0);
        else 
            ventana.mostrarMiPedido(referencia.getCantidad(), referencia.getDiasEspera());
    }
    /**
     * Método para mostrar los minoristas en la ventana.
     */
    public static void mostrarMinoristas(){
        ventana.mostrarMinoristas(mayorista.getMinoristas());
        actualizarClientes(mayorista.getMinoristas());
    }
    /**
     * Método para programar paso de día cada cierto tiempo.
     */
    public static void diaAuto(){
        // Clase en la que está el código a ejecutar 
        TimerTask timerTask = new TimerTask(){ 
            @Override
            public void run(){ 
             siguienteDia();
            } 
        };
      // Aquí se pone en marcha el timer cada segundo. 
     Timer timer = new Timer(); 
     // Dentro de 0 milisegundos avísame cada 10000 milisegundos 
     timer.scheduleAtFixedRate(timerTask, 0, 10000);
    }
}
