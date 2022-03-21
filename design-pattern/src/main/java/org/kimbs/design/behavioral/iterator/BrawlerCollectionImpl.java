package org.kimbs.design.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class BrawlerCollectionImpl implements BrawlerCollection {

    private final List<Brawler> brawlers;

    public BrawlerCollectionImpl() {
        this.brawlers = new ArrayList<>();
    }

    @Override
    public boolean addBrawler(Brawler brawler) {
        return this.brawlers.add(brawler);
    }

    @Override
    public boolean removeBrawler(Brawler brawler) {
        return this.brawlers.remove(brawler);
    }

    @Override
    public Iterator<Brawler> iterator(BrawlerType type) {
        return new BrawlerIterator(type, this.brawlers);
    }

    private static class BrawlerIterator implements Iterator<Brawler> {

        private BrawlerType type;
        private List<Brawler> brawlers;
        private int position;

        public BrawlerIterator(BrawlerType type, List<Brawler> brawlers) {
            this.type = type;
            this.brawlers = brawlers;
        }

        @Override
        public boolean hasNext() {
            while (position < brawlers.size()) {
                Brawler b = brawlers.get(position);
                if (b.getType() == type) {
                    return true;
                } else {
                    position++;
                }
            }
            return false;
        }

        @Override
        public Brawler next() {
            return brawlers.get(position++);
        }
    }
}
