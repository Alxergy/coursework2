package com.skypro.coursework2.service;

import com.skypro.coursework2.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Количество вопросов должно быть больше 0");
        }
        if (amount > questionService.getAll().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Превышено доступное количество вопросов");
        }
        Set<Question> list = new HashSet<>();
        while (list.size() < amount) {
            list.add(questionService.getRandomQuestion());
        }
        return list;
    }
}