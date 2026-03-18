package org.chatboot.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.chatboot.dtos.ChatRequest;
import org.chatboot.services.ChatService;

import java.util.HashMap;
import java.util.Map;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatController {

    @Inject
    ChatService chatService;

    @POST
    public Response enviarMensaje(ChatRequest request) {

            String respuesta = chatService.enviarMensaje(request.getMessage());

            Map<String, Object> response = new HashMap<>();
            response.put("respuesta", respuesta);
            response.put("historial", chatService.getHistorial());

            return Response.ok(response).build();

    }

    @GET
    @Path("/historial")
    public Response getHistorial() {
        return Response.ok(chatService.getHistorial()).build();
    }

    @DELETE
    @Path("/historial")
    public Response limpiarHistorial() {
        chatService.limpiarHistorial();
        return Response.ok(Map.of("mensaje", "Historial limpiado")).build();
    }
}