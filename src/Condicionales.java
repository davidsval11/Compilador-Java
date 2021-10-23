/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oscar
 */
public class Condicionales {
    private int linea_cond;
    private int linea_inicio;
    private int linea_final;
    private boolean ejecutar;
    private String lineas;
    private String tipo;

    public  Condicionales(int linea_cond, int linea_inicio, int linea_final, String lineas, String tipo, boolean ejecutar){
        this.linea_cond = linea_cond;
        this.linea_inicio = linea_inicio;
        this.linea_final = linea_final;
        this.lineas = lineas;
        this.tipo = tipo;
        this.ejecutar = ejecutar;
    }
    
    /**
     * @return the linea_cond
     */
    public int getLinea_cond() {
        return linea_cond;
    }

    /**
     * @param linea_cond the linea_cond to set
     */
    public void setLinea_cond(int linea_cond) {
        this.linea_cond = linea_cond;
    }

    /**
     * @return the linea_inicio
     */
    public int getLinea_inicio() {
        return linea_inicio;
    }

    /**
     * @param linea_inicio the linea_inicio to set
     */
    public void setLinea_inicio(int linea_inicio) {
        this.linea_inicio = linea_inicio;
    }

    /**
     * @return the linea_final
     */
    public int getLinea_final() {
        return linea_final;
    }

    /**
     * @param linea_final the linea_final to set
     */
    public void setLinea_final(int linea_final) {
        this.linea_final = linea_final;
    }

    /**
     * @return the lineas
     */
    public String getLineas() {
        return lineas;
    }

    /**
     * @param lineas the lineas to set
     */
    public void setLineas(String lineas) {
        this.lineas = lineas;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the ejecutar
     */
    public boolean getEjecutar() {
        return ejecutar;
    }

    /**
     * @param ejecutar the ejecutar to set
     */
    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }
}
