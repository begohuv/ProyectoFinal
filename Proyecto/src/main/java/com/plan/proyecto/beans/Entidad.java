/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plan.proyecto.beans;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Administrador
 */
@MappedSuperclass
public class Entidad implements Serializable{
    @Id
    @GeneratedValue
     Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
