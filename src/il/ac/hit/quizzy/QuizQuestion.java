package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String title;
    private String question;
    private List<Answer> answers = new ArrayList<>();

    // Protected constructor
    protected QuizQuestion() {
    }

    // Method to get the question text
    public String getQuestion() {
        return this.question;
    }

    // Method to get answers as a string array for displaying
    public String[] getAnswers() {
        String[] answerTexts = new String[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            answerTexts[i] = answers.get(i).getText();
        }
        return answerTexts;
    }

    public String getTitle() {
        return this.title;
    }

    // Method to check if a given answer index is correct
    public boolean isCorrectAnswer(int answerIndex) {
        return answers.get(answerIndex).isCorrect();
    }

    // Builder class
    public static class Builder implements IQuizQuestionBuilder {
        private String title;
        private String question;
        private List<Answer> answers = new ArrayList<>();

        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            this.title = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            this.question = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            this.answers.add(new Answer(text, correct));
            return this;
        }

        @Override
        public IQuizQuestion create() {
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.title = this.title;
            quizQuestion.question = this.question;
            quizQuestion.answers = this.answers;
            return quizQuestion;
        }
    }
}
