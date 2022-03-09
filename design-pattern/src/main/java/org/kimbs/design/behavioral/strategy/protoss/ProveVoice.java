package org.kimbs.design.behavioral.strategy.protoss;

import org.kimbs.design.behavioral.strategy.VoiceStrategy;

public class ProveVoice implements VoiceStrategy {

    @Override
    public void voice() {
        System.out.println("prove...");
    }
}
