package il.ac.hit.quizzy;

public class QuizFactory {

    public IQuiz createQuiz(QuizType type) {
        switch (type) {
            case TERMINAL:
                return new TerminalQuiz(); // Assuming this class will be implemented
            case GUI:
                return new GuiQuiz(); // Assuming this class will be implemented
            default:
                throw new IllegalArgumentException("Unknown QuizType");
        }
    }
}
