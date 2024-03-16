package view;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class TelaUpload {
    /*Aqui é onde começa a renderizaação das telas (main) a partir da tela de upload do arquivo */
    public static void main(String[] args) {
        //renderiza a tela de upload
        JFrame frame = new JFrame("Faça o upload do seu arquivo txt");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setVisible(true);

        //renderiza o label
        JLabel label = new JLabel("Escolha o arquivo que deseja fazer upload");
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(label, BorderLayout.NORTH);

        //renderiza o panel de escolher arquivo
        fileChooser escolherArquivo = new fileChooser();
        frame.getContentPane().add(escolherArquivo, BorderLayout.CENTER);
    }
    
}
