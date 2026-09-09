import java.awt.Color;
import java.awt.Font;

public class Tema { //Construindo a classe tema que define as cores do jogo

    //Fundo do jogo
    public static final Color FUNDO =
        new Color(58, 54, 48);

    //Linhas sutis no fundo, tipo textura de parede
    public static final Color FUNDO_LINHA =
        new Color(72, 67, 59);

    //Cor da barra
    public static final Color BARRA =
        new Color(101, 67, 33);

    //Borda da barra
    public static final Color BORDA_BARRA =
        new Color(70, 45, 20);

    //Detalhe da barra
    public static final Color DETALHE_BARRA =
        new Color(40, 40, 40);

    //Cor da bola
    public static final Color BOLA =
        new Color(236, 239, 244);

    //Definindo as cores dos tijolos
    public static final Color TIJOLO_VERMELHO =
        new Color(178, 34, 34);

    //Paleta de tijolos vermelho
    public static final Color[] TIJOLOS = {
        TIJOLO_VERMELHO,
        new Color(198, 93, 60),
        new Color(160, 64, 40),
        new Color(140, 55, 35),
        new Color(120, 45, 30)
    };

    //Linha de argamassa entre os tijolos
    public static final Color ARGAMASSA =
        new Color(214, 202, 178);

    //Poeira - quando destrói um bloco
    public static final Color POEIRA =
        new Color(200, 190, 170);

    //Placa de atenção
    public static final Color PLACA_FUNDO =
        new Color(240, 195, 30);

    public static final Color PLACA_BORDA =
        Color.BLACK;

    public static final Color TEXTO =
        Color.BLACK;

    public static final Color TEXTO_CLARO =
        Color.WHITE;

    public static final Font FONTE_HUD =
        new Font("Arial", Font.BOLD, 18);

    public static final Font FONTE_TITULO =
        new Font("Arial", Font.BOLD, 30);
}