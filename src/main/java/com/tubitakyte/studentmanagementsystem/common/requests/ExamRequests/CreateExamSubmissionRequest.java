package com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class CreateExamSubmissionRequest {

    @NotBlank
    @Max(value = 500)
    private String description;
    @NotBlank
    private Integer creatorStudentID;
    @NotBlank
    private Integer examID;

}
