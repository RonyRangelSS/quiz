package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class TelaUpload {
   TelaUpload() {
        // Configurações do frame
        JFrame frame = new JFrame("Faça o upload do seu arquivo!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 110));
        frame.getContentPane().setBackground(new Color(0xe1e5f2));

        ImageIcon iconCina = new ImageIcon("iconCinamoroll.jpg");
        frame.setIconImage(iconCina.getImage());

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0xe1e5f2));

        // Label com a instrução
        JLabel label = new JLabel("Escolha o arquivo que deseja fazer upload ✧*:°");
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        mainPanel.add(label, BorderLayout.NORTH);

        // Painel para o seletor de arquivo
        fileChooser escolherArquivo = new fileChooser();
        escolherArquivo.setBackground(new Color(0xe1e5f2));
        mainPanel.add(escolherArquivo, BorderLayout.CENTER);

        frame.add(mainPanel);

        // Ajusta o tamanho do frame e o torna visível
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
