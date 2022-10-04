package com.tubitakyte.studentmanagementsystem.Exams.Services;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests.CreateExamSubmissionRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExamSubmissionService {

    DataResult<ExamSubmissionDTO> createSubmission(CreateExamSubmissionRequest request, MultipartFile multipartFile) throws IOException;

    DataResult<ExamSubmissionDTO> getOneSubmission(Integer id);

    DataResult<List<ExamSubmissionDTO>> getAllSubmissions();

    DataResult<List<ExamSubmissionDTO>> getSubmissionsOfTheExamId(Integer id);

    DataResult<List<ExamSubmissionDTO>> getSubmissionsWithTheStudentId(Integer id);

}
