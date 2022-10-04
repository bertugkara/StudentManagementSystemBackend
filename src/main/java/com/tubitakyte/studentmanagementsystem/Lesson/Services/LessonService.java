package com.tubitakyte.studentmanagementsystem.Lesson.Services;

import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.AttachHomeworkToLessonRequest;
import com.tubitakyte.studentmanagementsystem.common.requests.LessonRelatedRequest.*;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.Result;

import java.util.List;


public interface LessonService {

    DataResult<LessonDTO> addLesson(LessonCreateRequest request);

    DataResult<List<LessonDTO>> getAll();

    DataResult<LessonDTO> getOneLesson(int id);

    DataResult<LessonDTO> addAnAssistantToLesson(AddAssistantToLessonRequest request);

    DataResult<LessonDTO> lessonUpdate(LessonUpdateRequest request);

    DataResult<LessonDTO> addHomeworkToLesson(AttachHomeworkToLessonRequest request);

    DataResult<LessonDTO> updateLessonForProfessor(LessonUpdateRequestFromProfessor
                                                    request);

    DataResult<LessonDTO> updateLessonForAssistant(LessonUpdateRequestFromAssistant
                                                           request);

    Result addStudentToTheLesson(Integer lessonID, Integer studentID);
}
