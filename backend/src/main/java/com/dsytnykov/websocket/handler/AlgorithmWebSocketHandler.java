package com.dsytnykov.websocket.handler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dsytnykov.websocket.algorithm.SortContext;
import com.dsytnykov.websocket.algorithm.sort.SortType;
import com.dsytnykov.websocket.model.AlgorithmRequest;
import com.dsytnykov.websocket.model.ArrayState;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AlgorithmWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        JsonNode jsonNode = objectMapper.readTree(payload);

        if (jsonNode.has("algorithmType") && jsonNode.has("array")) {
            AlgorithmRequest request = objectMapper.readValue(payload, AlgorithmRequest.class);
            handleAlgorithmRequest(request);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    private void handleAlgorithmRequest(AlgorithmRequest request) {
        String algorithmType = request.getAlgorithmType();
        int[] array = request.getArray();

        SortContext sortContext = new SortContext();
        sortContext.setStateUpdateCallback(this::broadcastArrayState);
        sortContext.setSortingAlgorithm(SortType.valueOf(algorithmType).getSortingAlgorithm());
        sortContext.sort(array);

        ArrayState finalState = new ArrayState();
        finalState.setArray(array);
        finalState.setCurrentIndex(-1);
        finalState.setCompareIndex(-1);
        finalState.setComplete(true);

        broadcastArrayState(finalState);
    }

    public void broadcastArrayState(ArrayState state) {
        try {
            String stateJson = objectMapper.writeValueAsString(state);
            TextMessage message = new TextMessage(stateJson);

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
