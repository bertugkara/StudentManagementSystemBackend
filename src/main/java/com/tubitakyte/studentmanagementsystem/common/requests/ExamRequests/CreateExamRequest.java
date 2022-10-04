package com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CreateExamRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String examDateTime;
    @NotBlank
    @Size(max = 500)
    private String examDetailsAndExamTips;
    @NotBlank
    private Integer roomID;
    @NotBlank
    private Integer creatorID;
    @NotBlank
    private Integer lessonID;

}
