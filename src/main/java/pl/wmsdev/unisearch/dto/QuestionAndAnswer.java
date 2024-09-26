package pl.wmsdev.unisearch.dto;

public record QuestionAndAnswer(String question, String answer) {


    public static QuestionAndAnswer from(String question, String answer) {
        return new QuestionAndAnswer(question, answer);
    }

    @Override
    public String toString() {
        return question + "\n" + answer + "\n";
    }
}
