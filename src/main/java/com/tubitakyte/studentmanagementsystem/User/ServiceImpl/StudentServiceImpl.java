package com.tubitakyte.studentmanagementsystem.User.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Exams.DataAccess.ExamDao;
import com.tubitakyte.studentmanagementsystem.Exams.DataAccess.ExamSubmissionDao;
import com.tubitakyte.studentmanagementsystem.Exams.entity.ExamSubmissions;
import com.tubitakyte.studentmanagementsystem.Exams.entity.Exams;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ConvertExamSubmissionListToExamSubmissionListDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion.ExamListToExamDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkListToHomeworkDtoList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions.HomeworkSubmissionListToSubmissionDTOList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.LessonConversions.ConvertLessonsListToLessonDTOList;
import com.tubitakyte.studentmanagementsystem.Homework.DataAccess.HomeworkSubmissionDao;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.HomeworkSubmissions;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkDTO;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.DataAccess.LessonDao;
import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.StudentDao;
import com.tubitakyte.studentmanagementsystem.User.Services.StudentService;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.StudentDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final LessonDao lessonDao;

    private final ExamDao examDao;

    private final ExamSubmissionDao examSubmissionDao;

    private final HomeworkSubmissionDao homeworkSubmissionDao;

    @Override
    public DataResult<StudentDTO> getByEmail(String email) {
        Student optionalStudent = studentDao.findByEmail(email);
        if (optionalStudent == null) {
            throw new UsernameNotFoundException("email could not found!");
        }
        List<Lessons> lessonStudentAttends = lessonDao.findByStudents_Id(optionalStudent.getId());
        List<LessonDTO> convertedList = ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonStudentAttends).lessonsList();

        return new SuccessDataResult<>(new StudentDTO(
                optionalStudent.getId(),
                optionalStudent.getFirstName(),
                optionalStudent.getEmail(),
                optionalStudent.getUsername(),
                optionalStudent.getLastName(),
                optionalStudent.getRoles(),
                convertedList));
    }


    @Override
    public DataResult<StudentDTO> getByUserID(int id) {
        Optional<Student> optionalStudent = studentDao.findById(id);
        if (!optionalStudent.isPresent()) {
            throw new UsernameNotFoundException("Student associated with the given id could not found!");
        }
        List<Lessons> lessonStudentAttends = lessonDao.findByStudents_Id(optionalStudent.get().getId());
        List<LessonDTO> convertedList = ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonStudentAttends).lessonsList();

        return new SuccessDataResult<>(new StudentDTO(
                optionalStudent.get().getId(),
                optionalStudent.get().getFirstName(),
                optionalStudent.get().getEmail(),
                optionalStudent.get().getUsername(),
                optionalStudent.get().getLastName(),
                optionalStudent.get().getRoles(),
                convertedList));
    }

    @Override
    public DataResult<List<StudentDTO>> getAll() {
        List<Student> optionalStudent = studentDao.findAll();
        if (optionalStudent.isEmpty()) {
            throw new UsernameNotFoundException("email could not found!");
        }
        List<StudentDTO> studentDTOList = new ArrayList<>();
        optionalStudent.forEach((tempStudent) -> {

            List<Lessons> lessonAssistantTeaches = lessonDao.findByStudents_Id(tempStudent.getId());
            List<LessonDTO> convertedList = ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            StudentDTO studentDTO = new StudentDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles(),
                    convertedList);
            studentDTOList.add(studentDTO);
        });

        return new SuccessDataResult<>(studentDTOList);
    }

    @Override
    public DataResult<List<StudentDTO>> getAllActiveUsers() {
        List<Student> optionalStudent = studentDao.findAllByIsActiveTrue();
        if (optionalStudent == null) {
            throw new UsernameNotFoundException("email could not found!");
        }
        List<StudentDTO> studentDTOList = new ArrayList<>();
        optionalStudent.forEach((tempStudent) -> {

            List<Lessons> lessonAssistantTeaches = lessonDao.findByStudents_Id(tempStudent.getId());
            List<LessonDTO> convertedList = ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            StudentDTO studentDTO = new StudentDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles(),
                    convertedList);
            studentDTOList.add(studentDTO);
        });

        return new SuccessDataResult<>(studentDTOList);
    }

    @Override
    public DataResult<List<StudentDTO>> getAllDeactiveUsers() {
        List<Student> optionalStudent = studentDao.findAllByIsActiveFalse();
        if (optionalStudent == null) {
            throw new UsernameNotFoundException("email could not found!");
        }
        List<StudentDTO> studentDTOList = new ArrayList<>();
        optionalStudent.forEach((tempStudent) -> {

            List<Lessons> lessonAssistantTeaches = lessonDao.findByStudents_Id(tempStudent.getId());
            List<LessonDTO> convertedList = ConvertLessonsListToLessonDTOList.convertLessonToDTO(lessonAssistantTeaches).lessonsList();

            StudentDTO studentDTO = new StudentDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles(),
                    convertedList);
            studentDTOList.add(studentDTO);
        });

        return new SuccessDataResult<>(studentDTOList);
    }

    @Override
    public DataResult<StudentDescribeMe> describeMe(Integer userID) {

        Student student = studentDao.findById(userID).get();

        List<Lessons> lessonsList = lessonDao.findByStudents_Id(userID);
        if(lessonsList.isEmpty() ){
            throw new EntityNotFoundException("No Lesson or Student found");
        }

        List<Homework> tempHomeworks=new ArrayList<>(); //ALL THE HOMEWORKS THAT STUDENT RESPONSIBLE
        lessonsList.forEach((tempLesson)-> tempHomeworks.addAll(tempLesson.getHomeworkList()));

        List<Exams> tempExams=new ArrayList<>(); //ALL THE EXAMS THAT STUDENT RESPONSIBLE
        lessonsList.forEach((tempLesson)->{
            List<Exams> exams=examDao.findAllByLesson_Id(tempLesson.getId());
            tempExams.addAll(exams);
        });

        // Exam Submissions That Student Performed
        List<ExamSubmissions> tempExamSubmissions = new ArrayList<>(examSubmissionDao.findAllBySubmissioner_Id(userID));

        // Homework Submissions that Student Performed
        List<HomeworkSubmissions> tempHomeworkSubmissions = new ArrayList<>(homeworkSubmissionDao.findAllByOwner_Id(userID));

        //DTO CONVERSION TIME
        List<ExamDTO> examDTOList= ExamListToExamDTOList.convert(tempExams).examDTOList();
        List<ExamSubmissionDTO> examSubmissionsList= ConvertExamSubmissionListToExamSubmissionListDTO.convert(tempExamSubmissions).examSubmissionsList();
        List<HomeworkDTO> homeworkDTOList= HomeworkListToHomeworkDtoList.convertToDTO(tempHomeworks).homeworkDTOList();
        List<HomeworkSubmissionDTO> homeworkSubmissionDTOList= HomeworkSubmissionListToSubmissionDTOList.convert(tempHomeworkSubmissions).homeworkSubmissionsList();
        List<LessonDTO> lessonsEnrolled= ConvertLessonsListToLessonDTOList.convertLessonToDTO(student.getLessons()).lessonsList();

                StudentDescribeMe studentDescribeMe = new StudentDescribeMe(
                student.getId(),
                student.getFirstName(),
                student.getEmail(),
                student.getUsername(),
                student.getLastName(),
                student.getRoles(),
                        examDTOList,
                        examSubmissionsList,
                        homeworkDTOList,
                        homeworkSubmissionDTOList,
                        lessonsEnrolled
        );
                return new SuccessDataResult<>(studentDescribeMe,"Successfully Fetched");
    }

    @Override
    public List<StudentDTO> getStudentsNotLessonsAttendedToGivenLessonCode(Integer lessonCode) {
        List<Student> students = studentDao.findAll();
        Optional<Lessons> lesson = lessonDao.findById(lessonCode);
        List<StudentDTO> StudentDTOList = new ArrayList<>();

        if (lesson.isEmpty()) {
            throw new UsernameNotFoundException("lesson does not exist!");
        }

        List<Student> studentToSentToDtoFunction = new ArrayList<>();

        lesson.get().getStudents().forEach((tempLessonAttentedStudent -> {
            students.forEach((tempStudent) -> {
                if (tempLessonAttentedStudent.getId() != tempStudent.getId()) {
                    studentToSentToDtoFunction.add(tempStudent);
                }
            });
        }));

        StudentDTOList = returnListOfStudents(studentToSentToDtoFunction);
        return StudentDTOList;
    }

    @Override
    public List<StudentDTO> returnListOfStudents(List<Student> StudentList) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        StudentList.forEach((tempAssistant) -> {
            StudentDTO studentDTO = new StudentDTO(tempAssistant.getId(),
                    tempAssistant.getFirstName(),
                    tempAssistant.getEmail(),
                    tempAssistant.getUsername(),
                    tempAssistant.getLastName(),
                    tempAssistant.getRoles(), null);
            studentDTOList.add(studentDTO);
        });
        return studentDTOList;
    }
}