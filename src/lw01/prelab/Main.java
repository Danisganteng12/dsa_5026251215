package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("jobs.txt"))) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                // Jenis input hanya dipakai untuk menentukan objek yang dibuat
                if (type.equals("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equals("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                } else {
                    throw new IllegalArgumentException("Unknown job type: " + type);
                }
            }
        }

        // Satu loop dengan referensi parent menunjukkan runtime polymorphism
        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}
