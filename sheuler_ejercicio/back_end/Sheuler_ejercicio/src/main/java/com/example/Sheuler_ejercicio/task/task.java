package com.example.Sheuler_ejercicio.task;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Sheuler_ejercicio.models.usuario;
import com.example.Sheuler_ejercicio.service.emailService;
import com.example.Sheuler_ejercicio.service.usuarioService;

@Component
public class task {

@Autowired
    private usuarioService data;

    @Autowired
    private emailService email;

    @Scheduled(cron ="0 30 13 * * *")
    public void sendNotificationcron() {
        var listaUsuario=data.cambiarTipoDocumento();
        for (usuario usuario : listaUsuario) {
            System.out.println("Usuario que requiere actualizar documento: "+
            usuario.getnombre_completo());
            email.cambiarTipoDocumento(usuario.getCorreo_electronico(),usuario.getnombre_completo(),usuario.getTipo_documento(),usuario.getNumero_documento(),usuario.getCorreo_electronico(),usuario.getContrasena());
        }
    }
   
    @Scheduled(cron ="0 30 13 * * *") //Para realizar las pruebas rápidas: fixedRate =1000
    public void sendNotificationActualizarContra() {
        var listaUsuario=data.actualizarContraseña();
        for (usuario usuario : listaUsuario) {
            System.out.println("Usuario que requiere actualizar contraseña: "+
            usuario.getnombre_completo());
            email.actualizarContraseña(usuario.getCorreo_electronico());
        }
    }
   
    @Scheduled(cron ="0 45 15 * * *")
    public void sendIniciarSesion() {
        var listaUsuario=data.iniciosesionNotificar();
        for (usuario usuario : listaUsuario) {
            System.out.println("Usuario que debe iniciar sesión: "+
            usuario.getnombre_completo());
            email.iniciosesionNotificar(usuario.getCorreo_electronico());
        }
    }
   
   
}

