package com.example.application.Service.ChatGPT;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ChatGPTAPIExample {
    public static void main(String[] args) throws IOException {
        // Set the API endpoint URL
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // Set your OpenAI API key
        // moved to external JSON file

        // Set the content type and accept headers
        String contentType = "application/json";
        String accept = "application/json";

        // Set the request payload
        String payload = "{\"model\": \"gpt-3.5-turbo\", \"messages\": " +
                "[{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"}, " +
                "{\"role\": \"user\", \"content\": \" Im Folgenden erhaelst du URLs von Stellenanzeigen. Diese sollen auf diese Themen beziehen: Technologie; Name des Unternehmens; Projekttitel bzw. Titel der Stelle; Beschreibung bzw. Taetigkeiten; Projektdauer; Startdatum; Profil und Qualifikationen\"} ," +
                "{\"role\": \"system\", \"content\": \"Alles klar das Format ist also so: 'Thema': 'Schlagwort'. Bitte schreibe NICHTS anderes ausser das Thema und das Schlagwort, ich muss naehmlich die Anworten parsen.\"}, " +
                "{\"role\": \"user\", \"content\": \"Richtig. Hier die URL: https://www.stepstone.de/stellenangebote--Senior-Cyber-Security-Manager-CERT-SOC-m-w-d-Bonn-Koeln-Muenchen-Nuernberg-Strausberg-BWI-GmbH--9532572-inline.html?rltr=2_2_25_seorl_m_0_0_0_0_1_0 .\"}]}";

        // Create an instance of CloseableHttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Create an instance of HttpPost with the API endpoint
        HttpPost httpPost = new HttpPost(apiUrl);

        // Set the request headers
//        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        httpPost.setHeader(HttpHeaders.ACCEPT, accept);

        // Set the request payload
        httpPost.setEntity(new StringEntity(payload));

        // Execute the request
        HttpResponse response = httpClient.execute(httpPost);

        // Get the response code
        int responseCode = response.getStatusLine().getStatusCode();

        // Read the response
        HttpEntity responseEntity = response.getEntity();
        String responseBody = EntityUtils.toString(responseEntity);

        // Close the HttpClient
        httpClient.close();

        // Print the response
        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Body: " + responseBody);
    }
}

