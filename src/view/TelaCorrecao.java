package view;

import java.awt.*;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import controller.Quiz;

public class TelaCorrecao extends JFrame {
    /*
     * Aqui é onde to criando a função pra renderizar a tela que vai ter o
     * questionário.
     * Ela já renderiza o título do questionário , as perguntas e alternativas
     */
    TelaCorrecao(Quiz quiz, String[] alternativasMarcadas, int quantidadeDeacertos, float time)
            throws UnsupportedEncodingException {
        setTitle(quiz.getTitle());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon iconCina = new ImageIcon("iconCinamoroll.jpg");
        setIconImage(iconCina.getImage());
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBackground(new Color(0xe1e5f2));
        setVisible(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        // renderiza o título
        JLabel titulo = new JLabel();
        titulo.setText("*:･ﾟ" + quiz.getTitle() + "*:･ﾟ");
        titulo.setFont(new Font("Monospaced", Font.BOLD, 34));
        ImageIcon sleepCina = new ImageIcon("sleepyCinamoroll.png");
        titulo.setIcon(sleepCina);
        titulo.setHorizontalTextPosition(JLabel.CENTER);

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new FlowLayout());
        tituloPanel.add(titulo);
        tituloPanel.setBackground(new Color(0xe1e5f2));
        mainPanel.add(tituloPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(new Color(0xe1e5f2));

        JPanel panelPerguntas = new JPanel();
        panelPerguntas.setLayout(new FlowLayout());
        panelPerguntas.setBackground(new Color(0xe1e5f2));

        // loops aninhados para renderizar as perguntas e alternativas
        int tamanhoDoQuiz = quiz.getLength();

        for (int i = 0; i < tamanhoDoQuiz; i++) {
            // perguntas
            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            painel.setMaximumSize(new Dimension(800, 600));
            painel.setMinimumSize(new Dimension(800, 100));
            String pergunta = quiz.getQuestions().get(i).get("title").toString();
            pergunta = new String(pergunta.getBytes("ISO-8859-1"), ("UTF-8"));
            JLabel perguntaLabel = new JLabel((i + 1) + ") " + pergunta);
            perguntaLabel.setFont(new Font("Serif", Font.BOLD, 18));
            painel.add(perguntaLabel);

            String letraCorreta = quiz.getQuestions().get(i).get("answer").toString();
            String respostaCorreta = quiz.getQuestions().get(i).get(letraCorreta).toString();
            String respostaMarcada = alternativasMarcadas[i];
            ButtonGroup grupoDeAlternativas = new ButtonGroup();

            int quantidadeDeAlternativas = Integer
                    .parseInt(quiz.getQuestions().get(i).get("amountAlternatives").toString());
            for (int j = 0; j < quantidadeDeAlternativas; j++) {
                // alternativas
                String letra = Character.toString((char) ('a' + j));
                String alternativa = quiz.getQuestions().get(i).get(letra).toString();

                alternativa = new String(alternativa.getBytes("ISO-8859-1"), ("UTF-8"));

                JRadioButton alternativaButton = new JRadioButton(alternativa);
                alternativaButton.setActionCommand(i + "");
                alternativaButton.setFont(new Font("Serif", Font.PLAIN, 15));
                grupoDeAlternativas.add(alternativaButton);
                if (respostaMarcada != null && respostaMarcada.equals(respostaCorreta) && respostaMarcada.equals(alternativa)) {
                    alternativaButton.setBackground(Color.GREEN);
                    alternativaButton.setText(alternativa + " ✓");
                    alternativaButton.setSelected(true);
                    perguntaLabel.setForeground(Color.GREEN);
                }
                if (respostaMarcada != null && !respostaMarcada.equals(respostaCorreta) && respostaMarcada.equals(alternativa)) {
                    alternativaButton.setBackground(Color.RED);
                    alternativaButton.setText(alternativa + " ✗");
                    alternativaButton.setSelected(true);
                    perguntaLabel.setForeground(Color.RED);
                } else {
                    alternativaButton.setForeground(UIManager.getColor("RadioButton.foreground"));
                }
                alternativaButton.setEnabled(false);
                painel.add(alternativaButton);

            }
            container.add(painel);
            container.add(Box.createRigidArea(new Dimension(0, 20)));
            panelPerguntas.add(container);
        }
        JLabel acertos = new JLabel("Você acertou " + quantidadeDeacertos + " de " + tamanhoDoQuiz + " questões");
        acertos.setFont(new Font("Serif", Font.BOLD, 18));
        container.add(acertos);
        JButton encerrar = new JButton("Encerrar quiz");
        encerrar.setBackground(new Color(0xc5dce4));
        encerrar.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            dispose();
        });

        JButton refazer = new JButton("Refazer quiz");
        refazer.setBackground(new Color(0xc5dce4));
        refazer.addActionListener(e -> {
            try {
                TelaQuiz telaQuiz = new TelaQuiz(quiz, time);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            dispose();
        });
        encerrar.setPreferredSize(new Dimension(200, 60));
        refazer.setPreferredSize(new Dimension(200, 60));

        container.revalidate();
        container.repaint();

        scroll.setViewportView(panelPerguntas);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BorderLayout());
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        painelBotoes.add(refazer, BorderLayout.WEST);
        painelBotoes.add(encerrar, BorderLayout.EAST);
        painelBotoes.setBackground(new Color(0xe1e5f2));
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);

        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(painelBotoes, BorderLayout.SOUTH);

    }

}
