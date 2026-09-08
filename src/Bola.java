import java.awt.Graphics;
import java.awt.Rectangle;

public class Bola {
    private int x;
    private int y;
    private int largura;
    private int altura;
    //velocidades separadas para movimento diagonal
    private int velX;
    private int velY;
    
    public Bola(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;

        this.largura = largura;
        this.altura = altura;

        this.velX = 4;
        this.velY = -5;
    }

    //movimento da bola
    public void atualizar() {
        x += velX;
        y += velY;
    }

    public void colisoesCenario(int larguraTela) {
        //colisão nas paredes, muda o lado
        if (x <= 0 || x + largura >= larguraTela) {
            inverterX();
        }

        //colisão com o teto, queda
        if (y <= 0) {
            inverterY();
        }
    }

    public void inverterX() {
        velX = -velX;
    }

    public void inverterY() {
        velY = -velY;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void desenhar(Graphics g) {
        g.setColor(Tema.BOLA);
        g.fillOval(x, y, largura, altura);
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

    //volta a bolinha para posição inicial e subindo
    public void resetBola(int xInicial, int yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.velY = -5;
    }
}
