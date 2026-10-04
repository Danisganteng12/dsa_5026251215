package lw03.prelab;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    static void problem1() {
        List<String> playlist = new ArrayList<>();
        Scanner sc = new Scanner(Main.class.getResourceAsStream("Playlist.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (line.startsWith("ADD ")) {
                String song = line.substring(4);
                playlist.add(song);
            } else if (line.startsWith("INSERT ")) {
                String[] parts = line.split(" ", 3);
                int index = Integer.parseInt(parts[1]);
                String song = parts[2];
                playlist.add(index, song);
            } else if (line.startsWith("REMOVE ")) {
                String song = line.substring(7);
                playlist.remove(song);
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicate = 0;
        Scanner sc = new Scanner(Main.class.getResourceAsStream("Participants.txt"));

        while (sc.hasNextLine()) {
            String name = sc.nextLine();
            if (participants.contains(name)) {
                duplicate++;
            } else {
                participants.add(name);
            }
        }
        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicate);
    }

    static void problem3() {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner sc = new Scanner(Main.class.getResourceAsStream("Inventory.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + quantity);
                } else {
                    stock.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= quantity) {
                    stock.put(product, stock.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        System.out.println("===== Problem 3 =====");
        for (String product : stock.keySet()) {
            System.out.println(product + ": " + stock.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}