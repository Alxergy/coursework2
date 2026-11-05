package com.skypro.coursework2.service;
import com.skypro.coursework2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;
    private Question q1;
    private Question q2;

    @BeforeEach
    void createExaminerServiceImpl() {
        q1 = new Question("q1", "a1");
        q2 = new Question("q2", "a2");
    }

    @Test
    void givenAmount_whenGet_thenReturnUnique() {
        when(questionService.getAll()).thenReturn(Set.of(q1, q2));
        when(questionService.getRandomQuestion()).thenReturn(q1, q2);
        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(Set.of(q1, q2), result);
    }

    @Test
    void givenZeroAmount_whenGet_thenThrowException() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(0));
    }

    @Test
    void givenNegativeAmount_whenGet_thenThrowException() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(-1));
    }

    @Test
    void givenAmountGreater_whenGet_thenThrowException() {
        when(questionService.getAll()).thenReturn(Set.of(q1, q2));
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(3));
    }
}