/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plan.proyecto.beans;

import org.springframework.stereotype.Component;

/**
 *
 * @author Administrador
 */
@Component
public class ComentarioDto {
    private String texto;    
    private String nombreUsuario;   
    private Long contenidoId;

    public ComentarioDto() {
    }

    public ComentarioDto(String texto, String nombreUsuario, Long contenidoId) {
        this.texto = texto;
        this.nombreUsuario = nombreUsuario;
        this.contenidoId = contenidoId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getContenidoId() {
        return contenidoId;
    }

    public void setContenidoId(Long contenidoId) {
        this.contenidoId = contenidoId;
    }

    @Override
    public String toString() {
        return "ComentarioDto{" + "texto=" + texto + ", nombreUsuario=" + nombreUsuario + ", contenidoId=" + contenidoId + '}';
    }
    
    
}
