/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicio;


import com.plan.proyecto.beans.Comentario;
import com.plan.proyecto.beans.ComentarioDto;

import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.ContenidoDto;
import com.plan.proyecto.beans.Usuario;
import com.plan.proyecto.daos.Dao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import static java.util.Collections.sort;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrador
 */
@Service
@Transactional
public class Servicio {

    @Autowired
    private Dao dao;

    @Transactional(readOnly = true)
    public Usuario compUsuario(Usuario usuario) {
        return dao.buscarUsuario(usuario);
        
    }
    public Boolean crearUsuario(Usuario usuario){       
        return dao.insertUsuario(usuario) != null;
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosUsuarios(Long id){
        List<Usuario> todosUsu=dao.todosUsuarios(id);        
        List<Usuario> soloAmigos=dao.amigosUnUsuario(id);  
        List<Usuario> noAmigos=new ArrayList<>();
        for (Usuario todo:todosUsu){
            if(!soloAmigos.contains(todo))
                noAmigos.add(todo);                  
            } 

        ordenarLista(noAmigos);
        
        return noAmigos;
    }

    private void ordenarLista(List<Usuario> lista) {
        Comparator<Usuario> comparar = new Comparator<Usuario>() {
            
            @Override
            public int compare(Usuario u1, Usuario u2) {
                return u1.getNombre().compareTo(u2.getNombre());
            }
        };
        
        Collections.sort(lista,comparar);
    }
    
    @Transactional(readOnly = true)
    public List<Contenido> obtenerContenidosUnUsuario(Long id){
        return dao.contenidosUnUsuario(id);
    } 
    
    public void crearContenido(ContenidoDto contenidoDto){        
        dao.insertContenido(contenidoDto);        
    }
    
    public Usuario usuarioId(Long id){
            return dao.obtenerUsuarioId(id);
    }
    
    public void borrarContenido(Contenido contenido){
        dao.borrarMensaje(contenido);
    }
    
    public List<Usuario> obtenerAmigosUnUsuario(Long id){
        List<Usuario> amigos=dao.amigosUnUsuario(id);
         ordenarLista(amigos);
        return(amigos);
        
    }
    public Contenido obtenerUnContenidoId(Long id){
        return dao.obtenerContenidoId(id);
    }
    
    public Usuario insertarAmigo(Long idAmigo,Long idUsuario){        
        return dao.insertAmigos(idAmigo, idUsuario);
    }
    
     public Usuario borrarAmigoUsuario(Long idUsuario,Long idAmigo){
        Usuario anfitrion=usuarioId(idUsuario);
        Usuario invitado=usuarioId(idAmigo);
        return dao.borrarAmigo(anfitrion, invitado);
     }
     
     public List<Comentario> obtenerComentariosUnContenido(Long idContenido){
         return dao.obtenerComentariosContenido(idContenido);
     }
     
     public void crearComentario(ComentarioDto comentarioDto){
         dao.insertComentario(comentarioDto);         
     }
}

