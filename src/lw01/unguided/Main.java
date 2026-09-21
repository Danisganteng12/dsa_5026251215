package lw01.unguided;

    import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) throws FileNotFoundException {

            try (Scanner scanner = new Scanner(new File("rentals.txt"))) {
            int n = scanner.nextInt();
            Rental[] rentals = new Rental[n];
            int[] units = new int[n];

            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                units[i] = scanner.nextInt();

                if (type.equals("LAPTOP")) {
                    rentals[i] = new LaptopRental(id, days);
                } else if (type.equals("PROJECTOR")) {
                    rentals[i] = new ProjectorRental(id, days);
                }
            }

            
            for (int i = 0; i < n; i++) {
                Rental r = rentals[i];
                System.out.println(r.getId() + " | " + r.label() + " | " + r.calculateCharge(units[i]));
                }
            } 
        }
    }