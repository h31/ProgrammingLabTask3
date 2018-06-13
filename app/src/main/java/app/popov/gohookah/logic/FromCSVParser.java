package app.popov.gohookah.logic;

import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.popov.gohookah.logic.database.Firebase;
import app.popov.gohookah.logic.database.FirebaseHookah;

public class FromCSVParser {
    //final static String API_KEY = ""
    public static void startParsing(String fileName, Context context) {
        try {
            List<String> lines = IOUtils.readLines(context.getAssets().open(fileName));
            for (String line : lines) {
                ArrayList<String> splittedLine = new ArrayList<String>(Arrays.asList(line.split("\",\"")));
                FirebaseHookah currentHookah = new FirebaseHookah();
                currentHookah.setCity(splittedLine.get(1));
                currentHookah.setClubName(normalizeClubName(splittedLine.get(0)));
                currentHookah.setPhoneNumber(normalizePhone(splittedLine.get(3)));
                String[] address = splittedLine.get(2).split(",");
                currentHookah.setStreet(address[0]);
                currentHookah.setHouseNumber(address[1].replaceAll(" лит ", ""));
                String[] coordinates = getCoordinates(splittedLine.get(6));
                currentHookah.setLongitude(Double.parseDouble(coordinates[1]));
                currentHookah.setLatitude(Double.parseDouble(coordinates[2]));
                currentHookah.setVk(normalizeVk(splittedLine.get(4)));
                currentHookah.setInstagram(normalizeInstagram(splittedLine.get(5)));
                currentHookah.setImages(new ArrayList<>());
                currentHookah.setCountry("Россия");
                System.out.println(currentHookah.toString());
                Firebase.addToFireBase(currentHookah, context);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String normalizeClubName(String withNameLine) {
        return withNameLine.replaceAll("\"", "");
    }

    public static String normalizePhone(String phoneNumber) {
        if (phoneNumber != "") {
            return "+" + phoneNumber.split(",")[0].replaceAll("-|\\(|\\)| ", "");
        }
        return "";
    }

    public static String normalizeVk(String groupURL) {
        return groupURL.replaceAll("https://vk.com/", "");
    }

    public static String normalizeInstagram(String instURL) {
        return instURL.replaceAll("https://instagram.com/", "");
    }

    public static String[] getCoordinates(String coordinates) {
        String coorditanesWithOutQuote = coordinates.replaceAll("\"", "");
        return coorditanesWithOutQuote.split("lon: | lat: ");
    }

}