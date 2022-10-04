package com.tubitakyte.studentmanagementsystem.Exams.Services;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests.CreateExamRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;

public interface ExamService {

    DataResult<ExamDTO> createExam(CreateExamRequest request);

    DataResult<ExamDTO> getOneExam(Integer id);

    DataResult<List<ExamDTO>> getAllExams();

    DataResult<List<ExamDTO>> getExamsWithTheCreatorId(Integer id);

    DataResult<List<ExamDTO>> getExamsWithLessonId(Integer id);

}
