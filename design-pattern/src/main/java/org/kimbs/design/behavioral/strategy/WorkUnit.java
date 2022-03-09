package org.kimbs.design.behavioral.strategy;

public abstract class WorkUnit<B extends BuildStrategy> {

    private final String name;

    private WorkStrategy workStrategy;
    private VoiceStrategy voiceStrategy;

    public WorkUnit(String name) {
        this.name = name;
    }

    public void setWorkStrategy(WorkStrategy workStrategy) {
        this.workStrategy = workStrategy;
    }

    public void setVoiceStrategy(VoiceStrategy voiceStrategy) {
        this.voiceStrategy = voiceStrategy;
    }

    public void voice() {
        this.voiceStrategy.voice();
    }

    public void work() {
        this.workStrategy.work();
    }

    public void build(B buildStrategy) {
        buildStrategy.build();
    }
}
