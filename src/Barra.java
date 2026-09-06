import java.awt.Graphics;
import java.awt.Rectangle;

public class Barra {
    private int x;
    private int y;
    private int largura;
    private int altura;
    private int velocidade;


    public Barra(
            int x,
            int y,
            int largura,
            int altura) {

        this.x = x;
        this.y = y;

        this.largura = largura;
        this.altura = altura;

        velocidade = 8;
    }


    //move para esquerda
    public void moverEsquerda() {
        x -= velocidade;

        //limite
        if (x < 0) {
            x = 0;
        }
    }


    //move para direita
    public void moverDireita(int larguraTela) {
        x += velocidade;

        //limite direito
        if (x + largura > larguraTela) {

            x = larguraTela - largura;
        }
    }


    //desenha a barra
    public void desenhar(Graphics g) {
        g.setColor(Tema.BARRA);

        g.fillRoundRect(
                x,
                y,
                largura,
                altura,
                10,
                10
        );

        g.setColor(Tema.BORDA_BARRA);

        g.drawRoundRect(
                x,
                y,
                largura,
                altura,
                10,
                10
        );

        //detalhes de parafusos
        g.setColor(Tema.DETALHE_BARRA);

        g.fillOval(x + 6, y + altura / 2 - 2, 4, 4);
        g.fillOval(x + largura - 10, y + altura / 2 - 2, 4, 4);
    }


    //area de colisão da barra
    public Rectangle getBounds() {

        return new Rectangle(
            x,
            y,
            largura,
            altura
        );
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
}