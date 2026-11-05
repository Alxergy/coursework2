package com.skypro.coursework2.service;

import com.skypro.coursework2.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(Question question);

    Question add(String question, String answer);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}