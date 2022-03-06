package org.kimbs.design.creational.singleton;

public class HPPrinter implements Printer {

    private static volatile HPPrinter printer = null;

    private HPPrinter() {

    }

    public static HPPrinter getPrinter() {
        if (printer == null) {
            synchronized (HPPrinter.class) {
                if (printer == null) {
                    printer = new HPPrinter();
                }
            }
        }

        return printer;
    }

    @Override
    public void print(String message) {
        System.out.println("printing... hp printer. message: " + message);
    }
}
