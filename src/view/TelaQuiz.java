package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import controller.Quiz;

public class TelaQuiz extends JFrame {
    /*Aqui é onde to criando a função pra renderizar a tela que vai ter o questionário.
    Ela já renderiza o título do questionário , as perguntas e alternativas
     */

    public Timer timer;

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

        JPanel panelEspaco = new JPanel();
        panelEspaco.setLayout(new BorderLayout());

        //renderiza o título
        JLabel titulo = new JLabel();
        titulo.setText("*:･ﾟ" + quiz.getTitle() + "*:･ﾟ");
        titulo.setFont(new Font("Monospaced", Font.BOLD, 34));
        ImageIcon sleepCina = new ImageIcon("sleepyCinamoroll.png");
        titulo.setIcon(sleepCina);
        titulo.setHorizontalTextPosition(JLabel.CENTER);

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new FlowLayout());
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tituloPanel.add(titulo);
        panelEspaco.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        panelEspaco.add(tituloPanel, BorderLayout.CENTER);
        mainPanel.add(panelEspaco, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(new Color(0xe1e5f2));

        JPanel panelPerguntas = new JPanel();
        panelPerguntas.setLayout(new FlowLayout());
        panelPerguntas.setBackground(new Color(0xe1e5f2));

        //loops aninhados para renderizar as perguntas e alternativas
        int tamanhoDoQuiz = quiz.getLength();
        String[] alternativasMarcadas = new String[tamanhoDoQuiz];

        for (int i = 0; i < tamanhoDoQuiz; i++) {
            //perguntas
            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            painel.setMaximumSize(new Dimension(800, 600));
            painel.setMinimumSize(new Dimension(800, 100));
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
            container.add(Box.createRigidArea(new Dimension(0, 20)));
            panelPerguntas.add(container);
        }

        // Lógica para verificar as respostas corretas
        ActionListener verificarRespostas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    TelaCorrecao correcao = new TelaCorrecao(quiz, alternativasMarcadas, quantidadeDeAcertos);
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        };

        // Lógica para o temporizador
        JLabel timerLabel = new JLabel();
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        timer = new Timer(1000, new ActionListener() {
            int secondsLeft = 40; // Defina o número inicial de segundos

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 15) {
                    timerLabel.setText("Faltam: " + secondsLeft + " segundinhos ;)");
                    secondsLeft--;
                } else if (secondsLeft > 0) {
                    timerLabel.setText("Eitaa, tá acabando (OMG) :" + secondsLeft + " segundinhos ;)");
                    secondsLeft--;
                } else {
                    timer.stop(); // Pare o temporizador quando os 60 segundos acabarem
                    timerLabel.setText("Cabosse! ;-;");
                    verificarRespostas.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });
        timer.start(); // Inicie o temporizador

        //Lógica para a correção das alternativas
        JButton botao = new JButton("Corrigir!");
        botao.setBackground(new Color(0xc5dce4));
        botao.setPreferredSize(new Dimension(100, 50));
        botao.addActionListener(verificarRespostas);

        JPanel panelBotao = new JPanel();
        panelBotao.setLayout(new BorderLayout());
        panelBotao.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        panelBotao.add(botao, BorderLayout.EAST);
        panelBotao.add(timerLabel, BorderLayout.WEST);
        panelBotao.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);

        scroll.setViewportView(panelPerguntas);
        scroll.setPreferredSize(new Dimension(800, 400));
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(panelBotao, BorderLayout.SOUTH);

    }

}
