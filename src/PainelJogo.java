//Importando classes necessárias para a criação do painel de jogo, incluindo gráficos, eventos e listas.
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//PainelJogo é a classe principal que representa o painel do jogo, onde todos os elementos do jogo são desenhados e atualizados
public class PainelJogo extends JPanel implements ActionListener, KeyListener {

    //largura e altura do painel do jogo
    private final int LARGURA_TELA = 800;
    private final int ALTURA_TELA = 600;

    private Timer timer;

    //Barra do jogador que se move horizontalmente
    private Barra barra;

    //Bola do jogo, inicia em cima da barra e colide com cenário e blocos
    private Bola bola;

    //Lista de blocos e partículas que serão desenhados no painel do jogo
    private List<Bloco> blocos;
    private List<Particula> particulas;

    private int pontuacao; //Atualiza a pontuação do jogador, que aumenta quando ele destrói blocos
    private boolean jogoAtivo;
    private boolean moverEsquerda, moverDireita;
    
    //Indica se o jogador venceu o jogo
    private boolean vitoria;

   //Inicializa o painel do jogo, configurando tamanho, cor de fundo, eventos de teclado e criando os elementos do jogo 
    public PainelJogo() {
        setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        setBackground(Tema.FUNDO);

        setFocusable(true);
        addKeyListener(this);

        barra = new Barra(LARGURA_TELA / 2 - 50, ALTURA_TELA - 40, 100, 15);
        bola = new Bola(0, 0, 20, 20);
        bola.posicaoInicial(barra);
        blocos = criarBlocos();
        particulas = new ArrayList<>();

        pontuacao = 0;        //Inicializa a pontuação
        jogoAtivo = true;
        vitoria = false; 

        timer = new Timer(16, this); //ajuste para 60 FPS
        timer.start();
    }

