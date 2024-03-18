package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial implements ActionListener {
    JFrame frame1 = new JFrame("Tela Inicial");
    JButton startButton = new JButton("Iniciar");
    JPanel panelTop = new JPanel();
    JPanel panelCenter = new JPanel();
    JPanel panelBottom = new JPanel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel("♡ Discentes: ♡");
    TelaInicial() {
        startButton.setPreferredSize(new Dimension(200, 70));
        startButton.setFont(new Font("Monospaced", Font.BOLD, 25));
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.setBackground(new Color(0xc5dce4));

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setPreferredSize(new Dimension(500, 400));
        frame1.getContentPane().setBackground(new Color(0xe1e5f2));
        ImageIcon iconCina = new ImageIcon("iconCinamoroll.jpg");
        frame1.setIconImage(iconCina.getImage());
        frame1.setLayout(new BorderLayout());

        panelTop.setBackground(new Color(0xe1e5f2));
        panelBottom.setBackground(new Color(0xe1e5f2));

        panelCenter.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridx = 0; c.gridy = 1;
        panelCenter.add(startButton,c);
        panelCenter.setBackground(new Color(0xe1e5f2));

        ImageIcon logo = new ImageIcon("logo.png");
        label1.setIcon(logo);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Serif", Font.BOLD, 25));

        ImageIcon discentes = new ImageIcon("discentes.png");
        label2.setIcon(discentes);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Serif", Font.PLAIN, 18));
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setVerticalTextPosition(JLabel.TOP);

        panelCenter.add(startButton);
        panelTop.add(label1);
        panelBottom.add(label2);

        frame1.add(panelTop, BorderLayout.NORTH);
        frame1.add(panelCenter, BorderLayout.CENTER);
        frame1.add(panelBottom, BorderLayout.SOUTH);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
            TelaUpload telaUpload = new TelaUpload();
            frame1.dispose();
        }
    }
}
