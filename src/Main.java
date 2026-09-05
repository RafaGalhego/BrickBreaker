package src;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        JFrame janela = new JFrame("Brick Breaker");
        PainelJogo painel = new PainelJogo();

        janela.add(painel);
        janela.pack();
        janela.setSize(800, 600);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}