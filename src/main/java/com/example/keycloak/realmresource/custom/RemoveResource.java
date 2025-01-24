package com.example.keycloak.realmresource.custom;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RemoveResource implements RealmResourceProvider {
    private final KeycloakSession session;

    public RemoveResource(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public Object getResource() {
        return this;
    }

    @Override
    public void close() {
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@QueryParam("user_id") String userid) {
        return Response.ok().build();
    }
}
