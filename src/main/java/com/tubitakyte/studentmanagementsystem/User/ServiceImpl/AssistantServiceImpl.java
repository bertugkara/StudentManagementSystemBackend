package com.tubitakyte.studentmanagementsystem.User.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DataAccess.ExamDao;
import com.tubitakyte.studentmanagementsystem.Exams.entity.ExamSubmissions;
import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ConvertExamSubmissionListToExamSubmissionListDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ExamListToExamDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkListToHomeworkDtoList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkSubmissionListToSubmissionDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions.ConvertLessonsListToLessonDTOList;
import com.tubitakyte.studentmanagementsystem.Homework.DataAccess.HomeworkDao;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.HomeworkSubmissions;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.DataAccess.LessonDao;
import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.AssistantDao;
import com.tubitakyte.studentmanagementsystem.User.Services.AssistantService;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.AssistantDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.AssistantDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.StudentDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AssistantServiceImpl implements AssistantService {

    private final AssistantDao assistantDao;

    private final  LessonDao lessonDao;

    private final ExamDao examDao;

    private final HomeworkDao homeworkDao;

    @Override
    public DataResult<AssistantDTO> getByEmail(String email) {
        Assistant optionalAssistant = assistantDao.findByEmail(email);
        if (optionalAssistant == null) {
            throw new UsernameNotFoundException("email could not found!");
        }

        List<Lessons> lessonAssistantTeaches=lessonDao.findByAssistants_Id(optionalAssistant.getId());
        List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

        return new SuccessDataResult<>(new AssistantDTO(
        optionalAssistant.getId(),
        optionalAssistant.getFirstName(),
        optionalAssistant.getEmail(),
        optionalAssistant.getUsername(),
        optionalAssistant.getLastName(),
        optionalAssistant.getRoles(),
        convertedList));
    }

    @Override
    public DataResult<AssistantDTO> getByUserID(int id) {
        Optional<Assistant> optionalAssistant = assistantDao.findById(id);
        if (!optionalAssistant.isPresent()) {
            throw new UsernameNotFoundException("Assistant associated with the given id could not found!");
        }
        List<Lessons> lessonAssistantTeaches=lessonDao.findByAssistants_Id(optionalAssistant.get().getId());
        List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

        return new SuccessDataResult<>( new AssistantDTO(
                optionalAssistant.get().getId(),
                optionalAssistant.get().getFirstName(),
                optionalAssistant.get().getEmail(),
                optionalAssistant.get().getUsername(),
                optionalAssistant.get().getLastName(),
                optionalAssistant.get().getRoles(),
                convertedList));
    }

    @Override
    public DataResult<List<AssistantDTO>> getAll() {
        List<Assistant> optionalAssistant = assistantDao.findAll();
        if (optionalAssistant.isEmpty()) {
            throw new UsernameNotFoundException("email could not found!");
        }

        List<AssistantDTO> assistantDTOList = new ArrayList<>();
        optionalAssistant.forEach((tempStudent) -> {

            List<Lessons> lessonAssistantTeaches=lessonDao.findByAssistants_Id(tempStudent.getId());
            List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            AssistantDTO assistantDTO = new AssistantDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles(),
                    convertedList);
            assistantDTOList.add(assistantDTO);
        });

        return new SuccessDataResult<>(assistantDTOList);
    }

    @Override
    public DataResult<List<AssistantDTO>> getAllActiveUsers() {
        List<Assistant> optionalAssistant = assistantDao.findAllByIsActiveTrue();
        if (optionalAssistant.isEmpty()) {
            throw new UsernameNotFoundException("email could not found!");
        }

        List<AssistantDTO> assistantDTOList = new ArrayList<>();
        optionalAssistant.forEach((tempStudent) -> {

            List<Lessons> lessonAssistantTeaches=lessonDao.findByAssistants_Id(tempStudent.getId());
            List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            AssistantDTO assistantDTO = new AssistantDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles(),
                    convertedList);
            assistantDTOList.add(assistantDTO);
        });

        return new SuccessDataResult<>(assistantDTOList);
    }

    @Override
    public DataResult<List<AssistantDTO>> getAllDeactiveUsers() {
        List<Assistant> optionalAssistant = assistantDao.findAllByIsActiveFalse();
        if (optionalAssistant.isEmpty()) {
            throw new UsernameNotFoundException("email could not found!");
        }

        List<AssistantDTO> assistantDTOList = new ArrayList<>();
        optionalAssistant.forEach((tempStudent) -> {

            List<Lessons> lessonAssistantTeaches=lessonDao.findByAssistants_Id(tempStudent.getId());
            List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            AssistantDTO assistantDTO = new AssistantDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles(),
                    convertedList);
            assistantDTOList.add(assistantDTO);
        });

        return new SuccessDataResult<>(assistantDTOList);
    }

    @Override
    public DataResult<AssistantDescribeMe> describeMe(Integer userID) {
        Assistant assistant = assistantDao.findById(userID).get();

        List<Lessons> lessonsList = lessonDao.findByAssistants_Id(userID);
        if(lessonsList.isEmpty() ){
            throw new EntityNotFoundException("No Lesson or Assistant found");
        }

        List<Homework> tempHomeworks=new ArrayList<>(); //ALL THE HOMEWORKS THAT Assistant RESPONSIBLE or Created
        tempHomeworks.addAll(homeworkDao.findAllByAssistant_Id(userID));
        tempHomeworks.addAll(homeworkDao.findAllByCreator_Id(userID));

        List<Exams> tempExams=new ArrayList<>(); //ALL THE EXAMS THAT Assistant RESPONSIBLE
        tempExams.addAll(examDao.findAllByCreator_Id(userID));

        //DTO CONVERSION TIME
        List<ExamDTO> examDTOList= ExamListToExamDTOList.convert(tempExams).examDTOList();
        List<HomeworkDTO> homeworkDTOList= HomeworkListToHomeworkDtoList.convertToDTO(tempHomeworks).homeworkDTOList();
        List<LessonDTO> lessonsEnrolled= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonsList).lessonsList();

        AssistantDescribeMe assistantDescribeMe = new AssistantDescribeMe(
                assistant.getId(),
                assistant.getFirstName(),
                assistant.getEmail(),
                assistant.getUsername(),
                assistant.getLastName(),
                assistant.getRoles(),
                examDTOList,
                homeworkDTOList,
                lessonsEnrolled
        );
        return new SuccessDataResult<>(assistantDescribeMe,"Successfully Fetched");
    }

    @Override
    public List<AssistantDTO> getAssistantsNotLessonsAttendedToGivenLessonCode(Integer lessonCode) {

        List<Assistant> assistants = assistantDao.findAll();
        Optional<Lessons> lesson = lessonDao.findById(lessonCode);
        List<AssistantDTO> assistantDTOList = new ArrayList<>();

        if (lesson.isEmpty()) {
            throw new UsernameNotFoundException("lesson does not exist!");
        }

        List<Assistant> assistantToSentToDtoFunction = new ArrayList<>();

        lesson.get().getAssistants().forEach((tempLessonAttendedAssistant -> assistants.forEach((tempAssistant) -> {
            if (!Objects.equals(tempLessonAttendedAssistant.getId(), tempAssistant.getId())) {
                assistantToSentToDtoFunction.add(tempAssistant);
            }
        })));

        assistantDTOList = returnListOfAssistantDTO(assistantToSentToDtoFunction);
        return assistantDTOList;
    }

    @Override
    public List<AssistantDTO> returnListOfAssistantDTO(List<Assistant> AssistantList) {

        List<AssistantDTO> assistantDTOList = new ArrayList<>();
        AssistantList.forEach((tempAssistant) -> {
            AssistantDTO assistantDTO = new AssistantDTO(tempAssistant.getId(),
                    tempAssistant.getFirstName(),
                    tempAssistant.getEmail(),
                    tempAssistant.getUsername(),
                    tempAssistant.getLastName(),
                    tempAssistant.getRoles(),null);
            assistantDTOList.add(assistantDTO);
        });
        return assistantDTOList;
    }



}
