package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.UserDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.User;

public record ConvertUserToUserDTO(
        UserDTO userDTO
) {
    public static ConvertUserToUserDTO convert(User user){
        return new ConvertUserToUserDTO(
                new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getEmail(),
                        user.getLastName(),
                        user.getRoles()
                )
        );
    }
}
