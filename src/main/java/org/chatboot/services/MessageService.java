package org.chatboot.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.chatboot.services.impl.BotStrategy;
import org.chatboot.services.impl.UsuarioStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class MessageService {

    private final Map<String, MessageStrategy> strategies = new ConcurrentHashMap<>();

    public MessageService() {
        strategies.put("usuario", new UsuarioStrategy());
        strategies.put("bot", new BotStrategy());
    }

    public Map<String, String> crearMensaje(String contenido, String tipo) {
        MessageStrategy strategy = strategies.get(tipo);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo no soportado: " + tipo);
        }
        return strategy.crearMensaje(contenido);
    }

    public void registrarStrategy(String tipo, MessageStrategy strategy) {
        strategies.put(tipo, strategy);
    }
}