package view;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import controller.Quiz;

public class TelaQuiz extends JFrame {
    /*Aqui é onde to criando a função pra renderizar a tela que vai ter o questionário.
    Ela já renderiza o título do questionário , as perguntas e alternativas. Falta pensar numa lógica para fazer
    a correção das respostas
     */
    TelaQuiz(Quiz quiz) throws UnsupportedEncodingException {
        setTitle(quiz.getTitle());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    
    //renderiza o título
    JLabel titulo = new JLabel(quiz.getTitle());
    add(titulo);

    //loops aninhados para renderizar as perguntas e alternativas
    int tamanhoDoQuiz = quiz.getLength();

    for (int i = 0; i < tamanhoDoQuiz; i++) {
        //perguntas
        String pergunta =  quiz.getQuestions().get(i).get("title").toString();
        pergunta = new String(pergunta.getBytes("ISO-8859-1"), ("UTF-8"));
        JLabel perguntaLabel = new JLabel(pergunta);
        add(perguntaLabel);

        for (int j = 0; j < 4 ; j++) {
            //alternativas
            String letra = Character.toString((char) ('a' + j));
            String alternativa = quiz.getQuestions().get(i).get(letra).toString();

            alternativa = new String(alternativa.getBytes("ISO-8859-1"), ("UTF-8"));

            JRadioButton alternativaButton = new JRadioButton(alternativa);

            add(alternativaButton);
        }
        }
    }
}
