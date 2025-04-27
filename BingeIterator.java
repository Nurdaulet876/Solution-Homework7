import java.util.Iterator;
import java.util.List;

public class BingeIterator implements EpisodeIterator{
    private final Iterator<Season> seasonIterator;
    private EpisodeIterator currentSeasonIterator;

    public BingeIterator(List<Season> seasons) {
        this.seasonIterator = seasons.iterator();
        if (seasonIterator.hasNext()) {
            currentSeasonIterator = seasonIterator.next().createIterator();
        }
    }

    @Override
    public boolean hasNext() {
        while ((currentSeasonIterator == null || !currentSeasonIterator.hasNext()) && seasonIterator.hasNext()) {
            currentSeasonIterator = seasonIterator.next().createIterator();
        }
        return currentSeasonIterator != null && currentSeasonIterator.hasNext();
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more episodes");
        }
        return currentSeasonIterator.next();
    }
}
