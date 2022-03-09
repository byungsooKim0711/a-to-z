package org.kimbs.design.behavioral.strategy.zerg;

public class Hatchery implements ZergBuildStrategy {

    @Override
    public void build() {
        System.out.println("build hatchery...");
    }
}
