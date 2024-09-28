package il.ac.hit.quizzy;

public class Answer {
    private String text;
    private boolean isCorrect;

    // Constructor
    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    // Getter for answer text
    public String getText() {
        return text;
    }

    // Getter for answer correctness
    public boolean isCorrect() {
        return isCorrect;
    }

    // Setter for answer text
    public void setText(String text) {
        this.text = text;
    }

    // Setter for answer correctness
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
