package com.tubitakyte.studentmanagementsystem.User.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DataAccess.ExamDao;
import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ExamListToExamDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkListToHomeworkDtoList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions.ConvertLessonsListToLessonDTOList;
import com.tubitakyte.studentmanagementsystem.Homework.DataAccess.HomeworkDao;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.DataAccess.LessonDao;
import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.ProfessorDao;
import com.tubitakyte.studentmanagementsystem.User.Services.ProfessorService;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.AssistantDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.ProfessorDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.ProfessorDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Professor;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    private final  ProfessorDao professorDao;
    private final  LessonDao lessonDao;
    private final HomeworkDao homeworkDao;

    private final ExamDao examDao;
    @Override
    public DataResult<ProfessorDTO> getByEmail(String email) {
        Professor optionalProfessor=professorDao.findByEmail(email);
        if(optionalProfessor ==null){
            throw new UsernameNotFoundException("email could not found!");
        }

        List<Lessons> lessonProfessorTeaches=lessonDao.findByInstructor_Id(optionalProfessor.getId());
        List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonProfessorTeaches).lessonsList();

        return new SuccessDataResult<>(new ProfessorDTO(
                optionalProfessor.getId(),
                optionalProfessor.getFirstName(),
                optionalProfessor.getEmail(),
                optionalProfessor.getUsername(),
                optionalProfessor.getLastName(),
                optionalProfessor.getRoles(),
                convertedList));
    }

    @Override
    public DataResult<ProfessorDTO> getByUserID(int id) {
        Optional<Professor> optionalProfessor=professorDao.findById(id);
        if(!optionalProfessor.isPresent()){
            throw new UsernameNotFoundException("Professor associated with the given id could not found!");
        }
        List<Lessons> lessonProfessorTeaches=lessonDao.findByInstructor_Id(optionalProfessor.get().getId());
        List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonProfessorTeaches).lessonsList();

        return new SuccessDataResult<>(new ProfessorDTO(
                optionalProfessor.get().getId(),
                optionalProfessor.get().getFirstName(),
                optionalProfessor.get().getEmail(),
                optionalProfessor.get().getUsername(),
                optionalProfessor.get().getLastName(),
                optionalProfessor.get().getRoles(),
                convertedList));
    }

    @Override
    public DataResult<List<ProfessorDTO>> getAll() {
        List<Professor> optionalProfessor=professorDao.findAll();
        if(optionalProfessor ==null){
            throw new UsernameNotFoundException("email could not found!");
        }
        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        optionalProfessor.forEach((tempProf) -> {

            List<Lessons> lessonAssistantTeaches=lessonDao.findByInstructor_Id(tempProf.getId());
            List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            ProfessorDTO professorDTO = new ProfessorDTO(tempProf.getId(),
                    tempProf.getFirstName(),
                    tempProf.getEmail(),
                    tempProf.getUsername(),
                    tempProf.getLastName(),
                    tempProf.getRoles(),
                    convertedList);
            professorDTOList.add(professorDTO);
        });

        return new SuccessDataResult<>(professorDTOList);
    }

    @Override
    public DataResult<List<ProfessorDTO>> getAllActiveUsers() {
        List<Professor> optionalProfessor=professorDao.findAllByIsActiveTrue();
        if(optionalProfessor ==null){
            throw new UsernameNotFoundException("email could not found!");
        }
        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        optionalProfessor.forEach((tempProf) -> {

            List<Lessons> lessonAssistantTeaches=lessonDao.findByInstructor_Id(tempProf.getId());
            List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            ProfessorDTO professorDTO = new ProfessorDTO(tempProf.getId(),
                    tempProf.getFirstName(),
                    tempProf.getEmail(),
                    tempProf.getUsername(),
                    tempProf.getLastName(),
                    tempProf.getRoles(),
                    convertedList);
            professorDTOList.add(professorDTO);
        });

        return new SuccessDataResult<>(professorDTOList);
    }

    @Override
    public DataResult<List<ProfessorDTO>> getAllDeactiveUsers() {
        List<Professor> optionalProfessor=professorDao.findAllByIsActiveFalse();
        if(optionalProfessor ==null){
            throw new UsernameNotFoundException("email could not found!");
        }
        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        optionalProfessor.forEach((tempProf) -> {

            List<Lessons> lessonAssistantTeaches=lessonDao.findByInstructor_Id(tempProf.getId());
            List<LessonDTO> convertedList= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            ProfessorDTO professorDTO = new ProfessorDTO(tempProf.getId(),
                    tempProf.getFirstName(),
                    tempProf.getEmail(),
                    tempProf.getUsername(),
                    tempProf.getLastName(),
                    tempProf.getRoles(),
                    convertedList);
            professorDTOList.add(professorDTO);
        });

        return new SuccessDataResult<>(professorDTOList);
    }

    @Override
    public DataResult<ProfessorDescribeMe> describeMe(Integer userID) {
        Professor professor = professorDao.findById(userID).get();

        List<Lessons> lessonsList = lessonDao.findByInstructor_Id(userID);

        List<Homework> tempHomeworks=new ArrayList<>(); //ALL THE HOMEWORKS THAT Assistant RESPONSIBLE or Created
        tempHomeworks.addAll(homeworkDao.findAllByCreator_Id(userID));

        List<Exams> tempExams=new ArrayList<>(); //ALL THE EXAMS THAT Assistant RESPONSIBLE
        tempExams.addAll(examDao.findAllByCreator_Id(userID));

        //DTO CONVERSION TIME
        List<ExamDTO> examDTOList= ExamListToExamDTOList.convert(tempExams).examDTOList();
        List<HomeworkDTO> homeworkDTOList= HomeworkListToHomeworkDtoList.convertToDTO(tempHomeworks).homeworkDTOList();
        List<LessonDTO> lessonsEnrolled= ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonsList).lessonsList();

        ProfessorDescribeMe professorDescribeMe = new ProfessorDescribeMe(
                professor.getId(),
                professor.getFirstName(),
                professor.getEmail(),
                professor.getUsername(),
                professor.getLastName(),
                professor.getRoles(),
                examDTOList,
                homeworkDTOList,
                lessonsEnrolled
        );
        return new SuccessDataResult<>(professorDescribeMe,"Successfully Fetched");
    }

    @Override
    public List<ProfessorDTO> getProfessorsNotLessonsAttendedToGivenLessonCode(Integer lessonCode) {
        List<Professor> professors = professorDao.findAll();
        Optional<Lessons> lesson = lessonDao.findById(lessonCode);
        List<ProfessorDTO> professorDTOList = new ArrayList<>();

        if (lesson.isEmpty()) {
            throw new UsernameNotFoundException("lesson does not exist!");
        }

        List<Professor> professorToConvertToDTO = new ArrayList<>();

            professors.forEach((tempProfessor) -> {
                if (tempProfessor.getId() != lesson.get().getInstructor().getId()) {
                    professorToConvertToDTO.add(tempProfessor);
                }
            });


        professorDTOList = returnListOfProfessor(professorToConvertToDTO);
        return professorDTOList;
    }

    @Override
    public List<ProfessorDTO> returnListOfProfessor(List<Professor> professorList) {
        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        professorList.forEach((tempAssistant) -> {
            ProfessorDTO professorDTO = new ProfessorDTO(tempAssistant.getId(),
                    tempAssistant.getFirstName(),
                    tempAssistant.getEmail(),
                    tempAssistant.getUsername(),
                    tempAssistant.getLastName(),
                    tempAssistant.getRoles(),null);
            professorDTOList.add(professorDTO);
        });
        return professorDTOList;
    }
}
