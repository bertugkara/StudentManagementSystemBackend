package com.tubitakyte.studentmanagementsystem.Exams.ServicesImpl;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DataAccess.ExamDao;
import com.tubitakyte.studentmanagementsystem.Exams.Services.ExamService;
import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ExamListToExamDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ExamToExamDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.DataAccess.LessonDao;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.Rooms.DataAccess.RoomDao;
import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.common.requests.ExamRequests.CreateExamRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.ErrorDataResult;
import com.tubitakyte.studentmanagementsystem.utilities.ErrorResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {

    private final ExamDao examDao;
    private final RoomDao roomDao;
    private final UserDao<User> userDao;
    private final LessonDao lessonDao;
    @Override
    public DataResult<ExamDTO> createExam(CreateExamRequest request) {
        Optional<Room> room= roomDao.findById(request.getRoomID());
        Optional<User> user=userDao.findById(request.getCreatorID());
        Optional<Lessons> lesson=lessonDao.findById(request.getLessonID());

        if (!(room.isPresent() && user.isPresent() && lesson.isPresent())) {
            throw new EntityNotFoundException("Please Check Inputs!");
        }
        Exams exams =new Exams(
                request.getName(),
                LocalDateTime.parse(request.getExamDateTime()),
                request.getExamDetailsAndExamTips(),
                room.get(),
                user.get(),
                lesson.get()
        );

        examDao.save(exams);

        return new SuccessDataResult<>(getOneExam(exams.getId()).getData(),"Succesfully Created");
    }


    @Override
    public DataResult<ExamDTO> getOneExam(Integer id) {
        Optional<Exams> exam= examDao.findById(id);
        if(exam.isEmpty()){
            throw new EntityNotFoundException("No exam found");
        }
       return new SuccessDataResult<>(ExamToExamDTO.convert(exam.get()).examDTO(),"Success");
    }

    @Override
    public DataResult<List<ExamDTO>> getAllExams() {
        List<Exams> examsList =examDao.findAll();
        if(examsList.isEmpty()){
            throw new EntityNotFoundException("no exam found");
        }

        return new SuccessDataResult<>(ExamListToExamDTOList.convert(examsList).examDTOList(),"Success");
    }

    @Override
    public DataResult<List<ExamDTO>> getExamsWithTheCreatorId(Integer id) {
        List<Exams> examsList =examDao.findAllByCreator_Id(id);
        if(examsList.isEmpty()){
            throw new EntityNotFoundException("no exam found");
        }

        return new SuccessDataResult<>(ExamListToExamDTOList.convert(examsList).examDTOList(),"Success");
    }

    @Override
    public DataResult<List<ExamDTO>> getExamsWithLessonId(Integer id) {
        List<Exams> examsList =examDao.findAllByLesson_Id(id);
        if(examsList.isEmpty()){
            return new ErrorDataResult("no exam found");
        }
        else {
            return new SuccessDataResult<>(ExamListToExamDTOList.convert(examsList).examDTOList(), "Success");
        }
        }
}
