package com.tubitakyte.studentmanagementsystem.common.requests.LessonRelatedRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LessonUpdateRequest {
    @NotBlank
    Integer id;
    String name;
    String description;
    String lessonType;
    String lessonCode;
    Integer rooms;
    List<Integer> timeIDList;
    Integer instructorID;
    List<Integer> assistantList;
    List<Integer> studentList;
}
