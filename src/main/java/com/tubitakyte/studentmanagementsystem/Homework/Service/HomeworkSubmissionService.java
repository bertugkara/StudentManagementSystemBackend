package com.tubitakyte.studentmanagementsystem.Homework.Service;

import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.CreateHomeworkSubmissionRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HomeworkSubmissionService {

    DataResult<HomeworkSubmissionDTO> createSubmissionAndAttachToHomework(CreateHomeworkSubmissionRequest request, MultipartFile file) throws IOException;

    DataResult<HomeworkSubmissionDTO> getOneSubmission(Integer id);

    DataResult<List<HomeworkSubmissionDTO>> getAllSubmissions();

    DataResult<List<HomeworkSubmissionDTO>> getSubmissionsWithTheHomeworkId(Integer id);


}
