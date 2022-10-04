package com.tubitakyte.studentmanagementsystem.Homework.Service;

import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.HomeworkCreateRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;

public interface HomeworkService {

    DataResult<HomeworkDTO> createHomework(HomeworkCreateRequest request);

    DataResult<HomeworkDTO> getOneHomework(Integer id);

    DataResult<List<HomeworkDTO>> getAllHomeworks();

    DataResult<List<HomeworkDTO>> getHomeworksBelongsToLessonId(Integer id);

}
