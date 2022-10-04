package com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CreateHomeworkSubmissionRequest {

    @NotBlank
    private String description;
    @NotBlank
    private Integer creatorStudentID;
    @NotBlank
    private Integer homeworkID;

}
