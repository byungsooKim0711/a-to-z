package org.kimbs.design.behavioral.strategy.terran;

public class CommandCenter implements TerranBuildStrategy {

    @Override
    public void build() {
        System.out.println("build command center...");
    }
}
