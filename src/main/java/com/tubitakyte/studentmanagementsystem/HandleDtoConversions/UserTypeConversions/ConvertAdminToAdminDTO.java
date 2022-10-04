package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.AdminDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Admin;

public record ConvertAdminToAdminDTO(
        AdminDTO adminDTO
) {
    public static ConvertAdminToAdminDTO convert(Admin admin){
        return new ConvertAdminToAdminDTO(
                new AdminDTO(
                        admin.getId(),
                        admin.getFirstName(),
                        admin.getEmail(),
                        admin.getLastName()
                )
        );
    }
}
