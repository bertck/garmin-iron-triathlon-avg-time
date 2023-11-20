package org.gitavgtime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args){
        final String[] url = new String[]{"https://www.startlist.pl/zawody/", "/wyniki/2023-garmin-iron-triathlon-", "-2023-dystans-1-8-ironman"};
        final String[][] races = new String[][]{
                {"1222", "nieporet"},
                {"1201", "zyrardow"},
                {"1161","serock"},
                {"1213","plock"},
                {"1207","rawa-mazowiecka"},
                {"1228","goldap"},
                {"1198","elblag"},
                {"1164","sycow"},
                {"1155","slesin"},
                {"1158","stezyca"},
                {"1179","skierniewice"}};
        Map<String, String> gitRaces = new HashMap<>();

        for (String[] race : races) {
            gitRaces.put(race[0], race[1]);
        }

        for (String value : gitRaces.values()){
            System.out.println(value);
        }

//        try {
//            final Document document = Jsoup.connect(url).get();
//            Elements element = document.select("div");
//            String tekst = element.text();
//
//        } catch (Exception e) {
//            System.out.println("\nBład łączenia się z witryną!\n");
//            e.printStackTrace();
//        }
    }
}
