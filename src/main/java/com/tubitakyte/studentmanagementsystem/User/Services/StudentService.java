package com.tubitakyte.studentmanagementsystem.User.Services;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.StudentDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;

public interface StudentService extends UserDTOservice<StudentDTO> {

    List<StudentDTO> getStudentsNotLessonsAttendedToGivenLessonCode(Integer lessonCode);

    List<StudentDTO> returnListOfStudents(List<Student> studentList);

    DataResult<StudentDescribeMe> describeMe(Integer userID);
}
