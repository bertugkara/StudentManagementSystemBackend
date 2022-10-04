package com.tubitakyte.studentmanagementsystem.Homework.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.Service.FileService;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkSubmissionListToSubmissionDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkSubmissionToHomeworkSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Homework.DataAccess.HomeworkDao;
import com.tubitakyte.studentmanagementsystem.Homework.DataAccess.HomeworkSubmissionDao;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.HomeworkSubmissions;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Service.HomeworkSubmissionService;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.StudentDao;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.CreateHomeworkSubmissionRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeworksSubmissionServiceImpl implements HomeworkSubmissionService {

    private final HomeworkDao homeworkDao;

    private final StudentDao studentDao;

    private final HomeworkSubmissionDao homeworkSubmissionDao;

    private final FileService fileService;

    @Override
    public DataResult<HomeworkSubmissionDTO> createSubmissionAndAttachToHomework(CreateHomeworkSubmissionRequest request, MultipartFile multipartFile) throws IOException {

        File file = fileService.addFile(multipartFile,request.getCreatorStudentID()).getData();

        Optional<Student> student = studentDao.findById(request.getCreatorStudentID());
        if (!student.isPresent()) {
            throw new EntityNotFoundException("No student has been found");
        }
        Optional<Homework> homework = homeworkDao.findById(request.getHomeworkID());
        if (!homework.isPresent()) {
            throw new EntityNotFoundException("No homework has been found");
        }

        HomeworkSubmissions homeworkSubmission = new HomeworkSubmissions(
                request.getDescription(),
                file,
                student.get(),
                homework.get()
        );

        homeworkSubmissionDao.save(homeworkSubmission);

        return new SuccessDataResult<>(getOneSubmission(homeworkSubmission.getId()).getData(), "Successful Submission");

    }

    @Override
    public DataResult<HomeworkSubmissionDTO> getOneSubmission(Integer id) {

        Optional<HomeworkSubmissions> homeworkSubmission = homeworkSubmissionDao.findById(id);
        if (!homeworkSubmission.isPresent()) {
            throw new EntityNotFoundException("There are no such homework Submission");
        }

        return new SuccessDataResult<>(HomeworkSubmissionToHomeworkSubmissionDTO.convert(homeworkSubmission.get()).homeworkSubmissionDTO());

    }

    @Override
    public DataResult<List<HomeworkSubmissionDTO>> getAllSubmissions() {
        List<HomeworkSubmissions> homeworkSubmissionsList = homeworkSubmissionDao.findAll();
        if (homeworkSubmissionsList.isEmpty()) {
            throw new EntityNotFoundException("No submission found");
        }
        return new SuccessDataResult<>(HomeworkSubmissionListToSubmissionDTOList.convert(homeworkSubmissionsList).homeworkSubmissionsList());
    }

    @Override
    public DataResult<List<HomeworkSubmissionDTO>> getSubmissionsWithTheHomeworkId(Integer id) {
        List<HomeworkSubmissions> homeworkSubmissionsList = homeworkSubmissionDao.findAllByHomework_Id(id);
        if (homeworkSubmissionsList.isEmpty()) {
            throw new EntityNotFoundException("No submission found");
        }
        return new SuccessDataResult<>(HomeworkSubmissionListToSubmissionDTOList.convert(homeworkSubmissionsList).homeworkSubmissionsList());

    }
}
