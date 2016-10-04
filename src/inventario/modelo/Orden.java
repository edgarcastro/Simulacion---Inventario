/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;


/**
 *
 * @author edgarcastro
 */
public class Orden{
    private int idMinorista;
    private int cantidad;
    private int diasEspera;
    private boolean entregado;
    private boolean aceptado;

    public Orden(int id, int cantidad, int diasEspera) {
        this.idMinorista = id;
        this.cantidad = cantidad;
        this.diasEspera = diasEspera;
        this.entregado = Boolean.FALSE;
        this.aceptado = Boolean.FALSE;
    }
    
    public Orden(Orden orden) {
        this.idMinorista = orden.getIdMinorista();
        this.cantidad = orden.getCantidad();
        this.diasEspera = orden.getDiasEspera();
        this.aceptado = orden.isAceptado();
        this.entregado = orden.isEntregado();
    }
    
    public void pasarDia() {
        this.setDiasEspera(this.getDiasEspera() - 1);
    }

    public int getIdMinorista() {
        return idMinorista;
    }

    public void setIdMinorista(int idMinorista) {
        this.idMinorista = idMinorista;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDiasEspera() {
        return diasEspera;
    }

    public void setDiasEspera(int diasEspera) {
        this.diasEspera = diasEspera;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
        
}
