package uet.oop.bomberman;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class GameSound {

    public static final String inGameSound = "playgame.wav";
    public static final String BOOM = "newbomb.wav";
    public static final String DEATH1 = "bomber_die.wav";
    public static final String DEATH2 = "enemy_die.wav";
    public static final String BANG = "bomb_bang.wav";
    public static final String ITEM = "item.wav";
    public static final String WIN = "win.wav";

    public static String SOUND_TEMPLATE = "/sound/%s";

    public static void playMusic(String filePath) {
        InputStream music;
        try {
            music = GameSound.class.getResourceAsStream(String.format(SOUND_TEMPLATE, filePath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);

        } catch (Exception e) {
            System.out.println("Error in file path!");
        }
    }

    public static Clip loopMusic(String nameSound){
        Clip clip = null;
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(GameSound.class.getResource("/sound/" + nameSound));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return clip;
    }
}
