
package com.policeexam.backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamQuestionController {

    private final ExamQuestionRepository repository;

    public ExamQuestionController(
            ExamQuestionRepository repository) {
        this.repository = repository;
    }

    // READ: Get all questions
    @GetMapping
    public List<ExamQuestion> getAllQuestions() {
        return repository.findAll();
    }

    // READ: Get questions by category
    @GetMapping("/category/{category}")
    public List<ExamQuestion> getByCategory(
            @PathVariable String category) {
        return repository.findByCategory(category);
    }

    // CREATE: Add a question
    @PostMapping
    public ExamQuestion addQuestion(
            @RequestBody ExamQuestion question) {
        return repository.save(question);
    }

    // UPDATE: Edit a question
    @PutMapping("/{id}")
    public ExamQuestion updateQuestion(
            @PathVariable Long id,
            @RequestBody ExamQuestion updatedQuestion) {

        return repository.findById(id).map(question -> {
            question.setCategory(updatedQuestion.getCategory());
            question.setQuestionText(
                    updatedQuestion.getQuestionText());
            question.setOptionA(updatedQuestion.getOptionA());
            question.setOptionB(updatedQuestion.getOptionB());
            question.setOptionC(updatedQuestion.getOptionC());
            question.setOptionD(updatedQuestion.getOptionD());
            question.setCorrectAnswer(
                    updatedQuestion.getCorrectAnswer());

            return repository.save(question);
        }).orElse(null);
    }

    // DELETE: Remove a question
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
