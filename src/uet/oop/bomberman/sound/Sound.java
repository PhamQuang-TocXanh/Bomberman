package uet.oop.bomberman.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Sound {
    private static final ClassLoader c = ClassLoader.getSystemClassLoader();
    private static Clip clip;

    public static String BOMB_BANG = "sound/bomb_bang.wav";
    public static String BOMBER_DIE = "sound/bomber_die.wav";
    public static String ITEM = "sound/item.wav";
    public static String ENEMY_DIE = "sound/enemy_die.wav";
    public static String WIN = "sound/win.wav";
    public static String BACKGROUND = "sound/background.wav";
    public static String CLICKY = "sound/clicky.wav";

    public void playMusicEffect(String music) {
        try {
            Clip clip1 = loadMusic(music);
            if (clip1 == null) throw new Exception("can't find " + music);
            clip1.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Music error: " + e.getMessage());
        }
    }

    public static void playBackgroundMusic() {
        try {
            if (clip == null) clip = loadMusic(BACKGROUND);
            if (clip == null) throw new Exception("can't find " + BACKGROUND);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Music error: " + e.getMessage());
        }
    }

    public static void stopBackgroundMusic() {
        if (clip != null) clip.stop();
    }

    private static Clip loadMusic(String music) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(Objects.requireNonNull(c.getResource(music)).getFile());
        if (file.exists()) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip1 = AudioSystem.getClip();
            clip1.open(audioInputStream);
            return clip1;
        }
        return null;
    }

}