package org.kimbs.design.behavioral.iterator;

public class Brawler {

    private String name;
    private BrawlerType type;

    public Brawler(String name, BrawlerType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrawlerType getType() {
        return type;
    }

    public void setType(BrawlerType type) {
        this.type = type;
    }
}
