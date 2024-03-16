package view;

import java.awt.event.*;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import controller.Quiz;

import javax.swing.*;

public class fileChooser extends JPanel {
    private JTextField nomeDoArquivoSelecionado;
    /*Essa é a função que vai renderizar o panel e o botão de escolher arquivo 
    e ao escolher o arquivo dá como argumento para a função que cria o quiz e a tela do quiz
    */

    fileChooser() {

        //registra em um text field o nome do arquivo escolhido
        nomeDoArquivoSelecionado = new JTextField(20);
        nomeDoArquivoSelecionado.setEditable(false);
        add(nomeDoArquivoSelecionado);

        //renderiza o botão de escolher arquivo
        JButton botaoEscolherArquivo = new JButton("Escolher Arquivo");
        botaoEscolherArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //abre o file chooser para escolher o arquivo
                JFileChooser escolherArquivo = new JFileChooser();
                int resultadoDaEscolha = escolherArquivo.showOpenDialog(null);
                if (resultadoDaEscolha == JFileChooser.APPROVE_OPTION) {
                    File arquivoEscolhido = escolherArquivo.getSelectedFile();
                    nomeDoArquivoSelecionado.setText(arquivoEscolhido.getAbsolutePath());
                    JLabel label = new JLabel("Arquivo escolhido: " + arquivoEscolhido.getName()+"\n Deseja abrir o arquivo?");
                    int result = JOptionPane
                    .showConfirmDialog(null, label, "Arquivo escolhido", JOptionPane.YES_NO_OPTION);
                    //se o arquivo for escolhido, cria o quiz e a tela do quiz
                    if (result == JOptionPane.YES_OPTION) {
                            Quiz quiz = new Quiz(arquivoEscolhido.getAbsolutePath());
                                try {
                                    TelaQuiz telaQuiz = new TelaQuiz(quiz);
                                } catch (UnsupportedEncodingException e1) {
                                    e1.printStackTrace();
                                }
                            
                    }
    
                }
            }
        });
        add(botaoEscolherArquivo);
    }
}