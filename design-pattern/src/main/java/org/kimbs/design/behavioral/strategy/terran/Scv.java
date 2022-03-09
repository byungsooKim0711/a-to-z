package org.kimbs.design.behavioral.strategy.terran;

import org.kimbs.design.behavioral.strategy.WorkUnit;

public class Scv extends WorkUnit<TerranBuildStrategy> {

    public Scv(String name) {
        super(name);
    }

    @Override
    public void setBuildStrategy(TerranBuildStrategy buildStrategy) {
        this.buildStrategy = buildStrategy;
    }
}
