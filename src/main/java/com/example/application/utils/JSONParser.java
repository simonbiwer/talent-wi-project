package com.example.application.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

    public static String parseJSON(String jsonString) {
        //String jsonString = "{\"id\":\"chatcmpl-7JNH7vRq3uSx6ifoFSPgHkLGFXMqg\",\"object\":\"chat.completion\",\"created\":1684852057,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":272,\"completion_tokens\":237,\"total_tokens\":509},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"Technologie: Keine\\nName des Unternehmens: Goost und Nowak Rechtsanwälte in Partnerschaft\\nProjekttitel bzw. Titel der Stelle: Steuerfachangestellter (m/w/d), Steuerfachwirtin (m/w/d), Bilanzbuchhalterin (m/w/d) in Vollzeit oder Teilzeit\\nBeschreibung bzw. Tätigkeiten: Verantwortung für die Erstellung von Finanzbuchhaltungen, Lohnbuchhaltungen, Jahresabschlüssen und Steuererklärungen. Mitwirkung bei steuerlichen Fragestellungen und Beratungen\\nProjektdauer: Keine\\nStartdatum: Unbekannt\\nProfil und Qualifikationen: Ausbildung zur Steuerfachangestellten (m/w/d), Steuerfachwirtin (m/w/d) oder Bilanzbuchhalterin (m/w/d), Kenntnisse im Steuerrecht, sorgfältige und eigenverantwortliche Arbeitsweise, Teamfähigkeit, Engagement und Zuverlässigkeit.\"},\"finish_reason\":\"stop\",\"index\":0}]}";

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray choices = json.getJSONArray("choices");
            JSONObject choice = choices.getJSONObject(0);
            JSONObject message = choice.getJSONObject("message");

            String content = message.getString("content");

            System.out.println(content);

            String[] lines = content.split(";");


            String technologie = extractValue(lines[0]);
            String unternehmen = extractValue(lines[1]);
            String titel = extractValue(lines[2]);
            String beschreibung = extractValue(lines[3]);
            String projektdauer = extractValue(lines[4]);
            String startdatum = extractValue(lines[5]);
            String qualifikationen = extractValue(lines[6]);

            System.out.println("Technologie: " + technologie);
            System.out.println("Name des Unternehmens: " + unternehmen);
            System.out.println("Projekttitel bzw. Titel der Stelle: " + titel);
            System.out.println("Beschreibung bzw. Taetigkeiten: " + beschreibung);
            System.out.println("Projektdauer: " + projektdauer);
            System.out.println("Startdatum: " + startdatum);
            System.out.println("Profil und Qualifikationen: " + qualifikationen);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Hier kommt noch der Responsebody hin.";
    }

    private static String extractValue(String line) {
        String[] parts = line.split(":");
        if (parts.length == 2) {
            return parts[1].trim();
        } else {
            return "";
        }
    }
}

