package org.kimbs.design.creational.singleton;

public class MockPrinter implements Printer {

    private MockPrinter() {

    }

    private static class PrinterHolder {
        private static final MockPrinter INSTANCE = new MockPrinter();
    }

    public static MockPrinter getPrinter() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return PrinterHolder.INSTANCE;
    }

    @Override
    public void print(String message) {
        System.out.println("printing... mock printer. message: " + message);
    }
}
