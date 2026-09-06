import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bloco {
    private int x;
    private int y;

    private int largura;
    private int altura;

    private Color cor;

    private int pontos;

    private boolean destruido;

    //Construtor original - vale 10 pontos
    public Bloco(
            int x,
            int y,
            int largura,
            int altura,
            Color cor) {

        this(x, y, largura, altura, cor, 10);
    }

    //Construtor com pontuação customizada
    public Bloco(
            int x,
            int y,
            int largura,
            int altura,
            Color cor,
            int pontos) {

        this.x = x;
        this.y = y;

        this.largura = largura;
        this.altura = altura;

        this.cor = cor;
        this.pontos = pontos;

        destruido = false;
    }

//Desenhando o bloco
    public void desenhar(Graphics g) {

        //Verificando se ja foi destruido
        if (destruido) {
            return;
        }

        //Corpo do tijolo
        g.setColor(cor);

        g.fillRect(
                x,
                y,
                largura,
                altura
        );

        g.setColor(Color.BLACK);

        g.drawRect(
                x,
                y,
                largura,
                altura
        );

        //brilho no topo do tijolo
        g.setColor(cor.brighter());

        g.drawLine(
                x + 3,
                y + 3,
                x + largura - 3,
                y + 3
        );

        //linha de cimento
        g.setColor(Tema.ARGAMASSA);

        g.drawLine(
                x,
                y + altura / 2,
                x + largura,
                y + altura / 2
        );
    }

    //Destruir bloco
    public void destruir() {

        destruido = true;
    }

    public boolean estaAtivo() {

        return !destruido;
    }

    public int getPontos() {
        return pontos;
    }

    //Area da colisão do bloco
    public Rectangle getBounds() {

        return new Rectangle(
                x,
                y,
                largura,
                altura
        );
    }
}