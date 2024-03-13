package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {

    private String fileName;
    private String title;
    private ArrayList<HashMap> questions;
    private int length;

    public Quiz(String fileName) {
        this.fileName = fileName;

        createQuiz();
    }

    public void createQuiz() {
        ArrayList<HashMap> questions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int amountQuestions = 0;
            int currentAmountAnswers;

            ArrayList<String> fileContent = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.add(line);
            }

            int i = 1;
            boolean stillQuestionsToRead = i < fileContent.size();

            while (stillQuestionsToRead) {
                currentAmountAnswers = Integer.parseInt(fileContent.get(i));

                amountQuestions++;

                HashMap<String, String> currentQuestion = new HashMap<>();
                String currentQuestionTitle = fileContent.get(i + 1);
                String currentQuestionAnswer = fileContent.get(i + currentAmountAnswers + 2);

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

                    currentQuestion.put(currentAlternativeLetter, currentAlternativeContent);
                    currentAlternativePointer++;

                    stillAlternativesToRead = currentAlternativePointer <= lastAlternativePointer;
                }

                questions.add(currentQuestion);
                i = i + currentAmountAnswers + 3;
                stillQuestionsToRead = i < fileContent.size();
            }

            bufferedReader.close();
            this.questions = questions;
            this.length = amountQuestions;
            this.title = fileContent.get(0);

        } catch (IOException e) {
            System.out.println("Não consegui encontrar o arquivo " + fileName);
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