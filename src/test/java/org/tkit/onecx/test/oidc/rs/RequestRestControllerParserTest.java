package org.tkit.onecx.test.oidc.rs;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class RequestRestControllerParserTest {

    @Inject
    BackendRestController controller;

    @Test
    void testParseClaims() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> controller.parseClaims(null))
                .withCauseInstanceOf(NullPointerException.class)
                .withMessage(
                        "java.lang.NullPointerException: Cannot invoke \"String.startsWith(String)\" because \"cs\" is null");
    }

}
