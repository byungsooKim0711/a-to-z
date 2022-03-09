package org.kimbs.design.behavioral.strategy;

import org.kimbs.design.behavioral.strategy.protoss.ProtossBuildStrategy;
import org.kimbs.design.behavioral.strategy.protoss.Prove;
import org.kimbs.design.behavioral.strategy.protoss.ProveBuildMenu;
import org.kimbs.design.behavioral.strategy.protoss.ProveVoice;
import org.kimbs.design.behavioral.strategy.terran.Scv;
import org.kimbs.design.behavioral.strategy.terran.ScvBuildMenu;
import org.kimbs.design.behavioral.strategy.terran.ScvVoice;
import org.kimbs.design.behavioral.strategy.terran.TerranBuildStrategy;
import org.kimbs.design.behavioral.strategy.zerg.Drone;
import org.kimbs.design.behavioral.strategy.zerg.DroneBuildMenu;
import org.kimbs.design.behavioral.strategy.zerg.ZergBuildStrategy;

public class Application {

    public static void main(String[] args) {
        System.out.println("---------------------------");

        WorkUnit<TerranBuildStrategy> scv = new Scv("scv");
        scv.setVoiceStrategy(new ScvVoice());
        scv.voice();
        scv.build(ScvBuildMenu.commandCenter());
        scv.build(ScvBuildMenu.supplyDepot());
        scv.build(ScvBuildMenu.barracks());

        System.out.println("---------------------------");

        WorkUnit<ProtossBuildStrategy> prove = new Prove("prove");
        prove.setVoiceStrategy(new ProveVoice());
        prove.voice();
        prove.build(ProveBuildMenu.nexus());
        prove.build(ProveBuildMenu.pylon());

        System.out.println("---------------------------");

        WorkUnit<ZergBuildStrategy> drone = new Drone("drone");
        drone.build(DroneBuildMenu.hatchery());

        System.out.println("---------------------------");
    }
}
