package lw01.prelab;

// Pengujian tambahan
public class PrelabChecks {
    private static int checks = 0;

    private static void expect(Object expected, Object actual) {
        checks++;
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
    }

    private static void expectInvalid(Runnable operation) {
        checks++;
        try {
            operation.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException.");
    }

    public static void main(String[] args) {
        PrintJob[] jobs = {
            new MonoPrint("P01", 8),
            new ColourPrint("P02", 12),
            new ColourPrint("P10", 10),
            new ColourPrint("P11", 11),
            new MonoPrint("P12", 1)
        };
        String[] summaries = {
            "P01 | Mono | 4000",
            "P02 | Colour | 19000",
            "P10 | Colour | 17000",
            "P11 | Colour | 18000",
            "P12 | Mono | 500"
        };
        int[] charges = {4000, 19000, 17000, 18000, 500};

        for (int i = 0; i < jobs.length; i++) {
            expect(summaries[i], jobs[i].summary());
            // Referensi interface tetap menjalankan method subclass yang sesuai.
            Chargeable chargeable = jobs[i];
            expect(charges[i], chargeable.calculateCharge());
        }

        PrintJob mono = jobs[0];
        PrintJob colour = jobs[1];
        expect("P01", mono.getId());
        expect(8, mono.getPages());
        expect("P02", colour.getId());
        expect(12, colour.getPages());
        expect("Mono", mono.label());
        expect("Colour", colour.label());

        expect(4000, mono.calculateCharge(1));
        expect(12000, mono.calculateCharge(3));
        expect(40000, mono.calculateCharge(10));
        expect(19000, colour.calculateCharge(1));
        expect(38000, colour.calculateCharge(2));
        expect(190000, colour.calculateCharge(10));
        expect(3500, new ColourPrint("C01", 1).calculateCharge());
        expect(15500, new ColourPrint("C09", 9).calculateCharge());
        expect(50000, new MonoPrint("M100", 100).calculateCharge());
        expect(107000, new ColourPrint("C100", 100).calculateCharge());

        expectInvalid(() -> new MonoPrint("M00", 0));
        expectInvalid(() -> new MonoPrint("MNEG", -1));
        expectInvalid(() -> new ColourPrint("C00", 0));
        expectInvalid(() -> new ColourPrint("CNEG", -1));
        expectInvalid(() -> mono.calculateCharge(0));
        expectInvalid(() -> mono.calculateCharge(-1));
        expectInvalid(() -> colour.calculateCharge(0));
        expectInvalid(() -> colour.calculateCharge(-1));

        expect("P01 | Mono | 4000", mono.summary());
        expect("P02 | Colour | 19000", colour.summary());
        System.out.println("PASS: " + checks + " checks.");
    }
}
