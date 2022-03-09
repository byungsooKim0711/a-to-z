package org.kimbs.design.behavioral.strategy.terran;

public class Barracks implements TerranBuildStrategy {

    @Override
    public void build() {
        System.out.println("build barracks...");
    }
}
