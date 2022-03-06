package org.kimbs.design.creational.singleton;

public enum LGPrinter implements Printer {

    INSTANCE;

    @Override
    public void print(String message) {
        System.out.println("printing... LG printer. message: " + message);
    }
}
