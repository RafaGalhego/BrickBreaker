package src;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class PainelJogo extends JPanel implements ActionListener {

    private Timer timer;

    public PainelJogo() {
        setBackground(Color.BLACK);

        timer = new Timer(16, this); //ajuste para 60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //limpar a tela

        //desenha a tela inicial
        g.setColor(Color.WHITE);
        g.drawString("Brick Breaker!", 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //mover objetos
        //checar colisões

        repaint();  //feitas as movimentações e verificações, redesenha o painel em tempo real
    }
}