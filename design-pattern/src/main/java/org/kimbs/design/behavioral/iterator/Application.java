package org.kimbs.design.behavioral.iterator;

public class Application {

    public static void main(String[] args) {
        BrawlerCollection brawlers = new BrawlerCollectionImpl();
        brawlers.addBrawler(new Brawler("Bull", BrawlerType.TANKER));
        brawlers.addBrawler(new Brawler("Max", BrawlerType.SUPPORTER));
        brawlers.addBrawler(new Brawler("Darryl", BrawlerType.HYBRID));
        brawlers.addBrawler(new Brawler("Colt", BrawlerType.DEALER));
        brawlers.addBrawler(new Brawler("Barley", BrawlerType.DEALER));
        brawlers.addBrawler(new Brawler("Byron", BrawlerType.SUPPORTER));
        brawlers.addBrawler(new Brawler("Pam", BrawlerType.SUPPORTER));
        brawlers.addBrawler(new Brawler("Gale", BrawlerType.HYBRID));
        brawlers.addBrawler(new Brawler("Stu", BrawlerType.DEALER));
        brawlers.addBrawler(new Brawler("Surge", BrawlerType.DEALER));

        Iterator<Brawler> supporterBrawlerIterator = brawlers.iterator(BrawlerType.SUPPORTER);

        while (supporterBrawlerIterator.hasNext()) {
            Brawler brawler = supporterBrawlerIterator.next();
            System.out.printf("name: %s, type: %s\n", brawler.getName(), brawler.getType());
        }

        Iterator<Brawler> dealerBrawlerIterator = brawlers.iterator(BrawlerType.DEALER);
        while (dealerBrawlerIterator.hasNext()) {
            Brawler brawler = dealerBrawlerIterator.next();
            System.out.printf("name: %s, type: %s\n", brawler.getName(), brawler.getType());
        }
    }
}
