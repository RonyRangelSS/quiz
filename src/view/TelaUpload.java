package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class TelaUpload {
    public static void main(String[] args) {
        // Configurações do frame
        JFrame frame = new JFrame("Faça o upload do seu arquivo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Label com a instrução
        JLabel label = new JLabel("Escolha o arquivo que deseja fazer upload");
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(label, BorderLayout.NORTH);

        // Painel para o seletor de arquivo
        FileChooser escolherArquivo = new FileChooser();
        mainPanel.add(escolherArquivo, BorderLayout.CENTER);

        frame.add(mainPanel);

        // Ajusta o tamanho do frame e o torna visível
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
