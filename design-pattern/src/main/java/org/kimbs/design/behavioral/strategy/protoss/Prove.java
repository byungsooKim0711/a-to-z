package org.kimbs.design.behavioral.strategy.protoss;

import org.kimbs.design.behavioral.strategy.WorkUnit;

public class Prove extends WorkUnit<ProtossBuildStrategy> {

    public Prove(String name) {
        super(name);
    }

    @Override
    protected void setBuildStrategy(ProtossBuildStrategy buildStrategy) {
        this.buildStrategy = buildStrategy;
    }
}
