package com.tubitakyte.studentmanagementsystem.Exams.controller;


import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Exams.Services.ExamSubmissionService;
import com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests.CreateExamSubmissionRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "api/examSubmission")
public class ExamSubmissionController {

    private final ExamSubmissionService examSubmissionService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @PostMapping("/createSubmission")
    public DataResult<ExamSubmissionDTO> createSubmission(@RequestPart("request") @Valid CreateExamSubmissionRequest request
                                                            , @RequestPart("file")MultipartFile file) throws IOException {
        return examSubmissionService.createSubmission(request,file);
    }

    @GetMapping("/getOneSubmission")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<ExamSubmissionDTO> getOneSubmission(@RequestParam @NotBlank Integer id) {
        return examSubmissionService.getOneSubmission(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping("/getOneSubmissionWithStudentId")
    public DataResult<List<ExamSubmissionDTO>> getOneSubmissionWithStudentId(@RequestParam @NotBlank Integer id) {
        return examSubmissionService.getSubmissionsWithTheStudentId(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    @GetMapping("/getSubmissionsOfTheExamId")
    public DataResult<List<ExamSubmissionDTO>> getSubmissionsOfTheExamId(@RequestParam @NotBlank Integer id) {
        return examSubmissionService.getSubmissionsOfTheExamId(id);
    }


}
