package org.kimbs.design.behavioral.strategy.terran;

public class ScvBuildMenu {

    public static TerranBuildStrategy commandCenter() {
        return new CommandCenter();
    }

    public static TerranBuildStrategy supplyDepot() {
        return new SupplyDepot();
    }

    public static TerranBuildStrategy barracks() {
        return new Barracks();
    }
}
