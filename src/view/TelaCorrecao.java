package view;
import java.awt.Color;
import java.awt.Font;
import java.io.UnsupportedEncodingException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Quiz;

public class TelaCorrecao extends JFrame {
    TelaCorrecao(Quiz quiz, String[] alternativasMarcadas) throws UnsupportedEncodingException {
        setTitle(quiz.getTitle());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);

        JLabel titulo = new JLabel(quiz.getTitle());
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo);

        int tamanhoDoQuiz = quiz.getLength();

        for (int i = 0; i < tamanhoDoQuiz; i++) {
            //perguntas
            String pergunta =  quiz.getQuestions().get(i).get("title").toString();
            pergunta = new String(pergunta.getBytes("ISO-8859-1"), ("UTF-8"));
            JLabel perguntaLabel = new JLabel((i+1)+ ") " + pergunta);
            perguntaLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(perguntaLabel);
            String letraCorreta = quiz.getQuestions().get(i).get("answer").toString();
            String respostaCorreta = quiz.getQuestions().get(i).get(letraCorreta).toString();
            String respostaMarcada = alternativasMarcadas[i];
    
            int quantidadeDeAlternativas = Integer.parseInt(quiz.getQuestions().get(i).get("amountAlternatives").toString());
            for (int j = 0; j < quantidadeDeAlternativas ; j++) {
                //alternativas
                String letra = Character.toString((char) ('a' + j));
                String alternativa = quiz.getQuestions().get(i).get(letra).toString();
    
                alternativa = new String(alternativa.getBytes("ISO-8859-1"), ("UTF-8"));
                JLabel alternativaLabel = new JLabel(alternativa);
                alternativaLabel.setFont(new Font("Arial", Font.BOLD, 16));
                add(alternativaLabel);
                
             }
             if (respostaMarcada.equals(respostaCorreta)) {
                JLabel marcador = new JLabel("Você acertou");
                marcador.setForeground(Color.GREEN);
                marcador.setFont(new Font("Arial", Font.BOLD, 16));
                add(marcador);
            } else {
                JLabel marcador = new JLabel("Você errou");
                marcador.setForeground(Color.RED);
                marcador.setFont(new Font("Arial", Font.BOLD, 16));
                add(marcador);
            }
             JLabel correcao = new JLabel("Alternativa correta: " + respostaCorreta);
             correcao.setForeground(Color.DARK_GRAY);
             add(correcao);
        }
        JButton encerrar = new JButton("Encerrar quiz");
        encerrar.addActionListener(e -> {
            System.exit(0);
        });
        add(encerrar);

        JButton refazer = new JButton("Refazer quiz");
        refazer.addActionListener(e -> {
            try {
                TelaQuiz telaQuiz = new TelaQuiz(quiz);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        });
        //quero colocar uma distância entre os botões e as outras coisas
        add(encerrar);
        add(refazer);

        
    }
}
