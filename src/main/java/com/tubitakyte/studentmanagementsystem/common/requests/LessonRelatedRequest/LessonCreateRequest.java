package com.tubitakyte.studentmanagementsystem.common.requests.LessonRelatedRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LessonCreateRequest {
    @NotBlank
    String name;
    @NotBlank
    String description;
    @NotBlank
    String lessonType;
    @NotBlank
    String lessonCode;
    @NotBlank
    Integer roomID;
    @NotBlank
    List<Integer> timeIDList;
    @NotBlank
    Integer instructorID;
    @NotBlank
    List<Integer> assistantList;
    @NotBlank
    List<Integer> studentList;
}
