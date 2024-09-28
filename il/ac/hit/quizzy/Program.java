package il.ac.hit.quizzy;

public class Program {
    public static void main(String[] args) throws QuizException {
        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(QuizType.GUI); // or TERMINAL
        quiz.setName("Quiz Demo");

        // Build and add some questions
        IQuizQuestionBuilder builder1 = new QuizQuestion.Builder();
        builder1.setTitle("Question 1")
                .setQuestion("What is the capital of France?")
                .addAnswer("Paris", true)
                .addAnswer("London", false)
                .addAnswer("Berlin", false);
        quiz.addQuestion(builder1.create());

        IQuizQuestionBuilder builder2 = new QuizQuestion.Builder();
        builder2.setTitle("Question 2")
                .setQuestion("What is 5 + 7?")
                .addAnswer("12", true)
                .addAnswer("10", false)
                .addAnswer("14", false);
        quiz.addQuestion(builder2.create());
        
        IQuizQuestionBuilder builder3 = new QuizQuestion.Builder();
        builder3.setTitle("Question 3")
                .setQuestion("Which planet is known as the Red Planet?")
                .addAnswer("Mars", true)
                .addAnswer("Jupiter", false)
                .addAnswer("Venus", false);
        IQuizQuestion question3 = builder3.create();
        quiz.addQuestion(question3);

        // Save the quiz to a file
        SimpleCSVQuizFilesDAO dao = SimpleCSVQuizFilesDAO.getInstance();
        dao.saveQuizToFile(quiz, "quizdata.csv");

        // Load the quiz from the file
        IQuiz loadedQuiz = dao.loadQuizFromFile("quizdata.csv");
        System.out.println("Loaded quiz name: " + loadedQuiz.getName());

        // Start the quiz
        loadedQuiz.start();
    }
}
