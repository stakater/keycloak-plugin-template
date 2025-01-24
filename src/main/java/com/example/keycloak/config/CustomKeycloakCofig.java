package com.example.keycloak.config;

import java.util.ArrayList;
import java.util.List;

public class CustomKeycloakCofig implements KeycloakConfig {

    @Override
    public List<KeycloakEnv> getEnvs() {

        var list = new ArrayList<KeycloakEnv>();
        for (var val : Envs.values()) {
            var env = KeycloakEnv.builder().envVar(val.getEnvVar()).spiVar(val.getSpiVar()).required(val.isRequired())
                    .isNumber(val.isNumber()).build();
            list.add(env);
        }
        return list;
    }

    public static enum Envs {
        CONFIG_1(true, true),
        CONFIG_2(true, false),
        ;

        private boolean required;
        private boolean isNumber;

        private Envs(boolean required, boolean isNumber) {
            this.required = required;
            this.isNumber = isNumber;
        }

        public String getEnvVar() {
            return name();
        }

        public boolean isRequired() {
            return required;
        }

        public boolean isNumber() {
            return isNumber;
        }

        public String getSpiVar() {
            return KeycloakConfig.getFormattedValue(name());
        }
    }
}
