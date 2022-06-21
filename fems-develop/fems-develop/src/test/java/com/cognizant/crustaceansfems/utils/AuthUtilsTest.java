package com.cognizant.crustaceansfems.utils;

import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthUtilsTest {

    AuthUtils authUtils=new AuthUtils();

    @InjectMocks
    AuthUtils mockUtils;

    @Mock
    JWT jwt;

    final String issuer = "https://enablement-keycloak.work.cognizant.studio/auth/realms/Enablement",
    token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLQUg2Ry1RdUdHLWFkNWpFMnpqeW44My1FdThMNHpuVVU4QmpYZUx1UFJjIn0.eyJleHAiOjE2NTExNjcxODUsImlhdCI6MTY1MTE2Njg4NSwianRpIjoiY2E0YjUxNDItZGNlMC00YjExLWEzODctNGQ3NDkwNTFkNzRmIiwiaXNzIjoiaHR0cHM6Ly9lbmFibGVtZW50LWtleWNsb2FrLndvcmsuY29nbml6YW50LnN0dWRpby9hdXRoL3JlYWxtcy9FbmFibGVtZW50IiwiYXVkIjpbInJlYWxtLW1hbmFnZW1lbnQiLCJhY2NvdW50Il0sInN1YiI6ImE5ZWVmYzk0LWEwYjctNGY2Ni04OThmLTUwNjRlMjhiYmJkZiIsInR5cCI6IkJlYXJlciIsImF6cCI6ImNydXN0YWNlYW5zLWF1dGgiLCJzZXNzaW9uX3N0YXRlIjoiMzI2ZGU3YTktZWNiZC00M2FhLTg4ZjItMjhiY2E2MTAzMzE2IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLWVuYWJsZW1lbnQiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJ2aWV3LWlkZW50aXR5LXByb3ZpZGVycyIsInZpZXctcmVhbG0iLCJjcmVhdGUtY2xpZW50IiwibWFuYWdlLXVzZXJzIiwidmlldy1hdXRob3JpemF0aW9uIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LXVzZXJzIiwibWFuYWdlLWV2ZW50cyIsInZpZXctZXZlbnRzIiwidmlldy11c2VycyIsInZpZXctY2xpZW50cyIsIm1hbmFnZS1jbGllbnRzIiwicXVlcnktZ3JvdXBzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImFkbWluIn0.lSdvWhk20Ov2PW_r-aEQb6ukg1R5H_LOvgqoY8aTX9s7JqOmzGanRtoY9YYwvs_Cuko8BUE0ZR7f9otrPI9KZfMh4COtvdRtZQN8agT0IZShrrNjCAy4qJfUDbc-RBt43hMlx2VedcgGBppGuZRvNfaTdkDxxkSEHOpuyTlmBWyyEjGidzqwEul_s_SNX3kgTJ_VgNsX4zADH5aWTplx4tPo_Xe7AUBc80KZw5jn0tKEhQbRrYcs8eoh8SP7kZpYxKoIuUi8dmeO_cPiiOehwkd3_LZOUbrS5s98qqnLEqTOmTqFGaSh2tDjSm3yi4XL4X0Tmsa6Oxkh5z0aMn8HqA";

    @Test
    public void testValidate() { assertFalse(authUtils.validate(token, issuer)); }

    @Test
    public void testEmptyValidate() {
        assertFalse(authUtils.validate("", issuer));
    }

//    @Test
//    void testJWTValidateFails(){
//        assertFalse(authUtils.signatureValidation(token));
//    }
}