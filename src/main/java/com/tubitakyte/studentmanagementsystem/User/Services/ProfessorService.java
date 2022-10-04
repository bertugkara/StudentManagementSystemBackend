package com.tubitakyte.studentmanagementsystem.User.Services;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.ProfessorDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.ProfessorDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Professor;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;


public interface ProfessorService extends UserDTOservice<ProfessorDTO> {
    List<ProfessorDTO> getProfessorsNotLessonsAttendedToGivenLessonCode(Integer lessonCode);

    List<ProfessorDTO> returnListOfProfessor(List<Professor> professorList);

    DataResult<ProfessorDescribeMe> describeMe(Integer userID);
}
