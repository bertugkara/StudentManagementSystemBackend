package com.tubitakyte.studentmanagementsystem.Homework.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkListToHomeworkDtoList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkToHomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Homework.DataAccess.HomeworkDao;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Service.HomeworkService;
import com.tubitakyte.studentmanagementsystem.Lesson.DataAccess.LessonDao;
import com.tubitakyte.studentmanagementsystem.Lesson.Services.LessonService;
import com.tubitakyte.studentmanagementsystem.Role.entity.Role;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.AssistantDao;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.AttachHomeworkToLessonRequest;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.HomeworkCreateRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.ErrorDataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeworksServiceImpl implements HomeworkService {

    private final LessonDao lessonDao;

    private final AssistantDao assistantDao;

    private final HomeworkDao homeworkDao;

    private final LessonService lessonService;

    private final UserDao<User> userDao;


    @Override
    public DataResult<HomeworkDTO> createHomework(HomeworkCreateRequest request) {

        Optional<User> user= userDao.findById(request.getCreator_id());

        if( !user.isPresent() ){
            throw new EntityNotFoundException("There no such Creator!");
        }

        controlIsUserStudent(user.get());

        Homework homework = new Homework(
                LocalDate.parse(request.getStartDate()),
                LocalDate.parse(request.getEndDate()),
                LocalTime.parse(request.getStartTime()),
                LocalTime.parse(request.getEndTime()),
                request.getDescription(),
                lessonDao.findById(request.getLesson_id()).get(),
                assistantDao.findById(request.getResponsible_assistant_ID()).get(),
                user.get()
        );

        homeworkDao.save(homework);

        lessonService.addHomeworkToLesson(new AttachHomeworkToLessonRequest((homework.getId()), request.getLesson_id()));

        if (homeworkDao.findById(homework.getId()).isPresent()) {
            return new SuccessDataResult<>(getOneHomework(homework.getId()).getData(), "Homework Successfully created.");
        } else {
            return new ErrorDataResult<>("Error Occured");
        }
    }

    private void controlIsUserStudent(User user) {
        Set<Role> userRoles= user.getRoles();
        userRoles.forEach((tempRole)->{
            if(tempRole.getName().name()=="ROLE_STUDENT"){
                throw new RuntimeException("Student can not create Homework");
            }
        });
    }

    @Override
    public DataResult<HomeworkDTO> getOneHomework(Integer id) {

        Optional<Homework> homework = homeworkDao.findById(id);
        if (homework.isPresent()) {
            return new SuccessDataResult<>(HomeworkToHomeworkDTO.convertToDTO(homework.get()).homeworkDTO(), "data Successfully fetched");
        } else {
            return new ErrorDataResult<>("Error occured");
        }
    }

    @Override
    public DataResult<List<HomeworkDTO>> getAllHomeworks() {
        List<Homework> homeworkList = homeworkDao.findAll();
        if (homeworkList.isEmpty()) {
            throw new EntityNotFoundException("Homeworks could not found");
        }

        return new SuccessDataResult<>(HomeworkListToHomeworkDtoList.convertToDTO(homeworkList).homeworkDTOList(), "Data successfully fetched.");

    }

    @Override
    public DataResult<List<HomeworkDTO>> getHomeworksBelongsToLessonId(Integer id) {

        List<Homework> homeworkList=homeworkDao.findAllByLesson_Id(id);
        if(homeworkList.isEmpty()){
            return new ErrorDataResult("No Homeworks created for this Lesson!");
        }
        else {
            return new SuccessDataResult<>(HomeworkListToHomeworkDtoList.convertToDTO(homeworkList).homeworkDTOList());
        }

    }
}
