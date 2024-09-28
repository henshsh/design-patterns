package il.ac.hit.quizzy;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;

public class SimpleCSVQuizFilesDAOTest {

    @Test
    public void testSaveAndLoadQuiz() throws QuizException {
        SimpleCSVQuizFilesDAO dao = SimpleCSVQuizFilesDAO.getInstance();

        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(QuizType.TERMINAL);
        quiz.setName("Test Quiz");

        IQuizQuestionBuilder builder = new QuizQuestion.Builder();
        builder.setTitle("Test Question");
        builder.setQuestion("What is 2 + 2?");
        builder.addAnswer("4", true);
        builder.addAnswer("3", false);
        builder.addAnswer("5", false);
        IQuizQuestion question = builder.create();
        quiz.addQuestion(question);

        dao.saveQuizToFile(quiz, "testQuiz.data");

        IQuiz loadedQuiz = dao.loadQuizFromFile("testQuiz.data");
        assertEquals(quiz.getName(), loadedQuiz.getName());
        new File("testQuiz.data").delete(); // clean up
    }
}
