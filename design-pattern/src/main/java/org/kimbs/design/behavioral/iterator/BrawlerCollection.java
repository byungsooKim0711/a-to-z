package org.kimbs.design.behavioral.iterator;

public interface BrawlerCollection {

    boolean addBrawler(Brawler brawler);
    boolean removeBrawler(Brawler brawler);
    Iterator<Brawler> iterator(BrawlerType type);
}
