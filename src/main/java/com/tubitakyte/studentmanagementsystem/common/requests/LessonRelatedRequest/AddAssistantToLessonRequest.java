package com.tubitakyte.studentmanagementsystem.common.requests.LessonRelatedRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddAssistantToLessonRequest {
    @NotBlank
    List<Integer> assistantList;
    @NotBlank
    Integer lessonCode;
}
