package il.ac.hit.quizzy;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuizFactoryTest {

    @Test
    public void testCreateQuiz() {
        QuizFactory factory = new QuizFactory();
        IQuiz terminalQuiz = factory.createQuiz(QuizType.TERMINAL);
        IQuiz guiQuiz = factory.createQuiz(QuizType.GUI);

        assertNotNull(terminalQuiz);
        assertNotNull(guiQuiz);

        assertTrue(terminalQuiz instanceof TerminalQuiz);
        assertTrue(guiQuiz instanceof GuiQuiz);
    }
}
