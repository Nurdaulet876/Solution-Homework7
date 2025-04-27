public class IteratorPatternDemo {
    public static void main(String[] args) {
        Series series = new Series();

        Season season1 = new Season();
        season1.addEpisode(new Episode("S1E1", 1200));
        season1.addEpisode(new Episode("S1E2", 1300));
        series.addSeason(season1);

        Season season2 = new Season();
        season2.addEpisode(new Episode("S2E1", 1400));
        season2.addEpisode(new Episode("S2E2", 1500));
        series.addSeason(season2);

        System.out.println("Normal Order:");
        EpisodeIterator normal = season1.createIterator();
        while (normal.hasNext()) {
            System.out.println(normal.next());
        }

        System.out.println("\nReverse Order:");
        EpisodeIterator reverse = season1.createReverseIterator();
        while (reverse.hasNext()) {
            System.out.println(reverse.next());
        }

        System.out.println("\nShuffle Order:");
        EpisodeIterator shuffle = season1.createShuffleIterator();
        while (shuffle.hasNext()) {
            System.out.println(shuffle.next());
        }

        System.out.println("\nBinge Watching Whole Series:");
        EpisodeIterator binge = series.createBingeIterator();
        while (binge.hasNext()) {
            System.out.println(binge.next());
        }
    }
}
