package com.example.keycloak.eventlisteners;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class JsonEventListenerProvider implements EventListenerProvider {
    private KeycloakSession session;
    private static final Gson gson = new Gson();

    public JsonEventListenerProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        log.info(transformEvent(event));
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        log.info(transformEvent(event));
    }

    @Override
    public void close() {

    }

    private JsonObject transformEvent(Object event) {
        JsonObject jsonObject = JsonParser.parseString(gson.toJson(event)).getAsJsonObject();
        jsonObject.addProperty("source", session.getContext().getConnection().getRemoteAddr());
        jsonObject.addProperty("destination", session.getContext().getConnection().getLocalAddr());
        jsonObject.addProperty("status", session.getContext().getHttpResponse().getStatus());
        return jsonObject;
    }
}
