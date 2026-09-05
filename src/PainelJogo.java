package src;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.List;

public class PainelJogo extends JPanel implements ActionListener, KeyListener {

    //Tamanho da tela
    private static final int LARGURA = 800;
    private static final int ALTURA = 600;

    //Controle do jogo
    private Timer timer;

    //Objeto - Barra e Blocos
    private Barra barra;
    private List<Bloco> blocos;

    //Controle da barra
    private boolean esquerdaPressionada;
    private boolean direitaPressionada;

    //Controle do fim do jogo
    private boolean gameOver;
    private boolean venceu;

    public PainelJogo() {

        setPreferredSize(
                new Dimension(LARGURA, ALTURA)
        );

        setBackground(Color.BLACK); //Mudar para cor do fundo depois============

        setFocusable(true); //permite entrada do teclado

        addKeyListener(this);

        //Criação da barra
        barra = new Barra(
                LARGURA / 2 - 60,
                ALTURA - 50,
                120,
                18
        );

        //Criação dos blocos
        blocos = new ArrayList<>();
        criarBlocos();

        //Controle do fim do jogo
        gameOver = false;
        venceu = false;

        timer = new Timer(16, this); //ajuste para 60 FPS
        timer.start();
    }

    private void criarBlocos() {

        int linhas = 5;
        int colunas = 8;

        int larguraBloco = 90;
        int alturaBloco = 25;

        int espacamento = 5;

        int inicioX = 20;
        int inicioY = 60;

        for (int linha = 0; linha < linhas; linha++) {

            for (int coluna = 0; coluna < colunas; coluna++) {

                int x =
                        inicioX +
                        coluna * (larguraBloco + espacamento);

                int y =
                        inicioY +
                        linha * (alturaBloco + espacamento);

                Color cor;

                if (linha == 0) {

                    cor = Tema.TIJOLO_VERMELHO;

                } else if (linha == 1) {

                    cor = Tema.TIJOLO_LARANJA;

                } else if (linha == 2) {

                    cor = Tema.TIJOLO_MARROM;

                } else if (linha == 3) {

                    cor = Tema.TIJOLO_CINZA;

                } else {

                    cor = Tema.TIJOLO_ESCUTO;
                }

                //Cria o bloco e adiciona na lista de blocos
                Bloco bloco = new Bloco(
                        x,
                        y,
                        larguraBloco,
                        alturaBloco,
                        cor
                );

                blocos.add(bloco);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); //limpar a tela

        //Desenha os blocos
        for (Bloco bloco : blocos) {
            bloco.desenhar(g);
        }

        //Desenha a barra
        barra.desenhar(g);

        //Desenha o nome do jogo
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Brick Breaker!", 20, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //mover objetos
        //checar colisões

        if (!gameOver && !venceu) {

            if (esquerdaPressionada) {

                barra.moverEsquerda();
            }

            if (direitaPressionada) {

                barra.moverDireita(LARGURA);
            }
        }

        repaint(); //feitas as movimentações e verificações, redesenha o painel em tempo real
    }

    //=====================================================
    // CONTROLE DO TECLADO
    //=====================================================

    @Override
    public void keyPressed(KeyEvent e) {

        //Tecla seta para esquerda
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            esquerdaPressionada = true;
        }

        //Tecla seta para direita
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            direitaPressionada = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //Quando soltar a tecla esquerda
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            esquerdaPressionada = false;
        }

        //Quando soltar a tecla direita
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            direitaPressionada = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Não será utilizado
    }
}