package org.kimbs.design.behavioral.strategy.zerg;

import org.kimbs.design.behavioral.strategy.WorkUnit;

public class Drone extends WorkUnit<ZergBuildStrategy> {

    public Drone(String name) {
        super(name);
    }

    @Override
    protected void setBuildStrategy(ZergBuildStrategy buildStrategy) {
        this.buildStrategy = buildStrategy;
    }
}
