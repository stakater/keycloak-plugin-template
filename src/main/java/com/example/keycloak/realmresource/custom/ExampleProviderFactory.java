package com.example.keycloak.realmresource.custom;

import java.util.Properties;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

import com.example.keycloak.config.CustomKeycloakCofig;
import com.google.auto.service.AutoService;

@AutoService(RealmResourceProviderFactory.class)
public class ExampleProviderFactory implements RealmResourceProviderFactory {

	private static volatile Properties props;

	public static final String ID = "example";

	@Override
	public RealmResourceProvider create(KeycloakSession session) {
		return new CustomResourceProvider(session);
	}

	@Override
	public void init(Scope config) {
		synchronized (ExampleProviderFactory.class) {
			if (props == null) {
				CustomKeycloakCofig envs = new CustomKeycloakCofig();
				props = envs.load(config);
			}
		}
	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {

	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void close() {
	}

}