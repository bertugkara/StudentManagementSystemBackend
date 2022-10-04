package com.tubitakyte.studentmanagementsystem.Lesson.ServicesImpl;

import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertAssistantListToTheDtoList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertProfessorToProfessorDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertStudentListToStudentDTOList;
import com.tubitakyte.studentmanagementsystem.Homework.DataAccess.HomeworkDao;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import com.tubitakyte.studentmanagementsystem.Lesson.DataAccess.LessonDao;
import com.tubitakyte.studentmanagementsystem.Lesson.LessonDTO.LessonDTO;
import com.tubitakyte.studentmanagementsystem.Lesson.Services.LessonService;
import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import com.tubitakyte.studentmanagementsystem.Lesson.enums.LessonType;
import com.tubitakyte.studentmanagementsystem.Rooms.DataAccess.RoomDao;
import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import com.tubitakyte.studentmanagementsystem.TimeTable.DataAccess.TimeTableDao;
import com.tubitakyte.studentmanagementsystem.TimeTable.Entity.TimeTable;
import com.tubitakyte.studentmanagementsystem.TimeTable.Service.TimeTableService;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.AssistantDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.ProfessorDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Professor;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests.AttachHomeworkToLessonRequest;
import com.tubitakyte.studentmanagementsystem.common.requests.LessonRelatedRequest.*;
import com.tubitakyte.studentmanagementsystem.utilities.*;
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
public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;

    private final RoomDao roomDao;

    private final UserDao<Professor> professorUserDao;

    private final UserDao<Assistant> assistantUserDao;

    private final UserDao<Student> studentUserDao;

    private final TimeTableDao timeTableDao;

    private final HomeworkDao homeworkDao;

    private final TimeTableService timeTableService;

    @Override
    @Transactional
    public DataResult<LessonDTO> addLesson(LessonCreateRequest request) {

        LessonType lessonType;
        switch (request.getLessonType()) {
            case "TYPE_COMPULSORY" -> lessonType = LessonType.TYPE_COMPULSORY;
            case "TYPE_ELECTIVE" -> lessonType = LessonType.TYPE_ELECTIVE;
            default -> throw new IllegalStateException("Unexpected value: " + request.getLessonType());
        }
        Optional<Room> room = roomDao.findById(request.getRoomID());

        List<TimeTable> times = new ArrayList<>();
        times = getIntegerListAndConvertToTimeTableList(request.getTimeIDList(), times);

        Optional<Professor> prof = professorUserDao.findById(request.getInstructorID());
        if (!prof.isPresent()) {
            throw new UsernameNotFoundException("Proffesor with The id: " + request.getInstructorID() + " could not found!!!");
        }
        Professor professor = prof.get();

        List<Assistant> assistantSet = new ArrayList<>();
        List<Integer> integerSet = request.getAssistantList();
        assistantSet = getIntegerListAndConvertToAssistantList(integerSet, assistantSet);

        List<Student> studentSet = new ArrayList<>();
        List<Integer> integerList = request.getStudentList();
        studentSet = getIntegerListAndConvertToStudentList(integerList, studentSet);

        Lessons lesson = new Lessons(
                request.getName(),
                request.getDescription(),
                lessonType, request.getLessonCode(), room.get(),
                times,
                professor,
                assistantSet,
                studentSet);

        lessonDao.save(lesson);

        timeTableService.updateTimeTableAsUsed(lesson);

        return new SuccessDataResult<>(getOneLesson(lesson.getId()).getData(), "Lesson is Successfully Listed");

    }


    @Override
    public DataResult<List<LessonDTO>> getAll() {
        List<Lessons> lessonsList = lessonDao.findAll();
        List<LessonDTO> lessonDTOList = new ArrayList<>();
        if (lessonsList == null) {
            throw new UsernameNotFoundException("no lesson list found");
        }
        lessonsList.forEach((temp) -> {

            Room room = temp.getRoom();

            List<TimeTable> timeTable = temp.getTimeTable();

            List<StudentDTO> students = new ArrayList<>();
            students = ConvertStudentListToStudentDTOList.convertStudentListToDTOList(temp.getStudents()).studentDTOList();

            List<AssistantDTO> assistants = new ArrayList<>();
            assistants = ConvertAssistantListToTheDtoList.convertAssistantListToAssistantDtoList(temp.getAssistants()).assistantDTOList();

            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO = ConvertProfessorToProfessorDTO.convertProfessorToDTO(temp.getInstructor()).professorDTO();

            LessonDTO lessonDTO = new LessonDTO(
                    temp.getId(),
                    temp.getName(),
                    temp.getDescription(),
                    temp.getLessonType().toString(),
                    temp.getLessonCode(),
                    room,
                    timeTable,
                    assistants,
                    students,
                    professorDTO
            );

            lessonDTOList.add(lessonDTO);
        });
        return new SuccessDataResult<>(lessonDTOList, "Lessons are successfully Listed");
    }

    @Override
    public DataResult<LessonDTO> getOneLesson(int id) {
        Optional<Lessons> lesson = lessonDao.findById(id);

        if (!lesson.isPresent()) {
            throw new UsernameNotFoundException("no lesson found");
        }

        List<StudentDTO> students = new ArrayList<>();
        students = ConvertStudentListToStudentDTOList.convertStudentListToDTOList(lesson.get().getStudents()).studentDTOList();


        List<AssistantDTO> assistants = new ArrayList<>();
        assistants = ConvertAssistantListToTheDtoList.convertAssistantListToAssistantDtoList(lesson.get().getAssistants()).assistantDTOList();


        ProfessorDTO professorDTO = ConvertProfessorToProfessorDTO.convertProfessorToDTO(lesson.get().getInstructor()).professorDTO();

        LessonDTO lessonDTO = new LessonDTO(
                lesson.get().getId(),
                lesson.get().getName(),
                lesson.get().getDescription(),
                lesson.get().getLessonType().toString(),
                lesson.get().getLessonCode(),
                lesson.get().getRoom(),
                lesson.get().getTimeTable(),
                assistants,
                students,
                professorDTO
        );

        return new SuccessDataResult<>(lessonDTO, "Lesson is Listed");

    }

    @Override
    public DataResult<LessonDTO> addAnAssistantToLesson(AddAssistantToLessonRequest request) {

        Optional<Lessons> lesson = lessonDao.findById(request.getLessonCode());

        List<Assistant> assistants = new ArrayList<>();
        assistants = getIntegerListAndConvertToAssistantList(request.getAssistantList(), assistants);

        if (!lesson.isPresent() || assistants.isEmpty()) {
            throw new UsernameNotFoundException("No Lesson or Assistant has been founded");
        }

        List<Assistant> existingAssistants = lesson.get().getAssistants();
        existingAssistants.addAll(assistants);
        lesson.get().updateAssistant(existingAssistants);
        lessonDao.save(lesson.get());

        return new SuccessDataResult<>(getOneLesson(lesson.get().getId()).getData(), "Succesfully Updated");
    }

    @Override
    public DataResult<LessonDTO> lessonUpdate(LessonUpdateRequest request) {

        Optional<Lessons> lesson = lessonDao.findById(request.getId());
        if (!lesson.isPresent()) {
            throw new EntityNotFoundException("Lesson could not found!");
        }
        LessonType lessonType;
        switch (request.getLessonType()) {
            case "TYPE_COMPULSORY" -> lessonType = LessonType.TYPE_COMPULSORY;
            case "TYPE_ELECTIVE" -> lessonType = LessonType.TYPE_ELECTIVE;
            default -> throw new IllegalStateException("Unexpected value: " + request.getLessonType());
        }

        Room room = roomDao.findById(request.getRooms()).get();

        //Compare the Updated Time IDlist from request and compare lesson times in database
        //If new time added, block it from database so that no lesson will use it,
        // and If a time dropped we must let it free so that other lessons can choose that hour because It is no longer used.
        List<TimeTable> times = new ArrayList<>();
        times = getIntegerListAndConvertToTimeTableList(request.getTimeIDList(), times);
        lesson.get().deleteTimeTable();
        timeTableService.setTimeTableFree(request.getTimeIDList());

        Optional<Professor> prof = professorUserDao.findById(request.getInstructorID());
        if (!prof.isPresent()) {
            throw new UsernameNotFoundException("Proffesor with The id: " + request.getInstructorID() + " could not found!!!");
        }
        Professor professor = prof.get();

        List<Assistant> assistantSet = new ArrayList<>();
        List<Integer> integerSet = request.getAssistantList();
        assistantSet = getIntegerListAndConvertToAssistantList(integerSet, assistantSet);

        List<Student> studentSet = new ArrayList<>();
        List<Integer> integerList = request.getStudentList();
        studentSet = getIntegerListAndConvertToStudentList(integerList, studentSet);

        Lessons updateLesson = new Lessons(
                request.getName(),
                request.getDescription(),
                lessonType,
                request.getLessonCode(),
                room,
                times,
                professor,
                assistantSet,
                studentSet
        );

        lesson.get().updateLesson(updateLesson);
        lessonDao.save(lesson.get());

        timeTableService.updateTimeTableAsUsed(updateLesson);

        return new SuccessDataResult<>(getOneLesson(lesson.get().getId()).getData(), "Successfully Updated");
    }

    @Override
    public DataResult<LessonDTO> addHomeworkToLesson(AttachHomeworkToLessonRequest request) {
        Optional<Lessons> lessons = lessonDao.findById(request.getLessonID());
        List<Homework> currentList = new ArrayList<>();

        Optional<Homework> homework = homeworkDao.findById(request.getHomeworkID());

        currentList=lessons.get().getHomeworkList();
        currentList.add(homework.get());

        lessons.get().attachHomeworkList(currentList);

        lessonDao.save(lessons.get());

        return new SuccessDataResult("Homework Successfully Attached");
    }

    @Override
    public DataResult<LessonDTO> updateLessonForProfessor(LessonUpdateRequestFromProfessor request) {
        Optional<Lessons> lesson = lessonDao.findById(request.getId());
        if (!lesson.isPresent()) {
            throw new EntityNotFoundException("Lesson could not found!");
        }
        Room room = roomDao.findById(request.getRooms()).get();

        //Compare the Updated Time IDlist from request and compare lesson times in database
        //If new time added, block it from database so that no lesson will use it,
        // and If a time dropped we must let it free so that other lessons can choose that hour because It is no longer used.
        List<TimeTable> times = new ArrayList<>();
        times = getIntegerListAndConvertToTimeTableList(request.getTimeIDList(), times);
        lesson.get().deleteTimeTable();
        timeTableService.setTimeTableFree(request.getTimeIDList());

        List<Assistant> assistantSet = new ArrayList<>();
        List<Integer> integerSet = request.getAssistantList();
        assistantSet = getIntegerListAndConvertToAssistantList(integerSet, assistantSet);

        Lessons updateLesson = new Lessons(
                request.getName(),
                request.getDescription(),
                lesson.get().getLessonType(),
                lesson.get().getLessonCode(),
                room,
                times,
                lesson.get().getInstructor(),
                assistantSet,
                lesson.get().getStudents()
        );

        lesson.get().updateLessonForProfessor(updateLesson);
        lessonDao.save(lesson.get());

        timeTableService.updateTimeTableAsUsed(updateLesson);

        return new SuccessDataResult<>(getOneLesson(lesson.get().getId()).getData(), "Successfully Updated");
    }

    @Override
    public DataResult<LessonDTO> updateLessonForAssistant(LessonUpdateRequestFromAssistant request) {
        Optional<Lessons> lesson = lessonDao.findById(request.getId());
        if (!lesson.isPresent()) {
            throw new EntityNotFoundException("Lesson could not found!");
        }
        Room room = roomDao.findById(request.getRooms()).get();

        Lessons updateLesson = new Lessons(
                request.getName(),
                request.getDescription(),
                lesson.get().getLessonType(),
                lesson.get().getLessonCode(),
                room,
                lesson.get().getTimeTable(),
                lesson.get().getInstructor(),
                lesson.get().getAssistants(),
                lesson.get().getStudents()
        );

        lesson.get().updateLessonForAssistant(updateLesson);
        lessonDao.save(lesson.get());
        return new SuccessDataResult<>(getOneLesson(lesson.get().getId()).getData(), "Successfully Updated");

    }

    @Override
    public Result addStudentToTheLesson(Integer lessonID, Integer studentID) {

        Optional<Lessons> lesson=lessonDao.findById(lessonID);
        if(!lesson.isPresent()){
            throw new EntityNotFoundException("lesson could not found");
        }
        Optional<Student> student=studentUserDao.findById(studentID);
        if(!student.isPresent()){
            throw new EntityNotFoundException("student could not found");

        }

        lesson.get().getStudents().forEach((tempStudent)->{
           if( tempStudent.getId()==studentID ) {
               throw new RuntimeException("This user already Signed Up to This lesson!");}
        });

        lesson.get().addStudent(student.get());
        lessonDao.save(lesson.get());

        return new SuccessResult("Student Successfully Added");
    }

    public List<Assistant> getIntegerListAndConvertToAssistantList(List<Integer> integerSet, List<Assistant> assistantSet) {

        integerSet.forEach(tempInt -> {
            Optional<Assistant> assistant = assistantUserDao.findById(tempInt);
            if (assistant.isPresent()) {
                Assistant tempAssistant = assistant.get();
                assistantSet.add(tempAssistant);
            }
        });
        return assistantSet;
    }

    public List<Student> getIntegerListAndConvertToStudentList(List<Integer> integerSet, List<Student> studentSet) {

        integerSet.forEach(tempID -> {
            Optional<Student> student = studentUserDao.findById(tempID);
            if (student.isPresent()) {
                Student tempStudent = student.get();
                studentSet.add(tempStudent);
            }
        });
        return studentSet;
    }

    public List<TimeTable> getIntegerListAndConvertToTimeTableList(List<Integer> integerList, List<TimeTable> timeTableList) {
        integerList.forEach((tempID) -> {
            Optional<TimeTable> timeTable = timeTableDao.findById(tempID);
            if (timeTable.isPresent()) {
                TimeTable tempTable = timeTable.get();
                timeTableList.add(tempTable);
            }
        });
        return timeTableList;
    }

//    //We have to set the new time as using and let the old time as free.
//    public List<Integer> compareToTimeTableAndCollectTheDifference(List<TimeTable> currentTimeTable, List<Integer> newTimeTableList) {
//        List<Integer> freeList = new ArrayList<>();
//
//        currentTimeTable.forEach((tempCurrentTimeTable) -> {
//            newTimeTableList.forEach((tempNewTimeTable) -> {
//                if (tempNewTimeTable == tempCurrentTimeTable.getId()) {
//                } else {
//                    freeList.add(tempCurrentTimeTable.getId());
//                }
//            });
//        });
//        System.out.println((freeList));
//        return freeList;
//    }
}
