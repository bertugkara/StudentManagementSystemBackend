package com.tubitakyte.studentmanagementsystem.Homework.Controller;

import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Service.HomeworkSubmissionService;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.CreateHomeworkSubmissionRequest;
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
@RequestMapping(path = "api/homeworkSubmissions")
public class HomeworkSubmissionController {

    private final HomeworkSubmissionService homeworkSubmissionService;

    @PostMapping( value = "/createSubmission")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<HomeworkSubmissionDTO> createSubmission(@RequestPart("request") @Valid CreateHomeworkSubmissionRequest request,
                                                              @RequestPart("file")MultipartFile file) throws IOException {
        return homeworkSubmissionService.createSubmissionAndAttachToHomework(request,file);
    }

    @GetMapping("/getOneSubmission")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<HomeworkSubmissionDTO> getOneSubmission(@RequestParam @NotBlank Integer id){
        return homeworkSubmissionService.getOneSubmission(id);
    }

    @GetMapping("/getAllSubmissions")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<HomeworkSubmissionDTO>> getAllSubmissions(){
        return homeworkSubmissionService.getAllSubmissions();
    }

    @GetMapping("/getSubmissionsWithTheHomeworkId")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    public DataResult<List<HomeworkSubmissionDTO>> getSubmissionsWithTheHomeworkId(@RequestParam @NotBlank Integer id){
        return homeworkSubmissionService.getSubmissionsWithTheHomeworkId(id);
    }
}
