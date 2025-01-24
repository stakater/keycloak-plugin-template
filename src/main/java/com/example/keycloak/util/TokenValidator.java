package com.example.keycloak.util;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.managers.AppAuthManager;
import org.keycloak.services.managers.AuthenticationManager.AuthResult;

public class TokenValidator {
    public static AuthResult validateBearerToken(KeycloakSession session) {
        return new AppAuthManager.BearerTokenAuthenticator(session).authenticate();
    }
}