package com.skypro.coursework2.service;

import com.skypro.coursework2.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Random random = new Random();
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question add(String question, String answer) {
        Question result = new Question(question, answer);
        questions.add(result);
        return result;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Вопрос не найден");
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Список вопросов пуст");
        }
        int randomIndex = random.nextInt(questions.size());
        return List.copyOf(questions).get(randomIndex);
    }
}