package il.ac.hit.quizzy;
import javax.swing.*;
import java.util.ArrayList;

public class GuiQuiz implements IQuiz {
    private String name;
    private ArrayList<IQuizQuestion> questions;
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
}
