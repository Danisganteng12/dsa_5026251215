package lw03.unguided;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner file = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        Map<String, Integer> enrollment = new HashMap<String, Integer>();
        Set<String> uniqueCodes = new HashSet<String>();
        List<String> courseOrder = new ArrayList<String>();
        List<String> checkResults = new ArrayList<String>();

        int rejected = 0;

        while (file.hasNext()) {
            String operation = file.next();
            String code = file.next();

            if (!uniqueCodes.contains(code)) {
                uniqueCodes.add(code);
                courseOrder.add(code);
            }

            if (operation.equals("REGISTER")) {
                int count = file.nextInt();

                if (count <= 0) {
                    rejected++;
                } else if (enrollment.containsKey(code)) {
                    enrollment.put(code, enrollment.get(code) + count);
                } else {
                    enrollment.put(code, count);
                }

            } else if (operation.equals("WITHDRAW")) {
                int count = file.nextInt();

                if (count <= 0) {
                    rejected++;
                } else if (!enrollment.containsKey(code)) {
                    rejected++;
                } else if (enrollment.get(code) < count) {
                    rejected++;
                } else {
                    enrollment.put(code, enrollment.get(code) - count);
                }

            } else if (operation.equals("CHECK")) {
                if (enrollment.containsKey(code)) {
                    checkResults.add(code + ": " + enrollment.get(code) + " students");
                } else {
                    checkResults.add(code + ": Not found");
                }
            }
        }

        file.close();

        System.out.println("===== Enrollment Checks =====");
        for (int i = 0; i < checkResults.size(); i++) {
            System.out.println(checkResults.get(i));
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");
        for (int i = 0; i < courseOrder.size(); i++) {
            String code = courseOrder.get(i);

            if (enrollment.containsKey(code)) {
                System.out.println(code + ": " + enrollment.get(code) + " students");
            }
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejected);
    }
}
