package com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttachHomeworkToLessonRequest {
    Integer homeworkID;
    Integer lessonID;
}
