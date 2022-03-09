package org.kimbs.design.behavioral.strategy.terran;

import org.kimbs.design.behavioral.strategy.VoiceStrategy;

import java.util.Random;

public class ScvVoice implements VoiceStrategy {

    private static Random random = new Random();

    private static String[] voiceList = {
            "Yes sir",
            "Orders, captain",
            "I read you",
            "Affirmative",
            "Roger that",
            "Orders received"
    };

    @Override
    public void voice() {
        int index = Math.abs(random.nextInt()) % voiceList.length;
        System.out.println(voiceList[index]);
    }
}
