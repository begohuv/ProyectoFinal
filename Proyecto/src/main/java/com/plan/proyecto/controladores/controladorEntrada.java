/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.controladores;

import com.plan.proyecto.beans.Comentario;
import com.plan.proyecto.beans.ComentarioDto;
import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.ContenidoDto;
import com.plan.proyecto.beans.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.plan.proyecto.servicio.Servicio;
import java.util.List;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrador
 */
@Controller
@RequestMapping("/js")
//@SessionAttributes("usuario")
public class controladorEntrada {

    @Autowired
    private Servicio su;

    @ModelAttribute("usuario")
    public Usuario getUsuario() {
        return new Usuario("","","apellido");
    }

     @ModelAttribute("usuario2")
    public Usuario getUsuario2() {
        return new Usuario();
    }
    @ModelAttribute("contenidoDto")
    public ContenidoDto getContenidoDto() {
        return new ContenidoDto();
    }
    @ModelAttribute("comentarioDto")
    public ComentarioDto getComentarioDto() {
        return new ComentarioDto();
    }

    @ModelAttribute("contenido")
    public Contenido unContenido() {
        return new Contenido();
    }

    @RequestMapping(value = "principal.curso",method = RequestMethod.GET)
    public void peticionGet() {

    }
    
     @RequestMapping(value = "otroMuro.curso",method = RequestMethod.GET)
    public void peticionGetOtroMuro() {

    }
    
     @RequestMapping(value = "muro.curso",method = RequestMethod.GET)
    public void peticionGetMuro() {

    }
    @RequestMapping(value = "cerrar.curso",method = RequestMethod.GET)
    public void peticionGetCerrar() {

    }
    
     @RequestMapping(value = "login.curso", method = RequestMethod.POST)
    public String procesarFormulario(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
        Usuario u = su.compUsuario(usuario);        
        if (u == null) {       
//            errores.rejectValue("nombre", "nombre.corto", "El usuario no existe .Registrese");
            usuario.setId(0L);
            usuario.setNombre("El usuario no existe .Registrese");
            model.addAttribute("usuario", usuario);
            model.addAttribute("info", "Usuario no autenticado");
            List<Usuario> usuarios=su.obtenerTodosUsuarios(usuario.getId());
            model.addAttribute("vacioUsuarios", usuarios.isEmpty());
            model.addAttribute("usuarios", usuarios);  
            return "js/principal";

        } else {
            cargardatos(model, u);
            return "js/muro";

        }

    }

    private void cargardatos(Model model, Usuario u) {
        model.addAttribute("usuario", u);
        List<Contenido> contenidos = su.obtenerContenidosUnUsuario(u.getId());
        model.addAttribute("vacio", contenidos.isEmpty());
        model.addAttribute("contenidos", contenidos);
        List<Usuario> usuarios=su.obtenerTodosUsuarios(u.getId());
        model.addAttribute("vacioUsuarios", usuarios.isEmpty());
        model.addAttribute("usuarios", usuarios);
        List<Usuario>amigos = su.obtenerAmigosUnUsuario(u.getId());
        model.addAttribute("vacioAmigo", amigos.isEmpty());
        model.addAttribute("amigos", amigos);
    }

    @RequestMapping(value = "crearcuenta.curso", method = RequestMethod.POST)
    public String procesarFormularioAlta(@Valid @ModelAttribute("usuario2") Usuario usuario2, BindingResult errores, Model model) {       
        Usuario u = su.compUsuario(usuario2);       
        if (u == null) {
           // errores.rejectValue("nombre", "nombre.corto", "Esto es la CHAPU");
            Boolean valor = su.crearUsuario(usuario2);
            model.addAttribute("usuario", usuario2);
            model.addAttribute("info", valor ? "Cuenta creada" : "Cuenta no creada");
            List<Contenido> contenidos = su.obtenerContenidosUnUsuario(usuario2.getId());
            model.addAttribute("vacio", contenidos.isEmpty());
            model.addAttribute("contenidos", contenidos);
            List<Usuario> usuarios=su.obtenerTodosUsuarios(u.getId());
            model.addAttribute("vacioUsuarios", usuarios.isEmpty());
            model.addAttribute("usuarios", usuarios);                       
            model.addAttribute("vacioAmigo", true);     
             

        } 
        else{
            model.addAttribute("vacioAmigo", true);
            cargardatos(model, u);
            
        }        
        return "js/muro";
    }

    @RequestMapping(value = "muro.curso", method = RequestMethod.POST)
    public String procesarMuro(@Valid @ModelAttribute("contenidoDto") ContenidoDto contenidoDto, @RequestParam("ident") Long id, BindingResult errores, Model model) {
        contenidoDto.setUsuarioId(id);       
        su.crearContenido(contenidoDto);        
        Usuario u = su.usuarioId(id);        
        cargardatos(model, u);
        contenidoDto.setTexto("");
        
        return "js/muro";
    }

    @RequestMapping(value = "borrarContenido.curso", method = RequestMethod.GET)
    public String procesarMuroBorrar(@RequestParam("id") Long id,@RequestParam("idUsuario") Long idUsu, Model model) {
        Contenido contenido=su.obtenerUnContenidoId(id);        
        su.borrarContenido(contenido);
        Usuario u = su.usuarioId(idUsu);
        cargardatos(model, u);
        
        return "js/muro";
    }
    
    @RequestMapping(value = "borrarContenidoComentario.curso", method = RequestMethod.GET)
    public String procesarotroMuroBorrar(@RequestParam("id") Long id,@RequestParam("idUsuario") Long idUsu, Model model) {
        Contenido contenido=su.obtenerUnContenidoId(id);        
        su.borrarContenido(contenido);
        Usuario u = su.usuarioId(idUsu);
        cargardatos(model, u);
        
        return "js/otroMuro";
    }
    
