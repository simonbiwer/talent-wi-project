package com.example.application.Service.ChatGPT;

import com.example.application.utils.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class ChatGPTAPIExample {
    public static HashMap<String, String> sendRequestToChatGPT(String jobURL) throws IOException {
        // Set the API endpoint URL
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // Set your OpenAI API key
        // moved to external JSON file

        // Set the content type and accept headers
        String contentType = "application/json";
        String accept = "application/json";

        // Set the request payload
        String payload = "{\"model\": \"gpt-3.5-turbo\", \"messages\": " +
                "[{\"role\": \"system\", \"content\": \"You are a helpful assistant, who reads URLs carefully and fulfills ALL requirements given by the user.\"}, " +

                "{\"role\": \"user\", \"content\": \" Im Folgenden erhaeltst du URLs von Stellenanzeigen. Diese sollen auf diese Themen untersucht werden:" +
                " Technologie; Name des Unternehmens; Projekttitel bzw. Titel der Stelle; Beschreibung bzw. Taetigkeiten; Projektdauer; Startdatum;" +
                " Profil und Qualifikationen; Bitte halte die Reihenfolge dieser Themen ein und trenne die Themen mit einem Semikolon." +
                "Falls du keine Informationen findest, schreibe 'keine Angabe'." +
                "Suche fuer Projektdauer nach Begriffen wie 'Feste Anstellung' oder 'Vollzeit' oder 'Teilzeit'. Wenn du nichts findest, schreib 'langfristig'." +
                "Wenn du zum Startdatum keine Angaben findest, schreib 'ab sofort'" +
                "Beschreibung bzw. Taetigkeiten soll die Aufgaben und Rolle des Jobs darstellen, bitte fass die Punkte auf der Seite zusammen" +
                "Profil und Qualifikationen beziehen sich auf den Bewerber, also welche Qualifikationen der Bewerber besitzt. Bitte fass die" +
                "auf der Seite genannten Punkte zusammen \"} ," +

                "{\"role\": \"assistant\", \"content\": \"Alles klar das Format ist also so: 'Thema': 'Schlagwort'; 'Naechstes Thema':" +
                " 'Naechstes Schlagwort' . Ich benutze ein Semikolon, bevor ich das naechste Thema starte. " +
                "Wenn ich innerhalb eines Themas etwas aufzaehle, benutze ich ein Komma." +
                " Ich schreibe NICHTS anderes ausser das Thema und das Schlagwort,damit die Anworten vom Stringparser aufgenommen werden koennen.\"}, " +

                "{\"role\": \"user\", \"content\": \"Richtig. Und ganz wichtig: Halte dich an die Reihenfolge. Beginne mit Thema 'Technologie'" +
                " und ende mit Thema 'Profil und Qualifikationen'. Ausserdem schreib bitte alles in eine Zeile, das heisst keine Zeilenumbrueche!" +
                "  Hier die URL: " + jobURL + ".\"}]}";

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

        return JSONParser.parseJSON(responseBody);
    }
}

