package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Quiz extends RuntimeException {

    private String fileName;
    private String title;
    private ArrayList<HashMap> questions;
    private int length;

    public Quiz(String fileName) {
        this.fileName = fileName;

        createQuiz();
    }

    public void createQuiz() throws WrongFileTypeException {

        boolean choosenFileIsNotTxt = !fileName.endsWith(".txt");

        ArrayList<HashMap> questions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if (choosenFileIsNotTxt) {
                throw new WrongFileTypeException("O arquivo selecionado não é um .txt");
            }

            int amountQuestions = 0;
            int currentAmountAnswers;

            ArrayList<String> fileContent = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.add(line);
            }

            int i = 1;
            boolean stillQuestionsToRead = i < fileContent.size();

            try {

                while (stillQuestionsToRead) {

                    try {
                        currentAmountAnswers = Integer.parseInt(fileContent.get(i));
                    } catch (NumberFormatException n) {
                        throw new WrongFileTypeException(
                                "O arquivo selecionado não está seguindo o padrão suportado pela aplicação");
                    }

                    amountQuestions++;

                    HashMap<String, String> currentQuestion = new HashMap<>();

                    String currentQuestionTitle = fileContent.get(i + 1);

                    if (currentQuestionTitle.isEmpty()) {
                        throw new WrongFileTypeException("Alguma questão no seu quiz não possui título");
                    }

                    String currentQuestionAnswer = fileContent.get(i + currentAmountAnswers + 2);
                    if (currentQuestionAnswer.isBlank()) {
                        throw new WrongFileTypeException(
                                "Alguma questão no seu quiz não pussue a resposta correta preenchida");
                    }

                    currentQuestion.put("title", currentQuestionTitle);
                    currentQuestion.put("answer", currentQuestionAnswer);
                    currentQuestion.put("amountAlternatives", Integer.toString(currentAmountAnswers));

                    int currentAlternativePointer = i + 2;
                    int lastAlternativePointer = i + currentAmountAnswers + 1;
                    boolean stillAlternativesToRead = currentAlternativePointer <= lastAlternativePointer;

                    String currentAlternativeLetter;
                    String currentAlternativeContent;

                    while (stillAlternativesToRead) {
                        currentAlternativeLetter = Character.toString((char) ('a'
                                + (currentAmountAnswers - (lastAlternativePointer - currentAlternativePointer + 1))));

                        currentAlternativeContent = fileContent.get(currentAlternativePointer);

                        if (currentAlternativeContent.isEmpty()) {
                            throw new WrongFileTypeException("Alguma alternativa no seu arquivo não está preenchida");
                        }
                        currentQuestion.put(currentAlternativeLetter, currentAlternativeContent);
                        currentAlternativePointer++;
                        stillAlternativesToRead = currentAlternativePointer <= lastAlternativePointer;
                    }

                    questions.add(currentQuestion);
                    i = i + currentAmountAnswers + 3;
                    stillQuestionsToRead = i < fileContent.size();
                }
            } catch (IndexOutOfBoundsException e) {
                throw new WrongFileTypeException(
                        "O arquivo selecionado não está seguindo o padrão suportado pela aplicação");
            }

            bufferedReader.close();
            this.questions = questions;
            this.length = amountQuestions;
            this.title = fileContent.get(0);

        } catch (IOException e) {
            throw new WrongFileTypeException("Não foi possível encontrar o arquivo selecionado");
        }

        this.questions = questions;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<HashMap> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<HashMap> questions) {
        this.questions = questions;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}