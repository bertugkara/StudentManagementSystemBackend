package com.tubitakyte.studentmanagementsystem.Exams.ServicesImpl;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DataAccess.ExamDao;
import com.tubitakyte.studentmanagementsystem.Exams.DataAccess.ExamSubmissionDao;
import com.tubitakyte.studentmanagementsystem.Exams.Services.ExamSubmissionService;
import com.tubitakyte.studentmanagementsystem.Exams.entity.ExamSubmissions;
import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.Service.FileService;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ConvertExamSubmissionListToExamSubmissionListDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ConvertExamSubmissionToExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.StudentDao;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests.CreateExamSubmissionRequest;
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
public class ExamSubmissionServiceImpl implements ExamSubmissionService {

    private final ExamDao examDao;
    private final StudentDao studentDao;
    private final FileService fileService;

    private final ExamSubmissionDao examSubmissionDao;

    @Override
    public DataResult<ExamSubmissionDTO> createSubmission(CreateExamSubmissionRequest request, MultipartFile multipartFile) throws IOException {

        Optional<Exams> exam=examDao.findById(request.getExamID());
        Optional<Student> student=studentDao.findById(request.getCreatorStudentID());
        File file=fileService.addFile(multipartFile,request.getCreatorStudentID()).getData();

        if(!exam.isPresent() || !student.isPresent()){
            throw new EntityNotFoundException("No exam or student found");
        }

        ExamSubmissions examSubmission=new ExamSubmissions(
                request.getDescription(),
                exam.get(),
                student.get(),
                file
        );

        examSubmissionDao.save(examSubmission);

        return new SuccessDataResult<>(getOneSubmission(examSubmission.getId()).getData(),"Success");

    }

    @Override
    public DataResult<ExamSubmissionDTO> getOneSubmission(Integer id) {

        Optional<ExamSubmissions> examSubmission=examSubmissionDao.findById(id);
        if(!examSubmission.isPresent()){
            throw new EntityNotFoundException("No submission found");
        }
        return new SuccessDataResult<>(ConvertExamSubmissionToExamSubmissionDTO.convert(examSubmission.get()).examSubmissionDTO(),"Success");
    }

    @Override
    public DataResult<List<ExamSubmissionDTO>> getAllSubmissions() {
        List<ExamSubmissions> examSubmission=examSubmissionDao.findAll();
        if(examSubmission.isEmpty()){
            throw new EntityNotFoundException("No submission found");
        }
        return new SuccessDataResult<>(ConvertExamSubmissionListToExamSubmissionListDTO.convert(examSubmission).examSubmissionsList(),"Success");
    }

    @Override
    public DataResult<List<ExamSubmissionDTO>> getSubmissionsOfTheExamId(Integer id) {
        List<ExamSubmissions> examSubmission=examSubmissionDao.findAllByExam_Id(id);
        if(examSubmission.isEmpty()){
            throw new EntityNotFoundException("No submission found");
        }
        return new SuccessDataResult<>(ConvertExamSubmissionListToExamSubmissionListDTO.convert(examSubmission).examSubmissionsList(),"Success");

    }

    @Override
    public DataResult<List<ExamSubmissionDTO>> getSubmissionsWithTheStudentId(Integer id) {
        List<ExamSubmissions> examSubmission=examSubmissionDao.findAllBySubmissioner_Id(id);
        if(examSubmission.isEmpty()){
            throw new EntityNotFoundException("No submission found");
        }
        return new SuccessDataResult<>(ConvertExamSubmissionListToExamSubmissionListDTO.convert(examSubmission).examSubmissionsList(),"Success");
    }

}
