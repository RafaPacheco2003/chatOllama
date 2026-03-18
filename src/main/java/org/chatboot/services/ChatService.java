package org.chatboot.services;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.*;

@ApplicationScoped
public class ChatService {
    @Inject
    ChatModel chatModel;
    @Inject
    MessageService messageService;

    private List<Map<String, String>> historial = new ArrayList<>();

    public String enviarMensaje(String mensaje) {
        // Usar strategy para usuario
        Map<String, String> msgUser = messageService.crearMensaje(mensaje, "usuario");
        historial.add(msgUser);

        // Construir historial de mensajes para el modelo (usuario y bot)
        List<ChatMessage> chatMessages = new ArrayList<>();
        for (Map<String, String> msg : historial) {
            if ("usuario".equals(msg.get("emisor"))) {
                chatMessages.add(UserMessage.from(msg.get("contenido")));
            } else if ("bot".equals(msg.get("emisor"))) {
                chatMessages.add(AiMessage.from(msg.get("contenido")));
            }
        }

        // Obtener respuesta considerando todo el historial
        ChatResponse response = chatModel.chat(chatMessages);
        String respuesta = response.aiMessage().text();

        // Usar strategy para bot
        Map<String, String> msgBot = messageService.crearMensaje(respuesta, "bot");
        historial.add(msgBot);

        return respuesta;
    }

    public List<Map<String, String>> getHistorial() {
        // Devuelve una copia ordenada y estructurada del historial
        List<Map<String, String>> historialMap = new ArrayList<>();
        for (Map<String, String> msg : historial) {
            Map<String, String> map = new HashMap<>();
            map.put("emisor", msg.get("emisor"));
            map.put("contenido", msg.get("contenido"));
            map.put("timestamp", msg.get("timestamp"));
            historialMap.add(map);
        }
        return historialMap;
    }

    public void limpiarHistorial() {
        historial.clear();
    }
}