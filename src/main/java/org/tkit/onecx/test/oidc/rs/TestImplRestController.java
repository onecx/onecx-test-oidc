package org.tkit.onecx.test.oidc.rs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwx.JsonWebStructure;
import org.tkit.quarkus.log.cdi.LogService;

import gen.org.tkit.onecx.test.oidc.rs.internal.TestImplApiService;
import gen.org.tkit.onecx.test.oidc.rs.internal.model.TokenDTO;

@ApplicationScoped
@Transactional(value = Transactional.TxType.NOT_SUPPORTED)
@LogService
public class TestImplRestController implements TestImplApiService {

    @Inject
    JsonWebToken jwt;

    @Override
    public Response testOidcService() {
        var claims = parseClaims(jwt.getRawToken());
        return Response.ok(new TokenDTO().raw(jwt.getRawToken()).json(claims)).build();
    }

    protected JwtClaims parseClaims(String token) {
        try {
            var jws = (JsonWebSignature) JsonWebStructure.fromCompactSerialization(token);
            return JwtClaims.parse(jws.getUnverifiedPayload());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
