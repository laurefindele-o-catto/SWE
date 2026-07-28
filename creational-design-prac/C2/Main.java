public class Main {
    public static void main(String[] args) {
        System.out.println("--- Game Engine Startup ---");

        // Client 1: Graphics Engine requests the configuration
        System.out.println("\n[Graphics Engine] Requesting configuration...");
        GameConfig graphicsConfig = GameConfig.getInstance();
        graphicsConfig.displaySettings();

        // Client 2: Audio Engine requests the configuration
        // Notice that the "Loading from disk" message will NOT print again
        System.out.println("\n[Audio Engine] Requesting configuration...");
        GameConfig audioConfig = GameConfig.getInstance();
        audioConfig.displaySettings();

        // Modifying state through one engine reference to show global propagation
        System.out.println("\n[Audio Engine] Lowering master volume to 50...");
        audioConfig.setAudioVolume(50);

        // Client 3: AI Engine requests the configuration
        System.out.println("\n[AI Engine] Requesting configuration...");
        GameConfig aiConfig = GameConfig.getInstance();
        aiConfig.displaySettings(); // Will reflect the volume change made by Audio

        System.out.println("\n--- Integrity Verification ---");
        System.out.println("Graphics Reference Hash: " + graphicsConfig.hashCode());
        System.out.println("Audio Reference Hash:    " + audioConfig.hashCode());
        System.out.println("AI Reference Hash:       " + aiConfig.hashCode());

        if (graphicsConfig == audioConfig && audioConfig == aiConfig) {
            System.out.println("SUCCESS: All engine modules point to the exact same memory instance!");
        } else {
            System.out.println("FAILURE: Configuration synchronization broken!");
        }
    }
}
