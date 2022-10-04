package com.tubitakyte.studentmanagementsystem.common.requests.LessonRelatedRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LessonUpdateRequestFromAssistant {

    @NotBlank
    Integer id;
    String name;
    String description;
    Integer rooms;
}
