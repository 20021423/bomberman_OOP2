package uet.oop.bomberman;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class GameSound {
    protected static AudioPlayer player = AudioPlayer.player;
    private static boolean stop_music = false;

    public static final String MENU = "menu.wav";
    public static final String PLAYGAME = "playgame.wav";
    public static final String BOMB = "newbomb.wav";
    public static final String BOMBER_DIE = "bomber_die.wav";
    public static final String ENEMY_DIE = "enemy_die.wav";
    public static final String BONG_BANG = "bomb_bang.wav";
    public static final String ITEM = "item.wav";
    public static final String WIN = "win.wav";
    public static final String LOSE = "lose.wav";

    public static String SOUND_TEMPLATE = "/sound/%s";

    public static void playMusic(String filePath) {
        InputStream music;
        try {
            music = GameSound.class.getResourceAsStream(String.format(SOUND_TEMPLATE, filePath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);

        } catch (Exception e) {
            System.out.println("File path was not found!");
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
