package com.example.application.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import oshi.util.GlobalConfig;

import java.util.HashMap;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Klasse, die die Response von ChatGPT verarbeit und parst, und die auf die global attributes setzt.
 */

public class JSONParser {

    public static HashMap<String, String> parseJSON(String jsonString) {
        //String jsonString = "{\"id\":\"chatcmpl-7JNH7vRq3uSx6ifoFSPgHkLGFXMqg\",\"object\":\"chat.completion\",\"created\":1684852057,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":272,\"completion_tokens\":237,\"total_tokens\":509},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"Technologie: Keine\\nName des Unternehmens: Goost und Nowak Rechtsanwälte in Partnerschaft\\nProjekttitel bzw. Titel der Stelle: Steuerfachangestellter (m/w/d), Steuerfachwirtin (m/w/d), Bilanzbuchhalterin (m/w/d) in Vollzeit oder Teilzeit\\nBeschreibung bzw. Tätigkeiten: Verantwortung für die Erstellung von Finanzbuchhaltungen, Lohnbuchhaltungen, Jahresabschlüssen und Steuererklärungen. Mitwirkung bei steuerlichen Fragestellungen und Beratungen\\nProjektdauer: Keine\\nStartdatum: Unbekannt\\nProfil und Qualifikationen: Ausbildung zur Steuerfachangestellten (m/w/d), Steuerfachwirtin (m/w/d) oder Bilanzbuchhalterin (m/w/d), Kenntnisse im Steuerrecht, sorgfältige und eigenverantwortliche Arbeitsweise, Teamfähigkeit, Engagement und Zuverlässigkeit.\"},\"finish_reason\":\"stop\",\"index\":0}]}";

        HashMap<String, String> attributes = new HashMap<>();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray choices = json.getJSONArray("choices");
            JSONObject choice = choices.getJSONObject(0);
            JSONObject message = choice.getJSONObject("message");

            String content = message.getString("content");

            System.out.println(content);

            String[] lines = content.split(";");

            attributes.put(Globals.Attributes.TECHNOLOGIE, extractValue(lines[0]));
            attributes.put(Globals.Attributes.UNTERNEHMEN, extractValue(lines[1]));
            attributes.put(Globals.Attributes.TITEL, extractValue(lines[2]));
            attributes.put(Globals.Attributes.BESCHREIBUNG, extractValue(lines[3]));
            attributes.put(Globals.Attributes.PROJEKTDAUER, extractValue(lines[4]));
            attributes.put(Globals.Attributes.STARTDATUM, extractValue(lines[5]));
            attributes.put(Globals.Attributes.QUALIFIKATIONEN, extractValue(lines[6]));

//            System.out.println("Technologie: " + technologie);
//            System.out.println("Name des Unternehmens: " + unternehmen);
//            System.out.println("Projekttitel bzw. Titel der Stelle: " + titel);
//            System.out.println("Beschreibung bzw. Taetigkeiten: " + beschreibung);
//            System.out.println("Projektdauer: " + projektdauer);
//            System.out.println("Startdatum: " + startdatum);
//            System.out.println("Profil und Qualifikationen: " + qualifikationen);
// /\,\;/g
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attributes;
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

