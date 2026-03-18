package org.chatboot.services;

import java.util.Map;

public interface MessageStrategy {
    Map<String, String> crearMensaje(String contenido);
}
