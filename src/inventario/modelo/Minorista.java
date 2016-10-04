/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author edgarcastro
 */
public class Minorista {
    private int inventario;
    private double p; //Punto de reorden
    private double q; //Cantidad a ordenar
    private double monto;
    private int id;
    
    private static final double PRECIOVENTA = 7.0;
    private static final double PRECIOCOMPRA = 5.0;
    
    private String ipServidor;
    private int puertoServidor;
    private IMayorista mayorista;

    public Minorista(double p, double q, String ipServidor, int puertoServidor) throws RemoteException, NotBoundException {
        this.p = p;
        this.q = q;
        this.monto = 500.0;
        
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
        Registry registro = LocateRegistry.getRegistry(this.ipServidor, this.puertoServidor);
        this.mayorista = (IMayorista) registro.lookup("Mayorista");
        this.id = this.mayorista.conectarse("H");
        if (this.id != -1)
            System.out.println("DEBUG: Conectado"); 
    }
    
    public void hacerPedido(int cantidad) throws RemoteException{
        this.mayorista.hacerPedido(cantidad, id);
    }
    
    public void confirmarPedido() throws RemoteException{
        this.mayorista.aceptarOrden(id);
    }
    
}
