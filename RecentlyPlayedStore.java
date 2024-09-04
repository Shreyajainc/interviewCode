import java.util.LinkedHashMap;
import java.util.Map;

public class RecentlyPlayedStore {

    private final int capacity;
    private final LinkedHashMap<String, String> songMap;

    // Constructor to set capacity and initialize the LinkedHashMap with access order
    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.songMap = new LinkedHashMap<String, String>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > RecentlyPlayedStore.this.capacity;
            }
        };
    }

    // Add a song played by the user
    public void playSong(String user, String song) {
        songMap.put(user, song);
    }

    // Display the list of recently played songs
    public void displaySongs() {
        System.out.println("Recently Played Songs: " + songMap);
    }

    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        // Simulating songs played by user
        store.playSong("User1", "S1");
        store.playSong("User1", "S2");
        store.playSong("User1", "S3");
        store.displaySongs(); // Output: {User1=S1, User1=S2, User1=S3}

        store.playSong("User1", "S4");
        store.displaySongs(); // Output: {User1=S2, User1=S3, User1=S4}

        store.playSong("User1", "S2");
        store.displaySongs(); // Output: {User1=S3, User1=S4, User1=S2}

        store.playSong("User1", "S1");
        store.displaySongs(); // Output: {User1=S4, User1=S2, User1=S1}
    }
}
