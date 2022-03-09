package org.kimbs.design.behavioral.strategy.protoss;

public class ProveBuildMenu {

    public static ProtossBuildStrategy nexus() {
        return new Nexus();
    }

    public static ProtossBuildStrategy pylon() {
        return new Pylon();
    }
}
