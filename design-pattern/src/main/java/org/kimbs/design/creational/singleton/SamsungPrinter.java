package org.kimbs.design.creational.singleton;

public class SamsungPrinter implements Printer {

    private static Printer printer = null;

    private SamsungPrinter() {

    }

    public synchronized static Printer getPrinter() {
        if (printer == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printer = new SamsungPrinter();
        }
        return printer;
    }

    @Override
    public void print(String message) {
        System.out.println("printing... samsung printer. message: " + message);
    }
}
