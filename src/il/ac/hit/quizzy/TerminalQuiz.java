package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalQuiz implements IQuiz {
    private String name;
    private ArrayList<IQuizQuestion> questions;
    private int score;

    public TerminalQuiz() {
        questions = new ArrayList<>();
        score = 0;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        for (IQuizQuestion question : questions) {
            QuizQuestion qq = (QuizQuestion) question;
            askQuestion(qq, scanner);
        }
        System.out.println("Your final score is: " + score);
    }

    private void askQuestion(QuizQuestion question, Scanner scanner) {
        System.out.println(question.getTitle());
        System.out.println(question.getQuestion());
        String[] answers = question.getAnswers();

        for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ". " + answers[i]);
        }

        int userAnswer = scanner.nextInt() - 1;
        if (question.isCorrectAnswer(userAnswer)) {
            score++;
        }
    }

    @Override
    public void setName(String text) {
        this.name = text;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }
}
