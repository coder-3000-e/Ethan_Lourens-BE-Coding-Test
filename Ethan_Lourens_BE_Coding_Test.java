/*Ethan Lourens
BE Coding Test*/

import java.util.*;
import java.util.Scanner;
import java.util.Map.*;
import java.util.stream.*;

public class Ethan_Lourens_BE_Coding_Test {
    public static HashMap<String, Integer> teamValues = new HashMap<String, Integer>();
    public static Map<String, Integer> sortHashMap;
    public static Integer win = 3;
    public static Integer tie = 1;
    public static Integer loss = 0;
    public static Integer count = 0;
    public static String inputLine;

    public static String removeAllDigit(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                result = result + str.charAt(i);
            }
        }
        return result;
    }

    public static Integer getVal(String str) {
        Integer num = Integer.parseInt(str.replaceAll("[\\D]", ""));
        return num;
    }

    public static void hashMapScan(String team, Integer teamPoint) {
        team = removeAllDigit(team);
        if (teamValues.containsKey(team)) {
            teamValues.replace(team, (teamValues.get(team) + teamPoint));
        } else {
            addToHashMap(team, teamPoint);
        }
    }

    public static void addToHashMap(String team, Integer teamPoint) {
        String teamName = team;
        Integer teamPoints = teamPoint;
        teamValues.put(teamName, teamPoints);
    }

    public static void calculateWin(String teamA, String teamB) {
        Integer teamAScore = getVal(teamA);
        Integer teamBScore = getVal(teamB);

        if (teamAScore > teamBScore) {
            teamAScore = win;
            teamBScore = loss;
        } else if (teamAScore < teamBScore) {
            teamAScore = loss;
            teamBScore = win;
        } else if (teamAScore == teamBScore) {
            teamAScore = tie;
            teamBScore = tie;
        }

        hashMapScan(teamA, teamAScore);
        hashMapScan(teamB, teamBScore);
    }

    public static void rearangeHashmap() {
        sortHashMap = teamValues.entrySet().stream()
                .sorted(Collections
                        .reverseOrder(
                                Map.Entry.<String, Integer>comparingByValue())
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void printOutput() {
        Integer prevVal = -1;
        for (Map.Entry mapElement : sortHashMap.entrySet()) {
            count++;
            String key = (String) mapElement.getKey();
            Integer value = (Integer) mapElement.getValue();
            if (prevVal == value) {
                System.out.printf("%d. %s, %d pts\n", count - 1, key.trim(), value);
                // System.out.println((count - 1) + ". " + key.trim() + ", " + value + " pts");
                prevVal = value;
            } else {
                System.out.printf("%d. %s, %d pts\n", count, key.trim(), value);
                // System.out.println(count + ". " + key.trim() + ", " + value + " pts");
                prevVal = value;
            }
        }
    }

    public void run() {
        Scanner inputScan = new Scanner(System.in);
        System.out.println("Welcome to my Program");
        System.out.println("Instructions: Please enter valid (formatted) Game Information");
        System.out.println("Instructions: For example: Lions 3, Snakes 3");
        System.out.println("Instructions: Press Enter to move to the next line\n");
        System.out.println("Please enter Game Information or enter 'done' when completed\n");
        while (!(inputLine = inputScan.nextLine()).equals("done")) {
            String[] splitTeam = inputLine.split(",");
            String teamA = splitTeam[0];
            String teamB = splitTeam[1];
            calculateWin(teamA, teamB);
        }
        rearangeHashmap();
        printOutput();
        inputScan.close();
    }

    public static void main(String[] args) {
        Ethan_Lourens_BE_Coding_Test obj = new Ethan_Lourens_BE_Coding_Test();
        obj.run();
    }
}