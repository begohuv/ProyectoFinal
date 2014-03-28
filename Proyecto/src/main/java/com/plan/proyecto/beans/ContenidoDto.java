/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plan.proyecto.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrador
 */
@Component
public class ContenidoDto {
    @NotNull
    @Size(min = 3, max = 100)
    private String texto;    
    private Long usuarioId;    
    private Long contenidoId;

    public ContenidoDto() {
    }

    public ContenidoDto(String texto, Long usuarioId, Long contenidoId) {
        this.texto = texto;
        this.usuarioId = usuarioId;
        this.contenidoId = contenidoId;
    }
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getContenidoId() {
        return contenidoId;
    }

    public void setContenidoId(Long contenidoId) {
        this.contenidoId = contenidoId;
    }

    @Override
    public String toString() {
        return "ContenidoDto{" + "texto=" + texto + ", usuarioId=" + usuarioId + ", contenidoId=" + contenidoId + '}';
    }
    
       
    
}
