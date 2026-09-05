package src;

import java.awt.*;

public class Barra {
    private int x, y;
    private int largura, altura;
    private int velocidade;
    private int limiteEsquerdo = 0;

  public Barra(int x, int y, int largura, int altura) {

        this.x = x;
        this.y = y;

        this.largura = largura;
        this.altura = altura;

        velocidade = 8;
    }
 
    public void moverEsquerda() {
        x -= velocidade;
        if (x < limiteEsquerdo) {
            x = limiteEsquerdo;
        }
    }
 
    public void moverDireita(int larguraTela) {
        x += velocidade;
        if (x + largura > larguraTela) {
            x = larguraTela - largura;
        }
    }
 
     public void desenhar(Graphics g) {

        //Barra
        g.setColor(Tema.BARRA);

        g.fillRoundRect(
                x,
                y,
                largura,
                altura,
                10,
                10
        );

        //Borda
        g.setColor(Tema.BORDA_BARRA);

        g.drawRoundRect(
                x,
                y,
                largura,
                altura,
                10,
                10
        );
    }

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