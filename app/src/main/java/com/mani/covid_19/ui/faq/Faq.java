package com.mani.covid_19.ui.faq;

public class Faq {
    private String question;
    private String answer;

    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
