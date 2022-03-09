package org.kimbs.design.behavioral.strategy.protoss;

public class Pylon implements ProtossBuildStrategy {

    @Override
    public void build() {
        System.out.println("build pylon...");
    }
}
