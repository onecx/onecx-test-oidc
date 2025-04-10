package org.tkit.onecx.test.oidc.rs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.tkit.quarkus.log.cdi.LogService;

import gen.org.tkit.onecx.test.oidc.client.api.BackendApi;
import gen.org.tkit.onecx.test.oidc.rs.internal.RequestApiService;

@ApplicationScoped
@Transactional(value = Transactional.TxType.NOT_SUPPORTED)
@LogService
public class RequestRestController implements RequestApiService {

    @Inject
    @RestClient
    BackendApi backendApi;

    @Override
    public Response testOidcClient() {
        try (var response = backendApi.testOidcService()) {
            var data = response.readEntity(Object.class);
            return Response.ok(data).build();
        }
    }
}
