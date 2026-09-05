import java.awt.*;

public class Barra {
    private int x, y;
    private int largura, altura;
    private int velocidade;
    private int limiteEsquerdo, limiteDireito;

    public Barra(int x, int y, int largura, int altura, int larguraTela) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.velocidade = 8;
        this.limiteEsquerdo = 0;
        this.limiteDireito = larguraTela - largura;
    }
 
    public void moverEsquerda() {
        x -= velocidade;
        if (x < limiteEsquerdo) {
            x = limiteEsquerdo;
        }
    }
 
    public void moverDireita() {
        x += velocidade;
        if (x > limiteDireito) {
            x = limiteDireito;
        }
    }
 
    /** Desenha a barra com detalhes que lembram uma viga de madeira/metal. */
    public void desenhar(Graphics g) {
        // corpo da barra
        g.setColor(Tema.COR_BARRA);
        g.fillRect(x, y, largura, altura);
 
        // borda
        g.setColor(Tema.COR_BARRA_BORDA);
        g.drawRect(x, y, largura, altura);
 
        // "parafusos" nas pontas, para reforçar a estética de construção
        g.setColor(Tema.COR_BARRA_DETALHE);
        g.fillOval(x + 4, y + altura / 2 - 2, 4, 4);
        g.fillOval(x + largura - 8, y + altura / 2 - 2, 4, 4);
    }
 
    /** Retângulo usado pela colega para checar colisão com a bola. */
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }
 
    public int getX() { return x; }
    public int getY() { return y; }
    public int getLargura() { return largura; }
    public int getAltura() { return altura; }
 
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
}
