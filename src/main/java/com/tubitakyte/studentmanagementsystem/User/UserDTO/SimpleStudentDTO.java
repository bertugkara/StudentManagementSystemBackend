package com.tubitakyte.studentmanagementsystem.User.UserDTO;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class SimpleStudentDTO extends UserDTO{
    public SimpleStudentDTO(Integer id, String firstName, String email, String lastName, Set<com.tubitakyte.studentmanagementsystem.Role.entity.Role> roles) {
        super(id, firstName, email, lastName, roles);
    }
}
