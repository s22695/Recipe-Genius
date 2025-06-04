package com.pro.auth;

import com.pro.auth.DTOs.CreateUserRequest;
import com.pro.auth.DTOs.LoginRequest;
import com.pro.auth.DTOs.RefreshRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class KeycloakAuthService {
    private String authServerUrl, realm, clientId, clientSecret, adminUsername, adminPassword;
    private final RestTemplate restTemplate = new RestTemplate();

    public KeycloakAuthService(
            @Value("${keycloak.auth-server-url}")
            String authServerUrl,
            @Value("${keycloak.realm}")
            String realm,
            @Value("${keycloak.client}")
            String clientId,
            @Value("${keycloak.credentials.secret}")
            String clientSecret,
            @Value("${keycloak.admin.username}")
            String adminUsername,
            @Value("${keycloak.admin.password}")
            String adminPassword
    ) {
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public ResponseEntity<?> login(LoginRequest req) {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", authServerUrl, realm);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("username", req.username());
        form.add("password", req.password());
        form.add("scope", "openid");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        return restTemplate.postForEntity(tokenUrl, request, Map.class);
    }

    public ResponseEntity<?> refresh(RefreshRequest req) {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", authServerUrl, realm);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "refresh_token");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("refresh_token", req.refreshToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        return restTemplate.postForEntity(tokenUrl, request, Map.class);
    }

    public ResponseEntity<?> getNewRefreshToken(RefreshRequest req) {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", authServerUrl, realm);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "refresh_token");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("refresh_token", req.refreshToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        return restTemplate.postForEntity(tokenUrl, request, Map.class);
    }

    public ResponseEntity<?> createUser(CreateUserRequest req) {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", authServerUrl, realm);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("username", adminUsername);
        form.add("password", adminPassword);
        form.add("scope", "openid");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        Map<String, Object> authResp = restTemplate.postForObject(tokenUrl, request, Map.class);
        String adminToken = (String) authResp.get("access_token");

        // Payload
        Map<String, Object> user = new HashMap<>();
        user.put("username", req.username());
        user.put("firstName", req.firstName());
        user.put("lastName", req.lastName());
        user.put("email", req.email());
        user.put("enabled", true);
        user.put("emailVerified", true);
        Map<String, Object> cred = new HashMap<>();
        cred.put("type", "password");
        cred.put("value", req.password());
        cred.put("temporary", false);
        user.put("credentials", Collections.singletonList(cred));

        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.setBearerAuth(adminToken);
        userHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> userRequest = new HttpEntity<>(user, userHeaders);

        String usersUrl = String.format("%s/admin/realms/%s/users", authServerUrl, realm);
        return restTemplate.postForEntity(usersUrl, userRequest, String.class);
    }
}
