package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.ProfessorDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Professor;

public record ConvertProfessorToProfessorDTO(
        ProfessorDTO professorDTO
) {
    public static ConvertProfessorToProfessorDTO convertProfessorToDTO(Professor professor){
        return new ConvertProfessorToProfessorDTO(
                new ProfessorDTO(
                        professor.getId(),
                        professor.getFirstName(),
                        professor.getEmail(),
                        professor.getUsername(),
                        professor.getLastName(),
                        professor.getRoles(),
                        null
                )
        );
    }
}
