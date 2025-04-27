import java.util.*;

public class ShuffleSeasonIterator implements EpisodeIterator{
    private final List<Episode> shuffledEpisodes;
    private int currentIndex = 0;

    public ShuffleSeasonIterator(List<Episode> episodes, long seed) {
        this.shuffledEpisodes = new ArrayList<>(episodes);
        Collections.shuffle(shuffledEpisodes, new Random(seed));
    }

    @Override
    public boolean hasNext() {
        return currentIndex < shuffledEpisodes.size();
    }

    @Override
    public Episode next() {
        return shuffledEpisodes.get(currentIndex++);
    }
}