package il.ac.hit.quizzy;

import java.io.*;
import java.util.List;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {
    private static SimpleCSVQuizFilesDAO instance;

    private SimpleCSVQuizFilesDAO() {
    }

    // Singleton Pattern
    public static SimpleCSVQuizFilesDAO getInstance() {
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Save the quiz name
            writer.write(quiz.getName() + "\n");

            // Save each question and its answers
            for (IQuizQuestion question : quiz.getQuestions()) {
                QuizQuestion qq = (QuizQuestion) question;
                writer.write(qq.getTitle() + "," + qq.getQuestion() + "\n");

                // Save each answer (text, isCorrect)
                for (Answer answer : qq.getAnswersList()) {
                    writer.write(answer.getText() + "," + answer.isCorrect() + "\n");
                }

                // Separate questions by an empty line
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new QuizException("Error saving quiz to file", e);
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the quiz name
            String quizName = reader.readLine();
            IQuiz quiz = new QuizFactory().createQuiz(QuizType.GUI); // Example: loading GUI quiz
            quiz.setName(quizName);

            // Read the questions and answers
            String line;
            QuizQuestion.Builder builder = null;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // If we hit a blank line, finalize the current question and add it to the quiz
                    if (builder != null) {
                        quiz.addQuestion(builder.create());
                        builder = null;
                    }
                } else {
                    // If it's the beginning of a new question
                    if (builder == null) {
                        builder = new QuizQuestion.Builder();
                        String[] parts = line.split(",", 2);
                        builder.setTitle(parts[0]);
                        builder.setQuestion(parts[1]);
                    } else {
                        // Otherwise, we're reading answers
                        String[] answerParts = line.split(",", 2);
                        String answerText = answerParts[0];
                        boolean isCorrect = Boolean.parseBoolean(answerParts[1]);
                        builder.addAnswer(answerText, isCorrect);
                    }
                }
            }

            // In case the last question doesn't end with a newline
            if (builder != null) {
                quiz.addQuestion(builder.create());
            }

            return quiz;
        } catch (IOException e) {
            throw new QuizException("Error loading quiz from file", e);
        }
    }
}
