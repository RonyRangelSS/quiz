package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.UnsupportedEncodingException;

import controller.Quiz;
import controller.WrongFileTypeException;

import javax.swing.*;

public class FileChooser extends JPanel {
    private JTextField nomeDoArquivoSelecionado;
    /*
     * Essa é a função que vai renderizar o panel e o botão de escolher arquivo
     * e ao escolher o arquivo dá como argumento para a função que cria o quiz e a
     * tela do quiz
     */

    FileChooser() {

        // registra em um text field o nome do arquivo escolhido
        nomeDoArquivoSelecionado = new JTextField(20);
        nomeDoArquivoSelecionado.setEditable(false);
        add(nomeDoArquivoSelecionado);

        // renderiza o botão de escolher arquivo
        JButton botaoEscolherArquivo = new JButton("Escolher Arquivo");
        botaoEscolherArquivo.setBackground(new Color(0xc5dce4));
        botaoEscolherArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // abre o file chooser para escolher o arquivo
                JFileChooser escolherArquivo = new JFileChooser();
                int resultadoDaEscolha = escolherArquivo.showOpenDialog(null);
                if (resultadoDaEscolha == JFileChooser.APPROVE_OPTION) {
                    File arquivoEscolhido = escolherArquivo.getSelectedFile();
                    nomeDoArquivoSelecionado.setText(arquivoEscolhido.getAbsolutePath());
                    JLabel label = new JLabel(
                            "Arquivo escolhido: " + arquivoEscolhido.getName() + ". Deseja abrir este arquivo?");
                    UIManager.put("OptionPane.yesButtonText", "Sim");
                    UIManager.put("OptionPane.noButtonText", "Não");
                    UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 16));
                    UIManager.put("OptionPane.messageBackground", new Color(0xe1e5f2));
                    UIManager.put("OptionPane.questionIcon", new ImageIcon("sleepyCinamoroll.png"));
                    int result = JOptionPane
                            .showConfirmDialog(null, label, "Arquivo escolhido", JOptionPane.YES_NO_OPTION);
                    // se o arquivo for escolhido, cria o quiz e a tela do quiz
                    if (result == JOptionPane.YES_OPTION) {
                        String time = (String)JOptionPane.showInputDialog(null, "Quantos minutos você quer para fazer o quiz ?\n OBS: Caso não queira tempo clique em cancele ou deixe em branco", "Tempo do Quiz",
                        JOptionPane.QUESTION_MESSAGE, new ImageIcon("sleepyCinamoroll.png"), null, "0");
                        if (time == null) {
                            time = "0";
                        }
                        float timeFloat = Float.parseFloat(time);
                        try {
                            Quiz quiz = new Quiz(arquivoEscolhido.getAbsolutePath());
                            try {
                                TelaQuiz telaQuiz = new TelaQuiz(quiz, timeFloat);
                            } catch (UnsupportedEncodingException e1) {
                                e1.printStackTrace();
                            }
                        } catch (WrongFileTypeException wfte) {
                            JOptionPane.showMessageDialog(null, wfte.getMessage());
                        }

                    }

                }
            }
        });
        add(botaoEscolherArquivo);
    }
}
