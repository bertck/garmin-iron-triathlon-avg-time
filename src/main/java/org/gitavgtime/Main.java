package org.gitavgtime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.LinkedList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    final int N = 200;
    final String[] url =
        new String[] {
          "https://www.startlist.pl/zawody/",
          "/wyniki/2023-garmin-iron-triathlon-",
          "-2023-dystans-1-8-ironman"
        };
    final String[][] races =
        new String[][] {
          {"1222", "nieporet"},
          {"1201", "zyrardow"},
          {"1161", "serock"},
          {"1213", "plock"},
          {"1207", "rawa-mazowiecka"},
          {"1228", "goldap"},
          {"1198", "elblag"},
          {"1164", "sycow"},
          {"1155", "slesin"},
          {"1158", "stezyca"},
          {"1179", "skierniewice"}
        };
    List<gitRace> gitRaces = new LinkedList<>();

    for (String[] race : races) {
      gitRaces.add(new gitRace(race[1], race[0]));
    }

    for (gitRace race : gitRaces) {
      countAverage(url, race, N);
    }
    bubbleSortRaces(gitRaces, N);

    for (int i = 0; i < gitRaces.size(); i++) {
      System.out.println(
          "\nMiejsce "
              + (i + 1)
              + ".: "
              + gitRaces.get(i).getName()
              + "\n\tZ wynikiem:\t"
              + toHourFormat(gitRaces.get(i).getAvgN()));
    }
  }

  private static void countAverage(String[] url, gitRace race, int N) {
    try {
      final Document document =
          Jsoup.connect(url[0] + race.getId() + url[1] + race.getName()).get();
      String tekst;
      Elements element;
      int sum = 0;
      int i;
      for (i = 1; i <= N; i++) {
        element = document.select("tr#p" + i + " > td > strong");
        tekst = element.text();
        if (!tekst.equals("") && !tekst.equals("DNS") && !tekst.equals("DNF") && !tekst.equals("DSQ")) {
          sum += toSeconds(tekst);
        } else {
            break;
        }
        if (i == 10) {
          race.setAvg10(sum / 10);
        }
        if (i == 20) {
          race.setAvg20(sum / 20);
        }
        if (i == 50) {
          race.setAvg50(sum / 50);
        }
        if (i == 100) {
          race.setAvg100(sum / 100);
        }
      }
      race.setAvgN(sum / (i-1));
    } catch (Exception e) {
      System.out.println("\nBład łączenia się z witryną "+ race.getName() +"!\n");
      e.printStackTrace();
    }
  }

  private static int toSeconds(String time) {
    String[] timeArray = time.split(":");
    int hours = Integer.parseInt(timeArray[0]);
    int minutes = Integer.parseInt(timeArray[1]);
    int seconds = Integer.parseInt(timeArray[2]);
    return (hours * 60 * 60) + (minutes * 60) + seconds;
  }

  private static String toHourFormat(int time) {
    int hours = time / 3600;
    int minutes = (time - (hours * 3600)) / 60;
    int seconds = time - (hours * 3600) - (minutes * 60);
    String timeInHourFormat = "";
    if (hours < 10 && hours >= 0) {
      timeInHourFormat += "0";
    }
    timeInHourFormat += hours + ":";
    if (minutes < 10 && minutes >= 0) {
      timeInHourFormat += "0";
    }
    timeInHourFormat += minutes + ":";
    if (seconds < 10 && seconds >= 0) {
      timeInHourFormat += "0";
    }
    timeInHourFormat += seconds;
    return timeInHourFormat;
  }

  private static void bubbleSortRaces(List<gitRace> raceList, int N) {

      switch (N) {
          case 10 -> {
              for (int i = 0; i < raceList.size() - 1; i++) {
                  for (int j = 0; j < raceList.size() - i - 1; j++) {
                      if (raceList.get(j).getAvg10() > raceList.get(j + 1).getAvg10()) {
                          raceListSwap(raceList, j, j + 1);
                      }
                  }
              }
          }
          case 20 -> {
              for (int i = 0; i < raceList.size() - 1; i++) {
                  for (int j = 0; j < raceList.size() - i - 1; j++) {
                      if (raceList.get(j).getAvg20() > raceList.get(j + 1).getAvg20()) {
                          raceListSwap(raceList, j, j + 1);
                      }
                  }
              }
          }
          case 50 -> {
              for (int i = 0; i < raceList.size() - 1; i++) {
                  for (int j = 0; j < raceList.size() - i - 1; j++) {
                      if (raceList.get(j).getAvg50() > raceList.get(j + 1).getAvg50()) {
                          raceListSwap(raceList, j, j + 1);
                      }
                  }
              }
          }
          case 100 -> {
              for (int i = 0; i < raceList.size() - 1; i++) {
                  for (int j = 0; j < raceList.size() - i - 1; j++) {
                      if (raceList.get(j).getAvg100() > raceList.get(j + 1).getAvg100()) {
                          raceListSwap(raceList, j, j + 1);
                      }
                  }
              }
          }
          default -> {
              for (int i = 0; i < raceList.size() - 1; i++) {
                  for (int j = 0; j < raceList.size() - i - 1; j++) {
                      if (raceList.get(j).getAvgN() > raceList.get(j + 1).getAvgN()) {
                          raceListSwap(raceList, j, j + 1);
                      }
                  }
              }
          }
      }
  }

  private static void raceListSwap(List<gitRace> list, int first, int second) {
    gitRace temp = list.get(second);
    list.remove(second);
    list.add(second, list.get(first));
    list.remove(first);
    list.add(first, temp);
  }
}
