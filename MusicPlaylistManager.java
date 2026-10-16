import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// ==========================================
// CONCEPT: OOPs (Abstraction & Inheritance)
// Abstract class defines a base template for audio items
// ==========================================
abstract class AudioItem {
    // CONCEPT: Datatypes (String) & Encapsulation (protected fields)
    protected String title;
    protected String artist;

    // CONCEPT: Constructors & Methods
    public AudioItem(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    // CONCEPT: OOPs (Abstraction & Polymorphism - Abstract Method)
    public abstract String getDetails();
}

// ==========================================
// CONCEPT: OOPs (Inheritance & Encapsulation)
// Song inherits from AudioItem
// ==========================================
class Song extends AudioItem {
    // CONCEPT: Datatypes (primitive int, String reference) & Encapsulation (private fields)
    private int id;
    private String genre;
    private int playCount;

    // CONCEPT: Constructor Chaining using 'this'
    // Calls the 5-parameter constructor from a 3-parameter constructor
    public Song(int id, String title, String artist) {
        this(id, title, artist, "General", 0); // CONCEPT: Constructor Chaining (this)
    }

    // Overloaded Constructor
    public Song(int id, String title, String artist, String genre, int playCount) {
        super(title, artist); // Calls parent constructor
        this.id = id;
        this.genre = genre;
        this.playCount = playCount;
    }

    // CONCEPT: Encapsulation (Getters and Setters)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getPlayCount() { return playCount; }

    // CONCEPT: Methods & Operators (Increment operator ++)
    public void incrementPlayCount() { 
        this.playCount++; // CONCEPT: Operators (Arithmetic / Unary Increment)
    }

    // CONCEPT: OOPs (Polymorphism - Method Overriding) & String Manipulation
    @Override
    public String getDetails() {
        // CONCEPT: String Concatenation Operator (+)
        return title + "(" + artist + "," + genre + ")";
    }
}

// ==========================================
// CONCEPT: Exceptions (Custom Exception Class)
// ==========================================
class InvalidSongException extends Exception {
    public InvalidSongException(String message) {
        super(message);
    }
}

// ==========================================
// MAIN CLASS: Music Playlist and Listening Stats Manager
// ==========================================
public class MusicPlaylistManager {

    // CONCEPT: Collections (ArrayList)
    private static List<Song> playlist = new ArrayList<>();

    // CONCEPT: 2D Array (Rows = 5 Songs, Columns = 7 Days of Week)
    private static int[][] listeningMatrix = new int[5][7];

    // CONCEPT: Scanner class for reading input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize sample playlist & matrix
        initializeData();

        // CONCEPT: Datatypes (boolean flag)
        boolean running = true;

