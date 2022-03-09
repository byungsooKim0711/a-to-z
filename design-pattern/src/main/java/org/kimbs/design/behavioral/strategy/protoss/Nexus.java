package org.kimbs.design.behavioral.strategy.protoss;

public class Nexus implements ProtossBuildStrategy {

    @Override
    public void build() {
        System.out.println("build nexus...");
    }
}
