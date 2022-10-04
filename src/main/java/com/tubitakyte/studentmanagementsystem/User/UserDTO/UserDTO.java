package com.tubitakyte.studentmanagementsystem.User.UserDTO;


import com.tubitakyte.studentmanagementsystem.Role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    private String firstName;

    private String email;

    private String username;

    private String lastName;

    private Set<Role> Role;

    public UserDTO(Integer id, String firstName, String email, String lastName,Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.Role=roles;
    }
    public UserDTO(Integer id, String firstName, String email, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }
}
