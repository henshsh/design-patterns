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
            writer.write("Quiz: " + quiz.getName() + "\n");
            // Add logic for writing questions to the file
        } catch (IOException e) {
            throw new QuizException("Error saving quiz to file", e);
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String name = reader.readLine();
            // Add logic for reading questions from the file
            IQuiz quiz = new QuizFactory().createQuiz(QuizType.GUI); // Example usage
            quiz.setName(name);
            return quiz;
        } catch (IOException e) {
            throw new QuizException("Error loading quiz from file", e);
        }
    }
}
