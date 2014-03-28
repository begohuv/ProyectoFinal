/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function enviarmensaje(idMensaje,idMostrar) {
    var nodoMensaje = document.getElementById(idMensaje);
    var mensaje = nodoMensaje.value;
    var nodoMostrar = document.getElementById(idMostrar);    
    
    var texto;
    
    if(mensaje.equals(""))
    {
        texto="No ha introducido ningun mensaje";
        
    }            
    else
    {
        texto=mensaje;
        
    }
    
    nodoMostrar.innerHTML =texto; 
    
    
}