    @RequestMapping(value = "altaAmigo.curso", method = RequestMethod.GET)    
    public String procesarAltaAmigo(@RequestParam("idUsuario") Long idUsuario, @RequestParam("idAmigo") Long idAmigo, Model model) {                       
       Usuario u=su.insertarAmigo(idAmigo, idUsuario);  
         cargardatos(model, u); 
        
         return "js/muro";
        }
    
    // *** PARA AJAX ***
    
//    @RequestMapping(value = "altaAmigo.curso", method = RequestMethod.GET)
//    @ResponseBody
//    public String procesarAltaAmigo(@RequestParam("idUsuario") Long idUsuario, @RequestParam("idAmigo") Long idAmigo, Model model) {                       
//       Usuario u=su.insertarAmigo(idAmigo, idUsuario);          
//       String html=null;       
//        List<Usuario>amigos = su.obtenerAmigosUnUsuario(idUsuario); 
//        for(Usuario amigo:amigos){
//            html="<ul>"+
//                 "<li>"+
//                 "<a href='muroAmigo.curso?idUsuario=${"+idUsuario+"}&idAmigo=+${"+idAmigo+"}'><h4>"+
//                 amigo.getNombre()+" "+amigo.getApellido()+"</h4></a>"+
//                 "</li>"+
//                 "</ul>";   
//        }
//        return html;       
//    }

@RequestMapping(value = "borrarAmigo.curso", method = RequestMethod.GET)
    public String procesarMuroBorrarAmigo(@RequestParam("idUsuario") Long idUsuario,@RequestParam("idAmigo") Long idAmigo, Model model) {
        
        su.borrarAmigoUsuario(idUsuario, idAmigo);        
         Usuario u = su.usuarioId(idUsuario);
        cargardatos(model, u);
        
        return "js/muro";
    }
    
     @RequestMapping(value = "muroAmigo.curso", method = RequestMethod.GET)
    public String procesarMuroAmigo(@RequestParam("idUsuario") Long idAnfitrion,@RequestParam("idAmigo") Long idUsuario, Model model) {
        Usuario anfitrion=su.usuarioId(idAnfitrion); 
        model.addAttribute("anfitrion", anfitrion);
        Usuario u=su.usuarioId(idUsuario); 
        cargardatos(model, u);    
        
            return "js/otroMuro";
    }   
    
    @RequestMapping(value = "otroMuro.curso", method = RequestMethod.POST)
    public String procesarOtroMuro(@Valid @ModelAttribute("contenidoDto") ContenidoDto contenidoDto, @RequestParam("idAnfitrion") Long idAnfitrion,@RequestParam("ident") Long id, BindingResult errores, Model model) {
        Usuario anfitrion=su.usuarioId(idAnfitrion); 
        model.addAttribute("anfitrion", anfitrion);
        contenidoDto.setUsuarioId(id);       
        su.crearContenido(contenidoDto);        
        Usuario u = su.usuarioId(id);                
        cargardatos(model, u);
        
        return "js/otroMuro";
    }
    
    @RequestMapping(value = "volverMuro.curso", method = RequestMethod.GET)
    public String procesarvolverMuro(@RequestParam("idAnfitrion") Long idUsuario, Model model) {        
        Usuario u=su.usuarioId(idUsuario); 
        cargardatos(model, u);
        
            return "js/muro";
    }   
    
    @RequestMapping(value = "mostrarComentario.curso", method = RequestMethod.GET)
    public String procesarmostrarComentarios(@RequestParam("id") Long idContenido,@RequestParam("idUsuario") Long idUsuario,@RequestParam("idUsuarioAmigo") Long idUsuarioAmigo, Model model) {
        
        Contenido contenido =su.obtenerUnContenidoId(idContenido);
        List<Comentario>comentarios =su.obtenerComentariosUnContenido(idContenido);
        model.addAttribute("contenido", contenido);
        model.addAttribute("vacioComentario", comentarios.isEmpty());
        model.addAttribute("comentarios", comentarios);    
        Usuario usuario=su.usuarioId(idUsuario);
        model.addAttribute("usuario", usuario);
        Usuario usuarioAmigo=su.usuarioId(idUsuarioAmigo);
        model.addAttribute("usuarioAmigo", usuarioAmigo);
        
            return "js/comentario";
    }   
    
    @RequestMapping(value = "comentario.curso", method = RequestMethod.POST)
    public String procesarComentarios(@Valid @ModelAttribute("comentarioDto") ComentarioDto comentarioDto,@RequestParam("idUsuario") Long idUsuario,@RequestParam("idUsuarioAmigo") Long idUsuarioAmigo,@RequestParam("idContenido") Long idContenido,BindingResult errores, Model model) {
        comentarioDto.setContenidoId(idContenido);
        Usuario usuario=su.usuarioId(idUsuario);
        comentarioDto.setNombreUsuario(usuario.getNombre());
        su.crearComentario(comentarioDto);                
        List<Comentario> comentarios = su.obtenerComentariosUnContenido(idContenido);
        Contenido contenido =su.obtenerUnContenidoId(idContenido);
        Usuario usuarioAmigo=su.usuarioId(idUsuarioAmigo);
        model.addAttribute("usuario", usuario);          
        model.addAttribute("usuarioAmigo", usuarioAmigo);
        model.addAttribute("contenido", contenido);
        model.addAttribute("vacioComentario", comentarios.isEmpty());
        model.addAttribute("comentarios", comentarios);    
        comentarioDto.setTexto(" ");
        return "js/comentario";
    }
}
