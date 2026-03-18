package org.chatboot.services.impl;

import org.chatboot.services.MessageStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UsuarioStrategy implements MessageStrategy {
    @Override
    public Map<String, String> crearMensaje(String contenido) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("emisor", "usuario");
        mensaje.put("contenido", contenido);
        mensaje.put("timestamp", new Date().toString());
        return mensaje;

    }
}
