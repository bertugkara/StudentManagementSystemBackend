package com.tubitakyte.studentmanagementsystem.Exams.controller;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.Services.ExamService;
import com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests.CreateExamRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/exam")
public class ExamController {

    private final ExamService examService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @PostMapping("/createExam")
    public DataResult<ExamDTO> createExam(@RequestBody @Valid CreateExamRequest request) {
        return examService.createExam(request);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping("/getOneExam")
    public DataResult<ExamDTO> getOneExam(@RequestParam Integer id) {
        return examService.getOneExam(id);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping("/getExamsWithCreatorId")
    public DataResult<List<ExamDTO>> getExamsWithCreatorId(@RequestParam Integer id) {
        return examService.getExamsWithTheCreatorId(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping("/getExamsWithTheLessonId")
    public DataResult<List<ExamDTO>> getExamsWithTheLessonId(@RequestParam Integer id) {
        return examService.getExamsWithLessonId(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping("/getAllExams")
    public DataResult<List<ExamDTO>> getAllExams() {
        return examService.getAllExams();
    }

}
