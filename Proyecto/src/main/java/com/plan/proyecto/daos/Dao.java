/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.daos;

import com.plan.proyecto.beans.Comentario;
import com.plan.proyecto.beans.ComentarioDto;
import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.ContenidoDto;
import com.plan.proyecto.beans.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class Dao {

    @PersistenceContext
    private EntityManager em;

    public Usuario buscarUsuario(Usuario usuario) {
        Query q = em.createQuery(" select u from Usuario u where u.nombre=:nombre and u.password=:password ");
        q.setParameter("nombre", usuario.getNombre());
        q.setParameter("password", usuario.getPassword());
        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuario> todosUsuarios(Long id) {
        Query q = em.createQuery(" select u from Usuario u where u.id<>:clave ");
        q.setParameter("clave", id);
        return q.getResultList();
    }

    public Usuario insertUsuario(Usuario usuario) {
        em.persist(usuario);
        return usuario.getId() == null ? null : usuario;

    }

    public List<Contenido> contenidosUnUsuario(Long id) {
        Query q = em.createQuery(" select c from Contenido c where c.usuario.id=:clave ");
        q.setParameter("clave", id);
        return q.getResultList();

    }

    public Usuario obtenerUsuarioId(Long id) {
        Query q = em.createQuery(" select u from Usuario u where u.id=:id ");
        q.setParameter("id", id);
        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public Usuario invitarA(Usuario anfitrion, Usuario invitado) {
        List<Usuario> invitados = anfitrion.getAmigos();

        if (invitados.contains(invitado)) {
            return anfitrion;
        } else {
            //anfitrion.getAmigos().add(invitado);
            invitado.getAmigos().add(anfitrion);
            invitados.add(invitado);            
            return em.merge(anfitrion);
        }
    }

    public Usuario insertAmigos(Long idAmigo, Long idUsuario) {
        Usuario usuario = em.find(Usuario.class, idUsuario);
        Usuario amigo = em.find(Usuario.class, idAmigo);
        return invitarA(usuario, amigo);

    }

    public void insertContenido(ContenidoDto contenidoDto) {
        Usuario usuario;
        Contenido c = new Contenido();
        usuario = obtenerUsuarioId(contenidoDto.getUsuarioId());
        c.setUsuario(usuario);
        c.setTexto(contenidoDto.getTexto());
        usuario.getContenidos().add(c);
    }

    public void borrarMensaje(Contenido contenido) {
        Contenido otra = em.find(Contenido.class, contenido.getId());
        em.remove(otra);
    }

    public List<Usuario> amigosUnUsuario(Long id) {
        Query q = em.createQuery(" select u.amigos from Usuario u where u.id =:clave ");
        q.setParameter("clave", id);
        return q.getResultList();

    }

    public Contenido obtenerContenidoId(Long id) {
        Query q = em.createQuery(" select c from Contenido c where c.id=:id ");
        q.setParameter("id", id);
        try {
            return (Contenido) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public Usuario borrarAmigo(Usuario anfitrion, Usuario invitado) {
        anfitrion.getAmigos().remove(invitado);
        invitado.getAmigos().remove(anfitrion);
        return em.merge(anfitrion);
    }

    public List<Comentario> obtenerComentariosContenido(Long idContenido) {
        Query q = em.createQuery(" select co from Comentario co where co.contenido.id=:id ");
        q.setParameter("id", idContenido);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public void insertComentario(ComentarioDto comentarioDto) {
        Contenido contenido;
        Comentario cm = new Comentario();
        contenido = obtenerContenidoId(comentarioDto.getContenidoId());
        cm.setContenido(contenido);
        cm.setTexto(comentarioDto.getTexto());
        cm.setNombreUsuario(comentarioDto.getNombreUsuario());
        cm.setFecha(new Date());
        contenido.getComentarios().add(cm);        
    }
}
