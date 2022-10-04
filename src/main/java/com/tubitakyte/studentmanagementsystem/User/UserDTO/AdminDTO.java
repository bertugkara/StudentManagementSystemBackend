package com.tubitakyte.studentmanagementsystem.User.UserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminDTO extends UserDTO {
    public AdminDTO(Integer id, String firstName, String email, String lastName) {
        super(id, firstName, email, lastName);
    }
}
