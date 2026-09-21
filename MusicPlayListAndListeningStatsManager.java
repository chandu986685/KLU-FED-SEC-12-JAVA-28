import java.util.Scanner;

public class MusicPlayListManager {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

        // Project Configuration Constants
        final int MAX_SONGS = 5;
        final int MAX_USERS = 3;

        // 1D Array to store song titles
        String[] playlist = new String[MAX_SONGS];
        playlist[0] = "OMG Daddy(Thaman S,Western pop and funk-style choruses)";
        playlist[1] = "Mental Madhilo(A.R. Rahman,Classical Carnatic fusion)";
        playlist[2] = "Jala Jala patham(DSP,Romantic)";
        playlist[3] = "Bum Ba Diga Diga(Anirudh Ravichander,Upbeat and energetic)";
        playlist[4] = "PavazaMalli(Sai Abhyankar,Melodic and soulful)";

        // 2D Array to store listening stats: Rows = Users, Columns = Song Play Counts
        int[][] listeningStats = new int[MAX_USERS][MAX_SONGS];

        // Variables and Data Types
        int choice;
        boolean running = true;

        System.out.println("=== Welcome to the Music Playlist & Listening Stats Manager ===");

        // Main Program Loop
        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-5): ");
            
            // Conditional statement to validate integer input
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            // Switch Case for Menu Selection
            switch (choice) {
                case 1:
                    displayPlaylist(playlist);
                    break;
                case 2:
                    logSongPlay(scanner, playlist, listeningStats, MAX_USERS, MAX_SONGS);
                    break;
                case 3:
                    displayStats(playlist, listeningStats, MAX_USERS, MAX_SONGS);
                    break;
                case 4:
                    displayTopSong(playlist, listeningStats, MAX_USERS, MAX_SONGS);
                    break;
                case 5:
                    // Operator utilization and exit flag update
                    running = false;
                    System.out.println("Exiting the application. Thank you for using the Manager!");
                    break;
                default:
                    System.out.println("Invalid option! Please select a valid menu number.");
            }
        }
        }
    }

    // Method 1: Display the current menu options
    public static void displayMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. View Playlist");
        System.out.println("2. Log a Song Play");
        System.out.println("3. View Listening Stats Matrix");
        System.out.println("4. View Top Played Song");
        System.out.println("5. Exit");
    }

    // Method 2: Display the 1D Array contents (Playlist)
    public static void displayPlaylist(String[] playlist) {
        System.out.println("\n--- CURRENT PLAYLIST ---");
        // Loop to traverse 1D array
        for (int i = 0; i < playlist.length; i++) {
            System.out.println("ID " + i + ": " + playlist[i]);
        }
    }

    // Method 3: Log a play count into the 2D Array using Operators & Conditional Statements
    public static void logSongPlay(Scanner sc, String[] playlist, int[][] stats, int maxUsers, int maxSongs) {
        System.out.print("Enter User ID (0 to " + (maxUsers - 1) + "): ");
        int userId = sc.nextInt();
        
        System.out.print("Enter Song ID (0 to " + (maxSongs - 1) + "): ");
        int songId = sc.nextInt();

        // Conditional statement for bounds checking
        if (userId >= 0 && userId < maxUsers && songId >= 0 && songId < maxSongs) {
            // Arithmetic assignment operator to update listening counts
            stats[userId][songId] += 1; 
            System.out.println("Successfully logged 1 play for '" + playlist[songId] + "' by User " + userId);
        } else {
            System.out.println("Error: Invalid User ID or Song ID selection!");
        }
    }

    // Method 4: Display the 2D Array data (Grid matrix representation)
    public static void displayStats(String[] playlist, int[][] stats, int maxUsers, int maxSongs) {
        System.out.println("\n--- LISTENING STATS MATRIX (Users vs Songs) ---");
        
        // Print header row
        System.out.print("User ID\t\t");
        for (int i = 0; i < maxSongs; i++) {
            System.out.print("[" + playlist[i] + "]\t");
        }
        System.out.println();

        // Nested Loops to traverse the 2D Array
        for (int i = 0; i < maxUsers; i++) {
            System.out.print("User " + i + "\t\t");
            for (int j = 0; j < maxSongs; j++) {
                System.out.print(stats[i][j] + "\t\t");
            }
            System.out.println();
        }
    }

    // Method 5: Calculate aggregated statistics from the 2D Array
    public static void displayTopSong(String[] playlist, int[][] stats, int maxUsers, int maxSongs) {
        int maxPlays = -1;
        int topSongIndex = 0;

        // Outer loop column-by-column to total up the stats for each specific song
        for (int col = 0; col < maxSongs; col++) {
            int currentSongTotal = 0;
            for (int row = 0; row < maxUsers; row++) {
                currentSongTotal += stats[row][col]; // Using arithmetic operators
            }

            // Conditional block to identify the highest play count
            if (currentSongTotal > maxPlays) {
                maxPlays = currentSongTotal;
                topSongIndex = col;
            }
        }

        System.out.println("\n--- TOP PLAYED SONG ---");
        if (maxPlays > 0) {
            System.out.println("The most played track is '" + playlist[topSongIndex] + "' with an overall total of " + maxPlays + " plays!");
        } else {
            System.out.println("No play history logged yet. Try logging a song play first!");
        }
    }
}
