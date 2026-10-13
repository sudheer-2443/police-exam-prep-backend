
package com.policeexam.backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamResultController {

    private final ExamResultRepository repository;

    public ExamResultController(ExamResultRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ExamResult> getAllResults() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ExamResult getResultById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public ExamResult addResult(@RequestBody ExamResult result) {
        return repository.save(result);
    }

    @PutMapping("/{id}")
    public ExamResult updateResult(
            @PathVariable Long id,
            @RequestBody ExamResult updatedResult) {

        return repository.findById(id).map(result -> {
            result.setStudentName(updatedResult.getStudentName());
            result.setTestName(updatedResult.getTestName());
            result.setScore(updatedResult.getScore());
            result.setTotalQuestions(updatedResult.getTotalQuestions());
            return repository.save(result);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
