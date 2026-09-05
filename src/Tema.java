import java.awt.Color;
import java.awt.Font;


public class Tema {

    // Fundo do jogo (efeito "parede em reboco")
    public static final Color COR_FUNDO = new Color(235, 224, 200);
    public static final Color COR_FUNDO_LINHA = new Color(216, 203, 176);

    // Cores dos tijolos (blocos) - varia por fileira
    public static final Color[] CORES_TIJOLO = {
            new Color(178, 79, 51),
            new Color(198, 93, 60),
            new Color(160, 64, 40),
            new Color(140, 55, 35),
            new Color(190, 105, 70),
            new Color(150, 70, 45)
    };
    public static final Color COR_ARGAMASSA = new Color(224, 214, 190);

    // Barra do jogador (estilo viga/prancha de madeira)
    public static final Color COR_BARRA = new Color(101, 67, 33);
    public static final Color COR_BARRA_BORDA = new Color(70, 45, 20);
    public static final Color COR_BARRA_DETALHE = new Color(50, 50, 50);

    // Bola (pode ser tratada como uma "pelota de reboco/pedra")
    public static final Color COR_BOLA = new Color(170, 170, 170);

    // Textos / HUD
    public static final Font FONTE_PONTUACAO = new Font("Arial", Font.BOLD, 20);
    public static final Font FONTE_TITULO = new Font("Arial", Font.BOLD, 32);
    public static final Color COR_TEXTO = new Color(60, 40, 20);
}