package com.example.BackendApp.service;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Value("${brevo_api_key}")
    private String apiKey;

    @Value("${app_base-url}")
    private String baseUrl;

    @Async
    public void sendVerificationEmail(String to, String token) {

        String link = baseUrl + "/api/autenticacion/verificacion?token=" + token;

        String url = "https://api.brevo.com/v3/smtp/email";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{"
                + "\"sender\":{\"email\":\"tuemail@gmail.com\",\"name\":\"BackendApp\"},"
                + "\"to\":[{\"email\":\"" + to + "\"}],"
                + "\"subject\":\"Verifica tu cuenta\","
                + "\"htmlContent\":\""
                + "<h2>Verifica tu cuenta</h2>"
                + "<p>Pulsa el botón para activar tu cuenta:</p>"
                + "<a href='" + link + "' style='padding:10px 20px;background:#007bff;color:white;text-decoration:none;border-radius:5px;'>Verificar cuenta</a>"
                + "\""
                + "}";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
            System.out.println("Email enviado " + to);
        } catch (Exception e) {
            System.out.println("Error enviando email: " + e.getMessage());
        }
    }
}