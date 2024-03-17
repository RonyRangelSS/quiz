package view;
import java.awt.*;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import controller.Quiz;

public class TelaQuiz extends JFrame {
    /*Aqui é onde to criando a função pra renderizar a tela que vai ter o questionário.
    Ela já renderiza o título do questionário , as perguntas e alternativas
     */
    TelaQuiz(Quiz quiz) throws UnsupportedEncodingException {
        setTitle(quiz.getTitle());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon iconCina = new ImageIcon("iconCinamoroll.jpg");
        setIconImage(iconCina.getImage());
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        //renderiza o título
        JLabel titulo = new JLabel();
        titulo.setText("*:･ﾟ" + quiz.getTitle() + "*:･ﾟ");
        titulo.setFont(new Font("Monospaced", Font.BOLD, 34));
        ImageIcon sleepCina = new ImageIcon("sleepyCinamoroll.png");
        titulo.setIcon(sleepCina);
        titulo.setHorizontalTextPosition(JLabel.RIGHT);
        mainPanel.add(titulo, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));


        //loops aninhados para renderizar as perguntas e alternativas
        int tamanhoDoQuiz = quiz.getLength();
        String[] alternativasMarcadas = new String[tamanhoDoQuiz];

        for (int i = 0; i < tamanhoDoQuiz; i++) {
            //perguntas
            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            String pergunta =  quiz.getQuestions().get(i).get("title").toString();
            pergunta = new String(pergunta.getBytes("ISO-8859-1"), ("UTF-8"));
            JLabel perguntaLabel = new JLabel((i+1)+ ") " + pergunta);
            perguntaLabel.setFont(new Font("Serif", Font.BOLD, 18));
            painel.add(perguntaLabel);

            ButtonGroup grupoDeAlternativas = new ButtonGroup();
            int quantidadeDeAlternativas = Integer.parseInt(quiz.getQuestions().get(i).get("amountAlternatives").toString());
            for (int j = 0; j < quantidadeDeAlternativas ; j++) {
                //alternativas
                String letra = Character.toString((char) ('a' + j));
                String alternativa = quiz.getQuestions().get(i).get(letra).toString();

                alternativa = new String(alternativa.getBytes("ISO-8859-1"), ("UTF-8"));

                JRadioButton alternativaButton = new JRadioButton(alternativa);
                alternativaButton.setActionCommand(i+"");
                alternativaButton.setFont(new Font("Serif", Font.PLAIN, 15));
                grupoDeAlternativas.add(alternativaButton);
                painel.add(alternativaButton);
                alternativaButton.addActionListener(e -> {
                    JRadioButton selectedButton = (JRadioButton) e.getSource();
                    String alternativaMarcada = selectedButton.getText();
                    System.out.println("Alternativa selecionada: " + alternativaMarcada);
                    int numeroDaQuestao = Integer.parseInt(selectedButton.getActionCommand());
                    alternativasMarcadas[numeroDaQuestao] = alternativaMarcada;



                });

            }
            container.add(painel);
        }
        //Lógica para a correção das alternativas
        JButton botao = new JButton("Corrigir!");
        botao.setBackground(new Color(0xc5dce4));
        botao.addActionListener(e -> {
            int quantidadeDeAcertos = 0;
            for (int k = 0; k < alternativasMarcadas.length; k++) {
                String alternativaMarcada = alternativasMarcadas[k];
                String letraCorreta = quiz.getQuestions().get(k).get("answer").toString();
                String respostaCorreta = quiz.getQuestions().get(k).get(letraCorreta).toString();
                if (alternativaMarcada.equals(respostaCorreta)) {
                    quantidadeDeAcertos++;
                }
            }
            JOptionPane.showMessageDialog(null, "Você acertou " + quantidadeDeAcertos + " perguntas");
            try {
                TelaCorrecao correcao = new TelaCorrecao(quiz, alternativasMarcadas);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

        });

        scroll.setViewportView(container);
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(botao, BorderLayout.SOUTH);

    }

}
