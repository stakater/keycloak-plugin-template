package com.example.keycloak.realmresource.custom;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

import jakarta.ws.rs.Path;

public class CustomResourceProvider implements RealmResourceProvider {

    private final KeycloakSession session;

    public CustomResourceProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public Object getResource() {
        return this;
    }

    @Override
    public void close() {
    }

    @Path("list")
    public ListResource getTrustedDevicesResource() {
        return new ListResource(session);
    }

    @Path("remove")
    public RemoveResource getRemoveTrustedDeviceResource() {
        return new RemoveResource(session);
    }
}