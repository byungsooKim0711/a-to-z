package org.kimbs.design.behavioral.strategy.terran;

public class SupplyDepot implements TerranBuildStrategy {

    @Override
    public void build() {
        System.out.println("build supply depot...");
    }
}
