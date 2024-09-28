package il.ac.hit.quizzy;

public class Program {
    public static void main(String[] args) throws QuizException {
        // Your test code goes here.
        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(QuizType.GUI);
        quiz.setName("Quiz Demo");
        // Other setup code for questions and running the quiz
    }
}
