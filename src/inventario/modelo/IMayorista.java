/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author edgarcastro
 */
public interface IMayorista extends Remote{
    public int conectarse(String var) throws RemoteException;
    public boolean desconectarse(String var) throws RemoteException;
    
    public int obtenerDia() throws RemoteException;
    public int numeroMinoristas() throws RemoteException;
    public String hacerPedido(int cantidad, int id) throws RemoteException;
    public void aceptarOrden(int id) throws RemoteException;
    public String verificarPedido(int id) throws RemoteException;
}
