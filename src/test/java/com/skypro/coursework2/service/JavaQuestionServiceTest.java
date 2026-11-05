package com.skypro.coursework2.service;

import com.skypro.coursework2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;
    private Question q1;
    private Question q2;

    @BeforeEach
    void createJavaQuestionService() {
        javaQuestionService = new JavaQuestionService();
        q1 = new Question("q1", "a1");
        q2 = new Question("q2", "a2");
    }

    @Test
    void givenQuestion_whenAdd_thenAddedAndReturned() {
        Question result = javaQuestionService.add(q1);
        assertEquals(q1, result);
        assertTrue(javaQuestionService.getAll().contains(q1));
    }

    @Test
    void givenQuestionStrings_whenAdd_thenCreatedAndReturned() {
        Question result = javaQuestionService.add("q1", "a1");
        assertEquals(q1, result);
        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    void givenDuplicateQuestion_whenAdd_thenNotAdded() {
        javaQuestionService.add(q1);
        javaQuestionService.add(new Question("q1", "a1"));
        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    void givenQuestion_whenRemove_thenRemovedAndReturned() {
        javaQuestionService.add(q1);
        Question removed = javaQuestionService.remove(new Question("q1", "a1"));
        assertEquals(q1, removed);
        assertTrue(javaQuestionService.getAll().isEmpty());
    }

    @Test
    void givenEmptyStorage_whenRemove_thenThrowException() {
        assertThrows(ResponseStatusException.class, () -> javaQuestionService.remove(q1));
    }

    @Test
    void givenStorage_whenGetAll_thenReturnAll() {
        javaQuestionService.add(q1);
        javaQuestionService.add(q2);
        Collection<Question> all = javaQuestionService.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(q1));
        assertTrue(all.contains(q2));
    }

    @Test
    void givenStorage_whenGetRandom_thenReturnRandom() {
        javaQuestionService.add(q1);
        javaQuestionService.add(q2);
        Question random = javaQuestionService.getRandomQuestion();
        assertTrue(random.equals(q1) || random.equals(q2));
    }

    @Test
    void givenEmptyStorage_whenGetRandom_thenThrowException() {
        assertThrows(ResponseStatusException.class, () -> javaQuestionService.getRandomQuestion());
    }
}