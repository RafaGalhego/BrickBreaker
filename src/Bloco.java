package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bloco {
    private int x;
    private int y;

    private int largura;
    private int altura;

    private Color cor;

    private boolean destruido;

    public Bloco(
            int x,
            int y,
            int largura,
            int altura,
            Color cor) {

        this.x = x;
        this.y = y;

        this.largura = largura;
        this.altura = altura;

        this.cor = cor;

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

        //visual do tijolo
        g.setColor(cor.brighter());

        g.drawLine(
                x + 3,
                y + 3,
                x + largura - 3,
                y + 3
        );
    }

    //Destruir bloco
    public void destruir() {

        destruido = true;
    }

    public boolean estaAtivo() {

        return !destruido;
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