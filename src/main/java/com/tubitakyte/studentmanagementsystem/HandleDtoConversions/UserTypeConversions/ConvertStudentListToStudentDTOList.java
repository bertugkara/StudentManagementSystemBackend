package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;

import java.util.ArrayList;
import java.util.List;

public record ConvertStudentListToStudentDTOList(
        List<StudentDTO> studentDTOList
) {
    public static ConvertStudentListToStudentDTOList convertStudentListToDTOList(List<Student> students){
        List<StudentDTO> studentDTOList = new ArrayList<>();
        students.forEach((tempStudent) -> {
            StudentDTO tempStu = ConvertStudentToStudentDTO.convertStudent(tempStudent).studentDTO();
            studentDTOList.add(tempStu);
        });
        return new ConvertStudentListToStudentDTOList(studentDTOList);
    }
}
