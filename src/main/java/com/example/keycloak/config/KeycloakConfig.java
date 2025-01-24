package com.example.keycloak.config;

import java.util.List;
import java.util.Properties;

import org.keycloak.Config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public interface KeycloakConfig {

    public List<KeycloakEnv> getEnvs();

    public default Properties load(Config.Scope config) {
        var envs = getEnvs();
        Properties props = new Properties();
        for (var env : envs) {
            String value = null;
            if (config != null) {
                value = config.get(env.getSpiVar());
            }

            if (value == null) {
                value = System.getenv(env.getEnvVar());
            }

            if (value == null) {
                value = env.getDefaultValue();
            }

            if (value == null) {
                if (env.isRequired()) {
                    throw new IllegalStateException("Missing required configuration: system env: " + env.getEnvVar()
                            + " or spi env (aVarKey should be passed as spi-<spiname>-<providerid>-a-var-key=value): "
                            + env.getSpiVar()
                            + " is required.");
                }
                continue;
            }

            if (env.isNumber()) {
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    throw new IllegalStateException(
                            "Invalid configuration: " + env.getEnvVar() + " or " + env.getSpiVar()
                                    + " should be a number.");
                }
            }

            props.setProperty(env.getEnvVar(), value);
        }
        return props;
    }

    @AllArgsConstructor
    @Data
    @Builder
    public static class KeycloakEnv {
        private String envVar;
        private String spiVar;
        private boolean required;
        private boolean isNumber;
        private String defaultValue;
    }

    /*
     * This method is used to get the formatted value of the enum. For example, if
     * the enum value is A_SECRET_KEY, the formatted value will be aSecretKey.
     */
    public static String getFormattedValue(String enVar) {
        String[] parts = enVar.toLowerCase().split("_");
        StringBuilder formattedValue = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            formattedValue.append(parts[i].substring(0, 1).toUpperCase())
                    .append(parts[i].substring(1));
        }
        return formattedValue.toString();
    }
}
