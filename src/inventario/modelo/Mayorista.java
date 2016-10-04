/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;

import com.google.gson.Gson;
import inventario.controlador.CMayorista;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import numerospseudoaleatorios.modelo.Generador;

/**
 *
 * @author edgarcastro
 */
public class Mayorista {
    private double inventario;
    private double p; //Punto de reorden
    private double q; //Cantidad a ordenar
    private int faltantes;
    private List<Orden> misOrdenes;
    private List<Orden> ordenes;
    
    private int diaActual;
    
    private List<String> minoristas;
    private Generador generador;
    
    private static boolean yaPedi = false;

    public Mayorista(double p, double q, Generador generador) {
        this.inventario = 50; //5000
        this.p = p;
        this.q = q;
        this.faltantes = 0;
        this.misOrdenes = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        
        this.diaActual = 1;
        
        this.minoristas = new ArrayList();
        this.generador = generador;
    }
    
    public void iniciar(int puerto) throws RemoteException, AlreadyBoundException{
        Remote stub;
        stub = UnicastRemoteObject.exportObject(new IMayorista() {
            @Override
            public int conectarse(String id) throws RemoteException {
                if(minoristas.add(id)){
                    System.out.println("DEBUG: Ha llegado cliente");
                    CMayorista.mostrarMinoristas();
                    return minoristas.size()-1;
                }else{
                    return -1;
                }
            }
            
            @Override
            public boolean desconectarse(String minorista) throws RemoteException {
                System.out.println("DEBUG: Ha salido cliente");
                CMayorista.mostrarMinoristas();
                return minoristas.remove(minorista);
            }
            
            @Override
            public int obtenerDia() throws RemoteException {
                return getDiaActual();
            }
            
            @Override
            public String hacerPedido(int cantidad, int id) throws RemoteException {
                Gson json = new Gson();
                for (Orden orden : ordenes) {
                    if(orden.getIdMinorista()==id && !orden.isEntregado() || orden.getDiasEspera()==0){
                        return "Ya tienes un pedido pendiente";
                    }
                }
                //System.out.println("DEBUG: Orden:"+json.toJson(ordenes.get(ordenes.size()-1)));
                if(inventario <= cantidad){
                    revisarInventario(cantidad);
                    ordenes.add(new Orden(id, cantidad, generarDiasEspera()+1+misOrdenes.get(misOrdenes.size()-1).getDiasEspera()));
                    //ordenes.add(new Orden(id, cantidad, 1+misOrdenes.get(misOrdenes.size()-1).getDiasEspera()));
                }else{
                    ordenes.add(new Orden(id, cantidad, generarDiasEspera()));
                    //ordenes.add(new Orden(id, cantidad, 0));
                }
                CMayorista.mostrarOrdenes();
                return json.toJson(ordenes.get(ordenes.size()-1));
            }
            
            @Override
            public void aceptarOrden(int id) throws RemoteException {
                for (Orden orden : ordenes) {
                    if(orden.getIdMinorista()==id){
                        orden.setAceptado(Boolean.TRUE);
                        CMayorista.mostrarOrdenes();
                        inventario -= orden.getCantidad();
                        if(inventario < 0){
                            faltantes += Math.abs(inventario);
                            inventario = 0;
                        }
                        CMayorista.mostrarStock();
                    }
                }
                
                //CMayorista.mostrarStock();
            }

            @Override
            public String verificarPedido(int id) throws RemoteException {
                Gson json = new Gson();
                for (Orden orden : ordenes) {
                    if(orden.getIdMinorista()==id && !orden.isEntregado()){
                        return json.toJson(orden);
                    }
                }
                return  json.toJson("estado: entregado");
            }

            @Override
            public int numeroMinoristas() throws RemoteException {
                return getOrdenes().size();
            }
        },0);
        Registry registry = LocateRegistry.createRegistry(puerto);
        registry.bind("Mayorista", stub);
        try {
            System.out.println("DEBUG: Mayorista corriendo en:"+InetAddress.getLocalHost().getHostAddress()+":"+puerto);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Mayorista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int generarDiasEspera() {
        int diasEspera;
        double numero = this.generador.siguienteVar();
        if(numero < 0.4){
            diasEspera = 0;
        }else if(numero < 0.6){
            diasEspera = 1;
        }else if(numero < 0.75){
            diasEspera = 2;
        }else if(numero < 0.9){
            diasEspera = 3;
        }else{
            diasEspera = 4;
        }
        return diasEspera;
    }
    
    public void disminuirDia(List<Orden> ordenes){
        ordenes.stream().forEach((orden) -> {
            orden.setDiasEspera(orden.getDiasEspera()-1);
        });
    }
    
    public void atenderOrdenes(){
        if(!this.ordenes.isEmpty()){
            for (Orden orden : this.ordenes) {
                if(orden.getDiasEspera() == 0 && orden.isAceptado()) {
                    orden.setEntregado(true);
                    /*if(orden.getCantidad() <= this.inventario) {
                        //this.inventario -= orden.getCantidad();
                        orden.setAtendido(true);
                    } else {
                        revisarInventario(orden.getCantidad());
                        orden.setDiasEspera(generarDiasEspera()+1+misOrdenes.get(misOrdenes.size()-1).getDiasEspera());
                    }*/
                }
            }
        }
        disminuirDia(this.ordenes);
        CMayorista.mostrarOrdenes();
    }
    
    public void revisarInventario(int valor){
        if(this.inventario <= valor && !yaPedi){
            this.misOrdenes.add(new Orden(-1, (int) ((int)this.q-this.inventario), generarDiasEspera()+1));
            yaPedi = true;
        }
        if(!this.misOrdenes.isEmpty()) {
            if(!this.misOrdenes.get(this.misOrdenes.size()-1).isEntregado()){
                if(this.misOrdenes.get(this.misOrdenes.size()-1).getDiasEspera() == -1){
                    this.inventario += this.misOrdenes.get(this.misOrdenes.size()-1).getCantidad();
                    this.inventario -= faltantes;
                    faltantes = 0;
                    this.misOrdenes.get(this.misOrdenes.size()-1).setEntregado(true);
                    yaPedi = false;
                    //System.out.println("Recibió pedido");
                }
            }
            CMayorista.mostrarMiPedido();
            //System.out.println("Dias espera: "+this.misOrdenes.get(this.misOrdenes.size()-1).getDiasEspera());
        }
        
        disminuirDia(this.misOrdenes);
    }
    
    public void pasarDia(){
        revisarInventario((int)this.p);  //Mis Ordenes
        atenderOrdenes();   //Ordenes de Minoristas
        this.diaActual++;
    }

    public int getDiaActual() {
        
        return diaActual;
    }

    public double getInventario() {
        return inventario;
    }

    public int getFaltantes() {
        return faltantes;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public List<Orden> getMisOrdenes() {
        return misOrdenes;
    }

    public List<String> getMinoristas() {
        return minoristas;
    }
}
