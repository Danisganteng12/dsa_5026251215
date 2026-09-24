package lw02.prelab;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Scanner file = new Scanner(new File("src/lw02/prelab/transactions.txt"));

        while (file.hasNextLine()) {

            String line = file.nextLine();
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

        file.close();


        Queue<String[]> queue = new LinkedList<>();

        while (!transactions.isEmpty()) {
            queue.add(transactions.removeFirst());
        }


        Stack<String[]> failed = new Stack<>();


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

                    } 
                    else if (type.equals("WITHDRAW")) {


                        if (amount > balance) {

                            failed.push(t);

                        } 
                        else {

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

            System.out.println(
                t[0] + " " +
                t[1] + " " +
                t[2]
            );
        }
    }
}
