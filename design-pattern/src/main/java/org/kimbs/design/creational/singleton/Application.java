package org.kimbs.design.creational.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        /** ------------------------- */

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                Printer mockPrinter = MockPrinter.getPrinter();
                System.out.println("** mock printer. name: " + mockPrinter);
            });
        }

        MockPrinter mockPrinter = MockPrinter.getPrinter();
        mockPrinter.print(mockPrinter.toString());

        /** ------------------------- */

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                Printer samsungPrinter = SamsungPrinter.getPrinter();
                System.out.println("** samsung printer. name: " + samsungPrinter);
            });
        }

        Printer samsungPrinter = SamsungPrinter.getPrinter();
        samsungPrinter.print(samsungPrinter.toString());

        /** ------------------------- */

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                Printer lgPrinter = LGPrinter.INSTANCE;
                System.out.println("** lg printer. name: " + lgPrinter.hashCode() + "");
            });
        }

        Printer lgPrinter = LGPrinter.INSTANCE;
        lgPrinter.print(lgPrinter.hashCode() + "");

        /** ------------------------- */
        for (int i=0; i<10; i++) {
            executor.submit(() -> {
                Printer hpPrinter = HPPrinter.getPrinter();
                System.out.println("hp printer. name: " + hpPrinter);
            });
        }

        Printer hpPrinter = HPPrinter.getPrinter();
        hpPrinter.print(hpPrinter.toString());


        executor.shutdown();
    }
}
