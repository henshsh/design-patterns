package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GuiQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions;
    private int score;

    public GuiQuiz() {
        questions = new ArrayList<>();
        score = 0;
    }

    @Override
    public void start() {
        for (IQuizQuestion question : questions) {
            QuizQuestion qq = (QuizQuestion) question;
            showQuestion(qq);
        }
        JOptionPane.showMessageDialog(null, "Your final score is: " + score);
    }

    private void showQuestion(QuizQuestion question) {
        String[] answers = question.getAnswers();
        int correctAnswerIndex = JOptionPane.showOptionDialog(null,
                question.getQuestion(),
                question.getTitle(),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                answers,
                answers[0]);

        if (question.isCorrectAnswer(correctAnswerIndex)) {
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

    @Override
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }
}
