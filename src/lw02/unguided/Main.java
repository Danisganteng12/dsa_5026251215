package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> stocks = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> successList = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedStack = new Stack<>();
        int MAX_BORROW = 2;

        String[] kalkulus = {"Kalkulus", "2"};
        String[] fisika = {"Fisika", "1"};
        String[] statistika = {"Statistika", "2"};
        stocks.add(kalkulus);
        stocks.add(fisika);
        stocks.add(statistika);

        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] request = line.split(" ");
            requests.add(request);
        }
        sc.close();

        for(String[] r : requests){
            String name = r[0];
            boolean found = false;
            for(String[] m : members){
                if(m[0].equals(name)){
                    found = true;
                    break;
                }
            }
            if(!found){
                members.add(new String[]{name, "0"});
            }
        }
        while (!requests.isEmpty()) {
            queue.add(requests.removeFirst());
        }
        while(!queue.isEmpty()){
            String[] r = queue.poll();
            String name = r[0];
            String title = r[1];
            String[] bookFound = null;
            for(String[] b : stocks){
                if(b[0].equals(title)){
                    bookFound = b;
                }
            }
            String[] memberFound = null;
            for(String[] m : members){
                if(m[0].equals(name)){
                    memberFound = m;
                }
            }
            int stockAmount = Integer.parseInt(bookFound[1]);
            int borrowAmount = Integer.parseInt(memberFound[1]);
            if(stockAmount > 0 && borrowAmount < MAX_BORROW){
                stockAmount = stockAmount - 1;
                borrowAmount = borrowAmount + 1;
                bookFound[1] = String.valueOf(stockAmount);
                memberFound[1] = String.valueOf(borrowAmount);
                successList.add(r);
            } else {
                failedStack.push(r);
            }
        }
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] s : successList) {
            System.out.println(s[0] + " " + s[1]);
        }
        System.out.println("\n=== Remaining Book Stock ===");
        for (String[] b : stocks) {
            System.out.println(b[0] + " : " + b[1]);
        }
        System.out.println("\n=== Failed Requests ===");
        while (!failedStack.isEmpty()) {
            String[] f = failedStack.pop();
            System.out.println(f[0] + " " + f[1]);
        }
    }
}