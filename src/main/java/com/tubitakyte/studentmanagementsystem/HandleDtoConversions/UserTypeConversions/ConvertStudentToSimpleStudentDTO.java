package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.SimpleStudentDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;

public record ConvertStudentToSimpleStudentDTO(
        SimpleStudentDTO simpleStudentDTO
) {
    public static ConvertStudentToSimpleStudentDTO convert(Student student){
        return new ConvertStudentToSimpleStudentDTO(
                new SimpleStudentDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getEmail(),
                        student.getLastName(),
                        student.getRoles()
                )
        );
    }
}
