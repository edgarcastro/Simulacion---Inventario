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
    /**
     * Método para realizar la conexión con el servidor.
     * 
     * @param tipoMinorista Clave que indica si el minorista es Humano o Simulado.
     * @return Su identificación dentro de la lista de minorista conectados.
     * @throws RemoteException 
     */
    public int conectarse(String tipoMinorista) throws RemoteException;
    /**
     * Método para desconectarse del servidor.
     * 
     * @param id Clave del minorista que se va a desconectar.
     * @return valor verdadero si la desconexión fue exitosa, falso en caso contrario.
     * @throws RemoteException 
     */
    public boolean desconectarse(String id) throws RemoteException;
    /**
     * Método para obtener el día en el que se encuentra el servidor.
     * 
     * @return Día en el cuál se encuentra la simulación.
     * @throws RemoteException 
     */
    public int obtenerDia() throws RemoteException;
    /**
     * Método para obtener la cantidad de minoristas que están haciendo peticiones al mayorista.
     * 
     * @return Número de minoristas.
     * @throws RemoteException 
     */
    public int numeroMinoristas() throws RemoteException;
    /**
     * Método para solicitar un pedido al mayorista.
     * 
     * @param cantidad Cantidad de productos a pedir.
     * @param id Clave de identificación del minorista.
     * @return Objeto JSON con la Orden ó mensaje de que ya tiene un pedido pendiente.
     * @throws RemoteException 
     */
    public String hacerPedido(int cantidad, int id) throws RemoteException;
    /**
     * Método para confirmar el pedido anteriormente solicitado.
     * 
     * @param id Clave de identificación del minorista.
     * @throws RemoteException 
     */
    public void aceptarOrden(int id) throws RemoteException;
    /**
     * Método para verificar mi último pedido.
     * 
     * @param id Clave de identificación del minorista.
     * @return Objeto JSON con la Orden ó mensaje de que el pedido fue entregado.
     * @throws RemoteException 
     */
    public String verificarPedido(int id) throws RemoteException;
}
