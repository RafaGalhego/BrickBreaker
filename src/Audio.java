import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Audio {

    public static void tocarSom(String caminhoRecurso) {
        new Thread(() -> {
            try {
                // Busca o arquivo a partir da pasta src
                InputStream audioSrc = Audio.class.getResourceAsStream(caminhoRecurso);
                
                if (audioSrc == null) {
                    System.err.println("ERRO: Arquivo não encontrado no caminho: " + caminhoRecurso);
                    return;
                }

                InputStream bufferedInput = new BufferedInputStream(audioSrc);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInput);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

            } catch (UnsupportedAudioFileException e) {
                System.err.println("ERRO: Formato WAV incompatível! Reencode o arquivo como PCM 16-bit.");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}