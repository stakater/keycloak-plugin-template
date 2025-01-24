package com.example.keycloak.authenticators;

import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import com.google.auto.service.AutoService;

// Example, uncomment this line to register the authenticator
@AutoService(AuthenticatorFactory.class)
public class OtpAuthenticatorFactory implements AuthenticatorFactory {

    private static final OtpAuthenticator SINGLETON = new OtpAuthenticator();

    @Override
    public void close() {
    }

    @Override
    public Authenticator create(KeycloakSession arg0) {
        return SINGLETON;
    }

    @Override
    public String getId() {
        return "cotp-authenticator";
    }

    @Override
    public void init(Scope arg0) {
    }

    @Override
    public void postInit(KeycloakSessionFactory arg0) {

    }

    @Override
    public String getDisplayType() {
        return "OTP Authenticator";
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return List.of();
    }

    @Override
    public String getHelpText() {
        return "OTP Authenticator help text";
    }

}
