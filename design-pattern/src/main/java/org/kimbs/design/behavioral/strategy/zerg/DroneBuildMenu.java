package org.kimbs.design.behavioral.strategy.zerg;

public class DroneBuildMenu {

    public static ZergBuildStrategy hatchery() {
        return new Hatchery();
    }
}
