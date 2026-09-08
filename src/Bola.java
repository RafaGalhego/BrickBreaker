import java.awt.Graphics;
import java.awt.Rectangle;

public class Bola {
    private double x;
    private double y;
    private int largura;
    private int altura;

    //velocidades separadas para movimento diagonal
    private double velX;
    private double velY;
    //armazena a velocidade atual da bolinha, permitindo aumentar gradualmente durante a partida
    private double velocidadeAtual = 4.5;
    
    private boolean emMovimento; //0-parada 1-em movimento
    
    public Bola(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;

        this.largura = largura;
        this.altura = altura;

        this.velX = velocidadeAtual;
        this.velY = -velocidadeAtual;

        this.emMovimento = false;
    }

    public void posicaoInicial(Barra barra) {
        if (!emMovimento) {
            //centraliza a bola acima da barra
            this.x = barra.getX() + (barra.getLargura() / 2.0) - (this.largura / 2.0);
            this.y = barra.getY() - this.altura;
        }
    }

    public void lancar() {
        if (!emMovimento) {
            emMovimento = true;
        }
    }

    //movimento da bola
    public void atualizar() {
        if (emMovimento) {
            x += velX;
            y += velY;
        }
    }

    public boolean estaMovendo() {
        return emMovimento;
    }

    public void colisoesCenario(int larguraTela) {
        //colisão nas paredes, muda o lado
        if (x <= 0) {
            x = 0;
            inverterX();
        } else if (x + largura >= larguraTela) {
            x = larguraTela - largura;
            inverterX();
        }

        //colisão com o teto, queda
        if (y <= 0) {
            y = 0;
            inverterY();
        }
    }

    //corrige bug de colisão com a barra (barra e bolinha colidem repetidamente)
    public void checarColisaoBarra(Barra barra) {
        if (getBounds().intersects(barra.getBounds())) {
            //bola só quica se estiver vindo de cima para baixo
            if (velY > 0) {
                //ao colidir é reposicionada acima da barra
                this.y = barra.getY() - this.altura;
                inverterY();
            }
        }
    }

    //aumenta a velocidade conforme pontuação
    public void aumentarVelocidade(double incremento) {
        velocidadeAtual += incremento;

        //mantem os sinais do movimento atual (+ ou -) ao aumentar o valor absoluto
        velX = (velX >= 0) ? velocidadeAtual : -velocidadeAtual;
        velY = (velY >= 0) ? velocidadeAtual : -velocidadeAtual;
    }

    public void inverterX() {
        velX = -velX;
    }

    public void inverterY() {
        velY = -velY;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, largura, altura);
    }

    public void desenhar(Graphics g) {
        g.setColor(Tema.BOLA);
        g.fillOval((int)x, (int)y, largura, altura);
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
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
        this.velY = -7;
    }
}
