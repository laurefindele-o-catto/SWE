public class GameConfig {
    // 1. Private static field to hold the single instance
    private static GameConfig instance;

    // Configuration state variables
    private String resolution;
    private int audioVolume;
    private String difficultyLevel;

    // 2. Private constructor prevents direct "new GameConfig()" calls
    private GameConfig() {
        // Simulating the expensive disk-loading operation
        System.out.println(">> Loading settings from disk (Expensive Operation!)...");
        this.resolution = "1920x1080";
        this.audioVolume = 80;
        this.difficultyLevel = "Hard";
    }

    // 3. Public static method providing global access point (Lazy Initialization)
    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    // Getters and setters to represent state mutation
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public int getAudioVolume() { return audioVolume; }
    public void setAudioVolume(int volume) { this.audioVolume = volume; }

    public String getDifficultyLevel() { return difficultyLevel; }

    public void displaySettings() {
        System.out.println("Current Config -> Resolution: " + resolution + 
                           ", Volume: " + audioVolume + 
                           ", Difficulty: " + difficultyLevel);
    }
}
