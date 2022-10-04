package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.StudentDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;

public record ConvertStudentToStudentDTO(
        StudentDTO studentDTO
) {
    public static ConvertStudentToStudentDTO convertStudent(Student student) {

        return new ConvertStudentToStudentDTO(
                new StudentDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getEmail(),
                        student.getUsername(),
                        student.getLastName(),
                        student.getRoles(),
                        null
                )
        );
    }
}