    //Monta a grade de tijolos
    private List<Bloco> criarBlocos() {
        List<Bloco> lista = new ArrayList<>();

        int linhas = 5;
        int colunas = 10;
        int largura = 70;
        int altura = 25;
        int espacamento = 4;
        int margemTopo = 70;
        int margemLateral = (LARGURA_TELA - (colunas * (largura + espacamento))) / 2;

        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                int x = margemLateral + c * (largura + espacamento);
                int y = margemTopo + l * (altura + espacamento);

                Color cor = Tema.TIJOLOS[l % Tema.TIJOLOS.length];
                int pontos = (linhas - l) * 10; //fileiras de cima valem mais

                lista.add(new Bloco(x, y, largura, altura, cor, pontos));
            }
        }
        return lista;
    }

    //Sobrescreve o método paintComponent para desenhar todos os elementos do jogo no painel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //limpar a tela

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        desenharFundo(g2);
        desenharFaixaObra(g2, 0);
        desenharFaixaObra(g2, ALTURA_TELA - 10);

        for (Bloco b : blocos) {
            b.desenhar(g2);
        }

        for (Particula p : particulas) {
            p.desenhar(g2);
        }

        barra.desenhar(g2);
        bola.desenhar(g2);

        desenharHud(g2);

        if (!jogoAtivo) {
            desenharFimDeJogo(g2);
        }
    }

    //Linhas sutis no fundo, tipo textura de parede
    private void desenharFundo(Graphics2D g) {
        g.setColor(Tema.FUNDO_LINHA);

        for (int y = 0; y < ALTURA_TELA; y += 40) {
            g.drawLine(0, y, LARGURA_TELA, y);
        }
    }

    //Faixa de atenção obra
    private void desenharFaixaObra(Graphics2D g, int y) {
        int altura = 10;
        int largListra = 20;

        for (int x = -altura; x < LARGURA_TELA; x += largListra) {
            boolean par = (x / largListra) % 2 == 0;
            g.setColor(par ? Color.BLACK : Tema.PLACA_FUNDO);

            int[] xs = { x, x + largListra, x + largListra + altura, x + altura };
            int[] ys = { y, y, y + altura, y + altura };

            g.fillPolygon(xs, ys, 4);
        }
    }

    //HUD estilo placa de obra
    private void desenharHud(Graphics2D g) {
        g.setColor(Tema.PLACA_FUNDO);
        g.fillRoundRect(14, 20, 160, 34, 8, 8);

        g.setColor(Tema.PLACA_BORDA);
        g.drawRoundRect(14, 20, 160, 34, 8, 8);

        g.setFont(Tema.FONTE_HUD);
        g.setColor(Tema.TEXTO);

        g.drawString("PONTOS: " + pontuacao, 26, 43);
    }

    //Desenha a tela de fim de jogo (Game Over ou Vitória)
    private void desenharFimDeJogo(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);

        g.setFont(Tema.FONTE_TITULO);
        g.setColor(Tema.TEXTO_CLARO);
        
        //Mensagem de fim de jogo depende se o jogador venceu ou perdeu
        String mensagem = vitoria ? "VITÓRIA!" : "GAME OVER";
        int xPos = LARGURA_TELA / 2 - (mensagem.length() * 15) / 2; //centraliza aproximadamente
        g.drawString(mensagem, xPos, ALTURA_TELA / 2);
    }

    //Atualiza a posição da barra e das particulas 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (jogoAtivo) {
            if (moverEsquerda) barra.moverEsquerda();
            if (moverDireita) barra.moverDireita(LARGURA_TELA);

            if (!bola.estaMovendo()) {
                bola.posicaoInicial(barra);
            } else {
                bola.atualizar();
                bola.colisoesCenario(LARGURA_TELA);

                //inverte o sentido ao colidir com a barra
                if (bola.getBounds().intersects(barra.getBounds())) {
                    bola.inverterY(); 
                }   

                //destroi blocos ao colidir com eles
                for (Bloco b : blocos) {
                    if (b.estaAtivo() && bola.getBounds().intersects(b.getBounds())) {
                        
                        destruirBloco(b);
                        bola.inverterY(); //inverte o sentido ao colidir com o bloco
                        
                        break;
                    }
                }
            }

            if (bola.getY() > ALTURA_TELA) {
                gameOver();
            }
            
            //verifica se todos os blocos foram destruídos
            boolean todosDestruidos = true;
            for (Bloco b : blocos) {
                if (b.estaAtivo()) {
                    todosDestruidos = false;
                    break;
                }
            }
            if (todosDestruidos) {
                jogoAtivo = false;
                vitoria = true;
            }
        }

        atualizarParticulas();

        repaint(); //feitas as movimentações e verificações, redesenha o painel em tempo real
    }


    //Destrói o bloco, soma os pontos dele e cria um efeito de poeira no local
    public void destruirBloco(Bloco b) {
        Rectangle bounds = b.getBounds();

        criarPoeira(
                bounds.x + bounds.width / 2,
                bounds.y + bounds.height / 2
        );

        //soma os pontos do bloco antes de destruir
        pontuacao += b.getPontos();

        b.destruir();
    }

    //método público para ser chamado a bola cair
    public void gameOver() {
        jogoAtivo = false;
        vitoria = false;
    }

    //Cria partículas de poeira no local onde o bloco foi destruído
    private void criarPoeira(int x, int y) {
        for (int i = 0; i < 8; i++) {
            particulas.add(new Particula(x, y));
        }
    }

    //Atualiza a posição das partículas e remove as que já acabaram
    private void atualizarParticulas() {
        Iterator<Particula> it = particulas.iterator();

        while (it.hasNext()) {
            Particula p = it.next();
            p.atualizar();

            if (p.acabou()) {
                it.remove();
            }
        }
    }

    //detecta quando as teclas de seta esquerda e direita são pressionadas ou liberadas
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) moverEsquerda = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) moverDireita = true;

        //disparar bola
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            bola.lancar();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) moverEsquerda = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) moverDireita = false;
    }
    @Override
    public void keyTyped(KeyEvent e) { }

    //Pequena partícula de poeira usada quando um bloco é destruído
    private static class Particula {
        private double x, y;
        private double vx, vy;
        private int vida;

        Particula(int x, int y) {
            this.x = x;
            this.y = y;

            vx = (Math.random() - 0.5) * 4;
            vy = (Math.random() - 0.5) * 4;

            vida = 20;
        }
        void atualizar() {
            x += vx;
            y += vy;
            vida--;
        }
        boolean acabou() {
            return vida <= 0;
        }
        void desenhar(Graphics2D g) {
            g.setColor(Tema.POEIRA);
            g.fillOval((int) x, (int) y, 4, 4);
        }
    }
}