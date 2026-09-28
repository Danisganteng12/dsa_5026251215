package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

      
        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"))) {

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] data = line.split(" ");

                transactions.add(data);

                boolean exist = false;

                for (String[] c : customers) {
                    if (c[0].equals(data[0])) {
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    customers.add(new String[]{data[0], "0"});
                }
            }
        }

        // Pindahkan semua transaksi ke queue
        while (!transactions.isEmpty()) {
            queue.add(transactions.removeFirst());
        }

        // Proses transaksi
        while (!queue.isEmpty()) {

            String[] t = queue.poll();

            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            for (String[] c : customers) {

                if (c[0].equals(name)) {

                    int balance = Integer.parseInt(c[1]);

                    if (type.equals("DEPOSIT")) {

                        balance += amount;
                        c[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {

                        if (amount > balance) {
                            failed.push(t);
                        } else {
                            balance -= amount;
                            c[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");

        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");

        while (!failed.empty()) {

            String[] t = failed.pop();

            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}