        // CONCEPT: Iterative Statements (while loop)
        while (running) {
            // Display Menu (Matching your output format)
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Playlist");
            System.out.println("2. Log a Song Play");
            System.out.println("3. View Listening Stats Matrix");
            System.out.println("4. View Top Played Song");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            // CONCEPT: Exceptions (try-catch block)
            try {
                // CONCEPT: Scanner Class methods
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                // CONCEPT: Conditional Statements (switch-case)
                switch (choice) {
                    case 1:
                        viewPlaylist();
                        break;
                    case 2:
                        logSongPlay();
                        break;
                    case 3:
                        viewListeningStatsMatrix();
                        break;
                    case 4:
                        viewTopPlayedSong();
                        break;
                    case 5:
                        // CONCEPT: File I/O
                        saveDataToFile();
                        System.out.println("Exiting Playlist Manager. Goodbye!");
                        running = false;
                        break;
                    default:
                        // CONCEPT: Conditional Statements (handling invalid range)
                        System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                // CONCEPT: Exception Handling for non-integer inputs
                System.out.println("Error: Please enter a valid numerical choice.");
                scanner.nextLine(); // Clear the bad input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // ==========================================
    // METHOD: Initialize default data
    // ==========================================
    private static void initializeData() {
        // CONCEPT: Classes and Objects (Creating Song objects)
        // Values match your output requirements
        playlist.add(new Song(0, "OMG Daddy", "Thaman S", "Western pop and funk-style choruses", 12));
        playlist.add(new Song(1, "Mental Madhilo", "A.R. Rahman", "Classical Carnatic fusion", 25));
        playlist.add(new Song(2, "Jala Jala patham", "DSP", "Romantic", 18));
        playlist.add(new Song(3, "Bum Ba Diga Diga", "Anirudh Ravichander", "Upbeat and energetic", 30));
        playlist.add(new Song(4, "PavazaMalli", "Sai Abhyankar", "Melodic and soulful", 22));

        // CONCEPT: 2D Arrays & Iterative Statements (Nested for-loops) & Operators
        for (int i = 0; i < listeningMatrix.length; i++) {
            for (int j = 0; j < listeningMatrix[i].length; j++) {
                listeningMatrix[i][j] = (i + 1) * (j + 1) + 2; // Matrix population using arithmetic operators
            }
        }
    }

    // ==========================================
    // METHOD: Option 1 - View Playlist
    // CONCEPT: Iterative Statements (For-each loop)
    // ==========================================
    private static void viewPlaylist() {
        System.out.println("\n--- CURRENT PLAYLIST ---");
        
        // CONCEPT: Iterative Statements (for-each loop over Collections)
        for (Song song : playlist) {
            // Outputs format: ID x: Title(Artist,Genre)
            System.out.println("ID " + song.getId() + ": " + song.getDetails());
        }
    }

    // ==========================================
    // METHOD: Option 2 - Log Song Play
    // CONCEPT: Searching, Custom Exceptions, 2D Array Update
    // ==========================================
    private static void logSongPlay() {
        System.out.print("Enter Song ID to log play (0-4): ");
        try {
            int id = scanner.nextInt();

            // CONCEPT: Conditional Statements (if-else with relational & logical operators)
            if (id < 0 || id >= playlist.size()) {
                // CONCEPT: Exceptions (Throwing custom exception)
                throw new InvalidSongException("Song ID " + id + " does not exist in playlist!");
            }

            // CONCEPT: Searching (Linear Search through List)
            Song selectedSong = null;
            for (Song s : playlist) {
                if (s.getId() == id) { // CONCEPT: Operators (Relational ==)
                    selectedSong = s;
                    break;
                }
            }

            if (selectedSong != null) {
                selectedSong.incrementPlayCount();

                System.out.print("Enter Day index to log (0=Mon, 1=Tue, ..., 6=Sun): ");
                int day = scanner.nextInt();

                // CONCEPT: Conditional Statements & 2D Array Modification
                if (day >= 0 && day < 7) {
                    listeningMatrix[id][day]++; // CONCEPT: 2D Array indexing & Increment operator
                    System.out.println("Successfully logged play for: " + selectedSong.getTitle());
                } else {
                    System.out.println("Invalid day index. Play count updated in general stats only.");
                }
            }
        } catch (InvalidSongException e) {
            System.out.println("Custom Exception Caught: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Input must be an integer.");
            scanner.nextLine();
        }
    }

    // ==========================================
    // METHOD: Option 3 - View Listening Matrix
    // CONCEPT: 1D Array, 2D Array & Nested Loops
    // ==========================================
    private static void viewListeningStatsMatrix() {
        System.out.println("\n--- LISTENING STATS MATRIX (Songs x Days) ---");
        
        // CONCEPT: 1D Array
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        // Header print
        System.out.print("ID\t");
        // CONCEPT: Iterative Statements (for-each loop on 1D Array)
        for (String day : days) {
            System.out.print(day + "\t");
        }
        System.out.println();

        // CONCEPT: 2D Array Traversal using standard nested for loops
        for (int i = 0; i < listeningMatrix.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < listeningMatrix[i].length; j++) {
                System.out.print(listeningMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // ==========================================
    // METHOD: Option 4 - View Top Played Song
    // CONCEPT: Library Algorithms, Lambdas, Streams, Sorting
    // ==========================================
    private static void viewTopPlayedSong() {
        System.out.println("\n--- TOP PLAYED SONG ---");

        // CONCEPT: Library Algorithms (Collections.max) & Lambdas (Comparator lambda)
        Song topSong = Collections.max(playlist, (s1, s2) -> Integer.compare(s1.getPlayCount(), s2.getPlayCount()));

        // CONCEPT: Streams, Lambdas, and Sorting
        List<Song> sortedList = playlist.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getPlayCount(), s1.getPlayCount())) // CONCEPT: Lambdas & Sorting
                .collect(Collectors.toList());

        System.out.println("Sorted Playlist by Popularity (Streams & Lambdas):");
        
        // CONCEPT: Streams & Lambdas (forEach)
        sortedList.forEach(s -> System.out.println(" - " + s.getTitle() + " (" + s.getPlayCount() + " plays)"));

        System.out.println("\nTop Song: " + topSong.getTitle() + " by " + topSong.getArtist() +
                " [" + topSong.getPlayCount() + " plays]");
    }

    // ==========================================
    // METHOD: File I/O
    // CONCEPT: File I/O (FileWriter, PrintWriter) & Exception Handling
    // ==========================================
    private static void saveDataToFile() {
        // CONCEPT: File I/O (Try-with-resources for auto-closing files)
        try (PrintWriter writer = new PrintWriter(new FileWriter("playlist_stats.txt"))) {
            writer.println("--- MUSIC PLAYLIST STATS EXPORT ---");
            
            // CONCEPT: Iterative Statements (for-each)
            for (Song song : playlist) {
                // CONCEPT: String formatting
                writer.println("ID " + song.getId() + " | " + song.getTitle() + " | Plays: " + song.getPlayCount());
            }
            System.out.println("Stats saved to 'playlist_stats.txt'.");
        } catch (IOException e) {
            // CONCEPT: Exception handling
            System.out.println("File I/O Exception occurred: " + e.getMessage());
        }
    }
}