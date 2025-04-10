package org.tkit.onecx.test.oidc.rs;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(targets = { JwtClaims.class, NumericDate.class })
public class ReflectionConfig {
}
