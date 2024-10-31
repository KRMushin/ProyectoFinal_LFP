/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Entity;

/**
 *
 * @author kevin-mushin
 */
public class ModificacionTabla {
    
    private String tablaModificada;
    private String tipoModificacion;
    private String consulta;

    public ModificacionTabla() {
    }

    public ModificacionTabla(String tablaModificada, String tipoModificacion) {
        this.tablaModificada = tablaModificada;
        this.tipoModificacion = tipoModificacion;
    }
    
    public String getTablaModificada() {
        return tablaModificada;
    }

    public void setTablaModificada(String tablaModificada) {
        this.tablaModificada = tablaModificada;
    }

    public String getTipoModificacion() {
        return tipoModificacion;
    }

    public void setTipoModificacion(String tipoModificacion) {
        this.tipoModificacion = tipoModificacion;
    }

    

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
    
    
    
    
    
}
